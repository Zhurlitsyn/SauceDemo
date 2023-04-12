pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }
    parameters {
     gitParameter branchFilter: 'origin/(.*)', defaultValue: 'main', name: 'BRANCH', type: 'PT_BRANCH'
    }

    stages {
        stage('GIT Clone') {
            steps {
                // Get some code from a GitHub repository
                git branch: "main", url: 'https://github.com/Zhurlitsyn/SauceDemo.git'


            }
        }
        stage('UI Test') {
            steps {
                   bat "mvn clean test"
                   }
        }
            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                    allure([
                             includeProperties: false,
                             jdk: '',
                             properties: [],
                             reportBuildPolicy: 'ALWAYS',
                             results: [[path: 'target/allure-results']]
                     ])
                }
            }
        }
        stage('Run test') {
         steps {
            // Get some code from a GitHub repository
            git branch: "${params.BRANCH}", url: 'https://github.com/Zhurlitsyn/SauceDemo.git'
         }
        }

    }

}
