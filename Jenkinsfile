pipeline {
  agent any

  environment {
    DOCKERHUB_CREDENTIALS = credentials('DOCKER_HUB_CREDENTIAL')
    VERSION = "${env.BUILD_ID}"

  }

  tools {
    maven "Maven"
  }

  stages {

    stage('Maven Build'){
        steps{
        sh 'mvn clean package  -DskipTests'
        }
    }

     stage('Run Tests') {
      steps {
        sh 'mvn test'
      }
    }

    stage('SonarQube Analysis') {
  steps {
    sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar -Dsonar.host.url=http://3.226.243.114:9000/ -Dsonar.login=squ_584cdecd1adc97832e4d5d9ff41e11ebb1d7782d'
  }
}


   stage('Check code coverage') {
            steps {
                script {
                    def token = "squ_584cdecd1adc97832e4d5d9ff41e11ebb1d7782d"
                    def sonarQubeUrl = "http://3.226.243.114:9000/api"
                    def componentKey = "com.vipsfoodcourt:user-service"
                    def coverageThreshold = 10.0

                    def response = sh (
                        script: "curl -H 'Authorization: Bearer ${token}' '${sonarQubeUrl}/measures/component?component=${componentKey}&metricKeys=coverage'",
                        returnStdout: true
                    ).trim()

                    def coverage = sh (
                        script: "echo '${response}' | jq -r '.component.measures[0].value'",
                        returnStdout: true
                    ).trim().toDouble()

                    echo "Coverage: ${coverage}"

                    if (coverage < coverageThreshold) {
                        error "Coverage is below the threshold of ${coverageThreshold}%. Aborting the pipeline."
                    }
                }
            }
        }


      stage('Docker Build and Push') {
      steps {
          sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
          sh 'docker build -t vipskuttu/user-service:${VERSION} .'
          sh 'docker push vipskuttu/user-service:${VERSION}'
      }
    }


     stage('Cleanup Workspace') {
      steps {
        deleteDir()

      }
    }



    stage('Update Image Tag in GitOps') {
      steps {
         checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[ credentialsId: 'git-ssh', url: 'git@github.com:vipulmuralidhar/deployment-folder.git']])
        script {
       sh '''
          sed -i "s/image:.*/image: vipskuttu\\/user-service:${VERSION}/" aws/user-manifest.yml
        '''
          sh 'git checkout master'
          sh 'git add .'
          sh 'git commit -m "Update image tag"'
        sshagent(['git-ssh'])
            {
                  sh('git push')
            }
        }
      }
    }

  }

}


