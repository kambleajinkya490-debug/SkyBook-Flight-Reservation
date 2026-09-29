output "vpc_id" {
  value = aws_vpc.skybook.id
}

output "public_subnet" {
  value = aws_subnet.public.id
}

output "private_subnet" {
  value = aws_subnet.private.id
}

output "jenkins_public_ip" {
  value = aws_instance.jenkins.public_ip
}

output "jenkins_dns" {
  value = aws_instance.jenkins.public_dns
}
