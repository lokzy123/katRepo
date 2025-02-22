pipeline {
    agent any

    stages {
        stage('Process Webhook') {
            steps {
                script {
                    // Access the webhook payload and
                    def payload = readJSON text: env.GITHUB_PAYLOAD // Adjust based on your webhook provider
                    echo "Received payload: ${payload}"

                    // Example: Use data from the payload
                    def branchName = payload.ref.split('/').last() // Extract branch name
                    echo "Branch: ${branchName}"
                }
            }
        }
}
}
