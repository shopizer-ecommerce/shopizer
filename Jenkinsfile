pipeline {
    agent {label 'OPENJDK-11-JDK'}
    triggers {
        pollSCM('* * * * *')
    }
    stages {
        satge('vcs') {
            steps {
                git branch: 'devops', url: 'https://github.com/longflewtinku/shopizer.git'         
            }
        }
        satge('build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}