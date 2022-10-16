pipeline{
    agent{label 'shipizer'}
    stages{
        stage('vpc'){
            steps{
                git url:'https://github.com/saisatyateja/my-shopizer.git',
                    branch: 'master'
            }
        }
        stage('build'){
            steps{
                sh 'mvn package'
            }
        }
        stage('Arachive Artifacts'){
            steps{
                archiveArtifact artifacts: '**/target/*.jar'
            }
        }
        stage('test reports'){
            steps{
                junit: '**/surefire-reports/*.xml'
            }
        }
    }
}