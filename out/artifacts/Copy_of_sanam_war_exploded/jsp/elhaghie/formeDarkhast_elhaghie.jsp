<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="darkhastBazkharids.size()>0">

    <display:table export="true" id="tableforaslid" uid="rpResult" name="darkhastBazkharids" sort="external"
                   partialList="true" htmlId="tblforaslid"
                   size="${darkhastBazkharids.size()}" pagesize="${darkhastBazkharids.size()}"
                   requestURI="" clearStatus="true" keepStatus="false" style="width: 100%; margin: 0 auto;">
        <%
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
        %>
        <display:column property="shomareDarkhast" title="شماره" style="background:${theColor};"/>
        <display:column property="elhaghie.tarikhAsar" title="تاریخ صدور" style="background:${theColor};"/>
        <display:column title="جزییات" style="background:${theColor};">
            <s:if test="elhaghie.darkhastBazkharid.darkhastType.toString().equals('VAM')">
                <s:url action="printSuratVaziatGhestVam" id="printSuratVaziat">
                    <s:param name="darkhastBazkharid.id">${rpResult.id}</s:param>
                </s:url>
                <s:url action="printDaftarcheGhestVamParsian" id="printDaftarcheParsian">
                    <s:param name="darkhastBazkharid.id">${rpResult.id}</s:param>
                </s:url>
                <s:url action="printDaftarcheGhestVamTejarat" id="printDaftarcheTejarat">
                    <s:param name="darkhastBazkharid.id">${rpResult.id}</s:param>
                </s:url>
                <s:url action="printSuratVaziatMaliVam" id="printSuratVaziatMaliVam">
                    <s:param name="darkhastBazkharid.id">${rpResult.id}</s:param>
                </s:url>
                <input type="button" onclick="window.open('${printSuratVaziatMaliVam}');" value="صورت وضعیت مالی وام">
                <input type="button" onclick="window.open('${printSuratVaziat}');" value="صورت وضعیت اقساط وام">
                <input type="button" onclick="window.open('${printDaftarcheParsian}');"
                       value="دفترچه اقساط وام پارسیان">
                <input type="button" onclick="window.open('${printDaftarcheTejarat}');" value="دفترچه اقساط وام تجارت">
            </s:if>
            <s:if test="elhaghie.darkhastBazkharid.darkhastType.toString().equals('BARDASHT_AZ_ANDOKHTE')">
                <s:url action="printJoziatBardasht" id="printJoziatBardasht">
                    <s:param name="darkhastBazkharid.id">${rpResult.id}</s:param>
                </s:url>
                <input type="button" onclick="window.open('${printJoziatBardasht}');" value="جزییات برداشت از اندوخته">
            </s:if>
        </display:column>

    </display:table>
</s:if>