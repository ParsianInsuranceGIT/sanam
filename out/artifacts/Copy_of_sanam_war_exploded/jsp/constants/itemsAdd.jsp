<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>

</head>
<body>
<form action="/addConstant" method="post" id="f_constant" name="f_constant">
    <table>
        <tr>
            <td>
                <label>تاریخ تاثیر:</label>
            </td>
            <td>
                <input type="text" name="constant.dateEffect" id="dateEffect" onkeyup=""
                       class="validate[required,custom[date]] datePkr" style="margin-right: -18px"/>
            </td>
            <td>
                <label for="constantStyle">نحوه تاثیر:</label>
            </td>
            <td>
                <s:select theme="simple" list="applyEnumStrings" name="constantStyle" listKey="enumS" listValue="farsiS"
                          cssStyle="width: 300px"/>
            </td>
            <td>
                <label for="tarhId">طرح: </label>
            </td>
            <td>
                <s:select theme="simple" list="tarhs" name="tarhId" listKey="id" listValue="name"
                          cssStyle="width: 300px"/>
            </td>
        </tr>
        <tr>
            <td>
                <label for="constantName">نام:</label>
            </td>
            <td>
                <s:doubleselect formName="f_constant" doubleList="hashNameEnumStrings.get(top)"
                                doubleName="constantName2" name="constantName" list="hashNameEnumStrings.keys"
                                doubleListValue="farsiS" doubleListKey="enumS" listKey="enumS" listValue="farsiS"
                                cssStyle="width: 200px" doubleCssStyle="width: 200px" theme="simple"/>
            </td>
            <td>
                <label>مقدار:</label>
            </td>
            <td>
                <textarea rows="2" cols="41" name="constant.value"></textarea>
            </td>
        </tr>
        <tr>

        </tr>

        <tr>
            <td colspan="2">
                <input type="button" value="اضافه" onclick="$('#f_constant').submit()"/>
                <input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'"
                       value="بازگشت"/>
            </td>
        </tr>
    </table>
</form>


</body>
</html>