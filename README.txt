1. In project directory input "mvn tomcat7:run -X"
2. Open in browser localhost:8080

If something went wrong:
1. Select project directory in cmd
2. Enter "mvn clean install"
3. Copy "target/Pharmacy-1.0-SNAPSHOT.war" to "%TOMCAT_HOME%/webapp/"
4. Run script "%TOMCAT_HOME%/bin/startup.sh"
5. For shutdown run script "%TOMCAT_HOME%/bin/shutdown.sh"