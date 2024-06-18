FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests


FROM amazoncorretto:17-alpine
COPY --from=build /target/API_rest.jar API_rest.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","API_rest.jar"]