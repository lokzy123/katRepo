// Define a global Groovy variable outside the pipeline block
def isBuildExecuted = false  // Global Boolean variable 

def receivedJson = ''

def commentUrl = ''

def testSuitePath = ''

def testSuiteCollectionPath = ''

def browser = ''

def profile = ''

def testSuiteVar = "TestSuite : "

def testSuiteCollectionVar = "TestSuiteCollectionPath :"

def exeProfile = "Exe Profile :"

def browserType = "Browser Type : "


pipeline {
    agent any
    environment {
        // Credential saved at jenkins(Personal Access token from github) : here git-token is id of that credential
        GITHUB_CREDENTIALS = credentials('git-token')  // Directly using the credential ID to access the token

    }

// parameters {
//         string(name: 'testSuitePath', defaultValue: 'Test Suites/Login_TestSuite', description: 'Path to the test suite')
//         string(name: 'browser', defaultValue: 'Chrome1', description: 'Browser type for the test')
//         string(name: 'profile', defaultValue: 'default', description: 'Execution profile')
//         string(name: 'apiKey', defaultValue: 'b844dd8a-1ca5-4002-9b63-a7e7cd7f9b0e', description: 'API key for authentication')
//         string(name: 'xvfbConfiguration', defaultValue: '', description: 'Xvfb configuration')
//     }
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
                       
						'get review comments url'
                        def review_comment_url = receivedJson.comments_url
						
						'Get comments url'
                        commentUrl = review_comment_url[0]
						
                        echo "commentUrl : ${commentUrl}"

						'get'
                        def prDescription = receivedJson.body

                        prDescription = prDescription[0]
                        //Print description of PR
                        echo "prDescription : ${prDescription}"

                        def lines = prDescription.split("\n|\r")
                        
                        for(def line : lines){
                            echo "line : ${line}"
                        if(line.contains(testSuiteVar)){
                         testSuitePath = line.split(":")[1].trim()

                            echo "testSuitePath : ${testSuitePath}"
                            
                        }else if(line.contains(testSuiteCollectionVar)){

                           testSuiteCollectionPath = line.split(":")[1].trim()

                            echo "testSuiteCollectionPath : ${testSuiteCollectionPath}"
                        }else if(line.contains(exeProfile)){
                            exeProfile = line.split(":")[1].trim()

                            echo "exeProfile : ${exeProfile}"
                        }else if(line.contains(browserType)){
                            browser = line.split(":")[1].trim()

                            echo "browser : ${browser}"
                        }
                        }

                        isBuildExecuted = true

			    executeKatalon executeArgs: '-retry=0 -testSuitePath= 'testSuitePath' -browserType='+browser+' -executionProfile='+exeProfile+' -apiKey="b844dd8a-1ca5-4002-9b63-a7e7cd7f9b0e" --config -proxy.auth.option=NO_PROXY -proxy.system.option=NO_PROXY -proxy.system.applyToDesiredCapabilities=true -webui.autoUpdateDrivers=true', location: '', version: '10.0.1', x11Display: '', xvfbConfiguration: ''
                       
                    }
                }
            }
        }
		// stage('Execution') {
		// 	steps{
		// 		script {
		// 			if(isBuildExecuted) {
		// 				executeKatalon executeArgs: '-retry=0 -testSuitePath='${testSuitePath}' -browserType='${browser}' -executionProfile='${exeProfile}' -apiKey="b844dd8a-1ca5-4002-9b63-a7e7cd7f9b0e" --config -proxy.auth.option=NO_PROXY -proxy.system.option=NO_PROXY -proxy.system.applyToDesiredCapabilities=true -webui.autoUpdateDrivers=true', location: '', version: '10.0.1', x11Display: '', xvfbConfiguration: ''
		// 			}
		// 		}
		// 	}
		// }
		
    }
    post {
        always {
            script {
                if (isBuildExecuted) {
                    // Accessing the GitHub token correctly
                    def token = GITHUB_CREDENTIALS_PSW
                    
                    echo "Comments URL: ${commentUrl}"

                    // Set report path 
                    def reportPath = env.JOB_URL

                    def buildNumber = env.BUILD_NUMBER

                    // Construct the Jenkins artifact URL (adjust it to your Jenkins URL)
                    def reportUrl = "${reportPath}/${buildNumber}/artifact/Reports/**/Login_TestSuite/**/*.html"

                    // reportUrl = reportUrl.replace("//","/")

                    echo "reportUrl: ${reportUrl}"
                    def commentBody = "'This is a comment from Jenkins! hey [View Katalon Test Report] :${reportUrl})'"

                    echo 'Build was Push or Pull Request '

                    //acrchive artifacts
                    archiveArtifacts allowEmptyArchive: true, artifacts: "Reports/**/Login_TestSuite/**/*.html"

                    //  // Get the list of files from the directory and sort them by modification date
                    // def files = sh(script: "ls -t Reports/**/Login_TestSuite/**/*.html", returnStdout: true).trim().split('\n')

                    // def fileContent

                    // def htmlContent 

                    // def latestFile 
                    // // If files exist in the directory
                    // if (files.size() > 0) {
                    //     // Get the latest file (first file in the sorted list)
                    //     latestFile = files[0]

                    //     echo "latestFile : ${latestFile}"
                    //     // Read the content of the latest file
                    //     fileContent = readFile("${latestFile}")

                    //     htmlContent = readFile(latestFile).bytes
                    //     // Print the file content or use it further in your pipeline
                    //     // echo "Content of the latest file: \n${fileContent}"

                    // }
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

                    // def encodedFile = java.util.Base64.getEncoder().encodeToString(fileContent)
                    // Prepare the body of the POST request
                    // def requestBody = [
                    //       // Name of the file to be created
                    //     'content': encodedFile  // The content to write to the file
                    // ]

                     // def requestBody = """{
                     //            "file": "${fileContent}"
                     //    }""

//                  // Make the HTTP request to post a comment
//                    def response_comment = httpRequest(
//                        url: commentUrl,
//                        httpMode: 'POST',
//                        contentType: 'APPLICATION_JSON',
//                        acceptType: 'APPLICATION_JSON',
//                        requestBody: """{
//                                "file": "${fileContent}"
//                        } """,
//                        customHeaders: [
//                            [name: 'Authorization', value: "Bearer ${token}"],
//                        ]
//                    )

                    // Read the HTML file content 
                    // sh """
                    //     curl -X POST ${commentUrl}
                    //     -H "Authorization: Bearer ${token}" 
                    //     -F "file=@${latestFile}" 
                    // """ 
                } else {
                    echo 'Build neither got a Execute Job Command nor a Pull Request'
                }
            }
        }
    }
}
