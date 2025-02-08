include .env

build:
	mvn clean compile package

build-docker:
	docker build -t $(DOCKER_IMAGE) .