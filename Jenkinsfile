// Define global Groovy variables outside the pipeline block

// Flags to execute build and tests
def executeBuild = false

// Default paths and configurations for the tests
def defaultSuitePath = "Test Suites/Login_TestSuite"
def defaultCollectionPath = "Test Suites/Test Suite Collection 1"
def defaultBrowser = "Chrome"
def defaultProfile = "default"
def executeCollection = false

// Variables to store data retrieved from the PR
def receivedJson = ''
def commentUrl = ''


pipeline {
    agent any
    environment {
        // Credentials for GitHub token
        GITHUB_CREDENTIALS = credentials('git-token')  // GitHub Personal Access Token
    }
    stages {
        stage('New PR Opened') {
            steps {
                script {
                    // Check if the event is a Pull Request
                    if (env.CHANGE_ID) {
                        def prNumber = env.CHANGE_ID
                        def prApiUrl = "https://api.github.com/repos/lokzy123/katRepo/issues/${prNumber}"

                        // Fetch the PR details from GitHub API
                        def response = httpRequest url: prApiUrl, acceptType: 'APPLICATION_JSON'
                        def responseBody = response.content.toString()

                        // Parse the JSON response
                        receivedJson = readJSON text: responseBody
                        commentUrl = receivedJson.comments_url
                        // prDescription = receivedJson.body

                        // Mark the flag to execute the build
                        executeBuild = true
                    }
                }
            }
        }

        stage('Only Pushes') {
            steps {
                script {
                    // Get commit hash and message for the recent commit
                    def commitHash = env.GIT_COMMIT
                    def commitMessage = sh(script: "git log -1 --pretty=%B ${commitHash}", returnStdout: true).trim()
                    echo "Commit Message for ${commitHash}: ${commitMessage}"

                    // Check if commit message contains "Execute Job"
                    if (commitMessage.contains('Execute Job')) {
                        def branchName = env.BRANCH_NAME
                        def prApiUrl = "https://api.github.com/repos/lokzy123/katRepo/pulls?head=lokzy123:${branchName}"

                        // Fetch PR details for the branch
                        def response = httpRequest url: prApiUrl, acceptType: 'APPLICATION_JSON'
                        def responseBody = response.content.toString()
                        receivedJson = readJSON text: responseBody
                        commentUrl = receivedJson.comments_url[0]
                      
                        // Set executeBuild flag to true
                        executeBuild = true
                    }
                }
            }
        }

        stage('Execution') {
            steps {
                script {
                    if (executeBuild) {
                        // Choose which Katalon command to run based on the test suite or collection path
                    if (executeCollection) {
                            executeKatalon executeArgs: "-retry=0 -testSuiteCollectionPath=\"${defaultCollectionPath}\" -browserType=\"${defaultBrowser}\" -executionProfile=\"${defaultProfile}\" -apiKey=\"b844dd8a-1ca5-4002-9b63-a7e7cd7f9b0e\" --config -proxy.auth.option=NO_PROXY -proxy.system.option=NO_PROXY -proxy.system.applyToDesiredCapabilities=true -webui.autoUpdateDrivers=true", location: '', version: '10.0.1'
                        } else {
                            executeKatalon executeArgs: "-retry=0 -testSuitePath=\"${defaultSuitePath}\" -browserType=\"${defaultBrowser}\" -executionProfile=\"${defaultProfile}\" -apiKey=\"b844dd8a-1ca5-4002-9b63-a7e7cd7f9b0e\" --config -proxy.auth.option=NO_PROXY -proxy.system.option=NO_PROXY -proxy.system.applyToDesiredCapabilities=true -webui.autoUpdateDrivers=true", location: '', version: '10.0.1'
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            script {
                if (executeBuild) {
                    def token = GITHUB_CREDENTIALS_PSW

                    def jobUrl = env.JOB_URL
                    def buildNumber = env.BUILD_NUMBER

                    // Archive artifacts 
                    archiveArtifacts allowEmptyArchive: true, artifacts: "Reports/**/"
                    
                    def files = sh(script: "ls -t Reports/**/", returnStdout: true).trim().split('\n')
                    if(files.size()>0){
                        latestFile = files[0]
                    }
                    
                    def reportUrl = "${jobUrl}/${buildNumber}/artifact/${latestFile}"

                    def consoleUrl = "${jobUrl}/${buildNumber}/console"

                    // Construct the comment body
                    def commentBody = "'This is a comment from Jenkins! hey [View Katalon Test Report] :${reportUrl}) View Console : ${consoleUrl}'"

                    // Make HTTP request to post a comment on the PR
                    def response_comment = httpRequest(
                        url: commentUrl,
                        httpMode: 'POST',
                        contentType: 'APPLICATION_JSON',
                        acceptType: 'APPLICATION_JSON',
                        requestBody: """{
                            "body": "${commentBody}"
                        }""",
                        customHeaders: [
                            [name: 'Authorization', value: "Bearer ${token}"]
                        ]
                    )
                } else {
                    echo 'Build neither got an Execute Job Command nor a Pull Request'
                }
            }
        }
    }
}
