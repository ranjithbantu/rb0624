## Set Up Your Environment

Make sure you have the following installed:
- Java Development Kit (JDK) 8 or higher
- Standalone JAR file for running tests on the JUnit Platform.
- An Integrated Development Environment (IDE) like IntelliJ IDEA, Eclipse, or Visual Studio Code


## Use Following Commands to Compile and Test the Application

- Complie the application.
```java
javac -d bin -cp "lib/*" src/*.java test/*.java
````

- Run JUnit test cases.
```java
java -jar lib/junit-platform-console-standalone-1.11.0-M2.jar --class-path bin --scan-class-path
````
