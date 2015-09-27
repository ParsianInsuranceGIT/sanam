<%@ page import="com.bitarts.parsian.model.bimename.DarkhastBazkharid" %>
<%@ page import="com.bitarts.parsian.model.bimename.DarkhastTaghirat" %>
<%@ page import="com.bitarts.parsian.model.pishnahad.*" %>
<%@ page import="com.bitarts.parsian.Core.Constant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="function" uri="http://tagutils" %>
<%@include file="/jsp/taglibs.jsp" %>
<%
    DarkhastBazkharid darkhastRun = (DarkhastBazkharid) request.getAttribute("darkhastBazkharid");
    DarkhastTaghirat darkhastTaghiratRun = (DarkhastTaghirat) request.getAttribute("darkhastTaghirat");
    com.bitarts.parsian.model.pishnahad.Pishnehad pishnehadRun = (Pishnehad) request.getAttribute("pishnehad");
%>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="../../css/pishnahadeBimeOmreEnferadi.css"/>
    <script type="text/javascript" src="/jsp/bimename/darkhastBimenameOmreEnferadi.js"></script>
    <script type="text/javascript" src="/jsp/pishnahad/pishnahadeBimeOmreEnferadi.js"></script>
    <title>سیستم درخواست</title>
</head>
<body>
<script type="text/javascript">
    $(document).ready
    (
            function()
            {
                <c:if test="${(darkhastBazkharid.state.id == 654 || darkhastBazkharid.state.id == 658 || darkhastBazkharid.state.id == 657)}">
                    $("#tabs").children().removeClass("activesubmit");
                    $("#tab_16").addClass("activesubmit");
                    $('#tabformcontent').children().hide();
                    $('#content_16').show();
                    <c:if test="${darkhastBazkharid.khesaratI.elhaghiye!=null}">
                        $("#dakhastTransitionSelector option[value='690']").remove();
                        $("#dakhastTransitionSelector option[value='614']").remove();
                        $("#dakhastTransitionSelector option[value='622']").remove();
                    </c:if>
                    <c:if test="${darkhastBazkharid.khesaratII!=null}">
                        <c:if test="${(
                                        (darkhastBazkharid.khesaratI.khesaratType=='AMRAZ' && !darkhastBazkharid.bimename.haveKhesaratAmraz)||
                                        (darkhastBazkharid.khesaratI.khesaratType=='NAGHSOZV' && !darkhastBazkharid.bimename.haveKhesaratNaghsOzv)||
                                        (darkhastBazkharid.khesaratI.khesaratType!='AMRAZ' && darkhastBazkharid.khesaratI.khesaratType!='NAGHSOZV')
                                      ) &&
                                      (darkhastBazkharid.khesaratI.elhaghiye==null)}">

                            $('#btnSodoor').val('صدور الحاقیه خسارت اول');
                        </c:if>
                        <c:if test="${(
                                        (darkhastBazkharid.khesaratI.khesaratType=='AMRAZ' && darkhastBazkharid.bimename.haveKhesaratAmraz)||
                                        (darkhastBazkharid.khesaratI.khesaratType=='NAGHSOZV' && darkhastBazkharid.bimename.haveKhesaratNaghsOzv)||
                                        (darkhastBazkharid.khesaratI.elhaghiye!=null)
                                      )}">
                            $('#btnSodoor').val('صدور الحاقیه خسارت دوم');
                        </c:if>
                        <c:if test="${darkhastBazkharid.khesaratII.elhaghiye!=null}">
                            $("#dakhastTransitionSelector option[value='690']").remove();
                            $("#dakhastTransitionSelector option[value='614']").remove();
                            $("#dakhastTransitionSelector option[value='622']").remove();
                            alertMessage("<p style='color: #ff0000;'>" + "الحاقیه مربوط به خسارات این درخواست صادر شده است،هم اکنون می توانید نسبت به ثبت وضعیت منتظر تایید نهایی پرونده خسارت اقدام نمایید." + "</p>");
                            $('#btnSodoor').attr("disabled", 'disabled');
                            $('#btnSodoor').hide();
                            <c:if test="${darkhastBazkharid.state.id==654}">
                                $('#dakhastTransitionSelector').append($("<option></option>").attr("value", '616').text("منتظر تاييد نهايي پرونده خسارت"));
                            </c:if>
                            <c:if test="${darkhastBazkharid.state.id==657}">
                                $('#send_request_btn').addClass('noAnyDisable');
                                $('#dakhastTransitionSelector').addClass('noAnyDisable');
                                $('#dakhastTransitionSelector').append($("<option></option>").attr("value", '636').text("منتظر تاييد نهايي پرونده خسارت"));
                            </c:if>
                        </c:if>
                    </c:if>
                    <c:if test="${darkhastBazkharid.khesaratII==null}">
                        <c:if test="${darkhastBazkharid.khesaratI.elhaghiye!=null ||
                                      (darkhastBazkharid.khesaratI.elhaghiye==null && darkhastBazkharid.khesaratI.khesaratType=='AMRAZ' && darkhastBazkharid.bimename.haveKhesaratAmraz)||
                                      (darkhastBazkharid.khesaratI.elhaghiye==null && darkhastBazkharid.khesaratI.khesaratType=='NAGHSOZV' && darkhastBazkharid.bimename.haveKhesaratNaghsOzv)
                                     }">
                            $("#dakhastTransitionSelector option[value='614']").remove();
                            $("#dakhastTransitionSelector option[value='690']").remove();
                            $("#dakhastTransitionSelector option[value='622']").remove();
                            alertMessage("<p style='color: #ff0000;'>" + "الحاقیه مربوط به خسارات این درخواست صادر شده است،هم اکنون می توانید نسبت به ثبت وضعیت منتظر تایید نهایی پرونده خسارت اقدام نمایید." + "</p>");
                            $('#btnSodoor').attr("disabled", 'disabled');
                            $('#btnSodoor').hide();
                            <c:if test="${darkhastBazkharid.state.id==654}">
                                $('#dakhastTransitionSelector').append($("<option></option>").attr("value", '616').text("منتظر تاييد نهايي پرونده خسارت"));
                            </c:if>
                            <c:if test="${darkhastBazkharid.state.id==657}">
                                $('#send_request_btn').addClass('noAnyDisable');
                                $('#dakhastTransitionSelector').addClass('noAnyDisable');
                                $('#dakhastTransitionSelector').append($("<option></option>").attr("value", '636').text("منتظر تاييد نهايي پرونده خسارت"));
                            </c:if>
                        </c:if>
                    </c:if>
                </c:if>

                $("ul.actionMessage span").each(function () {
                    alertMessage($(this).text());
                });
                var selectedTab = "${type}";
                if (selectedTab == "tabs-103")
                {
                    $("#tabs").children().removeClass("activesubmit");
                    $("#tab_103").addClass("activesubmit");
                    $('#tabformcontent').children().hide();
                    $('#content_103').show();
                }
                <c:if test="${darkhastBazkharid!=null && darkhastBazkharid.darkhastType=='KHESARAT' && (darkhastBazkharid.state==null || darkhastBazkharid.state.id==650 ||darkhastBazkharid.state.id==653 || darkhastBazkharid.state.id==600 || darkhastBazkharid.state.id==652|| darkhastBazkharid.state.id==648|| darkhastBazkharid.state.id==644|| darkhastBazkharid.state.id==645)}">
                    $('#content_16').hide();
                    $("#tabs").children().removeClass("activesubmit");
                    $("#tab_36").addClass("activesubmit");
//                    $('#tabformcontent').children().hide();

                    $('#content_36').show();
                </c:if>
                <%--<c:if test="${darkhastBazkharid!=null && darkhastBazkharid.darkhastType=='KHESARAT' && darkhastBazkharid.state.id==657}">--%>
                    <%--$("#tabs").children().removeClass("activesubmit");--%>
                    <%--$("#tab_16").addClass("activesubmit");--%>
<%--//                    $('#tabformcontent').children().hide();--%>
                    <%--$('#content_16').show();--%>
                <%--</c:if>--%>
                <c:if test="${darkhastTaghirat!=null && darkhastTaghirat.state.id==9180 && fn:length(listBimenameTaghirVM)>0}">
                    $("#tabs").children().removeClass("activesubmit");
                    $("#tab_18").addClass("activesubmit");
//                    $('#tabformcontent').children().hide();
                    $('.content').hide();
                    $('#content_18').show();
                </c:if>

                <c:if test="${darkhastBazkharid!=null && darkhastBazkharid.darkhastType=='KHESARAT' && (darkhastBazkharid.state.id==619 ||darkhastBazkharid.state.id==642)}">
                    $("#tabs").children().removeClass("activesubmit");
                    $("#tab_100").addClass("activesubmit");
