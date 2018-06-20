FROM openjdk:8-jdk-alpine
EXPOSE 9000
VOLUME /tmp
ARG build/libs/
ADD product-manager-service-0.0.1-SNAPSHOT.jar product-manager-service-0.0.1-SNAPSHOT.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /product-manager-service-0.0.1-SNAPSHOT.jar