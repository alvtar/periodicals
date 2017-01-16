<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html pageTitle="Подписки" message="${message}">
	<H2>История подписок</H2>
	<TABLE>
		<TR>
            <TH>Дата<BR>регистрации</TH> 
			<TH>Индекс<BR>издания</TH>
            <TH>Наименование</TH>
            <TH>Год<BR>подписки</TH>
            <TH>Месяцы подписки</TH>
			<TH>Сумма<BR>платежа, руб.</TH>
		</TR>
        <c:url value="/publication/subscriber_list.html" var="publicationSubscriberListUrl"/>
		<c:forEach items="${subscriptions}" var="subscription">
        
			<TR onclick="submitFormById('form-${subscription.id}')" class="${classname}">
				<TD  style="text-align:center;">
					${subscription.regDate}
					<FORM id="form-${subscription.id}" action="${subsListUrl}" method="post">
						<INPUT type="hidden" name="id" value="${subscription.id}">
					</FORM>
				</TD>

                <TD style="text-align:right;" >${subscription.publication.issn}</TD>
                <TD>${subscription.publication.title}</TD>
                <TD style="text-align:center;" >${subscription.subsYear}</TD>
                <TD style="text-align:right;" nowrap >${subscription.getMonthsBinaryArray()}</TD>
                
                <TD style="text-align:right;" >${subscription.paymentSum}</TD>
			</TR>
		</c:forEach>
	</TABLE>
	<FORM action="${publicationSubscriberListUrl}" method="post">
		<BUTTON type="submit">Назад к списку изданий</BUTTON>
	</FORM>
</u:html>