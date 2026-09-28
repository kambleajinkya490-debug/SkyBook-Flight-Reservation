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

        stage('Deploy to Minikube') {
            steps {
                sh 'minikube image load skybook-backend:latest'
                sh 'kubectl rollout restart deployment backend'
                sh 'kubectl rollout status deployment backend'
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
