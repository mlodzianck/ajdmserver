<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%-- 
--%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%-- 
--%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%-- 
--%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%-- 
--%>
Asterisk Java Fast Agi OSGi dynamic module alpha ajdmserver is set of
OSGi bundles designed to work inside SpringSource dmServer ver.
1.0.2.RELEASE. It is built on top asterisk-java framework and in fact it
only adapts few elements of that library to work with dmServer. With
ajdmserver agi scripts can be deployed as OSGi bundles. Creating a
script is as easy as creating plain script. It differs only in two
meta-files.
<br>
<br>
Main benfits of using ajdmserver:
<ul>
	<li> hot deploy of scripts without stopping or restarting server 
	<li> no
	changes in way of creating scripts- just implement AgiScript interface
	<li> access to OSGi services inside your applications 
	<li> multiple agi
	servers in one application 
	<li> easy access to all advantages of Spring
	framework (IoC, JDBC Spring Abstraction Layer, J2EE resources access
	and much more...)
</ul>