FROM amazoncorretto:17.0.8-alpine3.18
COPY build/libs/demo-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=${SPRING_ACTIVE_PROFILE}"]