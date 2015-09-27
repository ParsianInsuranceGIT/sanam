<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<center>
    <table>
        <tr>
            <td>شماره پرونده</td>
            <td><s:property value="khesarat.shomareParvande"/>&nbsp;</td>
            <td>
            </td>
            <td>
            </td>
        </tr>
        <tr>
            <td>نوع مورد خسارت:</td>
            <td align="right">
                <s:if test="khesarat.isOmr()">
                    عمر
                </s:if>
                <br/>
                <s:if test="khesarat.isHadese()">
                    حادثه
                </s:if>
                <br/>
                <s:if test="khesarat.isAmraz()">
                    امراض خاص
                </s:if>
                <br/>
                <s:if test="khesarat.isNaghsOzv()">
                    نقص عضو
                </s:if>
                <br/>
                <s:if test="khesarat.isMoafiat()">
                    معاقیت و از کار افتادگی
                </s:if>
                <br/>
            </td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>شرح خسارت:</td>
            <td>
                <s:textarea theme="simple" key="khesarat.sharhKhesarat" readonly="true" label="" cols="40" rows="3"/>
            </td>
        </tr>
        <tr>
            <td>علت حادثه:</td>
            <td>
                <s:textfield key="khesarat.ellat" label="" theme="simple" readonly="true" cssStyle="width: 280px"/>
            </td>
            <td>نحوه اعلام خسارت:</td>
            <td>
                <s:textfield key="khesarat.nahveElam" label="" theme="simple" readonly="true" cssStyle="width: 200px"/>
            </td>
        </tr>
        <tr>
            <td>شهر محل حادثه:</td>
            <td>
                <input type="text" value="<s:property value="khesarat.shahrMahalleHadese.cityName"/>"
                       readonly="readonly" id="kh_shahr" style="width: 70px"/>
                <input type="text" value="<s:property value="khesarat.ostanMahalleHadese.cityName"/>"
                       readonly="readonly" id="kh_ostan" style="width: 70px"/>
            </td>
            <td>مبلغ خسارت اعلام شده:</td>
            <td>
                <s:textfield theme="simple" label="" key="khesarat.amountElamShode" readonly="true"
                             cssClass="digitSeparators"
                             cssStyle="width: 200px"/>
            </td>
        </tr>
        <tr>
            <td>مبلغ خسارت تایید شده:</td>
            <td>
                <s:textfield id="kh_taidShode" theme="simple" label="" readonly="true" key="khesarat.amountTaidShode"
                             cssClass="digitSeparators" cssStyle="width: 280px" onkeyup="calcKhesaratGhabelPardakht()"/>
            </td>

            <td>مبلغ خسارت ارفاق مدیریتی:</td>
            <td>
                <s:textfield id="kh_erfagh" theme="simple" readonly="true" label="" key="khesarat.amountErfagh"
                             cssClass="digitSeparators" cssStyle="width: 200px" onkeyup="calcKhesaratGhabelPardakht()"/>
            </td>
        </tr>
        <tr>
            <td>مبلغ اندوخته روز فوت:</td>
            <td>
                <s:textfield id="kh_andookhte" theme="simple" label="" key="khesarat.andukhte"
                             cssClass="digitSeparators" cssStyle="width: 280px" readonly="true"/>
            </td>

            <td>مبلغ هزینه های آتی:</td>
            <td>
                <s:textfield id="kh_ati" theme="simple" label="" key="khesarat.amountAti"
                             cssClass="digitSeparators" cssStyle="width: 200px; direction: ltr;" readonly="true"/>
            </td>
        </tr>
        <tr>
            <td>مبلغ خسارت مازاد بر سقف:</td>
            <td>
                <s:textfield theme="simple" label="" key="khesarat.amountMazad" readonly="true"
                             cssClass="digitSeparators"
                             cssStyle="width: 280px"/>
            </td>
            <td>خسارت قابل پرداخت:</td>
            <td>
                <input id="kh_ghabelPardakht" type="text" style="width: 200px" readonly="readonly"
                       value="<s:property value="khesarat.amountGhabelPardakht"/>"/>
            </td>
        </tr>
        <tr>
            <td>نظریه کارشناس:</td>
            <td>
                <s:textarea key="khesarat.nazarKarshenas" cols="40" rows="3" theme="simple" label="" readonly="true"/>
            </td>
        </tr>
        <tr>
            <td>کارشناس خسارت:</td>
            <td>
                <input type="text" value="<s:property value="khesarat.karshenasKhesarat.fullName"/>"
                       readonly="readonly">
            </td>
            <td>تاریخ وقوع حادثه:</td>
            <td>
                <s:textfield theme="simple" label="" key="khesarat.accidentDate" readonly="true"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="border:0;"><input type="button" style="float:right;"
                                                     onclick="window.open('/printMohasebeKhesarat?khesarat.id=<s:property value="khesarat.getId()"/>')"
                                                     value="برگ محاسبه خسارت بیمه نامه عمر انفرادی"></td>
            <td colspan="2" style="border:0;"><input type="button" style="float:right;"
                                                     onclick="window.open('/printTainVaziatKhesarat?khesarat.id=<s:property value="khesarat.getId()"/>')"
                                                     value="اعلام خسارت و تعیین وضعیت پوشش بیمه شدگان خسارت دیده"></td>
        </tr>
        <c:if test="${khesarat.state.id==630}">
            <tr>
                <td>
                    <input type="button" value="کارشناسی مجدد"
                       <%--onclick="window.location='/sabtKhesaratHavale?type=4&khesarat.id=<s:property value="khesarat.id"/>'">--%>
                </td>
            </tr>
        </c:if>
    </table>
</center>

