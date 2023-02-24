FROM openjdk:17-oracle

#Information around who maintains the image
MAINTAINER com.abc.mohib.bank

COPY target/card-service-0.0.1-SNAPSHOT.jar card-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/card-service-0.0.1-SNAPSHOT.jar"]