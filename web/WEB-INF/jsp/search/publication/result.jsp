<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html pageTitle="Найденные издания" message="${message}">
	<H2>Результат поиска изданий</H2>
    <c:choose>
	<c:when test="${not empty publications}">
	<TABLE>
		<TR>
			<TH>Индекс<BR>издания</TH>
			<TH>Наименование</TH>
            <TH>Цена подписки<BR>за месяц, руб</TH>
			<TH>Доступно<BR>для подписки</TH>
		</TR>
        <c:url value="/subscription/add.html" var="SubscriptionAddUrl"/>
		<c:forEach items="${publications}" var="publication">

			<TR onclick="submitFormById('form-${publication.id}')" class="${classname}">
				<TD style="text-align:right;">
					${publication.issn}
					<FORM id="form-${publication.id}" action="${SubscriptionAddUrl}" method="post">
						<INPUT type="hidden" name="id" value="${publication.id}">
					</FORM>
				</TD>
				
                <TD>${publication.title}</TD>
                <TD style="text-align:right;" >${publication.monthCost}</TD>

				<TD> 
					<INPUT type="checkbox" id="active" name="active" value="${publication.active}" ${publication.active == 'true' ? 'checked' : ''} disabled>				
				</TD>

			</TR>
		</c:forEach>
	</TABLE>
    <c:url value="/publication/subscriber_list.html" var="publicationSubscriberListUrl"/>
	<FORM action="${publicationSubscriberListUrl}" method="post">
		<BUTTON type="submit">Назад к полному списку</BUTTON>
	</FORM>
    </c:when>
		<c:otherwise>
			<P>По запросу ничего не найдено</P>
		</c:otherwise>
	</c:choose>
</u:html>