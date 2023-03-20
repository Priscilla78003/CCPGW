#!/bin/sh

IMAGE_NAME=microservice-ccpgw-notification-1.0.0

PORT_MAPPING=9079:9079

DIRECTORY_MAPPING=/media/grant/GORData/Truteq-Gitea-Server-Repo/PlatformPAC/CCPGW-Postilion-Microservice/ccpgw-transaction-notification-microservice/config/:/config/

sudo docker run --net=host -it -v $DIRECTORY_MAPPING $IMAGE_NAME


