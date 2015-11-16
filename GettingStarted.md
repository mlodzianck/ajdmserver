# Installing dmServer #
> Please refer to http://static.springsource.com/projects/dm-server/1.0.x/getting-started/html/ch02.html

---

# Installing Eclipse tools #
> Please refer to http://static.springsource.com/projects/dm-server/1.0.x/getting-started/html/ch02s04.html

---

# Installing libraries #
> Copy (**in order as follows**)
    * `asterisk-java-0.3.1-bundle.jar`
    * `AgiDmSeverLib-1.0.1.jar`
> To directory
> > `<dm_installation_path>`\repository\libraries\usr\


---

# Deploying ajdmserver #
ajdmserver-1.0.1.jar exposes two services:
  * MappingStrategy
  * ServerManager
  * ScriptStats
  * XMLConfStarter

Mapping strategy is a service that implements  [org.asteriskjava.fastagi.MappingStrategy](http://asterisk-java.org/latest/apidocs/org/asteriskjava/fastagi/MappingStrategy.html) interface. It discovers and obtains AgiScripts. Each AgiServer has its own MappingStrategy instance.

ServerManager is a service that runs and stops AgiServers instances. It can be use to list servers and its properties.

ScriptStats service monitors FastAgi scripts.. Its features are used in WebAdmin application.

ServerStarter is responsible for creating AgiServer instances on dmServer startup. Servers are defined in XML file.
XML files with servers definitions must be located at:
  * /etc/agi-servers.xml under Unix systems
  * c:\agi-servers.xml under Windows systems
Before deploying this bundle you have to create config file to have your agi server (or servers) working. Example of such file can be found [here](ServerStarterXMLConfFile.md).

To install services deploy ajdmserver.jar in dmSpringSource Server web interface (if server is installed on localhost admin interface is located under link http://localhost:8080/admin)


---

# Delpoying WebAdmin #
WebAdmin is quite powerful WWW monitoring and administration tool for ajdmserver. Screenshots of it can be found [here](Screenshots.md)

After deploy www panel is avialable at `http://<host_name>:8080/agi/app`

To deploy this bundle simply upload AgiWebAdminApp-1.0.0.jar to dmServer via dmSpringSource Server web interface



---

# Configuring Eclipse to work with dmServer #
  * Bring up the Servers view: Window → Show View → Other → Server → Servers.
  * Bring up a "New Server" dialog: Right click in Servers view and select New → Server.
  * select "SpringSource dm Server v1.0" under the SpringSource category, give it an appropriate name, and click "Next".
    * In the "SpringSource AP installation directory" field, locate the springsource-dm-server-1.0.1.RELEASE directory
    * Select "Finish".

(Copy pasted from http://maniagnosis.blogspot.com/2008/09/developing-for-springsource-dm-server.html)

---

# First script #
  * Bring up 'New' dialog clicking File -> New -> Other
  * Select 'Bundle Project'
  * Give name to your project and click finish
  * In Servers view right click on your dmServer and select 'Add/Remove Project' and Add new created Bundle Project to server's repository
  * Next you have to add asterisk-java library from dmServer repository:
    * double click on MANIFEST.MF file
    * in bundle manifest click 'Dependencies' tab
    * in 'Import Bundle' section click 'Add...' button
    * in 'Bundle Selection' window choose org.asteriskjava 0.3.1 bundle
  * create class that implements AgiScript interface - this is your script
  * create new folder 'spring' inside META-INF folder
  * create new xml file inside spring folder, its name must match `*context.xml` pattern
  * xml file syntax example and explanation can be found [here](ScriptContext.md)
  * now bundle can be deployed to dmServer (it should be done automatically by Eclipse)
  * if you want to deploy bundle to remote server right click on eclipse project, choose 'Export' and export projext as Bundle Project

  * **IMPORTANT** each script is created in singleton model, it means that one script instance is shared between different requests
  * Unfortunatly there is no simple way to avoid singleton model (main reason is [this](http://static.springframework.org/osgi/docs/current/reference/html/known-issues.html#OSGI-237) issue) but there is workaround for this- it can be found [here](AgiScriptFactories.md)

---

# What next? #
[Using OSGi architecture in Agi scripts](agi_osgi.md)