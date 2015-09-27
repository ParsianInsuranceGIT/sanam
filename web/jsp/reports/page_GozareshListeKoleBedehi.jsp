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
<form name="" action="printListKoleBedehi" method="POST">

    <table  dir="rtl" class="inputList" width="90%">

        <tr>
            <td>نام بیمه گذار</td>
            <td>
                <span class="noThing">&nbsp;</span>
                <input type="text" name="rcptName" id="rcptName"/>

            </td>
            <td>پیش شماره بیمه نامه</td>
            <td>
                <span class="noThing">&nbsp;</span>
                <input type="text" name="pishShomareBimename" id="pishShomareBimename"/>
            </td>
        </tr>
        <tr>
            <td>نمایندگی</td>
            <td>
                <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('namayandeId','namayandeName', '');" style="float:left;" title="جستجو"></span>
                <input type="hidden" name="namayandeId" id="namayandeId" />
                <input type="text" name="namayandeName" id="namayandeName"  readonly="true"/>

            </td>
             <td>نوع بدهی</td>
            <td>
                <span class="noThing">&nbsp;</span>
                <input type="text" name="credebitType" id="credebitType"  readonly="true"/>
            </td>
        </tr>
        <tr>
            <td>تاریخ صدور از:</td>
            <td>
                <input type="text" name="azTarikheSodoreBimename" id="azTarikheSodoreBimename"  class="datePkr" readonly="true"/>
                    <span class="noThing"></span>
            </td>
            <td>تاریخ صدور تا:</td>
            <td>
                <input type="text" name="taTarikheSodoreBimename" id="taTarikheSodoreBimename" class="datePkr" readonly="true"/>
                 <span class="noThing"></span>
            </td>
        </tr>
        <tr>
            <td colspan="6">
                <script type="text/javascript">
                function clearSeachFrom()
                {
                $('#rcptName').val('');
                $('#pishShomareBimename').val('');
                $('#shomareMoshtari').val('');
                $('#namayandeId').val('');
                $('#namayandeName').val('');
                $('#credebitType').val('');
                $('#azTarikheSodoreBimename').val('');
                $('#taTarikheSodoreBimename').val('');
                }
                </script>
                <span class="noThing">&nbsp;</span>
                <input type="button" value="پاک کردن فرم" onclick="clearSeachFrom()"/>
            </td>
          </tr>
        <tr>
             <td colspan="6">
                 <span class="noThing">&nbsp;</span>
                <input type="submit" name="print" value= "چاپ گزارش بصورت PDF"/>
                <span class="help ui-icon ui-icon-print"   style="float:left;" title="گزارش"></span>
                <input type="button" name="print"  onclick="window.open('/fin/printListKoleBedehiWithExcelFormat?rcptName='+$('#rcptName').val()+'&azTarikheSodoreBimename='+$('#azTarikheSodoreBimename').val()+'&taTarikheSodoreBimename='+$('#taTarikheSodoreBimename').val()+'&vahedeSodorId='+$('#namayandeId').val()+'&pishShomareBimename='+$('#pishShomareBimename').val()+'')" value="چاپ گزارش بصورت EXCEl" />

            </td>
        </tr>
    </table>
  </form>
</body>
</html>