<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html pageTitle="Вход в систему" message="${message}">
    
	<H2>Вход в систему</H2>
    <LABEL style="font-size:9px; float:right; color:red;" >(Administrator: admin, admin; Subscriber: user1, 111)</LABEL>
	<c:url value="/login.html" var="loginUrl"/>
	<FORM action="${loginUrl}" method="post">
    
		<LABEL for="login">Логин:</LABEL>
		<INPUT type="text" id="login" name="login" value="${param.login}">
		<LABEL for="password">Пароль:</LABEL>
		<INPUT type="password" id="password" name="password">
		<BUTTON type="submit">Войти</BUTTON>
		<BUTTON type="reset">Очистить</BUTTON>
        <BUTTON type="button" style="float:right; margin-left:auto; margin-right:0;" onclick="submitFormById('form-register')" ${disabled}>Регистрация подписчика</button> 
	</FORM>

    <c:url value="/profile/subscriber_register.html" var="registerUrl"/>
    <FORM  action="${registerUrl}" id="form-register" method="post">
        
    </form>
 
</u:html>