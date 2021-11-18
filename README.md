# Reactive API integrates with Redis

Sample project of Reactive API integrates with Redis

## Getting Started

### Prerequisites

* [JDK 8](http://www.oracle.com/technetwork/pt/java/javase/overview/index.html) - Ensure JAVA_HOME environment variable
  is set and points to your JDK installation

* [Maven](https://maven.apache.org/) - Dependency Management Download from https://maven.apache.org/

* [Redis](https://redis.io/topics/quickstart) - Memory DB to save pre-calculated result

### Installing & Running the tests

Run maven clean install command will install the dependencies, compile and run the tests

```
mvn clean install
```

## Deployment

JAR package will be created under target/reactive-api-integrate-with-redis-{versionNo}.jar after packaging then you can run below
command to bring up the application

```
java -jar target/reactive-api-integrate-with-redis-{versionNo}.jar
```

## Usage

Reactive API is Asynchronous and Non-Blocking which is fit for high frequency request system.  
Path mapping is defined in the Router class, then handler will be invoked to handle business logic like service class.

### Save value

```
POST http://localhost:8080/api/test
```
Request Body
```
{
    "id": "metric1",
    "value": [
        {
            "timestamp": "2021-07-06T00:00:00.000+12:00",
            "event": {
                "unique_agents": 97,
                "total_records": 97
            }
        },
        {
            "timestamp": "2020-07-07T00:00:00.000+12:00",
            "event": {
                "unique_agents": 31,
                "total_records": 31
            }
        }
    ]
}
```
Metric result info in the RequestBody will be saved into Redis DB

### Fetch value

```
GET http://localhost:8080/api/test/{id}
```
Fetch Metric result info from Redis DB by id. Will return 404 error if can't find.

## Reference
[Build Reactive APIs with Spring WebFlux](https://developer.okta.com/blog/2018/09/24/reactive-apis-with-spring-webflux)  
[Difference between Reactive API and Synchronous API](https://dzone.com/articles/build-reactive-rest-apis-with-spring-webflux)