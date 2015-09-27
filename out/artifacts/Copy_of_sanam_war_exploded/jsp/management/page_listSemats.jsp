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
    <title>مدیریت سمت ها</title>
</head>
<body>
<s:actionmessage/>

<display:table export="false" id="tblListSemats" uid="semat" htmlId="tblListSemats"
               name="sessionScope.listSemats.list" partialList="true" clearStatus="true" keepStatus="false"
               style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
               size="${sessionScope.listSemats.fullListSize}"
               pagesize="${sessionScope.listSemats.objectsPerPage}">
    <display:column property="roleName" title="نام" style="background:none;background-color:${semat.roleColor};" class="td"></display:column>

    <display:column media="html"><input type="button" value="ویرایش" style="width: 50px"
                                        onclick="window.location='/sabtSemat?role.id=${semat.id}'"/></display:column>
    <display:column media="html">
         <input type="button" value="حذف" style="width:50px"
               onclick="
               confirmMessage('آیا از حذف سمت (${semat.roleName}) مطمئن هستید؟',
               '', function(){window.location='/deleteSemat?role.id=${semat.id}';})"/>
    </display:column>
</display:table>
<br>
<br>
<div>
    <input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'" value="بازگشت"/>
    <input type="button" onclick="window.location='/sabtSemat'" value="افزودن سمت">
</div>
</body>
</html>