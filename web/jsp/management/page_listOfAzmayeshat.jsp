<%@ page import="com.bitarts.parsian.model.Clinic" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
%>
<head>
    <title>مشاهده لیست آزمایشات کلینیک</title>
</head>
<% Clinic clinic = (Clinic) request.getAttribute("clinic");
%>

<div class=expandableTitleBar>
    <p class=heading ><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        آزمایشات ثبت شده در: &nbsp;&nbsp;&nbsp; <%=clinic.getClinicName()%>
    </p>
    <div class="staticTitleBar" id="result" style="direction:rtl;">
        <table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin:0 auto;" border="0">
            <thead>
            <s:if test="azmayeshat == null || azmayeshat.size == 0">
                <tr><td></td></tr>
                <tr>
                    <th colspan="5" width="150px">اطلاعاتی یافت نشد</th>
                </tr>
                <tr><td></td></tr>
            </s:if>
            <s:else>
                <tr>
                    <th >نوع آزمایش </th>
                    <th >نام آزمایش</th>
                    <th >قیمت (ریال)</th>
                    <th >توضیحات</th>
                </tr>
            </s:else>
            </thead>
            <tbody>

            <s:iterator value="azmayeshat" id="row" status="stat">
                <tr >
                        <%--<td/><s:property value="#row.getType()"/> <s:property value="#row.getBimeGozar().getShakhs().getNameKhanevadegi()"/>&nbsp;</td>--%>
                        <%--<td/><s:property value="#row.getName()"/> <s:property value="#row.getBimeGozar().getShakhs().getNameKhanevadegi()"/>&nbsp;</td>--%>
                    <td/><s:property value="#row.getAzmayeshName().getAzmayeshType().getType()"/>&nbsp;</td>
                    <td/><s:property value="#row.getAzmayeshName().getName()"/>&nbsp;</td>
                    <td/><s:property value="#row.getPrice()"/>&nbsp;</td>
                    <td/><s:property value="#row.getDescription()"/>&nbsp;</td>
                    <td style="border:none;"/><input type="button" value="ویرایش" onclick="window.location='/editAzmayesh?azmayesh.id=<s:property value="#row.getId()"/>&clinic.id=<%=request.getParameter("clinic.id")%>'"/> </td>
                    <td style="border:none;"/>
                            <input type="button" value="حذف" style="width:50px"
               onclick="
               confirmMessage('آیا از حذف (<s:property value="#row.getAzmayeshName().getName()"/>) مطمئن هستید؟',
               '', function(){window.location='/removeAzmayesh?azmayesh.id=<s:property value="#row.getId()"/>&clinic.id=<%=request.getParameter("clinic.id")%>';})"/>
                    </td>
                </tr>
            </s:iterator>


            </tbody>
        </table>
    </div>
    <br>
    <div>
        <input type="button" onclick="window.location='/prepareAzmayeshAddition?clinic.id=<%=request.getParameter("clinic.id")%>'" value="اضافه کردن آزمایش"/>
        <input type="button" onclick="window.location='/moshahedeClinics'" value="بازگشت"/>
    </div>
</div>