<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:choose>
	<c:when test="${not empty publication}">
        <c:set var="userId" value="${user.id}"/>
		<c:set var="id" value="${publication.id}"/>
		<c:set var="issn" value="${publication.issn}"/>
		<c:set var="title" value="${publication.title}"/>
        <c:set var="monthCost" value="${publication.monthCost}"/>
		<c:set var="active" value="${publication.active}"/>
		<c:set var="paymentSum" value="0"/>
		<c:set var="pageTitle" value="Издание ${publication.issn} ${publication.title} "/>
	</c:when>
	<c:otherwise>
		<c:set var="pageTitle" value="Подписка" />
	</c:otherwise>
</c:choose>
<u:html pageTitle="${pageTitle}" message="${message}" validator="validator-of-add-subscription-form.js">
	<H2>${pageTitle}</H2>
	<c:url value="/subscription/save.html" var="subscriptionSaveUrl"/>
	<FORM id="saveSubscriptionForm" action="${subscriptionSaveUrl}" method="post" onsubmit="return true">
		<c:if test="${not empty publication}">
			<INPUT type="hidden" name="id" value="${publication.id}">
		</c:if>
		<LABEL for="issn">Подписной индекс:</LABEL>
		<INPUT type="text" id="issn" name="issn" value="${issn}" disabled>
        
		<LABEL for="title">Наименование:</LABEL>
		<INPUT type="title" id="title" name="title" value="${title}" disabled>
    
		<LABEL for="monthCost">Цена подписки за месяц, руб:</LABEL>
		<INPUT type="text" id="monthCost" name="monthCost" value="${monthCost}" disabled>
		<INPUT type="hidden" id="monthCost" name="monthCost" value="${monthCost}">

        <LABEL for="year">Год подписки:</LABEL>
		<SELECT id="subsYear" name="subsYear" onChange="switchMonths(0)">
			<c:forEach items="${years}" var="subsYear">
				<OPTION value="${subsYear}" ${selected}>${subsYear.toString()}</OPTION>
			</c:forEach>
		</SELECT>
	 
	 
        <script type="text/javascript">
            function switchMonths(reset) {
				var items = document.getElementsByTagName("input"), len, n;
				for (n = 0, len = items.length; n < len; n += 1) {

                if (items.item(n).type === "checkbox" & checkRegexp(items.item(n).id,"month.*")) {
                        items.item(n).checked = false;
                           
                }
            }
				if (reset==0) {
					var i = document.getElementById("subsYear").options.selectedIndex;
				} else {
					i=0;
				}
				
				if (i==1) {
					month1.disabled=0;
					month2.disabled=0;
					month3.disabled=0;
					month4.disabled=0;
					month5.disabled=0;
					month6.disabled=0;
					month7.disabled=0;
					month8.disabled=0;
					month9.disabled=0;
					month10.disabled=0;
					month11.disabled=0;
					month12.disabled=0;
				} else {
					month1.disabled='${month1}';
					month2.disabled='${month2}';
					month3.disabled='${month3}';
					month4.disabled='${month4}';
					month5.disabled='${month5}';
					month6.disabled='${month6}';
					month7.disabled='${month7}';
					month8.disabled='${month8}';
					month9.disabled='${month9}';
					month10.disabled='${month10}';
					month11.disabled='${month11}';
					month12.disabled='${month12}';
				}
			}
        </script>
	
    
        <div class="paySumClass">
            <label for="paySumField">Стоимость подписки, руб.</label>
            <input style="width:170px; font-size:24px; font-weight:bold;" type="text" id="paySumField" value="${paymentSum}" disabled />
            <input type="hidden" id="paySumField" value="${paymentSum}" />
            <LABEL for="active">Доступно для подписки:</LABEL>
            <INPUT style="display: -webkit-box; width:24px;"  type="checkbox" id="active"  name="active" value="1" ${active == 'true' ? 'checked' : ''} disabled>
        </div>

        <LABEL style="margin-top:0;">Месяцы для подписки:</LABEL>
        <pre><LABEL style="margin-top:0;"> 1    2    3    4    5    6    7    8    9   10   11  12      Выбрать все</LABEL></pre>
    
        <input style="width:19px;" id="month1" type="checkbox" name="month1" onclick="calcCost()" ${month1 == 'false' ? 'disabled' : ''} />
        <input style="width:19px;" id="month2" type="checkbox" name="month2" onclick="calcCost()" ${month2 == 'false' ? 'disabled' : ''}/>
        <input style="width:19px;" id="month3" type="checkbox" name="month3" onclick="calcCost()" ${month3 == 'false' ? 'disabled' : ''}/>
        <input style="width:19px;" id="month4" type="checkbox" name="month4" onclick="calcCost()" ${month4 == 'false' ? 'disabled' : ''}/>
        <input style="width:19px;" id="month5" type="checkbox" name="month5" onclick="calcCost()" ${month5 == 'false' ? 'disabled' : ''}/>
        <input style="width:19px;" id="month6" type="checkbox" name="month6" onclick="calcCost()" ${month6 == 'false' ? 'disabled' : ''}/>
        <input style="width:19px;" id="month7" type="checkbox" name="month7" onclick="calcCost()" ${month7 == 'false' ? 'disabled' : ''}/>
        <input style="width:19px;" id="month8" type="checkbox" name="month8" onclick="calcCost()" ${month8 == 'false' ? 'disabled' : ''}/>
        <input style="width:19px;" id="month9" type="checkbox" name="month9" onclick="calcCost()" ${month9 == 'false' ? 'disabled' : ''}/>
        <input style="width:19px;" id="month10" type="checkbox" name="month10" onclick="calcCost()" ${month10 == 'false' ? 'disabled' : ''}/>
        <input style="width:19px;" id="month11" type="checkbox" name="month11" onclick="calcCost()" ${month11 == 'false' ? 'disabled' : ''}/>
        <input style="width:19px;" id="month12" type="checkbox" name="month12" onclick="calcCost()" ${month12 == 'false' ? 'disabled' : ''}/>
        <input style="width:105px;" id="month" type="checkbox" name="month"  onclick="checkAll(this)" /> 
        <BR>

        
        <script type="text/javascript">
            function calcCost() {
                var items = this.document.getElementsByTagName("input"), len, n;
                var checkedMonths=0;
                for (n = 0, len = items.length; n < len; n += 1) {
                    if (items.item(n).type == "checkbox" & checkRegexp(items.item(n).id,"month.*") & items.item(n).checked == true ) {
                        checkedMonths++;          
                    }
                }
                paymentSum=checkedMonths*monthCost.value;
                paySumField.value=paymentSum;
            }
        </script>

        
        <script type="text/javascript">
            function checkAll(obj) {
            'use strict';
            // Получаем NodeList дочерних элементов input формы: 
            var items = obj.form.getElementsByTagName("input"), 
                len, i;
            // Здесь, увы цикл по элементам формы:
            for (i = 0, len = items.length; i < len; i += 1) {
               // Если текущий элемент является чекбоксом...
                if (items.item(i).type === "checkbox" & checkRegexp(items.item(i).id,"month.")  & items.item(i).disabled!="1") {
                // Дальше логика простая: если checkbox "Выбрать всё" - отмечен            
                    if (obj.checked) {
                        // Отмечаем все чекбоксы...
                        items.item(i).checked = true;
                    } else {
                        // Иначе снимаем отметки со всех чекбоксов:
                        items.item(i).checked = false;
                    }       
                }
            }
            calcCost();
        }
        </script>
  
  
        <c:url value="/subscription/save.html" var="subscriptionSaveUrl"/>
		
        <script type="text/javascript">
            function saveSubscription() {
            if ((validateAddSubscription(this)) && (saveConfirmation(this, 'Вы уверены, что хотите подписаться на издание "' 
                +'${title}'+'"? Стоимость подписки будет списана с Вашего счета.'))) {   
                    return true;
            } else {
                return false;
            }
        }
        </script>
                
        <BUTTON type="button" onclick="saveSubscription()">Подписаться</BUTTON>
        
        <BUTTON type="reset" onclick="switchMonths(1);">Сбросить</BUTTON>
        
        <BUTTON type="button" onclick="history.back();" >Отменить</BUTTON>

     </FORM>
    
</u:html>