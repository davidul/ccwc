FROM maven:3.9.9-eclipse-temurin-23 as build

COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn -B -e -C -T 1C clean package

