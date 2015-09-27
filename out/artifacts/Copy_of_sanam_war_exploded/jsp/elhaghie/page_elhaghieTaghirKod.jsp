<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<c:out value="${bimename.id}"/>--%>
<s:actionmessage/>
<div align="center">

    <table>
        <tr>
            <td>شماره درخواست</td>
            <td style="color: blue"><s:property value="darkhastBazkharid.shomareDarkhast"/></td>
            <td>وضعیت درخواست</td>
            <td style="color: blue"><s:property value="darkhastBazkharid.state.stateName"/></td>
            <td>واحد صدور</td>
            <td style="color: green"><s:property
                    value="darkhastBazkharid.bimename.pishnehad.namayande.kodeNamayandeKargozar"/></td>
            <td>نمایندگی</td>
            <td style="color: green"><s:property value="darkhastBazkharid.taghirKodBe"/></td>
        </tr>
        <tr>
            <td>نوع درخواست</td>
            <td style="color: red">
                <s:if test="%{darkhastBazkharid.darkhastType.name().equals('TAGHIRKOD')}">
                    تغییر
                </s:if>
                <s:if test="%{darkhastBazkharid.darkhastType.name().equals('TOZIH')}">
                    الحاقیه توضیح
                </s:if>
            </td>
            <td>نوع خاص</td>
            <td style="color: red">
                <s:if test="%{darkhastBazkharid.darkhastType.name().equals('TAGHIRKOD')}">
                    تغییر کد نمایندگی
                </s:if>
                <s:if test="%{darkhastBazkharid.darkhastType.name().equals('TOZIH')}">
                    الحاقیه توضیح
                </s:if>
            </td>
            <td>جرئیات</td>
            <td style="color: red">
                <s:if test="%{darkhastBazkharid.darkhastType.name().equals('TAGHIRKOD')}">
                    <s:if test="%{darkhastBazkharid.bimename.pishnehad.namayande.kodeNamayandeKargozar.substring(0,6).equals('111120')}">
                        تغییر کد موقت
                    </s:if>
                    <s:else>
                        تغییر نمایندگی
                    </s:else>
                </s:if>
            </td>
            <td>کل حق بیمه</td>
            <td>0</td>
        </tr>
        <tr>
            <td>تاریخ صدور</td>
            <td style="color: green"><s:property value="darkhastBazkharid.tarikhDarkhast"/></td>
            <td>تاریخ اثر</td>
            <td style="color: green"><s:property value="darkhastBazkharid.bimename.tarikhShorou"/></td>
            <td>تاریخ اثر کارمزد</td>
            <td></td>
            <td>موضوع الحاقیه</td>
            <td><s:textfield key="darkhastBazkharid.mozuElhaghie" label="" theme="simple" disabled="true"/></td>
        </tr>
        <tr>
            <td>امضاکننده اول</td>
            <td>
                <input type="text" disabled="disabled"
                       value="<s:property value="darkhastBazkharid.emzaKonandeAval.user.fullName"/>"
                       id="bimename_emzaKonandeAval" style="width:100px"/>
            </td>
            <td>امضاکننده دوم</td>
            <td>
                <input type="text" disabled="disabled"
                       value="<s:property value="darkhastBazkharid.emzaKonandeDovom.user.fullName"/>"
                       id="bimename_emzaKonandeDovom" style="width:100px"/>
            </td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>متن الحاقیه</td>
            <td colspan="7"><s:textarea key="darkhastBazkharid.matnElhaghie" theme="simple" cols="140" disabled="true" rows="20"
                            label=""/></td>
        </tr>
        <tr>
            <td><input type="button" value="پرینت"
                       onclick="window.open('/printElhaghieTaghirKod?elhaghie.id=<s:property value="elhaghie.id"/>')">
            </td>
            <td></td>
        </tr>
    </table>

</div>
