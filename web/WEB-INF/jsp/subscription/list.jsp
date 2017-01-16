<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html pageTitle="Подписки" message="${message}">

    <H2>Список подписок</H2>
    
	<TABLE>
		<TR>
            <TH>Дата<BR>регистрации</TH>
            <TH>Ф.И.О.</TH>
			<TH>Адрес</TH>
            <TH>Наименование</TH>
            <TH>Год<BR>подписки</TH>
            <TH>Месяцы подписки</TH>
			<TH>Сумма<BR>платежа</TH>

		</TR>
        <c:url value="/subscription/list.html" var="subsListUrl"/>
		<c:forEach items="${subscriptions}" var="subscription">
        
        
			<TR onclick="submitFormById('form-${subscription.id}')" class="${classname}">
				<TD>
					${subscription.regDate}
					<FORM id="form-${subscription.id}" action="${subsListUrl}" method="post">
						<INPUT type="hidden" name="id" value="${subscription.id}">
					</FORM>
				</TD>

                <TD>${subscription.user.fullName}</TD>
                <TD>${subscription.user.address}</TD>
                <TD>${subscription.publication.title}</TD>
                <TD>${subscription.subsYear}</TD>
                <TD nowrap >${subscription.getMonthsBinaryArray()}</TD>
                <TD>${subscription.paymentSum}</TD>
			</TR>
		</c:forEach>
	</TABLE>
	    <FORM action="${subsListUrl}" method="post">
		<BUTTON type="submit">Обновить страницу</BUTTON>
	</FORM>
    
</u:html>