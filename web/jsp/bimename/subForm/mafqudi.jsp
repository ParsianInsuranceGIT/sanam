<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="mafghudForm">
          <table class="nonGrid" align="center" dir="rtl">
                <tr>
                    <td>
نام روزنامه کثیرالانتشار
                    </td>
                    <td>
                        <c:if test="${darkhastBazkharid!=null}">
                            <input type="text" name="darkhastBazkharid.nameRooznameh" id="nameRooznameh" value="${darkhastBazkharid.nameRooznameh}" style="width: 92%;" readonly="readonly"/>
                        </c:if>
                        <c:if test="${darkhastBazkharid==null}">
                            <input type="text" name="darkhastBazkharid.nameRooznameh" id="nameRooznameh" value="${darkhastBazkharid.nameRooznameh}" style="width: 92%;" class="validate[required]"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>
تاریخ اولین چاپ
                    </td>
                    <td>
                        <c:if test="${darkhastBazkharid==null}">
                            <input type="text" name="darkhastBazkharid.tarikhAvalinChap" id="tarikhAvalinChap" value="${darkhastBazkharid.tarikhAvalinChap}"  class="validate[required,custom[date]] datePkr"/>
                        </c:if>
                        <c:if test="${darkhastBazkharid!=null}">
                            <input type="text" name="darkhastBazkharid.tarikhAvalinChap" id="tarikhAvalinChap" value="${darkhastBazkharid.tarikhAvalinChap}"  readonly="readonly"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>
تاریخ دومین چاپ
                    </td>
                    <td>
                        <c:if test="${darkhastBazkharid!=null}">
                            <input type="text" name="darkhastBazkharid.tarikhDovominChap" id="tarikhDovominChap" value="${darkhastBazkharid.tarikhDovominChap}" readonly="readonly"/>
                        </c:if>
                        <c:if test="${darkhastBazkharid==null}">
                            <input type="text" name="darkhastBazkharid.tarikhDovominChap" id="tarikhDovominChap" value="${darkhastBazkharid.tarikhDovominChap}" class="validate[required,custom[date]] datePkr"/>
                        </c:if>
                    </td>
                </tr>
            </table>
</form>

<br/><br/>
<c:if test="${darkhastBazkharid.nameRooznameh != null}">
    <input type="button" value="پرینت فرم مفقودی" onclick="window.open('/printMafqudi?pishnehadReport.darkhastBazkharid.id=${darkhastBazkharid.id}');">
</c:if>
