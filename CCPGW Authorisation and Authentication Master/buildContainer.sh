docker build -t microservice-ccpgw-authentication-authorisation-2.0.0 .

docker save microservice-ccpgw-authentication-authorisation-2.0.0 | gzip > microservice-ccpgw-authentication-authorisation-2.0.0.tar.gz

rsync -azvuP microservice-ccpgw-authentication-authorisation-2.0.0.tar.gz ccpgw@10.160.30.95:~/CCPGW-Docker-Images/microservice-ccpgw-authentication-authorisation-2.0.0.tar.gz
