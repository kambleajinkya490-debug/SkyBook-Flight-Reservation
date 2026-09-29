resource "aws_security_group" "rds_sg" {
  name        = "SkyBook-RDS-SG"
  description = "Security group for SkyBook RDS PostgreSQL"
  vpc_id      = aws_vpc.skybook.id

  ingress {
    description = "PostgreSQL from VPC"
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = [var.vpc_cidr]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "SkyBook-RDS-SG"
  }
}

resource "aws_db_subnet_group" "skybook" {
  name = "skybook-rds-subnet-group"

  subnet_ids = [
    aws_subnet.private.id,
    aws_subnet.eks_private_a.id
  ]

  tags = {
    Name = "SkyBook-RDS-Subnet-Group"
  }
}

resource "aws_db_instance" "skybook" {
  identifier = "skybook-postgres"

  engine         = "postgres"
  engine_version = "16"

  instance_class        = "db.t3.micro"
  allocated_storage     = 20
  max_allocated_storage = 50
  storage_type          = "gp3"

  db_name  = "skybook"
  username = "skybookadmin"
  password = var.db_password

  port = 5432

  db_subnet_group_name   = aws_db_subnet_group.skybook.name
  vpc_security_group_ids = [aws_security_group.rds_sg.id]

  publicly_accessible = false
  skip_final_snapshot = true
  deletion_protection = false

  backup_retention_period = 0

  tags = {
    Name = "SkyBook-RDS"
  }
}
