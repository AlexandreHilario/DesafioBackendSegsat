FROM gradle:8.8-jdk21 AS builder

WORKDIR /home/gradle/src

COPY --chown=gradle:gradle . /home/gradle/src

RUN gradle build --no-daemon

#OpenJDK
FROM openjdk:21

WORKDIR /app

COPY --from=builder /home/gradle/src/build/libs/*.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","/app/app.jar"]