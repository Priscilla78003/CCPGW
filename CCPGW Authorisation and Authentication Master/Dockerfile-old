#FROM java:8
FROM openjdk:8-jdk-alpine
EXPOSE 9088

COPY ./ccpgw-authentication-authorisation-server/config/ /config/

ADD /ccpgw-authentication-authorisation-server/target/ccpgw-authentication-authorisation-server-1.10.0.jar ccpgw-authentication-authorisation-server.jar
ENTRYPOINT ["java", "-jar", "ccpgw-authentication-authorisation-server.jar"] 
