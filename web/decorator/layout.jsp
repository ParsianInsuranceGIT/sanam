<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 5/5/11
  Time: 1:04 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ include file="/jsp/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link type="image/x-icon" href="/img/favicon.ico" rel="shortcut icon">
    <title>پارسیان : <decorator:title default="..."/></title>

    <link type="text/css" rel="stylesheet" href="/css/redmond/jquery-ui-1.8.9.custom.css"/>
    <link type="text/css" rel="stylesheet" href="/css/tipsy.css"/>
    <link type="text/css" rel="stylesheet" href="/css/validationEngine.jquery.css"/>
    <link type="text/css" rel="stylesheet" href="/css/jquery.loadmask.css"/>
    <script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui-1.8.9.custom.min.js"></script>
    <script type="text/javascript" src="/js/jquery.tipsy.js"></script>
    <script type="text/javascript" src="/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="/js/jquery.validationEngine-fa.js"></script>
    <script type="text/javascript" src="/js/jquery.validationEngine.js"></script>


    <link type="text/css" rel="stylesheet" href="/css/smoothDivScroll.css"/>
    <script type="text/javascript" src="/js/jquery.mousewheel.min.js"></script>
    <script type="text/javascript" src="/js/jquery.kinetic.min.js"></script>
    <script type="text/javascript" src="/js/jquery.smoothdivscroll-1.3-min.js"></script>




    <%--<script type="text/javascript" src="/js/validation/estelam_valFuncs.js"/>--%>
    <script type="text/javascript" src="/js/jquery.global.js"></script>
    <script type="text/javascript" src="/js/jquery.loadmask.min.js"></script>

    <link rel="stylesheet" type="text/css" media="screen" href="/resource_lib/jqGrid/css/ui.jqgrid.css"/>
    <script src="/resource_lib/jqGrid/js/i18n/grid.locale-fa.js" type="text/javascript"></script>
    <script src="/resource_lib/jqGrid/js/jquery.jqGrid.src.js" type="text/javascript"></script>
    <%--<script src="/resource_lib/jqGrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>--%>

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
    <sec:authorize ifAnyGranted="ROLE_KARBAR_MALI,ROLE_HESAB_FIMABEYN,ROLE_MALI_ETEBAR_CHECK,ROLE_MALI_ETEBAR_CHECK,ROLE_MALI_OPERATOR">
        <script type="text/javascript" src="/js/menuSplit.js"></script>
    </sec:authorize>
    <link rel="stylesheet" type="text/css" href="/js/menuSplit.css">

    <style>
        .ui-menu { position: absolute; width: 100px; }
    </style>
    <script>
        $(function() {
            $( "#operationMaliBtn" )
                    .click(function() {
                        $('#listMali').css('z-index', 9999);
                        var menu = $( this ).parent().next().show().position({
                            my: "left top",
                            at: "left bottom",
                            of: this

                        });
                        $( document ).one( "click", function() {
                            menu.hide();
                        });
                        return false;
                    })
                    .parent()
                    .buttonset()
                    .next()
                    .hide()
                    .menu();
        });
    </script>
    <decorator:head/>
    <%--b-h--%>
    <sec:authorize ifAllGranted="ROLE_USER">
        <c:choose>
            <c:when test="${user != null && user.daftar.id!=1}">
                <c:set var="cssClass" value="mainNamayande"/>
            </c:when>

            <c:otherwise>
                <c:set var="cssClass" value=""/>
            </c:otherwise>
        </c:choose>
    </sec:authorize>
    <%--ta inja--%>
</head>
<body id="${cssClass}">

