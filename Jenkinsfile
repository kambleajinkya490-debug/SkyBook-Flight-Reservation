pipeline {
    agent any

    environment {
        APP_NAME = "skybook-backend"
    }

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

    }

    post {
        success {
            echo 'Build Successful ✅'
        }
        failure {
            echo 'Build Failed ❌'
        }
    }
}