//                    $('#tabformcontent').children().hide();
                    $('#content_100').show();
                </c:if>

                <c:if test="${darkhastBazkharid!=null && darkhastBazkharid.darkhastType=='KHESARAT' && (darkhastBazkharid.state.id == 655 || darkhastBazkharid.state.id == 656)}">
                    $("#tabs").children().removeClass("activesubmit");
                    $("#tab_43").addClass("activesubmit");
    //                    $('#tabformcontent').children().hide();
                    $('#content_43').show();
                </c:if>

                <c:if test="${darkhastBazkharid!=null && darkhastBazkharid.darkhastType=='KHESARAT'}">
                    $('#tab_110').hide()
                    $('#content_110').hide()
                </c:if>

                <%--if (${(khesaratAction && darkhastBazkharid==null  )})--%>
                <%--if (${(darkhastBazkharid!=null  )})--%>
                <%--{--%>
                    <%--&lt;%&ndash;$("#tabs").children().removeClass("activesubmit");&ndash;%&gt;--%>
                    <%--&lt;%&ndash;$("#tab_36").addClass("activesubmit");&ndash;%&gt;--%>
                    <%--&lt;%&ndash;$('#tabformcontent').children().hide();&ndash;%&gt;--%>
                    <%--&lt;%&ndash;$('#content_36').show();&ndash;%&gt;--%>
                <%--&lt;%&ndash;}&ndash;%&gt;--%>

                <%--&lt;%&ndash;if (${(khesaratAction && darkhastBazkharid != null)})&ndash;%&gt;--%>
                <%--&lt;%&ndash;{&ndash;%&gt;--%>
                    <%--&lt;%&ndash;$("#tabs").children().removeClass("activesubmit");&ndash;%&gt;--%>
                    <%--$("#tab_40").addClass("activesubmit");--%>
                    <%--$('#tabformcontent').children().hide();--%>
                    <%--$('#content_40').show();--%>
                <%--}--%>

                <%--if (${(darkhastBazkharid!=null && darkhastBazkharid.state.id == 642 )})--%>
                <%--{--%>
                    <%--$("#tabs").children().removeClass("activesubmit");--%>
                    <%--$("#tab_41").addClass("activesubmit");--%>
                    <%--$('#tabformcontent').children().hide();--%>
                    <%--$('#content_41').show();--%>
                <%--}--%>

            }


    );
</script>
<s:actionerror/>
<s:actionmessage/>
<p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
    <s:if test="%{darkhastTaghirat.state!=null}">
        <label style="float:right;">وضعیت درخواست: ${darkhastTaghirat.state.stateName}</label>
    </s:if>
    <s:elseif test="%{darkhastBazkharid.state!=null}">
        <label style="float:right;">وضعیت درخواست: ${darkhastBazkharid.state.stateName}</label>
    </s:elseif>
    <label style="float:right;margin-right:85px;">شماره بيمه‌نامه: ${bimename.shomare}</label>
    <%--<label style="float:right;margin-right:85px;">نام--%>
        <%--بيمه‌گذار: ${bimename.pishnehad.bimeGozar.shakhs.name} ${bimename.pishnehad.bimeGozar.shakhs.nameKhanevadegi}</label>--%>
    <%--<label style="float:left;">وضعیت بیمه نامه: ${bimename.state.stateName}</label>--%>
    <a id="savestarlinkid" href="javascript:void(0)"
       <c:if test="${darkhastBazkharid.state.id==1000}">onclick="goSave()"</c:if>
       <c:if test="${darkhastBazkharid.state.id!=1000}">onclick="sMainForm()"</c:if>
       class="tipsi ui-state-default"
       style="float:left;margin-left:2px" title="ذخیره">
        <span class="ui-icon ui-icon-disk" style="float:left;">&nbsp;</span>
        <span style="color:red" class="notSavedStar"></span>
    </a>

</p>
<div id="shakhsElements" class="vld" style="display:none;">
    <%@include file="/jsp/pishnahad/dialogForm/addShakhsForm.jsp" %>
</div>
<div id="shakhsHoghoghiElements" class="vld" style="display:none;">
    <%@include file="/jsp/pishnahad/dialogForm/addShakhsHoghoghiForm.jsp" %>
</div>
<div id="add_sabeghe_div" class="vld" style="display:none;">
    <%@include file="/jsp/pishnahad/dialogForm/addSavabegheBimei.jsp" %>
</div>
<div id="add_vaziateSalamatiKhanevadeyeBimeShode_div" class="vld" style="display:none;">
    <%@include file="/jsp/pishnahad/dialogForm/addVaziateSalamatiKhanevadeyeBimeShode.jsp" %>
</div>
<div id="add_EKASB_div" class="vld" style="display:none;">
    <%@include file="/jsp/pishnahad/dialogForm/addEstefadeKonandeganAzSarmayeBime.jsp" %>
</div>
<%@include file="/jsp/josteju/josteju.jsp" %>
<%@include file="/jsp/josteju/searchCity.jsp" %>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
<%--<c:set var="pishnehad" value="${bimename.pishnehad}"/>--%>
<c:set var="pishnehad" value="${darkhastTaghirat.newPishnehad}"/>
<%--<c:set var="estelam" value="${bimename.pishnehad.estelam}"/>--%>
<c:set var="estelam" value="${darkhastTaghirat.newPishnehad.estelam}"/>


<p style="direction:rtl;text-align:right;">
    <%--در این بخش شما می توانید درخواست بازخرید بیمه نامه خود را ایجاد نمایید. لطفا با دقت مواردی که از شما خواسته شده است را پاسخ دهید.--%>
</p>

