<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
%>
<head>
     <title>اضافه کردن آزمایش</title>
</head>
<form class="mainFrame" id="mainForm" method="post" action="/submitAzmayesh?clinic.id=<%=request.getParameter("clinic.id")%>">
<table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <thead>
        <tr>
            <th colspan="2">
                ثبت آزمایش:
            </th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>
                نام آزمایش:&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <select name="azmayeshname" id="azmayeshnameselect" onchange="azmayeshTypeHandler();">
                    <option value='-1'>لطفا انتخاب نمایید</option>
                    <c:forEach var="row" items="${azmayeshNames}">
                        <c:if test="${azmayeshName.id == row.id}">
                            <option selected="selected" value='<c:out value="${row.id}"/>'><c:out value="${row.name}"/></option>
                        </c:if>
                        <c:if test="${azmayeshName.id != row.id}">
                            <option value='<c:out value="${row.id}"/>'><c:out value="${row.name}"/></option>
                        </c:if>
                    </c:forEach>
                </select>
                <input type="hidden" name="azmayesh.azmayeshName.name" id="azmayeshname" value="${azmayeshName.name}"/>
                <input type="hidden" name="azmayesh.azmayeshName.id" id="azmayeshNameId" value="${azmayeshName.id}"/>
                <input type="hidden" name="azmayesh.azmayeshName.azmayeshType.id" id="azmayeshTypeId" value="${azmayeshName.azmayeshType.id}"/>
                <input type="hidden" name="azmayesh.azmayeshName.azmayeshType.type" id="azmayeshTypetype" value="${azmayeshName.azmayeshType.type}"/>
                <input type="hidden" name="azmayesh.id" id="azmayeshid" value="${azmayesh.id}"/>


                <script type="text/javascript">
                function azmayeshTypeHandler(){
                          var str = "";
                          var txt = "";
                          $("select option:selected").each(function () {
                                str += $(this).val() + "";
                                txt = $(this).text()+"";
                              });

                    window.location='/azmayeshTypeFetcher?azmayeshName.id='+str+'&clinic.id=<%=request.getParameter("clinic.id")%>' + '&azmayesh.id=${azmayesh.id}';

                }
                </script>

            </td>
        </tr>
        <tr>
            <td>
                نوع آزمایش&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <input type="text"  id="azmayeshType" readonly="readonly" value="${azmayeshType.type}"/>
            </td>
        </tr>
        <tr>
            <td>
                قیمت:&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <input type="text" name="azmayesh.price" id="azmayeshPrice" value="${azmayesh.price}" />
            </td>
        </tr>
        <tr>
            <td>
                توضیحات:&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td>
                <textarea rows="5" cols="50" name="azmayesh.description" id="azmayeshDescription" >${azmayesh.description}</textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="ثبت" />
                <input type="button" onclick="window.location='/findAzmayeshat?clinic.id=${clinic.id}'" value="برگشت"/>
            </td>
        </tr>
        <c:if test="${errorsMap['duplicateName'] !=null}">
            <tr>
                <td>
                    <p style="color: red;">
                        <img src="/img/error.png" height="15px" width="15px"/> &nbsp;<c:out value="${errorsMap['duplicateName']}"/>
                    </p>
                </td>
            </tr>
        </c:if>
    </tbody>
</table>
</form>