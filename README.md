# Weight-tracker
Simple java program that consumes data produced by sensor-emulator and stores them into Mongo database.It exposes REST based web services for reading the metrics and alerts stored in the database.

```
{
    {
         "value" : "150"
        "timeStamp" : "143745874587"
    },
    {
         "value" : "153"
        "timeStamp" : "143745874587"
    }
}
```

## How to build it locally?
```
Run Mongo service: mongod (Make sure its running on default port)

mvn clean install
```

## How to run it?
```
mvn spring:boot-run
```

## How to use it with sensor-emulator?
```
java -jar -Dbase.value=150 -Dapi.url=http://localhost:8080/postWeight sensor-emulator-0.0.1-SNAPSHOT.jar

url is as specified above. postWeight is the endpoint which consumes the data posted by the sensor
Endpoints exposed:
Retrieve All metrics: curl -i -H "Accept: appliX GET http://localhost:8080/retrieveAllMetrics
Retrieve IN time range: curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET "http://localhost:8080/retrieveMetricsInRange?from=1464329497133&to=1464329508897"

Retrieve All alerts: curl -i -H "Accept: appliX GET "http://localhost:8080/retrieveAllAlertMessages"
Retrieve Alerts in range: curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET "http://localhost:8080/retrieveAllAlertMessages?from=1464329497133&to=1464329508897"
```

## How to point to cloud deployed weight-tracker
```
    I have deployed this project on AWS EC2 cloud. In case of any issues in setting up, you can point to the cloud url to post the weights
    Cloud URL: http://54.183.200.233:8080

    Retrieve All metrics: curl -i -H "Accept: appliX GET http://54.183.200.233:8080/retrieveAllMetrics
    Retrieve IN time range: curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET "http://54.183.200.233:8080/retrieveMetricsInRange?from=1464329497133&to=1464329508897"

    Retrieve All alerts: curl -i -H "Accept: appliX GET "http://54.183.200.233:8080/retrieveAllAlertMessages"
    Retrieve Alerts in range: curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET "http://54.183.200.233:8080/retrieveAllAlertMessages?from=1464329497133&to=1464329508897"

```
## Pending Things
```
I was facing some issues writing Integration Test using Mockito. Need to fix that. So unit tests for REST services are pending.
```

