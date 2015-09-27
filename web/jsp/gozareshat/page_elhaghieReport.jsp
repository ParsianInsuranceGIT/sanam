<%@ page import="com.bitarts.parsian.model.bimename.Gharardad" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bitarts.parsian.model.constantItems.Tarh" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ taglib uri="/struts-tags" prefix="st" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>گزارش الحاقیه</title>
</head>
<body>
<script type="text/javascript">
    $(document).ready(function ()
    {

        $('#emzaName').val('');
        $('#emzaPersonalCode').val('');

        $('#tblEmza').dialog({
            modal: true, resizable: false, autoOpen: false,
            width: 700, maxHeight: 500, minHeight: 100,
            title: "جستجوی کاربر"});
    }) ;
    function fillEmze()
    {
        $('#tblEmza').dialog('open');
    }

    function selectRow(id, fn, ln)
    {
        var ctrlId = $('#NafareEmza').val();
        $(ctrlId).val(fn + " " + ln);
        $(ctrlId + '_id').val(id);

        hideEmzaModal();
    }

    function hideEmzaModal()
    {
        $('#emzaName').val('');
        $('#emzaPersonalCode').val('');
        $('#tblEmza').dialog('close');
        $('#searchResualt').html('');
    }
    function searchEmaz()
    {
        var emzaName = $('#emzaName').val();
        var emzaPersonalCode = $('#emzaPersonalCode').val();


        $.post("/findUsers.action?" + "elhaghieReq=" + true + "&user.firstName=" + emzaName + "&user.personalCode=" + emzaPersonalCode ,
                function (msg)
                {
                    $('#searchResualt').html(msg);
                });

        fillEmze();
    }
</script>

<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
<input type="hidden" id="NafareEmza"/>

<div id="tblEmza" style="display: none">
    <table cellpadding="3" cellspacing="3" border="0"
           style="margin-left:auto;margin-right:auto;align:center;direction: rtl;">
        <tr>
            <td colspan="5">جستجو کاربر</td>
        </tr>
        <tr>
            <td>کد پرسنلی</td>
            <td><input type="text" id="emzaPersonalCode"/></td>
            <td>نام</td>
            <td><input type="text" id="emzaName"/></td>
            <td><input type="button" value="جستجو" id="btnSearch"
                       onclick="searchEmaz()"></td>
        </tr>
    </table>
    <div id="searchResualt"></div>
</div>
    <div class="expandableTitleBar" id="expandableAsl">
        <%--<p class="heading">--%>
            <%--<span class="ui-icon ui-icon-plus" style="float:right;"></span>--%>
            <%--جستجو--%>
        <%--</p>--%>
