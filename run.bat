@echo off
echo  Compiling...

REM --- Build classpath from Maven local repo ---
set LOG4J_CP=lib\log4j-api-2.17.2.jar;lib\log4j-core-2.17.2.jar

javac -cp %LOG4J_CP% -d out ^
    .\src\main\java\kass_java\Model\*.java ^
    .\src\main\java\kass_java\repository\*.java ^
    .\src\main\java\kass_java\service\*.java ^
    .\src\main\java\kass_java\helper\*.java ^
    .\src\main\java\kass_java\Main.java ^
    .\src\main\java\kass_java\MainCLI.java

echo Running...
java -cp out;%LOG4J_CP% kass_java.MainCLI
pause
