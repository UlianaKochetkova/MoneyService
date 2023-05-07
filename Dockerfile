# creates a layer from the openjdk:17-alpine Docker image.
FROM openjdk:17-alpine

# cd /app
#WORKDIR /app
ARG JAR_FILE=./temp_money_service1/com/MoneyService/0.0.1-SNAPSHOT/MoneyService-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app/
EXPOSE 8090
CMD ["java", "-jar", "/app/MoneyService-0.0.1-SNAPSHOT.jar"]


#РАБОТАЮЩИЙ ВАРИАНТ ДЛЯ ДЕПЛОЯ
#COPY . /app/
#EXPOSE 8090
#CMD ["java", "-jar", "/app/temp_money_service1/com/MoneyService/0.0.1-SNAPSHOT/MoneyService-0.0.1-SNAPSHOT.jar"]

#РАБОТАЮЩИЙ ВАРИАНТ!!!!
#WORKDIR /app
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#COPY src ./src
#RUN chmod +x mvnw
#RUN ./mvnw dependency:go-offline
#EXPOSE 8090
#CMD ["./mvnw", "spring-boot:run"]