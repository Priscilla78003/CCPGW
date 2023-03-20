FROM openjdk:8-jdk-alpine
#FROM openjdk:17-alpine

EXPOSE 9092

RUN addgroup -g 1000 ccpgw
RUN adduser -u 1000 -G ccpgw -h /home/ccpgw -D ccpgw

ADD ./target/ccpgw-SOAP-frontend-adapter-1.0.0.jar ccpgw-SOAP-frontend-adapter.jar

RUN mkdir /config
RUN mkdir /logs

RUN chown -R ccpgw:ccpgw /config
RUN chown -R ccpgw:ccpgw /logs
RUN chown -R ccpgw:ccpgw ccpgw-SOAP-frontend-adapter.jar

USER ccpgw

ENTRYPOINT ["java", "-jar", "ccpgw-SOAP-frontend-adapter.jar"]  
