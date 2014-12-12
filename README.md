
War Executor

	What is it?
	-----------

	war-executor is a maven plugin using which deploying war and bringing up web server(Jetty for now) becomes as simple as running a script. It helps in distributing the war and packaged embedded server in zip format 

  Documentation
  -------------

  //TODO

  System Requirements
  -------------------

  JDK:
    1.6 or above (this is to execute Maven - it still allows you to build against 1.3
    and prior JDK's).

  Maven:
    Maven 3.2.3 or above if you don't have maven get it from http://maven.apache.org/download.cgi
	
	Steps to build war-executor
	---------------------------
	War executor consists of two modules embedded server which creates server, deploy the war 
	and runs it and
	pluing which creates the requried jar files and adds them to M2 repository. 
	this plugin can be installed to your local M2 repository by giving the command 
	"mvn clean install"

	Once this is added to local repository this plugin can be used in maven project by specifiying 			plugin in pom.xml as
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

	Description of plugin:
	----------------------
	<warFile> => specifies the path of war file to be added to .zip
	<defaultPort> => #not needed actually
	<goal> and <phase> specifices the goal and phase respectively

	Description of plugin pom:	
	--------------------------
	//Neeeded?

	Usage:
	------
	Zip file will be copied to the directory specified in the pom.xml of plugin generally it's copied
	to target directory of base direcotry which can be distributed easily

	For *nix based systems unzip the file and run runshell.sh [port number is optional argument. 	By 		default server runs at 8080 if no arugument is passed or passed argument overrides 8080]

	For windows based systems unizip the file and run run.cmd [port number is optional argument. By 	 	default server runs at 8080 if no argument is passed or passed argument overrides 8080]

	Note: In case of permission denied error You might need to add execute permissions for the 	script to run. Ex: *nix chmod +x runshell.sh
  Licensing
  ---------
 	//TODO
 
 TODO
	-----
 Embedded server should read the server version, configuaration from project pom and should be 
 able to deploy war. 
 Control should be given on packaging jars by WarServerAssemblyMojo.
