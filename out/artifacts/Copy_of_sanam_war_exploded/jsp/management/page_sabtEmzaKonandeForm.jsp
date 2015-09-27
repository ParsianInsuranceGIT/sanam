<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="s" uri="/struts-tags" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ include file="/jsp/taglibs.jsp"%>
<head>
    <title>افزودن امضا کننده</title>
</head>
<form class="mainFrame" id="mainForm" method="post" action="/sabtEmzaKonande">
    <table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0"
           style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <thead>
        <tr>
            <th colspan="2">
                ثبت امضا کننده:
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                نام کاربر
            </td>
            <td>
                <span class="noThing">&nbsp;</span>
                <span class="noThing">&nbsp;</span>
                <input type="text" readonly="readonly" value="${user.firstName}">
                <input type="hidden" name="emzaKonande.id" value="${emzaKonande.id}">
            </td>
        </tr>
        <tr>
            <td>
                کد کاربر
            </td>
            <td>
                <span class="noThing">&nbsp;</span>
                <span class="noThing">&nbsp;</span>
                <input type="text" readonly="readonly" name="emzaKonande.user.id" value="${user.id}">
            </td>
        </tr>
        <tr>
            <td>نوع امضاکننده</td>
            <td>
                <span class="noThing">&nbsp;</span>
                <span class="noThing">&nbsp;</span>
                <select name="emzakonandeType">
                    <option value=""></option>
                    <option value="SODUR"
                            <s:if test="emzaKonande.type.name().equals('SODUR')">selected="selected"</s:if> >
                        امضاکننده صدور
                    </option>
                    <option value="KHESARAT"
                            <s:if test="emzaKonande.type.name().equals('KHESARAT')">selected="selected"</s:if>>
                        امضاکننده خسارت
                    </option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                حداکثر اعتبار امضا(به ریال)
            </td>
            <td>
                <span class="noThing">&nbsp;</span>
                <span class="noThing">&nbsp;</span>
                <input type="text" name="emzaKonande.maxCapitalString" id="maxCapital" class="validate[required,custom[long]] text-input digitSeparators" value="${emzaKonande.maxCapitalString}"/>
            </td>
        </tr>
        <%--------------------------------------------------------------------------------------------------------------------%>
        <tr>
            <td id="mojtamaSodoorTxTd" rowspan="${fn:length(emzaKonande.otherMojtamaSodoor) == 0 ? 1 : 1+fn:length(emzaKonande.otherMojtamaSodoor)}">
                مجتمع صدور
            </td>
            <td>
                <script type="text/javascript">
                    var indx = ${fn:length(emzaKonande.otherMojtamaSodoor)};
                    function addVahedeSodoor(){
                        var newRowContent = $('#targetContent').html();
                        while(newRowContent.indexOf('_indx_') != -1){
                            newRowContent =  newRowContent.replace('_indx_', indx);
                        }
                        $('#afterMojtamaSodoorTr').before(newRowContent);
                        $('#mojtamaSodoorTxTd').attr("rowspan", $('#mojtamaSodoorTxTd').attr("rowspan")+1);
                        $(".help, .comment").tipsy({gravity:'s'});
                        indx++
                    }
                    function removeVahedeSodoor(trId){
                        var strName = $('#inp_'+trId).val();
                        if(strName && strName != "") strName = ('('+strName+')');
                        confirmMessage(' آیا از خذف واحد صدور '+strName+' مطمئن هستید؟', '', function(){
                            $('#mojtamaSodoorTxTd').attr("rowspan", $('#mojtamaSodoorTxTd').attr("rowspan")-1);
                            $('#tr_'+trId).remove();
                            $(".tipsy").remove();
                        });
                    }
                    function fillNMY(id){
                        fillNamayandegi('hid_'+id, 'inp_'+id, 'emzaKonandeActive');
                    }
                </script>

                <span class="noThing">&nbsp;</span>
                <span class="help ui-icon ui-icon-plus" onclick="addVahedeSodoor();" style="float:left;" title="افزودن واحد صدور"></span>
                <input type="text" value="${user.mojtamaSodoor.name}" readonly="readonly"/>
            </td>
        </tr>
        <c:forEach var="row" items="${emzaKonande.otherMojtamaSodoor}" varStatus="i">
            <tr id="tr_${i.index}">
                <td>
                    <span class="help ui-icon ui-icon-search" onclick="fillNMY(${i.index});" style="float:left;" title="جستجو"></span>
                    <span class="help ui-icon ui-icon-close" onclick="removeVahedeSodoor(${i.index});" style="float:left;" title="حذف"></span>
                    <input type='text' id="inp_${i.index}" value="${row.name}" readonly='readonly'/>
                    <input type='hidden' id="hid_${i.index}" value="${row.id}" name="emzaKonande.otherMojtamaSodoor[${i.index}].id"/>
                </td>
            </tr>
        </c:forEach>
        <%--------------------------------------------------------------------------------------------------------------------%>
        <tr id="afterMojtamaSodoorTr">
            <td>
                فعال
            </td>
            <td>
                <span class="noThing">&nbsp;</span>
                <span class="noThing">&nbsp;</span>
                <input type="checkbox" id="emzaKonandeActive" name="emzaKonande.active" ${emzaKonande.active == 'on'? "checked=checked" :''}/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="ثبت"/>
                <input type="button" onclick="window.location='/showEmzaKonandegan'" value="بازگشت"/>
            </td>
        </tr>
        </tbody>
    </table>
</form>

<table style="display:none;">
    <tbody id="targetContent">
    <tr id="tr__indx_">
        <td class="ui-widget-content">
            <span class="help ui-icon ui-icon-search" onclick="fillNMY(_indx_);" style="float:left;" title="جستجو"></span>
            <span class="help ui-icon ui-icon-close" onclick="removeVahedeSodoor(_indx_);" style="float:left;" title="حذف"></span>
            <input type='text' id="inp__indx_" readonly='readonly'/>
            <input type='hidden' id="hid__indx_" name="emzaKonande.otherMojtamaSodoor[_indx_].id"/>
        </td>
    </tr>
    </tbody>
</table>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>