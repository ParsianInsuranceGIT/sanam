<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ include file="/jsp/taglibs.jsp"%>
<%
    String valid = (String) request.getSession().getAttribute("authenticated");
    Integer username = (Integer) request.getSession().getAttribute("userid");
%>
<head>
    <title>لیست کلینیک های ثبت شده</title>
</head>
<%@include file="/jsp/josteju/searchCity.jsp" %>
<s:actionmessage/>
<div class=expandableTitleBar>
    <p class=heading><span class="ui-icon ui-icon-plus" style="direction:rtl;float:right;"></span>
        کلینیک های ثبت شده:
    </p>

    <form id="mainframe" action="/editClinic" method="post" accept-charset="UTF-8">
        <input type="hidden" id="clinicid" name="clinic.id" value=""/>
    </form>


    <div class="staticTitleBar" id="result" style="direction:rtl;">


        <s:if test="sessionScope.listClinics == null || sessionScope.listClinics.list.size == 0">
            <table class="jtable resultDets" align="center" width="900px" cellpadding="0" cellspacing="0"
                   style="border-spacing:1px;margin:0 auto;" border="0">
                <thead>
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <th colspan="5" width="150px">اطلاعاتی یافت نشد</th>
                </tr>
                <tr>
                    <td></td>
                </tr>
                </thead>
            </table>
        </s:if>
        <s:else>
            <form action="/moshahedeClinics" method="get">
                <table dir="rtl" class="inputList">
                    <tr>
                        <td>نام:</td>
                        <td>
                            <span class="datePkr_btn">&nbsp;</span>
                            <s:textfield name="sname" label="" id="sname" theme="simple"/>
                        </td>
                        <td>شهر:</td>
                        <td>
                            <span class="help ui-icon ui-icon-search" onclick="fillShahr('cityId','cityName','btnSubmit')" style="float:left;" title="جستجو"></span>
                            <s:textfield name="scityname" id="cityName" theme="simple" readonly="true"/>
                            <s:hidden name="cityId" id="cityId"/>
                        </td>
                    </tr>
                    <tr>
                        <td>از تاریخ:</td>
                        <td><s:textfield key="saztarikh" label="" theme="simple" cssClass="datePkr"/></td>
                        <td>تا تاریخ:</td>
                        <td><s:textfield key="statarikh" label="" theme="simple" cssClass="datePkr"/></td>
                    </tr>
                    <tr>
                        <td colspan="3"></td>
                        <td>
                            <span class="noThing">&nbsp;</span>
                            <s:submit value="جستجو" theme="simple"/>
                        </td>
                    </tr>
                </table>
            </form>

            <br>
            <br>
            <div style="position:absolute;">
                <c:if test="${fn:length(sessionScope.listClinics.list) > 0}">
                    <input type="button" style="float:right;" onclick="javascript:displaytagform_addAzemayeshToSomeClinics('clinicListForm',[{f:'par',v:['aa%22az']}])" value="اضافه كردن آزمايش"/>
                    <input type="button" style="float:right;margin-right:2px" onclick="javascript:displaytagform_addAzemayeshToAll()" value="اضافه كردن آزمايش به همه"/>
                </c:if>
                <input type="button" style="float:right;margin-right:2px" onclick="window.location='/jsp/management/page_sabtMoshakhasatClinic.jsp'" value="ثبت مشخصات کلینیک جدید"/>
                <input type="button" style="float:right;margin-right:2px" onclick="window.location='jsp/management/page_mainManagementPage.jsp'" value="بازگشت"/>
            </div>

            <div style="padding-top:8px"></div>
            <script type="text/javascript">
                function displaytagform(formname, fields){
                    var objfrm = document.forms[formname];
                    for (j=fields.length-1;j>=0;j--){
                        $(objfrm).prepend("<input type='hidden' id='embedI_"+fields[j].f+"'>");
                        $('#embedI_'+fields[j].f).attr("name", fields[j].f);
                        $('#embedI_'+fields[j].f).attr("value", fields[j].v);
                    }
                    objfrm.submit();
                }
                function displaytagform_addAzemayeshToAll(){
                    dialogFormStatic('addAzemayeshToAllDialogDiv', 'اضافه کردن آزمایش به همه کلینیک ها', function(){
                        var data = $('#addAzemayeshToAllDialogForm').serialize();
                        $.post("/addAzemayeshToAll?", data, function(msg){
                            if(msg.indexOf("") != -1){
                                $('#addAzemayeshToAllDialogDiv').dialog('close');
                                alertMessage(msg);
                            }else{
                                $('#errorDiv').html(msg);
                            }
                        });
                    });
                }
                function displaytagform_addAzemayeshToSomeClinics(formname, fields){
                    var objfrm = document.forms[formname];
                    if($(objfrm).serialize().indexOf('_chk') == -1){
                        alertMessage("حداقل باید یکی از کلینیک ها را انتخاب نمایید.");
                    }else{
                        for (j=fields.length-1;j>=0;j--){
                            $(objfrm).prepend("<input type='hidden' id='embedI_"+fields[j].f+"'>");
                            $('#embedI_'+fields[j].f).attr("name", fields[j].f);
                            $('#embedI_'+fields[j].f).attr("value", fields[j].v);
                        }
                        dialogFormStatic('addAzemayeshToAllDialogDiv', 'اضافه کردن آزمایش به کلینیک های انتخاب شده', function(){
                            objfrm.action = "/addAzemayeshToSomeClinics?"+$('#addAzemayeshToAllDialogForm').serialize();
                            objfrm.submit();
                        });
                    }
                }
                $(function(){
                    $('#clinicSelectAll').bind("change" ,function(){
                        $('input[name="_chk"]').each(function(){
                            $(this).attr('checked', $('#clinicSelectAll').attr('checked'));
                        })
                    });
                });
            </script>

            <form name="clinicListForm" action="?" method="POST">
                <display:table form="clinicListForm" excludedParams="_chk" decorator="org.displaytag.decorator.CheckboxTableDecorator"
                               export="false" id="tblListClinics" uid="clinic" htmlId="tblListClinics"
                               name="sessionScope.listClinics.list" partialList="true" clearStatus="true" keepStatus="false"
                               style="margin-right:auto;margin-left:auto;margin-top:20px;" requestURI=""
                               size="${sessionScope.listClinics.fullListSize}"
                               pagesize="${sessionScope.listClinics.objectsPerPage}">
                    <display:column property="checkbox" title="<input id='clinicSelectAll' type='checkbox' title='انتخاب/حذف همه'/>"/>
                    <display:column property="clinicName" title="نام کلینیک"></display:column>
                    <display:column property="cityName" title="شهر"></display:column>
                    <display:column property="address" title="آدرس"></display:column>
                    <display:column property="tarikhShorouGharardad" title="شروع قرارداد"></display:column>
                    <display:column property="tarikhEtmamGharardad" title="اتمام قرارداد"></display:column>
                    <display:column media="html">
                        <input type="button" value="ویرایش کلینیک" onclick='prepareToEdit(${clinic.id});'/>
                        <input type="hidden" id="clinicholder${clinic_rowNum}" value="${clinic.id}"/>
                    </display:column>
                    <display:column media="html"><input type="button" value="مشاهده آزمایشات"
                                                        onclick="window.location='/findAzmayeshat?clinic.id=${clinic.id}'"/> </display:column>
                    <%--<display:column media="html">--%>
                    <%--<input type="button" value="حذف کلینیک" onclick='prepareToRemove(${clinic.id});'/>                    --%>
                    <%--</display:column>--%>
                </display:table>
            </form>

        </s:else>

    </div>

    <br>
