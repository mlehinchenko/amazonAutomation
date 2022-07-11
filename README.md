# Amazon Test Automation Project

This is the Amazon test automation project. It supports Web UI testing.

## Table of Contents

1. Environment requirements
2. Test project configuration
3. Running tests
4. Docker tests run
5. Allure report
6. Project dependencies

## 1. Environment requirements

**Required:** Chrome browser, Java Development Kit 11 (JDK11) and Maven 3 should be present on environment in order to develop and/or run the tests.

Also, https://projectlombok.org/ is used to simplify creating/editing Java POJOs (data models). So in order to work with Project from IDE Lombok plugin should be installed.

## 2. Test project configuration

Run this command from the start to ensure that all project dependencies installed

```
$ mvn clean install -U -DskipTests=true
```

## 3. Running tests

In most simple way this command could be used to start tests execution:

```
$ mvn clean test -Dsuite=<suite> -Denv=<environment> -Dconfig=<config>
```

Possible values for the command line properties:

* `suite` is `simple_amazon`
* `env` is `amazon`
* `config` is `docker-driver`,`local-driver`

Example:
```
$ mvn clean test -Dsuite=simple_amazon -Denv=amazon -Dconfig=local-driver
```
## 4. Docker tests run
We can create a docker image of our Automation code. 
After that, we can use the same docker image as a service in a Docker-compose
file to run those test cases on the dockerized grid.
Prerequisites: Install Docker

To run tests in docker container please do commands below:
1. Need to open terminal with Dockerfile location

2. Run the below command to create a docker image
```
docker build -t amazonautomation:latest .
```
**docker**: To start any docker command

**build**: To build image

**-t**: Tag the image with latest tagname

**amazonautomation**: Name of the image

**.** : Dockerfile is present in my current directory, hence used dot

3. Execute docker-compose command to run tests on the dockerized grid: 
```
docker-compose run test
```
We can check execution in docker grid by accessing http://localhost:4444/ui.

Also, we can take a look on video of test execution in real time with next steps: 

1. Go to Session
2. Click on Video icon next to Test run 
2. Enter password 'secret' and press 'Ok' button
## 5. Allure report

Use `mvn allure:serve` to open up allure report. The same is working for tests that run in docker grid.

Also, during test execution the appropriate log message will output in the console.

## 6. Project dependencies

* `selenide` - wrapper on top of webDriver API client for browser manipulations.
* `webdrivermanager` - browsers driver resolver for managing webDriver binaries.
* `testng` - unit test framework for tests execution.
* `assertj-core` - matchers library for providing test assertions (when selenide assertions cannot be used).
* `allure-testng` - allure adapter for testng that enables logging feature.
* `aspectjweaver` - aspects weaver for AOP.
* `lombok` - helper library that simplifies creating/editing Java POJOs.