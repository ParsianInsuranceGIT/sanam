<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.model.bimename.Gharardad" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bitarts.parsian.Core.ConstantPaging" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ include file="/jsp/taglibs.jsp" %>

<script type="text/javascript">
    $(".jtable th").each(function(){
        $(this).addClass("ui-state-default");
    });
    $(".jtable td").each(function(){
        $(this).addClass("ui-widget-content");
    });
    $(".jtable tr").hover(function(){
        //        $(this).children("td").addClass("ui-state-hover");
    },function(){
        //        $(this).children("td").removeClass("ui-state-hover");
    });
    $("input,textarea").each(function(){$(this).addClass("ui-state-default  ui-corner-all");});
    $("select").each(function(){$(this).addClass("ui-state-default  ui-corner-all");});
    $(".help, .comment").tipsy({gravity:'s'});
//    $("form, .vld").validationEngine({promptPosition:"topLeft"});
    $('.dblRadio').buttonset();
    $(".tipsi").tipsy({gravity:'s'});


    $(".digitSeparators").each(function(){$(this).keyup();});
    $(".digitSeparatorsManfi").each(function(){$(this).keyup();});
</script>

<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>
<script type="text/javascript">
//    function displaytagform(formname, fields){
//        var objfrm = document.forms[formname];
//        for (j=fields.length-1;j>=0;j--){
//            $(objfrm).prepend("<input type='hidden' id='embedI_"+fields[j].f+"' name='"+fields[j].f+"' value='"+fields[j].v+"'></input>");
////                    $('#embedI_'+fields[j].f).attr("name", fields[j].f);
////                    $('#embedI_'+fields[j].f).attr("value", fields[j].v);
//        }
//        objfrm.submit();
//    }
    function displaytagform_someTakhsisBeKarshenas(formname, fields){
        var objfrm = document.forms[formname];
        if($(objfrm).serialize().indexOf('_chk') == -1){
            alertMessage("پیشنهادی انتخاب نشده است.");
        }else{
            for (j=fields.length-1;j>=0;j--){
                $(objfrm).prepend("<input type='hidden' id='embedI_"+fields[j].f+"' name='"+fields[j].f+"' value='"+fields[j].v+"'></input>");
//                        $('#embedI_'+fields[j].f).attr("name", fields[j].f);
//                        $('#embedI_'+fields[j].f).attr("value", fields[j].v);
            }
            dialogFormStatic('someTakhsisBeKarshenasDialogDiv', 'تخصیص به کارشناس', function(){
                objfrm.action = "/assignKarshenasToSomePishnehad?"+$('#takhsiskarshenasform').serialize()+"&logmessage="+$('#loggmessageSomeTakhsisBeKarshenasDialogDiv').val();
                objfrm.submit();
            });
        }
    }
    function displaytagform_transition(formname, fields){
        var objfrm = document.forms[formname];
        if($(objfrm).serialize().indexOf('_chk') == -1){
            alertMessage("پیشنهادی انتخاب نشده است.");
        }else{
            for (j=fields.length-1;j>=0;j--){
                $(objfrm).prepend("<input type='hidden' id='embedI_"+fields[j].f+"' name='"+fields[j].f+"' value='"+fields[j].v+"'></input>");
//                        $('#embedI_'+fields[j].f).attr("name", fields[j].f);
//                        $('#embedI_'+fields[j].f).attr("value", fields[j].v);
            }
            objfrm.action = "/transitionSomePishnehad?"+$('#takhsiskarshenasform').serialize()+"&logmessage="+$('#loggmessageSomeTakhsisBeKarshenasDialogDiv').val();
            objfrm.submit();
        }
    }
    $(function(){
        $('#pishnehadsSelectAll').bind("change" ,function(){
            $('input[name="_chk"]').each(function(){
                $(this).attr('checked', $('#pishnehadsSelectAll').attr('checked'));
            })
        });
    });
