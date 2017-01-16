<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html pageTitle="Поиск изданий">
	<H2>Поиск изданий</H2>
	<c:url value="/search/publication/result.html" var="searchPublicationResultUrl"/>
	<FORM action="${searchPublicationResultUrl}" method="post">
		<LABEL for="issn">Поиск по подписному индексу:</LABEL>
		<INPUT type="text" id="issn" name="issn" class="search">
		<BUTTON type="submit" class="search">Найти</BUTTON>
	</FORM>
	<HR>
	<FORM action="${searchPublicationResultUrl}" method="post">
		<LABEL for="title">Поиск по наименованию (или его части):</LABEL>
		<INPUT type="text" id="title" name="title" class="search">
		<BUTTON type="submit" class="search">Найти</BUTTON>
        <BR><BR>
	</FORM>
</u:html>