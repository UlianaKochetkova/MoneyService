# creates a layer from the openjdk:17-alpine Docker image.
FROM openjdk:17-alpine

# cd /app
WORKDIR /app

# Refer to Maven build -> finalName
ARG JAR_FILE=temp_money_service1/**/*.jar

# cp target/spring-boot-docker-0.0.1-SNAPSHOT.jar /app/spring-boot-docker.jar
COPY ${JAR_FILE} MoneyService.jar

# java -jar /app/spring-boot-docker.jar
CMD ["java", "-jar", "/app/MoneyService.jar"]

EXPOSE 8090

#РАБОТАЮЩИЙ ВАРИАНТ!!!!
#WORKDIR /app
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#COPY src ./src
#RUN chmod +x mvnw
#RUN ./mvnw dependency:go-offline
#EXPOSE 8090
#CMD ["./mvnw", "spring-boot:run"]