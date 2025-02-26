// Define a global Groovy variable outside the pipeline block
def isBuildExecuted = false  // Global Boolean variable

def receivedJson =''

pipeline {
    agent any
	environment {
		// Credential saved at jenkins(Personal Access token from github) : here git-token is id of that credential
        GITHUB_CREDENTIALS = credentials('git-token')  // Directly using the credential ID to access the token
		//Get password from credential
		
    }
    stages {
		stage('New PR Opened'){
			steps{
				script{
					if(env.CHANGE_ID){
						echo "PR Title: ${env.CHANGE_TITLE}"
						
						echo "${env.CHANGE_TITLE} :PR Step is executed successfully"
						
						isBuildExecuted = true
//						executeKatalon executeArgs: './katalonc -noSplash -runMode=console -projectPath="/Users/lokeshguppta/Katalon Studio/katRepo/katRepo/katRepoGit.prj" -retry=0 -testSuitePath="Test Suites/Login_TestSuite" -browserType="Chrome" -executionProfile="default" -apiKey="b844dd8a-1ca5-4002-9b63-a7e7cd7f9b0e" --config -proxy.auth.option=NO_PROXY -proxy.system.option=NO_PROXY -proxy.system.applyToDesiredCapabilities=true -webui.autoUpdateDrivers=true', location: '', version: '10.1.0', x11Display: '', xvfbConfiguration: ''
					}
				}
			}
		}
        stage('Only Pushes') {
			steps{
				script{
				// Commit hash
				def commitHash = ${env.GIT_COMMIT}   
					
				//Get the commit message for the specific commit hash
				def commitMessage = sh(script: "git log -1 --pretty=%B ${commitHash}", returnStdout: true).trim()
					
				//Echo the commit message to the console
				echo "Commit Message for ${commitHash}: ${commitMessage}"
				
				if(commitMessage.equals('Execute Job')) {
					
					echo "${commitMessage} :Push step started successfully"
					
					// Get the branch name (use BRANCH_NAME or set it as needed) check
					def branchName = env.BRANCH_NAME

					// GitHub API URL to get PRs for the branch (head=your-branch-name)
					def prApiUrl = "https://api.github.com/repos/lokzy123/katRepo/pulls?head=lokzy123:${branchName}"

					// Make an API request to GitHub to get pull requests associated with the branch
					def response = httpRequest url: prApiUrl, acceptType: 'APPLICATION_JSON'
					
					// Get response body as string
					def responseBody = response.content.toString()
					
					//echo "responseBody : ${responseBody}"
					
					//Get parsed json body
					receivedJsonPayload = readJSON text: responseBody
					
					//Get title from parsed json
					def title = receivedJsonPayload.title
					
					echo "Title : ${title}"
					
					//Get story names from title
					def storyNames = title.split(',')
					
					echo "StoryNames : ${storyNames}"
					
					
					isBuildExecuted = true
					
				}
				}
			}
        }
    }
			post {
				always {
					scrip{
					if(isBuildExecuted) {
						
						def token = GITHUB_CREDENTIALS_PSW
						
						def review_comment_url = receivedJsonPayload.comments_url
						
						def commenst_Url = review_comment_url[0]
						
						echo "Comments URL : ${commenst_Url}"
						
						def commentBody = 'This is a comment from Jenkins! hey'
						
						def response_comment = httpRequest(
							url: commenst_Url,
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
						
					}else {
						echo 'Build was neither for Push nor for Pull Request'
					}
					}
				}
         }
}
