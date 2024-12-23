@echo off
chcp 65001 > nul
setlocal enabledelayedexpansion

REM 启动类文件
set APP_MAIN=io.tiklab.kanass.starter.KanassApplication


set "current_path=%~dp0"
echo BAT_DATA_PATH：%current_path%

rem 进入目录
cd "%current_path%"

rem 上级目录
cd..

set DIRS=%cd%\

REM 输出基本文件地址
echo ROOT_DIRS:%DIRS%

REM 检查目录是否存在，如果不存在则创建
if not exist "%DIRS%" (
    echo ROOT_DIRS NOT FOUND CREATE......
	mkdir "%DIRS%"
    timeout /t 1 >nul
	if %errorlevel%==1 (
        echo Failed to create directory "%DIRS%"
    ) else (
        echo ROOT_DIRS CREATE SUCCESS
    )
)

REM 检查日志目录是否存在，如果不存在则创建
if not exist "%DIRS%\logs" (
    cd "%DIRS%"
    md "%DIRS%\logs"
)

REM 内嵌应用地址
set EMBEDDED_DIR=%DIRS%embbed
echo EMBEDDED_DIR:%EMBEDDED_DIR%

REM Yaml文件地址
set YAML_FILE=%DIRS%conf\application.yaml
set SERVER_PORT=
set DATE_HOME=
set PGSQL_PORT=
set PGSQL_ENABLE=false
set values=

rem 获取ServerPort
for /f "tokens=1,* delims=:" %%a in ('type "%YAML_FILE%"') do (
    rem 输出当前行内容，以便调试
    if "%%a" == "server" (
        set values=1
    )
    if "!values!" == "1" (
        if "%%a" == "  port" (
            set SERVER_PORT=%%b
            set values=0
            goto found
        )
    )
)

:found
set "SERVER_PORT=!SERVER_PORT: =!"
echo APPLY_SERVER_PORT:%SERVER_PORT%

rem 获取DateHome
for /f "tokens=1,* delims=:" %%a in ('type "%YAML_FILE%"') do (
    rem 输出当前行内容，以便调试
    if "%%a" == "DATA_HOME" (
         set DATE_HOME=%%b
    )
)

:found
set "DATE_HOME=!DATE_HOME: =!"
echo APPLY_DATA_HOME:%DATE_HOME%

rem 获取PgsqlPort
for /f "tokens=1,* delims=:" %%a in ('type "%YAML_FILE%"') do (
    rem 输出当前行内容，以便调试
    if "%%a" == "postgresql" (
        set values=1
    )
    if "!values!" == "1" (
        if "%%a" == "  db" (
            set values=2
        )
    )
    if "!values!" == "2" (
        if "%%a" == "    port" (
            set PGSQL_PORT=%%b
            set values=0
            goto found
        )
    )
)

:found
set "PGSQL_PORT=!PGSQL_PORT: =!"
rem echo Apply pgsql port:%PGSQL_PORT%


rem 获取PgsqlPort
for /f "tokens=1,* delims=:" %%a in ('type "%YAML_FILE%"') do (
    rem 输出当前行内容，以便调试
    if "%%a" == "postgresql" (
        set values=1
    )
    if "!values!" == "1" (
        if "%%a" == "  embbed" (
            set values=2
        )
    )
    if "!values!" == "2" (
        if "%%a" == "    enable" (
            set PGSQL_ENABLE=%%b
            set values=0
            goto found
        )
    )
)

:found
set "PGSQL_ENABLE=!PGSQL_ENABLE: =!"

REM 是否启用PGSQL
if %PGSQL_ENABLE% == true (
    echo USER ENABLE PGSQL PORT:%PGSQL_PORT%
) else (
    echo NOT USER ENABLE PGSQL
)


rem 判断java端口是否被占用
for /f "tokens=5" %%a in ('netstat -aon ^| findstr LISTENING ^| findstr "%SERVER_PORT%"') do (
    set SERVER_PID=%%a
)
if not "%SERVER_PID%" == "" (
    echo "PORT %SERVER_PORT% IS OCCUPIDED BY PROCESS WITH PID: %SERVER_PID%"
    exit
)

rem rem 判断pgsql端口是否被占用
rem for /f "tokens=5" %%a in ('netstat -aon ^| findstr LISTENING ^| findstr "%PGSQL_PORT%"') do (
rem     set PGSQL_PID=%%a
rem )

rem if "%PGSQL_ENABLE%" == "true" (
rem     if not "%PGSQL_PID%" == "" (
rem         echo "PORT %PGSQL_PORT% IS OCCUPIDED BY PROCESS WITH PID: %PGSQL_PID%"
rem         exit
rem     )
rem )

