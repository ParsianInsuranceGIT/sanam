<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
%>
<head>
     <title>لیست چک های دریافتی</title>
</head>
<body>
<table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <thead>
        <tr>
            <th>
حساب بانکی
            </th>
            <th>
                شماره چک
            </th>
            <th>
                مبلغ
            </th>
            <th>
                در وجه
            </th>
            <th>
                تاریخ سررسید
            </th>
            <th>
                وضعیت چک
            </th>

        </tr>
    </thead>
    <tbody>
        <c:if test="${daryafthayeCheck==null}">
            <tr>
                <td colspan="6">هیچ چک دریافتی ای در سیستم وجود ندارد</td>
            </tr>
        </c:if>
        <c:if test="${daryafthayeCheck!=null}">
            <c:forEach var="check" items="${daryafthayeCheck}">
                <tr>
                    <td>
                        ${check.hesabBanki}
                    </td>
                    <td>
                        ${check.credebit.shenaseCredebit}
                    </td>
                    <td>
                        ${check.credebit.amount}
                    </td>
                    <td>
                        ${check.darVajh}
                    </td>
                    <td>
                        ${check.tarikhSarresid}
                    </td>
                    <td>
                        <c:if test='${check.status == "NAZD_SANDOGH"}'>
نزد صندوق
                        </c:if>
                        <c:if test='${check.status == "MOSTARAD"}'>
 مستردد
                        </c:if>
                        <c:if test='${check.status == "VAGOZAR_SHODE"}'>
واگذار شده&nbsp;${check.hesabeVarizi}
                        </c:if>
                        <c:if test='${check.status == "VOSUL"}'>
                            وصول شده
                        </c:if>
                    </td>
                    <td>

                        <select id="hesab_varizi${check.id}">
                            <c:forEach var="hesab" items="${hesabha}">
                                <option value="${hesab.shomareHesab}">${hesab.shomareHesab}</option>
                            </c:forEach>
                        </select>
                        <input type="button" onclick="varizBeHesab(${check.id})" value="واریز به حساب">
                    </td>
                    <td>
                        <input type="button" onclick="vosoul(${check.id})" value="وصول">
                    </td>
                    <td>
                        <input type="button" onclick="bargasht(${check.id})" value="برگشت">
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </tbody>
</table>

<input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'" value="بازگشت" class="ui-state-default  ui-corner-all ui-helper-clearfix">
<form id="varizBeHesabDaryaftCheck" action="/fin/varizBeHesab.action" method="post" enctype="application/x-www-form-urlencoded" accept-charset="UTF-8">
    <input type="hidden" name="daryafteCheck.id" id="daryaft_check_id"/>
    <input type="hidden" name="daryafteCheck.hesabeVarizi" id="daryaft_check_hesab_varizi">
</form>
<form id="vosoulDaryaftCheck" action="/fin/vosouleCheck.action" method="post" enctype="application/x-www-form-urlencoded" accept-charset="UTF-8">
    <input type="hidden" name="daryafteCheck.id" id="daryaft_check_id_for_vosoul"/>
</form>
<form id="bargashtDaryaftCheck" action="/fin/bargashteCheck.action" method="post" enctype="application/x-www-form-urlencoded" accept-charset="UTF-8">
    <input type="hidden" name="daryafteCheck.id" id="daryaft_check_id_for_bargasht"/>
</form>
<script type="text/javascript">
    function varizBeHesab(id){
        $("#daryaft_check_id").val(id);
        $("#daryaft_check_hesab_varizi").val($("#hesab_varizi"+id).val())
        $("#varizBeHesabDaryaftCheck").submit();
    }
    function vosoul(id){
        $("#daryaft_check_id_for_vosoul").val(id);
        $("#vosoulDaryaftCheck").submit();
    }
    function bargasht(id){
        $("#daryaft_check_id_for_bargasht").val(id);
        $("#bargashtDaryaftCheck").submit();
    }
</script>
</body>