resource "aws_vpc" "skybook" {
  cidr_block           = var.vpc_cidr
  enable_dns_support   = true
  enable_dns_hostnames = true

  tags = {
    Name = "SkyBook-VPC"
  }
}

resource "aws_internet_gateway" "igw" {
  vpc_id = aws_vpc.skybook.id

  tags = {
    Name = "SkyBook-IGW"
  }
}

resource "aws_subnet" "public" {
  vpc_id                  = aws_vpc.skybook.id
  cidr_block              = var.public_subnet_cidr
  availability_zone       = "ap-south-1a"
  map_public_ip_on_launch = true

  tags = {
    Name = "Public-Subnet"
  }
}

resource "aws_subnet" "private" {
  vpc_id            = aws_vpc.skybook.id
  cidr_block        = var.private_subnet_cidr
  availability_zone = "ap-south-1b"

  tags = {
    Name = "Private-Subnet"
  }
}

resource "aws_route_table" "public_rt" {
  vpc_id = aws_vpc.skybook.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.igw.id
  }

  tags = {
    Name = "Public-RT"
  }
}

resource "aws_route_table_association" "public_assoc" {
  subnet_id      = aws_subnet.public.id
  route_table_id = aws_route_table.public_rt.id
}


