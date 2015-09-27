<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<dispay:table name="gharardad.credebitList" uid="rpResult">
    <dispay:column title="ردیف">${rpResult_rowNum}</dispay:column>
    <dispay:column title="شناسه پرداخت" property="shenaseCredebit"/>
    <dispay:column title="تاریخ" property="createdDate"/>
    <dispay:column title="مبلغ" property="amount"/>
</dispay:table>
