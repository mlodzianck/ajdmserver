# Asterisk Java Fast Agi OSGi dynamic module #

ajdmserver is set of OSGi bundles designed to work inside [SpringSource dmServer ver. 1.0.2.RELEASE](http://www.springsource.org/dmserver). It is built on top [asterisk-java](http://asterisk-java.org/) framework and in fact it only adapts few elements of that library to work with dmServer. With ajdmserver agi scripts can be deployed as OSGi bundles.
Creating a script is similar to the way shown in this [tutorial](http://asterisk-java.org/development/tutorial.html). It differs only in two meta-files. More you can read in short [tutorial](GettingStarted.md).


Main benfits of using ajdmserver:
  * hot deploy of scripts without stopping or restarting server
  * www administration panel ((server starting/stoping, deploying scripts, listing servers, statistics)
  * no changes in way of creating scripts- just implement AgiScript interface
  * access to OSGi services inside your applications
  * multiple agi servers in one application
  * easy access to all advantages of Spring framework (IoC, JDBC Spring Abstraction Layer, J2EE resources access and much more...)

In plans I've got:
  * integrate Asterisk Manager Intrface