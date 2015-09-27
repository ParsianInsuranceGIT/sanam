<%@ page import="com.bitarts.parsian.model.BankInfo" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
//    List<BankInfo> bankinfos = (List<BankInfo>) request.getAttribute("bankinfos");
%>
<head>
     <title>اطلاعات ذخیره شده در پایگاه داده</title>
</head>

<form id="uploadedform" action="/finalizeUpload" method="POST">
<%--<form id="uploadedform" action="/testExCell" method="POST">--%>
<s:actionerror/>
<div class=expandableTitleBar>
    <p class=heading ><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        اطلاعات بارگذارنده
    </p>
    <div class="staticTitleBar" style="direction:rtl;">
            <table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin:0 auto;" border="0">
                <tbody>
                    <tr>
                        <td>
                            نام بانک
                        </td>
                        <td>
                            <input type="text" name="bargozar.nameBank" value="${sessionScope.bargozar.nameBank}"/>
                        </td>

                        <td>
                            نام شعبه
                        </td>
                        <td>
                            <input type="text" name="bargozar.nameShobe" value="${sessionScope.bargozar.nameShobe}"/>
                            <input type="hidden" name="bargozar.shomareHesab" value="${sessionScope.bargozar.shomareHesab}"/>
                        </td>
                        <td>
                            تاریخ بارگذاری
                        </td>
                        <td>
                            <input type="text" class="validate[required,custom[date]]" id="bargozar_taarikh" name="bargozar.taarikh" value="${sessionScope.bargozar.taarikh}"/>
                        </td>
                    </tr>
                </tbody>
            </table>

    </div>
