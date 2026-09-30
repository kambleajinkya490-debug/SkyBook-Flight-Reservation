✈️ SkyBook – Flight Reservation System

A cloud-native Flight Reservation System built with React, Spring Boot, PostgreSQL, Docker, Kubernetes, AWS, Jenkins, SonarQube, Trivy, Prometheus, and Grafana.

🚀 Project Overview

SkyBook is a full-stack flight reservation application that allows users to:

Register and login
Search available flights
Select seats
Book flights
Generate PNR
Generate and download flight tickets as PDF

The application is deployed on AWS using Amazon EKS, Amazon RDS PostgreSQL, Amazon ECR, EC2, and AWS Application Load Balancer.

🏗️ System Architecture
                         GitHub
                            |
                            v
                         Jenkins
                            |
              +-------------+-------------+
              |                           |
          SonarQube                     Trivy
              |                           |
              +-------------+-------------+
                            |
                            v
                       Amazon ECR
                            |
                            v
                       Amazon EKS
                  +---------+---------+
                  |                   |
                  v                   v
             Frontend Pod        Backend Pod
                  |                   |
                  |                   v
                  |             Amazon RDS
                  |             PostgreSQL
                  |
                  v
             AWS ALB
                  |
               Internet


              EKS Monitoring
                    |
                    v
                Prometheus
                    |
                    v
                 Grafana
                    |
                    v
            Monitoring Dashboard
🛠️ Technology Stack
Frontend
React
Vite
JavaScript
CSS
Backend
Java 21
Spring Boot
Spring Data JPA
Spring Security
JWT Authentication
Gradle
Database
PostgreSQL
Amazon RDS

PostgreSQL is the database engine used by the application.

Amazon RDS is the AWS managed service hosting the PostgreSQL database.

Spring Boot Backend
        |
        v
Amazon RDS
        |
        v
PostgreSQL
        |
        +--> Users
        +--> Flights
        +--> Bookings
        +--> Seats
        +--> Tickets
DevOps / Cloud
AWS EC2
Amazon EKS
Amazon ECR
AWS Application Load Balancer
Docker
Kubernetes
Jenkins
Terraform
Linux
DevSecOps
SonarQube
Trivy
Monitoring
Prometheus
Grafana
📁 Project Structure
SkyBook-Flight-Reservation/
│
├── backend/
│   ├── src/
│   ├── build.gradle
│   ├── Dockerfile
│   └── gradlew
│
├── frontend/
│   ├── src/
│   ├── package.json
│   ├── Dockerfile
│   └── vite.config.js
│
├── k8s/
│   ├── backend-deployment.yaml
│   ├── backend-service.yaml
│   ├── frontend-deployment.yaml
│   ├── frontend-service.yaml
│   └── ingress.yaml
│
├── terraform/
│   ├── main.tf
│   ├── variables.tf
│   └── terraform.tfvars
│
├── Jenkinsfile
└── README.md
🔄 CI/CD Pipeline

Jenkins automates the build, security scanning, image creation, and Kubernetes deployment.

Developer
    |
    v
  GitHub
    |
    v
 Jenkins
    |
    +--> Checkout
    |
    +--> Build Backend
    |
    +--> SonarQube Analysis
    |
    +--> Trivy File System Scan
    |
    +--> Docker Build
    |
    +--> Trivy Image Scan
    |
    +--> Push Images to Amazon ECR
    |
    +--> Configure EKS
    |
    +--> Deploy Backend
    |
    +--> Deploy Frontend
    |
    v
Verify Deployment
Jenkins Pipeline Stages
Checkout source code from GitHub
Build Spring Boot backend
Run SonarQube code analysis
Run Trivy filesystem scan
Build backend Docker image
Build frontend Docker image
Run Trivy image scans
Push Docker images to Amazon ECR
Configure Kubernetes/EKS
Deploy backend
Deploy frontend
Verify pods, services, and ingress
☁️ AWS Deployment Architecture

The application is deployed using multiple AWS services.

                         Internet
                            |
                            v
                    AWS Application
                    Load Balancer
                            |
                 +----------+----------+
                 |                     |
              /api                    /
                 |                     |
                 v                     v
          Backend Service       Frontend Service
                 |                     |
                 v                     v
           Backend Pod            Frontend Pod
                 |
                 v
        Amazon RDS PostgreSQL
AWS Components
VPC
Public Subnets
Private Subnets
Internet Gateway
NAT Gateway
EC2 Jenkins Server
Amazon EKS
Amazon ECR
Amazon RDS PostgreSQL
Application Load Balancer
IAM
🚀 Deployment Process
1. Infrastructure Provisioning

Terraform is used to provision AWS infrastructure such as:

