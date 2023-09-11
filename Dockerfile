FROM amazoncorretto:17.0.8-alpine3.18
ARG JAR_FILE=target/*.jar
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar", "--spring.profiles.active=${SPRING_ACTIVE_PROFILE}"]
