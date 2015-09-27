<%--
  Created by IntelliJ IDEA.
  User: ramtinb
  Date: 4/10/12
  Time: 10:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>مدیریت بانک ها</title>
</head>
<body>
<s:actionmessage/>

<display:table export="false" id="tblListBanks" uid="bank" htmlId="tblListBanks"
               name="sessionScope.listBanks.list" partialList="true" clearStatus="true" keepStatus="false"
               style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
               size="${sessionScope.listBanks.fullListSize}"
               pagesize="${sessionScope.listBanks.objectsPerPage}">
    <display:column property="name" title="نام"></display:column>

    <display:column media="html"><input type="button" value="ویرایش" style="width: 50px"
                                        onclick="window.location='/sabtBank?bank.id=${bank.id}'"/></display:column>
    <display:column media="html">
        <input type="button" value="حذف" style="width:50px"
               onclick="
               confirmMessage('آیا از حذف (${bank.name}) مطمئن هستید؟',
               '', function(){window.location='/deleteBank?bank.id=${bank.id}';})"/>
    </display:column>
</display:table>
<br>
<br>
<div>
    <input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'" value="بازگشت"/>
    <input type="button" onclick="window.location='/sabtBank'" value="افزودن بانک">
</div>
</body>

</html>