<%@ page import="com.bitarts.parsian.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="div_mohasebat_paye">
</div>
<%--<form></form>--%>
<table>
    <tr>
        <td>
            <input type="button" onclick="taeedMohasebat();" value="تایید محاسبات پایه"/>
        </td>
        <td>
            <input type="button" onclick="tagheerSharayet();" value="نیاز به تغییر شرایط"/>
        </td>
    </tr>
</table>

<script type="text/javascript">
    function taeedMohasebat(){
        $("#transitionSelector").val(33);
        submitTransitionForm();

    }
    function tagheerSharayet(){
        $("#transitionSelector").val(32);
        submitTransitionForm();
    }
</script>