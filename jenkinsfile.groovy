pipeline {
    agent { label 'SHOPIZER' }                  
    stages {
        stage('vcs') {
            steps {
                git branch: 'release',
                       url: 'https://github.com/maheshryali/shopizer.git'
            }
        }
        stage('maven build') {
            steps {
                sh ("mvn package")
                }
        }
    }
     triggers { cron('* * * * *') }
}