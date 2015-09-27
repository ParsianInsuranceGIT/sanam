<%@ page import="com.bitarts.parsian.model.transitionwise.PezeshkSabtNazar" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bitarts.parsian.model.pishnahad.Pishnehad" %>
<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>تبدیل به بیمه نامه - بیمه نامه موقت</title>
</head>
<form action="makeFinalTransition.action" id="transitionNahaE" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
<div class=expandableTitleBar>
<p class=heading ><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
    فرم بیمه نامه
</p>
<table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin:0 auto;" border="0">
<tbody>
<tr>
    <td colspan="2">
        شماره پیشنهاد: &nbsp;&nbsp; <input type="text" readonly="readonly" value="${pishnehad.radif}" name="bimename.shomarePishnehad"/>&nbsp;&nbsp;&nbsp;&nbsp;
        شماره بیمه نامه:&nbsp;&nbsp; <input type="text" readonly="readonly" value="${pishnehad.radif}" name="bimename.shomareBimename"/>  &nbsp;&nbsp;&nbsp;&nbsp;
        وضعیت:&nbsp;&nbsp; <input type="text" readonly="readonly" value="تبدیل به بیمه نامه" name="bimename.vaziat"/>
    </td>
</tr>
<tr>
    <td width="20%">
        مشخصات بیمه گذار &nbsp;&nbsp;
    </td>
    <td width="80%">
        <div style="width:90%">
            <div class="div_inputList">
                <input type="text" readonly="readonly" value="${pishnehad.bimeGozar.shakhs.name} ${pishnehad.bimeGozar.shakhs.nameKhanevadegi}" name="bimename.bimegozarFullName"/>
                <label>نام و نام خانوادگی:</label>
            </div>
            <div class="div_inputList">
                <input type="text" readonly="readonly" value="${pishnehad.bimeGozar.shakhs.kodeMelliShenasayi}" name="bimename.bimegozarKodeMelli"/>
                <label>کد ملی:</label>
            </div>
            <div class="div_inputList">
                <input type="text" readonly="readonly" value="${pishnehad.bimeGozar.shakhs.shoghleAsli}" name="bimename.bimegozarShoghleAsli"/>
                <label>شغل اصلی:</label>
            </div>
            <div class="div_inputList">
                <input type="text" readonly="readonly" value="${pishnehad.bimeGozar.shakhs.shoghleFarei}" name="bimename.bimegozarShoghleFari"/>
                <label>شغل فرعی:</label>
            </div>
        </div>
    </td>
</tr>
<tr>
    <td>
        مشخصات بیمه شده&nbsp;&nbsp;
    </td>
    <td>
        <div style="width:90%">
            <div class="div_inputList">
                <input type="text" readonly="readonly" value="${pishnehad.bimeShode.shakhs.name} ${pishnehad.bimeShode.shakhs.nameKhanevadegi}" name="bimename.bimeshodeFullName"/>
                <label>نام و نام خانوادگی:</label>
            </div>
            <div class="div_inputList">
                <input type="text" readonly="readonly" value="${pishnehad.bimeShode.shakhs.kodeMelliShenasayi}" name="bimename.bimeshodeKodeMelli"/>
                <label>کد ملی:</label>
            </div>
            <div class="div_inputList">
                <input type="text" readonly="readonly" value="${pishnehad.bimeShode.shakhs.shoghleAsli}" name="bimename.bimeshodeShoghleAsli"/>
                <label>شغل اصلی:</label>
            </div>
            <div class="div_inputList">
                <input type="text" readonly="readonly" value="${pishnehad.bimeShode.shakhs.shoghleFarei}" name="bimename.bimeshodeShoghleFari"/>
                <label>شغل فرعی:</label>
            </div>
        </div>
    </td>