<div id="tabcontainer">
    <div id="tabs">
        <c:if test="${darkhastBazkharid!=null || darkhastTaghirat!=null}">
            <a href='javascript:void(0);' type="button" id="tab_1" class="tabsbtn">اطلاعات عمومي
                پيشنهاد</a>
            <a href='javascript:void(0);' type="button" id="tab_2" class="tabsbtn"> مشخصات بیمه گذار </a>
            <a href='javascript:void(0);' type="button" id="tab_3" class="tabsbtn"> مشخصات بیمه شده </a>
            <a href='javascript:void(0);' type="button" id="tab_4" class="tabsbtn">مشخصات بیمه نامه</a>
            <a href='javascript:void(0);' type="button" id="tab_5" class="tabsbtn">مشخصات استفاده کنندگان</a>
            <a href='javascript:void(0);' type="button" id="tab_6" class="tabsbtn">سؤالات عمومی از بیمه شده</a>
            <a href='javascript:void(0);' type="button" id="tab_7" onclick="checkJensiat();" class="tabsbtn">وضعیت سلامتی بیمه شده</a>
            <a href='javascript:void(0);' type="button" id="tab_8" class="tabsbtn">وضعیت سلامتی خانواده بیمه شده</a>
            <a href='javascript:void(0);' type="button" id="tab_9" class="tabsbtn">سوابق بیمه ای</a>
            <a href='javascript:void(0);' type="button" id="tab_10" class="tabsbtn">توسط فروشنده بیمه تکمیل گردد</a>
        </c:if>
        <c:if test="${(darkhastBazkharid.darkhastType == DarkhastType.BAZKHARID || darkhastBazkharid.darkhastType == 'BAZKHARID') && (bimenameIsMafqud == 'on' || darkhastBazkharid.bimenameIsMafqud == 'on')}">
            <a href='javascript:void(0);' type="button" id="tab_15" class="tabsbtn specTabsbtn">اعلام مفقودی بیمه
                نامه</a>
        </c:if>
        <s:if test="%{(darkhastBazkharid!=null && (darkhastBazkharid.darkhastType.name().equals('TAGHIRKOD')||darkhastBazkharid.darkhastType.name().equals('TOZIH')))||darkhastTozih}">
            <a href='javascript:void(0);' type="button" id="tab_110" class="tabsbtn activesubmit">صدور الحاقیه</a>
        </s:if>
        <s:else>
        <%--<s:if test="%{((khesaratAction && (khesarat==null||khesarat.state==null)) || havaleHa=='null')}">--%>
            <%--<s:if test="%{(darkhastBazkharid==null )}">--%>
            <c:if test="${darkhastBazkharid!=null && darkhastBazkharid.darkhastType=='KHESARAT'}">
                <a href='javascript:void(0);' type="button" id="tab_36" class="tabsbtn specTabsbtn">
                    <%--<s:if test="%{havaleHa=='null'}">--%>
                        <%--ویرایش خسارت--%>
                    <%--</s:if>--%>
                    <%--<s:else>--%>
                        اطلاعات خسارت
                    <%--</s:else>--%>
                </a>
                <s:if test="%{darkhastBazkharid.state.id == 655 || darkhastBazkharid.state.id == 656}">
                    <a href='javascript:void(0);' type="button" id="tab_43" class="tabsbtn specTabsbtn">حواله خسارت</a>
                </s:if>
            </c:if>
            <%--</s:if>--%>
            <%--<s:if test="%{(darkhastBazkharid!=null && darkhastBazkharid.state.id <= 642 )}">--%>
                <%--<a href='javascript:void(0);' type="button" id="tab_36"--%>
                   <%--class="tabsbtn specTabsbtn">--%>
                        <%--ویرایش خسارت--%>
                <%--</a>--%>
            <%--</s:if>--%>
        <%--<s:if test="%{(khesaratAction && (khesarat==null||khesarat.state==null)) || havaleHa=='null'}">--%>
        <%--<a href='javascript:void(0);' type="button" id="tab_36"--%>
           <%--class="tabsbtn activesubmit">--%>
            <%--<s:if test="%{havaleHa=='null'}">--%>
            <%--ویرایش خسارت--%>
            <%--</s:if>--%>
            <%--<s:else>--%>
            <%--ثبت خسارت--%>
            <%--</s:else>--%>


        <%--<s:if test="%{(darkhastBazkharid!=null )}">--%>
                <%--<a href='javascript:void(0);' type="button" id="tab_40"--%>
                   <%--class="tabsbtn specTabsbtn">ضمائم خسارت</a>--%>
            <%--</s:if>--%>

            <%--<s:if test="%{(darkhastBazkharid!=null && darkhastBazkharid.state.id == 642 )}">--%>
                <%--<a href='javascript:void(0);' type="button" id="tab_41"--%>
                   <%--class="tabsbtn specTabsbtn">تخصیص به کارشناس خسارت</a>--%>
            <%--</s:if>--%>

            <%--<s:if test="%{(darkhastBazkharid!=null && darkhastBazkharid.state.id == 650 || darkhastBazkharid.state.id == 652 )}">--%>
                <%--<a href='javascript:void(0);' type="button" id="tab_110"--%>
                   <%--class="tabsbtn specTabsbtn">صدور نهایی الحاقیه</a>--%>
            <%--</s:if>--%>

            <%--<s:if test="%{(darkhastBazkharid!=null && (darkhastBazkharid.state.id == 655 || darkhastBazkharid.state.id == 656 ))}">--%>
                <%--<a href='javascript:void(0);' type="button" id="tab_43" class="tabsbtn specTabsbtn">حواله خسارت</a>--%>
            <%--</s:if>--%>
        <%--<c:if test="${(darkhastBazkharid.darkhastType == DarkhastType.BAZKHARID || darkhastBazkharid.darkhastType == 'BAZKHARID') && (bimenameIsMafqud == 'on' || darkhastBazkharid.bimenameIsMafqud == 'on')}">--%>
            <%--<a href='javascript:void(0);' type="button" id="tab_15" class="tabsbtn specTabsbtn">اعلام مفقودی بیمه--%>
                <%--نامه</a>--%>

        <c:if test="${darkhastTaghirat == null}">
            <a href='javascript:void(0);' type="button" id="tab_110" class="tabsbtn activesubmit">درخواست بهره مندی از
                منافع</a>
        </c:if>
        <%--<s:if test="%{(darkhastBazkharid!=null && (darkhastBazkharid.darkhastType.name().equals('TAGHIRKOD')||darkhastBazkharid.darkhastType.name().equals('TOZIH')))||darkhastTozih}">--%>
        <c:if test="${darkhastTaghirat != null}">
            <a href='javascript:void(0);' type="button" id="tab_110"
               class="tabsbtn activesubmit specTabsbtn">تغییرات</a>
            <a href='javascript:void(0);' type="button" id="tab_201" class="tabsbtn specTabsbtn">فرم های الحاقیه</a>
            <a href='javascript:void(0);' type="button" id="tab_202" class="tabsbtn specTabsbtn">ضمائم پیشنهاد</a>
        </c:if>
        <%--</s:if>--%>
        <%--<s:else>--%>
            <%--<c:if test="${(darkhastBazkharid.darkhastType == DarkhastType.BAZKHARID || darkhastBazkharid.darkhastType == 'BAZKHARID') && (bimenameIsMafqud == 'on' || darkhastBazkharid.bimenameIsMafqud == 'on')}">--%>
                <%--<a href='javascript:void(0);' type="button" id="tab_15" class="tabsbtn specTabsbtn">اعلام مفقودی بیمه--%>
                    <%--نامه</a>--%>
            <%--</c:if>--%>
            <%--<s:if test="%{(darkhastBazkharid!=null && (darkhastBazkharid.darkhastType.name.equals('TAGHIRKOD')||darkhastBazkharid.darkhastType.name.equals('TOZIH')))||darkhastTozih}">--%>
                <%--<a href='javascript:void(0);' type="button" id="tab_110" class="tabsbtn activesubmit">صدور الحاقیه</a>--%>
            <%--</s:if>--%>
            <%--<s:else>--%>
                <%--<c:if test="${darkhastTaghirat == null}">--%>
                    <%--<a href='javascript:void(0);' type="button" id="tab_110" class="tabsbtn activesubmit">درخواست بهره مندی از--%>
                        <%--منافع</a>--%>
                <%--</c:if>--%>
                <%--<c:if test="${darkhastTaghirat != null}">--%>
                    <%--<a href='javascript:void(0);' type="button" id="tab_110" class="tabsbtn activesubmit specTabsbtn">تغییرات</a>--%>
                    <%--<a href='javascript:void(0);' type="button" id="tab_201" class="tabsbtn specTabsbtn">فرم های الحاقیه</a>--%>
                    <%--<a href='javascript:void(0);' type="button" id="tab_202" class="tabsbtn specTabsbtn">ضمائم پیشنهاد</a>--%>
                <%--</c:if>--%>
            <%--</s:else>--%>
        </s:else>
        <c:if test="${darkhastBazkharid.darkhastType=='EBTAL'}">
            <a  href='javascript:void(0);' type="button" id="tab_17" class="tabsbtn specTabsbtn">معرفی نامه های پزشکی</a>
        </c:if>
        <%--<c:if test="${darkhastBazkharid == null}">--%>
        <%--<a href='javascript:void(0);' type="button" id="tab_2" class="tabsbtn specTabsbtn">ضمائم</a>--%>
        <%--</c:if>--%>
        <%--<c:if test="${darkhastBazkharid.state.id <= 10000}">--%>
        <s:if test="%{(darkhastBazkharid!=null && (darkhastBazkharid.darkhastType.name.equals('TAGHIRKOD')||darkhastBazkharid.darkhastType.name.equals('TOZIH')))||darkhastTozih}"></s:if>
        <s:else>
            <c:if test="${darkhastTaghirat!=null}">
                <a href='javascript:void(0);' type="button" id="tab_100" class="tabsbtn specTabsbtn">ضمائم الحاقیه</a>
            </c:if>
            <c:if test="${darkhastTaghirat==null}">
                <c:if test="${darkhastBazkharid!=null}">
                    <a href='javascript:void(0);' type="button" id="tab_202" class="tabsbtn specTabsbtn">ضمائم پیشنهاد</a>
                    <c:if test="${!noZamaem}">
                        <a href='javascript:void(0);' type="button" id="tab_100" class="tabsbtn specTabsbtn">ضمائم</a>
                    </c:if>
                </c:if>

            </c:if>

            <%--<c:if test="${darkhastTaghirat.state.id == 50000}">--%>
            <%--<a href='javascript:void(0);' type="button" id="tab_2" class="tabsbtn specTabsbtn">ضمائم</a>--%>
            <%--</c:if>--%>

                <c:if test="${darkhastBazkharid.state.id ==657 ||darkhastBazkharid.state.id ==657 || darkhastBazkharid.state.id == 650|| darkhastBazkharid.state.id == 9050 ||darkhastBazkharid.state.id ==645 ||darkhastBazkharid.state.id ==642 || (darkhastBazkharid.state.id == 1100 && darkhastBazkharid.karshenas == null)|| (darkhastBazkharid.state.id == 11070 && darkhastBazkharid.karshenas == null && !timeToElamMali)|| (darkhastBazkharid.state.id == 11050 && darkhastBazkharid.karshenas == null && !timeToElamMali) || darkhastBazkharid.state.id == 10050 || darkhastBazkharid.state.id == 10020 || (darkhastBazkharid.state.id == 11020 && !timeToElamMali)|| darkhastBazkharid.state.id == 12030 ||  darkhastTaghirat.state.id == 9030 || darkhastTaghirat.state.id == 9070 || darkhastTaghirat.state.id == 9140 || darkhastTaghirat.state.id == 9160 ||(darkhastBazkharid.state.id == 1450 && darkhastBazkharid.karshenas == null)|| darkhastBazkharid.state.id == 1020}">
                    <sec:authorize  ifNotGranted="ROLE_PAS_KARSHENAS,ROLE_KARSHENAS_KHESARAT,ROLE_NAMAYANDE">
                        <c:if test="${darkhastTaghirat!=null || darkhastBazkharid.darkhastType!='KHESARAT' || darkhastBazkharid.khesaratI.elhaghiye==null}">
                            <a href='javascript:void(0);' type="button" id="tab_101" class="tabsbtn specTabsbtn">تخصیص به کارشناس</a>
                        </c:if>
                    </sec:authorize>
                </c:if>
            <c:if test="${darkhastTaghirat.state.id == 9080 || darkhastTaghirat.state.id == 9140 || darkhastTaghirat.state.id == 9030 || darkhastTaghirat.state.id == 9160 || darkhastTaghirat.state.id == 9050 ||
                     (
                        darkhastBazkharid.state.id == 1020 || darkhastBazkharid.state.id == 1100 || darkhastBazkharid.state.id == 1200 || darkhastBazkharid.state.id == 1450 ||
                        (
                           (darkhastBazkharid.state.id == 654 || darkhastBazkharid.state.id == 658 || darkhastBazkharid.state.id == 657) &&
                           (
                               (    darkhastBazkharid.khesaratII==null && darkhastBazkharid.khesaratI.elhaghiye==null &&
                                    (
                                        (darkhastBazkharid.khesaratI.khesaratType=='AMRAZ' && !darkhastBazkharid.bimename.haveKhesaratAmraz)||
                                        (darkhastBazkharid.khesaratI.khesaratType=='NAGHSOZV' && !darkhastBazkharid.bimename.haveKhesaratNaghsOzv)||
                                        (darkhastBazkharid.khesaratI.khesaratType!='AMRAZ' && darkhastBazkharid.khesaratI.khesaratType!='NAGHSOZV')
                                    )
                               )||
                               (darkhastBazkharid.khesaratII!=null && darkhastBazkharid.khesaratII.elhaghiye==null)
                           )
                        )
                     )}">
                <a href='javascript:void(0);' type="button" id="tab_16" class="tabsbtn specTabsbtn">صدور نهایی الحاقیه</a>
            </c:if>
            <c:if test="${darkhastTaghirat.state.id == 9080 || darkhastTaghirat.state.id == 9140 || darkhastTaghirat.state.id == 9030 || darkhastTaghirat.state.id == 9160 || darkhastTaghirat.state.id == 9050  || darkhastTaghirat.state.id == 9180}">
                <a href='javascript:void(0);' type="button" id="tab_18" class="tabsbtn specTabsbtn">لیست بیمه نامه
                    ها</a>
            </c:if>
            <c:if test="${(darkhastBazkharid.state.id == 1200 || darkhastBazkharid.state.id == 11030|| darkhastBazkharid.state.id == 12040|| darkhastBazkharid.state.id == 12060)&&timeToElamMali==false}">
                <a style="display:none" href='javascript:void(0);' type="button" id="tab_102" class="tabsbtn specTabsbtn">نظر کارشناسی</a>
            </c:if>

            <c:if test="${darkhastBazkharid.state.id == 1300}">
                <a href='javascript:void(0);' type="button" id="tab_115" class="tabsbtn specTabsbtn">ضمائم درخواست</a>
            </c:if>
            <sec:authorize ifAnyGranted="ROLE_PAS_KARSHENAS_MASOUL,ROLE_NAMAYANDE,ROLE_PAS_KARSHENAS,ROLE_KARSHENAS_SODUR,ROLE_KARSHENAS_MASOUL,ROLE_RAEIS_SODUR,ROLE_KARSHENAS_BAYEGANI">
                <c:if test="${darkhastBazkharid!=null && darkhastBazkharid.darkhastType=='VAM'}">
                    <a href='javascript:void(0);' type="button" id="tab_116" class="tabsbtn specTabsbtn">بهره مندی از منافع</a>
                </c:if>
            </sec:authorize>
            <c:if test="${darkhastBazkharid.state.id == 1400|| darkhastBazkharid.state.id == 11060|| darkhastBazkharid.state.id == 12070 || darkhastBazkharid.state.id==10060}">
                <a href='javascript:void(0);' type="button" id="tab_105" class="tabsbtn specTabsbtn">فرم عدم تطابق
                    امضا</a>
            </c:if>
            <c:if test="${darkhastBazkharid.darkhastType=='VAM' && darkhastBazkharid.state.id >= 10080}">
                <a href='javascript:void(0);' type="button" id="tab_103" class="tabsbtn specTabsbtn">استعلام شرایط و صدور
                    وام</a>
            </c:if>
            <c:if test="${darkhastBazkharid.state.id == 11030|| darkhastBazkharid.state.id == 11090 || darkhastBazkharid.state.id == 11020 || darkhastBazkharid.state.id == 11050|| darkhastBazkharid.state.id == 11070}">
                <a href='javascript:void(0);' type="button" id="tab_104" class="tabsbtn specTabsbtn"
                   >ثبت
                    برداشت از اندوخته</a>
            </c:if>
            <c:if test="${timeToElamMali}">
                <a href='javascript:void(0);' type="button" id="tab_119" class="tabsbtn specTabsbtn">اعلام به مالی</a>
            </c:if>
            <c:if test="${darkhastBazkharid.state.id == 12090 || darkhastBazkharid.state.id == 12100 || darkhastBazkharid.state.id == 12160}">
                <a href='javascript:void(0);' type="button" id="tab_120" class="tabsbtn specTabsbtn"
                   onclick="mohasebateJaraemeVaam();">تسويه پيش از موعد وام</a>
            </c:if>
            <c:if test="${darkhastTaghirat.state.id == 9060}">
                <a href='javascript:void(0);' type="button" id="tab_11" class="tabsbtn specTabsbtn">ثبت نظر رئیس اداره
                    صدور</a>
            </c:if>
            <c:if test="${(darkhastTaghirat.state.id == 9120 || darkhastTaghirat.state.id == 9110)}">
                <a href='javascript:void(0);' type="button" id="tab_12" class="tabsbtn specTabsbtn">ثبت نظر پزشک</a>
            </c:if>
            <c:if test="${(darkhastTaghirat.state.id == 9080 || darkhastTaghirat.state.id == 9140)}">
                <%--<a href='javascript:void(0);' type="button" id="tab_13" class="tabsbtn specTabsbtn">صدور معرفی نامه--%>
                    <%--پزشکی</a>--%>
            </c:if>
            <sec:authorize
                    ifAnyGranted="ROLE_RAEIS_KHESARAT,ROLE_KARSHENAS_MASOUL_KHESARAT,ROLE_KARSHENAS_KHESARAT">
                <c:if test="${darkhastBazkharid.darkhastType=='KHESARAT'}">
                    <a href='javascript:void(0);' type="button" id="tab_106"
                       class="tabsbtn specTabsbtn">نظر کارشناسی</a>
                </c:if>
            </sec:authorize>
            <c:if test="${darkhastBazkharid!=null || darkhastTaghirat!=null}">
                <c:if test="${darkhastBazkharid.state.id != 10000 && !noZamaem}">
                    <a href='javascript:void(0);' type="button" id="tab_14" class="tabsbtn specTabsbtn">
    پیغام درخواست
