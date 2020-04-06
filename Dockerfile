FROM openjdk:11
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY ./ddalivery-api/build/libs/* ./ddalivery-api-1.0-RELEASE.jar
EXPOSE 11000
CMD ["java","-jar","ddalivery-api-1.0-RELEASE.jar"]
