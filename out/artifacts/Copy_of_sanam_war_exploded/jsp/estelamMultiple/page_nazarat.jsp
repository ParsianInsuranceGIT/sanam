<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 5/15/11
  Time: 4:33 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <script type="text/javascript">
        $(function() {
            $("input, textarea").each(function() {
                $(this).addClass("ui-state-default  ui-corner-all ui-helper-clearfix");
                $(this).change();
            });
        });
    </script>
    <title>مشاهده نظرات</title>
</head>
<body>
<input type="button" onclick="window.location='/jsp/user/page_login.jsp'" value="بازگشت">
<table class="jGrid" width="780px" cellpadding="0" cellspacing="0">
    <tr>
        <th colspan="4">مشاهده نظرات ارسال شده</th>
    </tr>
    <tr>
        <th width="15%">نام</th>
        <th width="15%">پست الکترونیک</th>
        <th width="10%">کد نمایندگی</th>
        <th width="60%">نظر</th>
    </tr>
    <c:if test="${userComments == null || fn:length(userComments) == 0}">
        <tr><td colspan="4">در حال حاضر هیچ نظر ارسالی وجود ندارد.</td></tr>
    </c:if>
    <c:forEach var="nazar" items="${userComments}">
        <tr>
            <td>${nazar.userName}</td>
            <td>${nazar.email}</td>
            <td>${nazar.codeNamayandegi}</td>
            <td>${nazar.nazar}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>