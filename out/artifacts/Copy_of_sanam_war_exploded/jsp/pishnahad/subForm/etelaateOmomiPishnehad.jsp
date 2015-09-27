<%@ page import="com.bitarts.parsian.model.User" %>
<%@ page import="com.bitarts.parsian.model.pishnahad.Pishnehad" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bitarts.parsian.model.constantItems.Tarh" %>
<%@ page import="com.bitarts.parsian.model.bimename.Gharardad" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 6/26/11
  Time: 11:29 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/taglibs.jsp" %>
<%  List<Tarh> tarhs = (List<Tarh>) request.getAttribute("tarhs");
    List<Gharardad> gorouhha = (List<Gharardad>) request.getAttribute("grouhha");
    Pishnehad pishnehadRun = (Pishnehad) request.getAttribute("pishnehad"); %>
<table class="inputList" border="0" cellspacing="5" cellpadding="1">
<col class=inputCol>
<col class=inputCol>
<c:if test="${pishnehad.options == 'CODE_MOVAGHAT'}">
    <tr>
        <td>
            <input type="text" name="pishnehad.radif" value="نماینده کد موقت" id="pishnehad_codeMovaghat" disabled="disabled" style="color:red;"/>
        </td>
    </tr>
</c:if>
<c:if test="${!isHalateElhaghie && (pishnehad==null || pishnehad.id==null)}">
    <c:if test="${optionsPishnehad}">
        <input type="hidden" name="pishnehad.options" value="CODE_MOVAGHAT"/>
    </c:if>
</c:if>
<tr>
    <td>
        <input type=text name="pishnehad.radif" value='${pishnehad.radif}' id="pishnehad_radif"
               disabled="disabled"/>
        &nbsp;<label>كد رهگيري پيشنهاد</label>
    </td>
    <td>
        <input type=text name="pishnehad.createdDate"
               value='${pishnehad.createdDate != null ? pishnehad.createdDate : currentDate}'
               id="pishnehad_createdDate"
               disabled="disabled"/>
        &nbsp;<label>تاریخ ایجاد پیشنهاد موقت</label>
    </td>
</tr>
<tr>
    <td>
        <select name="pishnehad.noePishnehad" id="pishnehad_noePishnehad" onchange="if(this.value == ''){}else{}" <sec:authorize ifAnyGranted="ROLE_NAMAYANDE"><c:if test="${isHalateElhaghie}">disabled="disabled" </c:if></sec:authorize>>
            <option ${pishnehad.noePishnehad == 'الكترونيكي'?'selected=selected':''}>الكترونيكي</option>
            <option ${pishnehad.noePishnehad == 'ایمیل'?'selected=selected':''}>ایمیل</option>
            <option ${pishnehad.noePishnehad == 'اينترنتي'?'selected=selected':''}>اينترنتي</option>
            <option ${pishnehad.noePishnehad == 'كتبي'?'selected=selected':''}>كتبي</option>
            <option ${pishnehad.noePishnehad == 'شفاهي'?'selected=selected':''}>شفاهي</option>
        </select>
        &nbsp;<label>نوع پیشنهاد</label>
    </td>
    <td>
        <s:if test="taghirKodAction">
                <input type=text name="kodeNamayandeKargozar"
                       value='${pishnehad.namayande.kodeNamayandeKargozar}' id="kodeNamayandeKargozar"
                       class="validate[required,custom[integer]] text-input noAnyDisable"/>
            <%--<sec:authorize ifNotGranted="ROLE_TAGHIR_CODE_DAEM">--%>
                <c:if test="${pishnehad.options!=null && pishnehad.options == 'CODE_MOVAGHAT'}">
                <input type="button" class="noAnyDisable" value="ثبت کد"
                       onclick="loadBazaryab();"/>
                <div style="display:none;" id="bazaryab_load">
                    <div align="center">
                        <p align="center">: مشخصات نمایندگی جدید </p>
                        <table class="jtable" cellpadding="0" cellspacing="0" style="text-align:center;">
                            <thead>
                            <tr>
                                <th style="padding:0; width:100px"  class="ui-state-default" >نام نمایندگی</th>
                                <th style="padding:0; width:100px" class="ui-state-default" >کد نماینده</th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="ui-widget-content" id="nNamaTd"></td>
                                    <td class="ui-widget-content" id="kNamaTd"></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <br/>
                    <div>
                        <p >نام کاربری بازاریاب را وارد کنید</p>
                        <input type="text" name="bazarYabUserName" id="baz_user_name" class="validate[required,custom[integer]] text-input noAnyDisable" />
                    </div>
                </div>
            </c:if>
            <%--</sec:authorize>--%>
            <sec:authorize ifAnyGranted="ROLE_TAGHIR_CODE_DAEM">
                <c:if test="${pishnehad.options==''}">
                <input type="button" class="noAnyDisable" value="ثبت کد"
                       onclick="loadBazaryab();"/>
                <div style="display:none;" id="bazaryab_load">
                    <div align="center">
                        <p align="center">: مشخصات نمایندگی جدید </p>
                        <table class="jtable" cellpadding="0" cellspacing="0" style="text-align:center;">
                            <thead>
                            <tr>
                                <th style="padding:0; width:100px"  class="ui-state-default" >نام نمایندگی</th>
                                <th style="padding:0; width:100px" class="ui-state-default" >کد نماینده</th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="ui-widget-content" id="nNamaTd"></td>
                                    <td class="ui-widget-content" id="kNamaTd"></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <br/>
                    <div>
                        <p >نام کاربری بازاریاب را وارد کنید</p>
                        <input type="text" name="bazarYabUserName" id="baz_user_name" class="validate[required,custom[integer]] text-input noAnyDisable" />
                    </div>
                </div>
            </c:if>
            </sec:authorize>
        </s:if>
        <s:else>
            <input type=text name="pishnehad.namayande.kodeNamayandeKargozar"
                   value='${pishnehad.namayande.kodeNamayandeKargozar}' id="kodeNamayandeKargozar"
                   class="validate[required,custom[integer]] text-input" disabled="disabled"/>
        </s:else>
        &nbsp;<label for="kodeNamayandeKargozar">كد نمايندگي</label>
    </td>
