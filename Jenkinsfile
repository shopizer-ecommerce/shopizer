pipeline {
    agent {label 'OPENJDK-11-JDK'}
    stages {
        satge('vcs') {
            steps {
                git branch: 'devops', 
                    url: 'https://github.com/longflewtinku/shopizer.git'         
            }
        }
        triggers {
            pollSCM('* * * * *')
        }
        satge('build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}