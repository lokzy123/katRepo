pipeline {
    agent any
    environment {
        // The webhook data will be available as environment variables
        COMMIT_MESSAGE = "${env.GITHUB_PAYLOAD_COMMIT_MESSAGE}"
        BRANCH_NAME = "${env.GITHUB_PAYLOAD_REF}"
    }
    stages {
        stage('Checkout') {
            steps {
                script{
                     def payload = readJSON text: env.GITHUB_PAYLOAD // Assuming GitHub webhook
                    echo "Received webhook payload: ${payload}"
                }
                echo "Payload Commit Message: ${COMMIT_MESSAGE}"
                echo "Payload Branch: ${BRANCH_NAME}"
                // Here you could add more logic to handle the payload as needed
            }
        }
    }
}