</tr>
<tr>
    <td>
        <select name="pishnehad.noeGharardad" id="pishnehad_noeGharardad" onchange="if(this.value == ''){}else{}" <sec:authorize ifAnyGranted="ROLE_NAMAYANDE"><c:if test="${isHalateElhaghie}">disabled="disabled" </c:if></sec:authorize>>
            <%--<c:if test="${pishnehad.gharardad.id == null}">--%>
                <option ${pishnehad.noeGharardad== 'قرارداد عمومي بيمه مركزي'?'selected=selected':''}>قرارداد عمومي بيمه
                    مركزي
                </option>
                <option ${pishnehad.noeGharardad== 'قرارداد بيمه-بانك پارسيان(BankAssurance)'?'selected=selected':''}>
                    قرارداد بيمه-بانك پارسيان(BankAssurance)
                </option>
                <option ${pishnehad.noeGharardad== 'قرارداد كاركنان بيمه پارسيان'?'selected=selected':''}>قرارداد
                    كاركنان بيمه پارسيان
                </option>
            <%--</c:if>--%>
            <option ${pishnehad.noeGharardad== 'قرارداد فروش جمعي'?'selected=selected':''}>قرارداد فروش جمعي
            </option>
            <%--<c:if test="${pishnehad.gharardad.id == null}">--%>
                <option ${pishnehad.noeGharardad== 'ساير'?'selected=selected':''}>ساير</option>
            <%--</c:if>--%>
        </select>
        &nbsp;<label>نوع قرارداد</label>
    </td>
    <td>
            <c:if test="${pishnehad.radif == null}">
                <sec:authorize ifAllGranted="ROLE_NAMAYANDE">
                    <select name="pishnehad.bazarYab.id" id="bazaryab_list" <sec:authorize ifAnyGranted="ROLE_NAMAYANDE"><c:if test="${isHalateElhaghie}">disabled="disabled" </c:if></sec:authorize>>
                        <c:forEach items="${bazaryabs}" var="b">
                            <option value="${b.id}">${b.personalCode}&nbsp;${b.firstName}&nbsp;${b.lastName}</option>
                        </c:forEach>
                    </select>
                </sec:authorize>
                <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
                    <input type="text" readonly="readonly" value="${user.firstName}&nbsp;${user.lastName}"/>
                    <input type="hidden" name="pishnehad.bazarYab.id" value="${user.id}"/>
                </sec:authorize>
            </c:if>
            <c:if test="${pishnehad.radif != null}">
                <sec:authorize ifAnyGranted="ROLE_NAMAYANDE,ROLE_KARSHENAS_SODUR,ROLE_PAS_RAEIS,ROLE_PAS_KARSHENAS_MASOUL,ROLE_PAS_KARSHENAS">
                    <select name="pishnehad.bazarYab.id" id="bazaryab_list" <sec:authorize ifAnyGranted="ROLE_NAMAYANDE"><c:if test="${isHalateElhaghie}">disabled="disabled" </c:if></sec:authorize>>
                        <c:forEach items="${bazaryabs}" var="b">
                            <option value="${b.id}"
                                    <c:if test="${pishnehad.bazarYab.id == b.id}">selected</c:if> >${b.personalCode}&nbsp;${b.firstName}&nbsp;${b.lastName}</option>
                        </c:forEach>
                    </select>
                </sec:authorize>
                <sec:authorize ifNotGranted="ROLE_NAMAYANDE,ROLE_KARSHENAS_SODUR,ROLE_PAS_RAEIS,ROLE_PAS_KARSHENAS_MASOUL,ROLE_PAS_KARSHENAS">
                    <input type="text" readonly="readonly" value="${pishnehad.bazarYab.personalCode}&nbsp;${pishnehad.bazarYab.firstName}&nbsp;${pishnehad.bazarYab.lastName}" class=""/>
                    <input type="hidden" name="pishnehad.bazarYab.id" value="${pishnehad.bazarYab.id}"/>
                </sec:authorize>
            </c:if>
        &nbsp;<label>نام بازاریاب</label>
    </td>
