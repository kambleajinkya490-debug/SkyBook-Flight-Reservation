# ✈️ SkyBook – Flight Reservation System

<p align="center">
  <b>Cloud-Native Flight Reservation System with DevOps & DevSecOps</b>
</p>

<p align="center">
  Java Spring Boot • React • PostgreSQL • Docker • Kubernetes • Jenkins • AWS
</p>

---

## 📌 Project Overview

**SkyBook** is a production-style Flight Reservation System developed using
**Java Spring Boot, React, PostgreSQL, Docker, Kubernetes, Jenkins, Terraform and AWS**.

The project demonstrates a complete cloud-native workflow including:

- Application development
- Docker containerization
- Infrastructure as Code
- CI/CD automation
- DevSecOps security scanning
- Kubernetes deployment
- AWS cloud deployment
- Application monitoring

---

## ✈️ Application Features

### 👤 User Features

- User Registration
- User Login
- JWT Authentication
- Flight Search
- Flight Details
- Seat Availability
- Seat Selection
- Flight Booking
- PNR Generation
- Ticket Generation
- PDF Ticket Download


---

# 🏗️ System Architecture

```text
                         ┌──────────────────┐
                         │      USERS       │
                         └────────┬─────────┘
                                  │
                                  ▼
                     ┌───────────────────────┐
                     │     AWS ALB /         │
                     │       Ingress         │
                     └───────────┬───────────┘
                                 │
                    ┌────────────┴────────────┐
                    │                         │
                  /api                        /
                    │                         │
                    ▼                         ▼
          ┌─────────────────┐       ┌─────────────────┐
          │     Backend     │       │    Frontend     │
          │  Spring Boot    │       │ React + Vite    │
          │     :8081       │       │     :5173       │
          └────────┬────────┘       └─────────────────┘
                   │
                   ▼
          ┌─────────────────┐
          │   Amazon RDS    │
          │   PostgreSQL    │
          └─────────────────┘


Developer
    │
    ▼
┌──────────────┐
│    GitHub    │
└──────┬───────┘
       │
       ▼
┌──────────────┐
│    Jenkins   │
└──────┬───────┘
       │
       ├──► Gradle Build
       ├──► SonarQube Analysis
       ├──► Trivy Security Scan
       ├──► Docker Build
       ├──► Push Images to ECR
       └──► Deploy to EKS
       
