@echo off
echo  Compiling...

if not exist out mkdir out

javac -d out ^
    src/main/java/kass_java/Model/*.java ^
    src/main/java/kass_java/repository/ProductRepository.java ^
    src/main/java/kass_java/repository/CategoryRepository.java ^
    src/main/java/kass_java/repository/TableRepository.java ^
    src/main/java/kass_java/service/ProductService.java ^
    src/main/java/kass_java/service/OrderService.java ^
    src/main/java/kass_java/service/CheckoutService.java ^
    src/main/java/kass_java/MainCLI.java

if %errorlevel% neq 0 (
    echo  Compilation failed!
    pause
    exit /b
)

echo  Running...
java -cp out kass_java.MainCLI

pause
