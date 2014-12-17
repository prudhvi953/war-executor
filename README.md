war-executor
=========
war-executor is a maven plugin makes deploying war and bringing up web server(Jetty for now) as simple as running a script. It helps in distributing the war with embedded server as zip format (currently supporting)
 
war-executor has two components, 1) **Embedded Server** and 2) **Plugin**. Embedded server is responsible for bringing up the server at specified port. On install plugin installs itself to local
maven repository which zips Embedded server and war file together along with scripts for running. Once installed this plugin can be used in maven projects. 

### Steps to install war-executor on your machine:
1. Download the source code (You can do git <a href='http://git-scm.com/docs/git-clone'>clone</a>).
2. Make sure that JDK 6 or later, maven(3.2.3) or above are on path. If you don't have maven you can get it from <a href='http://maven.apache.org/download.cgi'>here</a> and install.
3. Open a terminal, browse to the directory where you have downloaded 
and give command  
> ```mvn clean install```
4. On getting 'BUILD SUCCESS' message, plugin is installed.

### How to use war-executor plugin:
* Upon successful installation of plugin you can use this in your maven projects by adding the following lines under <plugins>  </plugin> in pom.xml.
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
1. Extract the .zip file to your convenient directory and in that directory you should see runshell.sh (for *nix based OS) and run.cmd ( for windows based OS).
2. For *nix based OS
open a terminal and goto the extracted directory and give > ```./run.sh``` and you should see Server started message, open a browser goto localhost:8080 you should see your webapplication working.
3. For windows based OS
open a command prompt and goto the extracted directory and give >```./run.cmd``` and you should see Server started message, open a browser goto localhost:8080 you should see your webapplication working.

*Note : You might get permission denied for which you need to add executable permissions to run.sh/run.cmd files.
You can run your webapplication at any port by passing it as argument to run.sh /run.cmd.
Example >```./run.sh 8045``` or >```./run.cmd 8045```*

### TODO
1. Instead of default Jetty server, war-executor should be able to read server configuaration(ex: version,  deployment method) from project's pom and deploy war application in it.
