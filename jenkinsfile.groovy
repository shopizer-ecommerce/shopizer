pipeline {
    agent { label 'SHOPIZER' }                  
    stages {
        stage('vcs') {
            steps {
                git branch: 'developbranch',
                       url: 'https://github.com/maheshryali/shopizer.git'
            }
        }
        stage('maven build') {
            steps {
                sh ("mvn package")
                }
        }
    }
     triggers { pollSCM('* * * * *') }
}