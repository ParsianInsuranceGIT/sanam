<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<br class="clear"/>

<c:if test="${darkhastBazkharid.darkhastType!='KHESARAT'}">
    <input type="hidden" id="binaghsiteller" value="false"/>
    <input type="hidden" id="sehhatemzateller" value="false"/>
    <table class="mystrippedtable" id="table4naghsmadrak" dir="rtl" cellpadding="2px" cellspacing="0px"
           style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <tr class="odd">
            <td>
                آیا مدارک بیمه گذار برای صدور درخواست کامل است؟
            </td>
            <td>
                <input type="button" onclick="adameNaghseMadarek();" value="بلی"/>
            </td>
            <td>
                <input type="button" onclick="naghseMadarek();" value="خیر"/>
            </td>
        </tr>
    </table>
    <table class="mystrippedtable" id="table4sehatemza" dir="rtl" cellpadding="2px" cellspacing="0px"
           style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <tr class="odd">
            <td>
                آیا امضای بیمه گذار و بیمه شده در درخواست بهره مندی از منافع با امضای آنها در اصل بیمه نامه تطابق دارد؟
            </td>
            <td>
                <input type="button" onclick="tataabogheEmza();" value="بلی"/>
            </td>
            <td>
                <input type="button" onclick="adameTataabogheEmza();" value="خیر"/>
            </td>
        </tr>
    </table>
    <table id="table4taeednahaei" dir="rtl" cellpadding="2px" cellspacing="0px"
           style="display: none; border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <tr>
            <td>
                <input type="button" onclick="submitTaeedTransition()" value="تایید نهایی"/>
            </td>
        </tr>
    </table>

    <script type="text/javascript">
        function adameNaghseMadarek() {
            $("#table4naghsmadrak").hide();
            $("#binaghsiteller").val("true");
            if (($("#binaghsiteller").val() == "true") && ($("#sehhatemzateller").val() == "true")) {
                $("#table4taeednahaei").show();
            <s:if test="darkhastBazkharid.getDarkhastType().toString().equals('BARDASHT_AZ_ANDOKHTE')">
                $("#tab_8").show();
                $('.content').hide();
                $('#content_8').show();
                $('.tabsbtn').removeClass('activesubmit');
                $('#tab_8').addClass('activesubmit');
                $("#tab_4").hide();
            </s:if>
            }
        }
        function tataabogheEmza() {
            $("#table4sehatemza").hide();
            $("#sehhatemzateller").val("true");
            if (($("#binaghsiteller").val() == "true") && ($("#sehhatemzateller").val() == "true")) {
                $("#table4taeednahaei").show();
            <s:if test="darkhastBazkharid.getDarkhastType().toString().equals('BARDASHT_AZ_ANDOKHTE')">
                $("#tab_8").show();
                $('.content').hide();
                $('#content_8').show();
                $('.tabsbtn').removeClass('activesubmit');
                $('#tab_8').addClass('activesubmit');
                $("#tab_4").hide();
            </s:if>
            }
        }
        function naghseMadarek() {
            $("#dakhastTransitionSelector").val(1002)
            $("#darkhast_transitionId").val($("#dakhastTransitionSelector").val());
            submitTransitionForDarkhast();
        }
        function adameTataabogheEmza() {
            $("#dakhastTransitionSelector").val(1005)
            $("#darkhast_transitionId").val($("#dakhastTransitionSelector").val());
            submitTransitionForDarkhast();
        }
        function submitTaeedTransition() {
        <c:if test="${darkhastBazkharid.darkhastType == 'BARDASHT_AZ_ANDOKHTE'}">
//            $("#takhsiskharshenastransidfordakhast").val(11008);
        </c:if>
        <c:if test="${darkhastBazkharid.darkhastType == 'VAM'}">
            $("#takhsiskharshenastransidfordakhast").val(10008);
        </c:if>
        <c:if test="${darkhastBazkharid.darkhastType == 'BAZKHARID'}">
            $("#dakhastTransitionSelector").val(1004)
        </c:if>

            $("#darkhast_transitionId").val($("#dakhastTransitionSelector").val());
            $("#darkhastTransitionForm").submit();
        }
    </script>
</c:if>