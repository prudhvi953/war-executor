@echo off
echo %1
if "%1" == "" (
	SET port="8080"
) else (
	SET port=%1
)

java -cp "env/*" com.imaginea.embeddedserver.EmbeddedServer "app.war" "%port%"