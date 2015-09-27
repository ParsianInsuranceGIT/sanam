<%--<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/jsp/taglibs.jsp" %>
<head>
    <title>صفحه اصلی</title>
</head>
<div align="center">
    <table class="jtable">
        <s:if test="pishnehad.bimename.elhaghiyeha.size()>0">
            <s:iterator value="pishnehad.bimename.koleElhaghiyehaDaem" status="stat" id="row">
                <s:if test="%{#stat.index==0}">
                    <tr>
                        <th>ردیف</th>
                        <th>شماره الحاقیه</th>
                        <th>تاریخ اثر</th>
                        <th>تاریخ صدور</th>
                        <th>تاریخ درخواست</th>
                        <th>وضعیت</th>
                        <th>نوع الحاقیه</th>
                        <th>عملیات</th>
                    </tr>
                </s:if>
                <tr>
                    <td><s:property value="%{#stat.index+1}"/></td>
                    <td><s:property value="#row.getShomareElhaghiye()"/>&nbsp;</td>
                    <td><s:property value="#row.getTarikhAsar()"/>&nbsp;</td>
                    <td><s:property value="#row.getCreatedDate()"/>&nbsp;</td>
                    <td><s:property value="#row.getDarkhast().getDarkhastDate()"/>&nbsp;</td>
                    <td><s:property value="#row.getState().getStateName()"/>&nbsp;</td>
                    <td><s:property value="#row.getElhaghieTypeFarsi()"/>&nbsp;</td>
                    <td><input class="noAnyDisable" type="button" style="float:right;"
                                                     onclick="window.open('/printElhaghieFinalA5?elhaghiye.id=<s:property value="#row.getId()"/>')"
                                                     value="چاپ A5">
                        <input class="noAnyDisable" type="button" style="float:right;"
                                                     onclick="window.open('/printElhaghieFinalA4?elhaghiye.id=<s:property value="#row.getId()"/>')"
                                                     value="چاپ A4">
                    <input class="noAnyDisable" type="button" style="float:right;"
                                                     onclick="window.open('/showElhaghie?elhaghie.id=<s:property value="#row.getId()"/>')"
                                                     value="نمایش"></td>
                </tr>
            </s:iterator>
        </s:if>
    </table>
</div>