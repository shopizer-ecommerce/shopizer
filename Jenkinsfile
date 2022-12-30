
pipeline{
    agent {
        label 'MVN3'
    }
    stages{
        stage('clone'){
            steps{
                git url: 'https://github.com/tarunkumarpendem/shopizer.git',
                    branch: 'master'
            }
        }
        stage ('build') {
            steps {
               sh 'mvn clean package'
           }
        }
        stage('Build the Code') {
            steps {
                withSonarQubeEnv('sonarcloud') {
                    sh script: 'mvn clean package sonar:sonar'
                }
            }
        stage('archiving-artifacts'){
            steps{
                archiveArtifacts artifacts: '**/target/*.jar', followSymlinks: false
            }
        }
        stage('junit_reports'){
            steps{
                junit '**/surefire-reports/*.xml'
            }
        }
    }    

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