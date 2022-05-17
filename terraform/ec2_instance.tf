provider "aws" {
  region     = "${{ secrets.AWS_REGION }}"
  access_key = "${{ secrets.AWS_ACCESS_KEY_ID }}"
  secret_key = "${{ secrets.AWS_SECRET_ACCESS_KEY }}"
}

variable "key_name" {
  description = "Which is used for a name of key pair"
  default     = "employee-portal-key"
}

resource "tls_private_key" "rsa-key" {
  algorithm = "RSA"
  rsa_bits  = 4096
}

resource "aws_key_pair" "generated-key" {
  key_name   = var.key_name
  public_key = tls_private_key.rsa-key.public_key_openssh

  provisioner "local-exec" {
    command = "echo ${tls_private_key.rsa-key.private_key_pem} > ${var.key_name}.pem"
  }
  provisioner "local-exec" {
    command = "chmod 400 ${var.key_name}.pem"
  }
}

resource "aws_security_group" "employee-portal-security-groups" {
  name        = "employee-portal-security-groups"
  description = "employee-portal-security-groups"
  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
  ingress {
    from_port   = 3000
    to_port     = 3000
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    "name" = "security-group-employee"
  }
}

resource "aws_instance" "employee-portal" {
  ami                    = "${{ secrets.AWS_AMI }}"
  instance_type          = "t2.micro"
  key_name               = var.key_name
  vpc_security_group_ids = [aws_security_group.employee-portal-security-groups.id]
  tags = {
    Name = "employee-portal"
  }
   user_data = local.cloud_config_config
}

locals {
  cloud_config_config = <<-END
    #cloud-config
    ${jsonencode({
      write_files = [
        {
          path        = "/home/ubuntu/deploy_to_aws.yml"
          permissions = "600"
          owner       = "root:root"
          encoding    = "b64"
          content     = filebase64("../deploy_to_aws.yml")
        },
        {
          path        = "/home/ubuntu/docker-compose.yml"
          permissions = "600"
          owner       = "root:root"
          encoding    = "b64"
          content     = filebase64("../docker-compose.yml")
        }
      ]
      runcmd = [
        "ansible-playbook deploy_to_aws.yml"]
    })}
  END
}