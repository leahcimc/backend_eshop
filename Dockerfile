FROM eclipse-temurin:17-jdk-focal
VOLUME /tmp
ARG JAR_FILE
COPY ./build/libs/backend_eshop-0.0.2-SNAPSHOT.jar Project_Backend.jar
ENTRYPOINT ["java","-jar","/Project_Backend.jar"]