</a>
                    <a href='javascript:void(0);' type="button" id="tab_1079" class="tabsbtn specTabsbtn">الحاقیه ها</a>
                </c:if>
            </c:if>
            <c:if test="${darkhastBazkharid.state.id == 10110}">
                <%--<a href='javascript:void(0);' type="button" id="tab_10110" class="tabsbtn specTabsbtn">دفترچه اقساط--%>
                    <%--وام</a>--%>
            </c:if>

                <sec:authorize ifNotGranted="ROLE_KARMOZD_NAMAYANDE">
                    <a href='javascript:void(0);' type="button" id="tab_1077" class="tabsbtn specTabsbtn">بیمه نامه</a>
                </sec:authorize>
            <c:if test="${((darkhastBazkharid!=null) && ((darkhastBazkharid.darkhastType=='KHESARAT')||(darkhast!=null && pishnehad != null && pishnehad.bimeShode.id != null && !khesaratAction))) || (darkhastTaghirat!=null && darkhastTaghirat.state.id==9180)}">
                <a style="display: none;" href='javascript:void(0);' type="button" id="tab_1078" class="tabsbtn specTabsbtn">پیغام پیشنهاد</a>

            </c:if>
        </s:else>
    </div>
    <div id="tabformcontent">
    <form id="mainForm" method="post" action="innerRegisterPishnahadeBimeOmreEnferadi.action">
        <div id="pish_container">
            <input type="hidden" name="pishnehad.id" value="${pishnehad.id}">
            <input type="hidden" name="darkhastTaghirat.id" value="${darkhastTaghirat.id}">
            <input type="hidden" name="darkhastBazkharid.id" value="${darkhastBazkharid.id}">
            <div style="display:none;" class=content id="content_1">
                    <%
                        request.setAttribute("pishnehad", pishnehadRun);
                    %>
                    <jsp:include page="/jsp/pishnahad/subForm/etelaateOmomiPishnehad.jsp"/>
            </div>
            <div style="display:none;" class=content id="content_2">
                <%@include file="/jsp/pishnahad/subForm/bimeGozar.jsp" %>
            </div>
            <div style="display:none;" class=content id="content_3">
                <%@include file="/jsp/pishnahad/subForm/bimeShode.jsp" %>
            </div>
            <div style="display:none;" class=content id="content_4">
                <%@include file="/jsp/pishnahad/subForm/bimeName.jsp" %>
            </div>
            <div style="display:none;" class=content id="content_5">
                <%@include file="/jsp/pishnahad/subForm/estefadeKonandeganAzSarmayeBime.jsp" %>
            </div>
            <div style="display:none;" class=content id="content_6">
                <%@include file="/jsp/pishnahad/subForm/soalateOmomiAzBimeShode.jsp" %>
            </div>
            <div style="display:none;" class=content id="content_7">
                <%@include file="/jsp/pishnahad/subForm/vaziateSalamatiBimeShode.jsp" %>
            </div>
            <div style="display:none;" class=content id="content_8">
                <%@include file="/jsp/pishnahad/subForm/vaziateSalamatiKhanevadeyeBimeShode.jsp" %>
            </div>
            <div style="display:none;" class=content id="content_9">
                <%@include file="/jsp/pishnahad/subForm/savabegheBimei.jsp" %>
            </div>
            <div style="display:none;" class=content id="content_10">
                <%@include file="/jsp/pishnahad/subForm/foroshande.jsp" %>
            </div>

        </div>
        </form>
        <c:if test="${darkhastBazkharid!=null && darkhastBazkharid.darkhastType=='KHESARAT'}">
            <div class="content" id="content_36" style="display:none;">
                <%@include file="/jsp/management/khesarat/page_khesarat.jsp" %>
            </div>
        <div class="content" id="content_106" style="display:none;">
            <c:if test="${darkhastBazkharid.darkhastType=='KHESARAT'}">
                <table id="tableshowingfishes" class="jtable" cellspacing="0" cellpadding="0" border="0"
                       style="width:80%;border-spacing:1px;margin-left:auto;margin-right:auto;" dir="rtl">
                    <tbody>
                    <tr>
                        <th>
                            پیام
                        </th>
                    </tr>

                    <c:forEach var="db" items="${darkhastBazkharid.nazarKarshenasKhesaratBeTafkik}">
                        <tr>
                            <td>
                                    ${db}
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <table>
                    <tr>
                        <td>درج پیام</td>
                        <td><textarea id="nazar_karshenas_khesarat" rows="2" cols="80" ></textarea></td>
                    </tr>
                    <td>با تغییر وضعیت پیام به طور خودکار ذخیره می گردد.</td>
                </table>
            </c:if>
        </div>
            <s:if test="%{darkhastBazkharid.state.id == 655 || darkhastBazkharid.state.id == 656}">
                <div style="display:none;" class=content id="content_43">
                    <%@include file="/jsp/management/khesarat/page_khesaratHavale.jsp" %>
                    <%--<%@include file="/jsp/transitionForms/havaleKhesarat.jsp" %>--%>
                </div>
            </s:if>
        </c:if>
        <s:if test="%{(darkhastBazkharid!=null && (darkhastBazkharid.darkhastType.name().equals('TAGHIRKOD')||darkhastBazkharid.darkhastType.name().equals('TOZIH')))||darkhastTozih}">
            <div class=content id="content_110">
                <%@include file="/jsp/transitionForms/sodooreElhaghie.jsp" %>
            </div>
        </s:if>
        <s:else>
            <%--<s:if test="%{(darkhastBazkharid!=null)}">--%>
                <div class=content id="content_110">
                    <%--&lt;%&ndash;<%@include file="/jsp/pishnahad/page_sodurElhaghieTaghirKod.jsp" %>&ndash;%&gt;--%>
                    <%--<%@include file="/jsp/transitionForms/sodooreElhaghie.jsp" %>--%>
                <%--</div>--%>
            <%--</s:if>--%>
            <%--<s:else>--%>
                <%--<!--todo: mainForm4DArkhast form commented. . . -->--%>
                <%--&lt;%&ndash;<form class="mainFrame" id="mainForm4Darkhast" method="post" action="/sabteDarkhasteBazkharid">&ndash;%&gt;--%>
                    <%--<div class=content id="content_110">--%>

                        <input type="hidden" name="darkhast.darkhastType" value="${darkhast.darkhastType}"/>

                        <c:if test="${darkhastTaghirat == null}">
                            <%@include file="/jsp/bimename/subForm/formeDarkhast.jsp" %>
                        </c:if>
                        <%--<c:if test="${darkhastTaghirat != null}">--%>
                            <%--<%@include file="/jsp/bimename/darkhastTaghirat/darkhasteElhaghieTaghirat.jsp" %>--%>
                        <%--</c:if>--%>

                    <%--</div>--%>
                    <c:if test="${darkhastTaghirat != null}">
                        <%@include file="/jsp/bimename/darkhastTaghirat/darkhasteElhaghieTaghirat.jsp" %>
                            <%--<div class=content id="content_201" style="display:none;">--%>
                            <%--<%@include file="/jsp/bimename/darkhastTaghirat/subforms/taghiratSummary.jsp" %>--%>
                            <%--</div>--%>
                    </c:if>
                    <%--<c:if test="${darkhastTaghirat != null || darkhastBazkharid != null}">--%>
                            <%--<div class=content id="content_202" style="display:none;">--%>
                                <%--<%--%>
                                    <%--request.setAttribute("pishnehadRun", pishnehadRun);--%>
                                <%--%>--%>
                                <%--<jsp:include page="/jsp/pishnahad/subForm/listeZamaem.jsp"/>--%>
                            <%--</div>--%>
                    <%--</c:if>--%>
                    <%--<div style="display: none;" class=content id="content_15">--%>
                        <%--<%@include file="/jsp/bimename/subForm/mafqudi.jsp" %>--%>
                    <%--</div>--%>
                <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
            <%--</s:else>--%>
                </div>
            <c:if test="${darkhastTaghirat != null}">
                <div class=content id="content_201" style="display:none;">
                    <%@include file="/jsp/bimename/darkhastTaghirat/subforms/taghiratSummary.jsp" %>
                </div>
            </c:if>
            <c:if test="${darkhastTaghirat != null || darkhastBazkharid != null}">
                <div class=content id="content_202" style="display:none;">
                    <%
                        request.setAttribute("pishnehadRun", pishnehadRun);
                    %>
                    <jsp:include page="/jsp/pishnahad/subForm/listeZamaem.jsp"/>
                </div>
            </c:if>
            <div style="display: none;" class=content id="content_15">
                <%@include file="/jsp/bimename/subForm/mafqudi.jsp" %>
            </div>
            <%--</form>--%>
        </s:else>

        <div style="display:none;" class="content" id="content_17">
            <%@include file="/jsp/pishnahad/subForm/listMoarefiname.jsp" %>
        </div>
        <div style="display: none;" class=content id="content_100">
            <%@include file="/jsp/bimename/subForm/zamaemDarkhast.jsp" %>
        </div>
            <div style="display: none;" class=content id="content_101">
                <%@include file="/jsp/bimename/subForm/takhsisBeKarshenas.jsp" %>
            </div>
        <div style="display: none;" class=content id="content_102">
            <%@include file="/jsp/bimename/subForm/karshenasi.jsp" %>
        </div>
        <div style="display: none;" class=content id="content_115">
            <%@include file="/jsp/bimename/subForm/zamaemDarkhast.jsp" %>
        </div>
        <sec:authorize
                ifAnyGranted="ROLE_PAS_KARSHENAS_MASOUL,ROLE_NAMAYANDE,ROLE_PAS_KARSHENAS,ROLE_KARSHENAS_SODUR,ROLE_KARSHENAS_MASOUL,ROLE_RAEIS_SODUR,ROLE_KARSHENAS_BAYEGANI">
            <c:if test="${darkhastBazkharid!=null && darkhastBazkharid.darkhastType=='VAM'}">
                <div style="display: none;" class=content id="content_116">
                    <%@include file="/jsp/user/page_bahremandiAzManafe.jsp" %>
                </div>
            </c:if>
        </sec:authorize>
        <div style="display: none;" class=content id="content_105">
            <%@include file="/jsp/bimename/subForm/adameTataaboghEmza.jsp" %>
        </div>
        <c:if test="${darkhastBazkharid.darkhastType=='VAM' && darkhastTaghirat.state.id != 9010 && darkhastTaghirat.state.id != 9130}">
            <div style="display: none;" class=content id="content_103">
                <%@include file="/jsp/bimename/subForm/formeMianiVam.jsp" %>
            </div>
        </c:if>
        <c:if test="${(darkhastBazkharid.darkhastType=='VAM' && darkhastTaghirat.state.id != 9010 && darkhastTaghirat.state.id != 9130) || darkhastBazkharid.darkhastType=='BARDASHT_AZ_ANDOKHTE'}">
            <div style="display: none;" class=content id="content_119">
                <%@include file="/jsp/bimename/subForm/elamBeMali.jsp" %>
            </div>
        </c:if>
        <c:if test="${darkhastBazkharid.state.id == 11030|| darkhastBazkharid.state.id == 11090 || darkhastBazkharid.state.id == 11020 || darkhastBazkharid.state.id == 11050|| darkhastBazkharid.state.id == 11070}">
            <div class="content" style="display: none;" id="content_104">
                <%@include file="/jsp/bimename/subForm/bardashtAzAndukhte.jsp" %>
            </div>
        </c:if>
        <c:if test="${darkhastBazkharid.state.id == 12090 || darkhastBazkharid.state.id == 12100 || darkhastBazkharid.state.id == 12160}">
        <div style="display: none;" class=content id="content_120">
            <%@include file="/jsp/bimename/subForm/tasvieyePishAzMoedeVam.jsp" %>
        </div>
        </c:if>
        <div style="display: none;" class=content id="content_11">
            <%@include file="/jsp/bimename/darkhastTaghirat/subforms/taeedRaeis.jsp" %>
        </div>
        <div style="display: none;" class=content id="content_12">
            <%@include file="/jsp/bimename/darkhastTaghirat/subforms/taeedPezeshki.jsp" %>
        </div>
        <%--<div style="display: none;" class=content id="content_13">--%>
            <%--<%@include file="/jsp/bimename/darkhastTaghirat/subforms/sodourMoarefiname.jsp" %>--%>
        <%--</div>--%>
        <c:if test="${darkhastBazkharid!=null || darkhastTaghirat!=null}">
            <div style="display:none;" class="content" id="content_14">
                    <%--<%@include file="/jsp/bimename/subForm/tarikhcheDarkhast.jsp"%>--%>
                <%
                    request.setAttribute("darkhastBazkharid", darkhastRun);
                %>
                <jsp:include page="/jsp/bimename/subForm/tarikhcheDarkhast.jsp"/>
            </div>
        </c:if>
        <%--<div style="display: none;" class=content id="content_10110">--%>
            <%--<%@include file="/jsp/bimename/subForm/formeMianiVam_daftarche.jsp" %>--%>
        <%--</div>--%>
        <sec:authorize ifNotGranted="ROLE_KARMOZD_NAMAYANDE">
        <div style="display:none;" class="content" id="content_1077">
                    <%@include file="/jsp/bimename/subForm/bimenameSummary.jsp" %>
        </div>
        </sec:authorize>
        <div style="display:none;" class="content" id="content_1078">
                <%
                    request.setAttribute("pishnehadRun", pishnehadRun);
                %>
                <jsp:include page="/jsp/pishnahad/subForm/tarikhchePishnehad.jsp"/>
        </div>
        <div style="display:none;" class="content" id="content_1079">
            <jsp:include page="/jsp/user/page_kartablElhaghie.jsp"/>
        </div>
        <c:if test="${darkhastTaghirat.state.id == 9080 || darkhastTaghirat.state.id == 9140 || darkhastTaghirat.state.id == 9030 || darkhastTaghirat.state.id == 9160 || darkhastTaghirat.state.id == 9050 ||
                     (
                        darkhastBazkharid.state.id == 1020 || darkhastBazkharid.state.id == 1100 || darkhastBazkharid.state.id == 1200 || darkhastBazkharid.state.id == 1450 ||
                        (
                           (darkhastBazkharid.state.id == 654 || darkhastBazkharid.state.id == 658 || darkhastBazkharid.state.id == 657) &&
                           (
                               (    darkhastBazkharid.khesaratII==null && darkhastBazkharid.khesaratI.elhaghiye==null &&
                                    (
                                        (darkhastBazkharid.khesaratI.khesaratType=='AMRAZ' && !darkhastBazkharid.bimename.haveKhesaratAmraz)||
                                        (darkhastBazkharid.khesaratI.khesaratType=='NAGHSOZV' && !darkhastBazkharid.bimename.haveKhesaratNaghsOzv)||
                                        (darkhastBazkharid.khesaratI.khesaratType!='AMRAZ' && darkhastBazkharid.khesaratI.khesaratType!='NAGHSOZV')
                                    )
                               )||
                               (darkhastBazkharid.khesaratII!=null && darkhastBazkharid.khesaratII.elhaghiye==null)
                           )
                        )
                     )}">
        <div style="display: none;" class=content id="content_16">
            <%@include file="/jsp/transitionForms/sodooreElhaghie.jsp" %>
        </div>
        </c:if>
        <c:if test="${darkhastTaghirat.state.id == 9080 || darkhastTaghirat.state.id == 9140 || darkhastTaghirat.state.id == 9030
         || darkhastTaghirat.state.id == 9160 || darkhastTaghirat.state.id == 9050 || darkhastTaghirat.state.id == 9180}">
        <div style="display: none;" class=content id="content_18">
            <%@include file="/jsp/bimename/darkhastTaghirat/subforms/listBimenameTaghirat.jsp" %>
        </div>
    </c:if>
    </div>
