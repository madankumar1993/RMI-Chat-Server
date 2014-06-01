#!/bin/sh
rm *.class
javac Inter.java
javac Chat_server.java
rmic Chat_server
rmiregistry &
java -Djava.security.policy=my.policy Chat_server
