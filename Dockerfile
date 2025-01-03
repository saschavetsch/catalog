# Did not find Maven:4.0.0
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /catalog
COPY pom.xml .
COPY src ./src
# Skip tests, run in CI/CD pipeline before building the image
RUN mvn clean package -DskipTests
FROM eclipse-temurin:21
WORKDIR /catalog
COPY --from=build /catalog/target/CatalogApplication.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]