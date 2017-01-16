<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:choose>
	<c:when test="${not empty publication}">
		<c:set var="id" value="${publication.id}"/>
		<c:set var="issn" value="${publication.issn}"/>
		<c:set var="title" value="${publication.title}"/>
        <c:set var="monthCost" value="${publication.monthCost}"/>
		<c:set var="active" value="${publication.active}"/>
		<c:set var="pageTitle" value="Издание ${publication.issn} ${publication.title} "/>
	</c:when>
	<c:otherwise>
		<c:set var="pageTitle" value="Новое издание"/>
	</c:otherwise>
</c:choose>
<u:html pageTitle="${pageTitle}" message="${message}" validator="validator-of-edit-publication-form.js">
	<H2>${pageTitle}</H2>
	<c:url value="/publication/save.html" var="publicationSaveUrl"/>
	<FORM action="${publicationSaveUrl}" method="post" onsubmit="return validateEditPublication(this)">
		<c:if test="${not empty publication}">
			<INPUT type="hidden" name="id" value="${publication.id}">
		</c:if>
		<LABEL for="issn">Подписной индекс:</LABEL>
		<INPUT type="text" id="issn" name="issn" value="${issn}">
		<LABEL for="title">Наименование:</LABEL>
		<INPUT type="title" id="title" name="title" value="${title}">
    
		<LABEL for="monthCost">Цена подписки за месяц, руб:</LABEL>
		<INPUT type="text" id="monthCost" name="monthCost" value="${monthCost}">
     
		<LABEL for="active">Доступно для подписки:</LABEL>

        <INPUT style="display: -webkit-box; width:24px;"  type="checkbox" onchange = "saveState()" id="active"  name="active" ${active == 'true' ? 'checked' : ''}>			
		<BR>
  
		<BUTTON type="submit">Сохранить</BUTTON>
        
        <BUTTON type="reset">Сбросить</BUTTON>
        
		<c:if test="${not empty publication}">
			<BUTTON type="button" onclick="submitFormById('form-delete')" ${disabled}>Удалить</BUTTON>
		</c:if>
		
        <BUTTON type="button" onclick="history.back();" >Отменить</BUTTON>
        
	</FORM>
	<c:if test="${not empty publication}">
		<c:url value="/publication/delete.html" var="publicationDeleteUrl"/>
		<FORM action="${publicationDeleteUrl}" method="post" id="form-delete" onsubmit="return confirmation(this, 'Вы уверены, что хотите удалить издание?')">
			<INPUT type="hidden" name="id" value="${publication.id}">
		</FORM>

	</c:if>
</u:html>