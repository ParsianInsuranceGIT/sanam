<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%--
  Created by IntelliJ IDEA.
  User: a-khezri
  Date: 4/28/14
  Time: 12:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>گزارش اقساط وام</title>
</head>
<body>
    <div style="overflow: auto;">
        <div class="expandableTitleBar" id="expandableAsl">
            <p class="heading">
                <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                جستجو
            </p>

            <div class="content" style="display:none;" id="searchAslContent">
                <form action="/aghsatVamReport.action" id="form_aghsat_vam_report" method="post">
                    <table class="inputList" cellspacing="5" width="90%">
                        <tr>
                            <td>
                                <span class="noThing"></span>
                                <input type="text" name="gsv.shomareVam" id="shomare_vam" value="${gsv.shomareVam}"/>
                                <label>شماره وام</label>
                            </td>

                            <td>
                                <span class="noThing"></span>
                                <input type="text" name="gsv.shomareMoshtari" id="shomare_moshtari" value="${gsv.shomareMoshtari}"/>
                                <label>شماره مشتری</label>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <span class="noThing"></span>
                                <input type="submit" value="گزارش"/>
                                <script type="text/javascript">
                                    function clearSeachFromVam()
                                    {
                                        $('#shomare_vam').val('');
                                        $('#shomare_moshtari').val('');
                                    }
                                </script>
                                <input type="button" style="margin-left:25px" value="پاک کردن فرم"
                                       onclick="clearSeachFromVam()">
                            </td>
                    </table>
                </form>
                <br/>
                <hr/>
                <br/>
            </div>
        </div> <br/><br/>
            <display:table export="true" id="gvPaginatedList" uid="row" htmlId="gvPaginatedList" style="width:100%"
                           name="gvPaginatedList" clearStatus="true"
                           requestURI="" keepStatus="false">
                <display:column title="ردیف">${(gvPaginatedList.pageNumber-1)*30+row_rowNum }</display:column>
                <%--<display:column property="shomareBimename" title="شماره بيمه‌نامه"--%>
                                <%--style="white-space: nowrap;"></display:column>--%>
                <display:column property="shomareVam" title="شماره وام"
                                style="white-space: nowrap;"></display:column>
                <display:column property="ghestAmount" title="مبلغ (ریال)"></display:column>
                <display:column title="تاریخ ایجاد قسط" property="ghestCreatedDate"></display:column>
                <display:column property="ghestSarresidDate" title="تاریخ سررسید قسط"></display:column>
                <display:column title="مالیات (ریال)" property="ghestMaliat"/>
                <display:column property="ghestBahre" title="بهره (ریال)"/>
                <display:column property="ghestPaymentAmount" title="مبلغ پرداختی از اصل وام (ریال)"/>
                <display:column property="shomareMoshtari" title="شماره مشتری"/>
            </display:table>
    </div>
    <input type="button" onclick="window.location='/aghsatVamReport.action'" value="گزارش" />
    <input type="button" onclick="window.location = '/jsp/management/page_mainManagementPage.jsp' " value="بازگشت"/>
</body>
</html>