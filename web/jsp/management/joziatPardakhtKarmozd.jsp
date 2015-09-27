<%@ page import="com.bitarts.common.util.DateUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%--
  Created by IntelliJ IDEA.
  User: ramtinb
  Date: 4/10/12
  Time: 10:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>قسط ها</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link type="image/x-icon" href="/img/favicon.ico" rel="shortcut icon">
    <title>پارسیان : <decorator:title default="..."/></title>

    <link type="text/css" rel="stylesheet" href="/css/redmond/jquery-ui-1.8.9.custom.css"/>
    <link type="text/css" rel="stylesheet" href="/css/tipsy.css"/>
    <link type="text/css" rel="stylesheet" href="/css/validationEngine.jquery.css"/>
    <script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui-1.8.9.custom.min.js"></script>
    <script type="text/javascript" src="/js/jquery.tipsy.js"></script>
    <script type="text/javascript" src="/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="/js/jquery.validationEngine-fa.js"></script>
    <script type="text/javascript" src="/js/jquery.validationEngine.js"></script>
    <script type="text/javascript" src="/js/jquery.global.js"></script>

    <link rel="stylesheet" type="text/css" media="screen" href="/resource_lib/jqGrid/css/ui.jqgrid.css"/>
    <script src="/resource_lib/jqGrid/js/i18n/grid.locale-fa.js" type="text/javascript"></script>
    <script src="/resource_lib/jqGrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>

    <link type="text/css" rel="stylesheet" media="all" href="/resource_lib/calendar-blue.css" title="system"/>
    <script type="text/javascript" src="/resource_lib/jalali.js"></script>
    <script type="text/javascript" src="/resource_lib/calendar.js"></script>
    <script type="text/javascript" src="/resource_lib/calendar-setup.js"></script>
    <script type="text/javascript" src="/resource_lib/lang/calendar-fa.js"></script>

    <script type="text/javascript">
        var srvr_date = '<%=DateUtil.getCurrentDate()%>';
        srvr_date = srvr_date.split('/');
        var srvr_date_year = srvr_date[0];
        var srvr_date_mounth = srvr_date[1];
        var srvr_date_day = srvr_date[2];
    </script>
    <link type="text/css" rel="stylesheet" href="/css/styles.css"/>
    <script type="text/javascript" src="/js/common.js"></script>


</head>
<body dir="rtl">
<display:table export="false" id="tblListGhest" uid="nk" htmlId="tblListGhest" name="ghestList"
               partialList="true" clearStatus="true" keepStatus="false"
               style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
               size="${ghestList.size()}" pagesize="${ghestList.size()}">
    <display:column property="ghestBandi.bimename.shomare" title="مربوط به شماره بیمه نامه"></display:column>
    <display:column property="sarresidDate" title="سررسید"></display:column>
    <display:column property="credebit.amount" title="مبلغ قسط"></display:column>
    <display:column property="karmozdAmount" title="مبلغ کارمزد"></display:column>

</display:table>
</body>
</html>