</tr>
<tr>
    <td>
        <input type="text" readonly="readonly"
               value="${pishnehad.namayande.vahedSodur.name}"/>
        <%--<select name="pishnehad.vahedSodor" id="pishnehad_vahedeSodour" onchange="if(this.value == ''){}else{}">--%>
        <%--<option ${pishnehad.noeGharardad== 'شعبه صدور و خسارت عمر مرکز 111115'?'selected=selected':''}>شعبه صدور--%>
        <%--و خسارت عمر مرکز 111115--%>
        <%--</option>--%>
        <%--</select>--%>
        &nbsp;<label>واحد صدور</label>
    </td>
    <td>
        <input type="text" readonly="readonly"
               value="${pishnehad.namayande.sarparast.name}"/>
        <%--<select name="pishnehad.shobe" id="pishnehad_shobe" onchange="if(this.value == ''){}else{}">--%>
        <%--<option ${pishnehad.noeGharardad== 'شعبه صدور و خسارت عمر مرکز 111115'?'selected=selected':''}>شعبه صدور--%>
        <%--و خسارت عمر مرکز 111115--%>
        <%--</option>--%>
        <%--</select>--%>
        &nbsp;<label>سرپرست</label>
    </td>
</tr>
<tr>
    <td>

        <%
            if(pishnehadRun == null || (pishnehadRun.getState().getId()==10 || pishnehadRun.getState().getId() == 20))
            {
        %>
                <select id="pishnehad_nameTarh" onchange="getValidationConstAjax()" name="pishnehad.tarh.id">
                    <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
                        <option selected="selected"></option>
                        <option value="8890869">سالمندان</option>
                    </sec:authorize>
                    <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
                        <%
                            for (Tarh tarh : tarhs)
                            {
                        %>
                        <option value="<%=tarh.getId() == null ? "" : tarh.getId()%>" <% if ((pishnehadRun != null && pishnehadRun.getTarh() != null && pishnehadRun.getTarh().getId().equals(tarh.getId()))
                                || ((pishnehadRun == null || pishnehadRun.getTarh() == null)) && tarh.getId() == null)
                        { %>
                                selected="selected" <% } %> >
                            <%=tarh.getName()%>
                        </option>
                        <%}%>
                    </sec:authorize>
                </select>
        <%
            }
            else
            {
        %>
                <select id="pishnehad_nameTarh" onchange="getValidationConstAjax()" name="pishnehad.tarh.id"
                        <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
                            <c:if test="${!isHalateElhaghie}">disabled="disabled"</c:if>
                        </sec:authorize> >
                    <%
                        for (Tarh tarh : tarhs)
                        {
                    %>
                    <option value="<%=tarh.getId() == null ? "" : tarh.getId()%>" <% if ((pishnehadRun != null && pishnehadRun.getTarh() != null && pishnehadRun.getTarh().getId().equals(tarh.getId()))
                            || ((pishnehadRun == null || pishnehadRun.getTarh() == null)) && tarh.getId() == null)
                    { %>
                            selected="selected" <% } %> >
                        <%=tarh.getName()%>
                    </option>
                    <%}%>
                </select>
        <%
            }
        %>
        &nbsp;<label>نام طرح</label>
    </td>
    <s:if test="%{!khesaratAction}">
        <%--<s:bean name="com.bitarts.parsian.action.PishnahadAction">--%>
            <td>
                <select id="pishnehad_gharardad" name="pishnehad.gharardad.id" <sec:authorize ifAnyGranted="ROLE_NAMAYANDE"><c:if test="${!isHalateElhaghie}">disabled="disabled"</c:if></sec:authorize> >
            <%for (Gharardad goroh : gorouhha) { %>
            <option value="<%=goroh.getId() == null ? "" : goroh.getId()%>" <% if ((pishnehadRun != null && pishnehadRun.getGharardad() != null && pishnehadRun.getGharardad().getId().equals(goroh.getId()))
                                                      || ((pishnehadRun == null || pishnehadRun.getGharardad() == null)) && goroh.getId() == null)  { %>
                    selected="selected" <% } %> >
                <%=goroh.getNameSherkat()%>
            </option>
            <%}%>
        </select>
                &nbsp;<label>گروه بيمه‌نامه</label>
            </td>
        <%--</s:bean>--%>
    </s:if>
