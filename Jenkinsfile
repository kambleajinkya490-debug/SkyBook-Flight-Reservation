pipeline {
    agent any

    environment {
        AWS_REGION = 'ap-south-1'
        ECR_BACKEND = '060516714221.dkr.ecr.ap-south-1.amazonaws.com/skybook-backend'
        ECR_FRONTEND = '060516714221.dkr.ecr.ap-south-1.amazonaws.com/skybook-frontend'
        EKS_CLUSTER = 'SkyBook-EKS'
        TRIVY_CACHE = '/var/lib/trivy'
        TRIVY_TMP = '/var/lib/trivy-tmp'
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

        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool 'sonar-scanner'

                    withSonarQubeEnv('SkyBook-SonarQube') {
                        sh """
                            ${scannerHome}/bin/sonar-scanner \
                            -Dsonar.projectKey=SkyBook \
                            -Dsonar.projectName="SkyBook Flight Reservation" \
                            -Dsonar.sources=backend/src,frontend/src \
                            -Dsonar.sourceEncoding=UTF-8 \
                            -Dsonar.java.binaries=backend/build/classes/java/main
                        """
                    }
                }
            }
        }

        stage('Trivy File System Scan') {
            steps {
                sh '''
                    TMPDIR=$TRIVY_TMP trivy fs \
                    --config /dev/null \
                    --ignorefile /dev/null \
                    --scanners vuln \
                    --cache-dir $TRIVY_CACHE \
                    --severity HIGH,CRITICAL \
                    --exit-code 0 \
                    .
                '''
            }
        }

        stage('Docker Login') {
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

        stage('Trivy Backend Image Scan') {
            steps {
                sh '''
                    TMPDIR=$TRIVY_TMP trivy image \
                    --config /dev/null \
                    --ignorefile /dev/null \
                    --scanners vuln \
                    --cache-dir $TRIVY_CACHE \
                    --severity HIGH,CRITICAL \
                    --exit-code 0 \
                    $ECR_BACKEND:latest
                '''
            }
        }

        stage('Trivy Frontend Image Scan') {
            steps {
                sh '''
                    TMPDIR=$TRIVY_TMP trivy image \
                    --config /dev/null \
                    --ignorefile /dev/null \
                    --scanners vuln \
                    --cache-dir $TRIVY_CACHE \
                    --severity HIGH,CRITICAL \
                    --exit-code 0 \
                    $ECR_FRONTEND:latest
                '''
            }
        }

        stage('Push Backend') {
            steps {
                sh '''
                    docker push $ECR_BACKEND:latest
                '''
            }
        }

        stage('Push Frontend') {
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
            echo 'SKYBOOK DEVSECOPS PIPELINE SUCCESS'
        }

        failure {
            echo 'SKYBOOK PIPELINE FAILED'
        }
    }
}
