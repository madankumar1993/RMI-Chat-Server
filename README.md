RMI-based-Chat-Room
===================

A chat room built using Remote Method Invocation (RMI) of Java


##### RMI
=========
RMI is a java methodology for Remote Procedure Calls (RPCs). The framework provides a mechanism to access remote objects by clients as though they were local to the clients' service.

RMI Documentation (http://docs.oracle.com/javase/tutorial/rmi/)

In a nut-shell, <br>
1. RMIs make the server define a service interface <br>
2. The client recieves a reference to this service (<i>stub</i>) <br>
3. Client implements the interface<br>
 

TODO - mention steps to start server and chat clients. 


Server start: 
<code> java -Djava.security.policy=my.policy Chat_server </code>
