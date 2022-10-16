pipeline{
  agent { label 'java_11'}
  trigger {pollscm('30 17 * * 1-5'')}
  stages{
    stage('shopizer'){
       steps{
          git branch:'develop', url: 'https://github.com/vamsibakka/shopizer.git'
}
}
 stage('build'){
    steps{
      sh 'mvn package'
      sh '/target/*.war'
}
}
 stage('archive'){
    steps{
        junit '**/surefire-reports/*.xml'
}
}
}
}