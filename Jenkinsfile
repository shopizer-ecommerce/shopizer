pipeline {
    agent {label 'OPENJDK-11-JDK'}
    triggers {
        pollSCM('0 17 * * *')
    }
    stages {
        stage('vcs') {
            steps {
                git branch: 'devops', url: 'https://github.com/longflewtinku/shopizer.git'         
            }
        }
        stage('build') {
            steps {
                sh 'git checkout devops'
                sh 'git merge release --no-ff'
                sh 'git add .'
                sh 'git commit -m "changes"'
                sh 'git push -u origin devops'
            }
        }
    }
}