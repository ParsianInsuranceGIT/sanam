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

<html>
<head>
    <title>فهرست نماینده ها</title>
</head>
<body>

<form action="" id="form_karmozd">
    <table cellspacing="5" width="80%" style="margin: 0 auto;">
        <tr dir="ltr">
            <td>
                <input type="text" name="azTarikhSodur" id="azTarikheSodoreBimename" onkeyup=""
                       value='${azTarikhSodur}' class="datePkr"/>
                &nbsp;<label>از تاریخ صدور</label>
            </td>
            <td width="250px"></td>
            <td>
                <input type="text" name="taTarikhSodur" id="taTarikheSodoreBimename" onkeyup=""
                       value='${taTarikhSodur}' class="datePkr"/>
                &nbsp;<label>تا تاریخ صدور</label>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="button" value="انتخاب همه موارد" onclick="selectAll()" style="margin-bottom: -20px">
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <display:table export="false" id="tblListNamayande" uid="namayande" htmlId="tblListNamayande"
                               name="namayandeList.list" partialList="true" clearStatus="true" keepStatus="false"
                               style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
                               size="${namayandeList.fullListSize}" pagesize="${namayandeList.objectsPerPage}">
                    <display:column style="background:${theColor};" media="html"><input type="checkbox"
                                                                                        name="selectList"
                                                                                        value="${namayande.id}"/></display:column>
                    <display:column property="name" title="نام"></display:column>
                    <display:column property="kodeNamayandeKargozar" title="کد نماینده کارگزار"></display:column>
                    <display:column property="type" title="نوع"></display:column>
                    <display:column property="ostan.cityName" title="استان"></display:column>
                    <display:column property="shahr.cityName" title="شهر"></display:column>
                    <display:column property="address" title="آدرس"></display:column>
                    <display:column property="telephone" title="تلفن"></display:column>

                </display:table>
            </td>
        </tr>
    </table>
</form>
<input type="button" value="پرداخت کارمزد"
       onclick="window.location='/pardakhtKarmozdStage1?'+$('#form_karmozd').serialize()">
<script type="text/javascript">
    var on = false;
    function selectAll() {
        if (!on) {

            $('input[name=selectList]').attr("checked", true);
            on = true;
        } else {
            $('input[name=selectList]').attr("checked", false);
            on = false;
        }
    }
</script>
</body>
</html>