</div>
<br class="clear"/>

<div id="sabteDarkhastButtons">
    <c:if test="${darkhastBazkharid.id == null && darkhastTaghirat == null}">
    <%--<c:if test="${khesaratAction != null}">--%>
        <%--<input type="button"  style="float:left;margin:2px" onclick="sbmt();" value="ثبت درخواست" />--%>
    <%--</c:if>--%>
    <%--<c:if test="${khesaratAction == null}">--%>
        <input type="button" value="ثبت درخواست" style="float:left;margin:2px" onclick="submitDarkhast();"/>
                           <%--loginUser?selectedTab=tabs-4--%>
    <%--</c:if>--%>
        <input type="button" onclick="window.location='/loginUser'" value="بازگشت"
               style="float:left;margin:2px"/>
    </c:if>
</div>
<div>&nbsp;</div>
<c:if test="${allowedTransitions != null}">
    <div id="controllerbuttons" style="float: left;">
            <%--<c:if test="${darkhastBazkharid.state.id==10000}">
                <input type="button"
                       onclick="window.open('/printeVam.action?pishnehadReport.darkhastBazkharid.id=${darkhastBazkharid.id}');"
                       value="پرینت">
            </c:if>--%>
        <input type="button" class="noAnyDisable" onclick="window.location='/loginUser?selectedTab=tabs-4'" value="بازگشت" style="float:left;"/>


        <select style="float:left; margin-left: 10px;" id="dakhastTransitionSelector" name="transitionSelector">
                <c:forEach var="transition" items="${allowedTransitions}" varStatus="i"  >
                    <option value='<c:out value="${transition.id}" />'><c:out value="${transition.transitionName}"/></option>
                </c:forEach>
        </select>

            <input type="button" style="float:left;margin-left:2px" id="send_request_btn" value="ارسال" onclick="submitTransitionForDarkhast();"/>


    </div>
    <div id="printbuttons" style="float: right; padding-right: 20px;">
        <c:if test="${darkhastBazkharid.state.id == 1500}">
            <input type="button" value="پرینت درخواست صدور چك بازخريدي"
                   onclick="window.open('/printeDarkhasteSodoreCheckeBazkharidi?pishnehadReport.darkhastBazkharid.id=${darkhastBazkharid.id}');">
            <input type="button" value="پرینت حواله خسارت"
                   onclick="window.open('/printeHavaleKhesarat?pishnehadReport.darkhastBazkharid.id=${darkhastBazkharid.id}');">
        </c:if>
    </div>