</div>
<div class=expandableTitleBar>
    <p class=heading ><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        اطلاعات آماده برای ذخیره شدن از فایل بارگذاری شده
    </p>
    <div class="staticTitleBar" id="result" style="direction:rtl;">
        <table class="jtable resultDets" align="center" cellpadding="0" cellspacing="0" style="border-spacing: 1px;margin: 0 auto;" border="0">
            <tr>
                <th colspan="2">گزارشات</th>
            </tr>
            <tr>
                <td valign="top" style="border: none;">
                    <table class="jtable resultDets" align="center" cellpadding="0" cellspacing="0"
                   style="border-spacing:1px;margin:0 auto;" border="0">
                <tbody>
                    <tr>
                        <th>نوع وضعیتی</th>
                        <th>تعداد</th>
                    </tr>
                <s:iterator value="#session.typesReportMap" id="r">
                    <tr>
                        <td><s:property value="#r.key"/></td>
                        <td><s:property value="#r.value"/></td>
                    </tr>
                </s:iterator>
                <tr>
                    <th>مجموع</th>
                    <th>${bankinfos.fullListSize}</th>
                </tr>
                </tbody>
            </table>
                </td>
                <td valign="top" style=" border: none;">
                    <table class="jtable resultDets" align="center" cellpadding="0" cellspacing="0"
               style="border-spacing:1px;margin:0 auto;" border="0">
            <tbody>
                <tr>
                    <th>کد مشتری های تکراری در فایل</th>
                    <th>تعداد تکرار</th>
                </tr>
            <s:iterator value="#session.codeMoshtariTekrariMap" id="r1">
                <s:if test="#r1.value > 1">
                    <tr>
                        <td><s:property value="#r1.key"/></td>
                        <td><s:property value="#r1.value"/></td>
                    </tr>
                </s:if>
            </s:iterator>
            </tbody>
        </table>
                </td>
            </tr>
        </table>

        <display:table export="true" id="tblListFileUpld" uid="row" htmlId="tblListFileUpldHtml"
                    name="bankinfos" partialList="false" clearStatus="true" keepStatus="false"
                    style="margin-right:auto;margin-left:auto;margin-top:20px;width:80%;background-color:yellow" requestURI=""
                    size="${bankinfos.fullListSize}" pagesize="${bankinfos.objectsPerPage}">
            <c:if test="${row.stateId == -1 || row.stateId == -4}">
                <display:column style="background:#00cc66;" title="ردیف" >${row_rowNum+((bankinfos.pageNumber-1)*bankinfos.objectsPerPage)}</display:column>
                <%--<display:column style="background:#00cc66;" title="ردیف در فایل" property="idUploaded"></display:column>--%>
                <display:column style="background:#00cc66;" title="کد دریافت" property="kodeDaryaft"></display:column>
                <display:column style="background:#00cc66;" title="تاریخ" property="taarikh"></display:column>
                <display:column style="background:#00cc66;" title="توضیحات" property="desc"></display:column>
                <display:column style="background:#00cc66;" title="مبلغ" property="mablagh"></display:column>
                <display:column style="background:#00cc66;" title="شماره فیش" property="shomareFish"></display:column>
                <display:column style="background:#00cc66;" title="شناسه پرداخت" property="codeMoshtari"/>
                <%--<display:column style="background:#00cc66;" title="وضعیت" property="vaziyateEtebar"/>--%>
                <display:column style="background:#00cc66;" title="وضعیت" property="vaziyateEtebarDesc"/>
                <%--<input type="hidden" value='<c:out value="${row.time}"/>'/>--%>
                <%--<input type="hidden" value='<c:out value="${row.kodeShobe}"/>'/>--%>

            </c:if>
            <c:if test="${row.stateId == -2 || row.stateId == -5 || row.stateId == -6 || row.stateId == -9 || row.stateId == -10 }">
                <display:column style="background:#f08080;" title="ردیف" >${row_rowNum+((bankinfos.pageNumber-1)*bankinfos.objectsPerPage)}</display:column>
                <%--<display:column style="background:#f08080;" title="ردیف در فایل" property="idUploaded"></display:column>--%>
                <display:column style="background:#f08080;" title="کد دریافت" property="kodeDaryaft"></display:column>
                <display:column style="background:#f08080;" title="تاریخ" property="taarikh"></display:column>
                <display:column style="background:#f08080;" title="توضیحات" property="desc"></display:column>
                <display:column style="background:#f08080;" title="مبلغ" property="mablagh"></display:column>
                <display:column style="background:#f08080;" title="شماره فیش" property="shomareFish"></display:column>
                <display:column style="background:#f08080;" title="شناسه پرداخت" property="codeMoshtari"/>
                <%--<display:column style="background:#f08080;" title="وضعیت" property="vaziyateEtebar"/>--%>
                <display:column style="background:#f08080;" title="وضعیت" property="vaziyateEtebarDesc"/>
                <%--<input type="hidden" value='<c:out value="${row.time}"/>'/>--%>
                <%--<input type="hidden" value='<c:out value="${row.kodeShobe}"/>'/>--%>
            </c:if>
            <c:if test="${row.stateId == -3}">
                <display:column style="background:yellow;" title="ردیف" >${row_rowNum+((bankinfos.pageNumber-1)*bankinfos.objectsPerPage)}</display:column>
                <%--<display:column style="background:yellow;" title="ردیف در فایل" property="idUploaded"></display:column>--%>
                <display:column style="background:yellow;" title="کد دریافت" property="kodeDaryaft"></display:column>
                <display:column style="background:yellow;" title="تاریخ" property="taarikh"></display:column>
                <display:column style="background:yellow;" title="توضیحات" property="desc"></display:column>
                <display:column style="background:yellow;" title="مبلغ" property="mablagh"></display:column>
                <display:column style="background:yellow;" title="شماره فیش" property="shomareFish"></display:column>
                <display:column style="background:yellow;" title="شناسه پرداخت" property="codeMoshtari"/>
                <%--<display:column style="background:yellow;" title="وضعیت" property="vaziyateEtebar"/>--%>
                <display:column style="background:yellow;" title="وضعیت" property="vaziyateEtebarDesc"/>
                <%--<input type="hidden" value='<c:out value="${row.time}"/>'/>--%>
                <%--<input type="hidden" value='<c:out value="${row.kodeShobe}"/>'/>--%>
            </c:if>
            <input type="hidden" name="serialBankInfo" value="${serialBankInfo}"/>
        </display:table>
    <div>
        <sec:authorize ifAnyGranted="ROLE_KARBAR_MALI">
            <input type="button" onclick="window.location='/jsp/management/page_sabtFileTxtAzBank.jsp'" value="بازگشت"/>
        </sec:authorize>
        <sec:authorize ifNotGranted="ROLE_KARBAR_MALI">
            <input type="button" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'" value="بازگشت"/>
        </sec:authorize>
        <input type="submit" value="ثبت"/>
        <%--<c:choose>--%>
            <%--<c:when test="${hasErrorRow == true}">--%>
                <%--<input type="button" onclick="window.open('/testExCell');" id="print_btn" value="دريافت اكسل"/>--%>
            <%--</c:when>--%>
            <%--<c:otherwise>--%>
                <%--<input type="submit" value="ثبت"/>--%>
            <%--</c:otherwise>--%>
        <%--</c:choose>--%>

    </div>
