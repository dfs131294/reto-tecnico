FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} tipocambio.jar
ENTRYPOINT ["java","-jar","/tipocambio.jar"]