</c:if>
<br class="clear"/>

<div>&nbsp;</div>
<form action="/makeDarkhastTransition" id="darkhastTransitionForm" method="post" accept-charset="UTF-8">
    <%--<input type="hidden" name="darkhastBazkharid.id" id="darkhast_bazkharidId" value="${khesarat.bimename.darkhastBazkharid.id}"/>--%>
        <input type="hidden" name="darkhastBazkharid.id" id="darkhast_bazkharidId"
               value="<c:out value='${darkhastBazkharid.id}'/>"/>
    <input type="hidden" name="transitionId" id="darkhast_transitionId"/>
    <%--<input type="hidden" name="khesarat.id" value="${khesarat.id}" id="khesarat_transitionId"/>--%>
    <input type="hidden" name="nazarKarshenasKhesaratBeTafkik" id="lognazarKhesarat"/>
    <input type="hidden" name="logmessage" id="log_message_darkhast"/>
</form>
<form action="/makeDarkhastTaghiratTransition" id="darkhastTaghiratTransitionForm" method="post"
      accept-charset="UTF-8">
    <input type="hidden" name="darkhastTaghirat.id" id="darkhast_taghiratId"
           value="<c:out value='${darkhastTaghirat.id}'/>"/>
    <%--<input type="hidden" name="darkhastBazkharid.id" id="darkhast_bazkharidId10" value="${darkhastBazkharid.id}"/>--%>
    <%--<input type="hidden" name="khesarat.id" id="khesarat_form_id"--%>
           <%--value="${khesarat.id}"/>--%>
    <input type="hidden" name="transitionId" id="darkhasttaghirat_transitionId"/>
    <input type="hidden" name="logmessage" id="log_message_darkhast_taghirat"/>
</form>


<div id="pdarkhast_popup" style="display:none;">
    <div id="new_khesarat" style="direction: rtl;  color: #d93f3f;"><br/></div>

    <textarea rows="5" cols="32" name="logmessage" id="darkhastloggmessage"></textarea>
    <div id="bahreMandi" style="direction: rtl;  color: #d93f3f;"><br/></div>
</div>
<div id="jarime_holder" style="display: none;"/>

