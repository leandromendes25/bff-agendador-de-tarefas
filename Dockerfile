FROM maven:3.9-amazoncorretto-21 AS BUILD
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTest

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
expose 8083
CMD ["java","-jar","/app/BFF.jar"]