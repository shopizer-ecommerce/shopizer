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
        stage ('Artifactory configuration') {
            steps {
                rtMavenDeployer (
                    id: "Maven_1",
                    serverId: "shopizer",
                    releaseRepo: 'tarun-libs-release-local',
                    snapshotRepo: 'tarun-snapshot-release-local'
                )
            }
        }
        stage ('Exec Maven') {
            steps {
                rtMavenRun (
                    tool: 'MVN-3.6.3', // Tool name from Jenkins configuration
                    pom: 'pom.xml',
                    goals: 'clean install',
                    deployerId: "MAVEN_DEPLOYER"
                )
            }
        }
        stage ('Publish build info') {
            steps {
                rtPublishBuildInfo (
                    serverId: "Maven_1"
                )
            }
        }
    }
}