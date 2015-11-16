In 90% cases AgiScripts are stateless and one instance can be shared between different requests. But if there is need for unique AgiScript instance per each agi requests some extra work must be done.

First of all you have to import AgiDmSeverLib to your project from dmServer repository.
  * double click on MANIFEST.MF file
  * in bundle manifest click 'Dependencies' tab
  * in 'Import Bundle' section click 'Add...' button
  * in 'Bundle Selection' window choose AgiDmSeverLib
Next you have to create class that implements IAgiScriptFactory interface. getScript() method of this class must return your AgiScript you intend to be non-sigleton script.
At the end edit script context xml file like in beow example.
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:osgi="http://www.springframework.org/schema/osgi"
	xsi:schemaLocation="http://www.springframework.org/schema/beans   
       http://www.springframework.org/schema/beans/spring-beans.xsd  
       http://www.springframework.org/schema/osgi  
       http://www.springframework.org/schema/osgi/spring-osgi.xsd">


	<osgi:service id="MyScriptFactory" interface="org.dmagiserver.IAgiScriptFactory">
		<osgi:service-properties>
			<entry key="agiRequestPattern" value="HelloWorld" />
			<entry key="agiDomain" value="domain1" />
		</osgi:service-properties>
		<bean  class="my.scripts.MyScriptFactory" />
	</osgi:service>
	
</beans>
```
As you see in this file exported service is IAgiScriptFActory instead AgiScript.
And that's all. Now each agi request obtains unique script instance