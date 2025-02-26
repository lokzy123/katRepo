pipeline {
    agent any
    stages {
        stage('Get PR for Branch') {
            steps {
                script {
                    // Get the branch name (use BRANCH_NAME or set it as needed) check data
                    def branchName = env.BRANCH_NAME

                    // GitHub API URL to get PRs for the branch (head=your-branch-name)
                    def prApiUrl = "https://api.github.com/repos/lokzy123/katRepo/pulls?head=lokzy123:${branchName}"

                    // Make an API request to GitHub to get pull requests associated with the branch
                    def response = httpRequest url: prApiUrl, acceptType: 'APPLICATION_JSON'

			def responseBody = response.content.toString()
			echo "responseBody : ${responseBody}"
			def prJson = readJSON text: responseBody
   //                  // Parse the response to check if there are any PRs for the branch
   //                  def jsonSlurper = new groovy.json.JsonSlurper()
			// def parsedJson = jsonSlurper.parseText(prJson)

			def title = prJson.title
			echo "Title : ${title}"
			def review_comment_url = prJson.comments_url
			def commenst_Url = review_comment_url[0]
			echo "Comments URL : ${commenst_Url}"

			 // Comment message to post
                    def commentBody = '{"body": "This is a comment from Jenkins!"}'
                    
                    // Make the API request to post the comment on the PR using httpRequest
                    def response_comment = httpRequest(
                        url: commenst_Url,
                        httpMode: 'POST',
                        contentType: 'APPLICATION_JSON',
                        headers: [
				[name: 'Authorization', value: "Bearer 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"],
                           	[name: 'Accept', value: 'application/vnd.github.v3+json']
                        ],
                        requestBody: commentBody
                    )

			def responseBody_Cmt = response_comment.content.toString()
		echo "Parsed Json comment: ${prJson}"
                    echo "Parsed Json : ${prJson}"
                    // def prList = readJSON text: response

                    // if (prList.size() > 0) {
                    //     // If PRs exist, echo the first PR title (or process as needed)
                    //     def prTitle = prList[0].title
                    //     def prUrl = prList[0].html_url
                    //     echo "PR for branch ${branchName}: ${prTitle} (${prUrl})"
                    // } else {
                    //     echo "No PR found for branch ${branchName}."
                    // }
                }
            }
        }
    }
}
