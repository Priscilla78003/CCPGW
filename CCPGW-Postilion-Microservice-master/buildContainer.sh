docker build -t microservice-ccpgw-notification-1.14.9 .

docker save microservice-ccpgw-notification-1.14.9 | gzip > microservice-ccpgw-notification-1.14.9.tar.gz

rsync -azvuP microservice-ccpgw-notification-1.14.9.tar.gz ccpgw@10.160.30.93:~/CCPGW-Docker-Images/microservice-ccpgw-notification-1.14.9.tar.gz

