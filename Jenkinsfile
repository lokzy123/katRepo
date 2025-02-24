pipeline {
    agent any
    stages {
        stage('Executing Code') {
            steps {
                script {
		    //Pass true if want to trigger jenkins job else keep false
                    if (true) {
			//Execute command
			executeKatalon executeArgs: './katalonc -noSplash -runMode=console -retry=0 -testSuitePath="Test Suites/Login_TestSuite" -browserType="Chrome" -executionProfile="default" -apiKey="b844dd8a-1ca5-4002-9b63-a7e7cd7f9b0e" --config -proxy.auth.option=NO_PROXY -proxy.system.option=NO_PROXY -proxy.system.applyToDesiredCapabilities=true -webui.autoUpdateDrivers=true', location: '', version: '10.1.0', x11Display: '', xvfbConfiguration: ''   
                    } else {
                        echo "TRIGGERING IS OFF"
                    }
                }
            }
        }
    }
}