</div>
<script type="text/javascript">
    function prepareToEdit(val) {
        document.getElementById("clinicid").value = val;
        jQuery("#mainframe").attr("action", "/editClinic");
        jQuery("#mainframe").submit();
    }
    function prepareToRemove(val) {
        document.getElementById("clinicid").value = val;
        jQuery("#mainframe").attr("action", "/removeClinic");
        jQuery("#mainframe").submit();
    }
</script>

<div id="addAzemayeshToAllDialogDiv" style="display:none;">
    <form id="addAzemayeshToAllDialogForm" action="">
        <div id="errorDiv"></div>
        <table dir="rtl" class="jtable resultDets" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
            <tbody>
            <tr>
                <td>
                    نام آزمایش
                </td>
                <td>
                    <select name="azmayesh.azmayeshName.id" id="azmayeshnameselect" onchange="azmayeshTypeHandler();">
                        <option value='-1' id2=''>لطفا انتخاب نمایید</option>
                        <c:forEach var="row" items="${azmayeshNames}">
                            <option value='${row.id}' id2="${row.azmayeshType.type}">${row.name}</option>
                        </c:forEach>
                    </select>

                    <script type="text/javascript">
                        function azmayeshTypeHandler(){
                            $('#azmayeshType').val($('#azmayeshnameselect option:selected').attr("id2"));
                        }
                    </script>

                </td>
            </tr>
            <tr>
                <td>
                    نوع آزمایش
                </td>
                <td>
                    <input type="text" id="azmayeshType" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td>
                    قیمت
                </td>
                <td>
                    <input type="text" name="azmayesh.price" id="azmayeshPrice" class="validate[required]"/>
                </td>
            </tr>
            <tr>
                <td>
                    توضیحات
                </td>
                <td>
                    <textarea rows="5" cols="50" name="azmayesh.description" id="azmayeshDescription" ></textarea>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>