FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY ./target/personalinfo-1.0.0.jar /tmp/app.jar
EXPOSE 8087
RUN sh -c 'touch /app.jar'
ENTRYPOINT java -jar /tmp/app.jar