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
                sh 'docker build -t=preetivijay/selenium .'
            }
        }
        stage('Push Image'){
            steps{
                sh 'docker push preetivijay/selenium'
            }
        }
    }

}