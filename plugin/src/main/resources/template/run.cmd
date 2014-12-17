SET warPath='dir *.war'

IF "$1"!="" SET port="$1"
IF "$1"=="" SET port="8080"

java -cp "env/*" com.imaginea.embeddedserver.EmbeddedServer $warPath $port
