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

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t skybook-backend:latest ./backend'
            }
        }

        stage('Transfer Image to DevOps Server') {
            steps {
                sh '''
                    docker save skybook-backend:latest | gzip | ssh -o StrictHostKeyChecking=no ubuntu@13.233.118.205 'gunzip | docker load'
                '''
            }
        }

        stage('Deploy to Minikube') {
            steps {
                sh '''
                    ssh -o StrictHostKeyChecking=no ubuntu@13.233.118.205 "
                        minikube image load skybook-backend:latest &&
                        kubectl rollout restart deployment backend &&
                        kubectl rollout status deployment backend
                    "
                '''
            }
        }
    }

    post {
        success {
            echo 'CI/CD SUCCESS 🚀'
        }

        failure {
            echo 'Pipeline Failed ❌'
        }
    }
}
