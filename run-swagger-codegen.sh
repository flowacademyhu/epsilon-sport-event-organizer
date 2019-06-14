#!/bin/bash

if [ ! -z $1 ];
then
 export SWAGGER_URL=$1
fi

if [[ -z ${SWAGGER_URL} ]];
then
 echo "Setup SWAGGER_URL env:"
 read swagger_api_url
 export SWAGGER_URL=$swagger_api_url
else
 echo "API docs url is: ${SWAGGER_URL}"
fi

java -version 2>/dev/null || exit

curl ${SWAGGER_URL} -s || echo "Server not running or url is invalid!" && exit

if [ ! -f ./swagger-codegen-cli-2.2.1.jar ]; then
  wget https://oss.sonatype.org/content/repositories/releases/io/swagger/swagger-codegen-cli/2.2.1/swagger-codegen-cli-2.2.1.jar
fi

java -jar ./swagger-codegen-cli-2.2.1.jar generate -l typescript-angular -i ${SWAGGER_URL} -o ./client/api

