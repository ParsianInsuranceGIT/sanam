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
<head><title>گزارش صورت وضعيت مالي بيمه نامه</title></head>
<body>
<script type="text/javascript">
    function clearSeachFrom()
    {
        $('#shomareBimenameFrom').val('');
    }
//    function searchVaziatBimename(){
//        window.open('/fin/printSoratVaziateMaliBimename?shomareBimenameFrom='+$('#shomareBimenameFrom').val()+'');
//    }

</script>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
<form name="suratVaziatMaliBimenameForm" action="/fin/printSoratVaziateMaliBimename" method="POST" >

    <table class="inputList" width="90%">

      <tr>
          <td>
          <span class="noThing"></span>
              &nbsp;<label>شماره بيمه نامه</label>
          <input type="text" name="shomareBimenameFrom" id="shomareBimenameFrom" class="validate[required]" />

          </td>
          <td>

          </td>
          <%--<td>--%>
              <%--<span class="noThing"></span>--%>
              <%--&nbsp;<label>شماره بیمه نامه تا:</label>--%>
              <%--<input type="text" name="shomareBimenameTo" id="shomareBimenameTo" />--%>

          <%--</td>--%>
      </tr>
        <tr>

        </tr>

        <tr>
            <td>

            </td>
            <td colspan="1">
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