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

        stage('Checkout') {
            steps {
                // Checkout your repository
               // git url: 'https://your-repo-url.git', branch: 'main'
            }
        }

        stage('Run Katalon Tests') {
            steps {
                script {
                    // Define the path to Katalon Studio executable and your project
                    //def katalonPath = '/path/to/Katalon Studio/Katalon'
                   // def projectPath = '/path/to/your/katalon/project'
                    //def testCasePath = 'Test Cases/YourTestCase' // Adjust this to your test case path

                    // Command to execute Katalon tests
                   // def command = "${katalonPath} -noSplash -runMode=console -projectPath=\"${projectPath}\" -testSuitePath=\"${testCasePath}\" -executionProfile=default -browserType=Chrome"

                    // Execute the command
                   // sh command
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
