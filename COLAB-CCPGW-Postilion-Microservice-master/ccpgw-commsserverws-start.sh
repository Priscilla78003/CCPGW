#!/bin/sh

IMAGE_NAME=microservice-ccpgw-comms-server-ws-async-1.0.0

PORT_MAPPING=9077:9077

DIRECTORY_MAPPING=$HOME/Truteq-Gogs-Server-Repo/CCPGW-Postilion-Microservice/ccpgw-comms-server-ws-async/config/:/config/

sudo docker run --net=host -it -v $DIRECTORY_MAPPING $IMAGE_NAME


