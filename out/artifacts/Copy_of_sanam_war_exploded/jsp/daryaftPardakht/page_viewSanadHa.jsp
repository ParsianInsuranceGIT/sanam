<%--


  Created by IntelliJ IDEA.
  User: Arron0
  Date: Sep 6, 2011
  Time: 5:53:55 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<html>
<head>
    <title>ليست اسناد</title>                                       
</head>
<body>
<p class="heading ui-widget-header ui-corner-all ui-helper-clearfix"><span class="ui-icon ui-icon-plus"
                                                                           style="direction:rtl;float:right;"></span>
    ليست اسناد
</p>
<display:table export="true" id="sanadListTbl" uid="row" name="sanadListPaginated"
               sort="external" htmlId="sanadListTbl"
               partialList="true"
               size="sanadListPaginated.fullListSize"
               pagesize="${sanadListPaginated.objectsPerPage}"
               requestURI="" clearStatus="true" keepStatus="false"
               excludedParams="" style="width: 100%; margin: 0 auto;">
    <display:column title="رديف" style="">${row_rowNum}</display:column>
    <display:column property="shomare" title="شماره" style=""/>
    <display:column title="نوع سند" style="">${row.noeSanad == "GHABZE_RESID"? "قبض رسيد": row.noeSanad == "PARDAKHT"? "پرداخت":row.noeSanad}</display:column>
    <display:column title="وضعيت" style="">${row.vaziat == "MOVAGHAT"? "موقت": row.vaziat == "DAEM"?"دائم":row.vaziat}</display:column>
    <display:column property="createdDate" title="زمان ثبت" style=""/>
    <display:column title="عمليات" style="">
        <input type="button" onclick="window.location='/fin/viewKhateSanadHa?sanad.id=${row.id}'" value="نمايش خط سند ها"/>
    </display:column>
</display:table>

<br>
<div>
    <input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'" value="بازگشت"/>
    <input type="button" onclick="window.location='/fin/loadSanadZani'" value="ثبت سند دستي"/>
</div>


</body>
</html>