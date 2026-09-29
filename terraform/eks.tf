
resource "aws_eks_cluster" "skybook" {
  name     = "SkyBook-EKS"
  role_arn = aws_iam_role.eks_cluster_role.arn

  version = "1.33"

  vpc_config {
    subnet_ids = [
      aws_subnet.public.id,
      aws_subnet.private.id,
      aws_subnet.eks_public_b.id,
      aws_subnet.eks_private_a.id
    ]

    endpoint_public_access  = true
    endpoint_private_access = true
  }

  depends_on = [
    aws_iam_role_policy_attachment.eks_cluster_policy
  ]

  tags = {
    Name = "SkyBook-EKS"
  }
}



resource "aws_eks_node_group" "skybook" {
  cluster_name    = aws_eks_cluster.skybook.name
  node_group_name = "SkyBook-Worker-Nodes"

  node_role_arn = aws_iam_role.eks_node_role.arn

  subnet_ids = [
    aws_subnet.private.id,
    aws_subnet.eks_private_a.id
  ]

  instance_types = ["c7i-flex.large"]

  capacity_type = "ON_DEMAND"

  scaling_config {
    desired_size = 1
    min_size     = 1
    max_size     = 2

  }

  disk_size = 20

  depends_on = [
    aws_iam_role_policy_attachment.eks_worker_node_policy,
    aws_iam_role_policy_attachment.eks_cni_policy,
    aws_iam_role_policy_attachment.eks_ecr_policy
  ]

  tags = {
    Name = "SkyBook-EKS-Worker"
  }
}
