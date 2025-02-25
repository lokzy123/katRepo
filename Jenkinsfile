pipeline {
    agent any
    stages {
        stage('Executing Code') {
            steps {
                script {
                    if (env.CHANGE_ID) {
                        echo "Pull request TRIGGERING IS On"
                        echo "PR Title: ${env.CHANGE_TITLE}"
                        echo "PR Cmmit: ${env.GIT_COMMIT}"
                        // Specify the commit hash (for example, a previously known commit hash)
                    // def commitHash = ${env.GIT_COMMIT} // Replace with your desired commit hash

                    // // Get the commit message for the specific commit hash
                    // def commitMessage = sh(script: "git log -1 --pretty=%B ${commitHash}", returnStdout: true).trim()

                    // // Echo the commit message to the console
                    // echo "Commit Message for ${commitHash}: ${commitMessage}"
                    }else{
                        echo "PR Title: ${env.CHANGE_TITLE}"
                    echo "Pull request TRIGGERING IS Off"
                        echo "PR Cmmit: ${env.GIT_COMMIT}"

                        // Specify the commit hash (for example, a previously known commit hash)
                    // def commitHash = ${env.GIT_COMMIT}   // Replace with your desired commit hash

                    // // Get the commit message for the specific commit hash
                    // def commitMessage = sh(script: "git log -1 --pretty=%B ${commitHash}", returnStdout: true).trim()

                    // // Echo the commit message to the console
                    // echo "Commit Message for ${commitHash}: ${commitMessage}"
                    }

                    //executeKatalon executeArgs: './katalonc -noSplash -runMode=console -projectPath="/Users/lokeshguppta/Katalon Studio/katRepo/katRepo/katRepoGit.prj" -retry=0 -testSuitePath="Test Suites/Login_TestSuite" -browserType="Chrome" -executionProfile="default" -apiKey="b844dd8a-1ca5-4002-9b63-a7e7cd7f9b0e" --config -proxy.auth.option=NO_PROXY -proxy.system.option=NO_PROXY -proxy.system.applyToDesiredCapabilities=true -webui.autoUpdateDrivers=true', location: '', version: '10.1.0', x11Display: '', xvfbConfiguration: ''
                    }
                }
            }
        }
}
