#!/bin/sh
javac Inter.java
javac Client.java
java -Djava.security.policy=my.policy Client
