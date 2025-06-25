# Этап 1: Сборка WAR-файла
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Этап 2: Tomcat и деплой .war
FROM tomcat:10.1-jdk17
WORKDIR /usr/local/tomcat

# Удаляем стандартные приложения Tomcat
RUN rm -rf webapps/*

# Копируем war в ROOT.war (чтобы открывался по http://localhost:8080/)
COPY --from=build /app/target/todo_list.war webapps/ROOT.war

EXPOSE 8080