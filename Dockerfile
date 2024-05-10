FROM openjdk:21
ADD target/banking-app.jar banking-app.jar
ENTRYPOINT ["java", "-jar", "banking-app.jar"]