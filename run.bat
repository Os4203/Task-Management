@echo off
echo ========================================
echo     Task Management System - Maven
echo ========================================
echo.

echo Cleaning and compiling the project...
call mvn clean compile

if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo.
echo Choose an option:
echo 1. Run Database Setup
echo 2. Run Main Application  
echo 3. Run with Debug Info
echo 4. Package to JAR
echo 5. Exit
echo.
set /p choice="Enter your choice (1-5): "

if "%choice%"=="1" (
    echo.
    echo Running Database Setup...
    call mvn exec:java -Dexec.mainClass="DatabaseSetup"
) else if "%choice%"=="2" (
    echo.
    echo Running Task Management Application...
    call mvn exec:java -Dexec.mainClass="Main"
) else if "%choice%"=="3" (
    echo.
    echo Running with debug information...
    call mvn exec:java -Dexec.mainClass="Main" -Dexec.args="-Xdebug"
) else if "%choice%"=="4" (
    echo.
    echo Packaging application to JAR...
    call mvn package
    echo.
    echo JAR created in target directory!
    echo To run: java -jar target/task-management-1.0.0-jar-with-dependencies.jar
) else if "%choice%"=="5" (
    echo Goodbye!
    exit /b 0
) else (
    echo Invalid choice!
)

echo.
pause