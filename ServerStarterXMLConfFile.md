
```
<?xml version="1.0" encoding="UTF-8"?>
<servers>
        <server>
                <serverPort>2233</serverPort>
                <serverDomain>domain2</serverDomain>
                <poolSize>10</poolSize>
                <maxPoolSize>100</maxPoolSize>
        </server>


         <server>
                <serverPort>2234</serverPort>
                <serverDomain>domain1</serverDomain>
                <poolSize>10</poolSize>
                <maxPoolSize>100</maxPoolSize>
        </server>


         <server>
                <serverPort>2235</serverPort>
                <serverDomain>domain3</serverDomain>
                <poolSize>10</poolSize>
                <maxPoolSize>100</maxPoolSize>
        </server>


</servers>
```
There are three servers defined in this file
  1. domain2 server listening on port 2233
  1. domain1 server listening on port 2234
  1. domain3 server listening on port 2235

serverPort and serverDomain are mandatory fields
poolSize and maxPoolSize are optional


poolSize and maxPoolSize are parameters of DefaultAgiServer.
Explanation of theese parameters can be found [here](http://asterisk-java.org/development/apidocs/org/asteriskjava/fastagi/AbstractAgiServer.html#setPoolSize(int)) and [here](http://asterisk-java.org/development/apidocs/org/asteriskjava/fastagi/AbstractAgiServer.html#setMaximumPoolSize(int))