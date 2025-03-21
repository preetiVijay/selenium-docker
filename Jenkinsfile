pipeline{
    agent any
//     agent none

    stages{
        stage('build'){
//         agent{
//              docker{
//                  image 'maven:3.9.3-eclipse-temurin-17-focal'
//               }
//          }
            steps{
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build Docker Image'){
//            steps{
//             script{
//                 app = docker.build('preetivijay/selenium')
//             }
//                }
            steps{
                sh 'docker build -t=preetivijay/selenium:latest .'
            }
        }
        stage('Push Image'){
            environment{
                DOCKER_HUB = credentials('docker_credentials')
            }
            steps{
//                 sh 'docker login -u ${DOCKER_HUB_USR} -p ${DOCKER_HUB_PSW}'
                sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                sh 'docker push preetivijay/selenium:latest'
                sh "docker tag preetivijay/selenium:latest preetivijay/selenium:${env.BUILD_NUMBER}"
                sh "docker push preetivijay/selenium:${env.BUILD_NUMBER}"
            }
//             steps{
//                 script{
//                     docker.withRegistry('', 'docker_credentials'){
//                         app.push("latest")
//                     }
//                 }
//             }
        }
    }

    post{
        always{
            sh "docker logout"
        }
    }

}