pipeline {
    agent any

    stages {

        stage('Clone') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/kambleajinkya490-debug/SkyBook-Flight-Reservation.git'
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend') {
                    sh 'chmod +x gradlew'
                    sh './gradlew clean build -x test'
                }
            }
        }

        stage('Build Backend Docker Image') {
            steps {
                sh 'docker build -t skybook-backend:latest ./backend'
            }
        }

        stage('Build Frontend Docker Image') {
            steps {
                sh 'docker build -t skybook-frontend:latest ./frontend'
            }
        }

        stage('Transfer Images to DevOps Server') {
            steps {
                sh '''
                    docker save skybook-backend:latest | gzip | ssh -o StrictHostKeyChecking=no ubuntu@13.233.118.205 'gunzip | docker load'

                    docker save skybook-frontend:latest | gzip | ssh -o StrictHostKeyChecking=no ubuntu@13.233.118.205 'gunzip | docker load'
                '''
            }
        }

stage('Deploy to Minikube') {
    steps {
        sh '''
            ssh -o StrictHostKeyChecking=no ubuntu@13.233.118.205 "
                kubectl rollout restart deployment backend &&
                kubectl rollout restart deployment frontend &&
                kubectl rollout status deployment backend &&
                kubectl rollout status deployment frontend
            "
        '''
    }
}
    }

    post {
        success {
            echo '======================================'
            echo '   SKYBOOK CI/CD SUCCESS 🚀'
            echo '   Backend + Frontend Deployed ✅'
            echo '======================================'
        }

        failure {
            echo '======================================'
            echo '   SKYBOOK PIPELINE FAILED ❌'
            echo '======================================'
        }
    }
}
