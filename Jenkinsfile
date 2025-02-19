pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo "Building the project on branch: ${env.BRANCH_NAME}"
                // Add build steps here (e.g., Maven, Gradle)
                sh 'echo "Building..."'
            }
        }
        stage('Test') {
            steps {
                echo "Running tests for branch: ${env.BRANCH_NAME}"
                // Add test steps here (e.g., running unit tests)
                sh 'echo "Running Tests..."'
            }
        }
    }
    post {
        always {
            echo "Pipeline finished for branch: ${env.BRANCH_NAME}"
        }
    }
}
