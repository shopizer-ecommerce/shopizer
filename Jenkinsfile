node {
 stage('clone git repo'){
 git 'https://github.com/tickytec/shopizer.git'
 }
 
 stage("configure") {
        sh "mkdir $WORKSPACE/$BUILD_NUMBER/"
    }
 
 stage('run test'){
 sh "mkdir /tmp/reports"
 sh "cd /opt/homebrew/Cellar/jmeter/5.5/bin"
      sh "jmeter -Jjmeter.save.saveservice.output_format=xml -n -t Shopizer2.0.jmx -l /tmp/reports/JMeter.jtl -e -o /tmp/reports/HtmlReport"
 }
 
 stage('publish results'){
 sh "mv /tmp/reports/* $WORKSPACE/$BUILD_NUMBER/"
 archiveArtifacts artifacts: '$WORKSPACE/$BUILD_NUMBER/JMeter.jtl, $WORKSPACE/$BUILD_NUMBER/HtmlReport/index.html'
    } 
  }

