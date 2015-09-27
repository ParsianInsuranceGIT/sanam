<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


    <table dir="rtl" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <tr>
            <td>
                <label>نقص مدارک</label>
            </td>
            <td>
                <c:if test="${pishnehad.naghsPishnehad.naghsMadaarek == '1'}">
                    <input disabled="disabled" id="naqs_madrak" type="checkbox" checked="checked" name="naghsPishnehad.naghsMadaarek"/>
                </c:if>
                <c:if test="${pishnehad.naghsPishnehad.naghsMadaarek != '1'}">
                    <input disabled="disabled" id="naqs_madrak" type="checkbox" name="naghsPishnehad.naghsMadaarek"/>
                </c:if>
            </td>
        </tr>
        <tr>
            <td>
                <label>نقص اطلاعات</label>
            </td>
            <td>
                <c:if test="${pishnehad.naghsPishnehad.naghsEtelaat == '1'}">
                    <input disabled="disabled" id="naqs_etelaat" type="checkbox" checked="checked" name="naghsPishnehad.naghsEtelaat"/>
                </c:if>
                <c:if test="${pishnehad.naghsPishnehad.naghsEtelaat != '1'}">
                    <input disabled="disabled" id="naqs_etelaat" type="checkbox" name="naghsPishnehad.naghsEtelaat"/>
                </c:if>
            </td>
        </tr>
        <tr id="naghsfishrow">
            <td>
                <label>نقص فیش</label>
            </td>
            <td>
                <c:if test="${pishnehad.naghsPishnehad.naghsFish == '1'}">
                    <input disabled="disabled" id="naqs_fish" type="checkbox" checked="checked" name="naghsPishnehad.naghsFish"/>
                </c:if>
                <c:if test="${pishnehad.naghsPishnehad.naghsFish != '1'}">
                    <input disabled="disabled" id="naqs_fish" type="checkbox" name="naghsPishnehad.naghsFish"/>
                </c:if>
            </td>
        </tr>
        <tr>
            <td>
                توضیحات:
            </td>
            <td>
                <textarea disabled="disabled" id="naqs_tozihat" rows="5" cols="20" name="naghsPishnehad.tozihat">${pishnehad.naghsPishnehad.tozihat}</textarea>
            </td>
        </tr>
    </table>