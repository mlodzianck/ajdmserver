
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:osgi="http://www.springframework.org/schema/osgi"
	xsi:schemaLocation="http://www.springframework.org/schema/beans   
       http://www.springframework.org/schema/beans/spring-beans.xsd  
       http://www.springframework.org/schema/osgi  
       http://www.springframework.org/schema/osgi/spring-osgi.xsd">


	<osgi:service id="ExampleScript" interface="org.asteriskjava.fastagi.AgiScript">
		<osgi:service-properties>
			<entry key="agiRequestPattern" value="HelloWorld" />
			<entry key="agiDomain" value="domain1" />
		</osgi:service-properties>
		<bean class="my.package.Script" />
	</osgi:service>
</beans>
```

Most important (from ajdmserver point of view) is part enclosed in `<osgi:service></osgi:service>` tags. It is OSGi service and spring anonymous bean definition wich provides osgi service.

Exported service must have two properties:
  * agiRequestPattern- it is regular expression pattern used by OSGiMappingStrategy to match [script name](http://asterisk-java.org/latest/apidocs/org/asteriskjava/fastagi/AgiRequest.html#getScript()) from agi request with particular script
  * agiDomain- is a domain of agi server (domain for servers are defined in [agi-servers.xml file](ServerStarterXMLConfFile.md))



Comprehensive description of spring dm bundles context files can be found [here](http://static.springframework.org/osgi/docs/1.2.0/reference/html/service-registry.html)