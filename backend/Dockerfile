FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN chmod +x ./mvnw || echo "mvnw not found, using mvn"
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/job-app-tailor-backend-0.0.1-SNAPSHOT.jar"]
