pipeline {
    agent any
    environment {
        GITHUB_TOKEN = credentials('Github@neww2020')  // Store GitHub token in Jenkins credentials
        REPO_OWNER = 'lokzy0105@gmail.com'  // GitHub repository owner/username
        REPO_NAME = 'katRepo' // GitHub repository name
    }
    stages {
        stage('Fetch Pull Request Description') {
            steps {
                script {
                    // Check if this is a PR build
                    if (env.CHANGE_ID) {
                        // Prepare the GitHub API URL to fetch PR details
                        def prUrl = "https://api.github.com/repos/${env.REPO_OWNER}/${env.REPO_NAME}/pulls/${env.CHANGE_ID}"
                        
                        // Send the GET request to GitHub API
                        def prResponse = sh(script: """
                            curl -H "Authorization: token ${env.GITHUB_TOKEN}" \
                                 -H "Accept: application/vnd.github.v3+json" \
                                 "${prUrl}"
                            """, returnStdout: true).trim()

                        // Parse the PR details (using JSON parsing)
                        def prJson = readJSON text: prResponse

                        // Extract the pull request description
                        def prDescription = prJson.body
                        echo "Pull Request Description: ${prDescription}"
                    } else {
                        echo "This is not a PR build."
                    }
                }
            }
        }
    }
}
