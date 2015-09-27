<%@ page import="java.util.List" %>
<%@ page import="com.bitarts.parsian.model.asnadeSodor.Ghest" %>
<%@ page import="com.bitarts.parsian.model.asnadeSodor.Credebit" %>
<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.model.asnadeSodor.Sanad" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
    List kolli = (List) request.getAttribute("tarakoneshat");
    List<Sanad> sanads = (List<Sanad>) request.getAttribute("sanadList");
    String pishnehadId = (String) request.getAttribute("pishnehadId");
%>

<head>
<title>نتیجه ی محاسبات فنی دریافت و پرداخت مشتری </title>
</head>
<div class=expandableTitleBar>
    <p class=heading ><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
نتیجه ی محاسبات فنی دریافت وپرداخت برای مشتری
    </p>
    <div class="staticTitleBar" id="result" style="direction:rtl;">
        <table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin:0 auto;" border="0">
            <thead>
               <s:if test="sanadList== null || sanadList.size == 0">
                   <tr><td></td></tr>
                   <tr>
                       <th colspan="5" width="150px">اطلاعاتی یافت نشد</th>
                   </tr>
                   <tr><td></td></tr>
               </s:if>
        <s:else>
            <tr>
                <th>ردیف</th>
                <th>نوع</th>
                <th>حق بیمه پرداختی</th>
                <th>تاریخ</th>
                <th>هزینه کارمزد</th>
                <th>هزینه بیمه گری</th>
                <th>هزینه اداری</th>
                <th>هزینه وصول</th>
                <th>حق بیمه خالص فوت یکجا</th>
                <th>سرمایه فوت به هر علت</th>
                <th>سرمایه فوت در اثر حادثه</th>
                <th>سرمایه پوشش اضافی امراض خاص</th>
                <th>حق بیمه پوشش های اضافی</th>
                <th>تفاوت زمانی</th>
                <th>ارزش آتی</th>
            </tr>

            </thead>
            <tbody>
            <%
                int counter = 0;
                double finalAmount = 0;
                double finallAmount = 0;
                for(Sanad sanad:sanads){
                    if(sanad.getBedehis().iterator().next().getPishnehad().getId().equals(Integer.parseInt(pishnehadId))){
            %>
                <tr >
                    <td>
                        <%=++counter%>
                    </td>
                    <td>
قسط
                    </td>
                    <td>
                        <%=(Math.round(Double.parseDouble(sanad.getBedehi().getAmount())))+"-"%>
                    </td>
                    <td>
                        <%=sanad.getBedehi().getSarresidDate()%>
                    </td>
                    <td>
                        <%=Math.round(Double.parseDouble(sanad.getBedehi().getHazineKarmonz()))+"-"%>
                    </td>
                    <td>
                        <%=Math.round(Double.parseDouble(sanad.getBedehi().getHazineBimegari()))+"-"%>
                    </td>
                    <td>
                        <%=Math.round(Double.parseDouble(sanad.getBedehi().getHazineEdari()))+"-"%>
                    </td>
                    <td>
                        <%=Math.round(Double.parseDouble(sanad.getBedehi().getHazineVosoul()))+"-"%>
                    </td>
                    <td>
                        <%=Math.round(Double.parseDouble(sanad.getBedehi().getHaghBimeFotYekja()))+"-"%>
                    </td>
                    <td>
                        <%=Math.round(Double.parseDouble(sanad.getBedehi().getSarmayeFotBeHarEllat()))+"-"%>
                    </td>
                    <td>
                        <%=Math.round(Double.parseDouble(sanad.getBedehi().getSarmayeFotDarAsarHadeseh()))+"-"%>
                    </td>
                    <td>
                        <%=Math.round(Double.parseDouble(sanad.getBedehi().getSarmayePoosheshEzafiAmraazKhaas()))+"-"%>
                    </td>
                    <td>
                        <%=Math.round(Double.parseDouble(sanad.getBedehi().getHaghBimePoosheshhayeEzafi()))+"-"%>
                    </td>
                    <td>
                        <%
                            int difffDate = DateUtil.getTimeDifferenceByDayCount(DateUtil.getCurrentDate(), sanad.getBedehi().getSarresidDate());
                            double amounttVal = Double.parseDouble(sanad.getBedehi().getAmount());
                            amounttVal = amounttVal * -1;
                            double difffperyear = difffDate/365.0;
                            double difffperyearenchanted = Math.pow(1.15,difffperyear);
                            double amounttEnchanted = amounttVal*difffperyearenchanted;
                            finalAmount += amounttEnchanted;
                        %>
                        <%= difffDate%>
                    </td>
                    <td>
                         <%=Math.round(amounttEnchanted)*-1+"-"%>
                    </td>
                </tr>
                <tr >
                    <td>
                        <%=++counter%>
                    </td>
                    <td>
                        پرداخت
                    </td>
                    <td>
                        <%=Math.round(Double.parseDouble(sanad.getCredebit().getAmount()))%>
                    </td>
                    <td dir="ltr">
                        <%=sanad.getCredebit().getCreatedDate().split("/")[2]%>/<%=sanad.getCredebit().getCreatedDate().split("/")[1]%>/<%=sanad.getCredebit().getCreatedDate().split("/")[0]%>
                    </td>
                    <td>
                         -
                    </td>
                    <td>
                         -
                    </td>
                    <td>
                         -
                    </td>
                    <td>
                         -
                    </td>
                    <td>
                         -
                    </td>
                    <td>
                         -
                    </td>
                    <td>
                         -
                    </td>
                    <td>
                         -
                    </td>
                    <td>
                         -
                    </td>
                    <td>
                        <%
                            int diffDate = DateUtil.getTimeDifferenceByDayCount(DateUtil.getCurrentDate(), sanad.getCredebit().getCreatedDate());
                            int amountVal = Integer.parseInt(sanad.getCredebit().getAmount());
                            double diffperyear = diffDate/365.0;
                            double diffperyearenchanted = Math.pow(1.15,diffperyear);
                            double amountEnchanted = amountVal*diffperyearenchanted;
                            finalAmount += amountEnchanted;
                        %>
                        <%= diffDate%>
                    </td>
                    <td>
                         <%=Math.round(amountEnchanted)%>
                    </td>
                </tr>
            <%
                    }
            }
            %>
            <tr>
                <td>
                    <%=++counter%>
                </td>
                <td colspan="6">
                    جمع کل
                </td>
                <td colspan="8" style="color:white;background:gray;">
                    <%
                        if(finalAmount < 0){
                    %>
                            <%=Math.round(finalAmount)*-1+"-"%>
                    <%
                        }else{
                    %>
                            <%=Math.round(finalAmount)%>
                    <%
                        }
                    %>
                </td>
            </tr>
            </tbody>
        </table>
        </s:else>
    </div>
    <br/>
     <div style="height:30px;">&nbsp;</div>
</div>