<script type="text/javascript">

    <c:if test="${darkhast.darkhastType=='VAM' || darkhastBazkharid.darkhastType=='VAM'}">
    <%
        boolean isViewAlert=false;
        String msg="";
        int i=1;
        Bimename theBimename = request.getAttribute("bimename")!=null?(Bimename)request.getAttribute("bimename"):(Bimename)request.getAttribute("darkhastBazkharid.bimename");
        if (DateUtil.isGreaterThan(DateUtil.addYear(theBimename.getTarikhShorou(), 2), DateUtil.getCurrentDate()))
        {
            msg+=i+") ";i++;
            msg+="برای ثبت درخواست وام باید حداقل دو سال از شروع بیمه نامه سپری شده باشد.";
            msg+="<br/><br/>";
            isViewAlert=true;
        }

        if(theBimename.getHasGhestMoavagh())
        {
            msg+=i+") ";i++;
            msg+="بيمه‌نامه داراي بدهي معوق است، براي دريافت وام ابتدا اقساط معوق را تسويه نماييد.";
            msg+="<br/><br/>";
            isViewAlert=true;
        }

        int numberOfVaams=0;
        for (DarkhastBazkharid theDarkhast : theBimename.getAllDarkhasts())
        {
            if (theDarkhast.getState()==null || theDarkhast.getState().getId().equals(Constant.VAM_ENSERAF)) continue;
            if (theDarkhast.getDarkhastType()!=null && theDarkhast.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM))
            {
                numberOfVaams++;
//                        if(theDarkhast.getState().getId().intValue()!=Constant.VAM_FINAL_STATE.intValue()){
//                            hasUndoneVam = true;
//                        }
//                        if(theDarkhast.getGhestBandi()!=null)
//                        for (Ghest g : theDarkhast.getGhestBandi().getGhestList()) {
//                            if (!g.getCredebit().getRemainingAmount().equals("0")) {
//                                hasUndoneVam = true;
//                                break;
//                            }
//                        }
            }
        }

        if (numberOfVaams > 2)
        {
            msg+=i+") ";i++;
            msg+="تعداد دفعات مجاز دريافت وام براي هر بيمه‌نامه حداكثر دو بار مي‌باشد.";
            msg+="<br/><br/>";
            isViewAlert=true;
        }

        if(isViewAlert)
        {
    %>
            $(document).ready(function ()
            {

                alertMessageWithTitle("<p style='color: #d93f3f;'> "+ "<%=msg%>" +"</p>","<b>"+"هشــدار"+"</b>");
                <c:if test="${darkhastBazkharid.state.id==10090}">
                $('#bahreMandi').html("<%=msg%>");
                </c:if>
            });
    <%
        }
    %>


    </c:if>
    <c:if test="${darkhastBazkharid.state.id==1000 && darkhastBazkharid.darkhastType=='BAZKHARID' && darkhastBazkharid.bimenameIsMafqud == 'on'}">
        $(document).ready(function(){alertMessage("فرم اعلام مفقودی را (از تب اعلام مفقودی بیمه نامه) چاپ گرفته و سپس در تب ضمائم با عنوان فرم اعلام مفقودی ضمیمه کنید.");});
    </c:if>
    <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
        <c:if test="${darkhastBazkharid.state.id==1400}">
            $(document).ready(function(){alertMessage("فرم تطابق امضا را تكميل وچاپ گرفته سپس درقسمت ضمائم آپلود کنید");});
        </c:if>
    </sec:authorize>

    $(document).ready(function ()
    {
                                   <c:if test="${darkhastBazkharid.state.id==640}">
                                       $('#new_khesarat').html('<p dir="rtl" style="text-align: justify;">توجه داشته باشيد با ثبت پرونده خسارت، تا زمان اتمام بررسي خسارت امكان انجام عمليات ديگري  براي اين بيمه نامه وجود نخواهد داشت. آيا از ادامه فرايند مطمئن هستيد؟</p><hr/>');
                                   </c:if>
    })
    function goSave()
    {
        $("#mainForm4Darkhast").append('<input type="hidden" name="darkhastBazkharid.id" value="' + ${darkhastBazkharid.id} + '" />')
        $("#mainForm4Darkhast").attr("action", "saveBazkharidEbtalInfo.action") ;

        var form = document.getElementById('mainForm4Darkhast');
        form.submit();
    }

    <c:if test="${backfromupload =='true'|| darkhastBazkharid.state.id==640 }">
    $('.tabsbtn').removeClass('activesubmit');
    $('#tab_100').addClass('activesubmit');
    $('.content').hide();
    $('#content_100').show();
    </c:if>
    <c:if test="${pishnehad.state.id >= 10}">
        $("#tab_1078").show();
    </c:if>
    <c:if test="${timeToElamMali && (darkhastBazkharid.state.id==11020 || darkhastBazkharid.state.id==11030|| darkhastBazkharid.state.id==11080|| darkhastBazkharid.state.id==11070)}">
    $('.tabsbtn').removeClass('activesubmit');
    $('.content').hide();
    $('#tab_119').addClass('activesubmit');
    $('#content_119').show();
    </c:if>


    <sec:authorize ifAnyGranted="ROLE_PAS_KARSHENAS">
    $("#tab_101").hide();
    </sec:authorize>


    <c:set var="shakhsHoghoghi" value="<%=Shakhs.BimeGozarType.HOGHOGHI%>"/>
    <c:if test="${pishnehad.bimeGozar.shakhs.type==shakhsHoghoghi}">
    disableHaghighiElements();
    </c:if>
    <c:if test="${pishnehad.bimeGozar.shakhs.type!=shakhsHoghoghi}">
    enableHaghighiElements();
    </c:if>
    $(document).ready(function () {
        <c:if test="${darkhastBazkharid.khesaratI.elhaghiye != null || (darkhastBazkharid.khesaratII != null && darkhastBazkharid.khesaratII.elhaghiye != null)}">
            disablePishnehadTabs("content_36");
        </c:if>
        <sec:authorize ifAnyGranted="ROLE_PAS_KARSHENAS,ROLE_PAS_KARSHENAS_MASOUL,ROLE_PAS_RAEIS">
            <c:if test="${darkhastBazkharid.state!=null}">
                disablePishnehadTabs("content_36") ;
            </c:if>
        </sec:authorize>
        <c:if test="${readOnlyMode}">
            disablePishnehadTabs("tabformcontent");
            disablePishnehadTabs("controllerbuttons")
        </c:if>
        checkJensiat();
        <c:if test="${(darkhastTaghirat.state.id != 9010 && darkhastTaghirat.state.id != 9130 && darkhastTaghirat.state.id != 9080 &&
                        darkhastTaghirat.state.id != 9050 && darkhastTaghirat.state.id != 9140 && darkhastTaghirat.state.id != 9040 && darkhastTaghirat.state.id != 9150 &&
                        darkhastTaghirat.state.id != 9030 && darkhastBazkharid.state.id != 1000)}">

        disablePishnehadTabs("pish_container");
        $("#savestarlinkid").hide();
        </c:if>
        <c:if test="${darkhastBazkharid!=null && darkhastBazkharid.darkhastType=='KHESARAT' && darkhastBazkharid.state==null}">
            disablePishnehadTabs("pish_container");
            $("#savestarlinkid").hide();
        </c:if>
        <c:if test="${darkhastBazkharid.shomareBardashtAzAndukhte!=null}">
            disablePishnehadTabs("content_104");
        </c:if>
    });
    function openDialogueForDarkhast(theId, theForm) {
        $('#pdarkhast_popup').dialog({
            modal:true,
            width:260,
            resizable:false,
            closeText:"",
            title:"گذاشتن پیغام",
            buttons:{
                "بستن":function () {
                    $(this).dialog("close");
                    return false;
                },
                "انجام":function () {
                    $('#' + theId).val($('#darkhastloggmessage').val());
                    $('#lognazarKhesarat').val($('#nazar_karshenas_khesarat').val());
                    $(this).dialog("close");
                    $("#" + theForm).append(document.getElementById("lognazarKhesarat"));
                    $("#" + theForm).submit();
                    return true;
                }
            }
        });
    }

    function openDialogBoxAndSubmitTo(theId, theForm) {
    var stateBegin = "${darkhastTaghirat.state.stateName}";
    if(${darkhastBazkharid.state!=null})
        var stateBegin = "${darkhastBazkharid.state.stateName}";
    var stateFinish = $("#dakhastTransitionSelector option:selected").text();
    if(${darkhastTaghirat.state.id==9160} || ${darkhastTaghirat.state.id==9080} || ${darkhastTaghirat.state.id==9030} || ${darkhastTaghirat.state.id==9140} || ${darkhastTaghirat.state.id==9050})
    {
        stateFinish = "صدور نهایی الحاقیه";
    }
    <c:if test="${darkhastBazkharid.darkhastType=='TAGHIRKOD'}">
        stateBegin = "الحاقیه موقت"
        stateFinish = "الحاقیه دائم";
    </c:if>
    $('#pdarkhast_popup').dialog({
        modal:true,
        width:300,
        resizable:false,
        closeText:"",
        title:"تغییر وضعیت از " + stateBegin + " به " + stateFinish,
        buttons:{
            "بستن":function () {
                $(this).dialog("close");
                return false;
            },
            "انجام":function () {
                $('#' + theId).val($('#loggmessage').val());
                $(this).dialog("close");
                if (${darkhastTaghirat.state.id==9160} || ${darkhastTaghirat.state.id==9080} || ${darkhastTaghirat.state.id==9030} || ${darkhastTaghirat.state.id==9140} || ${darkhastTaghirat.state.id==9050})
                {
                    var input = document.createElement('input');
                    input.type = 'hidden';
                    input.value = '';
                    input.name = 'infoElhaghie[0]';
                    $('#' + theForm).append(input);
                    <c:forEach var="row" items="${listBimenameTaghirVM}" varStatus="i">
                        $('#mod_elhaghie_info'+${i.index+1}).buttonset().buttonset("enable");
                        $('#' + theForm).append(document.getElementById("elhaghie_info_subject${i.index+1}"));
                        $('#' + theForm).append(document.getElementById("elhaghie_info_desc${i.index+1}"));
                        $('#' + theForm).append(document.getElementById("elhaghie_info_date${i.index+1}"));
                        $('#' + theForm).append(document.getElementById("mod_elhaghie_info_new${i.index+1}"));
                        $('#' + theForm).append(document.getElementById("mod_elhaghie_info_current${i.index+1}"));
                    </c:forEach>
                }
                $('#' + theForm).submit();
                return true;
            }
        }
    });
}
<c:if test="${darkhastBazkharid.state.id==1010 && darkhastBazkharid.darkhastType=='EBTAL'}">
    $(document).ready(function(){alertMessage('در صورتیکه مدارک شما ظرف 10 روز آینده به دست اداره خدمات پس از صدور نرسد درخواست شما منقضی شده و باطل می گردد ');});
