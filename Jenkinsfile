pipeline {
    agent {label 'OPENJDK-11-JDK'}
    triggers {
        pollSCM('0 17 * * *')
    }
    stages {
        stage('vcs') {
            steps {
                git branch: 'release', url: 'https://github.com/longflewtinku/shopizer.git'         
            }
        }
        stage('merge') {
            steps {
                sh 'git checkout devops'
                sh 'git merge release --no-ff'
            }
        }
        stage('build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}