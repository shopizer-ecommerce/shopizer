pipeline {
    agent any 
        stages {
            stage('shopizer') {
                steps {
                    git branch: 'master'
                           url: 'https://github.com/maheshryali/shopizer.git'
                    sh 'mvn package'
                }
            }
        }
    
}