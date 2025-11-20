FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean package -DskipTests

EXPOSE 9095
CMD ["java", "-jar", "target/app-0.0.1-SNAPSHOT.jar"]
