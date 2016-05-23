# TDA367-project
Group project in Chalmers course TDA367. 

**Commands listed below must be executed from inside the `get_rect` folder.**

## Prerequisites 
* Gradle 2.11
	 * with Java 1.8 as Gradle JVM
* Java 1.8 or higher

## Game controls
When Xbox Controller is attached, keyboard still works. No additional setting neeeds to be done.

| Action            | Keyboard   | Xbox Controller   |
|-------------------|------------|-------------------|
| Player movement   | W A S D    | Left joystick     |
| Shoot (and aim)   | Arrow keys | Right joystick    |
| Jump              | Space      | Right trigger     |
| Interact          | E          | A                 |
| Confirm (in menu) | Enter      | A                 |
| Switch weapon     | Q          | Y                 |
| Pause             | ESC        | START             |
| Exit menus        | ESC        | B                 |
| Menu movement     | Arrow keys | Arrow keys (DPAD) |

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