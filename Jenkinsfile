pipeline {
    agent any
    parameters {
        string(name: 'parameter1', defaultValue: 'defaultValue1', description: 'Parameter 1 for the webhook')
        
    }
    stages {
        stage('Print commit messgae') {
            steps {
                script{
		def commitHash = sh(script: 'git rev-parse HEAD^2',returnStdout: true).trim()
                def commitMessage = sh(script: 'git log -1 --pretty=%B ${commitHash}',returnStdout: true).trim()
                echo "Commit message: ${commitMessage}"
                }
                echo "Building the project on branch: ${env.BRANCH_NAME}"
				echo "Comment Body: ${env.COMMENT_BODY}"
				echo "Description PR : ${env.PR_DESCRIPTION}"
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
