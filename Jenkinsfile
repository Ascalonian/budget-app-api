pipeline {
  agent any
  stages {
    stage('Build') {
      agent any
      steps {
        echo 'Start building project'
        sh 'mvn clean install -Dlicense.skip=true -DskipTests=true'
        echo 'Building finished'
      }
    }

    stage('Test') {
      steps {
        echo 'Start unit testing'
        sh 'mvn test'
        echo 'Testing finished'
      }
    }

    stage('Approval') {
      steps {
        input 'Okay to proceed?'
      }
    }

  }
  tools {
    maven 'maven'
  }
}