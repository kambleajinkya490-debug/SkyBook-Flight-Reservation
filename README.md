SkyBook – Flight Reservation System

A cloud-native Flight Reservation System built with React, Spring Boot, PostgreSQL, Docker, Kubernetes, AWS, Jenkins, SonarQube, Trivy, Prometheus, and Grafana.

🚀 Project Overview

SkyBook allows users to:

Register and login

Search available flights

Select seats

Book flights

Generate PNR

View/download flight tickets as PDF

The application is deployed on AWS using Amazon EKS, Amazon RDS PostgreSQL, Amazon ECR, and an AWS Application Load Balancer.

🏗️ Architecture

GitHub
  |
  v
Jenkins
  |
  +--> SonarQube
  +--> Trivy
  |
  v
Amazon ECR
  |
  v
Amazon EKS
  |
  +--> Frontend Pod
  |
  +--> Backend Pod
          |
          v
      Amazon RDS

EKS --> Prometheus --> Grafana

🛠️ Technology Stack

Frontend

React

Vite

JavaScript

Backend

Java 21

Spring Boot

Spring Data JPA

Spring Security

JWT

Gradle

Database

PostgreSQL

Amazon RDS

DevOps / Cloud

AWS EC2

Amazon EKS

Amazon ECR

AWS Application Load Balancer

Docker

Kubernetes

Jenkins

Terraform

DevSecOps

SonarQube

Trivy

Monitoring

Prometheus

Grafana

📁 Project Structure

SkyBook-Flight-Reservation/
├── backend/
├── frontend/
├── k8s/
├── terraform/
├── Jenkinsfile
└── README.md

🔄 CI/CD Pipeline

Developer Push
      |
    GitHub
      |
    Jenkins
      |
      +--> Checkout
      +--> Build Backend
      +--> SonarQube Analysis
      +--> Trivy Scan
      +--> Docker Build
      +--> Trivy Image Scan
      +--> Push Images to ECR
      +--> Configure EKS
      +--> Deploy Backend
      +--> Deploy Frontend
      |
      v
Verify Deployment

☸️ Kubernetes Architecture

Internet
   |
   v
AWS Application Load Balancer
   |
   +---- /api ---> Backend Service ---> Backend Pod
   |
   +---- / ------> Frontend Service -> Frontend Pod
                                      |
                                      v
                               Amazon RDS PostgreSQL

📊 Monitoring

Prometheus collects Kubernetes/application metrics such as:

CPU usage

Memory usage

Pod status

Pod restarts

Node metrics

Network metrics

Grafana visualizes the Prometheus metrics using dashboards.

EKS
 |
 v
Prometheus
 |
 v
Grafana
 |
 v
Monitoring Dashboard

🔐 Security

The project includes:

JWT authentication

BCrypt password hashing

Spring Security

SonarQube static analysis

Trivy vulnerability scanning

AWS IAM

Private RDS database

✈️ Application Flow

User
 |
 v
Login / Register
 |
 v
Search Flights
 |
 v
Select Flight
 |
 v
Select Seat
 |
 v
Create Booking
 |
 v
Generate PNR
 |
 v
Generate Ticket
 |
 v
Download PDF

🧪 Testing

Tested flows include:

User registration

User login

Flight creation

Flight listing/search

Seat availability

Seat booking

PNR generation

Ticket generation

PDF ticket download

Frontend/backend communication through ALB

☁️ AWS Components

VPC

Public/private subnets

Internet Gateway

NAT Gateway

EC2 Jenkins server

Amazon EKS

Amazon ECR

Amazon RDS PostgreSQL

Application Load Balancer

IAM

📌 Future Improvements

AWS Secrets Manager / Kubernetes Secrets

Unique Docker image tags

Automated unit/integration tests

Jenkins quality gates

Prometheus alerting

Grafana alerts

HTTPS with ACM

Route 53 domain

Horizontal Pod Autoscaler

Application-specific Prometheus metrics

👨‍💻 Author

Ajinkya Kamble

Cloud & DevOps Engineer 

Skills: AWS | Docker | Kubernetes | Jenkins | Terraform | Linux | CI/CD | DevSecOps | Prometheus | Grafana
