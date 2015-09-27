<%@ page import="com.bitarts.parsian.model.transitionwise.PezeshkSabtNazar" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%--<form></form>--%>

<div class=expandableTitleBar>
    <p class=heading ><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        نظرات ثبت شده توسط پزشک
    </p>
    <table class="resultDets" align="center" width="80%" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin:0 auto;" border="0">
        <thead>
        <tr>
            <th>
                نوع بیماری
            </th>
            <th>
                اضافه نرخ
            </th>
            <th>
                حذف نظر
            </th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${darkhastTaghirat.nazaraatePezeshk != null}">
            <c:if test="${pezeshkNazar.fromPezeshk == 1}">
                <c:forEach var="pezeshkNazar" items="${darkhastTaghirat.nazaraatePezeshk}">
                    <tr>
                        <td>
                            ${pezeshkNazar.nazar}
                        </td>
                        <td>
                            ${pezeshkNazar.ezafeNerkh}
                        </td>
                        <td>
                            <a href="javascript:void(0);" onclick="window.location='/pezeshkHazfeNazar?pezeshkSabtNazar.id=${pezeshkNazar.id}&darkhastTaghirat.id=${darkhastTaghirat.id}'">حذف</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${pezeshkNazar.fromPezeshk == 0}">
            <tr>
                <td colspan="3">
                    تا کنون نظری توسط پزشک برای این پیشنهاد ثبت نشده است.
                </td>
            </tr>
            </c:if>
        </c:if>
        </tbody>
    </table>
<br/>
<br/>

</div>

<div class=expandableTitleBar>
    <p class=heading ><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        نظر رئیس اداره
    </p>
    <table class="resultDets" align="center" width="80%" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin:0 auto;" border="0">
        <tr>
            <td>
                توضیحات:
            </td>
            <td colspan="3">
                <textarea rows="5" cols="65" id="nazarraeislog"></textarea>
            </td>
        </tr>
        <tr>
            <td>
                اضافه نرخ:
            </td>

            <c:set var="pezmajmou" value="${0}"/>
            <c:forEach var="pezeshkNazar" items="${darkhastTaghirat.nazaraatePezeshk}">
                <c:set var="pezmajmou" value="${pezmajmou+pezeshkNazar.ezafeNerkh}"/>
            </c:forEach>
            <td>
                <input type="text" name="pezeshkSabtNazar.ezafeNerkh" value="${pezmajmou}"/>
            </td>
            <c:if test="${darkhastTaghirat==null}">
                <td>عدم تایید پزشکی</td>
                <td>
                    <input type="checkbox" name="taeedornot" id="raeistaeedornot" onchange="checkEverything();"/>
                </td>
            </c:if>
            <c:if test="${darkhastTaghirat!=null}">
                <td>نیاز به آزمایش دارد؟</td>
                <td>
                    <input type="checkbox" name="azmayeshornot" id="raeisazmayeshornot" onchange="checkAzmayeshOrNot();"/>
                </td>
            </c:if>
        </tr>
        <tr>
            <td>
                &nbsp;
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <p style="color: red;">
                    توجه داشته باشید که بعد از کلیک دکمه «تایید» یا «عدم تایید» امکان تغییر اطلاعات را نخواهید داشت!
                </p>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <input type ="button" style="display: none;" id="raeistaeedmikonam" onclick="taeedKon()" value="تایید"/>
                <input type ="button" style="display: none;" id="raeistaeednemikonam" onclick="taeedNakon()" value="عدم تایید"/>
            </td>
        </tr>
    </table>
</div>

<form action="/taeedeNazarePezeshkForElhaghiyeTaghirat" id="raeisekhoshnazar" method="post" accept-charset="UTF-8">
    <input type="hidden" name="transitionId" id="raeistransitionid"/>
    <input type="hidden" name="logmessage" id="raeislogmessage"/>
    <input type="hidden" name="pishnehad.id" value="${darkhastTaghirat.id}"/>
</form>

<script type="text/javascript">
    function checkAzmayeshOrNot(){
        var raeisChecked = $("#raeisazmayeshornot").attr("checked");
        if(raeisChecked){
            $("#darkhat_taghirat_azmayesh").val("yes");
        }else{
            $("#darkhat_taghirat_azmayesh").val("no");
        }
    }
    function checkEverything(){
        var raeisChecked = $("#raeistaeedornot").attr("checked");
        if(raeisChecked){
            $("#raeistaeednemikonam").show();
            $("#raeistaeedmikonam").hide();
        }else{
            $("#raeistaeednemikonam").hide();
            $("#raeistaeedmikonam").show();
        }
    }
    function taeedKon(){
        <c:if test="${darkhastTaghirat == null}">
            $("#transitionSelector").val('27');
            $('#logmessage').val($('#nazarraeislog').val());
            submitTransitionForm();
        </c:if>
        <c:if test="${darkhastTaghirat != null}">
            $("#dakhastTransitionSelector").val('9016');
            $('#darkhastloggmessage').val($('#nazarraeislog').val());
            submitTransitionForDarkhast();
        </c:if>
    }
    function taeedNakon(){
        <c:if test="${darkhastTaghirat == null}">
            $("#transitionSelector").val('28');
            $('#logmessage').val($('#nazarraeislog').val());
            submitTransitionForm();
        </c:if>
        <c:if test="${darkhastTaghirat != null}">
            $("#dakhastTransitionSelector").val('9016');
            $('#darkhastloggmessage').val($('#nazarraeislog').val());
            submitTransitionForDarkhast();
        </c:if>
    }

</script>