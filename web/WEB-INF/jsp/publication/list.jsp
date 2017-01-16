<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html pageTitle="Издания" message="${message}">
	<H2>Список периодических изданий</H2>
	<TABLE>
		<TR>
			<TH>Индекс</TH>
			<TH>Наименование</TH>
            <TH>Цена подписки за месяц, руб</TH>
			<TH>Доступно для подписки</TH>
		</TR>
        <c:url value="/publication/edit.html" var="publicationEditUrl"/>
		<c:forEach items="${publications}" var="publication">

			<TR onclick="submitFormById('form-${publication.id}')" class="${classname}">
				<TD>
					${publication.issn}
					<FORM id="form-${publication.id}" action="${publicationEditUrl}" method="post">
						<INPUT type="hidden" name="id" value="${publication.id}">
					</FORM>
				</TD>
				
                <TD>${publication.title}</TD>
                <TD>${publication.monthCost}</TD>
				<TD> 
					<INPUT type="checkbox" name="active" value="${publication.active}" ${publication.active == 'true' ? 'checked' : ''} disabled>				
				</TD>
			</TR>
		</c:forEach>
	</TABLE>
	<FORM action="${publicationEditUrl}" method="post">
		<BUTTON type="submit">Добавить издание</BUTTON>
	</FORM>
</u:html>