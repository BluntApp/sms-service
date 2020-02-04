FROM java:8
ADD target/sms-service.jar sms-service.jar
ENTRYPOINT ["java","-jar","sms-service.jar"]
