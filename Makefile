include .env

build:
	mvn clean package

build-docker:
	docker build -t $(DOCKER_IMAGE) .