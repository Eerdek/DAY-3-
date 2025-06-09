@echo off
echo Compiling...

javac -d out ^
    .\src\main\java\kass_java\Model\*.java ^
    .\src\main\java\kass_java\repository\*.java ^
    .\src\main\java\kass_java\service\*.java ^
    .\src\main\java\kass_java\helper\*.java ^
    .\src\main\java\kass_java\Main.java ^
    .\src\main\java\kass_java\MainCLI.java

echo Running...
java -cp out kass_java.Main
pause
