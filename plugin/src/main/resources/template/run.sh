if [ $# -eq 1 ]
   then
	port=$1
   else
        port="8080"
fi

java -cp "env/*" com.imaginea.embeddedserver.EmbeddedServer "app.war" $port
