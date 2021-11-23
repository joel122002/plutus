# ATMS

This project creates an ATM GUI in Java using swing which is connected to a MySQL Database.

## Installation
For running this project you will be needing [Maven](https://maven.apache.org/). Click here to [download](https://maven.apache.org/download.cgi)

Clone this repo into a folder with the following directory structure

```bash
.
│   pom.xml
│
├───lib
│       mysql-connector-java-{version}.jar
│
├───src
│   ├───main
│   │   └───java
│   │       └───ATM_system
│   │               .gitignore
│   │               ATMS.form
│   │               ATMS.java
│   │               Credentials.java
│   │               DatabaseOperations.java
│   │               README.md
│   │               User.java
```
You can download the mysql-connector from [Maven Repository](https://mvnrepository.com/artifact/mysql/mysql-connector-java/)

The Credentials.java file contains all the credentials to connect to MySQL. Here is a demo

```java
package ATM_system;

public class Credentials {
    public static final String SQL_USERNAME = "username";
    public static final String SQL_PASSWORD = "password";
    public static final String SQL_URL = "jdbc:mysql://host:port/ATMS";
}
```
Now we have to create a database and table. For that we have to open mysql in the command line. Now we create the database.
```sql
CREATE DATABASE ATMS;
```

Now we create the table.

```sql
USE ATMS;
CREATE TABLE accounts (
    CARD_NUMBER BIGINT,
    PIN SMALLINT,
    BALANCE BIGINT
);
```
The last thing we need to set up is pom.xml . Simply copy paste the below code into pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>ATM_system</groupId>
    <artifactId>ATM_system</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>
    <dependencies>
        <dependency>
            <groupId>org.netbeans.external</groupId>
            <artifactId>AbsoluteLayout</artifactId>
            <version>RELEASE120</version>
        </dependency>
        <dependency>
            <groupId>org.xerial</groupId>
            <artifactId>sqlite-jdbc</artifactId>
            <version>3.34.0</version>
        </dependency>

    </dependencies>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
    </properties>
    <build>
        <plugins>
            <plugin>
                <artifactId>maven-assembly-plugin</artifactId>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>ATM_system.ATMS</mainClass>
                        </manifest>
                    </archive>
                    <descriptorRefs>
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>
                </configuration>
                <executions>
                    <execution>
                        <id>make-assembly</id>
                        <phase>package</phase> <!-- packaging phase -->
                        <goals>
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```
Now we're ready to go
## Usage
Change the directory to the root (i.e. where pom.xml is) and write the following commands
```bash
mvn compile
mvn exec:java -Dexec.mainClass=ATM_system.ATMS
```
To add a user/card number
```bash
mvn exec:java -D"exec.mainClass"="ATM_system.DatabaseOperations" -D"exec.args"="CARD_NUMBER PIN BALANCE"
```

## Creating a jar
To create a jar of Add User simply replace
```xml
<manifest>
    <mainClass>ATM_system.ATMS</mainClass>
</manifest>
```
with
```xml
<manifest>
    <mainClass>ATM_system.DatabaseOperations</mainClass>
</manifest>
```
To create the jar simply write
```bash
mvn clean install
```

To create the jar for the GUI you can use the same pom.xml for in [Installation](#Installation).
Again to create the jar simply write
```bash
mvn clean install
```