<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<title><tiles:insertAttribute name="title" /></title>
<header id="myheader">
	<tiles:insertAttribute name="header" />

</header>
<section id="site-content" style="margin-right: 5%; margin-left: 5%">
	<tiles:insertAttribute name="body" />

</section>
<footer id="myfooter">
	<tiles:insertAttribute name="footer" />

</footer>