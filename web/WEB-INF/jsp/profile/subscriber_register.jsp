<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:set var="pageTitle" value="Регистрация подписчика"/>
<c:set var="id" value="${authorizedUser.id}"/>
<c:set var="login" value="${authorizedUser.login}"/>
<c:set var="password" value="${authorizedUser.password}"/>
<c:set var="roleName" value="${authorizedUser.role}"/>
<c:set var="role" value="${authorizedUser.role}"/>
<c:set var="fullName" value="${authorizedUser.fullName}"/>
<c:set var="zipCode" value="${authorizedUser.zipCode}"/>
<c:set var="address" value="${authorizedUser.address}"/>

<u:html pageTitle="${pageTitle}" message="${message}" validator="validator-of-register-subscriber-form.js">
	<H2>${pageTitle}</H2>
	<c:url value="/profile/subscriber_save.html" var="subscriberSaveUrl"/>
	<FORM action="${subscriberSaveUrl}" method="post" onsubmit="return validateRegisterSubscriber(this)">
		<LABEL for="login">Логин:</LABEL>
		<INPUT type="text" id="login" name="login">
        
		<LABEL for="password">Пароль:</LABEL>
		<INPUT type="password" id="password" name="password">
        
        <LABEL for="password2">Пароль еще раз:</LABEL>
		<INPUT type="password" id="password2" name="password2">
        
        <LABEL for="roleName">Роль:</LABEL>
        <INPUT type="text" id="roleName" name="roleName" value="${roleName.name}" readonly>
        <INPUT type="hidden" id="role" name="role" value="${role.id}">
        
		<LABEL for="fullName">Ф.И.О.:</LABEL>
		<INPUT type="text" id="fullName" name="fullName" value="${fullName}">
        
		<LABEL for="zipCode">Почтовый индекс:</LABEL>
		<INPUT type="text" id="zipCode" name="zipCode" value="${zipCode}">
        
        <LABEL for="address">Адрес:</LABEL>
		<INPUT type="text" id="address" name="address" value="${address}">
		
		<BR><BR>

		<BUTTON type="submit">Сохранить</BUTTON>
        
        <BUTTON type="reset">Сбросить</BUTTON>
        
        <BUTTON type="button" onclick="history.back();" >Отменить</BUTTON>

	</FORM>

</u:html>