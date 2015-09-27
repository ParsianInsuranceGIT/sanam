<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="jtable resultDets">
    <tr dir="rtl">
        <td>
            نوع دریافت کننده
        </td>
        <td>
            نام دریافت کننده
        </td>
        <td>
            شماره داخلی
        </td>
        <td>
            نوع حواله
        </td>
        <td>
            عملکرد حواله
        </td>
        <td>
            شماره حواله
        </td>
        <td>
            تاریخ حواله
        </td>
        <td>
            مبلغ حواله
        </td>
        <td>
            شناسه پرداخت
        </td>
    </tr>
    <s:iterator value="khesarat.havaleList" status="stat">
        <tr>
            <td><s:property value="typeRecieverFarsi"/></td>
            <td><s:property value="name"/></td>
            <td><s:property value="shomareDakheli"/></td>
            <td><s:property value="typeHavale"/></td>
            <td><s:property value="amalkardHavale"/></td>
            <td><s:property value="shomareHavale"/></td>
            <td><s:property value="tarikhHavale"/></td>
            <td><s:property value="amountHavale"/></td>
            <td><s:property value="credebit.shenaseCredebit"/></td>
        </tr>
    </s:iterator>
</table>
<input type="button" value="پرینت"
       onclick="window.open('/printKhesaratHavale?khesarat.id=<s:property value="khesarat.id"/>')">