<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript" src="/js/jquery.validationEngine.js"></script>
<html>
<head>
    <title>تقسیط گروهـــی</title>
</head>
<body>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>
    <div style="overflow: auto;">
        <div class="expandableTitleBar" id="expandableAsl">
            <p class="heading">
                <span class="ui-icon ui-icon-plus" style="float:right;"></span>
                جستجو
            </p>

            <div class="content" style="display:none;" id="reportsAslContent">
                <%@include file="/jsp/management/gozaresh/searchBatchTaghsitReport.jsp" %>
            </div>
            <form name="form_taghsit" action="?" method="POST">
            <div id="expandable_tgsit" style="overflow: auto;">

                <c:if test="${fn:length(batchTaghsitVMPgList.list)!=0 && batchTaghsitVMPgList!=null}">
                    <br/><br/>
                    <input type="button" onclick="parsianPrint();" value="چاپ گروهی دفترچه پارسیان"/>
                    <input type="button" onclick="tejaratPrint();" value="چاپ گروهی دفترچه تجارت"/>
                </c:if>
                <display:table   excludedParams="batchTaghsitVMSelectAll* _chk"
                                 decorator="org.displaytag.decorator.CheckboxTableDecorator"
                                 export="true" id="tbl_batch_taghsit" uid="row" name="batchTaghsitVMPgList.list"
                                 sort="external" partialList="true" htmlId="tbl_batch_taghsit"
                                 size="${batchTaghsitVMPgList.fullListSize}"
                                 pagesize="${batchTaghsitVMPgList.objectsPerPage}"
                                 requestURI="?${pagingParams}" clearStatus="true" keepStatus="false"
                                 style="width: 100%; margin: 0 auto;">
                        <display:column property="checkbox" media="html" title="<input id='batchTaghsitVMSelectAll' type='checkbox' title='انتخاب/حذف همه'/>" />
                        <display:column title="رديف">${row_rowNum}</display:column>
                        <display:column style="white-space: nowrap;" property="shomareBimename" title="شماره بیمه نامه"/>
                        <display:column  title="نوع بیمه نامه">عمر و سرمایه گذاری</display:column>
                        <display:column title="بیمه گذار">${row.bimeGozarFirstName} ${row.bimeGozarLastName}</display:column>
                        <display:column title="بیمه شده">${row.bimeShodeFirstName} ${row.bimeShodeLastName}</display:column>
                        <display:column property="bimenameTarikheShoro" title="تاریخ شروع بیمه نامه"/>
                        <display:column property="bimenameTarikhEngheza" title="تاریخ انقضاء بیمه نامه"/>
                        <display:column property="bimenameTarikhSodur" title="تاریخ صدور بیمه نامه"/>
                        <display:column property="modat" title="مدت"/>
                        <display:column property="noeGharardad" title="نوع قرارداد"/>
                        <display:column property="nahvePardakhtFarsi" title="نحوه پرداخت"/>
                        <display:column property="saleBimeei" title="سال بیمه ای"/>
                        <display:column property="aghsatPardakhtiCount" title="تعداد اقساط پرداختی"/>
                        <display:column property="tarikhShoroNewYear" title="تاریخ شروع سال جدید"/>
                        <display:column property="haghBimeNewYear" title="حق بیمه سال جدید (ریال)"/>
                        <display:column  title="نمایندگی">${row.namayandeName}-${row.namayandeCode}</display:column>
                        <display:column  title="تقسیط کننده">${row.creatorFirstName} ${row.creatorLastName}</display:column>
                        <display:column  property="tarikhTaghsit" title="تاریخ تقسیط"/>
                        <display:column  media="html" title="سوابق دفترچه">
                            <input type="button" value="..." onclick="viewLogs(${row.id});"/>
                        </display:column>
                        <display:column media="excel"  title="چاپ دفترچه" >
                            ${row.hasPrint==null?"گرفته نــشده":"گرفته شده"}
                        </display:column>
                </display:table>

                <script type="text/javascript" >
                    function displaytagform_somePrintParsian(formname, fields){
                        var objfrm = document.forms[formname];
                        if($(objfrm).serialize().indexOf('_chk') == -1){
                            alertMessage("اعتباری انتخاب نشده است.");
                        }else{
                            for (j=fields.length-1;j>=0;j--){
                                $(objfrm).prepend("<input type='hidden' id='embedI_"+fields[j].f+"' name='"+fields[j].f+"' value='"+fields[j].v+"'></input>");

                            }
                            objfrm.target = "_blank";
                            objfrm.action = "printeBatchAghsatParsian.action";
                            objfrm.submit();
    //                        $('#form_taghsit').attr('action', 'printeBatchAghsatParsian.action');
    //                        $('#form_taghsit').attr('target', '_blank');
    //                        $("#form_taghsit").submit();
                        }
                    }

                    function displaytagform_somePrintTejarat(formname, fields){
                        var objfrm = document.forms[formname];
                        if($(objfrm).serialize().indexOf('_chk') == -1){
                            alertMessage("اعتباری انتخاب نشده است.");
                        }else{
                            for (j=fields.length-1;j>=0;j--){
                                $(objfrm).prepend("<input type='hidden' id='embedI_"+fields[j].f+"' name='"+fields[j].f+"' value='"+fields[j].v+"'></input>");

                            }
    //                        $('#form_taghsit').attr('target', '_blank');
    //                        $('#form_taghsit').attr('action', 'printeBatchAghsatTejarat.action');
    //                        $("#form_taghsit").submit();
                            objfrm.target = "_blank";
                            objfrm.action = "/printeBatchAghsatTejarat";
                            objfrm.submit();
                        }
                    }


                    $(function(){
                        $('#batchTaghsitVMSelectAll').bind("change" ,function(){
                            $('input[name="_chk"]').each(function(){
                                $(this).attr('checked', $('#batchTaghsitVMSelectAll').attr('checked'));
                            })
                        });
                    });

                    function parsianPrint()
                    {
                        displaytagform_somePrintParsian('form_taghsit', [{f:'par',v:['aa%22az']}]);
                    }

                    function tejaratPrint()
                    {
                        displaytagform_somePrintTejarat('form_taghsit', [{f:'par',v:['aa%22az']}]);

                    }

                    function viewLogs(gbId)
                    {

                        $('#rslt').dialog({
                            modal: true,
                            width: 350,
                            resizable: false,
                            closeText: "",
                            title: "سوابق دفترچه",
                            buttons:
                            {
                                "بستن": function (){$(this).dialog("close");}
                            }
                        });
                        $.post('/logsDaftarche.action?ghestBandiId='+gbId,function (response){$('#rslt').html(response);});
                    }
                </script>
            </div>
            <div id="rslt" style="display: none;">
            </div>
            </form>
        </div>
    </div>
</body>
</html>
