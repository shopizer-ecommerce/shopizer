pipeline {
  agent any

  tools {
    maven 'maven'
  }

  stages {
    
    stage('Clean') {
        steps {
            echo '******************* Maven Clean Source Code *******************'
            sh 'mvn clean'
            echo '******************* Mave Clean Execution Completed *******************'
        }
    }
    stage('Build') {
      steps {
        sh 'mvn build'
      }
    }
    
    stage('Test'){
        steps {
            echo '******************* Java Unit Testing Started *******************'
            sh 'mvn test'
            echo '******************* Java Unit Testing Execution Completed *******************'
        }
    }
    
    stage('Release'){
        steps{
            echo '******************* GitHub Release Started *******************'
            
            echo '******************* GitHub Release Completed *******************'
        }
        
    }
  }
}
