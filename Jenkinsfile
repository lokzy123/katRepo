// Define a global Groovy variable outside the pipeline block
def isBuildExecuted = false  // Global Boolean variable

def receivedJson = ''

def commentUrl = ''



pipeline {
    agent any
    environment {
        // Credential saved at jenkins(Personal Access token from github) : here git-token is id of that credential
        GITHUB_CREDENTIALS = credentials('git-token')  // Directly using the credential ID to access the token

    }
    stages {
        stage('New PR Opened'){
            steps{
                script{
                    if (env.CHANGE_ID) {
                        //Get the title of PR
                        echo "PR Title: ${env.CHANGE_TITLE}"
                        
                        echo "Enetered is PR Step"

                        //Get PR number
                        def prNumber = env.CHANGE_ID
                        
                        // GitHub API URL to of PRs
                        def prApiUrl = "https://api.github.com/repos/lokzy123/katRepo/issues/${prNumber}"

                        // Make an API request to GitHub to get pull requests response associated with the branch
                        def response = httpRequest url: prApiUrl, acceptType: 'APPLICATION_JSON'
                        
                        // Get response body as string
                        def responseBody = response.content.toString()

                        // Get parsed json body
                        receivedJson = readJSON text: responseBody

                        //Get Comment URl From received json
                        commentUrl = receivedJson.comments_url

                        //Print URL on Console
                        echo "commentUrl : ${commentUrl}"

                        //Mark build executed flag true
                        isBuildExecuted = true

                        def prDescription = receivedJson.body

                        //Print description of PR
                        echo "prDescription : ${prDescription}"

                        def lines = prDescription.split("\n|\r")
                        for(def line : lines){
                            echo "line : ${line}"
                        if(line.containsIgnoreCase("TestSuiteCollectionPath")){

                            echo "line : ${line}"
                            
                        }else{

                        }
                        }

                        //Execution command to execute test suite
                        // executeKatalon executeArgs: '-retry=0 -testSuitePath="Test Suites/Login_TestSuite" -browserType="Chrome" -executionProfile="default" -apiKey="b844dd8a-1ca5-4002-9b63-a7e7cd7f9b0e" --config -proxy.auth.option=NO_PROXY -proxy.system.option=NO_PROXY -proxy.system.applyToDesiredCapabilities=true -webui.autoUpdateDrivers=true', location: '', version: '10.0.1', x11Display: '', xvfbConfiguration: ''
                    }
                }
            }
        }
        stage('Only Pushes') {
            steps{
                script {
                    // Commit hash
                    def commitHash = env.GIT_COMMIT
                    
                    // Get the commit message for the specific commit hash
                    def commitMessage = sh(script: "git log -1 --pretty=%B ${commitHash}", returnStdout: true).trim()
                    
                    // Echo the commit message to the console
                    echo "Commit Message for ${commitHash}: ${commitMessage}"
                    
                    if (commitMessage.contains('Execute Job')) {
                        echo "Push step started successfully : ${commitMessage}"
                        
                        // Get the branch name (use BRANCH_NAME or set it as needed)
                        def branchName = env.BRANCH_NAME
                        
                        // GitHub API URL to get PRs for the branch (head=your-branch-name)
                        def prApiUrl = "https://api.github.com/repos/lokzy123/katRepo/pulls?head=lokzy123:${branchName}"

                        // Make an API request to GitHub to get pull requests associated with the branch
                        def response = httpRequest url: prApiUrl, acceptType: 'APPLICATION_JSON'
                        
                        // Get response body as string
                        def responseBody = response.content.toString()
                        
                        // Get parsed json body
                        receivedJson = readJSON text: responseBody

                        echo "receivedJson : ${receivedJson}"
                        // Get title from parsed json
                        def title = receivedJson.title
                        echo "Title: ${title}"
                        
                        // Get story names from title
                        def storyNames = title[0].toString().split(",")
                        
                        echo "Story Names: ${storyNames}"

                        def review_comment_url = receivedJson.comments_url
                        commentUrl = review_comment_url[0]
                        echo "commentUrl : ${commentUrl}"

                        def prDescription = receivedJson.body

                        //Print description of PR
                        echo "prDescription : ${prDescription}"

                        def lines = prDescription.split("\n|\r")
                        for(def line : lines){
                            echo "line : ${line}"
                        if(line.containsIgnoreCase("TestSuiteCollectionPath")){

                            echo "line : ${line}"
                            
                        }else{

                        }
                        }

                        isBuildExecuted = true

                        // executeKatalon executeArgs: '-retry=0 -testSuitePath="Test Suites/Login_TestSuite" -browserType="Chrome" -executionProfile="default" -apiKey="b844dd8a-1ca5-4002-9b63-a7e7cd7f9b0e" --config -proxy.auth.option=NO_PROXY -proxy.system.option=NO_PROXY -proxy.system.applyToDesiredCapabilities=true -webui.autoUpdateDrivers=true', location: '', version: '10.0.1', x11Display: '', xvfbConfiguration: ''
                    }
                }
            }
        }
    }
    post {
        always {
            script {
                if (isBuildExecuted) {
                    // Accessing the GitHub token correctly
                    def token = GITHUB_CREDENTIALS_PSW


                    // echo "receivedJson : ${receivedJson}"
                    
                    // // Get the comments URL from the received JSON
                    // def review_comment_url = receivedJson.comments_url
                    
                    // // In case the review_comment_url has multiple URLs, select the first one
                    // def commentUrl = review_comment_url[0]
                    
                    echo "Comments URL: ${commentUrl}"

                    // Set report path 
                    def reportPath = env.JOB_URL

                    def buildNumber = env.BUILD_NUMBER

                    // Construct the Jenkins artifact URL (adjust it to your Jenkins URL)
                    def reportUrl = "${reportPath}/${buildNumber}/artifact/Reports/**/Login_TestSuite/**/*.html"

                    echo "reportUrl: ${reportUrl}"
                    def commentBody = "'This is a comment from Jenkins! hey [View Katalon Test Report] :${reportUrl})'"

                    echo 'Build was Push or Pull Request '

                    //acrchive artifacts
                    archiveArtifacts allowEmptyArchive: true, artifacts: "Reports/**/Login_TestSuite/**/*.html"

                    
                    //Make the HTTP request to post a comment
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
                    
                    
                    echo 'Build neither got a Execute Job Command nor a Pull Request'
                }
            }
        }
    }
}