</div>
</form>
<div style="clear:both"/>
<div id="resultholder" style="display: none;">
</div>
<div style="clear:both"/>
<script type="text/javascript">
    function deleteTheRow(val){
        $("#"+val).fadeTo(1500,0,function(){
            $("#"+val).remove();
        });

    }
    function tryToEditTheRow(id){
        $("#shomareMoshtariLabel"+id).hide();
        var elem = "<input type='text' class='ui-state-default  ui-corner-all' id='temptextforshomaremoshtari"+id+"' value='"+ $("#shomareMoshtariLabel"+id).text()+"'/>";
        elem+="<input class='ui-state-default  ui-corner-all' id='tempbuttonforshomaremoshtari"+id+"' type='button' value='تایید' onclick='taeedTaghirShomareMoshtari("+id+")'/>"
        if(!$("#temptextforshomaremoshtari"+id).length){
            $("#shomareMoshtariTd"+id).append(elem);
        }
    }
    function taeedTaghirShomareMoshtari(id){
        $("#shomareMoshtariLabel"+id).text($("#temptextforshomaremoshtari"+id).val());
        $("#shomareMoshtariHidden"+id).val($("#temptextforshomaremoshtari"+id).val());
        $("#temptextforshomaremoshtari"+id).remove();
        $("#tempbuttonforshomaremoshtari"+id).remove();
        $("#shomareMoshtariLabel"+id).show();
        var shomareHidden = $('#shomareMoshtariHidden'+id).val();
        var mablagh = $('#mablaghLabel'+id).text();
        $.post("/searchForThisNewShomareMoshtari?bankInfo.shomareMoshtari="+shomareHidden+"&bankInfo.mablagh="+mablagh, function(msg) {
            $("#resultholder").html(msg);
            var decider = $("#resultForShenaseMoshtari").val();
            if(parseInt(decider)==-2){
                $("#tr_"+id).attr("onmouseover","");
                $("#aslcontainer"+id).val(-2);
                $("#tr_"+id+" td").each(function(){
                    $(this).attr('style',"padding:10px;background:#ffff99;border-top: 1px solid #ff9900;border-bottom: 1px solid #ff9900;");
                })
            }else if(parseInt(decider)==-3){
                $("#aslcontainer"+id).val(-3);
                $("#tr_"+id).attr("onmouseover","");
                $("#tr_"+id+" td").each(function(){
                    $(this).attr('style',"padding:10px;background:#00cc66;border-top: 1px solid #009933;border-bottom: 1px solid #009933;");
                })
            }else if(parseInt(decider)==-4){
                $("#aslcontainer"+id).val(-4);
                $("#tr_"+id).attr("onmouseover","");
                $("#tr_"+id+" td").each(function(){
                    $(this).attr('style',"padding:10px;background:#33ff33;border-top: 1px solid green;border-bottom: 1px solid green;");
                })
            }

        });


    }
</script>