</tr>
<tr>
    <td>
        <input type="text" readonly="readonly"
               value="${pishnehad.namayande.ostan.cityName}"/>
        &nbsp;<label>استان نمایندگی</label>
    </td>
    <td>
        <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
            <input type="text" readonly="readonly"
                   value="${pishnehad.namayande.telephone}"/>
            &nbsp;<label>تلفن نماینگی</label>
        </sec:authorize>
        <sec:authorize ifAllGranted="ROLE_NAMAYANDE">
            <input type="text"
                   value="${user.namayandegi.telephone}" name="namayandegiTelephone" <sec:authorize ifAnyGranted="ROLE_NAMAYANDE"><c:if test="${isHalateElhaghie}">readonly="readonly" </c:if></sec:authorize>/>

            &nbsp;<label>تلفن نماینگی</label>
        </sec:authorize>
    </td>
</tr>
<tr>
    <td>
        <select name="pishnehad.noeBimename" id="pishnehad_noeBimename" onchange="if(this.value == ''){}else{}" <sec:authorize ifAnyGranted="ROLE_NAMAYANDE"><c:if test="${isHalateElhaghie}">disabled="disabled" </c:if></sec:authorize>>
            <option ${pishnehad.noeBimename == 'انفرادی'?'selected=selected':''}>انفرادی</option>
            <option ${pishnehad.noeBimename == 'خانواده'?'selected=selected':''}>خانواده</option>
        </select>
        &nbsp;<label>نوع بیمه نامه</label>
    </td>
</tr>
<c:if test="${pishnehad.bimename != null}">
<tr>
    <td>
        <input type=text value='${pishnehad.bimename.shomare}' id="bimename_shomare"
               disabled="disabled"/>
        &nbsp;<label>شماره بیمه نامه</label>
    </td>
    <td>
        <input type=text value='${pishnehad.bimename.tarikhSodour}' id="bimename_tarikhSodour"
               disabled="disabled"/>
        &nbsp;<label>تاریخ صدور</label>
    </td>
