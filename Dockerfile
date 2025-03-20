FROM bellsoft/liberica-openjdk-alpine:21.0.6
#install curl and jq utilities
RUN apk add curl jq
# create a workspace
WORKDIR /home/selenium-docker
# Add the required files
ADD target/docker-resources ./
ADD runner.sh runner.sh
# Start the runner script
ENTRYPOINT sh runner.sh