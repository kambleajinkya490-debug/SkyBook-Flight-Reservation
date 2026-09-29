
resource "aws_subnet" "eks_public_b" {
  vpc_id                  = aws_vpc.skybook.id
  cidr_block              = "10.0.3.0/24"
  availability_zone       = "ap-south-1b"
  map_public_ip_on_launch = true

  tags = {
    Name                                = "EKS-Public-Subnet-B"
    "kubernetes.io/role/elb"            = "1"
    "kubernetes.io/cluster/SkyBook-EKS" = "shared"
  }
}


resource "aws_subnet" "eks_private_a" {
  vpc_id            = aws_vpc.skybook.id
  cidr_block        = "10.0.4.0/24"
  availability_zone = "ap-south-1a"

  tags = {
    Name                                = "EKS-Private-Subnet-A"
    "kubernetes.io/role/internal-elb"   = "1"
    "kubernetes.io/cluster/SkyBook-EKS" = "shared"
  }
}


resource "aws_route_table" "eks_public_rt" {
  vpc_id = aws_vpc.skybook.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.igw.id
  }

  tags = {
    Name = "EKS-Public-RT"
  }
}


resource "aws_route_table_association" "eks_public_b_assoc" {
  subnet_id      = aws_subnet.eks_public_b.id
  route_table_id = aws_route_table.eks_public_rt.id
}


resource "aws_eip" "nat" {
  domain = "vpc"

  tags = {
    Name = "SkyBook-NAT-EIP"
  }
}


resource "aws_nat_gateway" "skybook" {
  allocation_id = aws_eip.nat.id
  subnet_id     = aws_subnet.public.id

  depends_on = [
    aws_internet_gateway.igw
  ]

  tags = {
    Name = "SkyBook-NAT-Gateway"
  }
}


resource "aws_route_table" "eks_private_rt" {
  vpc_id = aws_vpc.skybook.id

  route {
    cidr_block     = "0.0.0.0/0"
    nat_gateway_id = aws_nat_gateway.skybook.id
  }

  tags = {
    Name = "EKS-Private-RT"
  }
}


resource "aws_route_table_association" "existing_private_assoc" {
  subnet_id      = aws_subnet.private.id
  route_table_id = aws_route_table.eks_private_rt.id
}


resource "aws_route_table_association" "eks_private_a_assoc" {
  subnet_id      = aws_subnet.eks_private_a.id
  route_table_id = aws_route_table.eks_private_rt.id
}
