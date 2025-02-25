pipeline {
    agent any
    stages {
        stage('Get PR for Branch') {
            steps {
                script {
                    // Get the branch name (use BRANCH_NAME or set it as needed)
                    def branchName = env.BRANCH_NAME

                    // GitHub API URL to get PRs for the branch (head=your-branch-name)
                    def prApiUrl = "https://api.github.com/repos/lokzy123/katRepo/pulls?head=your-org:${branchName}"

                    // Make an API request to GitHub to get pull requests associated with the branch
                    def response = httpRequest url: prApiUrl, customHeaders: [[name: 'Authorization', value: 'Bearer Github@neww2020']], acceptType: 'APPLICATION_JSON'

                    // Parse the response to check if there are any PRs for the branch
                    def prList = readJSON text: response

                    if (prList.size() > 0) {
                        // If PRs exist, echo the first PR title (or process as needed)
                        def prTitle = prList[0].title
                        def prUrl = prList[0].html_url
                        echo "PR for branch ${branchName}: ${prTitle} (${prUrl})"
                    } else {
                        echo "No PR found for branch ${branchName}."
                    }
                }
            }
        }
    }
}
