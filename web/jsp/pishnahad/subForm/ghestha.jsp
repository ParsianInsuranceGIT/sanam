<%@ page import="com.bitarts.parsian.service.calculations.Reports.TaghsitReport" %>
<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="expandableTitleBar">
    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
        اقساط تولید شده
    </p>

    <div class="content" dir="rtl" style="text-align:center;">

        <table align="center" class="jtable resultDets" cellpadding="0" cellspacing="0" style="margin:0 auto;">
            <thead>
            <tr>
                <th style="padding:5" class="ui-state-default">سال</th>
                <th style="padding:5" class="ui-state-default">تاریخ</th>
                <th style="padding:5" class="ui-state-default">حق بیمه پرداختی</th>
                <th style="padding:5" class="ui-state-default">نوع</th>
                <th style="padding:5" class="ui-state-default">هزینه کارمزد</th>
                <th style="padding:5" class="ui-state-default">هزینه بیمه گری</th>
                <th style="padding:5" class="ui-state-default">هزینه اداری</th>
                <th style="padding:5" class="ui-state-default">هزینه وصول</th>
                <th style="padding:5" class="ui-state-default">حق بيمه خالص فوت يكجا</th>
                <th style="padding:0" class="ui-state-default">حق بیمه پوشش امراض</th>
                <th style="padding:0" class="ui-state-default">حق بیمه پوشش حادثه</th>
                <th style="padding:0" class="ui-state-default">حق بیمه پوشش نقص عضو</th>
                <th style="padding:0" class="ui-state-default">حق بیمه پوشش معافیت</th>
                <th style="padding:0" class="ui-state-default">حق بیمه پوشش های اضافی</th>
                <th style="padding:0" class="ui-state-default">مالیات</th>
                <th style="padding:5" class="ui-state-default">مجموع هزینه ها</th>
                <th style="padding:5" class="ui-state-default">خالص سرمایه گذاری</th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="taghsitReport" id="row" status="stat">
                <tr>
                    <td style="padding:5px 5px" class="ui-widget-content"><s:property value="#row.getSaal()"/></td>
                    <td style="padding:5px 5px" class="ui-widget-content"><s:property value="#row.getTarikh()"/></td>
                    <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param
                            name="value" value="#row.getHaghBimePardaakhtiSaal()"/></s:text></td>
                    <td style="padding:5px 5px" class="ui-widget-content">قسط</td>
                    <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param
                            name="value" value="#row.getKaarmozd()"/></s:text></td>
                    <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param
                            name="value" value="#row.getHazineBimeGari()"/></s:text></td>
                    <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param
                            name="value" value="#row.getHazineEdari()"/></s:text></td>
                    <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param
                            name="value" value="#row.getHazineVosul()"/></s:text></td>
                    <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param
                            name="value" value="#row.getHaghBimeKhaalesFotYekja()"/></s:text></td>
                    <s:if test="#row.isPoosheshEzafiDetailsCorrect()">
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                           value="#row.getHaghBimePoosheshAmraz()"/></s:text></td>
                    </s:if>
                    <s:else>
                        <td style="color:blue;" class="ui-widget-content"><s:text name="doubleFormat"><s:param
                                name="value" value="#row.getHaghBimePoosheshAmraz()"/></s:text></td>
                    </s:else>

                    <s:if test="#row.isPoosheshEzafiDetailsCorrect()">
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                           value="#row.getHaghBimePoosheshHadese()"/></s:text></td>
                    </s:if>
                    <s:else>
                        <td style="color:blue;" class="ui-widget-content"><s:text name="doubleFormat"><s:param
                                name="value" value="#row.getHaghBimePoosheshHadese()"/></s:text></td>
                    </s:else>

                    <s:if test="#row.isPoosheshEzafiDetailsCorrect()">
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                           value="#row.getHaghBimePoosheshNaghsOzv()"/></s:text></td>
                    </s:if>
                    <s:else>
                        <td style="color:blue;" class="ui-widget-content"><s:text name="doubleFormat"><s:param
                                name="value" value="#row.getHaghBimePoosheshNaghsOzv()"/></s:text></td>
                    </s:else>
                    <s:if test="#row.isPoosheshEzafiDetailsCorrect()">
                        <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                           value="#row.getHaghBimePoosheshMoafiat()"/></s:text></td>
                    </s:if>
                    <s:else>
                        <td style="color:blue;" class="ui-widget-content"><s:text name="doubleFormat"><s:param
                                name="value" value="#row.getHaghBimePoosheshMoafiat()"/></s:text></td>
                    </s:else>

                    <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                       value="#row.getHaghBimePusheshHaayeEzaafi()"/></s:text></td>

                    <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                       value="#row.getMaliat()"/></s:text></td>


                    <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                       value="#row.getMaliat()+#row.getHaghBimePusheshHaayeEzaafi()+#row.getKaarmozd()+#row.getHazineBimeGari()+#row.getHazineEdari()+#row.getHazineVosul()+#row.getHaghBimeKhaalesFotYekja()"/></s:text></td>
                    <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                       value="#row.getHaghBimePardaakhtiSaal()-(#row.getMaliat()+#row.getHaghBimePusheshHaayeEzaafi()+#row.getKaarmozd()+#row.getHazineBimeGari()+#row.getHazineEdari()+#row.getHazineVosul()+#row.getHaghBimeKhaalesFotYekja())"/></s:text></td>
                </tr>
            </s:iterator>
            <tr>
                <td>&nbsp;</td>
            </tr>
            <s:iterator value="trList" id="row" status="stat">
                <tr>
                    <td style="padding:5px 5px" class="ui-state-default" colspan="2">جمع :</td>
                    <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param
                            name="value" value="#row.getHaghBimePardaakhtiSaal()"/></s:text></td>
                    <td style="padding:5px 5px" class="ui-widget-content">قسط</td>
                    <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param
                            name="value" value="#row.getKaarmozd()"/></s:text></td>
                    <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param
                            name="value" value="#row.getHazineBimeGari()"/></s:text></td>
                    <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param
                            name="value" value="#row.getHazineEdari()"/></s:text></td>
                    <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param
                            name="value" value="#row.getHazineVosul()"/></s:text></td>
                    <td style="padding:5px 5px" class="ui-widget-content"><s:text name="doubleFormat"><s:param
                            name="value" value="#row.getHaghBimeKhaalesFotYekja()"/></s:text></td>
                    <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                       value="#row.getHaghBimePoosheshAmraz()"/></s:text></td>
                    <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                       value="#row.getHaghBimePoosheshHadese()"/></s:text></td>
                    <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                       value="#row.getHaghBimePoosheshNaghsOzv()"/></s:text></td>
                    <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                       value="#row.getHaghBimePoosheshMoafiat()"/></s:text></td>
                    <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                       value="#row.getHaghBimePusheshHaayeEzaafi()"/></s:text></td>
                    <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                       value="#row.getMaliat()"/></s:text></td>
                    <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                       value="#row.getMaliat()+#row.getHaghBimePusheshHaayeEzaafi()+#row.getKaarmozd()+#row.getHazineBimeGari()+#row.getHazineEdari()+#row.getHazineVosul()+#row.getHaghBimeKhaalesFotYekja()"/></s:text></td>
                    <td class="ui-widget-content"><s:text name="doubleFormat"><s:param name="value"
                                                                                       value="#row.getHaghBimePardaakhtiSaal()-(#row.getMaliat()+#row.getHaghBimePusheshHaayeEzaafi()+#row.getKaarmozd()+#row.getHazineBimeGari()+#row.getHazineEdari()+#row.getHazineVosul()+#row.getHaghBimeKhaalesFotYekja())"/></s:text></td>
                </tr>
            </s:iterator>
            <%--<tr>--%>
            <%--<td>&nbsp;</td>--%>
            <%--</tr>--%>
            </tbody>
        </table>
        <br/>
        <div style="float:right;margin-right:5px;">
        کسر هزار ریال:
        <s:text name="doubleFormat"><s:param
                name="value" value="taghsitReport[0].kasrHazine"/></s:text>
        </div>
        <c:if test="${showTaghsit != 'yes'}">
            <input type="button" class="noAnyDisable" style="float:left;margin-left:20px" onclick="tayideGhestbandi()"
                   value="تایید"/>
        </c:if>
        <input type="button" class="noAnyDisable" style="float:left;margin-left:20px" onclick="showVaziateGhestBandi()"
               value="بازگشت"/>

        <div style="height:30px;">&nbsp;</div>
    </div>
</div>
<script type="text/javascript">
    function tayideGhestbandi() {
        $.post("/tayideGhestbandi.action?pishnehad.id=${pishnehad.id}&saleBimei=${saleBimei}", function(msg) {
            $('#div_showVaziateGhestBandi').html(msg);
            $("#div_showVaziateGhestBandi input").each(function() {
                $(this).addClass("ui-state-default  ui-corner-all ui-helper-clearfix");
            });
        });
    }
</script>
