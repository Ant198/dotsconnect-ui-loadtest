pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Ant198/formSubmissionTest.git'
            }
        }

        stage('Run Jmeter') {
                    steps {
                        sh 'jmeter -n -t testplan.jmx -l results/results.jtl'
                    }
                }

        stage('Run test') {
            steps {
                script{
                    sh "mvn clean test"
                }
            }
         }
    }
    post {
        always {
            testNG()
            allure includeProperties: false,
                jdk: '',
                results: [[path: 'allure-results']]
        }
    }
}