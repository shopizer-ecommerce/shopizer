pipeline {
    agent { label 'JDK11 && MVN3 && NVM && PY3' }
    stages {
        stage('vcs') {
            steps {
                git branch: 'SPRINT_1_DEV', url: 'https://github.com/satishnamgadda/shopizer.git' 
            }
        }
        stage('build') {
            steps {
                sh '/usr/share/maven/bin/mvn package'
            }
        }
        stage('archive artifacts') {
            steps {
                archiveArtifacts artifacts: 'sm-shop/target/*.jar', followSymlinks: false
            }
        }
        stage('test results') {
            steps {
                junit 'sm-shop/target/surefire-reports/*.xml'
            }
        }
    }
}