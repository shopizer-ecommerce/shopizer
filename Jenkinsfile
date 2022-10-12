pipeline{
  agent { lable 'java_11'}
  stages{
    stage('shopizer'){
       steps{
          git branch:'master', url: 'https://github.com/vamsibakka/shopizer.git'
}
}
 stage('build'){
    steps{
      sh 'mvn package'
}
}
 stage('archive'){
    steps{
        junit '**/surefire-reports/*.xml'
}
}
}
}