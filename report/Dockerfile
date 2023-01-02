FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY ./target/report-1.0.0.jar /tmp/app.jar
EXPOSE 8085
RUN sh -c 'touch /app.jar'
ENTRYPOINT java -jar /tmp/app.jar