pipeline {
    agent any

    environment {
        SELENOID_URL = "${params.SELENOID_URL}"
        BROWSER = "${params.BROWSER}"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/dimamoryk/AutotestWithSelenoid.git',
                    credentialsId: 'github-credentials'
            }
        }

        stage('Run Tests') {
            steps {
                sh '''
                    mvn clean test \
                        -Dselenoid.url=${SELENOID_URL} \
                        -Dbrowser=${BROWSER}
                '''
            }
        }

        stage('Publish Reports') {
            steps {
                publishHTML([
                    reportDir: 'target/surefire-reports',
                    reportFiles: '*.html',
                    reportName: 'Test Reports'
                ])
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}