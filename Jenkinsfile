pipeline {
    agent any
    stages {
        stage('Run Jmeter') {
                    steps {
                        bat 'jmeter -n -t dotsconnect-test.jmx -l results\results.jtl'
                    }
                }

        stage('Run test') {
            steps {
                script{
                    bat "mvn clean test"
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