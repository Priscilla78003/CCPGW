#!/bin/sh

IMAGE_NAME=microservice-ccpgw-communication-server-1.0.0

PORT_MAPPING=16011:16011

DIRECTORY_MAPPING=$HOME/Truteq-Gogs-Server-Repo/CCPGW-Postilion-Microservice/ccpgw-communication-server/docker-image/ccpgw-communication-server/config/:/config/

DIRECTORY_MAPPING_LIB=$HOME/Truteq-Gogs-Server-Repo/CCPGW-Postilion-Microservice/ccpgw-communication-server/docker-image/ccpgw-communication-server/lib/:/lib/

sudo docker run --name=commsServ --net=host -d -v $DIRECTORY_MAPPING $IMAGE_NAME
