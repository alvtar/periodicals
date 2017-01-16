<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html pageTitle="Издания" message="${message}">
	<H2>Список периодических изданий</H2>
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
    <c:url value="/search/publication/form.html" var="searchPublicationUrl"/>
	<FORM action="${searchPublicationUrl}" method="post">
		<BUTTON type="submit">Поиск издания</BUTTON>
	</FORM>
</u:html>