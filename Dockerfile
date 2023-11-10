FROM openjdk:17
EXPOSE 8080
ADD target/blogTaskWithSecurity-0.0.1-SNAPSHOT.jar blog-sec-cicd.jar
ENTRYPOINT ["java","-jar","blog-sec-cicd.jar"]

HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:8080/actuator/health || exit 1
