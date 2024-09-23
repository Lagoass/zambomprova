FROM amazoncorretto:21
COPY target/zambomprova-0.0.1-SNAPSHOT.jar /usr/app/
ENTRYPOINT ["java", "-jar", "/app.jar"]