pipeline {
    agent any

    tools {
        maven 'Maven 3.9.10'
        jdk 'JDK-17'
    }

    environment {
        DOCKER_IMAGE = 'transchile/devops-app:latest'
        SONAR_TOKEN = credentials('SONAR_TOKEN')
    }

    stages {
        stage('Checkout codigo') {
            steps {
                checkout scm
            }
        }

        stage('Compilar') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Empaquetar') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Pruebas') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Análisis de calidad con SonarQube') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    withSonarQubeEnv('SonarQube') {
                        bat 'mvn sonar:sonar -Dsonar.projectKey=TransChile -Dsonar.host.url=http://localhost:9000 -Dsonar.login=%SONAR_TOKEN%'
                    }
                }
            }
        }

        stage('Analisis de dependencias') {
            steps {
                bat 'if not exist dependency-check-report mkdir dependency-check-report'
                tool 'OWASP_DC_CLI'
                dependencyCheck odcInstallation: 'OWASP_DC_CLI', additionalArguments: '--project "transchile" --scan . --format "HTML" --out "dependency-check-report" --enableExperimental'
            }
        }


        stage('Construir imagen Docker') {
            steps {
                script {
                    bat "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Despliegue a docker') {
            steps {
                echo "Imagen Docker creada: ${DOCKER_IMAGE}"
            }
        }
    }
}