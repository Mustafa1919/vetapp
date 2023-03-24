FROM openjdk:17
WORKDIR /app
COPY target/veterinary-0.0.1-SNAPSHOT.jar veterinary-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","veterinary-0.0.1-SNAPSHOT.jar"]