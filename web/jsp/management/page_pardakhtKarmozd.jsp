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
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>فهرست نماینده ها</title>
</head>
<body>
<form action="" id="form_karmozd">
    <table cellspacing="5" width="80%">
        <tr dir="ltr">
            <td>
                <input type="hidden" name="azTarikhSodur" id="azTarikheSodoreBimename" onkeyup=""
                       value='${azTarikhSodur}'/>
            </td>
            <td width="250px"></td>
            <td>
                <input type="hidden" name="taTarikhSodur" id="taTarikheSodoreBimename" onkeyup=""
                       value='${taTarikhSodur}'/>
            </td>
        </tr>
        <s:if test="namayandes!=null">

            <tr>
                <td>
                    <display:table export="false" id="tblListNamayande" uid="nk" htmlId="tblListNamayande" name="nks"
                                   partialList="true" clearStatus="true" keepStatus="false"
                                   style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
                                   size="${nks.size()}" pagesize="${nks.size()}">
                        <s:if test="stage==2">
                                <display:column media="html"><input type="checkbox" name="selectList" value="${namayande.id}"/></display:column>
                        </s:if>
                        <display:column property="namayande.name" title="نام"></display:column>
                        <display:column property="namayande.kodeNamayandeKargozar"
                                        title="کد نماینده کارگزار"></display:column>
                        <s:if test="stage==1">
                            <display:column property="amount" title="مبلغ پرداختی"></display:column>
                            <display:column media="html"><input type="button" value="جزییات"
                                                                onclick="window.open('joziatPardakhtKarmozd?namayande.id=${nk.namayande.id}&azTarikhSodur=${azTarikhSodur}&taTarikhSodur=${taTarikhSodur}','جدول قسط','width=800px,height=600px')"></display:column>
                        </s:if>
                        <s:if test="stage==2">
                            <display:column property="credebit.shenaseCredebit" title="شناسه پرداخت"></display:column>
                            <display:column property="credebit.amount" title="مبلغ پرداختی"></display:column>
                        </s:if>
                    </display:table>
                </td>
            </tr>

        </s:if>
    </table>
    <s:if test="stage==1">
        <input type="button" value="تایید"
               onclick="window.location='/pardakhtKarmozdStage2?'+$('#form_karmozd').serialize()">
    </s:if>
    <input type="button" value="بازگشت" onclick="window.location='/listAllNamayandeForKarmozd'">
</form>
</body>
</html>
