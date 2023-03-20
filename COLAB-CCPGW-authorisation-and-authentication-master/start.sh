#!/bin/sh

IMAGE_NAME=microservice-ccpgw-authentication-authorisation-1.0

PORT_MAPPING=9088:9088

DIRECTORY_MAPPING=$HOME/Truteq-Dev/IPGW-docker-images/microservice-ccpgw-auhorisation-and-authentication/logs:/logs

sudo docker run --net=host -d -v $DIRECTORY_MAPPING $IMAGE_NAME


