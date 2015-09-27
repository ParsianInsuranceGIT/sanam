<%@ page import="com.bitarts.parsian.model.pishnahad.Pishnehad" %>
<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.model.pishnahad.Fish" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Pishnehad pishnehadRun = (Pishnehad) request.getAttribute("pishnehadRun");
%>

<c:set var="pishnehad" value="<%=pishnehadRun%>"/>
        <table id="table4elamshomarehesabforenseraf" dir="rtl" cellpadding="0" class="mystrippedtable" cellspacing="0" style="width:88%;border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
            <tr class="odd">
                <td>
                    تاریخ:&nbsp;&nbsp;<b><%=DateUtil.getCurrentDate()%></b>
                    <br/>
                    جناب آقای
                    <br/>
                    رئیس محترم اداره ی حسابداری عملیات
                    <br/>
                    احتراما با توجه به منع صدور بیمه گذار، مقرر فرمایید چک به مبلغ &nbsp;<input type="text" readonly="readonly" id="elamenseraf_mablagh" name="elameHesab.mablagh" value="${pishnehad.elameEnseraf.mablagh}"/>&nbsp;&nbsp; در وجه آقای &nbsp;
                    <b>${pishnehad.bimeGozar.shakhs.name}&nbsp;${pishnehad.bimeGozar.shakhs.nameKhanevadegi}</b> &nbsp;&nbsp; صادر و به حساب ایشان واریز گردد.
                    <br/>
                    کد رهگیری: &nbsp;&nbsp;<b>${pishnehad.radif}</b>
                    <br/>
                    شماره حساب: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" readonly="readonly" name="elameHesab.shomareHesabBanki" id="elamenseraf_shomarehesab" value="${pishnehad.elameEnseraf.shomareHesabBanki}"/> &nbsp;&nbsp;
                    <br/>
                    بانک: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input  type="text" readonly="readonly" id="elameenseraf_bankname" name="elameHesab.bankName" value="${pishnehad.elameEnseraf.bankName}"/>&nbsp;&nbsp;
                    <br/>
                    نام شعبه:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="elameenseraf_shobename" readonly="readonly" name="elameHesab.shobeName" value="${pishnehad.elameEnseraf.shobeName}"/>
                    <br/>
                    شبا: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" readonly="readonly" name="elameHesab.shomareShaba" value="${pishnehad.elameEnseraf.shomareShaba}" id="elamenseraf_shomaresahba"/>IR
                    <br/>
                    مبلغ پیش پرداخت:&nbsp;&nbsp;
                    <%
                        List<Fish> fishs = pishnehadRun.getFishs();
                        double majmu = 0;
                        if(fishs!=null){
                            for (Fish fish : fishs) {
                                if(fish.getFound().equalsIgnoreCase("true")){
                                    majmu +=Math.round(Double.valueOf(fish.getMablagh().replaceAll(",","")));
                                }
                            }
                        }
                    %>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" readonly="readonly" id="mablagh_pishpardakht" value="<%=majmu%>"/>
                    <br/>
                    <br/>
                </td>
            </tr>
        </table>