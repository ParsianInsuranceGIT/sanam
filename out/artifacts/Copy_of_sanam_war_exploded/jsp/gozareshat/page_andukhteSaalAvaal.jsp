<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 10/5/11
  Time: 5:21 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="st" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head><title>گزارش اندوخته سال اول قرارداد های سیستم</title>
    <style>
        #main {
            width: 1200px;
        }
    </style>
</head>
<body>

<form action="/makeGozaresheAndukhteSaalAvaal" id="form_makeGozaresheAndukhteSaalAvaal" method="post">

    <table class="inputList" cellspacing="1" width="80%">
        <tr>
            <td>
                <label>قرارداد</label>
                <st:select list="gharardadList" listValue="nameSherkat" listKey="id" name="gharardadId" theme="simple"/>
            </td>
            <td style="width: 70%"></td>
        </tr>
        <tr>
            <td></td>
        </tr>
        <tr>
            <td>
                <input type="button" onclick="window.location='/loginUser'" value="بازگشت"/>
                <input type="submit" value="گزارش"/>
            </td>
            <td style="width: 70%"></td>
        </tr>

        <tr>
            <td colspan="2">
                <div style="overflow: auto;width: 100%">
                    <c:if test="${sessionScope.andukhteSaalAvaalList != null && fn:length(andukhteSaalAvaalList) >0 }">
                        <display:table export="true" id="andukhteSaalAvaalList" uid="rpViewResult"
                                       htmlId="andukhteSaalAvaalList"
                                       name="sessionScope.andukhteSaalAvaalList" clearStatus="true"
                                       requestURI="makeGozaresheAndukhteSaalAvaal" keepStatus="false">
                            <display:column title="ردیف">${rpViewResult_rowNum}</display:column>
                            <display:column property="fullNameBimeGozar" title="نام و نام خانوادگي بيمه گذار"></display:column>
                            <display:column property="shomareBimename" title="شماره بيمه نامه"></display:column>
                            <display:column property="hagheBimePayePardakhtiSaalAvaal" title="حق بيمه پايه پرداختي سال اول"></display:column>
                            <display:column property="koleHagheBimePardakhtiSaalAvaal" title="کل حق بيمه پرداختي سال اول"></display:column>
                            <display:column property="andukhteTeoricPayanSaalAvaal" title="اندوخته تئوريک پايان سال اول"></display:column>
                            <display:column property="andukhteAlalHesabPayanSaalAvaal" title="اندوخته علي الحساب پايان سال اول"></display:column>
                            <display:column property="andukhteGhatiPayanSaalAvaal" title="اندوخته قطعي پايان سال اول"></display:column>




                            <st:url id="urlPrint" action="printZakhireRiazi"/>
                            <input type="button" onclick="window.open('${urlPrint}')" value="پرینت">
                        </display:table>
                    </c:if>
                    <c:if test="${sessionScope.andukhteSaalAvaalList != null && fn:length(andukhteSaalAvaalList) ==0 }">
                        اطلاعاتی یافت نشد
                    </c:if>
                </div>
            </td>
        </tr>
    </table>
</form>
</body>
</html>