</script>

    <div class="staticTitleBar" id="aslSearchResult" style="width:98%;direction:rtl;margin:0 auto;">
        <sec:authorize ifAnyGranted="ROLE_KARSHENAS_MASOUL,ROLE_KARSHENAS_BAYEGANI">
            <sec:authorize ifAllGranted="ROLE_KARSHENAS_MASOUL">
                <div style="position:absolute;">
                    <input type="button" style="float:right;" onclick="javascript:displaytagform_someTakhsisBeKarshenas('pishListFormOfRKM',[{f:'par',v:['aa%22az']}])" value="تخصیص به کارشناس"/>
                </div>
            </sec:authorize>
            <sec:authorize ifAllGranted="ROLE_KARSHENAS_BAYEGANI">
                <div style="position:absolute;">
                    <input type="button" style="float:right;" onclick="javascript:displaytagform_transition('pishListFormOfRKM',[{f:'par',v:['aa%22az']}])" value="تغییر وضعیت از صدور با اعلام اقساط به ارسال شده"/>
                </div>
            </sec:authorize>
            <div style="padding-top:27px"></div>
            <form name="pishListFormOfRKM" action="?" method="POST">
                <display:table   excludedParams="selectedTab pishnehadSearch* _chk"
                               decorator="org.displaytag.decorator.CheckboxTableDecorator"
                               export="true" id="tableforaslid" uid="rpResult" name="pishnehadsVMPaginatedList"
                               sort="external" partialList="true" htmlId="tblforaslid"
                               size="${pishnehadsVMPaginatedList.fullListSize}"
                               pagesize="${pishnehadsVMPaginatedList.objectsPerPage}"
                               requestURI="?${pagingParams}" clearStatus="true" keepStatus="false"
                               style="width: 100%; margin: 0 auto;">
                    <%
                        request.setCharacterEncoding("UTF-8");
                        response.setCharacterEncoding("UTF-8");
                    %>
                    <display:setProperty name="pagination.pagenumber.param"><%=ConstantPaging.reportResultPageNumber%></display:setProperty>
                    <%--<display:setProperty name="pagination.pagenumber.param" value="pageNum"/>--%>
                    <display:column property="checkbox" title="<input id='pishnehadsSelectAll' type='checkbox' title='انتخاب/حذف همه'/>"
                                    style="background:${rpResult.roleColor};"/>
                    <display:column title="" style="background:${rpResult.roleColor};" media="html">
                        <c:if test="${rpResult.pishpardakhtOK}"><img src="/img/dollar.png"/></c:if>
                    </display:column>
                    <display:column title="" style="background:${rpResult.roleColor};" media="html">
                        <a target="_blank" href="/editShowForm?pishnehad.id=${rpResult.id}" style="padding:7px; display:block;">نمايش</a>
                    </display:column>
                    <display:column title="ردیف" style="background:${rpResult.roleColor};">
                        ${rpResult_rowNum+((pishnehadsVMPaginatedList.pageNumber-1)*pishnehadsVMPaginatedList.objectsPerPage)}
                    </display:column>
                    <display:column property="radif" title="کد رهگیری"
                                    style="background:${rpResult.roleColor}; white-space: nowrap;"/>
                    <display:column title="نام نماینده"
                                    style="background:${rpResult.roleColor};">
                        ${rpResult.namayandeName}
                    </display:column>
                    <display:column title="کارشناس" style="background:${rpResult.roleColor};">
                        ${rpResult.karshenasFirstName}&nbsp;${rpResult.karshenasLastName}
                    </display:column>
                    <display:column property="newDate" title="تاریخ ثبت وضعیت جدید"
                                    style="background:${rpResult.roleColor};"/>
                    <display:column property="stateName" title="وضعیت"
                                    style="background:${rpResult.roleColor};"/>
                    <display:column title="نام بیمه شده" style="background:${rpResult.roleColor};">
                        ${rpResult.bimeShodeFirstName}&nbsp;${rpResult.bimeShodeLastName}
                    </display:column>
                    <display:column property="gharardadType" title="نوع قرارداد"
                                    style="background:${rpResult.roleColor};"/>
                    <display:column title="استان نمایندگی/سرپرست"
                                    style="background:${rpResult.roleColor};">
                        ${rpResult.ostan}/${rpResult.sarparastName}
                    </display:column>
                    <display:column property="sarmayePayeFot" title="سرمایه فوت (ریال)"
                                    style="background:${rpResult.roleColor};"/>
                </display:table>
            </form>
            <div id="someTakhsisBeKarshenasDialogDiv" style="display:none;direction:rtl;">
                <%@include file="/jsp/pishnahad/subForm/takhsisBeKarshenas.jsp"%>
            </div>
        </sec:authorize>
        <sec:authorize ifNotGranted="ROLE_KARSHENAS_MASOUL,ROLE_KARSHENAS_BAYEGANI">
            <display:table export="true" id="tableforaslid" uid="rpResult" name="pishnehadsVMPaginatedList"
                           sort="external" partialList="true" htmlId="tblforaslid"
                           size="${pishnehadsVMPaginatedList.fullListSize}"
                           pagesize="${pishnehadsVMPaginatedList.objectsPerPage}"
                           requestURI="?${pagingParams}" clearStatus="true" keepStatus="false"
                           excludedParams="selectedTab pishnehadSearch*" style="width: 100%; margin: 0 auto;">
                <%
                    request.setCharacterEncoding("UTF-8");
                    response.setCharacterEncoding("UTF-8");
                %>
                <display:setProperty name="pagination.pagenumber.param"><%=ConstantPaging.reportResultPageNumber%></display:setProperty>
                <%--<display:setProperty name="pagination.pagenumber.param" value="pageNum"/>--%>
                <display:column title="" style="background:${rpResult.roleColor};" media="html">
                    <c:if test="${rpResult.pishpardakhtOK}"><img src="/img/dollar.png"/></c:if>
                </display:column>
                <display:column title="" style="background:${rpResult.roleColor};" media="html">
                    <a href="/editShowForm?pishnehad.id=${rpResult.id}" style="padding:7px; display:block;">نمايش</a>
                </display:column>
                <display:column title="ردیف" style="background:${rpResult.roleColor};">
                    ${rpResult_rowNum+((pishnehadsVMPaginatedList.pageNumber-1)*pishnehadsVMPaginatedList.objectsPerPage)}
                </display:column>
                <display:column property="radif" title="کد رهگیری"
                                style="background:${rpResult.roleColor}; white-space: nowrap;"/>
                <display:column property="namayandeName" title="نام نماینده/کارگزار"
                                style="background:${rpResult.roleColor};"/>
                <display:column title="کارشناس" style="background:${rpResult.roleColor};">
                    ${rpResult.karshenasFirstName}&nbsp;${rpResult.karshenasLastName}
                </display:column>
                <display:column property="newDate" title="تاریخ ثبت وضعیت جدید"
                                style="background:${rpResult.roleColor};"/>
                <display:column property="stateName" title="وضعیت"
                                style="background:${rpResult.roleColor};"/>
                <display:column title="نام بیمه شده" style="background:${rpResult.roleColor};">
                    ${rpResult.bimeShodeFirstName}&nbsp;${rpResult.bimeShodeLastName}
                </display:column>
                <display:column property="gharardadType" title="نوع قرارداد"
                                style="background:${rpResult.roleColor};"/>
                <display:column property="ostan" title="استان نمایندگی"
                                style="background:${rpResult.roleColor};"/>
                <display:column property="sarmayePayeFot" title="سرمایه فوت (ریال)"
                                style="background:${rpResult.roleColor};"/>
            </display:table>
        </sec:authorize>
    </div>
    <div style="clear:both;">
    </div>







