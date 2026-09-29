variable "region" {
  default = "ap-south-1"
}

variable "vpc_cidr" {
  default = "10.0.0.0/16"
}

variable "public_subnet_cidr" {
  default = "10.0.1.0/24"
}

variable "private_subnet_cidr" {
  default = "10.0.2.0/24"
}

variable "ami_id" {
  type = string
}

variable "instance_type" {
  default = "c7i-flex.large"
}

variable "key_name" {
  default = "bookmyshow-key"
}
