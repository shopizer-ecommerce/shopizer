node {
    stage('clone git repo') {
    git 'https://github.com/path_to_git_repo/app.git'
    }
    stage("configure") {
    sh "mkdir $WORKSPACE/$BUILD_NUMBER/"
    }
    stage('run test') {
        sh "mkdir /tmp/reports"
        sh "cd ~/Documents/Performance/apache-jmeter-5.5/apache-jmeter-5.5/bin"
        sh "jmeter -Jjmeter.save.saveservice.output_format=xml
        -n -t ~/Documents/Performance/apache-jmeter-5.5/apache-jmeter-5.5/bin/oavelika_testplan.jmx
        -l /tmp/reports/JMeter.jtl -e -o /tmp/reports/HtmlReport"
    }
    stage('publish results') {
        sh "mv /tmp/reports/* $WORKSPACE/$BUILD_NUMBER/"
        archiveArtifacts artifacts: '$WORKSPACE/$BUILD_NUMBER/JMeter.jtl, $WORKSPACE/$BUILD_NUMBER/HtmlReport/index.html'
        }
    }
}

