<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<html>
<head>
</head>
<body>
<div align="center">
    <s:actionerror/>
    <s:actionmessage/>

    <form action="/setMobileNumber" method="post">
        <p>

        </p>
        <p>
            به منظور استفاده از امکان بازیابی کلمه عبور در صورت فراموشی آن، لطفا شماره موبایل خود را وارد نمایید.
        </p>

        <table dir="rtl">
            <tr>
                <td>تلفن همراه</td>
                <td><input type="text" id="mobileForget" name="mobileForget" class="validate[custom[telephone_hamraah]] text-input ui-state-default ui-corner-all"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit"  value="ثبت"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>