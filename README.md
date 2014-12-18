war-executor
=========
war-executor is a maven plugin makes deploying war and bringing up web server(Jetty for now) as simple as running a script. It helps in distributing the war with embedded server as zip format (currently supporting)

### How to use war-executor plugin:
* war-executor can be added to your maven projects by adding the following lines under ``` <plugins> ... </plugin> ``` in pom.xml.
```
<plugin>
	<groupId>com.imaginea.warexecutor</groupId>
		<artifactId>plugin</artifactId>
			<configuration>
				<warFile>${project.build.directory}/${project.artifactId}.war</warFile>
				<defaultPort>9000</defaultPort>
			</configuration>
			<executions>
				<execution>
					<goals>
						<goal>war-server-assembly</goal>
					</goals>
				<phase>package</phase>
				</execution>
			</executions>
</plugin> 
```
* On running Successful mvn package on your project you should see a .zip file in your target directory[this is by default unless you changed in plugin source before installing] something like this projectname.executable.zip

### How to use output .zip file:
1. Extract the .zip file to your convenient directory and in that directory you should see run.sh (for *nix based OS) and run.cmd ( for windows based OS).
2. Ex: > ```./run.sh``` or > ```./run.cmd```  
You can specify port number as optional argument like > ```./run.sh 8045```  or >```./run.cmd 8045```
Home page of web application will be opened in default browser.

*Note : You might get permission denied for which you need to add executable permissions to run.sh/run.cmd files.*

### TODO
1. Instead of default Jetty server, war-executor should be able to read server configuaration(ex: version,  deployment method) from project's pom and deploy war application in it.
