pipeline {
    agent any

    tools {
        maven 'Maven 3.8.6'
        jdk 'JDK-17'
    }

    stages {
        stage('Clonar repositorio') {
            steps {
                git 'https://github.com/tu-usuario/transchile-ci-demo.git'
            }
        }

        stage('Compilar') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Pruebas') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Análisis de calidad (opcional)') {
            steps {
                echo 'Aquí se puede integrar SonarQube más adelante'
            }
        }

        stage('Despliegue (simulado)') {
            steps {
                echo 'Despliegue exitoso a entorno de pruebas'
            }
        }
    }
}