<div id="main">

    <sec:authorize ifAllGranted="ROLE_USER">
        <div id="main_header">
            <table width="100%">
                <tr>
                    <td width="6%">
                        <input type="button" id="exit_parsian_btn"  value="خروج" />
                    </td>
                    <sec:authorize ifAnyGranted="ROLE_KARBAR_MALI">
                    <td width="11%">
                        <div >
                            <a style="height:22px;text-emphasis-color: #2E6E9E; background: #eaf2ff;border: 1px solid #b7d2ff;filter: none;" class="easyui-splitbutton" menu="#mm"  iconCls="icon-edit"><font color="#2E6E9E">عملیات مالی</font></a>
                        </div>
                        <div id="mm" style="width:5px;">
                            <sec:authorize ifNotGranted="ROLE_MALI_VIEW">
                                <div onclick="window.location='/fin/loadSanadZani?r=${rndValue}'"><a style="cursor:pointer">ثبت سند دستی</a></div>
                                <div class="menu-sep"></div>
                            </sec:authorize>
                            <div>
                                <span>مشاهدات</span>
                                <div>
                                    <div onclick="window.location='/fin/listBedehiNamayande?r=${rndValue}'"><a style="cursor:pointer">مشاهده لیست بدهی ها</a></div>
                                    <div onclick="window.location='/fin/viewKhateSanadHa?r=${rndValue}'"><a style="cursor:pointer">مشاهده اسناد</a></div>
                                    <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
                                        <div onclick="window.location='/fin/listEtebaratNamayande'"><a style="cursor:pointer">لیست اعتبارات و بدهی ها</a></div>
                                    </sec:authorize>
                                    <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
                                        <div onclick="window.location='/fin/listEtebarat'"><a style="cursor:pointer">لیست اعتبارات و بدهی ها</a></div>
                                    </sec:authorize>
                                    <sec:authorize ifAnyGranted="ROLE_MALI_PARDAKHT_INTERNETI">
                                        <div class="menu-sep"></div>
                                        <div onclick="window.location='/fin/listPardakhtInternetiBedehi'"><a style="cursor:pointer">پرداخت اینترنتی بدهی</a></div>
                                    </sec:authorize>
                                    <div class="menu-sep"></div>
                                    <div onclick="window.location='/fin/listPardakhtPaya'"><a style="cursor:pointer">پرداخت از طریق پایا</a></div>
                                </div>
                            </div>
                            <sec:authorize ifAnyGranted="ROLE_MALI_ETEBAR_CHECK">
                                <div class="menu-sep"></div>
                                    <div>
                                        <span>اعتبارات چک</span>
                                        <div>
                                            <div onclick="window.location='/fin/elamVagozari'"><a style="cursor:pointer">عملیات چک</a></div>
                                            <div onclick="window.location='/fin/listVagozari'"><a
                                                    style="cursor:pointer">لیست واگذاری</a></div>
                                            <%--<sec:authorize ifNotGranted="ROLE_NAMAYANDE">--%>
                                                <%--<div onclick="window.location='/fin/viewDasteCheckHa'"><a style="cursor:pointer">دسته چک ها</a></div>--%>
                                            <%--</sec:authorize>--%>
                                            <%--<div onclick="window.location='/fin/listAllEtebaratCheck'"><a style="cursor:pointer">مشاهده چک های دریافتی</a></div>--%>
                                        </div>
                                    </div>
                            </sec:authorize>
                            <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
                                <sec:authorize ifNotGranted="ROLE_MALI_VIEW">
                                    <div class="menu-sep"></div>
                                    <div onclick="window.location='/fin/listMaliNamayande.action'"><a style="cursor:pointer">لیست کنترل مالی نمایندگان</a></div>
                                    <div class="menu-sep"></div>
                                    <div>
                                        <span>تایید وصولی</span>
                                        <div>
                                            <div onclick="window.location='/uploadFileBank.action'"><a style="cursor:pointer">آپلود فایل بانک</a></div>
                                            <div onclick="window.location='/fin/listGozareshUpload'"><a style="cursor:pointer">گزارش آپلود فایل</a></div>
                                        </div>
                                    </div>
                                </sec:authorize>
                            </sec:authorize>
                                    <div>
                                        <span> گزارشات</span>
                                        <div>
                                            <%--<div class="menu-sep"></div>--%>
                                            <div onclick="window.location='/fin/listGozareshVosouli'"><a style="cursor:pointer">گزارش وصولی</a></div>

                                            <div class="menu-sep"></div>
                                             <div onclick="window.location='/fin/gozareshSoratVaziyateMaliBimename'"><a style="cursor:pointer">گزارش صورت وضعیت مالی بیمه نامه</a></div>

                                            <div class="menu-sep"></div>
                                            <div onclick="window.location='/fin/gozareshkoliEtebarvaBedehi'"><a style="cursor:pointer">گزارش کلی اعتبارات و بدهی ها</a></div>

                                            <div class="menu-sep"></div>
                                            <div onclick="window.location='/fin/gozaresheListeKoleBedehi'"><a style="cursor:pointer">گزارش لیست کل بدهی</a></div>

                                            <div class="menu-sep"></div>
                                            <div onclick="window.location='/fin/prepareCashTurnoverReport'"><a style="cursor:pointer">گزارش عملکرد صندوق</a></div>
                                                <div class="menu-sep"></div>
                                                <div onclick="window.location='/fin/gozareshBedehiNamayande'"><a style="cursor:pointer">گزارش بدهي نماينده</a></div>

                                        </div>
                                    </div>

                                    <c:if test="${sessionScope.daftar_id!=1}">


                                         <div class="menu-sep"></div>
                                         <div onclick="window.location='/fin/listBedehihayeDaftarParsian'"><a style="cursor:pointer">انتقال بدهي</a></div>
                                    </c:if>

                               </div>

                    </td>
                    <td width="9%">
                        <input type="button" value="مستند مالی" onclick="window.open('/extra/maali.pdf')"/>
                    </td>
                                </sec:authorize>
                    <sec:authorize ifAnyGranted="ROLE_KARBAR_MALI">
                    <td width="30%">
                            </sec:authorize>
                    <sec:authorize ifNotGranted="ROLE_KARBAR_MALI">
                    <td width="34%">
                    </sec:authorize>
                        <sec:authorize
                                ifAnyGranted="ROLE_ADMIN">
                            <div id="dashbord_monitor">
                                <input type="button" value="مانیتورینگ"
                                       onclick="window.location='/administrator/monitoring'"/>
                            </div>
                        </sec:authorize>
                        <sec:authorize
                                ifAnyGranted="ROLE_RAEIS_SODUR,ROLE_SUPERVISOR,ROLE_KARSHENAS_MASOUL">
                            <div id="dashbord_dogmeh">
                                <input type="button" value="داشبورد مدیریتی"
                                       onclick="window.location='/jsp/management/page_mainManagementPage.jsp'"/>
                            </div>
                        </sec:authorize>
                        <sec:authorize ifAnyGranted="ROLE_KARSHENAS_BAYEGANI">
                            <div id="managementSerials_dogmeh">
                                <input type="button" value="مدیریت سریال ها"
                                       onclick="window.location='/listAllSerial.action'"/>
                            </div>
                        </sec:authorize>
                            <input type="button" value="فرم ها و مستندات مورد نیاز"
                                   />
                    </td>


                    <td dir="rtl">
                        <sec:authorize ifNotGranted="ROLE_LIMITED_AMIN_PARSIAN">
                            <a href="changeUserPassword?userTemp.id=${user.id}" style="float: right">تغییر رمز عبور</a>
                        </sec:authorize>
                        نام شخص وارد شده :
                           ${user!=null?user.firstName:""} ${user!=null?user.lastName:""}

                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <% String username = SecurityContextHolder.getContext().getAuthentication().getName();
                        System.out.println(username);%>
                        نام نمایندگی :
                        <c:if test="${empty user.namayandegi.name}">
                            --
                        </c:if>
                        <c:if test="${not empty user.namayandegi.name}">
                            ${user.namayandegi.name}
                        </c:if>

                        <br/>
                        نقش ها :
                        <fmt-rt:setBundle basename="com.bitarts.parsian.i18n.enumTypes_fa"/>
                        <c:forEach var="rol" items="${user.roles}" varStatus="i">

                            <fmt-rt:message>${rol.roleName}</fmt-rt:message>
                            <c:if test="${(i.index+1) != fn:length(user.roles)}">,</c:if>
                        </c:forEach>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        نوع دفتر:
                        <c:if test="${user != null && user.daftar.id!=1}">
