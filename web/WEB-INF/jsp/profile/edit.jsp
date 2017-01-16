<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html pageTitle="Смена пароля пользователя «${authorizedUser.login}»" message="${message}" validator="validator-of-change-password-form.js">
	<H2>Смена пароля пользователя «${authorizedUser.login}»</H2>
	<c:url value="/profile/save.html" var="profileSaveUrl"/>
	<FORM action="${profileSaveUrl}" method="post" onsubmit="return validateChangePassword(this)">
		<LABEL for="login">Логин:</LABEL>
		<INPUT type="text" id="login" value="${authorizedUser.login}" disabled>
		<LABEL for="role">Роль:</LABEL>
		<SELECT id="role" disabled>
			<OPTION selected>${authorizedUser.role.name}</OPTION>
		</SELECT>
		<LABEL for="old-password">Старый пароль:</LABEL>
		<INPUT type="password" id="old-password" name="old-password">
		<LABEL for="new-password-1">Новый пароль:</LABEL>    
		<INPUT type="password" id="new-password-1" name="new-password">
		<LABEL for="new-password-2">Новый пароль ещё раз:</LABEL>
		<INPUT type="password" id="new-password-2">
        <BR><BR>
		<BUTTON type="submit">Сменить пароль</BUTTON>
		<BUTTON type="reset">Сбросить</BUTTON>
        <BUTTON type="button" onclick="history.back();" >Отменить</BUTTON>
	</FORM>
</u:html>