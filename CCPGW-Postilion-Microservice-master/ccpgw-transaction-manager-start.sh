#!/bin/sh

IMAGE_NAME=microservice-ccpgw-transaction-manager-1.0.0

PORT_MAPPING=9078:9078

DIRECTORY_MAPPING=$HOMET/Truteq-Gogs-Server-Repo/CCPGW-Postilion-Microservice/ccpgw-transaction-manager-microservice/docker-image/ccpgw-transaction-manager-microserv/config/:/config/

sudo docker run --net=host -it -v $DIRECTORY_MAPPING $IMAGE_NAME


