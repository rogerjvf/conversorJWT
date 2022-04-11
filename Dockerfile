FROM openjdk:11

EXPOSE 8082

ADD target/conversor.jar conversor.java

ENTRYPOINT ["java","-jar", "conversor.java"]