</tr>
<tr>
    <td>
        مشخصات بیمه نامه&nbsp;&nbsp;
    </td>
    <td>
        <div style="width:90%">
            <div class="div_inputList">
                <input type="text" readonly="readonly" value="${pishnehad.estelam.mablagh_seporde_ebtedaye_sal}&nbsp;&nbsp;ریال" name="bimename.hagheBimeAvvaliye"/>
                حق بیمه اولیه:
            </div>
            <div class="div_inputList">
                <input type="text" readonly="readonly" value="${pishnehad.estelam.hagh_bime_pardakhti}&nbsp;&nbsp;ریال" name="bimename.hagheBimePardakhti"/>
                حق بیمه منظم پرداختی:
            </div>
            <div class="div_inputList">
                <input type="text" readonly="readonly" value='${pishnehad.estelam.nahve_pardakht_hagh_bime}' name="bimename.nahveyePardakht"/>
                نحوه پرداخت:
            </div>
            <div class="div_inputList">
                <input type="text" readonly="readonly" name="bimename.sarmayeFot" value="${pishnehad.estelam.sarmaye_paye_fot}&nbsp;&nbsp;ریال"/>
                سرمایه فوت:
            </div>
            <div class="div_inputList">
                <input type="text" readonly="readonly" value="${pishnehad.estelam.nerkh_afzayesh_salaneh_hagh_bime} &nbsp;&nbsp;درصد" name="bimename.nerkhAfzayeshHaghBime"/>
                نرخ افزايش حق بيمه:
            </div>
            <div class="div_inputList">
                <input type="text" readonly="readonly" value="${pishnehad.estelam.nerkh_afzayesh_salaneh_sarmaye_fot} &nbsp;&nbsp;درصد" name="bimename.nerkhAfzayeshSarmayeFot"/>
                نرخ افزايش سرمایه فوت:
            </div>
        </div>
    </td>
</tr>
<tr>
    <td>
        مدت&nbsp;&nbsp;
    </td>
    <td>
        <div style="width:90%">
            <div class="div_inputList">
                <input type="text" name="bimename.moddat" readonly="readonly" value="${pishnehad.estelam.modat_bimename}&nbsp;&nbsp;سال" />
                مدت:
            </div>
            <div class="div_inputList">
                <input type="text" name="bimename.tarikheSodur" readonly="readonly" value="<%=DateUtil.getCurrentDate()%>&nbsp;&nbsp;"/>
                تاریخ صدور:
            </div>
            <div class="div_inputList">
                <input type="text" name="bimename.tarikheShorou" />
                تاریخ شروع:
            </div>
            <div class="div_inputList">
                <input type="text" name="bimename.tarikheEngheza" readonly="readonly" />
                تاریخ انقضا
            </div>
        </div>
    </td>
</tr>
<tr>
    <td>
        شرایط خصوصی&nbsp;&nbsp;
    </td>
    <td>
        <textarea rows="5" cols="105" style="font-weight:normal;" name="bimename.sharayetKhosusi"></textarea>
    </td>
</tr>
<tr>
    <td>
        امضا کنندگان&nbsp;&nbsp;
    </td>
    <td>
        <div style="width:90%">
            <div class="div_inputList">
                <input type="text" name="bimename.emzakonandeAval"/>
                امضا کننده اول:
            </div>
            <div class="div_inputList">
                <input type="text" name="bimename.emzakonandeDovom"/>
                امضا کننده دوم:
            </div>
        </div>
    </td>
