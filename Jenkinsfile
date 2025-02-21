pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
				scripts{
					if(true) {
						executeKatalon executeArgs: '-projectPath="/Users/lokeshguppta/Katalon Studio/LoginTest/katRepo/katRepoGit.prj" -retry=0 -testSuitePath="Test Suites/Login_TestSuite" -browserType="Chrome" -executionProfile="default" -apiKey="b844dd8a-1ca5-4002-9b63-a7e7cd7f9b0e" --config -proxy.auth.option=NO_PROXY -proxy.system.option=NO_PROXY -proxy.system.applyToDesiredCapabilities=true -webui.autoUpdateDrivers=true', location: '', version: '10.1.0', x11Display: '', xvfbConfiguration: ''
					}
					
				}
                // Add test steps here (e.g., running unit tests)
                
		    	}
		    }
		    
                echo "Running tests for branch: ${env.BRANCH_NAME}"
		  
            }
    post {
        always {
            echo "Pipeline finished for branch: ${env.BRANCH_NAME}"
        }
    }
}
