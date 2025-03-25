pipeline{
    agent any

    stages{
        stage('build'){
            steps{
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build Docker Image'){
            steps{
                sh 'docker build -t=preetivijay/selenium:latest .'
            }
        }
        stage('Push Image'){
            environment{
                DOCKER_HUB = credentials('docker_credentials')
            }
            steps{
                sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                sh 'docker push preetivijay/selenium:latest'
                sh "docker tag preetivijay/selenium:latest preetivijay/selenium:${env.BUILD_NUMBER}"
                sh "docker push preetivijay/selenium:${env.BUILD_NUMBER}"
            }
        }
       stage('Run tests - chrome'){
            steps{
                sh 'docker-compose -f grid.yaml up --scale chrome=2 -d'
                sh 'BROWSER=chrome docker-compose up'
            }
       }
       stage('Run tests - firefox'){
                   steps{
                       sh 'docker-compose -f grid.yaml up --scale firefox=2 -d'
                       sh 'BROWSER=firefox docker-compose up'
                   }
              }

    }

    post{
        always{
            sh "docker-compose -f grid.yaml down"
            sh "docker-compose down"
        }
    }

}