</tr>
<tr>
    <td>
        پوشش های اضافی&nbsp;&nbsp;
    </td>
    <td>
        <table align="center">
            <thead>
            <th>
                نام پوشش اضافی
            </th>
            <th>
                جزئیات خطر
            </th>
            <th>
                سرمایه تعهد
            </th>
            <th>
                نرخ تعهد
            </th>
            <th>
                تاریخ شروع
            </th>
            <th>
                تاریخ پایان
            </th>

            </thead>
            <tbody>
            <c:if test="${pishnehad.estelam.pooshesh_amraz_khas == 'yes'}">
                <tr>
                    <td>
                        پوشش امراض خاص
                    </td>
                    <td>
                        <select name="bimename.tabaghekhatarForAmrazKhaas">
                            <option value="1">طبقه 1</option>
                            <option value="2">طبقه 2</option>
                            <option value="3">طبقه 3</option>
                            <option value="4">طبقه 4</option>
                            <option value="5">طبقه 5</option>
                        </select>
                    </td>
                    <td>
                            ${pishnehad.estelam.sarmaye_pooshesh_amraz_khas}&nbsp;&nbsp;ریال
                    </td>
                    <td>
                        -
                    </td>
                    <td>
                        <%=DateUtil.getCurrentDate()%>
                    </td>
                    <td>
                        -
                    </td>
                </tr>
            </c:if>
            <c:if test="${pishnehad.estelam.pooshesh_fot_dar_asar_haadese == 'yes'}">
                <tr>
                    <td>
                        پوشش فوت در اثر حادثه
                    </td>
                    <td>
                        <select name="bimename.tabaghekhatarForHadese">
                            <option value="1">طبقه 1</option>
                            <option value="2">طبقه 2</option>
                            <option value="3">طبقه 3</option>
                            <option value="4">طبقه 4</option>
                            <option value="5">طبقه 5</option>
                        </select>
                    </td>
                    <td>
                            ${pishnehad.estelam.sarmaye_paye_fot_dar_asar_hadese}&nbsp;&nbsp;ریال
                    </td>
                    <td>
                        -
                    </td>
                    <td>
                        <%=DateUtil.getCurrentDate()%>
                    </td>
                    <td>
                        -
                    </td>
                </tr>
            </c:if>
            <c:if test="${pishnehad.estelam.pooshesh_fot_dar_asar_zelzele == 'yes'}">
                <tr>
                    <td>
                        پوشش فوت در اثر زلزله
                    </td>
                    <td>
                        <select name="bimename.tabaghekhatarForZelzele">
                            <option value="1">طبقه 1</option>
                            <option value="2">طبقه 2</option>
                            <option value="3">طبقه 3</option>
                            <option value="4">طبقه 4</option>
                            <option value="5">طبقه 5</option>
                        </select>
                    </td>
                    <td>
                        -
                    </td>
                    <td>
                        -
                    </td>
                    <td>
                        <%=DateUtil.getCurrentDate()%>
                    </td>
                    <td>
                        -
                    </td>
                </tr>
            </c:if>
            <c:if test="${pishnehad.estelam.pooshesh_naghs_ozv == 'yes'}">
                <tr>
                    <td>
                        پوشش نقص عضو
                    </td>
                    <td>
                        <select name="bimename.tabaghekhatarForNaghsOzv">
                            <option value="1">طبقه 1</option>
                            <option value="2">طبقه 2</option>
                            <option value="3">طبقه 3</option>
                            <option value="4">طبقه 4</option>
                            <option value="5">طبقه 5</option>
                        </select>
                    </td>
                    <td>
                            ${pishnehad.estelam.sarmaye_pooshesh_naghs_ozv}&nbsp;&nbsp;ریال
                    </td>
                    <td>
                        -
                    </td>
                    <td>
                        <%=DateUtil.getCurrentDate()%>
                    </td>
                    <td>
                        -
                    </td>
                </tr>
            </c:if>
            <c:if test="${pishnehad.estelam.pooshesh_moafiat == 'yes'}">
                <tr>
                    <td>
                        پوشش معافیت
                    </td>
                    <td>
                        <select name="bimename.tabaghekhatarForMoafiat">
                            <option value="1">طبقه 1</option>
                            <option value="2">طبقه 2</option>
                            <option value="3">طبقه 3</option>
                            <option value="4">طبقه 4</option>
                            <option value="5">طبقه 5</option>
                        </select>
                    </td>
                    <td>
                        -
                    </td>
                    <td>
                        -
                    </td>
                    <td>
                        <%=DateUtil.getCurrentDate()%>
                    </td>
                    <td>
                        -
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </td>
</tr>
</tbody>
</table>
<input type="hidden" name="pishnehadId" id="pishnehadId" value="${pishnehadId}"/>
<input type="hidden" name="transitionId" id="transitionId" value="${transitionId}"/>
<input type="hidden" name="logmessage" id="logmessage" value="${logmessage}"/>
<div style="height:40px;">
    <br/>
<div style="float:right;">
    <input type="button" onclick="$('#transitionNahaE').submit();" value="تایید نهایی" />
    <input type="button" onclick="window.location='/loginUser.action'" value="بازگشت"/>
</div>
    </div>
</div>
</form>
</html>