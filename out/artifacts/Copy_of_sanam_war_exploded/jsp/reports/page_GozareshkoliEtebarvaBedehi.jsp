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
<head><title>لیست اعتبارات و بدهی ها</title></head>
<body>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
<form name="" action="printGozaresheKoliEtebarvaBedehi" method="POST">
    <table  dir="rtl" class="inputList" width="90%">
      <tr>
          <td>تاریخ صدور از:</td>
          <td>
              <span class="noThing">&nbsp;</span>
             <input type="text" name="azTarikheSodoreBimename" id="azTarikheSodoreBimename"  class="datePkr" readonly="true"/>
          </td>

          <td>تاریخ صدور تا:</td>
          <td>
               <span class="noThing">&nbsp;</span>
              <input type="text" name="taTarikheSodoreBimename" id="taTarikheSodoreBimename" class="datePkr" readonly="true"/>
          </td>
      </tr>
      <tr>
          <td>محل صدور از:</td>
          <td>
              <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('vahedeSodorId','namayandeName', '');" style="float:left;" title="جستجو"></span>
              <input type="hidden" name="vahedeSodorId" id="vahedeSodorId" />
              <input type="text" name="namayandeName" id="namayandeName"  readonly="true"/>
          </td>
        </tr>
        <tr>
            <td colspan="6">
                <script type="text/javascript">
                    function clearSeachFrom()
                    {
                        $('#azTarikheSodoreBimename').val('');
                        $('#taTarikheSodoreBimename').val('');
                        $('#vahedeSodorId').val('');
                        $('#namayandeName').val('');
                    }
                </script>
                <span class="noThing"></span>
                <input type="submit" name="print" value="چاپ گزارش"    />
                <input type="button" value="پاک کردن فرم" onclick="clearSeachFrom()"/>
            </td>
        </tr>
       </table>
    </form>
</body>
</html>