<%--
  Created by IntelliJ IDEA.
  User: ramtinb
  Date: 4/10/12
  Time: 10:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="st" uri="/struts-tags" %>
<%@ include file="/jsp/taglibs.jsp" %>

<html>
<head>
    <title>ثبت کاربر</title>
    <script type="text/javascript" language="JavaScript">
        function showHideTrNamayande() {
            if ($('#ROLE_NAMAYANDE').attr('checked') == true || $('#ROLE_BAZARYAB').attr('checked') == true || $('#ROLE_KARMOZD_NAMAYANDE').attr('checked') == true) {
                $('#trNamayande').show();
                $('#namayandei_name').removeAttr("disabled");
            }
            else {
                $('#trNamayande').hide();
                $('#namayandei_name').attr("disabled", true);
            }

            if ($('#ROLE_NAMAYANDE').attr('checked') == false || $('#ROLE_BAZARYAB').attr('checked') == false || $('#ROLE_KARMOZD_NAMAYANDE').attr('checked') == true) {
                $('#namayandei_id option').removeAttr('selected')
            }

        }
    </script>
</head>
<body>

<c:if test="${candidateUser.id == null}">
<form class="mainFrame" name="mainForm" method="post" action="/addUser.action">
    <tr>
        <td>-</td>
        <td>${fn:length(roleList)}</td>
    </tr>
    </c:if>
    <c:if test="${candidateUser.id != null}">
    <form class="mainFrame" name="mainForm" method="post" action="/editUser.action?candidateUser.id=${candidateUser.id}">
        </c:if>
        <s:actionmessage/>
        <s:actionerror/>
        <table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0"
               style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
            <thead>
            <tr>
                <th colspan="2">
                    ثبت کاربر
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>نام</td>
                <td><input type="text" name="candidateUser.firstName" value="${candidateUser.firstName}" id="user_firstName"  class="validate[required]"/>
                </td>
            </tr>
            <tr>
                <td>نام خانوادگی</td>
                <td><input type="text" name="candidateUser.lastName" value="${candidateUser.lastName}" id="user_lastName"  class="validate[required]"/>

                </td>
            </tr>
            <tr>
                <td>نام کاربری</td>
                <td><input type="text" name="candidateUser.username" value="${candidateUser.username}" id="username"
                ${candidateUser.id != null ? 'readonly=readonly' : 'class="validate[required]"'}
                        /></td>
            </tr>
            <tr>
                <td>رمز عبور</td>
                <td><input type="text" name="candidateUser.password" value="${candidateUser.password}" id="password"
                           class="validate[required]"/></td>
            </tr>
            <tr>
                <td>کد پرسنلی</td>
                <td><input type="text" name="candidateUser.personalCode" value="${candidateUser.personalCode}" id="personalCode"
                           class="validate[required,custom[onlyNumber]]"/></td>
            </tr>
            <tr>
                <td>تلفن همراه</td>
                <td><input type="text" name="candidateUser.mobile" value="${candidateUser.mobile}" id="mobile"
                           class="validate[custom[telephone_hamraah]] text-input ui-state-default ui-corner-all"/></td>
            </tr>

            <tr>
                <td>سمت سازمانی</td>
                <td>
                    <st:i18n name="com.bitarts.parsian.i18n.enumTypes_fa">
                        <st:select list="roleList" listKey="getText(roleName)" listValue="getText(roleName)"
                                   name="candidateUser.sematSazmani" value="candidateUser.sematSazmani" id="user_sematSazmani"
                                   theme="simple"/>
                    </st:i18n>
                </td>
            </tr>
            <tr>
                <td>فعال</td>
                <td><input type="checkbox" name="candidateUser.faal"
                           <c:if test="${candidateUser.faal}">checked="checked"</c:if> value="true"
                        /></td>
            </tr>
            <tr>
                <td>نام مجتمع صدور</td>
                <td>
                    <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('user_mojtamaSodorId','user_mojtamaSodorName', '');" style="float:left;" title="جستجو"></span>
                    <input type="hidden" name="candidateUser.mojtamaSodoor.id" value="${candidateUser.mojtamaSodoor.id}" id="user_mojtamaSodorId"/>
                    <input type="text" name="candidateUser.mojtamaSodoor.name" value="${candidateUser.mojtamaSodoor.name}" id="user_mojtamaSodorName" readonly="readonly" class="validate[required]"/>
                </td>
            </tr>
            <tr>
                <td>سمت سازمانی</td>
                <td>
                    <table cellpadding="1" cellspacing="1" border="0">
                        <fmt-rt:setBundle basename="com.bitarts.parsian.i18n.enumTypes_fa"/>
                        <c:forEach items="${roleList}" var="role" >
                            <tr>
                                <c:if test='${role.roleName == "ROLE_USER"}'>
                                    <td><input type="checkbox" name="selectedRoles" checked="checked" value="${role.id}"
                                               id="${role.roleName}"
                                               onclick="$('#${role.roleName}').attr('checked','true');"/></td>
                                </c:if>

                                <c:if test='${role.roleName == "ROLE_NAMAYANDE"}'>
                                    <c:if test="${selectedRoles.contains('ROLE_NAMAYANDE') == true}">
                                        <td><input type="checkbox" name="selectedRoles" value="${role.id}"
                                                   id="${role.roleName}" onclick="showHideTrNamayande()"
                                                   checked="checked"/></td>
                                    </c:if>
                                    <c:if test="${selectedRoles.contains('ROLE_NAMAYANDE') == false}">
                                        <td><input type="checkbox" name="selectedRoles" value="${role.id}"
                                                   id="${role.roleName}" onclick="showHideTrNamayande()"/></td>
                                    </c:if>
                                </c:if>

                                <c:if test='${role.roleName == "ROLE_KARMOZD_NAMAYANDE"}'>
                                    <c:if test="${selectedRoles.contains('ROLE_KARMOZD_NAMAYANDE') == true}">
                                        <td><input type="checkbox" name="selectedRoles" value="${role.id}"
                                                   id="${role.roleName}" onclick="showHideTrNamayande()"
                                                   checked="checked"/></td>
                                    </c:if>
                                    <c:if test="${selectedRoles.contains('ROLE_KARMOZD_NAMAYANDE') == false}">
                                        <td><input type="checkbox" name="selectedRoles" value="${role.id}"
                                                   id="${role.roleName}" onclick="showHideTrNamayande()"/></td>
                                    </c:if>
                                </c:if>

                                <c:if test='${role.roleName == "ROLE_BAZARYAB"}'>
                                    <c:if test="${selectedRoles.contains('ROLE_BAZARYAB') == true}">
                                        <td><input type="checkbox" name="selectedRoles" value="${role.id}"
                                                   id="${role.roleName}" onclick="showHideTrNamayande()"
                                                   checked="checked"/></td>
                                    </c:if>
                                    <c:if test="${selectedRoles.contains('ROLE_BAZARYAB') == false}">
                                        <td><input type="checkbox" name="selectedRoles" value="${role.id}"
                                                   id="${role.roleName}" onclick="showHideTrNamayande()"/></td>
                                    </c:if>
                                </c:if>

                                <c:if test='${role.roleName != "ROLE_USER" && role.roleName != "ROLE_NAMAYANDE" && role.roleName != "ROLE_BAZARYAB" && role.roleName != "ROLE_KARMOZD_NAMAYANDE"  }'>
                                    <c:if test="${selectedRoles.contains(role.roleName.toString()) == true}">
                                        <td><input type="checkbox" name="selectedRoles" value="${role.id}"
                                                   id="${role.roleName}" checked="checked"/></td>
                                    </c:if>

                                    <c:if test="${selectedRoles.contains(role.roleName.toString()) == false}">
                                        <td><input type="checkbox" name="selectedRoles" value="${role.id}"
                                                   id="${role.roleName}"/></td>
                                    </c:if>
                                </c:if>

                                <td align="right"><fmt-rt:message>${role.roleName}</fmt-rt:message></td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
            </tr>

            <c:if test='${selectedRoles.contains("ROLE_NAMAYANDE") == false}'>
                <script type="text/javascript">
                    $(document).ready(function () {
                        $('#trNamayande').hide();
                        $('#namayandei_name').attr("disabled", true);
                    });
                </script>
            </c:if>

            <tr id="trNamayande">
                <td>نمایندگی</td>
                <td>
                    <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('namayandei_id','namayandei_name', '');" style="float:left;" title="جستجو"></span>
                    <input type="hidden" name="candidateUser.namayandegi.id" id="namayandei_id" value="${candidateUser.namayandegi.id}"/>
                    <input id="namayandei_name" name="candidateUser.namayandegi.name" value="${candidateUser.namayandegi.name}" readonly="readonly" class="validate[required]"/>
                    <%--<select name="user.namayandegi.id" id="namayandei_id">--%>
                    <%--<c:forEach items="${namayandeList}" var="namayande">--%>
                    <%--<c:if test="${user.namayandegi.id ==  namayande.id}">--%>
                    <%--<option value="${namayande.id}" selected="selected">${namayande.name}</option>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${user.namayandegi.id !=  namayande.id}">--%>
                    <%--<option value="${namayande.id}">${namayande.name}</option>--%>
                    <%--</c:if>--%>
                    <%--</c:forEach>--%>
                    <%--</select>--%>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" id="btnSubmit" value="ثبت"/>
                    <input type="button" value="بازگشت"
                           onclick="window.location='/listAllUser'"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
</body>
</html>