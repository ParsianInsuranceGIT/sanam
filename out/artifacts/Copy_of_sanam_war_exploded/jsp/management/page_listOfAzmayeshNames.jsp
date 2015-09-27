<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
%>
<head>
    <title>لیست نوع آزمایش ها</title>
</head>
<div class=expandableTitleBar>
    <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        لیست نام آزمایشهای ثبت شده
    </p>

    <div class="staticTitleBar" id="result" style="direction:rtl;">

        <s:if test="sessionScope.listAzmayeshNames == null || sessionScope.listAzmayeshNames.list.size == 0">
            <table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0"
                   style="border-spacing:1px;margin:0 auto;" border="0">
                <thead>
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <th colspan="5" width="150px">اطلاعاتی یافت نشد</th>
                </tr>
                <tr>
                    <td></td>
                </tr>
                </thead>
            </table>
        </s:if>
        <s:else>
            <form action="/listAllAzmayeshNames" method="get">
                <table dir="rtl" class="inputList">
                    <tr>
                        <td>نوع آزمایش:</td>
                        <td><s:textfield key="snoe" label="" theme="simple"/></td>
                        <td>نام آزمایش:</td>
                        <td>
                            <s:textfield key="sname" label="" theme="simple"/>
                        </td>
                    </tr>
                    <tr>
                        <td><s:submit value="جستجو" theme="simple"/></td>
                    </tr>
                </table>
            </form>
            <display:table export="false" id="tblListAzmayeshs" uid="azmayesh" htmlId="tblListAzmayeshs"
                           name="sessionScope.listAzmayeshNames.list" partialList="true" clearStatus="true"
                           keepStatus="false"
                           style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
                           size="${sessionScope.listAzmayeshNames.fullListSize}"
                           pagesize="${sessionScope.listAzmayeshNames.objectsPerPage}">
                <display:column property="azmayeshType.type" title="نوع آزمایش"></display:column>
                <display:column property="name" title="نام آزمایش"></display:column>
                <display:column property="description" title="توضیحات"></display:column>
                <display:column media="html"><input type="button" value="ویرایش"
                                                    onclick="window.location='/editAzmayeshName?azmayeshName.id=${azmayesh.id}'"/></display:column>
                <%--<display:column media="html">--%>
                    <%--<input type="button" value="حذف"--%>
                    <%--onclick="confirmMessage('آیا از حذف آزمایش (${azmayesh.name}) مطمئن هستید؟',--%>
                    <%--'', function(){window.location='/removeAzmayeshName.action?azmayeshName.id=${azmayesh.id}';})"/>--%>
                <%--</display:column>--%>
            </display:table>
        </s:else>

    </div>
    <br>
    <div>
        <input type="button" onclick="window.location='jsp/management/page_mainManagementPage.jsp'" value="بازگشت"/>
        <input type="button" onclick="window.location='/prepareForSabtNameAzmayesh'" value="ثبت نام آزمایش"/>
    </div>


</div>