REM JDK路径
set JAVA_HOME=%EMBEDDED_DIR%\jdk-16.0.2
echo JAVA_HOME:%JAVA_HOME%
if not exist "%JAVA_HOME%" (
    echo UNABLE TO OBTAIN THE JAVA_HOME PATH!
    goto :start_error
)

REM PGSQL路径
set PGSQL_VERSION=pgsql-10.23
set PGSQL_DIR=%EMBEDDED_DIR%\%PGSQL_VERSION%
echo PGSQL_DIR:%PGSQL_DIR%

REM 进入JDK目录
cd "%JAVA_HOME%\bin"

REM 判断是否启动
set PID=0
for /f "usebackq tokens=1-2" %%a in (`.\jps.exe -l ^| findstr %APP_MAIN%`) do (
set PID=%%a
)

REM JAVA基本配置
set CLASSPATH=%DIRS%lib\*

set APP_HOME=%DIRS%
set APP_LOG=%DIRS%logs\
set APP_CONFIG=%DIRS%conf\application.yaml

set JAVA_OPTS=-server -Xms512m -Xmx512m -Xmn128m -XX:ParallelGCThreads=20 -XX:+UseParallelGC -XX:MaxGCPauseMillis=850 -"Xlog:gc:%APP_LOG%gc.log"
rem set JAVA_OPTS=%JAVA_OPTS% -"DlogPath=%APP_LOG%" -"Duser.timezone=GMT+08"
set JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF-8 -"Dspring.config.location=file:%APP_CONFIG%"
set JAVA_OPTS=%JAVA_OPTS% --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.sql/java.sql=ALL-UNNAMED

set FILE_PATH=%DATE_HOME%\other\apply_run.txt


REM 判断pid
if %PID%==0 (

    cd "%JAVA_HOME%\bin"

    start /b .\java.exe %JAVA_OPTS% -classpath "%CLASSPATH%" %APP_MAIN% >> "%DIRS%logs\app.log"
    echo The application is starting. Please wait ......

    del "%FILE_PATH%"

) else (
    echo =============================================================================================================
    echo %APP_MAIN% already started PID=%PID%
    echo ================================================================================================================
    exit
)

timeout /t 2 >nul

REM 获取状态
set state=
for /f "usebackq tokens=1-2" %%c in (`.\jps.exe -l ^| findstr %APP_MAIN%`) do (
    set state=%%c
)

REM 运行 ipconfig 命令并将输出重定向到临时文件
ipconfig > temp.txt

REM 从临时文件中提取 IPv4 地址信息并存储在变量中
for /f "tokens=2 delims=:" %%a in ('find "IPv4" temp.txt') do (
    set IP_ADDRESS=%%a
)

REM 删除临时文件
del temp.txt
set "IP_ADDRESS=!IP_ADDRESS: =!"


if "%state%"=="" (
    echo ================================================================================================================
    echo %APP_MAIN% START FAIL
    echo ================================================================================================================
)

echo %APP_MAIN% START SUCCESS (PID=%state%^)
rem echo find pgsql status......

rem rem REM 设置变量
rem set RETRY_LIMIT=40
rem set RETRY_COUNT=0


rem :check_file
rem REM 检查文件是否存在
rem if not exist "%FILE_PATH%" (
rem     rem echo 文件不存在，等待文件创建... 重试次数: %RETRY_COUNT%
rem     goto :wait_and_retry
rem )

rem REM 读取文件第一行内容
rem set /p FIRST_LINE=<%FILE_PATH%

rem REM 判断第一行内容是否为 1
rem if "%FIRST_LINE%"=="1" (
rem     rem echo 文件第一行内容为 1，继续执行...
rem     goto :continue
rem )

rem REM 如果内容不为 1
rem rem echo 文件第一行内容不是 1，等待 2 秒后重试... 重试次数: %RETRY_COUNT%

rem :wait_and_retry
rem REM 增加重试计数并等待
rem set /a RETRY_COUNT+=1
rem if %RETRY_COUNT% GEQ %RETRY_LIMIT% (
rem     echo 启动超时.....
rem     exit /b 1
rem )
rem timeout /t 3 >nul
rem goto :check_file

rem timeout /t 4 >nul
rem :continue
rem echo PGSQL START SUCCESS...

echo ====================================浏览器输入以下链接即可访问====================================================
echo http://%IP_ADDRESS%:%SERVER_PORT%
echo ================================================================================================================



cd %DIRS%bin
