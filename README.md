ParslyAppTest

Parsly App Test executes end-to-end web tests to the app money-tracker
you can clone the repo from this website 

git clone https://github.com/Hugo-Gameiro/Parsly.git

# Pre-Requisites

Java JDK 11 or higher
Apache Maven Project (Available for download at https://maven.apache.org/download.cgi)
Installation guide available https://maven.apache.org/install.html

Install the JAVA_HOME and MAVEN_HOME environment variables

# Execution

The test application can be executed by 2 ways: 

1. IDE: selecting the Parsley/src/test/resources/regression.xml file and running it as testNG tests
2. Command line: at yourPathlocation/Parsly executing the command: mvn clean test -DsuiteXmlFile=regression.xml -Dbrowser= x
   (x = chrome | firefox | ie)

# Reports

The reports are available at yourPathlocation/Parsly/Test Reports/report_timestamp/Report.html
This paste needs to be manually deleted once it's no longer in use