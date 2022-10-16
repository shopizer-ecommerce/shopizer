pipeline{
    agent{label 'shopizer'}
    triggers { pollSCM('* * * * * ') }
    parameters { string(name:'mvncmd',defaultValue:'package',description:'build goal')}
    stages{
        stage('vpc'){
            steps{
                git url:'https://github.com/saisatyateja/my-shopizer.git',
                    branch: 'master'
            }
        }
        stage('build'){
            steps{
                sh "mvn '${params.mvncmd}'"
            }
        }
        stage('Arachive Artifacts'){
            steps{
                archiveArtifact artifacts: '**/sm-core-model/target/*.jar'
            }
        }
        stage('test reports'){
            steps{
                junit '**/surefire-reports/*.xml'
            }
        }
    }
}