</tr>
</c:if>
<%--<tr>--%>
<%--<td>--%>
<%--<label>طرح: </label>--%>
<%--</td>--%>
<%--<td>--%>
<%--<s:select theme="simple" list="tarhs" key="tarhId" label="" listKey="id" listValue="name" emptyOption="true"--%>
<%--cssStyle="width: 300px"/>--%>
<%--</td>--%>
<%--</tr>--%>
<% List<User> karshenasha_omomi = (List<User>) request.getAttribute("karshenasha"); %>
<c:if test="${pishnehad != null && pishnehad.karshenas != null}">
    <tr>
        <td>
            <select class="noAnyDisable" id="whichkarshenas_id"  <sec:authorize
                    ifAnyGranted="ROLE_KARSHENAS_SODUR,ROLE_PEZESHK,ROLE_KARBAR_MALI,ROLE_KARSHENAS_BAYEGANI,ROLE_NAMAYANDE,ROLE_BAZARYAB,ROLE_KARMOZD,ROLE_KARMOZD_NAMAYANDE"> disabled="disabled" </sec:authorize> >
                <%
                    int i=0;
                    for (User user : karshenasha_omomi) { %>
                <option value="<%=user.getId()%>" <% if (pishnehadRun.getKarshenas().getId().equals(user.getId())) { i++;%>
                        selected="selected" <% } %> >
                    <%=user.getFirstName()%>&nbsp;&nbsp;<%=user.getLastName()%> - <%=user.getAssignCount()%>
                </option>
                <%  }
                    if(pishnehadRun.getKarshenas().getId()!=null && i==0)
                    {
                %>
                        <option value="<%=pishnehadRun.getKarshenas().getId()%>" selected>
                            <%=pishnehadRun.getKarshenas().getFirstName()%>&nbsp;&nbsp;<%=pishnehadRun.getKarshenas().getLastName()%> - <%=pishnehadRun.getKarshenas().getAssignCount()%>
                        </option>
                <%
                    }

                %>
            </select>

            &nbsp;<label>نام کارشناس</label>
        </td>
    </tr>

    <sec:authorize ifAnyGranted="ROLE_KARSHENAS_MASOUL,ROLE_RAEIS_SODUR">
        <tr>
            <td>
                <input type="button" value="تغییر کارشناس" onclick="updateKarshenas();" class="noAnyDisable"/>
            </td>
        </tr>

    </sec:authorize>
</c:if>
</table>

<script type="text/javascript">
    function updateKarshenas() {
        $.post('/updateKarshenas.action?pishnehad.id=${pishnehad.id}&karshenasId=' + $('#whichkarshenas_id').val(), function () {
//            alertMessage("کارشناس با موفقیت تغییر یافت.");
        });
    }

    var dilg ;
    function loadBazaryab()
    {
        $.post
        (
            '/loadDeafultBazaryab.action?kodeNamayandeKargozar='+$('#kodeNamayandeKargozar').val(),
            function(msg)
            {

                $('#nNamaTd').html(msg.split(',')[0]);
                $('#kNamaTd').html(msg.split(',')[1]);
                $('#baz_user_name').val(msg.split(',')[2]);
                dilg = $('#bazaryab_load').dialog({
                    modal:true,
                    width: 400,
                    resizable:false,
                    closeText: "",
                    title:"تغییر کد نمایندگی",
                    close:enseraf,
                    buttons: {
                        "ثبت": function(){
                                            if($('#nNamaTd').html()==''||$('#kNamaTd').html()=='')
                                            {
                                                $(this).dialog("close");
                                                alertMessage("کد نمایندگی صحیح الزامی است.");
                                            }
                                            else
                                            {
                                                var type_enum=msg.split(',')[3];
                                                var type_enum_cur='${pishnehad.namayande.namayandeType}';
                                                if((type_enum_cur == "FORUSHANDE") &&(type_enum!="MOJTAMA" && type_enum!="SHOBE" && type_enum!="ICD") && ${pishnehad.options==''})
                                                {
                                                    alertMessage("بیمه نامه های نمایندگان فروش را تنها می توان به یکی از نمایندگان شعبه، ICDو یا مجتمع بیمه ای تخصیص داد.")
                                                }
                                                else if((type_enum_cur == "NAMAYANDE_HOGHUGHI" ||type_enum_cur == "NAMAYANDE_HAGHIGHI" ||type_enum_cur == "KARGOZAR_HOGHUGHI" ||type_enum_cur == "KARGOZAR_HAGHIGHI" ||type_enum_cur == "BAJE_NAMAYANDE_HOGHUGHI") && (type_enum == "FORUSHANDE") && ${pishnehad.options==''})
                                                {
                                                    alertMessage("این بیمه نامه را تنها نمی توان به نمایندگان فروش تخصیص داد.")
                                                }

                                                else
                                                {
                                                    window.location='/sabteDarkhasteTaghirCode?pishnehad.id=<s:property value="pishnehad.id"/>&kodeNamayandeKargozar='+$('#kodeNamayandeKargozar').val()+'&bazarYabUserName='+$('#baz_user_name').val();
                                                }
                                            }
                                         },
                        "انصراف": function(){$(this).dialog("close");}
                    }
                });
            }
        );
    }
</script>
