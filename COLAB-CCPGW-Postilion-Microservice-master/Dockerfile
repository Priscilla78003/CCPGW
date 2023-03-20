#FROM java:8
FROM openjdk:8-jdk-alpine
EXPOSE 9079

COPY ./ccpgw-transaction-notification-microservice/config/ /config/

ADD /ccpgw-transaction-notification-microservice/ccpgw-transaction-notification-microservice-1.0.0.jar ccpgw-transaction-notification-microservice.jar
ENTRYPOINT ["java", "-jar", "ccpgw-transaction-notification-microservice.jar"] 