</c:if>

    function submitTransitionForDarkhast() {
        var stateBegin = "${darkhastTaghirat.state.stateName}";
        var stateFinish = $("#dakhastTransitionSelector option:selected").text();
        var trans=$("#dakhastTransitionSelector option:selected").val();
        var checker = false;
        <c:if test="${darkhastTaghirat == null}">
            <c:if test="${darkhastBazkharid != null}">
                stateBegin = "${darkhastBazkharid.state.stateName}";
                if(trans==1100)
                {
                    <c:if test="${darkhastBazkharid.state.id==1000}">
                        stateFinish = "درخواست جدید";
                    </c:if>
                }
                <c:if test="${darkhastBazkharid.darkhastType=='EBTAL' || darkhastBazkharid.darkhastType=='BAZKHARID'}">
                    <c:if test="${darkhastBazkharid.state.id==1000}">
                        if(trans==null || trans!=1110)
                            stateFinish = "درخواست جدید";
                        else if(trans==1110)
                            stateFinish ="انصراف از درخواست";
                    </c:if>
                </c:if>
                <c:if test="${darkhastBazkharid.darkhastType=='BARDASHT_AZ_ANDOKHTE'}">
                    <c:if test="${darkhastBazkharid.state.id==11000}">
                    if (trans == null || trans != 11010)
                        stateFinish = "درخواست برداشت جدید"
                    else if (trans == 11010)
                    {
                        stateFinish = "انصراف از درخواست";
                        checker=true;
                    }

                    </c:if>
                </c:if>
                <c:if test="${darkhastBazkharid.darkhastType=='VAM'}">
                    <c:if test="${darkhastBazkharid.state.id==10000}">

                    if (trans!=null && trans == 10014)
                    {
                        stateFinish = "انصراف از درخواست وام";
                        checker =true;
                    }

                    if (trans == null || trans == 10001)
                    {
                        stateFinish = "درخواست وام جدید"
                    }
                    </c:if>
                </c:if>
            </c:if>
        </c:if>


         <%--window.alert(${darkhastTaghirat.state.id})--%>
        <%--<c:if test="${(darkhastTaghirat.state.id==9080)} ">--%>
        <%--if(${darkhastTaghirat.state.id==9080})    {--%>
            <%--window.alert(${darkhastTaghirat.state.id});var stateFinish="صدور نهایی الحاقیه"    ;    }--%>

    <c:if test="${darkhastTaghirat != null}">
        if ($("#dakhastTransitionSelector").val() == 9017) {
            $('.tabsbtn').removeClass('activesubmit');
            $('#tab_101').addClass('activesubmit');
            submitTransitionForDarkhast();
            $("#tab_101").show();
            $('.content').hide();
            $('#content_101').show();
        }
    </c:if>
        if($("#dakhastTransitionSelector").val() == 1110)
        {
            checker =true;
        }
//        if ($("#dakhastTransitionSelector").val() == 9001 || $("#dakhastTransitionSelector").val() == 9004) {
    <c:if test="${darkhastTaghirat != null}">
        <c:if test="${(darkhastTaghirat.zamaemDarkhast.fileKollieId!=null)}">
            checker = true;
        </c:if>
    </c:if>
//        }
        <c:if test="${darkhastBazkharid != null}">
            <c:if test="${darkhastBazkharid.zamaemDarkhast.fileDarkhastBahremandiId != null}">
                checker = true;
            </c:if>
        </c:if>
        if ($("#dakhastTransitionSelector").val() == 9002) {
            checker = true;
        }
//        if ($("#dakhastTransitionSelector").val() >= 600 || $("#dakhastTransitionSelector").val() <= 620) {
//            checker = true;
//        }
        <c:if test="${darkhastBazkharid.darkhastType=='BAZKHARID' && darkhastBazkharid.bimenameIsMafqud == 'on'}">
            <c:if test="${darkhastBazkharid.zamaemDarkhast.fileElameMafghudiId == null}">
                checker = false;
            </c:if>
        </c:if>
        <c:if test="${darkhastBazkharid.darkhastType=='KHESARAT'}">
            <c:if test="${darkhastBazkharid.zamaemDarkhast.fileElamKhesaratId != null}">
                checker = true;
            </c:if>
//            if(trans==600)
//            {
                <%--<c:if test="${darkhastBazkharid.zamaemDarkhast.fileElamKhesaratId == null}">--%>
//                    checker = false;
                <%--</c:if>--%>
//            }
        if(trans== 32213265|| trans== 619)
        {$('#new_khesarat').hide();
            checker=true;}
        </c:if>
        if(!checker){
            <c:if test="${darkhastBazkharid.darkhastType=='KHESARAT'}">
                alertMessage("ضمائم مربوط به خسارت را آپلود کنید.");
            </c:if>
            <c:if test="${darkhastBazkharid.darkhastType!='KHESARAT'}">
                alertMessage("ضمائم مربوط به الحاقیه را آپلود کنید.");
            </c:if>
        }
        else {
            var db_have_havale='${darkhastBazkharid.haveHavale}'
            if(trans==617 && db_have_havale=='false')
                alertMessage("پیش از نهایی کردن خسارت، حواله های مربوطه را ایجاد نمایید.");
            else
            {
                $('#pdarkhast_popup').dialog({
                modal:true,
                width:260,
                resizable:false,
                closeText:"",
                title:"تغییر وضعیت از " + stateBegin + " به " + stateFinish,
                buttons:{
                    "بستن":function () {
                        $(this).dialog("close");
                    },
                    "انجام":function () {
                        <c:if test="${darkhastTaghirat == null}">
                        $('#darkhast_transitionId').val($('#dakhastTransitionSelector').val());
                        <c:if test="${darkhastBazkharid.darkhastType=='EBTAL'||darkhastBazkharid.darkhastType=='BAZKHARID'}">
                            if(trans==1100 || trans==null)
                            {
                                <c:if test="${darkhastBazkharid.state.id==1000}">
                                    $("#darkhast_transitionId").val(1100);
                                </c:if>
                            }
                            <c:if test="${darkhastBazkharid.darkhastType=='EBTAL'}">
                                <c:if test="${darkhastBazkharid.state.id==1000}">
                                    $("#darkhast_transitionId").val(1100);
                                </c:if>
                            if(trans==1110)
                            {
                                $("#darkhast_transitionId").val(1110);
                            }
                            </c:if>
                        </c:if>
                        <c:if test="${darkhastBazkharid.darkhastType=='BARDASHT_AZ_ANDOKHTE'}">
                            <c:if test="${darkhastBazkharid.state.id==11000}">
                        if (trans == null || (trans != 11001 && trans != 11010))
                            $("#darkhast_transitionId").val(11001);
                        else if (trans == 11010)
                            $("#darkhast_transitionId").val(11010);
                            </c:if>
                        </c:if>
                        <c:if test="${darkhastBazkharid.darkhastType=='VAM'}">
                            <c:if test="${darkhastBazkharid.state.id==10000}">
                                if (trans != null && trans == 10014)
                                {
                                    $("#darkhast_transitionId").val(10014);
                                }

                                if (trans == null || trans == 10001)
                                {
                                    $("#darkhast_transitionId").val(10001);
                                }

                            </c:if>
                        </c:if>


                        <%--<c:if test="${darkhastBazkharid.state.id==640}">--%>
                            <%--if (trans != null && trans == 601)--%>
                            <%--{--%>
                                <%--$("#darkhast_transitionId").val(601);--%>
                            <%--}--%>

                        <%--</c:if>--%>

                        <%--<c:if test="${darkhastBazkharid.state.id>=640 && darkhastBazkharid.state.id<=660}">--%>
                        <%--if (trans != null)--%>
                        <%--{--%>
                            <%--$("#darkhast_transitionId").val(trans);--%>
                            <%--$("#darkhast_bazkharidId10").val(${darkhastBazkharid.id});--%>
                            <%--$("#darkhast_bazkharidId").val(${darkhastBazkharid.id});--%>

                        <%--}--%>

                        <%--</c:if>--%>






                        $('#lognazarKhesarat').val($('#nazar_karshenas_khesarat').val());
                        $('#log_message_darkhast').val($('#darkhastloggmessage').val());
                        <%
                          request.setCharacterEncoding("UTF-8");
                          response.setCharacterEncoding("UTF-8");
                        %>
                        $('#darkhastTransitionForm').submit();
                        </c:if>
                        <c:if test="${darkhastTaghirat != null}">
                        $('#darkhasttaghirat_transitionId').val($('#dakhastTransitionSelector').val());
                        $('#log_message_darkhast_taghirat').val($('#darkhastloggmessage').val());
                        <%
                          request.setCharacterEncoding("UTF-8");
                          response.setCharacterEncoding("UTF-8");
                        %>
                        $('#darkhastTaghiratTransitionForm').submit();
                        </c:if>
                        $('#send_request_btn').attr('disabled',true);
                        $(this).dialog("close");
                    }
                }
            });
            }
        }
        ;
    }
    function mohasebateJaraemeVaam() {

        $.post("/ajaxlyCheckJaraemeVaam?darkhastBazkharid.id=${darkhastBazkharid.id}", function (msg) {
            $('#content_120').hide();
            $("#jarime_holder").html(msg);
            var takhir = parseInt($("#vam_takhir").val());
            var tavigh = parseInt($("#vam_tavigh").val());
            var jame = parseInt($("#vam_jame").val());
            var total = parseInt(takhir) + parseInt(tavigh) + parseInt(jame);
            $("#takhir_asl").text(jQuery.global.format(takhir, 'n0'));
            $("#tavigh_asl").text(jQuery.global.format(tavigh, 'n0'));
            $("#jame_asl").text(jQuery.global.format(jame, 'n0'));
            $("#total_jame_asl").text(jQuery.global.format(total, 'n0'));
            $('#content_120').show();
        });
    }

    function submitDarkhast() {
        <c:if test="${(bimenameIsMafqud == 'on' || darkhastBazkharid.bimenameIsMafqud == 'on')}">
            $.validationEngine.onSubmitValid = true;
            if ($.validationEngine.submitValidation($('#mafghudForm')) === false)
            {
                var input=document.createElement('input');
                input.type='hidden';
                input.name= 'darkhastBazkharid.nameRooznameh';
                input.value=$("#nameRooznameh").val();
                $("#mainForm4Darkhast").append(input);

                var input1=document.createElement('input');
                input1.type='hidden';
                input1.value= $("#tarikhAvalinChap").val();
                input1.name= 'darkhastBazkharid.tarikhAvalinChap';
                $("#mainForm4Darkhast").append(input1);

                var input2=document.createElement('input');
                input2.type='hidden';
                input2.value=$("#tarikhDovominChap").val();
                input2.name='darkhastBazkharid.tarikhDovominChap';
                $("#mainForm4Darkhast").append(input2);

                var input3=document.createElement('input');
                input3.type='hidden';
                input3.value='on';
                input3.name= 'bimenameIsMafqud';
                $("#mainForm4Darkhast").append(input3);
                $("#mainForm4Darkhast").submit();
            }
            else
            {
                changeMenu(15)
            }
        </c:if>
        <c:if test="${( bimenameIsMafqud != 'on' && darkhastBazkharid.bimenameIsMafqud != 'on')}">
            $("#mainForm4Darkhast").submit();
        </c:if>

    }

    function checkJensiat() {
        if (($('#editBimeshodeASF_jensiat_i').is(':checked')) || (${pishnehad.bimeShode.shakhs.jensiat=='مرد'})) {
            $("#banovanHeader").hide();
            $("#soal_x_25").attr("readonly", true);
            $("#soal_x_26").attr("readonly", true);
            $("#theTr26").hide();
            $("#theTr27").hide();
        } else {
            $("#banovanHeader").show();
            $("#soal_x_25").removeAttr("readonly");
            $("#soal_x_26").removeAttr("readonly");
            $("#theTr26").show();
            $("#theTr27").show();
        }
    }
    <c:if test="${darkhastBazkharid!=null && darkhastBazkharid.state.id==10100 && sodurVamSuccess=='yes'}">
    $(document).ready(function () {
        alert("صدور وام با موفقیت انجام شد.");
    });

    </c:if>
    <c:if test="${darkhastBazkharid!=null && darkhastBazkharid.state.id==10020}">
        $(document).ready(function (){$('#dakhastTransitionSelector option[value="10026"]').attr("selected", true);});
    </c:if>

    <c:if test="${darkhastBazkharid!=null && darkhastBazkharid.state.id==10030}">
        $(document).ready(function (){$('#dakhastTransitionSelector option[value="10008"]').attr("selected", true);});
    </c:if>
</script>
<input type="hidden" id="messagePass"/>

</body>
</html>
