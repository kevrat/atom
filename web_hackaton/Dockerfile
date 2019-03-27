#1
FROM gradle:4.5.1-jdk10 as compile
#2
COPY . /home/source/java
#3
WORKDIR /home/source/java
# Default gradle user is `gradle`. We need to add permission on working directory for gradle to build.
#4
USER root
#5
RUN chown -R gradle /home/source/java
#6
USER gradle
#7
RUN gradle clean build
FROM openjdk:11-jre
WORKDIR /home/application/java
#8
COPY --from=compile "/home/source/java/build/libs/java.jar" /services/chat.jar
EXPOSE 8080
#9
CMD ["java", "-jar", "/services/chat.jar"]