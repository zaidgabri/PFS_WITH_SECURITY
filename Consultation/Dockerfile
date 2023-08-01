FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE}  consulationservice.jar
ENTRYPOINT ["java","-jar","/consultationservice.jar"]
EXPOSE 8082