<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head></head>
<body>
<center>
    <table>
        <tr>
            <td>سرمایه فوت به هر علت:</td>
            <td><s:property value="bimename.pishnehad.estelam.sarmaye_paye_fot"/></td>
        </tr>
        <tr>
            <td>نوع سرمایه:</td>
            <td></td>
        </tr>
        <tr>
            <td>مبلغ قسط:</td>
            <td><s:property value="bimename.majmuAghsat"/></td>
        </tr>
        <tr>
            <td>نحوه پرداخت حق بیمه نامه:</td>
            <td><s:property value="bimename.pishnehad.estelam.nahvePardakhtHaghBimeFarsi"/></td>
        </tr>
        <tr>
            <td>مدت بیمه نامه:</td>
            <td><s:property value="bimename.pishnehad.estelam.modat_bimename"/></td>
        </tr>
        <tr>
            <td>نوع حق بیمه:</td>
            <td></td>
        </tr>
        <tr>
            <td>سرمایه فوت در اثر حادثه:</td>
            <td><s:property value="bimename.pishnehad.estelam.sarmaye_paye_fot_dar_asar_hadese"/></td>
        </tr>
        <tr>
            <td>معافیت از پرداخت حق بیمه:</td>
            <td><s:if
                    test="bimename.pishnehad.estelam.pooshesh_moafiat.equals('yes')">دارد</s:if><s:else>ندارد</s:else></td>
        </tr>
        <tr>
            <td>بیماری های خاص:</td>
            <td><s:property value="bimename.pishnehad.estelam.sarmaye_pooshesh_amraz_khas"/></td>
        </tr>
        <tr>
            <td>ارزش بازخریدی:</td>
            <td><s:property value="bimename.arzeshBazkharidi"/></td>
        </tr>
        <tr>
            <td>وام دریافتی:</td>
            <td><s:property value="bimename.tedadVam"/></td>
        </tr>
        <tr>
            <td>اقساط وام پرداختی:</td>
            <td><s:property value="bimename.majmuAghsatVamPardakhtShode"/></td>
        </tr>
        <tr>
            <td>مدت بازپرداخت اقساط وام:</td>
            <td><s:property value="bimename.modatBazPardakhtVam"/></td>
        </tr>
        <tr>
            <td>نحوه پرداخت اقساط وام:</td>
            <td><s:property value="bimename.nahvePardakhtVam"/></td>
        </tr>
        <tr>
            <td>تلفن/موبایل:</td>
            <td><s:property value="bimename.pishnehad.addressBimeGozar.telphoneMobile"/></td>
        </tr>
        <tr>
            <td>آدرس بیمه گذار:</td>
            <td><s:property value="bimename.pishnehad.addressBimeGozar.neshaniManzel"/></td>
        </tr>
    </table>
</center>
</body>
</html>