نماينده
                        </c:if>
                        <c:if test="${user == null || user.daftar.id==1}">
                                پارسيان
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>

    </sec:authorize>
    <div id="main_content">
        <decorator:body/>
    </div>

    <div id="main_footer">مدیریت فناوری اطلاعات بیمه پارسیان - <%=DateUtil.getCurrentDate().substring(0,4)%> ©</div>
</div>

<script type="text/javascript">
    var dataInput = document.getElementById('exit_parsian_btn');
    $(document).ready(function ()
    {
        var username = '${user !=null ? user.username:0}';
        var password = '@password';
        if (username != '0')
        {
            if (!(username in localStorage))
            {
                localStorage.setItem(username, password);
            }
        }
    });

    dataInput.addEventListener
    (
        "click",
        function ()
        {
            var username = '<%=
            SecurityContextHolder.getContext().getAuthentication()!=null && SecurityContextHolder.getContext().getAuthentication().getName()!=null?SecurityContextHolder.getContext().getAuthentication().getName():0
            %>'
            localStorage.removeItem(username);
            window.location = '/j_spring_security_logout';
        }
    );

    window.addEventListener
    (
        'storage',
        function (event)
        {
            var username='<%=SecurityContextHolder.getContext().getAuthentication()!=null && SecurityContextHolder.getContext().getAuthentication().getName()!=null?SecurityContextHolder.getContext().getAuthentication().getName():0%>'
            if(username=='0')
            {}
            else
            {
                if (username==event.key)
                {
                    if(event.newValue==null)
                        window.location = '/j_spring_security_logout';
                }
            }
        }
    );

    $(document).ajaxStart(
            function () {
                $('#main').mask('در حال بارگذاری');
            }).ajaxStop(function () {
                $('#main').unmask();
            });
//    $(document).ajaxSetup({cache: false});
    $("#main").submit(function(){ $('#main').mask('در حال بارگذاری'); document.forms[0].submit() })

    $(document).ready(function(){
        $('#main').unmask();
    });


</script>
</body>
</html>