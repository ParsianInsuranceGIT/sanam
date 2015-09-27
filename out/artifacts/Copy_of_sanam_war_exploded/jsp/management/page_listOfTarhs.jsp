<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>طرح ها</title>
</head>
<div class=expandableTitleBar>
    <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        لیست طرح ها
    </p>

    <div class="staticTitleBar" id="result" style="direction:rtl;">
        <table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0"
               style="border-spacing:1px;margin:0 auto;" border="0">
            <thead>
            <s:if test="tarhs == null || tarhs.size == 0">
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <th colspan="5" width="150px">اطلاعاتی یافت نشد</th>
                </tr>
                <tr>
                    <td></td>
                </tr>
            </s:if>
            <s:else>
                <tr>
                    <th>شماره</th>
                    <th>نام طرح</th>
                </tr>
            </s:else>
            </thead>
            <tbody>
            <s:iterator value="tarhs" id="row" status="stat">
                <tr>
                    <td><s:property value="#row.getId()"/></td>
                    <td><s:property value="#row.getName()"/></td>
                    <td style="border:none;">
                    <input type="button" value="ویرایش"
                           onclick="window.location='/addTarhPrepare?tarh.id=<s:property value="#row.getId()"/>'"/></td>
                    <td style="border:none;">
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
    <br>
    <div>
        <input type="button" onclick="window.location='jsp/management/page_mainManagementPage.jsp'" value="بازگشت"/>
        <input type="button" onclick="window.location='jsp/management/page_addTarh.jsp'" value="ثبت طرح"/>
    </div>


</div>