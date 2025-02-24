pipeline {
    agent any
    environment {
        GITHUB_TOKEN = 'Github@neww2020'  // 
        REPO_OWNER = 'lokzy123'  // GitHub repository owner/username
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

							// Make the HTTP GET request to fetch PR details using httpRequest step
                   //  def response = httpRequest(
                   //     url: prUrl,
                   //     httpMode: 'GET',
                   //     customHeaders: [[name: 'Authorization', value: "token ${GITHUB_TOKEN}"]],
                   //     validResponseCodes: '200'
                   // )

			    // Make the HTTP GET request to fetch PR details
                    // def connection = new URL(prUrl).openConnection() as HttpURLConnection
                    // connection.setRequestMethod("GET")
                    // connection.setRequestProperty("Authorization", "token ${GITHUB_TOKEN}")
                    // connection.connect()
		
							// Read the response and parse JSON
							// def response = connection.inputStream.text
							// Parse the JSON payload using JsonSlurper
							def jsonSlurper = new groovy.json.JsonSlurper()
							def parsedJson = jsonSlurper.parseText(prResponse)
							
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
		
                        // Parse the PR details (using JSON parsing)
                        //def prJson = readJSON text: prResponse

                        // Extract the pull request description
                        //def prDescription = prJson.body
                       // echo "Pull Request Description: ${prDescription}"
                    } else {
                        echo "This is not a PR build."
                    }
                }
            }
        }
    }
}
