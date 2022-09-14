FROM maven:3.8.6-jdk-8 as build
COPY . /app
WORKDIR /app
RUN mvn -f pom.xml clean package -DskipTests

FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY --from=build /app/target/e-commerce-0.0.1-SNAPSHOT.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","app.jar"]