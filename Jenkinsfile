pipeline {
    agent any

    environment {
        // Define the GitHub webhook event URL
        GITHUB_TOKEN = 'Github@neww2020'
    }

    stages {
        stage('Receive GitHub Webhook Payload') {
            steps {
                script {
                    // Capture the webhook payload from GitHub (via environment variable)
                    def payload = params.payload  // GitHub webhook payload is passed as 'payload'

                    // Parse the JSON payload (you can modify this depending on the event)
                    def json = readJSON text: payload
                    echo "Received GitHub Webhook payload: ${json}"

                    // You can now process the payload as needed, for example:
                    if (json.action == 'opened') {
                        echo "New issue opened: ${json.issue.title}"
                    }

                    // Example of accessing GitHub issue data
                    def issueTitle = json.issue.title
                    def issueURL = json.issue.html_url
                    echo "Issue Title: ${issueTitle}"
                    echo "Issue URL: ${issueURL}"
                }
            }
        }

        stage('Process Webhook Data') {
            steps {
                script {
                    // Example of further processing after receiving the webhook data
                    echo "Processing the webhook data further..."
                    // You can perform operations based on the GitHub webhook data here
                }
            }
        }
    }
}
