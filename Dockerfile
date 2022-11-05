FROM openjdk:11
ARG jar_file=/build/libs/composer-0.0.1-SNAPSHOT.jar
COPY ${jar_file} composer.jar
ENTRYPOINT ["java","-jar","/composer.jar"]