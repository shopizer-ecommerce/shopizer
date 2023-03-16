pipeline {

  agent any

  options {

    buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '5', daysToKeepStr: '', numToKeepStr: '5')
    }

  stages {

    stage('Hello') {

      steps {

        sh '''

          java -version

        '''
            }
        }
    }
}