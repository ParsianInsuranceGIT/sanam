<%--
  Created by IntelliJ IDEA.
  User: Arron0
  Date: Sep 6, 2011
  Time: 5:53:55 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<html>
<head><title>گزارش بدهي نماينده</title></head>
<body>
    <script type="text/javascript">
        function clearSeachFrom()
        {
            $('#search_namayandegiName_gozareshBedehi').val('');
        }
//        function searchbedehi(){
//            window.open('/fin/printBedehiNamayande?namayandegiId_gozareshbedehi='+$('#namayandegiId_gozareshbedehi').val()+'');
//        }

    </script>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
<form name="gozareshBedehiNamayandeForm" action="/fin/printBedehiNamayande" method="POST" >

    <table class="inputList" width="90%" >

        <tr >
            <td >
                <span class="noThing"></span>
                &nbsp;<label>نمايندگي</label>
                <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('namayandegiId_gozareshbedehi','search_namayandegiName_gozareshBedehi', '');" style="float:left;" title="جستجو"></span>
                <input type="hidden" name="namayandegiId_gozareshbedehi" id="namayandegiId_gozareshbedehi" />
                <input type="text" name="search_namayandegiName_gozareshBedehi" id="search_namayandegiName_gozareshBedehi" class="validate[required]" readonly="true"/>

            </td>


        </tr>
        <tr>

        </tr>

        <tr>
           <td></td>
            <td colspan="1">
                <%--<span class="noThing"></span>--%>
                <input type="button" value="پاک کردن فرم" onclick="clearSeachFrom()"/>
                <span class="noThing"></span>
                <input type="submit" name="print"   value="چاپ گزارش"    />
            </td>
            <td>

            </td>
        </tr>

        <%--      <s:submit   align="center" name="print" value="چاپ گزارش" />--%>


    </table>
</form>
</body>
</html>