<%@ page import="com.bitarts.parsian.model.transitionwise.PezeshkSabtNazar" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
    int sumUp = 0;
    List<PezeshkSabtNazar> nazaraat = (List<PezeshkSabtNazar>) request.getAttribute("nazaraat");
    for(PezeshkSabtNazar pezeshkSabtNazar:nazaraat){
        sumUp += Integer.parseInt(pezeshkSabtNazar.getEzafeNerkh());
    }
%>
<head>
     <title>لیست نظرات رئیس اداره در مورد پیشنهاد</title>
</head>
<div class=expandableTitleBar>
    <p class=heading ><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
نظرات ثبت شده توسط پزشک    </p>
    <div class="staticTitleBar" id="result" style="direction:rtl;">
        <table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin:0 auto;" border="0">
            <thead>
               <s:if test="nazaraat == null || nazaraat.size == 0">
                   <tr><td></td></tr>
                   <tr>
                       <th colspan="5" width="150px">اطلاعاتی یافت نشد</th>
                   </tr>
                   <tr><td></td></tr>
               </s:if>
        <s:else>
            <tr>
                <th > توضیحات مربوطه</th>
                <th >اضافه نرخ پزشکی</th>
            </tr>
            </s:else>
            </thead>
            <tbody>
            <s:iterator value="nazaraat" id="row" status="stat">
                <tr >
                    <td/><s:property value="#row.getNazar()"/>&nbsp;</td>
                    <td/><s:property value="#row.getEzafeNerkh()"/>&nbsp;</td>
                </tr>
            </s:iterator>
                <tr>
                    <td></td>
                    <td>
                        جمع اضافه نرخ:&nbsp;&nbsp;&nbsp;<%=sumUp%>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<div class=expandableTitleBar>
    <p class=heading ><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
ثبت نظر شما</p>
    <form action="makeFinalTransition.action" id="transitionNahaE" method="post" accept-charset="UTF-8">
        <input type="hidden" name="pishnehadId" id="pishnehadId" value="<%=request.getParameter("pishnehadId")%>"/>
        <input type="hidden" name="transitionId" id="transitionId" value="<%=request.getParameter("transitionId")%>"/>
        <input type="hidden" name="logmessage" id="logmessage" value="<%=request.getParameter("logmessage")%>"/>
    </form>
    <form action="redirectToAddNazarRaeis.action" id="redirectAddNazar" method="post" accept-charset="UTF-8">
        <input type="hidden" name="pishnehadId" id="pishnehadIdNazar" value="<%=request.getParameter("pishnehadId")%>"/>
        <input type="hidden" name="transitionId" id="transitionIdNazar" value="<%=request.getParameter("transitionId")%>"/>
        <input type="hidden" name="logmessage" id="logmessageNazar" value="<%=request.getParameter("logmessage")%>"/>
        <input type="hidden" name="sumup" id="sumup" value="<%=sumUp%>"/>
    </form>
    <div style="float:right;">
        <input type="button" onclick="submitRedirect();" value="نظر نهایی"/>
        <input type="button" onclick="window.location='/loginUser.action'" value="بازگشت"/>
    </div>
</div>
<script type="text/javascript">
    function submitRedirect(){
        $("#redirectAddNazar").submit();
    }
</script>
