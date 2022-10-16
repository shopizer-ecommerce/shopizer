pipeline {
    agent {label 'OPENJDK-11-JDK'}
    triggers {
        pollSCM('30 17 * * 1-5')
    }
    stages {
        stage('vcs') {
            steps {
                git branch: 'release', url: 'https://github.com/longflewtinku/shopizer.git'         
            }
        }
        stage('build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}