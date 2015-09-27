<%@ page import="com.bitarts.parsian.model.transitionwise.PezeshkSabtNazar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class=expandableTitleBar>
    <p class=heading ><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        نظرات ثبت شده تا کنون
    </p>
    <table class="jtable" align="center" width="80%" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin:0 auto;" border="0">
        <thead>
        <tr>
            <th>
                نوع بیماری
            </th>
            <th>
                اضافه نرخ
            </th>
            <th>
                توضیحات
            </th>
            <th>
                حذف نظر
            </th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${darkhastTaghirat.nazaraatePezeshk != null}">
            <c:forEach var="peznazar" items="${darkhastTaghirat.nazaraatePezeshk}">
                <c:if test="${peznazar.fromPezeshk == 'true'}">
                    <tr>
                        <td>
                            ${peznazar.nazar}
                        </td>
                        <td>
                            ${peznazar.ezafeNerkh}
                        </td>
                        <td>
                            ${peznazar.tozihat}
                        </td>
                        <td>
                            <a href="javascript:void(0);" onclick="window.location='/removeNazarPezeshkDarkhastTaghirat?pezeshkSabtNazar.id=${peznazar.id}&darkhastTaghirat.id=${darkhastTaghirat.id}'">حذف</a>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </c:if>
        <c:if test="${darkhastTaghirat.nazaraatePezeshk == null}">
            <tr>
                <td colspan="3">
                    تا کنون نظری توسط پزشک برای این پیشنهاد ثبت نشده است.
                </td>
            </tr>
        </c:if>
        </tbody>
    </table>
<br/>
<br/>
    <c:if test="${darkhastTaghirat.state.id == 9110}">
        <form action="/sabteNazarePezeshkDarkhastTaghirat" id="pezeshkekhoshnazar" method="post" accept-charset="UTF-8">

            <input type="hidden" name="darkhastTaghirat.id" value="${darkhastTaghirat.id}" />

            <table align="center" width="70%" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin:0 auto;" border="0">
                <tr>
                    <td>
                        <label>نوع بیماری:</label>&nbsp;&nbsp;
                    </td>
                    <td>
                        <input type="text" name="pezeshkSabtNazar.nazar"/>
                    </td>
                    <td>
                        <label>اضافه نرخ:</label>&nbsp;&nbsp;
                    </td>
                    <td>
                        <input type="text" name="pezeshkSabtNazar.ezafeNerkh"/>
                    </td>
                    <td>
                        <label>توضیحات</label>&nbsp;&nbsp;
                    </td>
                    <td>
                        <input type="text" name="pezeshkSabtNazar.tozihat"/>
                    </td>
                    <td>
                        <input type="submit" value="ثبت"/>
                    </td>
                </tr>
            </table>
        </form>
    </c:if>
    <c:if test="${darkhastTaghirat == null}">
    <table>
        <tr>
            <td>عدم تایید پزشکی</td>
            <td>
                <input type="checkbox" name="taeedornot" id="taeedornot"/>
            </td>
        </tr>
    </table>
    <br/>
    <br/>

    <table align="center" width="70%" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin:0 auto;" border="0">

        <tr>
            <td>
                <input type="button" name="niazeazmayeshemojaddad" id="niazeazmayeshemojaddad" onclick="send4AzmayesheMojaddad();" value="نیاز به آزمایش مجدد"/>
            </td>
            <td>
                <input type="button" name="niazemadareketakmili" id="niazemadareketakmili" onclick="send4TakmileMadarek();" value="نیاز به مدارک تکمیلی"/>
            </td>
            <td>
                <input type="button" name="pezeshkenazzaar" id="pezeshkenazzaar" onclick="send4EtmameNazarDadan();" value="ثبت نظر پزشک"/>
            </td>
        </tr>
    </table>
    </c:if>

</div>
<script type="text/javascript">
function send4TakmileMadarek(){
    $("#transitionSelector").val('20');
    submitTransitionForm();
}
function send4AzmayesheMojaddad(){
    $("#transitionSelector").val('23');
    submitTransitionForm();
}
function send4EtmameNazarDadan(){
    <c:set var="pezmajmou" value="${0}"/>
        <c:forEach var="pezeshkNazar" items="${darkhastTaghirat.nazaraatePezeshk}">
        <c:set var="pezmajmou" value="${pezmajmou+pezeshkNazar.ezafeNerkh}"/>
    </c:forEach>
    var maj = ${pezmajmou};
    var checked = $("#taeedornot").attr("checked");
    if((!checked) && maj>30){
        $("#transitionSelector").val('24');
        submitTransitionForm();
    }
    if((!checked) && maj<=30){
    }
    if(checked){
    }
}
</script>
