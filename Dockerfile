FROM openjdk:12-alpine

COPY target/UsiPusi.jar /demo.jar

CMD ["java", "-jar", "/demo.jar"]