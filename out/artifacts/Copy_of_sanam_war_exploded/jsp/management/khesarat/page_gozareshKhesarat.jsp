<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:actionerror/>
<s:actionmessage/>
<form action="/gozareshKhesarat" method="post">
    <table>
        <tr>
            <td colspan="2">
                <input type="text" name="khesarat.ostanMahalleHadese.cityName" id="ostan"
                       value="${khesarat.ostanMahalleHadese.cityName}" style="margin-right: 152px"/>
                <input type="hidden" name="khesarat.ostanMahalleHadese.cityId" id="ostanId"
                       value="${khesarat.ostanMahalleHadese.cityId}"/>
                <label>استان</label>
            </td>
            <td colspan="2">
                <input type="text" name="khesarat.shahrMahalleHadese.cityName" id="shahr"
                       value="${khesarat.shahrMahalleHadese.cityName}" style="margin-right: 243px"/>
                <input type="hidden" name="khesarat.shahrMahalleHadese.cityId" id="shahrId"
                       value="${khesarat.shahrMahalleHadese.cityId}"/>
                <label>شهر</label>
                <input type="button" id="btnOstanShahrSelector" value="انتخاب"
                       onclick="fillShahrOstan('shahrId','shahr','ostanId','ostan','vahedSodor')"
                       style=""/>
            </td>


            </td>
        </tr>
        <tr>
            <td><label>واحد صدور</label></td>
            <td>
                <%--<s:select theme="simple" emptyOption="true" headerValue="" list="vahedSodurs"--%>
                          <%--name="vahedSodur.id" value="%{khesarat.bimename.pishnehad.namayande.vahedSodur.id}"--%>
                          <%--listKey="id"--%>
                          <%--listValue="name"/>--%>
                <%--<input type="text" name="pishnehadSearch.vahedSodor" id="vahedSodor" onkeyup=""--%>
                <%--value='${pishnehadSearch.vahedSodor}' class=""/>--%>
            </td>
            <td><label>نمايندگي</label></td>
            <td>
                <%--<s:select theme="simple" emptyOption="true" headerValue="" list="vahedSodurs"--%>
                          <%--name="namayande.id" value="%{khesarat.bimename.pishnehad.namayande.id}" listKey="id"--%>
                          <%--listValue="name"/>--%>
                <%--<input type="text" name="pishnehadSearch.namayande" id="" onkeyup=""--%>
                <%--value='${pishnehadSearch.namayande}' class=""/>--%>
            </td>
        </tr>
        <tr>
            <td><label>نوع قرارداد</label></td>
            <td>
                <s:select theme="simple" emptyOption="true" headerValue="" list="noeGharardads"
                          name="noeGharardad" value="noeGharardad"/>
            </td>
            <td><label>نوع طرح</label></td>
            <td dir="rtl">
                <s:select theme="simple" headerValue="" list="tarhs"
                          listKey="id" listValue="name"
                          name="tarh.name" value="tarh.name"/>
            </td>
        </tr>
        <tr>
            <td><label>نام بیمه گذار</label></td>
            <td>
                <span class="noThing"></span>
                <input type="text" name="nameBimegozar" id="" onkeyup="" value='${nameBimeGozar}'
                       class="" style="margin-right: 22px"/>

            </td>
            <td><label>نوع خسارت</label></td>
            <td>
                <select name="noeKhesarat">
                    <option value="-1">همه موارد</option>
                    <option value="0">عمر</option>
                    <option value="1">حادثه</option>
                    <option value="2">امراض خاص</option>
                    <option value="3">معافیت و از کار افتادگی</option>
                </select>
                <%--<input type="text" name="pishnehadSearch.karbareSaderKonnandeh" id="" onkeyup=""--%>
                <%--value='${pishnehadSearch.karbareSaderKonnandeh}' class=""/>--%>

            </td>
        </tr>
        <tr>
            <td><label>از تاریخ پرداخت خسارت</label></td>
            <td>
                <input type="text" name="azTarikh" id="azTarikheSodoreBimename" onkeyup=""
                       value='${azTarikh}' class="datePkr" style="margin-right: 22px"/>

            </td>
            <td><label>تا تاریخ پرداخت خسارت</label></td>
            <td>
                <input type="text" name="taTarikh" id="taTarikheSodoreBimename" onkeyup=""
                       value='${taTarikh}' class="datePkr" style="margin-right: 25px"/>

            </td>
        </tr>
        <tr>
            <td><label>علت حادثه</label></td>
            <td>
                <input type="text" name="khesarat.ellat" onkeyup=""
                       value='${khesarat.ellat}' class=""/>
            </td>
            <td colspan="2"></td>
        </tr>
    </table>
    <input type="submit" value="جستجو">
    <input type="button" value="بازگشت" onclick="window.location='/jsp/management/page_mainManagementPage.jsp'">
