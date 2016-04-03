# TDA367-project
Group project in Chalmers course TDA367. 

**Commands listed below must be executed from inside the `get_rect` folder.**

## Prerequisites 
* Gradle
* Java 1.7 or higher

## Run on desktop
```
gradle desktop:run
```

## Run JUnit tests
```
gradle core:test
```

### View test coverage report
Run tests, then open `core/build/reports/jacoco/html/index.html`

## Build as a standalone JAR file
```
gradle desktop:dist
```

JAR will be available in `desktop/build/libs/`