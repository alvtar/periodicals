<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="headerMain">
 
 <div id="header">
	<H1>Система подписки на<BR>периодические издания</H1>

	<c:if test="${not empty authorizedUser.login}">

		<UL class="right">
					<LI class="item">
				<c:url value="/img/arrow.gif" var="arrowUrl"/>
					<A href="#" class="drop-button">${authorizedUser.login} <IMG class="arrow" src="${arrowUrl}"></A>
						<OL class="drop">
                            <c:if test="${authorizedUser.role eq 'SUBSCRIBER'}">
                                <c:url value="/profile/subscriber_edit.html" var="subscriberProfileEditUrl"/>
                                <LI><A href="${subscriberProfileEditUrl}">профиль</A></LI>
                            </c:if>
							<c:url value="/profile/edit.html" var="profileEditUrl"/>
							<LI><A href="${profileEditUrl}">смена пароля</A></LI>
							<c:url value="/logout.html" var="logoutUrl"/>
							<LI><A href="${logoutUrl}">выход</A></LI>
						</OL>
			</LI>
			<c:forEach items="${menu}" var="item">
				<c:url value="${item.url}" var="itemUrl"/>
				<LI class="item"><A href="${itemUrl}">${item.name}</A></LI>
			</c:forEach>

		</UL>
	</c:if>
</div>
 
</div>