</form>
<s:if test="%{khesaratha != null && khesaratha.size() >0 }">
    <div style="overflow: auto;width: 900px">
        <display:table export="true" id="tblPishnehadResultList" uid="rpViewResult"
                       htmlId="tblPishnehadResultList"
                       name="khesaratha" clearStatus="true"
                       keepStatus="false">
            <display:column title="ردیف">${rpViewResult_rowNum}</display:column>
            <display:column property="ostanMahalleHadese.cityName"
                            title="استان"></display:column>
            <display:column property="shahrMahalleHadese.cityName" title="شهر"></display:column>
            <display:column property="bimename.pishnehad.namayande.vahedSodur.name"
                            title="واحد صدور"></display:column>
            <display:column property="bimename.pishnehad.namayande.vahedSodur.kodeNamayandeKargozar"
                            title="کد واحد صدور"></display:column>
            <display:column property="bimename.pishnehad.namayande.name" title="نام نماينده"></display:column>
            <display:column property="bimename.pishnehad.namayande.kodeNamayandeKargozar"
                            title="کد نمایندگی"></display:column>

            <display:column
                    title="نوع بیمه نامه">عمر و سرمایه گذاری</display:column>
            <display:column property="bimename.pishnehad.noeGharardad" title="نوع قرارداد"></display:column>
            <display:column property="bimename.pishnehad.tarh.name" title="نوع طرح"></display:column>
            <display:column property="bimename.shomare"
                            title="شماره بيمه‌نامه"></display:column>
            <display:column
                    title="نام بيمه‌گذار">${rpViewResult.bimename.pishnehad.bimeGozar.shakhs.name} &nbsp; ${rpViewResult.bimename.pishnehad.bimeGozar.shakhs.nameKhanevadegi}</display:column>
            <display:column
                    title="نام بيمه‌شده">${rpViewResult.bimename.pishnehad.bimeShode.shakhs.name} &nbsp; ${rpViewResult.bimename.pishnehad.bimeShode.shakhs.nameKhanevadegi}</display:column>
            <display:column property="bimename.pishnehad.bimename.tarikhSodour"
                            title="تاريخ صدور"></display:column>
            <display:column property="bimename.pishnehad.bimename.tarikhShorou"
                            title="تاريخ شروع بيمه"></display:column>
            <display:column property="bimename.pishnehad.bimename.tarikhEngheza"
                            title="تاريخ پايان بيمه‌نامه"></display:column>
            <display:column property="bimename.pishnehad.estelam.hagh_bime_pardakhti"
                            title="حق بیمه"></display:column>
            <display:column property="shomareParvande"
                            title="شماره پرونده خسارت"></display:column>
            <display:column
                    title="شماره حواله خسارت">${rpViewResult.havaleList.size()>0?rpViewResult.havaleList.get(0).shomareHavale:''}</display:column>
            <display:column
                    title="تاریخ حواله خسارت">${rpViewResult.havaleList.size()>0?rpViewResult.havaleList.get(0).tarikhHavale:''}</display:column>

            <display:column property="accidentDate"
                            title="تاریخ وقوع خسارت"></display:column>
            <display:column property="createdDate"
                            title="تاریخ اعلام خسارت"></display:column>
            <display:column
                    title="تاریخ پرداخت خسارت">${rpViewResult.havaleList.size()>0?rpViewResult.havaleList.get(0).credebit.createdDate:''}</display:column>
            <display:column property="typeFarsi"
                            title="نوع خسارت"></display:column>
            <display:column property="ellat"
                            title="علت حادثه"></display:column>
            <display:column property="amountGhabelPardakht"
                            title="مبلغ خسارت"></display:column>
            <display:column
                    title="ذینفع">${rpViewResult.bimename.pishnehad.estefadeKonandeganAzSarmayeBime.size()>0?rpViewResult.bimename.pishnehad.estefadeKonandeganAzSarmayeBime.get(0).fullName:''}</display:column>
            <display:column
                    title="دریافت کننده خسارت">${rpViewResult.havaleList.size()>0?rpViewResult.havaleList.get(0).name:''}</display:column>
        </display:table>
    </div>
</s:if>
<s:if test="%{khesaratha != null && khesaratha.size() ==0 }">
    اطلاعاتی یافت نشد
</s:if>