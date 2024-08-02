FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/POC-DB-aisolation-levels-0.0.1-SNAPSHOT.jar /app
CMD ["java", "-jar", "POC-DB-aisolation-levels-0.0.1-SNAPSHOT.jar"]
EXPOSE 9001
# build:
# docker build -t poc .
# run:
# docker run -p 9001:9001 --network infrastructure_general poc