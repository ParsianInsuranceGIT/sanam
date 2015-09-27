<%@ page import="com.bitarts.parsian.model.User" %>
<%@ page import="com.bitarts.common.util.DateUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: Feb 16, 2011
  Time: 4:01:02 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int counterMoarefi = 0;
%>
<form action="/sodurMoarefiname.action" id="sodurMoarefinameForm" method="get">
<div id="sodurMoarefiname">
    <div class="expandableTitleBar" id="poosheshasli">
        <p class="heading">
            <span class="ui-icon ui-icon-plus" style="float:right;"></span>
            جدول معرفی نامه های پزشکی
        </p>
    </div>
    <div id="moarefiTable">
    <table class="inputList jtable" border="0" cellspacing="1" cellpadding="1">
        <tr>
            <th width="9%">شماره</th>
            <th>تاريخ ايجاد</th>
            <th>نام كلينيك</th>
            <th>وضعيت</th>
            <th>پرینت</th>
            <th>ابطال معرفي نامه</th>
        </tr>
        <c:if test="${fn:length(pishnehad.moarefinameList)==0}"><tr><td colspan="6">تا کنون معرفی نامه ای برای این پیشنهاد ثبت نشده است.</td></tr></c:if>
        <c:if test="${fn:length(pishnehad.moarefinameList)>0}">
            <c:forEach var="row" items="${pishnehad.moarefinameList}" varStatus="i">
                <c:if test="${row.vaziat=='BATEL_SHODE'}">
                    <c:set var="theColor" value="lightgray"/>
                </c:if>
                <c:if test="${row.vaziat=='DAR_JARYAN'}">
                    <c:set var="theColor" value="white"/>
                </c:if>
                <tr>
                    <td style="background:${theColor};">${i.index + 1}</td>
                    <td style="background:${theColor};">${row.tarikhSodur}</td>
                    <td style="background:${theColor};">
                    <c:if test="${row.clinic.id == 111111}">
                        ${row.clinicNameSayer}
                    </c:if>
                    <c:if test="${row.clinic.id != 111111}">
                        ${row.clinic.clinicName}
                    </c:if>
                    </td>
                    <td style="background:${theColor};">
                        <c:if test="${row.vaziat=='BATEL_SHODE'}">
                            باطل شده
                        </c:if>
                        <c:if test="${row.vaziat=='DAR_JARYAN'}">
                            در جریان
                        </c:if>
                    </td>
                    <td style="background:${theColor};"><a onclick="window.open('/print_darkhasteMoayenatePezeshki.action?pishnehadReport.pishnehad.id=${pishnehad.id}&pishnehadReport.moarefiname.id=${row.id}');" href="javascript:void(0);">پرینت درخواست معاینات پزشکی</a></td>
                    <td style="background:${theColor};">
                        <c:if test="${row.vaziat=='BATEL_SHODE'}">
                            -
                        </c:if>
                        <c:if test="${row.vaziat=='DAR_JARYAN'}">
                            <a onclick="ebtalMoarefiname(${row.id});">ابطال</a>
                            <%--<a href="/ebtalMoarefiname.action?pishnehad.id=${pishnehad.id}&moarefiname.id=${row.id}">ابطال</a>--%>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    </div>

    <table class="inputList" style="margin-top:30px" border="0" cellspacing="5" cellpadding="1">
        <col class="inputCol">
        <col class="inputCol">
        <tr>
            <td>
                <input type="text" name="moarefiname.tarikhSodur" id="moarefiname_tarikhSodur" readonly="readonly" value="<%=DateUtil.getCurrentDate()%>">
                <label>تاریخ صدور : </label>
            </td>
            <td>
                <input type="text" id="moarefiname_tarikheEtebar" readonly="readonly" value="<%=DateUtil.addDays(DateUtil.getCurrentDate(), 15)%>">
                <label>تاریخ اعتبار : </label>
            </td>
        </tr>
        <tr>
            <td>

                <select name="moarefiname.clinic.id" id="moarefiname_clinicName" onchange="fillAzemayeshs(this.selectedIndex);">
                    <c:if test="${clinics != null}">
                        <c:forEach var="row" items="${clinics}">
                            <option value="${row.id}">${row.clinicName}</option>
                        </c:forEach>
                    </c:if>
                </select>
                <label>نام کلینیک : </label>
            </td>
            <td>
                <input type="text" id="moarefiname_sayer" name="moarefiname.clinicNameSayer">
                <label>سایر : </label>
            </td>
        </tr>
        <% if(pishnehadRun.getNoeBimename().equals("خانواده")){ %>
        <tr>
            <td>
                <input type="text" id="moarefiname_bimeshode_name" name="moarefiname.bimeShodeName">
                <label>نام و نام خانوادگی:</label>
            </td>
            <td>
                <input type="text" id="moarefiname_bimeshode_shomaresh" name="moarefiname.bimeShodeShomareSh">
                <label>شناره شناسنامه:</label>
            </td>
        </tr>
            <tr>
            <td>
                <input type="text" id="moarefiname_bimeshode_tarikht" name="moarefiname.bimeShodeTarikhTavallod" class="datePkr" style="width : 120px;">
                <label>تاریخ تولد:</label>
            </td>
            <td>
                <input type="text" id="moarefiname_bimeshode_melli" name="moarefiname.bimeShodeCodeMelli">
                <label>کد ملی:</label>
            </td>
        </tr>
            <tr>
            <td>
                <input type="text" id="moarefiname_bimeshode_pedar" name="moarefiname.bimeShodeNamePedar">
                <label>نام پدر:</label>
            </td>

        </tr>
        <%}%>
    </table>
    <table class="inputList jtable" style="margin-top:30px" border="0" cellspacing="5" cellpadding="1">
        <tr>
            <th width='6%'>انتخاب</th>
            <th>نوع آزمايش</th>
            <th>نام آزمايش</th>
        </tr>
        <tbody id="azemayeshHa"></tbody>
    </table>
    <c:forEach items="${pishnehad.moarefinameList}" var="moarefiname">
        <c:if test="${moarefiname.vaziat == 'DAR_JARYAN'}">
            <% counterMoarefi++; %>
        </c:if>
    </c:forEach>
    <% if(counterMoarefi==0 || pishnehadRun.getNoeBimename().equals("خانواده")){ %>
        <input type="button" id="sabteMoarefinameButton" onclick="submitSodorMoarefiForm();" style="float:left;margin-left:20px" value="ثبت معرفی نامه"  />
    <%}%>
    <input type="button" onclick="submitSodorMoarefiFormAndTransit();" style="float:left;margin-left:20px" value="اتمام صدور معرفی نامه" />
    <script type="text/javascript">
        var azemayeshIdArray = new Array();
    var azemayeshArray = new Array();
    var azemayeshTypeArray = new Array();

    var azemayeshIdArrayAll = new Array();
    var azemayeshArrayAll = new Array();
    var azemayeshTypeArrayAll = new Array();
    <c:forEach var="row" items="${clinics}" varStatus="i">
    azemayeshIdArray[${i.index}] = new Array(
            <c:forEach var="azmayesh" items="${row.azmayeshs}" varStatus="j">
            '${azmayesh.id}'
            <c:if test="${j.index+1 lt fn:length(row.azmayeshs)}">, </c:if>
            </c:forEach>
    );
    azemayeshArray[${i.index}] = new Array(
            <c:forEach var="azmayesh" items="${row.azmayeshs}" varStatus="j">
            '${azmayesh.azmayeshName.name}'
            <c:if test="${j.index+1 lt fn:length(row.azmayeshs)}">, </c:if>
            </c:forEach>
    );
    azemayeshTypeArray[${i.index}] = new Array(
            <c:forEach var="azmayesh" items="${row.azmayeshs}" varStatus="j">
            '${azmayesh.azmayeshName.azmayeshType.type}'
            <c:if test="${j.index+1 lt fn:length(row.azmayeshs)}">, </c:if>
            </c:forEach>
    );
    </c:forEach>
    function fillAzemayeshs(index) {
        if ($("#moarefiname_clinicName").val() == "111111") {
            $("#moarefiname_sayer").removeAttr("readonly");
            $("#moarefiname_sayer").removeAttr("disabled");
        }
        else {
            $("#moarefiname_sayer").attr("readonly", true);
            $("#moarefiname_sayer").attr("disabled", true);
        }

        var str = "";
        if (azemayeshArray[index]) {
            for (var j2 = 0; j2 < azemayeshArray[index].length; j2++)
                str += "<tr><td><input type=\"checkbox\" id=\"azemayesh_chb_" + j2 + "\"/><input type=\"hidden\" id=\"azemayesh_inp_" + j2 + "\" name=\"moarefiname.azmayeshList[" + j2 + "].id\" value=\"" + azemayeshIdArray[index][j2] + "\"/></td>" +
                        "<td>" + azemayeshArray[index][j2] + "</td>" +
                        "<td>" + azemayeshTypeArray[index][j2] + "</td></tr>";
        }
        $('#azemayeshHa').html(str);
    }
        function submitSodorMoarefiFormAndTransit()
        {
            <c:if test="${pishnehad.state.id == 80}">
            $("#transitionSelector").val('15');
            </c:if>
            <c:if test="${pishnehad.state.id == 330}">
            $("#transitionSelector").val('60');
            </c:if>
            <c:if test="${pishnehad.state.id == 60}">
            $("#transitionSelector").val('44');
            </c:if>
            submitTransitionForm();
        }
        function ebtalMoarefiname(id)
        {
            $.post('/ebtalMoarefiname.action?pishnehad.id='+'${pishnehad.id}'+'&moarefiname.id='+id, function(msg) {
                $('#moarefiTable').html(msg);
                $(".jtable th").each(function(){
                    $(this).addClass("ui-state-default");
                });
                $(".jtable td").each(function(){
                    $(this).addClass("ui-widget-content");
                });
                <% if(!pishnehadRun.getNoeBimename().equals("خانواده")){ %>
                if($('#moarefinameSize').val() > 0) {
                    $('#sabteMoarefinameButton').hide();
                } else {
                    $('#sabteMoarefinameButton').show();
                }
                <% } %>
            });
        }
        function submitSodorMoarefiForm() {
        $("#transitionSelector").val('15');
        //                        $("#moarefitransid").val($("#transitionSelector").val());
        var elems = $("#azemayeshHa input[type=checkbox]");
        var sizeChecked = $("#azemayeshHa input[type=checkbox]:checked").length;
        var size = elems.length;
        var counter = 0;
        if (sizeChecked != 0) {
            elems.each(function () {
                if (!$(this).is(':checked')) {
                    $('#azemayesh_inp_' + this.id.split('azemayesh_chb_')[1]).attr('name', '');
                }
                counter++;
                if (counter == size) {
                    fillAndSubmit();
                }
            });
        } else {
            alertMessage("برای ادامه باید حتما حداقل یک آزمایش را انتخاب کرده و معرفی نامه برای آن صادر نمایید.");
        }
    <%--<%}%>--%>
    }

        function fillAndSubmit() {
            $.post('/sodurMoarefiname.action?pishnehadId='+${pishnehad.id}, $('#sodurMoarefinameForm').serialize(), function(msg) {
                $('#moarefiTable').html(msg);
                $(".jtable th").each(function(){
                    $(this).addClass("ui-state-default");
                });
                $(".jtable td").each(function(){
                    $(this).addClass("ui-widget-content");
                });
                <% if(!pishnehadRun.getNoeBimename().equals("خانواده")){ %>
                if($('#moarefinameSize').val() > 0) {
                    $('#sabteMoarefinameButton').hide();
                } else {
                    $('#sabteMoarefinameButton').show();
                }
                <% } %>
            });
        }
    </script>
</div>
</form>

