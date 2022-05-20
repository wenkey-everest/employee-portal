terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "3.26.0"
    }
  }
  required_version = ">= 1.1.0"

  cloud {
    organization = "api-login"

    workspaces {
      name = "employee-portal"
    }
  }
}

variable "AWS_REGION" {
  default     = "us-west-2"
  description = "The AWS region to use"
}

variable "AWS_AMI" {
  description = "The AMI to use"
  default     = "ami-0ee8244746ec5d6d4"
}

provider "aws" {
  region = var.AWS_REGION
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
    command = "echo \"${tls_private_key.rsa-key.private_key_pem}\" > ${var.key_name}.pem"
  }
  provisioner "local-exec" {
    command = "chmod 600 ${var.key_name}.pem"
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
  ami                    = var.AWS_AMI
  instance_type          = "t2.micro"
  key_name               = var.key_name
  vpc_security_group_ids = [aws_security_group.employee-portal-security-groups.id]
  tags = {
    Name = "employee-portal"
  }
  user_data = local.cloud_config_config
}

resource "aws_ssm_parameter" "secret" {
  name        = "public_ip"
  description = "It contains public ip of instance directly"
  type        = "SecureString"
  value       = aws_instance.employee-portal.public_ip
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
      content     = filebase64("${path.root}/deploy_to_aws.yml")
    },
    {
      path        = "/home/ubuntu/docker-compose.yml"
      permissions = "600"
      owner       = "root:root"
      encoding    = "b64"
      content     = filebase64("${path.root}/docker-compose.yml")
    }
  ],
  runcmd = [
    "sudo -s",
    "sudo apt update && sudo apt upgrade -y",
    "sudo apt install -y ansible",
    "chmod +x /home/ubuntu/deploy_to_aws.yml",
    "ansible-playbook /home/ubuntu/deploy_to_aws.yml"
  ]
})}
  END
} 