<%----%>
        <%--<div class="content" style="display:none;" id="searchAslContent">--%>
            <form action="/elhaghieReport.action" id="form_elhaghie_report" method="post"> <br/><br/>
                <table class="inputList" cellspacing="5" width="90%">
                    <tr>
                        <td>
                            <sec:authorize ifAnyGranted="ROLE_PAS_RAEIS,ROLE_PAS_KARSHENAS_MASOUL">
                                <span class="ui-icon ui-icon-trash" title="پاک کردن" id="search_vahedSodurRemover" onclick="deleteField('search_vahedSodur');" style="float:left;">  &nbsp;</span>
                                <span class="help ui-icon ui-icon-search"
                                      onclick="fillNamayandegi('search_vahedSodurId','search_vahedSodurName', '');"
                                      style="float:left;" title="جستجو"></span>
                                <span style="float: left;"> &nbsp; &nbsp;</span>
                                <input type="hidden"  name="elhaghieVMS.vahedSodurId" id="search_vahedSodurId" value="${elhaghieVMS.vahedSodurId}"/>
                                <input type="text"
                                       style="float:left;" name="elhaghieVMS.vahedSodurName" id="search_vahedSodurName" value="${elhaghieVMS.vahedSodurName}"
                                        readonly="true"/>

                                <label>واحد صدور</label>
                            </sec:authorize>
                        </td>
                        <td>
                            <span class="noThing"></span>
                            <input type="text" name="elhaghieVMS.bimeGozarFirstName" id="search_fNameBimeGozar"
                                   value="${elhaghieVMS.bimeGozarFirstName}"/>
                            &nbsp;<label>نام بیمه گذار</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <sec:authorize ifAnyGranted="ROLE_PAS_RAEIS,ROLE_PAS_KARSHENAS_MASOUL">
                                <span class="ui-icon ui-icon-trash" title="پاک کردن" id="search_namayandegiRemover"onclick="deleteField('search_namayandegi');" style="float:left;">  &nbsp;</span>
                                <span class="help ui-icon ui-icon-search"
                                      onclick="fillNamayandegi('search_namayandegiId','search_namayandegiName', '');"
                                      style="float:left;" title="جستجو"></span>
                                <span style="float: left;"> &nbsp; &nbsp;</span>

                                <input type="hidden" name="elhaghieVMS.namayandeId" id="search_namayandegiId"  value="${elhaghieVMS.namayandeId}"/>
                                <input type="text" name="elhaghieVMS.namayandeName" id="search_namayandegiName" value="${elhaghieVMS.namayandeName}"
                                       readonly="true"/>
                                <label>نمایندگی</label>
                            </sec:authorize>
                        </td>
                        <td>
                            <span class="noThing"></span>
                            <input type="text" name="elhaghieVMS.bimeGozarLastName" id="search_lNameBimeGozar"
                                   value="${elhaghieVMS.bimeGozarLastName}"/>
                            &nbsp;<label>نام خانوادگی بیمه گذار</label>
                        </td>

                    </tr>
                    <tr>
                        <td>
                            <span class="ui-icon ui-icon-trash" title="پاک کردن" id="elhaghiye_emzaKonandeAvalRemover" onclick="deleteField('elhaghiye_emzaKonandeAval');" style="float:left;"></span>
                            <span class="help ui-icon ui-icon-search"
                                  onclick="$('#NafareEmza').val('#elhaghiye_emzaKonandeAvalName');fillEmze();"
                                  style="float:left;" title="جستجو"></span>
                            <span style="float: left;"> &nbsp; &nbsp;</span>
                            <input type="text" id="elhaghiye_emzaKonandeAvalName" readonly="readonly"
                                   name="elhaghieVMS.userSaderKonandeName" value="${elhaghieVMS.userSaderKonandeName}"/>
                            <input type="hidden" name="elhaghieVMS.userSaderKonandeId" id="elhaghiye_emzaKonandeAvalName_id"
                                   value="${elhaghieVMS.userSaderKonandeId}"/>
                            <label>کاربر صادرکننده</label>
                        </td>
                        <td>
                            <span class="noThing"></span>
                            <input type="text" name="elhaghieVMS.bimeGozarCodeMeliOrShenasaei" id="search_codeBimeGozar"
                                   value="${elhaghieVMS.bimeGozarCodeMeliOrShenasaei}"/>
                            &nbsp;<label>کد ملی/شناسایی بیمه گذار</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span class="noThing"></span>
                            <span class="noThing">&nbsp;&nbsp;</span>
                            <st:select theme="simple" emptyOption="true" id="noe_grr" headerValue=""
                                       list="noeGharardads"
                                       name="elhaghieVMS.noeGharardad" value="elhaghieVMS.noeGharardad"/>
                            &nbsp;<label >نوع قرارداد</label>
                        </td>
                        <td>
                            <span class="noThing"></span>
                            <input type="text" name="elhaghieVMS.bimeShodeFirstName" id="search_fNameBimeShode"
                                   value="${elhaghieVMS.bimeShodeFirstName}"/>
                            &nbsp;<label>نام بیمه شده</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span class="noThing"></span>
                            <span class="noThing"></span>
                            <select name="elhaghieVMS.elhaghiyeTypeStr" id="search_elType">
                                <option <c:if test="${elhaghieVMS.elhaghiyeTypeStr==''||elhaghieVMS.elhaghiyeTypeStr==null}">selected="selected" </c:if> ></option>
                                <option <c:if test="${elhaghieVMS.elhaghiyeTypeStr=='EBTAL'}">selected="selected"</c:if> value="EBTAL">ابطال</option>
                                <option <c:if test="${elhaghieVMS.elhaghiyeTypeStr=='BAZKHARID'}">selected="selected"</c:if>value="BAZKHARID">بازخرید</option>
                                <option <c:if test="${elhaghieVMS.elhaghiyeTypeStr=='TOZIH'}">selected="selected"</c:if>value="TOZIH">توضیح</option>
                                <option <c:if test="${elhaghieVMS.elhaghiyeTypeStr=='TAGHIRKOD'}">selected="selected"</c:if>value="TAGHIRKOD">تغییر کد</option>
                                <option <c:if test="${elhaghieVMS.elhaghiyeTypeStr=='TAGHYIRAT'}">selected="selected"</c:if>value="TAGHYIRAT">تغییرات</option>
                            </select>
                            <label>نوع الحاقیه</label>
                        </td>
                        <td>
                            <span class="noThing"></span>
                            <input type="text" name="elhaghieVMS.bimeShodeLastName" id="search_lNameBimeShode"
                                   value="${elhaghieVMS.bimeShodeLastName}"/>
                            &nbsp;<label>نام خانوادگی بیمه شده</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <%
                                if (request.getAttribute("grouhHa") != null)
                                {
                            %>
                            <span class="noThing"></span>
                            <span class="noThing"></span>
                            <select id="bimename_goroh" name="elhaghieVMS.bimenameGroupId">
                                <%--<%--%>
                                    <%--for (Gharardad goroh : (List<Gharardad>) request.getAttribute("grouhHa"))--%>
                                    <%--{ %>--%>
                                <c:forEach var="goroh" items="${grouhHa}" varStatus="i">
                                    <c:if test="${goroh.id == null}">
                                        <option <c:if test="${elhaghieVMS.bimenameGroupId==null}">selected="selected"</c:if>  >
                                        </option>
                                    </c:if>
                                    <c:if test="${goroh.id != null}">
                                        <option value="${goroh.id}" <c:if test="${elhaghieVMS.bimenameGroupId==goroh.id}">selected="selected"</c:if>  >
                                            ${goroh.nameSherkat}
                                        </option>
                                    </c:if>
                                </c:forEach>
                                <%--<%}%>--%>
                            </select>
                            &nbsp;<label>گروه بيمه‌نامه</label>
                            <%}%>
                        </td>
                        <td>
                            <span class="noThing"></span>
                            <input type="text" name="elhaghieVMS.bimeShodeCodeMeliOrShenasaei" id="search_codeBimeShode"
                                   value="${elhaghieVMS.bimeShodeCodeMeliOrShenasaei}"/>
                            &nbsp;<label>کد ملی/شناسایی بیمه شده</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <%
                                if (request.getAttribute("tarhs") != null)
                                {
                            %>
                            <span class="noThing"></span>
                            <span class="noThing"></span>
                            <%--<st:select theme="simple" emptyOption="true" headerValue="" list="tarhs"--%>
                                       <%--name="elhaghieVMS.tarhId" value="elhaghieVMS.tarhId"/>--%>
                            <select id="tarh" name="elhaghieVMS.tarhId">

                                <%--&lt;%&ndash;%>
                                    <%--for (Tarh tarh : (List<Tarh>) request.getAttribute("tarhs"))--%>
                                    <%--{ %>--%>
                                <c:forEach var="tarh" items="${tarhs}" varStatus="j">
                                    <c:if test="${tarh.id == null}">
                                        <option value="" <c:if test="${elhaghieVMS.tarhId==null}">selected="selected"</c:if>  >
                                        </option>
                                    </c:if>
                                    <c:if test="${tarh.id != null}">
                                        <option value="${tarh.id}" <c:if test="${elhaghieVMS.tarhId==tarh.id}">selected="selected"</c:if>  >
                                            ${tarh.name}
                                        </option>
                                    </c:if>
                                </c:forEach>
                                <%--<%}%>--%>
                            </select>
                            &nbsp;<label>نوع طرح</label>
                            <%}%>
                        </td>
                        <td>
                            <span class="noThing"></span>
                            <input type="text" name="elhaghieVMS.shomareBimename" id="search_shomare_bimename"
                                   value="${elhaghieVMS.shomareBimename}"/>
                            &nbsp;<label>شماره بیمه نامه</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span class="noThing">
                            </span>
                            <input type="text" name="elhaghieVMS.fromTarikhSodurElhaghie"
                                   value="${elhaghieVMS.fromTarikhSodurElhaghie}" id="sabt_az_tarikh_"
                                   class="datePkr validate[custom[date]]"/>
                            &nbsp;<label>از تاریخ صدور الحاقیه</label>
                        </td>
                        <td>
                            <input type="text" name="elhaghieVMS.toTarikhSodurElhaghie"
                                   value="${elhaghieVMS.toTarikhSodurElhaghie}" id="sabt_ta_tarikh_"
                                   class="datePkr validate[custom[date]]"/>
                            &nbsp;<label>تا تاریخ صدور الحاقیه</label>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2">
                            <span class="noThing"></span>
                            <input type="button"
                                   onclick="window.location = '/jsp/management/page_mainManagementPage.jsp' "
                                   value="بازگشت"/>
                            <input type="submit" style="margin-left:12px" value="گزارش"/>
                            <script type="text/javascript">

                                function deleteField(arg)
                                {
                                    if(arg=='elhaghiye_emzaKonandeAval')
                                    {
                                        $('#elhaghiye_emzaKonandeAvalName').val('')
                                        $('#elhaghiye_emzaKonandeAvalName_id').val('')
                                    }
                                    else
                                    {
    //                                    $('#'+arg+'Remover').hide();
                                        $('#'+arg+'Id').val('');
                                        $('#'+arg+'Name').val('');
                                    }
                                }

                                function showRemover(arg)
                                {
                                    if($('#'+arg + 'Name').val()!=null && $('#' + arg + 'Name').val() != '')
                                    {
                                        $('#' + arg + 'Remover').show();
                                    }
                                }

                                function clearSeachFromVam()
                                {
                                    $('#search_fNameBimeGozar').val('');
                                    $('#search_fNameBimeShode').val('');

                                    $('#search_lNameBimeGozar').val('');
                                    $('#search_lNameBimeShode').val('');

                                    $('#search_vahedSodurId').val('');
                                    $('#search_vahedSodurName').val('');
                                    $('#search_namayandegiId').val('');
                                    $('#search_namayandegiName').val('');
                                    $('#noe_grr').val('');
                                    $('#tarh').val('');
                                    $('#search_shomare_bimename').val('');
                                    $('#sabt_az_tarikh_').val('');
                                    $('#sabt_ta_tarikh_').val('');
                                    $('#search_codeBimeGozar').val('');
                                    $('#search_codeBimeShode').val('');
                                    $('#search_elType').val('');
                                    $('#bimename_goroh').val('');
                                    $('#elhaghiye_emzaKonandeAvalName').val('');
                                    $('#elhaghiye_emzaKonandeAvalName_id').val('')
                                }
                            </script>
                            <input type="button" style="margin-left:12px" value="پاک کردن فرم"onclick="clearSeachFromVam()">

                        </td>
                </table>
            </form>
            <br/>
            <hr/>
            <br/>
        <%--</div>--%>
    </div>
    <br/><br/>

    <div style="overflow: auto;">
    <display:table export="true" id="elhaghiePgList" uid="row" htmlId="elhaghiePgList" style="width:100%"
                   name="elhaghiePgList" clearStatus="true"
                   requestURI="" keepStatus="false">
        <display:column title="ردیف">${(elhaghiePgList.pageNumber-1)*30+row_rowNum }</display:column>
        <display:column property="tarikhSodourElhaghie" title="تاریخ صدور الحاقیه" style="white-space: nowrap;"></display:column>
        <display:column property="vahedSodurCode" title="کد واحد صدور"></display:column>
        <display:column property="vahedSodurName" title="نام واحد صدور"></display:column>
        <display:column title="شماره بيمه‌نامه" property="shomareBimename" style="white-space: nowrap;"></display:column>
        <display:column title="شماره الحاقیه" property="shomareElhaghie" style="white-space: nowrap;"></display:column>
        <display:column title="ردیف الحاقیه" property="radifElhaghie"></display:column>
        <display:column title="نوع الحاقیه" property="elhaghiyeTypeFa"></display:column>
        <display:column property="noeKhas" title="نوع خاص"/>
        <display:column title="تاریخ اثر الحاقیه" property="tarikhAsarElhaghie"></display:column>
        <display:column title="گروه بیمه نامه" property="bimenameGroup"></display:column>
        <%--<display:column title="نوع خاص" ></display:column>--%>
        <display:column property="elhaghieSubject" title="موضوع الحاقیه"/>
        <display:column property="bimeGozarName" title="بیمه گذار"/>
        <display:column property="modatBimename" title="مدت بیمه نامه"/>
        <display:column property="sarmayeFot" title="سرمایه فوت (ریال)"/>
        <display:column property="tarikhShorouBimename" title="تاریخ شروع بیمه نامه"/>
        <display:column property="tarikhEnghezaBimename" title="تاریخ پایان بیمه نامه"/>
        <display:column property="haghBimeElhaghie" title="حق بیمه الحاقیه"/>
        <display:column property="namayandeCode" title="کد نمایندگی"/>
        <display:column property="namayandeName" title="نام نمایندگی"/>
        <display:column property="tarikhSodourBimename" title="تاریخ صدور بیمه نامه"/>
        <display:column property="nerkhTadilSarmaye" title="نرخ تعدیل سرمایه"/>
        <display:column property="nerkhTadilHaghBime" title="نرخ تعدیل حق بیمه"/>
        <display:column property="bimeShodeName" title="بیمه شده"/>
        <display:column property="bimeShodeCodeMeliOrShenasaei" style="white-space: nowrap;" title="کد ملی/شناسایی بیمه شده"/>
        <display:column property="bimeShodeAge" title="سن بیمه شده"/>
        <display:column property="nahvePardakhtHaghBimeFa" title="نحوه پرداخت حق بیمه"/>
        <display:column property="sarmayeFotDarHadese" title="سرمایه فوت در اثر حادثه (ریال)"/>
        <display:column property="sarmayeAmrazKhas" title="سرمایه امراض خاص (ریال)"/>
        <display:column property="sarmayeNaghsOzv" title="سرمایه نقص عضو (ریال)"/>
        <display:column property="poosheshMoafiatAzKarOftadegiFa" title="پوشش معافیت از کارافتادگی"/>
        <display:column property="userSaderKonandeName" title="نام کاربر"/>
        <display:column property="userSaderKonandeCode" title="کد کاربر"/>

    </display:table>
</div>
<%--<input type="button" onclick="window.location='/elhaghieReport.action'" value="گزارش"/>--%>
<%--<input type="button" onclick="window.location = '/jsp/management/page_mainManagementPage.jsp' " value="بازگشت"/>--%>
</body>
</html>