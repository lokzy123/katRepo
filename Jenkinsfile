pipeline {
    agent any
	triggers {
        // Trigger the pipeline when the webhook is received
        genericWebhookTrigger(
            genericVariables: [
                [key: 'COMMENT', value: '$.comment.body'],
                [key: 'PR_NUMBER', value: '$.pull_request.number']
            ]
        )
    }
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
                echo "Parameter 1 : ${params.parameter1}"
		def comment = COMMENT
                def prNumber = PR_NUMBER
		echo "Received comment: ${comment} from PR number: ${prNumber}"

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
