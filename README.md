# Sales Analyzer
System that analyzes files from ~/data/in and puts the analyzes in the ~/data/out folder. The original files are moved to ~/data/in/read. By default the routine runs every 10 seconds.
## Prerequisites

1. Maven 3.5 or newer
2. Java SE 8 or newer
3. Docker 17 or newer to build and run docker images

Verify prerequisites
```
java --version
mvn --version
docker --version
```

Create folder `<HOME_PATH>/data/in`, `<HOME_PATH>/data/in/read` and `<HOME_PATH>/data/out`
## Build

```
mvn package
```

## Start the application
From project folder:
```
java -jar target/sales-analyzer.jar
```

## Exercise the application

```
curl -X GET http://localhost:9999/actuator/health
{"status":"UP"}

```

## Build the Docker Image
From project folder:
```
docker build -t sales-analyzer target
```

## Start the application with Docker

```
docker run -p 9999:9999 -v <HOME_PATH>/data:/root/data sales-analyzer
```
