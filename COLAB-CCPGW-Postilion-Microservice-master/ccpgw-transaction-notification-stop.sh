#/bin/sh

IMAGE_NAME=microservice-ccpgw-notification-1.0.0

CONTAINER_PIDS=`sudo docker ps | awk '/'$IMAGE_NAME'/{print $1}'`

#echo $CONTAINER_PIDS
for CONTAINER_PID in $CONTAINER_PIDS
do
  echo Stopping $CONTAINER_PID
  sudo docker stop $CONTAINER_PID
done
sudo docker ps 
