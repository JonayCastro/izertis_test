FROM openjdk:17-jdk-slim
WORKDIR /APP
COPY target/ /APP
EXPOSE 8080
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]
