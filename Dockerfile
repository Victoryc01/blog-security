FROM openjdk:20
EXPOSE 8080
ADD target/blogTaskWithSecurity-0.0.1-SNAPSHOT.jar blog-sec-cicd.jar
ENTRYPOINT ["java","-jar","blog-sec-cicd.jar"]