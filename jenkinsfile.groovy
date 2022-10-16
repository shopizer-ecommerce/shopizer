pipeline {
    agent {label ('shopizer')}
    parameters {
          choice(name: 'branch', choices: ['master', 'developbranch'], description: 'forselectingbranch')
          choice(name: 'maven', choices: ['package', 'install', 'clean package', 'clean install'], description: 'formavenbuildpackage') 
                      }                  
    stages {
        stage('vcs') {
            steps {
                git branch: ${params.branch},
                       url: 'https://github.com/maheshryali/shopizer.git'
            }
        }
        stage('maven build') {
            steps {
                sh ('mvn ${params.maven}')
                }
        }
    }
     triggers { pollSCM('* * * * *') }
}