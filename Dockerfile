FROM openjdk:17

COPY ./target/user-mock-service-0.0.1.jar /app/user-mock-service-0.0.1.jar

WORKDIR /app/

EXPOSE 8082

CMD [ "java", "-jar", "/app/user-mock-service-0.0.1.jar" ]