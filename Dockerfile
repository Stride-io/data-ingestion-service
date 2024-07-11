FROM amazoncorretto:17.0.11-alpine
EXPOSE 8080

ARG JAR_FILE=../build/libs/*.jar
RUN echo JAR_FILE

COPY ${JAR_FILE} data-ingestion-service.jar
ENTRYPOINT ["java","-jar","/data-ingestion-service.jar"]