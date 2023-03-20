#!/bin/sh

IMAGE_NAME=mariadb-ccpgw-transactional-db.1.3

PORT_MAPPING=33306:3306

DIRECTORY_MAPPING_1=$HOME/Truteq-Dev/Data/mariadb-ccpgw/config:/etc/mysql/conf.d

DIRECTORY_MAPPING_2=$HOME/Truteq-Dev/Data/mariadb-ccpgw/data:/var/lib/mysql 

sudo docker run --restart=always --name=ccpgwtransactdb -d \
-p  $PORT_MAPPING \
-v $DIRECTORY_MAPPING_1 \
-v $DIRECTORY_MAPPING_2 \
-e ALLOW_EMPTY_PASSWORD=yes \
-e MARIADB_USER=secure_ccpgwuser \
-e MARIADB_DATABASE=ccpgwtransactdb \
-e MYSQL_ROOT_PASSWORD=ccpgwtr4ns4ctdb4dm1n \
-e MARIADB_PASSWORD=53cur3u53r \
$IMAGE_NAME
