<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head></head>
<body>
<s:actionmessage/>
<form action="/bazgashtVaziat" method="post">
    <table>
        <tr>
            <td>
                شناسه بانک پیشنهاد
            </td>
            <td>
                <input type="text" name="pishnehadId"/>
            </td>
            <td>
                شناسه وضعیت
            </td>
            <td>
                <input type="text" name="stateId"/>
            </td>
            <td>
                <input type="submit" value="انجام"/>
            </td>
        </tr>
    </table>
</body>
</html>