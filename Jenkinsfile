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

                    // Parse the JSON payload using JsonSlurper
                    def jsonSlurper = new groovy.json.JsonSlurper()
                    def parsedJson = jsonSlurper.parseText(payload)
                    
                    // Log the parsed JSON object for inspection
                    echo "Received GitHub Webhook payload: ${parsedJson}"

                    // Example: Check for a GitHub issue event (you can customize this part)
                    if (parsedJson.action == 'opened') {
                        echo "New issue opened: ${parsedJson.issue.title}"
                    }

                    // Example: Accessing the GitHub issue data
                    def issueTitle = parsedJson.issue.title
                    def issueURL = parsedJson.issue.html_url
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
