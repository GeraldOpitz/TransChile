pipeline {
    agent any

    tools {
        maven 'Maven 3.9.10'
        jdk 'JDK-17'
    }

    stages {
        stage('Checkout código') {
            steps {
                checkout scm
            }
        }

        stage('Compilar') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Pruebas') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Análisis de calidad con SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat 'mvn sonar:sonar -Dsonar.projectKey=TransChile -Dsonar.host.url=http://localhost:9000 -Dsonar.login=$SONAR_TOKEN'

                }
            }
        }

        stage('Despliegue (simulado)') {
            steps {
                echo 'Despliegue exitoso a entorno de pruebas'
            }
        }
    }
}