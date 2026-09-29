pipeline {
    agent any

    environment {
        AWS_REGION = 'ap-south-1'

        ECR_BACKEND = '060516714221.dkr.ecr.ap-south-1.amazonaws.com/skybook-backend'
        ECR_FRONTEND = '060516714221.dkr.ecr.ap-south-1.amazonaws.com/skybook-frontend'

        EKS_CLUSTER = 'SkyBook-EKS'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/kambleajinkya490-debug/SkyBook-Flight-Reservation.git'
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend') {
                    sh '''
                        chmod +x gradlew
                        ./gradlew clean build -x test
                    '''
                }
            }
        }

        stage('Docker Login to ECR') {
            steps {
                sh '''
                    aws ecr get-login-password --region $AWS_REGION | \
                    docker login \
                    --username AWS \
                    --password-stdin 060516714221.dkr.ecr.ap-south-1.amazonaws.com
                '''
            }
        }

        stage('Build Backend Docker Image') {
            steps {
                sh '''
                    docker build \
                    -t $ECR_BACKEND:latest \
                    ./backend
                '''
            }
        }

        stage('Build Frontend Docker Image') {
            steps {
                sh '''
                    docker build \
                    -t $ECR_FRONTEND:latest \
                    ./frontend
                '''
            }
        }

        stage('Push Backend to ECR') {
            steps {
                sh '''
                    docker push $ECR_BACKEND:latest
                '''
            }
        }

        stage('Push Frontend to ECR') {
            steps {
                sh '''
                    docker push $ECR_FRONTEND:latest
                '''
            }
        }

        stage('Configure EKS') {
            steps {
                sh '''
                    aws eks update-kubeconfig \
                    --region $AWS_REGION \
                    --name $EKS_CLUSTER

                    kubectl get nodes
                '''
            }
        }

        stage('Deploy Backend') {
            steps {
                sh '''
                    kubectl apply -f k8s/backend-deployment.yaml
                    kubectl apply -f k8s/backend-service.yaml

                    kubectl rollout restart deployment/backend

                    kubectl rollout status deployment/backend --timeout=180s
                '''
            }
        }

        stage('Deploy Frontend') {
            steps {
                sh '''
                    kubectl apply -f k8s/frontend-deployment.yaml
                    kubectl apply -f k8s/frontend-service.yaml

                    kubectl rollout restart deployment/frontend

                    kubectl rollout status deployment/frontend --timeout=180s
                '''
            }
        }

        stage('Verify Deployment') {
            steps {
                sh '''
                    echo "===== PODS ====="
                    kubectl get pods

                    echo "===== SERVICES ====="
                    kubectl get services

                    echo "===== INGRESS ====="
                    kubectl get ingress
                '''
            }
        }
    }

    post {

        success {
            echo '''
              SKYBOOK CI/CD SUCCESS 
              ECR PUSH SUCCESS 
              EKS DEPLOYMENT SUCCESS 
            '''
        }

        failure {
            echo '''

              SKYBOOK PIPELINE FAILED 
            '''
        }
    }
}
