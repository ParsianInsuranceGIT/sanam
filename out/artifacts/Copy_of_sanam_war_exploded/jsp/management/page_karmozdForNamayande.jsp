<%@ page import="com.bitarts.common.displaytag.PaginatedListImpl" %>
<%@ page import="com.bitarts.parsian.model.karmozd.KarmozdNamayande" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%--<script type="text/javascript">--%>
<%--&lt;%&ndash;&ndash;%&gt;--%>
        <%--$(function()--%>
        <%--{--%>
            <%--$('#selectAll').change(function(){--%>
                <%--$('input[name="_chk"]').each(function(){--%>
                    <%--$(this).attr('checked', $('#selectAll').attr('checked'));--%>
                <%--})--%>
            <%--});--%>
        <%--});--%>
        <%--function displaytagform_transition(formname)--%>
        <%--{--%>
            <%--var objfrm = document.forms[formname];--%>
            <%--if($(objfrm).serialize().indexOf('_chk') == -1) alertMessage("دسته سریالی انتخاب نشده است.");--%>
            <%--else $(objfrm).submit();--%>
        <%--}--%>

    <%--</script>--%>
<form action="karmozdPayment.action?karmozd.id=${karmozd.id}" method="POST" name="namayandeKarmozdFrm" id="n_k_frm">
    <display:table export="true" id="tblListKarmozdNamayande" uid="row" htmlId="tblListKarmozdNamayande"
                    name="paginatedListKarmozdNamayande.list" partialList="true" clearStatus="true" keepStatus="false"
                    style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
                    size="${ paginatedListKarmozdNamayande.fullListSize}" pagesize="${ paginatedListKarmozdNamayande.objectsPerPage}">
        <display:column title="ردیف">${row_rowNum}</display:column>
        <display:column  title="نام و کد نماینده" property="namayande.name_kod"/>
        <display:column title="میزان کارمزد پرداختی" property="amount"/>
        <display:column title="وضعیت ">${row.stateFarsi}</display:column>
    </display:table>
    <%
        if(((PaginatedListImpl<KarmozdNamayande>)request.getAttribute("paginatedListKarmozdNamayande")).getFullListSize()> 0)
        {
    %>
                <input type="submit" id="sbmt_n_k_frm"  value="پرداخت"/>
    <%
        }
    %>
</form>

