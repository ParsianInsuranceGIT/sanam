<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="/css/pishnahadeBimeOmreEnferadi.css"/>
    <script type="text/javascript" src="/jsp/pishnahad/pishnahadeBimeOmreEnferadi.js"></script>
</head>
<body>

<s:actionerror/>
<%@include file="/jsp/josteju/searchCity.jsp" %>
<form action="/addKhesaratHavaleDo" method="post">
    <input type="hidden" name="khesarat.id" value="<s:property value="khesarat.id"/>"/>
    <input type="hidden" name="khesaratHavale.id" value="<s:property value="khesaratHavale.id"/>"/>
    <table>
        <tr>
            <td>نوع دریافت کننده:</td>
            <td>

                    <s:select list="#{'BIMEGOZAR':'بیمه گذار','BIMESHODE':'بیمه شده','ZINAF':'ذینفع'}" id="khesaratHavale_typeReciever"
                        key="khesaratHavale.typeReciever" label="" theme="simple" onchange="fillSubType();"/>
            </td>
            <td id="z_lbl" style="display: none">ذینفع را انتخاب کنید: </td>
            <td>
                <c:forEach var="payee" items="${khesarat.bimename.pishnehad.estefadeKonandeganAzSarmayeBime}" varStatus="i">
                    <input type="hidden" id="z_name${i.index}" value="${payee.name} ${payee.nameKhanevadegi}"/>
                    <input type="hidden" id="z_shsh${i.index}" value="${payee.shomareShenasname}"/>
                    <input type="hidden" id="z_shahr${i.index}" value="${payee.mahalleSodoorShenasnameh}"/>
                    <input type="hidden" id="z_nesbat${i.index}" value="${payee.nesbatBabimeShode}"/>
                </c:forEach>
                <select id="khesaratHavale_subType" style="display: none;">
                    <c:forEach var="payee" items="${khesarat.bimename.pishnehad.estefadeKonandeganAzSarmayeBime}" varStatus="i">
                        <option value="${i.index}" onclick="loadOtherFieldsFromZinaf(${i.index});" >${payee.name} ${payee.nameKhanevadegi}</option>
                    </c:forEach>
                </select>
            </td>

            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>شماره حساب:</td>
            <td><input type="text" name="khesaratHavale.shomareHesab"
                       value="<s:property value="khesaratHavale.shomareHesab"/>"/></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>نسبت:</td>
            <td>
                <%--<s:select list="nesbatha" label="" key="khesaratHavale.nesbatBabimeShode" theme="simple"/>--%>
                <input type="text" id="kh_nesbat" name="khesaratHavale.nesbatBabimeShode" />
            </td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>نام:</td>
            <td><input type="text" name="khesaratHavale.name" id="kh_name" value="<s:property value="khesaratHavale.name"/>"></td>
            <td>شماره شناسنامه:</td>
            <td><input type="text" name="khesaratHavale.shomareShenasname" id="kh_shsh"
                       value="<s:property value="khesaratHavale.shomareShenasname"/>"></td>
            <td>محل صدور:</td>
            <td><input type="text" name="khesaratHavale.mahalleSodur.cityName"
                       value="<s:property value="khesaratHavale.mahalleSodur.cityName"/>" id="kh_shahr"
                       style="width: 100px"/>
                <input type="hidden" name="khesaratHavale.mahalleSodur.cityId"
                       value="<s:property value="khesaratHavale.mahalleSodur.cityId"/>" id="kh_shahrId"/>
                <input type="button" id="btnOstanShahrSelector" value="انتخاب"
                       onclick="fillShahrOstan('kh_shahrId','kh_shahr','kh_ostanId','kh_ostan','kh_vahedSodor')"
                       style=""/>
            </td>
        </tr>
        <tr>
            <td>تایید کننده:</td>
            <td><s:select list="karshenasKhesaratList" key="khesaratHavale.userCreator.id" label="" listKey="id"
                          listValue="fullName" theme="simple"/></td>
            <td>درصد خسارت:</td>
            <td>
                <script type="text/javascript" >

                    function fillSubType()
                    {
                        $('#khesaratHavale_subType').hide();
                        $('#z_lbl').hide();
                        var typSlctd = $('#khesaratHavale_typeReciever').val();
                        if (typSlctd == 'BIMEGOZAR')
                        {
                            $('#kh_name').val('${khesarat.bimename.pishnehad.bimeGozar.shakhs.name} ${khesarat.bimename.pishnehad.bimeGozar.shakhs.nameKhanevadegi}');
                            $('#kh_shsh').val(${khesarat.bimename.pishnehad.bimeGozar.shakhs.shomareShenasnameh});
                            $('#kh_shahr').val('${khesarat.bimename.pishnehad.bimeGozar.shakhs.mahalleSodoreShenasnameh.cityName}');
                            $('#kh_shahrId').val('${khesarat.bimename.pishnehad.bimeGozar.shakhs.mahalleSodoreShenasnameh.cityId}');
                        }

                        if (typSlctd == 'BIMESHODE')
                        {
                            $('#kh_name').val('${khesarat.bimename.pishnehad.bimeShode.shakhs.name} ${khesarat.bimename.pishnehad.bimeShode.shakhs.nameKhanevadegi}');
                            $('#kh_shsh').val(${khesarat.bimename.pishnehad.bimeShode.shakhs.shomareShenasnameh});
                            $('#kh_shahr').val('${khesarat.bimename.pishnehad.bimeShode.shakhs.mahalleSodoreShenasnameh.cityName}');
                            $('#kh_shahrId').val('${khesarat.bimename.pishnehad.bimeShode.shakhs.mahalleSodoreShenasnameh.cityId}');
                        }

                        if (typSlctd == 'ZINAF')
                        {
                            var size= ${khesarat.bimename.pishnehad.estefadeKonandeganAzSarmayeBime.size()};
                            if(size>1)
                            {
                                $('#khesaratHavale_subType').show();
                                $('#z_lbl').show();
                            }
                            else
                            {
                                $('#khesaratHavale_subType').hide();
                                $('#z_lbl').hide();
                            }

                        }
                    }

                    function loadOtherFieldsFromZinaf(arg)
                    {
                        $('#kh_name').val($('#z_name' + arg).val());
                        $('#kh_shsh').val($('#z_shsh' + arg).val());
                        $('#kh_nesbat').val($('#z_nesbat' + arg).val());
                        $('#kh_shahr').val('');
                        $('#kh_shahrId').val('');
                    }

                    function onchangeDarsad()
                    {
                        var remainingDarsad = '${khesarat.remainingDarsad}';
                        var inDarsad = $("#darsad").val();
                        if(inDarsad > parseInt(remainingDarsad))
                        {

                            $("#darsad").val(${khesarat.remainingDarsad});
                            $("#amount").val(jQuery.global.format(${khesarat.remainingAmount}));
                        }
                        else
                        {

                            afp = '${khesarat.amountGhabelPardakht}';
                            var finalAmount=jQuery.global.format(((inDarsad * parseInt(afp.replace(new RegExp(",", "gm"), ""))) / 100));
                            if (finalAmount.indexOf("NaN") != -1)
                            {
                                finalAmount="";
                            }

                            $("#amount").val(finalAmount);
                        }
                    } ;
                    function onchangeAmount()
                    {
                        var remainingAmount = '${khesarat.remainingAmount}';
                        var inAmount = parseInt($("#amount").val().replace(new RegExp(",", "gm"), ""));
                        if ( inAmount> parseInt(remainingAmount.replace(new RegExp(",", "gm"), "")))
                        {
                            $("#amount").val(jQuery.global.format(parseInt(remainingAmount.replace(new RegExp(",", "gm"), ""))));
                            $("#darsad").val(${khesarat.remainingDarsad});
                        }
                        else
                        {
                            afp= '${khesarat.amountGhabelPardakht}';

                            $("#darsad").val(Math.round((inAmount * 100) / parseInt(afp.replace(new RegExp(",", "gm"), ""))));
                        }
                    }
                </script>
                <input type="text" name="khesaratHavale.darsadKhesarat"    onchange="onchangeDarsad();" id="darsad"
                       class="validate[custom[integer]]"
                       value="<s:property value="khesarat.remainingDarsad"/>">
            </td>
            <td>
                مبلغ حواله :
            </td>
            <td>
                <input type="text" name="khesaratHavale.amountHavale" onchange="onchangeAmount();" id="amount"  class="digitSeparators" value="${khesarat.remainingAmount}"/>
            </td>
            </td>
        </tr>
    </table>
    <input type="submit" value="ثبت">
</form>
</body>
</html>