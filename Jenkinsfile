pipeline {
    agent any

    stages {
        stage('Receive Webhook Payload') {
            steps {
                script {
                    // Access the webhook payload
                    def payload = readJSON text: env.GITHUB_PAYLOAD // Assuming GitHub webhook
                    echo "Received webhook payload: ${payload}"

                    // Example: Access specific fields from the payload
                    def repositoryName = payload.repository.name
                    def commitMessage = payload.head_commit.message

                    echo "Repository: ${repositoryName}"
                    echo "Commit Message: ${commitMessage}"
                }
            }
        }
    }

    post {
        always {
            // Archive test results or perform cleanup
           // junit '**/Reports/*.xml' // Adjust this to your report path
            //archiveArtifacts artifacts: 'Reports/**', allowEmptyArchive: true // Archive all reports
        }
    }
}
