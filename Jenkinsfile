pipeline {
    agent any
    parameters {
        string(name: 'parameter1', defaultValue: 'defaultValue1', description: 'Parameter 1 for the webhook')
        
    }
    stages {
		stage('Checkout'){
			steps{
				checkout scm
			}
		}
        stage('Print commint messgae') {
            steps {
                script{
                def commitMessage = sh(script: 'git log -1 --pretty=%s',returnStdout: true).trim()
                echo "Commit message: ${commitMessage}"
                }
                echo "Building the project on branch: ${env.BRANCH_NAME}"
                echo "Parameter 1 : ${params.parameter1}"
                // Add build steps here (e.g., Maven, Gradle)
                sh 'echo "Building...Success"'
            }
        }
        stage('Test') {
            steps {
                echo "Running tests for branch: ${env.BRANCH_NAME}"
                // Add test steps here (e.g., running unit tests)
                sh 'echo "Running Tests...Success"'
            }
        }
    }
    post {
        always {
            echo "Pipeline finished for branch: ${env.BRANCH_NAME}"
        }
    }
}
