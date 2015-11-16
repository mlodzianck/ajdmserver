OSGi fatures can be accessed using BundleContext object. If your script implements BundleContextAware interface ajdmserver sets (invoking setBundleContext method) bundleContext property of your script. Having bundleContext object AgiScript can access services using snippet:
```
ServiceReference ref= bundleContext.getServiceReference("your class interface name");
if (ref!=null) {
                        System.err.println("Reference found");
                        ImportedServiceInterface importedService=(ImportedServiceInterface )bundleContext.getService(ref);
                        
                } else {
                        System.err.println("Reference not found");
                }
```



Alternatively you can import service reference in context xml file.
Lets's say that one of your bundles exports service under interface IMyExternalService.
Your script's context xml file may look like:
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:osgi="http://www.springframework.org/schema/osgi"
        xsi:schemaLocation="http://www.springframework.org/schema/beans  
       http://www.springframework.org/schema/beans/spring-beans.xsd  
       http://www.springframework.org/schema/osgi  
       http://www.springframework.org/schema/osgi/spring-osgi.xsd">


        <osgi:service id="TestScript" interface="org.asteriskjava.fastagi.AgiScript">
                <osgi:service-properties>
                        <entry key="agiRequestPattern" value="InboundHandler" />
                        <entry key="agiDomain" value="domain1" />
                </osgi:service-properties>
                <bean class="mt.TestScript" />
                <property name="myExternalServiceReference">
                                <osgi:reference inteterface="IMyExternalService"/>
                        </property>


        </osgi:service>
       
</beans>


```
BundleContext and ServiceReference interfaces are part of org.osgi.framework package, so it must be imported into bundle.
BundleContextAware is member of  org.springframework.osgi.context package, it also must be included