VPC
 |
 +--> Subnets
 |
 +--> Internet Gateway
 |
 +--> NAT Gateway
 |
 +--> Route Tables
 |
 +--> Jenkins EC2
 |
 +--> EKS
 |
 +--> RDS
 |
 +--> IAM

Typical Terraform workflow:

terraform init
terraform plan
terraform apply
2. Build Docker Images

Backend and frontend are containerized separately.

docker build -t skybook-backend ./backend
docker build -t skybook-frontend ./frontend
3. Push Images to Amazon ECR

The Docker images are pushed to Amazon ECR.

Jenkins
   |
   +--> Backend Image
   |
   +--> Frontend Image
            |
            v
        Amazon ECR
4. Deploy to Amazon EKS

Kubernetes manifests are applied to the EKS cluster.

kubectl apply -f k8s/backend-deployment.yaml
kubectl apply -f k8s/backend-service.yaml
kubectl apply -f k8s/frontend-deployment.yaml
kubectl apply -f k8s/frontend-service.yaml
kubectl apply -f k8s/ingress.yaml
5. AWS Application Load Balancer

AWS Load Balancer Controller creates an internet-facing Application Load Balancer.

Routing:

ALB
 |
 +---- /api/* ----> Backend Service ----> Backend Pod
 |
 +---- /* --------> Frontend Service --> Frontend Pod

This allows users to access the complete application through one public endpoint.

☸️ Kubernetes Deployment

The application uses:

Deployment
Service
Ingress
Config/environment variables
AWS Load Balancer Controller
Backend
Backend Deployment
       |
       v
Backend Pod
       |
       v
Backend Service
Frontend
Frontend Deployment
       |
       v
Frontend Pod
       |
       v
Frontend Service
📊 Monitoring

Prometheus and Grafana are deployed in the Kubernetes monitoring namespace.

Prometheus

Prometheus collects and stores metrics from the Kubernetes environment.

Metrics include:

CPU usage
Memory usage
Pod status
Pod restarts
Node metrics
Network metrics
Grafana

Grafana connects to Prometheus and visualizes the collected metrics through dashboards.

Amazon EKS
    |
    v
Prometheus
    |
    v
Grafana
    |
    v
Monitoring Dashboard
🔐 Security / DevSecOps

The project includes multiple security practices.

Application Security
JWT authentication
BCrypt password hashing
Spring Security
Code Security

SonarQube is used for static code analysis.

Source Code
     |
     v
 SonarQube
     |
     v
Code Quality Analysis
Container Security

Trivy scans the filesystem and Docker images for vulnerabilities.

Docker Image
     |
     v
   Trivy
     |
     v
Vulnerability Scan
AWS Security
IAM roles and policies
Private RDS database
VPC networking
Public/private subnet separation
Security groups
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
Download PDF Ticket
🧪 Testing

Tested application flows include:

User registration
User login
Flight creation
Flight listing/search
Seat availability
Seat selection
Seat booking
PNR generation
Ticket generation
PDF ticket download
Frontend/backend communication through ALB
🗄️ Database Flow
Frontend
   |
   v
Spring Boot Backend
   |
   v
Spring Data JPA
   |
   v
PostgreSQL
   |
   v
Amazon RDS

Main application data includes:

users
flights
bookings
seats
tickets
🐳 Docker

Separate Docker images are created for the frontend and backend.

Frontend Source
      |
      v
Frontend Docker Image
      |
      v
Amazon ECR
      |
      v
Amazon EKS
Backend Source
      |
      v
Backend Docker Image
      |
      v
Amazon ECR
      |
      v
Amazon EKS
📈 Production Deployment Flow
Developer
    |
    v
Git Push
    |
    v
GitHub
    |
    v
Jenkins
    |
    +--> SonarQube
    |
    +--> Trivy
    |
    +--> Docker Build
    |
    v
Amazon ECR
    |
    v
Amazon EKS
    |
    +--> Frontend
    |
    +--> Backend
            |
            v
       Amazon RDS
            |
            v
       PostgreSQL

EKS
 |
 v
Prometheus
 |
 v
Grafana
 |
 v
Monitoring
📌 Future Improvements
AWS Secrets Manager / Kubernetes Secrets
Unique Docker image tags instead of latest
Automated unit/integration tests
Jenkins quality gates
Prometheus alerting
Grafana alerts
HTTPS with AWS ACM
Route 53 domain
Horizontal Pod Autoscaler
Application-specific Prometheus metrics
Backup and disaster recovery improvements
👨‍💻 Author

Ajinkya Kamble

Cloud & DevOps Engineer

Skills: AWS | Docker | Kubernetes | Jenkins | Terraform | Linux | CI/CD | DevSecOps | Prometheus | Grafana
