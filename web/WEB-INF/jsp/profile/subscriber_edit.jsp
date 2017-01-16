<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

        <c:set var="id" value="${authorizedUser.id}"/>
		<c:set var="login" value="${authorizedUser.login}"/>
		<c:set var="password" value="${authorizedUser.password}"/>
        <c:set var="role" value="${authorizedUser.role}"/>
		<c:set var="fullName" value="${authorizedUser.fullName}"/>
		<c:set var="zipCode" value="${authorizedUser.zipCode}"/>
		<c:set var="address" value="${authorizedUser.address}"/>

<u:html pageTitle="Профиль подписчика «${fullName}»" message="${message}" validator="validator-of-edit-subscriber-profile-form.js">
	<H2>Редактирование профиля подписчика «${fullName}»</H2>
	<c:url value="/profile/subscriber_save.html" var="subscriberSaveUrl"/>
	<FORM action="${subscriberSaveUrl}" method="post" onsubmit="return validateEditSubscriber(this)">
        <INPUT type="hidden" name="id" value="${id}">
		<LABEL for="login">Логин:</LABEL>
		<INPUT type="text" id="login" name="login" value="${login}" disabled>
        
        <INPUT type="hidden" id="login"  name="login" value="${login}">
        <INPUT type="hidden" id="password"  name="password" value="${password}">
        
		<LABEL for="roleName">Роль:</LABEL>
		<SELECT id="roleName" disabled>
			<OPTION selected>${role.name}</OPTION>
		</SELECT>
        <INPUT type="hidden" id="role"  name="role" value="${role.id}">

        <LABEL for="fullName">Ф.И.О.:</LABEL>
		<INPUT type="text" id="fullName" name="fullName" value="${fullName}">
        
		<LABEL for="zipCode">Почтовый индекс:</LABEL>
		<INPUT type="text" id="zipCode" name="zipCode" value="${zipCode}">
        
        <LABEL for="address">Адрес:</LABEL>
		<INPUT type="text" id="address" name="address" value="${address}">
		
		<BR><BR>

		<BUTTON type="submit">Сохранить профиль</BUTTON>
		<BUTTON type="reset">Сбросить</BUTTON>
        <BUTTON type="button" onclick="history.back();" >Отменить</BUTTON>
	</FORM>
</u:html>