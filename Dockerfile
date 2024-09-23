FROM amazoncorretto:21
COPY target/prova-0.0.1-SNAPSHOT.jar /usr/app/
ENTRYPOINT ["java", "-jar", "/app.jar"]