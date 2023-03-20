#FROM java:8
FROM openjdk:8-jdk-alpine
EXPOSE 9088

ADD ccpgw-authentication-authorisation-server-1.0.0.jar ccpgw-authentication-authorisation-server.jar

ENTRYPOINT ["java", "-jar", "ccpgw-authentication-authorisation-server.jar"] 
