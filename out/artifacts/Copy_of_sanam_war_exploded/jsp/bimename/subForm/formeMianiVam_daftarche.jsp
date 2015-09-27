<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="/struts-tags" prefix="st" %>
<div style="height:30px">

    <c:if test="${darkhastBazkharid.ghestBandi.id == null}">
        <input type="button" onclick="alertMessage('تقسیط انجام نشده است.')" style="float:left;margin-left:2px;"
               value="چاپ دفترچه اقساط وام">
    </c:if>
    <c:if test="${darkhastBazkharid.ghestBandi.id != null}">
        <st:url action="printDaftarcheGhestVamParsian" id="printDaftarcheParsian">
            <st:param name="darkhastBazkharid.id" value="darkhastBazkharid.id"/>
        </st:url>
        <st:url action="printDaftarcheGhestVamTejarat" id="printDaftarcheTejarat">
            <st:param name="darkhastBazkharid.id" value="darkhastBazkharid.id"/>
        </st:url>
        <input type="button" onclick="window.open('${printDaftarcheParsian}');" style="float:left;margin-left:2px;"
               value="چاپ دفترچه اقساط وام پارسیان">
        <input type="button" onclick="window.open('${printDaftarcheTejarat}');" style="float:left;margin-left:2px;"
               value="چاپ دفترچه اقساط وام تجارت">
    </c:if>
</div>
