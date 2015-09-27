<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<display:table export="true"  id="khateSanadListTbl1" uid="row" name="khateSanadListPaginated"
               sort="external" htmlId="khateSanadListTbl1"
               partialList="true"
               size="${khateSanadListPaginated.fullListSize}"
               pagesize="${khateSanadListPaginated.objectsPerPage}"
               requestURI="" clearStatus="true" keepStatus="false"  cellpadding="10"
               excludedParams="" style=" width: 100%; margin: 0 auto;" >

    <display:column title="نوع بدهی" property="bedehiCredebit.credebitTypeFarsi" style="border-width:thin; border-color:#4297d7; border-style:solid"/>
    <display:column title="بدهی باقی مانده" property="bedehiCredebit.remainingAmount" style="border-width:thin; border-color:#4297d7; border-style:solid"/>
    <display:column title="شناسایی بدهی/شناسه پرداخت" style="border-width:thin; border-color:#4297d7; border-style:solid">
        ${row.bedehiCredebit.shomareMoshtari == null? '-':row.bedehiCredebit.shomareMoshtari}/${row.bedehiCredebit.shenaseCredebit == null? '-':row.bedehiCredebit.shenaseCredebit}
    </display:column>
    <display:column  title="شماره بیمه نامه" style="border-width:thin; border-color:#4297d7; border-style:solid">
        ${row.bedehiCredebit.bimename.shomare}
    </display:column>
    <display:column title="نوع اعتبار" property="etebarCredebit.credebitTypeFarsi" style="border-width:thin; border-color:#4297d7; border-style:solid"/>
    <display:column title="اعتبار باقی مانده" property="etebarCredebit.remainingAmount" style="border-width:thin; border-color:#4297d7; border-style:solid"/>
    <display:column title="شناسایی اعتبار/شناسه پرداخت" style="border-width:thin; border-color:#4297d7;border-style:solid">
        ${row.etebarCredebit.shomareMoshtari == null? '-':row.etebarCredebit.shomareMoshtari}/${row.etebarCredebit.shenaseCredebit == null? '-':row.etebarCredebit.shenaseCredebit}
    </display:column>
    <display:column property="amount" title="مقدار سند" style="border-width:thin; border-color:#4297d7; border-style:solid"/>
    <display:column title="ردیف" style="border-style:solid; border-width:thin; border-color:#4297d7">${row_rowNum}</display:column>
    <%--<display:column title="شماره سند" property="sanad.shomare"/>--%>
    <%--<display:column title="نوع سند">${row.sanad.noeSanad == "GHABZE_RESID"? "قبض رسید": row.sanad.noeSanad == "PARDAKHT"? "پرداخت":row.noeSanad}</display:column>--%>
    <%--<display:column title="وضعیت سند" >${row.sanad.vaziat == "DAEM"? "دائم":"موقت"}</display:column>--%>
    <%--<display:column title="زمان ثبت" property="sanad.createdDate"/>--%>
</display:table>