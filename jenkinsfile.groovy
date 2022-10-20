pipeline {
<<<<<<< HEAD
    agent {label ('shopizer')}
    parameters {
    choice(name: 'branches', choices: ['one', 'two', 'three'], description: '')
    }
}
=======
    agent { label 'SHOPIZER' }                  
    stages {
        stage('vcs') {
            steps {
                git branch: 'release',
                       url: 'https://github.com/maheshryali/shopizer.git'
            }
        }
        stage('maven build') {
            steps {
                sh ("mvn package")
                }
        }
        stage( 'artifacts') {
            steps {
            junit '**/surefire-reports/*.xml'
            }
        }
}
        triggers { cron('30 17 * * *') }
}
>>>>>>> edf95ff29db753fa937c26c0c631351079a04453
