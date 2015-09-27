<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/jsp/taglibs.jsp" %>
<div align="center">
    <table class="jtable">
        <s:if test="pishnehad!=null">
            <s:if test="pishnehad.bimename.darkhastBahreMandiFinal.size()>0">
                <s:iterator value="pishnehad.bimename.darkhastBahreMandiFinal" status="stat" id="row">
                    <s:if test="%{#stat.index==0}">
                        <tr>
                            <%--<th>ردیف</th>--%>
                            <th>نوع بهره مندی از منافع</th>
                            <th>شماره</th>
                            <th>تاریخ صدور</th>
                            <th>جزئیات</th>
                        </tr>
                    </s:if>
                    <tr>
                        <%--<td><s:property value="%{#stat.index+1}"/></td>--%>
                        <td><s:property value="#row.getDarkhstTypeFarsi()"/>&nbsp;</td>
                        <td>
                            <c:if test="${row.darkhastType=='VAM'}">
                                <s:property value="#row.getShomareVam()"/>&nbsp;
                            </c:if>
                            <c:if test="${row.darkhastType=='BARDASHT_AZ_ANDOKHTE'}">
                                <s:property value="#row.getShomareBardashtAzAndukhte()"/>&nbsp;
                            </c:if>
                        </td>
                        <td><s:property value="#row.getTarikhDarkhast()"/>&nbsp;</td>
                        <td>
                            <c:if test="${row.darkhastType=='VAM'}">
                                <input type="button" class="noAnyDisable"
                                       onclick="window.open('/printDaftarcheGhestVamParsian.action?darkhastBazkharid.id=<s:property value="#row.getId()"/>');"
                                       style="float:left;margin-left:2px;"
                                       value="چاپ دفترچه اقساط وام پارسیان"/>
                                <input type="button" class="noAnyDisable"
                                       onclick="window.open('/printDaftarcheGhestVamTejarat.action?darkhastBazkharid.id=<s:property value="#row.getId()"/>');"
                                       style="float:left;margin-left:2px;"
                                       value="چاپ دفترچه اقساط وام تجارت"/>
                                <input type="button"
                                       onclick="window.open('/printSuratVaziatGhestVam.action?darkhastBazkharid.id=<s:property value="#row.getId()"/>');"
                                       style="float:left;margin-left:2px;"
                                       value="صورت وضعیت اقساط وام">
                            </c:if>
                        </td>
                    </tr>
                </s:iterator>
            </s:if>
        </s:if>
        <%--<s:if test="darkhastBazkharid!=null">--%>
            <%--<s:if test="darkhastBazkharid.bimename.darkhastBahreMandiFinal.size()>0">--%>
                <%--<s:iterator value="darkhastBazkharid.bimename.darkhastBahreMandiFinal" status="stat" id="row">--%>
                    <%--<s:if test="%{#stat.index==0}">--%>
                        <%--<tr>--%>
                                <%--&lt;%&ndash;<th>ردیف</th>&ndash;%&gt;--%>
                            <%--<th>نوع بهره مندی از منافع</th>--%>
                            <%--<th>شماره</th>--%>
                            <%--<th>تاریخ صدور</th>--%>
                            <%--<th>جزئیات</th>--%>
                        <%--</tr>--%>
                    <%--</s:if>--%>
                    <%--<tr>--%>
                            <%--&lt;%&ndash;<td><s:property value="%{#stat.index+1}"/></td>&ndash;%&gt;--%>
                        <%--<td><s:property value="#row.getDarkhstTypeFarsi()"/>&nbsp;</td>--%>
                        <%--<td></td>--%>
                        <%--<td><s:property value="#row.getTarikhDarkhast()"/>&nbsp;</td>--%>
                        <%--<td>--%>
                            <%--<input type="button"--%>
                                   <%--onclick="window.open('/printDaftarcheGhestVamParsian.action?darkhastBazkharid.id=<s:property value="#row.getId()"/>');"--%>
                                   <%--style="float:left;margin-left:2px;"--%>
                                   <%--value="چاپ دفترچه اقساط وام پارسیان"/>--%>
                            <%--<input type="button"--%>
                                   <%--onclick="window.open('/printDaftarcheGhestVamTejarat.action?darkhastBazkharid.id=<s:property value="#row.getId()"/>');"--%>
                                   <%--style="float:left;margin-left:2px;"--%>
                                   <%--value="چاپ دفترچه اقساط وام تجارت"/>--%>
                            <%--<input type="button"--%>
                                   <%--onclick="window.open('/printSuratVaziatGhestVam.action?darkhastBazkharid.id=<s:property value="#row.getId()"/>');"--%>
                                   <%--style="float:left;margin-left:2px;"--%>
                                   <%--value="صورت وضعیت اقساط وام"/>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                <%--</s:iterator>--%>
            <%--</s:if>--%>
        <%--</s:if>--%>
    </table>
</div>