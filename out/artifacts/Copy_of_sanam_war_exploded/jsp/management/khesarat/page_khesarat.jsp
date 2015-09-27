<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 6/26/11
  Time: 11:29 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:actionerror/>
<s:actionmessage/>
<script type="text/javascript" src="js/jquery-ui-1.8.9.custom.min.js"></script>
<script type="text/javascript">

    $(function()
    {
        $("#tabs_khesarat").tabs({
            selected: 0
        });
    });


    function selectFot()
    {
        khesaratCount(('I'));
        $('#cnt_I').attr('selected','selected');
        $('#cnt_II').removeAttr('selected');
    }

    function khesaratCount(arg)
    {
        if (arg == 'II')
        {
            $('#kh_2').show();
            $('#tabs_kh_two').show();
            $("#tabs_khesarat").tabs({selected: 1});
        }
        if(arg=='I')
        {
            $('#kh_2').hide();
            $('#tabs_kh_two').hide();
            $("#tabs_khesarat").tabs({selected: 0});
        }
    }
</script>

<form action="sabteDarkhasteBazkharid" method="post" id="submit_khesarat" theme="simple">
<input type="hidden" id="transition_id" name="transitionId" value="650"/>
<label for="count_khesarat">تعداد خسارات </label>
<input type="hidden" id="khs_cnt" name="khesaratCount" value="1"/>
<select id="count_khesarat" disabled="disabled">
    <option id="cnt_I" onclick="khesaratCount('I');$('#khs_cnt').val(1);"
        <c:if test="${darkhastBazkharid.state==null ||(darkhastBazkharid.state!=null && darkhastBazkharid.khesaratII==null)}">selected="selected"</c:if>
        value="1">يک</option>
    <option id="cnt_II" onclick="khesaratCount('II');$('#khs_cnt').val(2);"
            <c:if test="${darkhastBazkharid.state!=null && darkhastBazkharid.khesaratII!=null}">selected="selected"</c:if>
            value="2">دو</option>
</select>
<br/><br/>

<%--<input type="hidden" name="bimename" value="<s:property value="pishnehad.bimename.id"/>">--%>
<%--<input type="hidden" name="pishnehad.id" value="<s:property value="pishnehad.id"/>">--%>
<%--<input type="hidden" name="bimename.id" value="<s:property value="bimename.id"/>">--%>
<s:if test="khesaratAction">
    <script type="text/javascript">
        <c:if test="${bimename.vameTasvieNashode}">
            $('#kh_hadese').attr('disabled', 'disabled');
            $('#1kh_hadese').attr('disabled', 'disabled');
            $('#kh_hadese').removeAttr('checked');
            $('#1kh_hadese').removeAttr('checked');

            $('#kh_omr').removeAttr('checked');
            $('#1kh_omr').removeAttr('checked');
            $('#kh_omr').attr('disabled', 'disabled');
            $('#1kh_omr').attr('disabled', 'disabled');
        </c:if>
    </script>
    <input type="hidden" name="darkhastBazkharid.id" value="${darkhast.id}"/>
    <input type="hidden" name="darkhastBazkharid.darkhastType" value="${darkhast.darkhastType}"/>
    <input type="hidden" name="darkhast.darkhastType" value="${darkhast.darkhast.darkhastType}"/>
    <input type="hidden" name="darkhast.id" value="${darkhast.darkhast.id}"/>
</s:if>
<s:else>
    <input type="hidden" name="darkhastBazkharid.id" value="${darkhastBazkharid.id}"/>
</s:else>
<div id="tabs_khesarat">
    <ul>
        <li id="kh_1"><a href="#tabs_kh_own">خسارت اول</a></li>
        <li id="kh_2" style="display: none;"><a href="#tabs_kh_two">خسارت دوم</a></li>
    </ul>
    <div id="tabs_kh_own">
        <%--<s:form action="sabteDarkhasteBazkharid" method="post" id="kh_form" theme="simple">--%>

            <%--<input type="hidden" name="khesarat.bimename.id" value="<s:property value="pishnehad.bimename.id"/>">--%>
            <%--<input type="hidden" name="pishnehad.id" value="<s:property value="pishnehad.id"/>">--%>
            <%--<input type="hidden" name="bimename.id" value="<s:property value="bimename.id"/>">--%>
            <%--<s:if test="khesaratAction">--%>
                <%--<input type="hidden" name="darkhastBazkharid.id" value="${darkhast.id}"/>--%>
                <%--<input type="hidden" name="darkhastBazkharid.darkhastType" value="${darkhast.darkhastType}"/>--%>
                <%--<input type="hidden" name="darkhast.darkhastType" value="${darkhast.darkhast.darkhastType}"/>--%>
                <%--<input type="hidden" name="darkhast.id" value="${darkhast.darkhast.id}"/>--%>
            <%--</s:if>--%>
            <table class="mystrippedtable" id="tablekhesarat" dir="rtl" cellpadding="2px" cellspacing="0px"
                   style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
            <input type="hidden" id="khsrt_type" name="darkhastBazkharid.khesaratI.khesaratType" value="${darkhastBazkharid.khesaratI.khesaratType}"/>
                <tr   >
                    <td>نوع مورد خسارت:</td>
                    <td align="right">
                    <c:if test="${!bimename.vameTasvieNashode}">
                        <input type="checkbox" name="kh_omr" id="kh_omr" value="true" onclick="selectFot();updateCheckBoxes(this);"
                                <c:if test="${darkhastBazkharid.khesaratI.khesaratType=='OMR'}">
                                    checked="true" disabled="disabled"
                                </c:if>
                                />
                        فوت به هر علت
                        <div id="chk_omr" ></div>

                        <br/>
                        <s:if test="pishnehad.bimename.pooshesh_hadese">
                            <input type="checkbox" name="kh_hadese" id="kh_hadese" value="true" onclick="selectFot();updateCheckBoxes(this);"
                                   <c:if test="${darkhastBazkharid.khesaratI.khesaratType=='HADESE'}">
                                       checked="true" disabled="disabled"
                                   </c:if>
                                   />
                        </s:if>
                        <s:else><input type="checkbox" name="kh_hadese" value="true" disabled="disabled"/></s:else>
                        فوت در اثر حادثه
                        <div id="chk_hadese" ></div>
                        <br/>
                    </c:if>
                        <s:if test="pishnehad.bimename.pooshesh_amraz_forKhesarat">
                            <input type="checkbox" name="kh_amraz" id="kh_amraz" value="true"
                                   <c:if test="${darkhastBazkharid.khesaratI.khesaratType=='AMRAZ'}">
                                       checked="true"
                                       disabled="disabled"
                                   </c:if>
                                   onclick="updateCheckBoxes(this);"/>
                        </s:if>
                        <s:else><input type="checkbox" name="kh_amraz" value="true" disabled="disabled"/></s:else>
                        امراض خاص
                        <div id="chk_amraz" ></div>
                        <br/>
                        <s:if test="pishnehad.bimename.pooshesh_naghsOzv_forKhesarat">
                            <input type="checkbox" name="kh_naghs" id="kh_naghs" value="true"
                                    <c:if test="${darkhastBazkharid.khesaratI.khesaratType=='NAGHSOZV'}">
                                        checked="true"
                                        disabled="disabled"
                                    </c:if>
                                   onclick="updateCheckBoxes(this);"/>
                        </s:if>
                        <s:else><input type="checkbox" name="kh_naghs" value="true" disabled="disabled"/></s:else>
                        نقص عضو
                        <div id="chk_naghs" ></div>
                        <br/>
                        <%--<s:if test="pishnehad.bimename.pooshesh_moafiat">--%>
                            <%--<input type="checkbox" name="kh_moafiat" id="kh_moafiat" value="true"--%>
                                    <%--<c:if test="${darkhastBazkharid.khesaratI.khesaratType=='MOAFIAT'}">--%>
                                        <%--checked="true"--%>
                                        <%--disabled="disabled"--%>
                                    <%--</c:if>--%>
                                   <%--onclick="updateCheckBoxes(this);"/>--%>
                        <%--</s:if>--%>
                        <%--<s:else><input type="checkbox" name="kh_moafiat" value="true" disabled="disabled"/></s:else>--%>
                        <%--‌معافيت درصورت ازكارافتادگي--%>
                        <%--<div id="chk_moafiat" ></div>--%>
                        <%--<br/>--%>
                    </td>
                    <c:if test="${darkhastBazkharid.shomareParvande!=null}">
                        <td>شماره پرونده: </td>
                        <td><input type="text" value="${darkhastBazkharid.shomareParvande}" readonly="readonly" /></td>
                    </c:if>
                </tr>
                <tr >
                    <td><label id="naghs_darsad_lbl" style="display: none">درصد نقص عضو : </label></td>
                    <td><span class="noThing"></span>
                        <input type="text" id="darsad_naghs_ozv" maxlength="3"
                               class="validate[required,custom[integer]]"
                               style="display: none;"
                               <%--onchange="calcAmountElamShodeForNaghsOzv();" --%>
                               onkeyup="maxHund();" name="darkhastBazkharid.khesaratI.darsadNaghsOzv"
                               <c:if test="${darkhastBazkharid.khesaratI.darsadNaghsOzv!=null}">value="${darkhastBazkharid.khesaratI.darsadNaghsOzv}"</c:if>
                               <c:if test="${darkhastBazkharid.khesaratI.darsadNaghsOzv==null}">value="0"</c:if>
                                />
                    </td>

                    <td>علت خسارت:</td>
                    <td><span class="noThing"></span>
                        <select id="khesarat_type_list" name="darkhastBazkharid.khesaratI.ellat"
                                <c:if test="${darkhastBazkharid.khesaratI.ellat!=null}"> disabled="disabled"</c:if>
                                >
                        <option selected="selected">${darkhastBazkharid.khesaratI.ellat}</option>
                        </select>
                    </td>
                </tr>
            <tr >
                <td>شرح خسارت:</td>
                <td colspan="4"><span class="noThing"></span>
                    <%--<s:textarea theme="simple" key="darkhastBazkharid.khesaratI.sharhKhesarat" value="${darkhastBazkharid.khesaratI.sharhKhesarat}" id="khe_sharh"--%>
                    <%--label="" cols="40" rows="3"/>--%>
                    <textarea name="darkhastBazkharid.khesaratI.sharhKhesarat" rows="3" cols="102" id="khe_sharh">${darkhastBazkharid.khesaratI.sharhKhesarat}</textarea>
                </td>
            </tr>
                <tr >
                        <%--<td>علت حادثه:</td>--%>
                        <%--<td>--%>
                        <%--<s:textfield key="khesarat.ellat" label="" theme="simple" cssStyle="width: 280px"/>--%>
                        <%--</td>--%>
                    <td>واحد اعلام کننده خسارت :</td>
                    <td>
                        <span class="noThing"></span>
                        <input type="hidden" name="darkhastBazkharid.khesaratI.pronouncerOrg.id" id="pronouncerOrgId"
                                <c:if test="${darkhastBazkharid.khesaratI.pronouncerOrg!=null}">
                                    value="${darkhastBazkharid.khesaratI.pronouncerOrg.id}"
                                </c:if>
                                <c:if test="${darkhastBazkharid.khesaratI.pronouncerOrg==null}">
                                    <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
                                        value="${user.namayandegi.id}"
                                    </sec:authorize>
                                    <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
                                        value="${user.mojtamaSodoor.id}"
                                    </sec:authorize>
                                </c:if>
                               readonly="readonly"/>

                        <input type="text" name="darkhastBazkharid.khesaratI.pronouncerOrg.name" id="pronouncerOrgName"
                                <c:if test="${darkhastBazkharid.khesaratI.pronouncerOrg!=null}">
                                    value="${darkhastBazkharid.khesaratI.pronouncerOrg.name}"
                                </c:if>
                                <c:if test="${darkhastBazkharid.khesaratI.pronouncerOrg==null}">
                                    <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
                                        value="${user.namayandegi.name}"
                                    </sec:authorize>
                                    <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
                                        value="${user.mojtamaSodoor.name}"
                                    </sec:authorize>
                                </c:if>
                               readonly="readonly"/>

                            <%--<input type="button" id="btnNamayandeSelector" value="انتخاب" onclick="fillNamayandegi('pronouncerOrgId','pronouncerOrgName','namayandeLaghvShode_yes');" />--%>
                    </td>
                    <td>نحوه اعلام خسارت:</td>
                    <td>
                        <span class="noThing"></span>
                        <input type="text" name="darkhastBazkharid.khesaratI.nahveElam"
                                     <c:if test="${darkhastBazkharid.khesaratI.nahveElam!=null}">value="${darkhastBazkharid.khesaratI.nahveElam}"</c:if>
                                    <c:if test="${darkhastBazkharid.khesaratI.nahveElam==null}">value="نامه و حضوری" </c:if>
                        />
                    </td>
                </tr>
                <tr >
                    <td>استان محل خسارت :</td>
                    <td>
                        <span class="noThing"></span>
                        <input type="text" name="darkhastBazkharid.khesaratI.ostanMahalleHadese.cityName" id="kh_ostan"
                               class="validate[required]"
                               value="${darkhastBazkharid.khesaratI.ostanMahalleHadese.cityName}" readonly="readonly"/>
                        <input type="hidden" name="darkhastBazkharid.khesaratI.ostanMahalleHadese.cityId"
                               id="kh_ostanId"
                               value="${darkhastBazkharid.khesaratI.ostanMahalleHadese.cityId}"/>
                    </td>
                    <td>شهر محل خسارت :</td>
                    <td>
                        <input type="text" name="darkhastBazkharid.khesaratI.shahrMahalleHadese.cityName" id="kh_shahr"
                               class="validate[required]"
                               value="${darkhastBazkharid.khesaratI.shahrMahalleHadese.cityName}" readonly="readonly"/>
                        <input type="hidden" name="darkhastBazkharid.khesaratI.shahrMahalleHadese.cityId"
                               id="kh_shahrId"
                               value="${darkhastBazkharid.khesaratI.shahrMahalleHadese.cityId}"/>

                        <%--<input type="button" id="btnOstanShahrSelector" value="انتخاب"--%>
                               <%--onclick="fillShahrOstan('kh_shahrId','kh_shahr','kh_ostanId','kh_ostan','kh_vahedSodor')"--%>
                               <%--style=""/>--%>
                        <span class="ui-icon ui-icon-search" id="btnOstanShahrSelector"
                              onclick="fillShahrOstan('kh_shahrId','kh_shahr','kh_ostanId','kh_ostan','kh_vahedSodor')"
                              style="float:left;" title="جستجو"></span>
                    </td>
                </tr>
                <tr >
                    <td <sec:authorize ifAnyGranted="ROLE_NAMAYANDE"></sec:authorize> >مبلغ خسارت اعلام شده:</td>
                    <td><span class="noThing"></span>
                        <input type="text" name="darkhastBazkharid.khesaratI.amountElamShode" value="${darkhastBazkharid.khesaratI.amountElamShode}"
                        <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
                            readonly="readonly" disabled="disabled"
                        </sec:authorize>
                                     class="digitSeparators"  id="amount_elam_shode"/>
                    </td>
                    <td>تاریخ وقوع خسارت :</td>
                    <td>
                        <input type="text" name="darkhastBazkharid.khesaratI.accidentDate" onchange="checkAccidentDate();" readonly="readonly"
                               value="${darkhastBazkharid.khesaratI.accidentDate}" id="accident_Date" class="datePkr validate[required[date]]"/>
                    </td>
                </tr>
                <tr >
                    <td>مبلغ اندوخته روز خسارت:</td>
                    <td><span class="noThing"></span>
                        <input type="text" id="kh_andookhte" name="darkhastBazkharid.khesaratI.andukhte" value="${darkhastBazkharid.khesaratI.andukhte}"
                                <sec:authorize ifAnyGranted="ROLE_NAMAYANDE"> disabled="disabled"</sec:authorize>
                               class="digitSeparators" readonly="true"/>
                    </td>
                    <td>مبلغ هزینه های آتی:</td>
                    <td><span class="noThing"></span>
                        <input type="text" id="kh_ati"
                                <sec:authorize ifAnyGranted="ROLE_NAMAYANDE"> disabled="disabled"</sec:authorize>
                               name="darkhastBazkharid.khesaratI.amountAti" value="${darkhastBazkharid.khesaratI.amountAti}"
                                     class="digitSeparators" readonly="true"/>
                    </td>
                </tr>
                <tr >
                    <td>مبلغ خسارت تایید شده: </td>
                    <td><span class="noThing"></span>
                        <input type="text" id="khe_taidShode"   <sec:authorize ifAnyGranted="ROLE_NAMAYANDE"> readonly="readonly" disabled="disabled"</sec:authorize>
                                     name="darkhastBazkharid.khesaratI.amountTaidShode" value="${darkhastBazkharid.khesaratI.amountTaidShode}"
                                     <sec:authorize ifNotGranted="ROLE_RAEIS_KHESARAT"> <c:if test="${darkhastBazkharid.akhzeMojavezKhesarat && darkhastBazkharid.state.id!=644}">readonly="readonly"</c:if></sec:authorize>
                                     class="digitSeparators" />
                    </td>
                    <td <sec:authorize ifAnyGranted="ROLE_NAMAYANDE"></sec:authorize> >مبلغ خسارت ارفاق مدیریتی:</td>
                    <td><span class="noThing"></span>
                        <input type="text" id="kh_erfagh" name="darkhastBazkharid.khesaratI.amountErfagh"
                                <sec:authorize ifAnyGranted="ROLE_NAMAYANDE"> disabled="disabled" readonly="readonly"</sec:authorize>
                                     class="digitSeparatorsManfi validate[required]"
                                <c:if test="${darkhastBazkharid.khesaratI.amountErfagh!=null}">
                                     value="${darkhastBazkharid.khesaratI.amountErfagh}"
                                </c:if>
                                <c:if test="${darkhastBazkharid.khesaratI.amountErfagh==null}">
                                     value="0"
                                </c:if>/>
                    </td>
                </tr>
                <tr >
                    <c:if test="${darkhastBazkharid.khesaratI.khesaratType=='OMR' || darkhastBazkharid.khesaratI.khesaratType=='HADESE'}">
                        <td>جمع اقساط پرداخت شده آتي</td>
                        <td><span class="noThing"></span>
                            <input type="text" name="darkhastBazkharid.khesaratI.jameAghsatePardakhtiAti" value="${darkhastBazkharid.khesaratI.jameAghsatePardakhtiAti}"
                                  disabled="disabled" readonly="readonly"
                                         cssClass="digitSeparators" id="amount_mazad"
                                         />
                        </td>
                    </c:if>
                    <td>خسارت قابل پرداخت:</td>
                    <td><span class="noThing"></span>
                        <input type="text" id="kh_ghabelPardakht"      <sec:authorize ifAnyGranted="ROLE_NAMAYANDE"> disabled="disabled"</sec:authorize>
                                     name="darkhastBazkharid.khesaratI.amountGhabelPardakht" value="${darkhastBazkharid.khesaratI.amountGhabelPardakht}"
                                     class="digitSeparatorsManfi" readonly="true"/>
                    </td>
                </tr>
                <tr >
                    <td <sec:authorize ifAnyGranted="ROLE_NAMAYANDE"></sec:authorize> >نظریه کارشناس:</td>
                    <td colspan="4"><span class="noThing"></span>
                        <%--<s:textarea key="darkhastBazkharid.khesaratI.nazarKarshenas" cols="40" rows="3" theme="simple" label=""--%>
                                    <%--id="kh_nazarie"/>--%>
                        <textarea name="darkhastBazkharid.khesaratI.nazarKarshenas" rows="3" cols="102"
                                  <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">disabled="disabled" readonly="readonly"</sec:authorize>
                                  id="khe_sharh">${darkhastBazkharid.khesaratI.nazarKarshenas}</textarea>
                    </td>
                </tr>

            </table>
            <br/><br/>

            <s:if test="%{havaleHa=='null'}">
                <input type="hidden" name="khesaratId" value="${khesarat.id}"/>
                <input type="hidden" name="updateOrSave" value="update"/>
            </s:if>
            <%--<s:submit action="sabtKhesarat" value="ثبت"/>--%>
            <%--<input type="button" onclick="sbmt();" value="ثبت"/>--%>

        <c:if test="${darkhastBazkharid.state.id==642||darkhastBazkharid.state.id==648||darkhastBazkharid.state.id==644||darkhastBazkharid.state.id==645||darkhastBazkharid.state.id==650||darkhastBazkharid.state.id==600||darkhastBazkharid.state.id==653||darkhastBazkharid.state.id==654}">
                <%--<input type="hidden" name="darkhastBazkharid.id" value="${darkhastBazkharid.id}"/>--%>
                <input type="button" onclick="calculate();" class="noAnyDisable" value="محاسبه"/>
            </c:if>
            <%--<input type="button" id="btn_fill_zero" onclick="calculateWithZeroVal();" style="display: none"--%>
                   <%--value="محاسبه با مقادیر صفر"/>--%>

            <%--<input type="button" style="float:right;"--%>
                   <%--onclick="window.open('/printMohasebeKhesarat?movaghat=yes&pishnehad.id=<s:property--%>
                           <%--value="pishnehad.id"/>&khesarat.amountGhabelPardakht='+$('#kh_ghabelPardakht').val()+--%>
                           <%--'&khesarat.amountMazad='+$('#amount_mazad').val()+--%>
                           <%--'&khesarat.amountErfagh='+$('#kh_erfagh').val()+--%>
                           <%--'&khesarat.amountTaidShode='+$('#khe_taidShode').val()+--%>
                           <%--'&khesarat.sharhKhesarat='+$('#khe_sharh').val()+--%>
                           <%--'&khesarat.amountElamShode='+$('#amount_elam_shode').val()+--%>
                           <%--'&khesarat.amountAti='+$('#kh_ati').val()+--%>
                           <%--'&khesarat.nazarKarshenas='+$('#kh_nazarie').val()+--%>
                           <%--'&khesarat.accidentDate='+$('#accident_Date').val()+--%>
                           <%--'&khesarat.andukhte='+$('#kh_andookhte').val()--%>
                           <%--)"--%>
                   <%--value="برگ محاسبه خسارت بیمه نامه عمر انفرادی"></td>--%>


            <%--<p>درصورتيكه مدارك شما ظرف مدت 30 روز ازشروع ثبت خسارت به دست اداره خسارت نرسد درخواست شما منقضي مي گردد</p>--%>
            <%--</s:form>--%>
    </div>
    <div id="tabs_kh_two" style="display: none;">
 <%--<s:form action="sabteDarkhasteBazkharid" method="post" id="1kh_form" theme="simple">--%>
    <%--<input type="hidden" name="khesarat.bimename.id" value="<s:property value="pishnehad.bimename.id"/>">--%>
    <%--<input type="hidden" name="pishnehad.id" value="<s:property value="pishnehad.id"/>">--%>
    <%--<input type="hidden" name="bimename.id" value="<s:property value="bimename.id"/>">--%>
    <%--<s:if test="khesaratAction">--%>
    <%--<input type="hidden" name="darkhastBazkharid.id" value="${darkhast.id}"/>--%>
    <%--<input type="hidden" name="darkhastBazkharid.darkhastType" value="${darkhast.darkhastType}"/>--%>
    <%--<input type="hidden" name="darkhast.darkhastType" value="${darkhast.darkhast.darkhastType}"/>--%>
    <%--<input type="hidden" name="darkhast.id" value="${darkhast.darkhast.id}"/>--%>
    <%--</s:if>--%>
 <table class="mystrippedtable" id="1tablekhesarat" dir="rtl" cellpadding="2px" cellspacing="0px"
        style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
 <input type="hidden" id="1khsrt_type" name="darkhastBazkharid.khesaratII.khesaratType" value="${darkhastBazkharid.khesaratII.khesaratType}"/>
        <tr >

            <td>نوع مورد خسارت:</td>
            <td align="right">
                <c:if test="${!bimename.vameTasvieNashode}">
                <input type="checkbox" name="kh_omrII" id="1kh_omr" value="true" onclick="updateCheckBoxes1(this);"
                        <c:if test="${darkhastBazkharid.khesaratII.khesaratType=='OMR'}">
                            checked="true"
                            disabled="disabled"
                        </c:if>
                        />
                فوت به هر علت
                <div id="1chk_omr" ></div>
                <br/>
                <s:if test="pishnehad.bimename.pooshesh_hadese">
                    <input type="checkbox" name="kh_hadeseII" id="1kh_hadese" value="true"
                            <c:if test="${darkhastBazkharid.khesaratII.khesaratType=='HADESE'}">
                                checked="true"
                                disabled="disabled"
                            </c:if>
                           onclick="updateCheckBoxes1(this);"/>
                </s:if><s:else><input type="checkbox" name="kh_hadeseII" value="true" disabled="disabled"/></s:else>
                فوت در اثر حادثه
                <div id="1chk_hadese" ></div>
                <br/>
                </c:if>
                <s:if test="pishnehad.bimename.pooshesh_amraz_forKhesarat">
                    <input type="checkbox" name="kh_amrazII" id="1kh_amraz" value="true"
                            <c:if test="${darkhastBazkharid.khesaratII.khesaratType=='AMRAZ'}">
                                checked="true"
                                disabled="disabled"
                            </c:if>
                           onclick="updateCheckBoxes1(this);"/>
                </s:if><s:else><input type="checkbox" name="kh_amrazII" value="true" disabled="disabled"/></s:else>
                امراض خاص
                <div id="1chk_amraz"></div>
                <br/>
                <s:if test="pishnehad.bimename.pooshesh_naghsOzv_forKhesarat">
                    <input type="checkbox" name="kh_naghsII" id="1kh_naghs" value="true"
                            <c:if test="${darkhastBazkharid.khesaratII.khesaratType=='NAGHSOZV'}">
                                checked="true"
                                disabled="disabled"
                            </c:if>
                           onclick="updateCheckBoxes1(this);"/>
                </s:if><s:else><input type="checkbox" name="kh_naghsII" value="true" disabled="disabled"/></s:else>
                نقص عضو
                <div id="1chk_naghs" ></div>
                <br/>
                <%--<s:if test="pishnehad.bimename.pooshesh_moafiat">--%>
                    <%--<input type="checkbox" name="kh_moafiatII" id="1kh_moafiat" value="true"--%>
                            <%--<c:if test="${darkhastBazkharid.khesaratII.khesaratType=='MOAFIAT'}">--%>
                                <%--checked="true"--%>
                                <%--disabled="disabled"--%>
                            <%--</c:if>--%>
                           <%--onclick="updateCheckBoxes1(this);"/>--%>
                <%--</s:if><s:else><input type="checkbox" name="kh_moafiatII" value="true" disabled="disabled"/></s:else>--%>
                <%--‌معافيت درصورت ازكارافتادگي--%>
                <%--<div id="1chk_moafiat"></div>--%>
                <%--<br/>--%>
            </td>
            <c:if test="${darkhastBazkharid.shomareParvande!=null}">
                <td>شماره پرونده:</td>
                <td><input type="text" value="${darkhastBazkharid.shomareParvande}" readonly="readonly"/></td>
            </c:if>
        </tr>
        <tr >
            <td><label id="1naghs_darsad_lbl" style="display: none">درصد نقص عضو : </label></td>
            <td>
                <span class="noThing"></span>
                <input type="text" id="1darsad_naghs_ozv" maxlength="3" class="validate[required,custom[integer]]"
                       style="display: none;"
                       <%--onchange="calcAmountElamShodeForNaghsOzv1();" --%>
                       onkeyup="maxHund1();"
                       name="darkhastBazkharid.khesaratII.darsadNaghsOzv"
                       <c:if test="${darkhastBazkharid.khesaratII.darsadNaghsOzv!=null}">value="${darkhastBazkharid.khesaratII.darsadNaghsOzv}"</c:if>
                       <c:if test="${darkhastBazkharid.khesaratII.darsadNaghsOzv==null}">value="0"</c:if>
                        />
            </td>

            <td>علت خسارت:</td>
            <td><span class="noThing"></span>
                <select id="1khesarat_type_list" name="darkhastBazkharid.khesaratII.ellat"
                        <c:if test="${darkhastBazkharid.khesaratII.ellat!=null}"> disabled="disabled"</c:if>
                >
                <option selected="selected">${darkhastBazkharid.khesaratII.ellat}</option>
                </select>
            </td>
        </tr>
         <tr >
             <td>شرح خسارت:</td>
             <td colspan="4"><span class="noThing"></span>
                 <%--<s:textarea theme="simple" key="darkhastBazkharid.khesaratI.sharhKhesarat" value="${darkhastBazkharid.khesaratI.sharhKhesarat}" id="khe_sharh"--%>
                 <%--label="" cols="40" rows="3"/>--%>
                 <textarea name="darkhastBazkharid.khesaratII.sharhKhesarat" rows="3" cols="102" id="1khe_sharh">${darkhastBazkharid.khesaratII.sharhKhesarat}</textarea>
             </td>
         </tr>
        <tr >
                <%--<td>علت حادثه:</td>--%>
                <%--<td>--%>
                <%--<s:textfield key="khesarat.ellat" label="" theme="simple" cssStyle="width: 280px"/>--%>
                <%--</td>--%>
            <td>واحد اعلام کننده خسارت :</td>
            <td>
                <span class="noThing"></span>
                <input type="hidden" name="darkhastBazkharid.khesaratII.pronouncerOrg.id" id="1pronouncerOrgId"
                        <c:if test="${darkhastBazkharid.khesaratII.pronouncerOrg!=null}">
                            value="${darkhastBazkharid.khesaratII.pronouncerOrg.id}"
                        </c:if>
                        <c:if test="${darkhastBazkharid.khesaratII.pronouncerOrg==null}">
                            <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
                                value="${user.namayandegi.id}"
                            </sec:authorize>
                            <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
                                value="${user.mojtamaSodoor.id}"
                            </sec:authorize>
                        </c:if>
                       readonly="readonly"/>
                <input type="text" name="darkhastBazkharid.khesaratII.pronouncerOrg.name" id="1pronouncerOrgName"
                        <c:if test="${darkhastBazkharid.khesaratII.pronouncerOrg!=null}">
                            value="${darkhastBazkharid.khesaratII.pronouncerOrg.name}"
                        </c:if>
                        <c:if test="${darkhastBazkharid.khesaratII.pronouncerOrg==null}">
                            <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
                                value="${user.namayandegi.name}"
                            </sec:authorize>
                            <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
                                value="${user.mojtamaSodoor.name}"
                            </sec:authorize>
                        </c:if>
                       readonly="readonly"/>

                    <%--<input type="button" id="1btnNamayandeSelector" value="انتخاب" onclick="fillNamayandegi('pronouncerOrgId','pronouncerOrgName','namayandeLaghvShode_yes');" />--%>
            </td>
            <td>نحوه اعلام خسارت:</td>
            <td>
                <span class="noThing"></span>
                <input type="text" name="darkhastBazkharid.khesaratII.nahveElam"
                       <c:if test="${darkhastBazkharid.khesaratII.nahveElam!=null}">value="${darkhastBazkharid.khesaratII.nahveElam}"</c:if>
                       <c:if test="${darkhastBazkharid.khesaratII.nahveElam==null}">value="نامه و حضوری" </c:if>
                        />
            </td>
        </tr>
        <tr >
            <td>استان محل خسارت :</td>
            <td>
                <span class="noThing"></span>
                <input type="text" name="darkhastBazkharid.khesaratII.ostanMahalleHadese.cityName" id="1kh_ostan"
                       class="validate[required]"
                       value="${darkhastBazkharid.khesaratII.ostanMahalleHadese.cityName}" readonly="readonly"/>
                <input type="hidden" name="darkhastBazkharid.khesaratII.ostanMahalleHadese.cityId" id="1kh_ostanId"
                       value="${darkhastBazkharid.khesaratII.ostanMahalleHadese.cityId}"/>
            </td>
            <td>شهر محل خسارت :</td>
            <td>
                <input type="text" name="darkhastBazkharid.khesaratII.shahrMahalleHadese.cityName" id="1kh_shahr"
                       class="validate[required]"
                       value="${darkhastBazkharid.khesaratII.shahrMahalleHadese.cityName}" readonly="readonly"/>
                <input type="hidden" name="darkhastBazkharid.khesaratII.shahrMahalleHadese.cityId" id="1kh_shahrId"
                       value="${darkhastBazkharid.khesaratII.shahrMahalleHadese.cityId}"/>
                 <span class="ui-icon ui-icon-search" id="1btnOstanShahrSelector"
                       onclick="fillShahrOstan('1kh_shahrId','1kh_shahr','1kh_ostanId','1kh_ostan','1kh_vahedSodor')"
                       style="float:left;" title="جستجو"></span>
            </td>
        </tr>
        <tr >
            <td <sec:authorize ifAnyGranted="ROLE_NAMAYANDE"></sec:authorize> >مبلغ خسارت اعلام شده:</td>
            <td><span class="noThing"></span>
                <input type="text" name="darkhastBazkharid.khesaratII.amountElamShode" value="${darkhastBazkharid.khesaratII.amountElamShode}"
                        <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
                            readonly="readonly" disabled="disabled"
                        </sec:authorize>
                             class="digitSeparators"  id="1amount_elam_shode"
                             />
            </td>
            <td>تاریخ وقوع خسارت :</td>
            <td>
                <input type="text" name="darkhastBazkharid.khesaratII.accidentDate" onchange="checkAccidentDate();"  readonly="readonly"
                       value="${darkhastBazkharid.khesaratII.accidentDate}" id="1accident_Date" class="datePkr validate[required[date]]"/>
            </td>
        </tr>
        <tr >
            <td>مبلغ اندوخته روز خسارت:</td>
            <td><span class="noThing"></span>
                <input type="text" id="1kh_andookhte" name="darkhastBazkharid.khesaratII.andukhte"
                       value="${darkhastBazkharid.khesaratI.andukhte}"
                        <sec:authorize
                                ifAnyGranted="ROLE_NAMAYANDE"> disabled="disabled"</sec:authorize>
                       class="digitSeparators" readonly="true"/>
            </td>
            <td>مبلغ هزینه های آتی:</td>
            <td><span class="noThing"></span>
                <input type="text" id="1kh_ati"
                        <sec:authorize ifAnyGranted="ROLE_NAMAYANDE"> disabled="disabled"</sec:authorize>
                       name="darkhastBazkharid.khesaratII.amountAti" value="${darkhastBazkharid.khesaratII.amountAti}" class="digitSeparators" readonly="true"/>
            </td>
        </tr>
        <tr >
            <td>مبلغ خسارت تایید شده: </td>
            <td><span class="noThing"></span>

                <input  type="text" id="1khe_taidShode"
                    <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">disabled="disabled" readonly="readonly"</sec:authorize>
                         name="darkhastBazkharid.khesaratII.amountTaidShode" value="${darkhastBazkharid.khesaratII.amountTaidShode}"
                        <sec:authorize ifNotGranted="ROLE_RAEIS_KHESARAT"><c:if test="${darkhastBazkharid.akhzeMojavezKhesarat && darkhastBazkharid.state.id!=644}">readonly="readonly"</c:if></sec:authorize>
                         class="digitSeparators" />
            </td>
            <td <sec:authorize ifAnyGranted="ROLE_NAMAYANDE"></sec:authorize> >مبلغ خسارت ارفاق مدیریتی:</td>
            <td><span class="noThing"></span>
                <input type="text" id="1kh_erfagh" name="darkhastBazkharid.khesaratII.amountErfagh"
                        <c:if test="${darkhastBazkharid.khesaratII.amountErfagh!=null}">
                            value="${darkhastBazkharid.khesaratII.amountErfagh}"
                        </c:if>
                        <c:if test="${darkhastBazkharid.khesaratII.amountErfagh==null}">
                            value="0"
                        </c:if>
                        <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">disabled="disabled" readonly="readonly"</sec:authorize>
                             class="digitSeparatorsManfi validate[required]"/>
            </td>
        </tr>
        <tr >
            <c:if test="${darkhastBazkharid.khesaratII.khesaratType=='OMR' || darkhastBazkharid.khesaratII.khesaratType=='HADESE'}">
                <td>جمع اقساط پرداخت شده آتي </td>
                <td><span class="noThing"></span>
                    <input type="text" name="darkhastBazkharid.khesaratII.jameAghsatePardakhtiAti" value="${darkhastBazkharid.khesaratII.jameAghsatePardakhtiAti}"
                           disabled="disabled" readonly="readonly"
                                 class="digitSeparators" id="1amount_mazad"/>
                </td>
            </c:if>
            <td>خسارت قابل پرداخت:</td>
            <td><span class="noThing"></span>
                <input type="text" id="1kh_ghabelPardakht" <sec:authorize ifAnyGranted="ROLE_NAMAYANDE"> disabled="disabled"</sec:authorize>
                             name="darkhastBazkharid.khesaratII.amountGhabelPardakht" value="${darkhastBazkharid.khesaratII.amountGhabelPardakht}"
                             class="digitSeparatorsManfi" readonly="true"/>
            </td>
        </tr>
        <tr >
            <td <sec:authorize ifAnyGranted="ROLE_NAMAYANDE"></sec:authorize> >نظریه کارشناس:</td>
            <td colspan="4"><span class="noThing"></span>
                    <%--<s:textarea key="darkhastBazkharid.khesaratI.nazarKarshenas" cols="40" rows="3" theme="simple" label=""--%>
                    <%--id="kh_nazarie"/>--%>
                <textarea name="darkhastBazkharid.khesaratII.nazarKarshenas" rows="3" cols="102"
                          <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">disabled="disabled" readonly="readonly"</sec:authorize>
                          id="1khe_sharh">${darkhastBazkharid.khesaratII.nazarKarshenas}</textarea>
            </td>
        </tr>

    </table>
    <br/><br/>

    <s:if test="%{havaleHa=='null'}">
    <input type="hidden" name="khesaratId" value="${khesarat.id}"/>
    <input type="hidden" name="updateOrSave" value="update"/>
    </s:if>
        <%--<s:submit action="sabtKhesarat" value="ثبت"/>--%>
        <%--<input type="button" onclick="sbmt();" value="ثبت"/>--%>


    <c:if test="${darkhastBazkharid.state.id==642||darkhastBazkharid.state.id==648||darkhastBazkharid.state.id==644||darkhastBazkharid.state.id==645||darkhastBazkharid.state.id==650||darkhastBazkharid.state.id==600||darkhastBazkharid.state.id==653||darkhastBazkharid.state.id==654}">
        <input type="button" onclick="calculate1();" class="noAnyDisable" value="محاسبه"/>
    </c:if>

</div>
</div>
<c:if test="${darkhastBazkharid.state.id==null}">    <br/>
    <input type="button" onclick="goMovagaht()"
           value="ثبت درخواست موقت"/>
    <br/>
    <script type="text/javascript">
        function goMovagaht()
        {
            if(!($('#kh_naghs').is(':checked') ||$('#kh_hadese').is(':checked') || $('#kh_omr').is(':checked') || $('#kh_amraz').is(':checked')|| $('#kh_moafiat').is(':checked')))
            {
                alertMessage("نوع مورد خسارت را انتخاب کنید");
            }

            else if($('#khs_cnt').val() == '2' && !($('#1kh_naghs').is(':checked') || $('#1kh_hadese').is(':checked') || $('#1kh_omr').is(':checked') || $('#1kh_amraz').is(':checked') || $('#1kh_moafiat').is(':checked')))
            {
                alertMessage("نوع مورد خسارت دوم را انتخاب کنید");
            }
            else
            {
                if($('#kh_amraz').is(':checked') && ($('#amount_elam_shode').val()==null||$('#amount_elam_shode').val()==''))
                {
                    alertMessage("مبلغ خسارت امراض اعلام شده را وارد نمایید.");
                }
                else
                {
                    $.ajax({
                    type: "POST",
                    async: false,
                    data: $("#submit_khesarat").serialize(),
                    url: "/checkAccidentDateAjaxly",
                    success: function (response)
                    {
                        if(response=='true')
                        {
                            $('#submit_khesarat').submit();
                        }
                        else if(response=='great')
                        {
                            alertMessage("تاریخ وقوع خسارت نباید بعد از تاریخ ثبت درخواست خسارت باشد.");
                        }
                        else if(response=='less')
                        {
                            alertMessage("تاریخ وقوع خسارت نباید قبل از تاریخ شروع بیمه نامه باشد.");
                        }
                        else
                        {
                            alertMessage("تاریخ وقوع خسارت را وارد کنید.")
                        }
                    }
                    });
                }
            }
        }
    </script>
    <p>
        <img src="/img/alert.png" height="20px" width="20px"/>
        <b>
        درصورتيكه مدارك شما ظرف مدت 30 روز ازشروع ثبت خسارت به دست اداره خسارت نرسد درخواست شما منقضي مي گردد
        </b>
    </p>
</c:if>
<input type="hidden" id="tashkilparvande" name="tashkilPrvandeLog" value=""/>
<c:if test="${darkhastBazkharid.state.id==642}">
    <br/><br/><input type="button" onclick="goTransition('660');"
           value="تشکیل پرونده"/>
</c:if>
<c:if test="${darkhastBazkharid.state.id==653}">
    <br/><br/><input type="button" onclick="taeedBarresi();"
           value="تایید بررسی پرونده خسارت"/>
</c:if>
<c:if test="${darkhastBazkharid.state.id==648}">
    <br/><br/><input type="button" onclick="goTransition('650')"
           value="تشکیل پرونده"/>
</c:if>
<c:if test="${darkhastBazkharid.state.id==645}">
    <br/><br/><input type="button" onclick="goTransition('670')"
           value="تاييد و ثبت خسارت"/>
</c:if>
<c:if test="${darkhastBazkharid.state.id==650}">
    <br/><br/><input type="button" onclick="goTransition('618')"
           value="تشکیل پرونده"/>
</c:if>
<c:if test="${darkhastBazkharid.state.id==600}">
    <br/><br/>
<c:if test="${(darkhastBazkharid.khesaratII==null)&&
              (
                (darkhastBazkharid.khesaratI.khesaratType=='NAGHSOZV' && darkhastBazkharid.bimename.haveKhesaratNaghsOzv)||
                (darkhastBazkharid.khesaratI.khesaratType=='AMRAZ' && darkhastBazkharid.bimename.haveKhesaratAmraz)
              )}">
    <input type="button" onclick="goTransition('700')" value="منتظر تایید نهایی"/>
</c:if>
<c:if test="${(darkhastBazkharid.khesaratII!=null)||
              (
                (darkhastBazkharid.khesaratI.khesaratType!='NAGHSOZV' || !darkhastBazkharid.bimename.haveKhesaratNaghsOzv)&&
                (darkhastBazkharid.khesaratI.khesaratType!='AMRAZ' || !darkhastBazkharid.bimename.haveKhesaratAmraz)
              )}">
    <input type="button" onclick="goTransition('613')" value="الحاقیه جدید"/>
</c:if>
</c:if>
</form>

<%--<table id="madarekMoredNiaz" style="border-color:black; " align="center">--%>
    <%--<tr style="border:black; background-color:#eaf2ff;">--%>
        <%--<th style="border-bottom-color:black;">--%>
            <%--درصورتيكه مدارك شما ظرف مدت 30 روز ازشروع ثبت خسارت به دست اداره خسارت نرسد درخواست شما منقضي مي گردد--%>
        <%--</th>--%>
    <%--</tr>--%>
<%--</table>--%>

<script type="text/javascript">

        function checkAccidentDate()
        {
            if($('#accident_Date').val()!=null && $('#accident_Date').val()!='' && ($('#1accident_Date').val() != '')&&$('#1accident_Date').val() != null)
            {
                if(isDateGreaterThan($('#accident_Date').val(), $('#1accident_Date').val()))
                {
                    alertMessage("تاریخ خسارت فوت باید بزرگتر مساوی تاریخ وقوع  خسارت اول باشد.");
//                    $('#accident_Date').val('')
                    $('#1accident_Date').val('')
                }
            }
        }


        function goTransition(arg)
        {
            if($('#khe_taidShode').val()==null || $('#khe_taidShode').val() == '')
            {
                alertMessage("مبلغ تایید شده الزامی است.");
            }
            else
            {
                var haveMojavez = false;
                <c:if test="${darkhastBazkharid.akhzeMojavezKhesarat}">
                    haveMojavez = true;
                </c:if>
                if($('#count_khesarat').val()==2)
                {
                    if ($('#1khe_taidShode').val() == null || $('#1khe_taidShode').val() == '')
                    {
                        alertMessage("مبلغ تایید شده خسارت دوم الزامی است.");
                        $('#kh_2').show();
                        $('#tabs_kh_two').show();
                        $("#tabs_khesarat").tabs({selected: 1});
                    }
                    else
                    {

                        if (arg == '613' && !haveMojavez && parseInt($('#1kh_ghabelPardakht').val().replace(new RegExp(",", "gm"), "")) != parseInt($('#1khe_taidShode').val().replace(new RegExp(",", "gm"), "")))
                        {
                            alertMessage('مبلغ خسارت قابل پرداخت با مبلغ خسارت تاييد شده يكي نمي باشد لطفا جهت اخذ مجوز ،‌وضعيت " نياز به اخذ مجوز "‌را ثبت نمائيد');
                            $('#kh_2').show();
                            $('#tabs_kh_two').show();
                            $("#tabs_khesarat").tabs({selected: 1});
                        }
                        else
                        {
                            //            $('#dakhastTransitionSelector').val(arg);
                            $("#submit_khesarat").attr("action", "makeDarkhastTransition.action");
                            //            $("#transition_id").remove();
                            //            $("#submit_khesarat").append('<input type="hidden" id="transition_id" name="transitionId" value="' + arg + '" />');
                            $('#transition_id').val(arg);
                            //            $("#dakhastTransitionSelector").val(arg);

                            openDialogueForDarkhast('tashkilparvande', 'submit_khesarat');
                        }
                    }
                }
                else
                {
                    if(arg == '613' && !haveMojavez && ($('#kh_hadese').is(':checked') || $('#kh_omr').is(':checked')) && parseInt($('#kh_ghabelPardakht').val().replace(new RegExp(",", "gm"), ""))!= parseInt($('#khe_taidShode').val().replace(new RegExp(",", "gm"), "")))
                    {
                        alertMessage('مبلغ خسارت قابل پرداخت با مبلغ خسارت تاييد شده يكي نمي باشد لطفا جهت اخذ مجوز ،‌وضعيت " نياز به اخذ مجوز "‌را ثبت نمائيد');
                    }
                    else
                    {

            //            $('#dakhastTransitionSelector').val(arg);
                        $("#submit_khesarat").attr("action", "makeDarkhastTransition.action");
            //            $("#transition_id").remove();
            //            $("#submit_khesarat").append('<input type="hidden" id="transition_id" name="transitionId" value="' + arg + '" />');
                        $('#transition_id').val(arg);
            //            $("#dakhastTransitionSelector").val(arg);

                        openDialogueForDarkhast('tashkilparvande', 'submit_khesarat');
                    }
                }
            }
        }
        function taeedBarresi()
        {
            var haveMojavez = false;
            <c:if test="${darkhastBazkharid.akhzeMojavezKhesarat}">
                haveMojavez = true;
            </c:if>
            if ( !haveMojavez && ($('#kh_hadese').is(':checked') || $('#kh_omr').is(':checked')) && parseInt($('#kh_ghabelPardakht').val().replace(new RegExp(",", "gm"), "")) != parseInt($('#khe_taidShode').val().replace(new RegExp(",", "gm"), "")))
            {

                $("#submit_khesarat").attr("action", "makeDarkhastTransition.action");
                $('#transition_id').val(623);

                openDialogueForDarkhast('tashkilparvande', 'submit_khesarat');
                return ;
            }
            else
            {
                if ($('#count_khesarat').val() == 2)
                {
                    if (!haveMojavez && ($('#1kh_hadese').is(':checked') || $('#1kh_omr').is(':checked')) && parseInt($('#1kh_ghabelPardakht').val().replace(new RegExp(",", "gm"), "")) != parseInt($('#1khe_taidShode').val().replace(new RegExp(",", "gm"), "")))
                    {
                        $("#submit_khesarat").attr("action", "makeDarkhastTransition.action");
                        $('#transition_id').val(623);
                        openDialogueForDarkhast('tashkilparvande', 'submit_khesarat');
                        return;
                    }

                }
                else
                {
                    $("#submit_khesarat").attr("action", "makeDarkhastTransition.action");
                    $('#transition_id').val(615);
                    openDialogueForDarkhast('tashkilparvande', 'submit_khesarat');
                    return;
                }
            }
        }
        function sbmt()
        {
            var tORf = "submit";
    //        var amountElami= $("#amount_elam_shode").val().replace(new Regex(",", "gm"), "");
    //        var amountTaeided= $("#khe_taidShode").val().replace(new Regex(",", "gm"), "");
            var amount = $("#kh_ghabelPardakht").val().replace(new RegExp(",", "gm"), "");

            if ($('#kh_amraz').is(':checked'))
            {
                if (parseInt(amount) > parseInt($('#chk_amraz').html().replace(new RegExp(",", "gm"), "")))
                    tORf = "<p style='color: #ee0101'>" + "مبلغ خسارت بزرگتر از سرمایه خسارت این بیمه نامه می باشد." + "</b>";
            }
            if (tORf == "submit")
            {
                $("#kh_form").submit();
            }

            else alertMessage(tORf);
        }

        function viewNan()
        {
//            if ($("#amount_elam_shode").val() == "NaN")
//            {
//                              alert('viewNan');
//                $("#amount_elam_shode").val("");
//            }
        }
        $(document).ready(function ()
        {
            <c:if test="${darkhastBazkharid.state.id==653||darkhastBazkharid.state.id==600||darkhastBazkharid.state.id==642||darkhastBazkharid.state.id==648||darkhastBazkharid.state.id==650||darkhastBazkharid.state.id==644||darkhastBazkharid.state.id==645}">
                $('#amount_elam_shode').attr('readonly','readonly');
                $('#1amount_elam_shode').attr('readonly','readonly');
            </c:if>
            <c:if test="${darkhastBazkharid.state.id==619 || darkhastBazkharid.state.id==652||darkhastBazkharid.state.id==646||darkhastBazkharid.state.id==657||darkhastBazkharid.state.id==658||darkhastBazkharid.state.id==654}">
                disablePishnehadTabs("content_36");
            </c:if>
            <c:if test="${darkhastBazkharid.state.id==640||darkhastBazkharid.state.id==649}">
                <c:if test="${user.id!=darkhastBazkharid.creator.id}">
                    disablePishnehadTabs("content_36");
                </c:if>
                <c:if test="${user.id==darkhastBazkharid.creator.id}">

//                    khe_taidShode
//                    kh_erfagh
                </c:if>
            </c:if>
            <c:if test="${darkhastBazkharid.khesaratI.khesaratType!='OMR' || darkhastBazkharid.khesaratI.khesaratType!='HADESE'}">
                $('#kh_ati').attr('disabled','disabled');
            </c:if>
            <c:if test="${darkhastBazkharid.state!=null}">
                $('#kh_omr').attr('disabled', 'disabled');
                $('#kh_naghs').attr('disabled', 'disabled');
                $('#kh_hadese').attr('disabled', 'disabled');
                $('#kh_amraz').attr('disabled', 'disabled');
                $('#kh_moafiat').attr('disabled', 'disabled');

                $('#1kh_omr').attr('disabled', 'disabled');
                $('#1kh_naghs').attr('disabled', 'disabled');
                $('#1kh_hadese').attr('disabled', 'disabled');
                $('#1kh_amraz').attr('disabled', 'disabled');
                $('#1kh_moafiat').attr('disabled', 'disabled');
            </c:if>
            <c:if test="${darkhastBazkharid.state!=null && darkhastBazkharid.khesaratII!=null}">
                $('#kh_2').show();
                $('#tabs_kh_two').show();

            </c:if>
            <c:if test="${darkhastBazkharid.state.id==652 && darkhastBazkharid.bimename.vameTasvieNashode}">
                alertMessage("اين بيمه نامه داراي وام تسويه نشده مي باشد ");
            </c:if>

            <c:if test="${darkhastBazkharid.state!=null && darkhastBazkharid.khesaratI.khesaratType=='AMRAZ'&& darkhastBazkharid.khesaratI.timeWaitingAmraz}">
                alertMessage("پوشش امراض خسارت داراي سه ماه دوره انتظار مي باشد  ");
            </c:if>
//            viewNan();
            <c:if test="${darkhastBazkharid.state.id==600 && darkhastBazkharid.shomareParvande!=null && generatedShomareParvande}">
                   alertMessage("شماره پرونده : ${darkhastBazkharid.shomareParvande}");
            </c:if>
        });
        function calcAmountElamShodeForNaghsOzv()
        {
            var sarmaye = $('#chk_naghs').html().replace(new RegExp(",", "gm"), "");
            var percent = $("#darsad_naghs_ozv").val();
            var percentOfSarmaye = parseInt(sarmaye) * parseInt(percent) / 100;
            $("#amount_elam_shode").val(jQuery.global.format(percentOfSarmaye));
        }
        function maxHund()
        {
            var percent = $("#darsad_naghs_ozv").val();
            if (percent > 100)
            {
                $("#darsad_naghs_ozv").val(100);
            }
        }

        function calcMazad()
        {
            var elamShodeAmount = 0;
            if ($("#amount_elam_shode").val() != null && $("#amount_elam_shode").val().replace(new RegExp(",", "gm"), "") > 0)
            {
                elamShodeAmount = $("#amount_elam_shode").val().replace(new RegExp(",", "gm"), "");
            }
            var taidShodeAmount = 0;
            if ($("#khe_taidShode").val() != null && $("#khe_taidShode").val().replace(new RegExp(",", "gm"), "") > 0)
            {
                taidShodeAmount = $("#khe_taidShode").val().replace(new RegExp(",", "gm"), "");
            }
            var mazad = taidShodeAmount - elamShodeAmount;
            $("#amount_mazad").val(jQuery.global.format(mazad));
        }

        function calcKhesaratGhabelPardakht()
        {
            andukhte = $('#kh_andookhte').val().replace(new RegExp(",", "gm"), "");
            hazineha = $('#kh_ati').val().replace(new RegExp(",", "gm"), "");
            elamShode=$('#amount_elam_shode').val() != null ? $('#amount_elam_shode').val().replace(new RegExp(",", "gm"), "") : 0;
            taeedshode = $('#khe_taidShode').val().replace(new RegExp(",", "gm"), "");
            erfagh = $('#kh_erfagh').val().replace(new RegExp(",", "gm"), "");
            pardakht = 0;

            if ($('#kh_omr').is(':checked'))
            {
                pardakhtAghsatAti = $("#amount_mazad").val().replace(new RegExp(",", "gm"), "");
                $('#amount_elam_shode').val($('#chk_omr').html())
                sarmayeFowt = $('#amount_elam_shode').val()!=null?$('#amount_elam_shode').val().replace(new RegExp(",", "gm"), ""):0;
                pardakht = parseInt(erfagh) + parseInt(andukhte) - parseInt(hazineha) + parseInt(sarmayeFowt) + parseInt(pardakhtAghsatAti);
            }
            if ($('#kh_hadese').is(':checked'))
            {
                pardakhtAghsatAti = $("#amount_mazad").val().replace(new RegExp(",", "gm"), "");
                var amountElamShode = $('#amount_elam_shode').val().replace(new RegExp(",", "gm"), ""); //majmoo sarmaye fowt va sarmaye hadese
                pardakht = parseInt(erfagh) + parseInt(andukhte) - parseInt(hazineha) + parseInt(amountElamShode) + parseInt(pardakhtAghsatAti);
            }
            if ($('#kh_amraz').is(':checked'))
            {
                sarma_amraz = $('#chk_amraz').html().replace(new RegExp(",", "gm"), "");
                if(parseInt(elamShode) > parseInt(sarma_amraz))
                    pardakht = parseInt(sarma_amraz) + parseInt(erfagh);
                else
                    pardakht = parseInt(elamShode) + parseInt(erfagh);
            }
            if ($('#kh_naghs').is(':checked'))
            {
                pardakht = parseInt(elamShode) + parseInt(erfagh);
            }
            if ($('#kh_moafiat').is(':checked'))
            {
                pardakht = parseInt(elamShode) + parseInt(erfagh);
            }
            $('#kh_ghabelPardakht').val(pardakht);
            var v = jQuery.global.format(jQuery.global.parseInt($('#kh_ghabelPardakht').val()), "n0");
            if (v.indexOf("NaN") != -1)
            {
                v = "";
            }
            $('#kh_ghabelPardakht').val(v);
        }

        function calculate()
        {
            if ($('#accident_Date').val() == '' || $('#accident_Date').val() == null)
            {
                alertMessage("لطفاً تاریخ وقوع خسارت را وارد کنید.");
            }
            else
            {
                $("#khesarat_num").remove();
                $("#submit_khesarat").append('<input type="hidden" id="khesarat_num" name="khesaratNum" value="I" />');
                $.post('/calculateKhesaratAjaxly.action?darkhastBazkharid' + $('#submit_khesarat').serialize(), function (msg)
                {
                    if ($(msg).filter("#ajaxcalc_successOrFaild").val() == "success")
                    {
                        $('#kh_andookhte').val($(msg).filter('#ajaxcalc_andukhte').val());

                        if (parseInt($('#kh_andookhte').val().replace(new RegExp(",", "gm"), "")) <= 0)
                        {
                            window.alertMessage("اندوخته بیمه نامه کوچکتر مساوی با صفر است.");
                        }

                        $('#kh_ati').val($(msg).filter('#ajaxcalc_hazineha').val());
                        $('#chk_omr').html(' ' + $(msg).filter('#ajaxcalc_sarmayeFot').val() + '');
                        $('#chk_naghs').html(' ' + $(msg).filter('#ajaxcalc_sarmayeNaghsOzv').val() + '');
                        $('#chk_hadese').html(' ' + $(msg).filter('#ajaxcalc_sarmayeHadese').val() + '');
                        $('#chk_amraz').html(' ' + $(msg).filter('#ajaxcalc_sarmayeAmraz').val() + '');
                        $('#amount_elam_shode').val($(msg).filter('#ajaxcalc_kh_amount_elami').val());
                        $("#amount_mazad").val($(msg).filter('#ajaxcalc_kh_jame_aghsate_pardakhti_ati').val());
                        calcKhesaratGhabelPardakht();
                    }
                    else //if(msg.filter("#ajaxcalc_successOrFaild").val()=="faild")
                    {
                        alertMessage(msg);
                        $('#kh_ghabelPardakht').val('');
                    }
                });
            }
        }

        function updateCheckBoxes(combo)
        {
            $('#amount_elam_shode').removeClass("validate[required]");
            $('#count_khesarat').attr('disabled','disabled');
            $('#khs_cnt').val(1);
            <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
                $('#amount_elam_shode').attr('readonly', 'readonly');
                $('#amount_elam_shode').attr('disabled', 'disabled');
            </sec:authorize>
            viewNan();
            $("#naghs_darsad_lbl").hide();
            $("#btn_fill_zero").hide();
            $("#darsad_naghs_ozv").hide();
//            $("#amount_elam_shode").attr("readonly", false);
            var typesArrayAmraz = ["انواع سرطان", "شيمي درماني ", "سكته قلبي", "سكته مغزي ", "پيوند اعضاي اصلي بدن ", "جراحي قلب باز ", "تعويض دريچه قلب "];
            var typesArrayNaghs = [" قرارگرفتن / گيركردن اعضا زير دستگاه ( بريدگي و قطع اعضاء) ", "سقوط از ارتفاع ( خردشدگي و شكستگي اعضاء) ", "سقوط در سطح همتراز ( شكستگي اعضاء) ", "تصادف ", "آسيب حين بازي و انواع ورزش و مسابقات ( فوتبال –ورزشهاي رزمي – تير و كمان و ...", "اصابت جسم سخت ", "برق گرفتگي ", "آتش سوزي ( سوختگي) ", " تماس با مواد شيميائي ", "مسموميت", "نقص عضو در اثر عوارض دارويي", "نقص عضو ناشي از عوامل غير قابل پيش بيني"];
            var typesArrayFot = ["سكته ", " سرطان ", "سوختگي", "قتل ", "خودكشي", "برق گرفتگي", " سقوط از ارتفاع ", "سقوط در سطح همتراز", "تصادف ", "غرق شدگي", "مصرف مواد مخدر و الكل ", " فوت ناشي از عوارض پس از عمل جراحي ", "  فوت ناشي از عوامل غير قابل پيش بيني", "مسموميت", "فوت ناشي از بيماري"];
            var typesArrayHadese = ["سوختگي", "قتل ", "برق گرفتگي", " سقوط از ارتفاع ", "سقوط در سطح همتراز", "تصادف ", "غرق شدگي", "اصابت جسم سخت", "حوادث ناشي از عوامل غير قابل پيش بيني"];
            var typesArrayMoafiat = ["-"];
            var typesArrayDefault = ["نوع خسارت را انتخاب کنيد"];
            var selectedArray;
            var htmlString = "";
            if ($('#kh_naghs').is(':checked') && combo.id=='kh_naghs')
            {
                $('#khsrt_type').val('NAGHSOZV');
                selectedArray = typesArrayNaghs;
                $("#amount_elam_shode").attr("readonly", true);
                $("#naghs_darsad_lbl").show();
                $("#darsad_naghs_ozv").show();

//                $("#submit_khesarat").remove('#khesarat_num');
//                $("#submit_khesarat").append('<input type="hidden" id="khesarat_num" name="khesaratNum" value="I" />')
//
//                $.post('/calculateKhesaratAjaxly.action?' + $('#submit_khesarat').serialize(), function (msg)
//                {
//                    $('#chk_naghs').html(' ' + $(msg).filter('#ajaxcalc_sarmayeNaghsOzv').val() + '');
//                });
                $("#kh_ati").val('');
                $("#kh_ati").attr('readonly', 'readonly');
                $('#kh_amraz').removeAttr('checked');
                $('#kh_omr').removeAttr('checked');
                $('#kh_hadese').removeAttr('checked');
                $('#kh_moafiat').removeAttr('checked');

                $('#count_khesarat').removeAttr('disabled')    ;
                $('#1kh_moafiat').attr('disabled','disabled')   ;
                $('#1kh_amraz').attr('disabled','disabled')      ;
                $('#1kh_naghs').attr('disabled','disabled')       ;
            }
            else if ($('#kh_amraz').is(':checked') && combo.id == 'kh_amraz')
            {
                $("#amount_elam_shode").addClass("validate[required]");
                $('#khsrt_type').val('AMRAZ');
                selectedArray = typesArrayAmraz;
                $("#kh_ati").val('');
                $("#kh_ati").attr('readonly', 'readonly');
                $('#kh_naghs').removeAttr('checked');
                $('#kh_omr').removeAttr('checked');
                $('#kh_hadese').removeAttr('checked');
                $('#kh_moafiat').removeAttr('checked');

                $('#count_khesarat').removeAttr('disabled');
                $('#amount_elam_shode').removeAttr('readonly');
                $('#amount_elam_shode').removeAttr('disabled');
                $('#1kh_moafiat').attr('disabled', 'disabled');
                $('#1kh_amraz').attr('disabled', 'disabled');
                $('#1kh_naghs').attr('disabled', 'disabled');
            }
            else if ($('#kh_omr').is(':checked') && combo.id == 'kh_omr')
            {
                $('#khsrt_type').val('OMR');
                selectedArray = typesArrayFot;
                $("#btn_fill_zero").show();
                $("#amount_elam_shode").attr("readonly", true);
//                $("#submit_khesarat").remove('#khesarat_num');
//                $("#submit_khesarat").append('<input type="hidden" id="khesarat_num" name="khesaratNum" value="I" />')
<%----%>
//                $.post('/calculateKhesaratAjaxly.action?' + $('#submit_khesarat').serialize(), function (msg)
//                {
//                    $('#amount_elam_shode').val($(msg).filter('#ajaxcalc_sarmayeFot').val());
//                });
                $("#kh_ati").val('');
//                $("#kh_ati").removeAttr('readonly');
                $('#kh_naghs').removeAttr('checked');
                $('#kh_amraz').removeAttr('checked');
                $('#kh_hadese').removeAttr('checked');
                $('#kh_moafiat').removeAttr('checked');
            }
            else if ($('#kh_hadese').is(':checked') && combo.id == 'kh_hadese')
            {
                $('#khsrt_type').val('HADESE');
                selectedArray = typesArrayHadese;
                $("#btn_fill_zero").show();
                $("#amount_elam_shode").attr("readonly", true);
//                $("#submit_khesarat").remove('#khesarat_num');
//                $("#submit_khesarat").append('<input type="hidden" id="khesarat_num" name="khesaratNum" value="I" />')
<%----%>
//                $.post('/calculateKhesaratAjaxly.action?' + $('#submit_khesarat').serialize(), function (msg)
//                {
//                    var sarmaHades = $(msg).filter('#ajaxcalc_sarmayeHadese').val().replace(new RegExp(",", "gm"), "");
//                    var sarmaFowt = $(msg).filter('#ajaxcalc_sarmayeFot').val().replace(new RegExp(",", "gm"), "");
//                    var majmoFotHadese = parseInt(sarmaHades) + parseInt(sarmaFowt);
//                    $('#amount_elam_shode').val(jQuery.global.format(majmoFotHadese));
//                });
                $("#kh_ati").val('');
//                $("#kh_ati").removeAttr('readonly');
                $('#kh_naghs').removeAttr('checked');
                $('#kh_amraz').removeAttr('checked');
                $('#kh_omr').removeAttr('checked');
                $('#kh_moafiat').removeAttr('checked');
            }
            else if ($('#kh_moafiat').is(':checked') && combo.id == 'kh_moafiat')
            {
                $("#amount_elam_shode").attr("readonly", true);
                $('#khsrt_type').val('MOAFIAT');
                selectedArray = typesArrayMoafiat;
                $("#kh_ati").val('');
                $("#kh_ati").attr('readonly', 'readonly');
                $('#kh_naghs').removeAttr('checked');
                $('#kh_amraz').removeAttr('checked');
                $('#kh_omr').removeAttr('checked');
                $('#kh_hadese').removeAttr('checked');

                $('#count_khesarat').removeAttr('disabled');
                $('#1kh_moafiat').attr('disabled', 'disabled');
                $('#1kh_amraz').attr('disabled', 'disabled');
                $('#1kh_naghs').attr('disabled', 'disabled');
            }
            else
            {
                selectedArray = typesArrayDefault;
                $("#kh_ati").val('');
                $("#kh_ati").attr('readonly', 'readonly');
            }
            $.each(selectedArray, function (val, text)
            {
                htmlString += "<option value='" + text + "'>" + text + "</option>";
            });
            $('#khesarat_type_list').html(htmlString);
        }

         //----------------------------------------------------------------------------
        function sbmt1()
        {
            var tORf = "submit";
            //        var amountElami= $("#amount_elam_shode").val().replace(new Regex(",", "gm"), "");
            //        var amountTaeided= $("#khe_taidShode").val().replace(new Regex(",", "gm"), "");
            var amount = $("#1kh_ghabelPardakht").val().replace(new RegExp(",", "gm"), "");

            if ($('#1kh_amraz').is(':checked'))
            {
                if (parseInt(amount) > parseInt($('#1chk_amraz').html().replace(new RegExp(",", "gm"), "")))
                    tORf = "<p style='color: #ee0101'>" + "مبلغ خسارت بزرگتر از سرمایه خسارت این بیمه نامه می باشد." + "</b>";
            }
            if (tORf == "submit")
            {
                $("#1kh_form").submit();
            }

            else alertMessage(tORf);
        }

        function viewNan1()
        {
//            if ($("#1amount_elam_shode").val() == "NaN")
//            {
//
//                $("#1amount_elam_shode").val("");
//            }
        }
        $(document).ready(function ()
        {
//            viewNan1();
        });
        function calcAmountElamShodeForNaghsOzv1()
        {
            var sarmaye = $('#1chk_naghs').html().replace(new RegExp(",", "gm"), "");
            var percent = $("#1darsad_naghs_ozv").val();
            var percentOfSarmaye = parseInt(sarmaye) * parseInt(percent) / 100;
            $("#1amount_elam_shode").val(jQuery.global.format(percentOfSarmaye));
        }
        function maxHund1()
        {
            var percent = $("#1darsad_naghs_ozv").val();
            if (percent > 100)
            {
                $("#1darsad_naghs_ozv").val(100);
            }
        }

        function calcMazad1()
        {
            var elamShodeAmount = 0;
            if ($("#1amount_elam_shode").val() != null && $("#1amount_elam_shode").val().replace(new RegExp(",", "gm"), "") > 0)
            {
                elamShodeAmount = $("#1amount_elam_shode").val().replace(new RegExp(",", "gm"), "");
            }
            var taidShodeAmount = 0;
            if ($("#1khe_taidShode").val() != null && $("#1khe_taidShode").val().replace(new RegExp(",", "gm"), "") > 0)
            {
                taidShodeAmount = $("#1khe_taidShode").val().replace(new RegExp(",", "gm"), "");
            }
            var mazad = taidShodeAmount - elamShodeAmount;
            $("#1amount_mazad").val(jQuery.global.format(mazad));
        }

        function calculateWithZeroVal1()
        {
            if ($('#1kh_omr').is(':checked') || $('#1kh_hadese').is(':checked'))
            {
                $('#1amount_elam_shode').val(0);
                $('#1kh_andookhte').val(0);
                $('#1kh_ati').val(0);

                $('#1khe_taidShode').val(0);
                $('#1kh_erfagh').val(0);
                $('#1kh_ghabelPardakht').val(0)
            }
        }

        function calcKhesaratGhabelPardakht1()
        {
            andukhte = $('#1kh_andookhte').val().replace(new RegExp(",", "gm"), "");
            hazineha = $('#1kh_ati').val().replace(new RegExp(",", "gm"), "");
            elamShode = $('#1amount_elam_shode').val() != null ? $('#1amount_elam_shode').val().replace(new RegExp(",", "gm"), "") : 0;
            taeedshode = $('#1khe_taidShode').val().replace(new RegExp(",", "gm"), "");
            erfagh = $('#1kh_erfagh').val().replace(new RegExp(",", "gm"), "");
            pardakht = 0;
            if ($('#1kh_omr').is(':checked'))
            {
                pardakhtAghsatAti = $("#1amount_mazad").val().replace(new RegExp(",", "gm"), "");
                sarmayeFowt = $('#1amount_elam_shode').val() != null ? $('#1amount_elam_shode').val().replace(new RegExp(",", "gm"), "") : 0;
                pardakht = parseInt(erfagh) + parseInt(andukhte) - parseInt(hazineha) + parseInt(sarmayeFowt)+ parseInt(pardakhtAghsatAti);
            }
            if ($('#1kh_hadese').is(':checked'))
            {

                pardakhtAghsatAti = $("#1amount_mazad").val().replace(new RegExp(",", "gm"), "");
                var amountElamShode = $('#1amount_elam_shode').val().replace(new RegExp(",", "gm"), ""); //majmoo sarmaye fowt va sarmaye hadese
                pardakht = parseInt(erfagh) + parseInt(andukhte) - parseInt(hazineha) + parseInt(amountElamShode)+parseInt(pardakhtAghsatAti);
            }
            if ($('#1kh_amraz').is(':checked'))
            {
                pardakht = parseInt(elamShode) + parseInt(erfagh);
            }
            if ($('#1kh_naghs').is(':checked'))
            {
                pardakht = parseInt(elamShode) + parseInt(erfagh);
            }
            if ($('#1kh_moafiat').is(':checked'))
            {
                pardakht = parseInt(elamShode) + parseInt(erfagh);
            }
            $('#1kh_ghabelPardakht').val(pardakht);
            var v = jQuery.global.format(jQuery.global.parseInt($('#1kh_ghabelPardakht').val()), "n0");
            if (v.indexOf("NaN") != -1)
            {
                v = "";
            }
            $('#1kh_ghabelPardakht').val(v);
        }

        function calculate1()
        {
            if ($('#1accident_Date').val() == '' || $('#1accident_Date').val() == null)
            {
                alertMessage("لطفاً تاریخ وقوع خسارت را وارد کنید.");
            }
            else
            {
                $("#khesarat_num").remove();
                $("#submit_khesarat").append('<input type="hidden" id="khesarat_num" name="khesaratNum" value="II" />') ;
                $.post('/calculateKhesaratAjaxly.action?' + $('#submit_khesarat').serialize(), function (msg)
                {
                    if ($(msg).filter("#ajaxcalc_successOrFaild").val() == "success")
                    {
                        $('#1kh_andookhte').val($(msg).filter('#ajaxcalc_andukhte').val());

                        if (parseInt($('#1kh_andookhte').val().replace(new RegExp(",", "gm"), "")) <= 0)
                        {
                            window.alertMessage("اندوخته بیمه نامه کوچکتر مساوی با صفر است.");
                        }

                            $('#1kh_ati').val($(msg).filter('#ajaxcalc_hazineha').val());
                            $('#1chk_omr').html(' ' + $(msg).filter('#ajaxcalc_sarmayeFot').val() + '');
                            $('#1chk_naghs').html(' ' + $(msg).filter('#ajaxcalc_sarmayeNaghsOzv').val() + '');
                            $('#1chk_hadese').html(' ' + $(msg).filter('#ajaxcalc_sarmayeHadese').val() + '');
                            $('#1chk_amraz').html(' ' + $(msg).filter('#ajaxcalc_sarmayeAmraz').val() + '');
                            $('#1amount_elam_shode').val($(msg).filter('#ajaxcalc_kh_amount_elami').val());
                            $("#1amount_mazad").val($(msg).filter('#ajaxcalc_kh_jame_aghsate_pardakhti_ati').val());
                            calcKhesaratGhabelPardakht1();

                    }
                    else //if(msg.filter("#ajaxcalc_successOrFaild").val()=="faild")
                    {
                        alertMessage(msg);
                        $('#1kh_ghabelPardakht').val('');
                    }
                });
            }
        }

        function updateCheckBoxes1(combo)
        {
            $('#1amount_elam_shode').removeClass("validate[required]");
            viewNan1();
            $("#1naghs_darsad_lbl").hide();
            $("#1btn_fill_zero").hide();
            $("#1darsad_naghs_ozv").hide();

            <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
                $('#1amount_elam_shode').attr('readonly', 'readonly');
                $('#1amount_elam_shode').attr('disabled', 'disabled');

            </sec:authorize>

//            $("#1amount_elam_shode").attr("readonly", false);
            var typesArrayAmraz = ["انواع سرطان", "شيمي درماني ", "سكته قلبي", "سكته مغزي ", "پيوند اعضاي اصلي بدن ", "جراحي قلب باز ", "تعويض دريچه قلب "];
            var typesArrayNaghs = [" قرارگرفتن / گيركردن اعضا زير دستگاه ( بريدگي و قطع اعضاء) ", "سقوط از ارتفاع ( خردشدگي و شكستگي اعضاء) ", "سقوط در سطح همتراز ( شكستگي اعضاء) ", "تصادف ", "آسيب حين بازي و انواع ورزش و مسابقات ( فوتبال –ورزشهاي رزمي – تير و كمان و ...", "اصابت جسم سخت ", "برق گرفتگي ", "آتش سوزي ( سوختگي) ", " تماس با مواد شيميائي ", "مسموميت", "نقص عضو در اثر عوارض دارويي", "نقص عضو ناشي از عوامل غير قابل پيش بيني"];
            var typesArrayFot = ["سكته ", " سرطان ", "سوختگي", "قتل ", "خودكشي", "برق گرفتگي", " سقوط از ارتفاع ", "سقوط در سطح همتراز", "تصادف ", "غرق شدگي", "مصرف مواد مخدر و الكل ", " فوت ناشي از عوارض پس از عمل جراحي ", "  فوت ناشي از عوامل غير قابل پيش بيني", "مسموميت", "فوت ناشي از بيماري"];
            var typesArrayHadese = ["سوختگي", "قتل ", "برق گرفتگي", " سقوط از ارتفاع ", "سقوط در سطح همتراز", "تصادف ", "غرق شدگي", "اصابت جسم سخت", "حوادث ناشي از عوامل غير قابل پيش بيني"];
            var typesArrayMoafiat = ["-"];
            var typesArrayDefault = ["نوع خسارت را انتخاب کنيد"];
            var selectedArray;
            var htmlString = "";
            if ($('#1kh_naghs').is(':checked') && combo.id == '1kh_naghs')
            {
                $('#1khsrt_type').val('NAGHSOZV') ;
                selectedArray = typesArrayNaghs;
                $("#1amount_elam_shode").attr("readonly", true);
                $("#1naghs_darsad_lbl").show();
                $("#1darsad_naghs_ozv").show();

//                $("#submit_khesarat").remove('#khesarat_num') ;
//                $("#submit_khesarat").append('<input type="hidden" id="khesarat_num" name="khesaratNum" value="II" />');
<%----%>
//                $.post('/calculateKhesaratAjaxly.action?' + $('#submit_khesarat').serialize(), function (msg)
//                {
//                    $('#1chk_naghs').html(' ' + $(msg).filter('#ajaxcalc_sarmayeNaghsOzv').val() + '');
//                });
                $("#1kh_ati").val('');
                $("#1kh_ati").attr('readonly', 'readonly');
                            $('#1kh_amraz').removeAttr('checked');
                            $('#1kh_omr').removeAttr('checked');
                            $('#1kh_hadese').removeAttr('checked');
                            $('#1kh_moafiat').removeAttr('checked');
            }
            else if ($('#1kh_amraz').is(':checked') && combo.id == '1kh_amraz')
            {
                $('#1khsrt_type').val('AMRAZ');
                selectedArray = typesArrayAmraz;
                $("#1kh_ati").val('');
                $("#1amount_elam_shode").addClass("validate[required]");
                $("#1kh_ati").attr('readonly', 'readonly');
                            $('#1kh_naghs').removeAttr('checked');
                            $('#1kh_omr').removeAttr('checked');
                            $('#1kh_hadese').removeAttr('checked');
                            $('#1kh_moafiat').removeAttr('checked');
                            $('#1amount_elam_shode').removeAttr('readonly');
                            $('#1amount_elam_shode').removeAttr('disabled');
            }
            else if ($('#1kh_omr').is(':checked') && combo.id == '1kh_omr')
            {
                $('#1khsrt_type').val('OMR');
                selectedArray = typesArrayFot;
                $("#1btn_fill_zero").show();
                $("#1amount_elam_shode").attr("readonly", true);
//                $("#submit_khesarat").remove('#khesarat_num');
//                $("#submit_khesarat").append('<input type="hidden" id="khesarat_num" name="khesaratNum" value="II" />');
<%----%>
//                $.post('/calculateKhesaratAjaxly.action?' + $('#submit_khesarat').serialize(), function (msg)
//                {
//                    $('#1amount_elam_shode').val($(msg).filter('#ajaxcalc_sarmayeFot').val());
//                });
                $("#1kh_ati").val('');
//                $("#1kh_ati").removeAttr('readonly');
                            $('#1kh_naghs').removeAttr('checked');
                            $('#1kh_amraz').removeAttr('checked');
                            $('#1kh_hadese').removeAttr('checked');
                            $('#1kh_moafiat').removeAttr('checked');
            }
            else if ($('#1kh_hadese').is(':checked') && combo.id == '1kh_hadese')
            {
                $("#1amount_elam_shode").attr("readonly", true);
                $('#1khsrt_type').val('HADESE');
                selectedArray = typesArrayHadese;
                $("#1btn_fill_zero").show();

//                $("#submit_khesarat").remove('#khesarat_num');
//                $("#submit_khesarat").append('<input type="hidden" id="khesarat_num" name="khesaratNum" value="II" />');
<%----%>
//                $.post('/calculateKhesaratAjaxly.action?' + $('#submit_khesarat').serialize(), function (msg)
//                {
//                    var sarmaHades = $(msg).filter('#ajaxcalc_sarmayeHadese').val().replace(new RegExp(",", "gm"), "");
//                    var sarmaFowt = $(msg).filter('#ajaxcalc_sarmayeFot').val().replace(new RegExp(",", "gm"), "");
//                    var majmoFotHadese = parseInt(sarmaHades) + parseInt(sarmaFowt);
//                    $('#1amount_elam_shode').val(jQuery.global.format(majmoFotHadese));
//                });
                $("#1kh_ati").val('');
//                $("#1kh_ati").removeAttr('readonly');
                            $('#1kh_naghs').removeAttr('checked');
                            $('#1kh_amraz').removeAttr('checked');
                            $('#1kh_omr').removeAttr('checked');
                            $('#1kh_moafiat').removeAttr('checked');
            }
            else if ($('#1kh_moafiat').is(':checked') && combo.id == '1kh_moafiat')
            {
                $("#1amount_elam_shode").attr("readonly", true);
                $('#1khsrt_type').val('MOAFIAT');
                selectedArray = typesArrayMoafiat;
                $("#1kh_ati").val('');
                $("#1kh_ati").attr('readonly', 'readonly');
                            $('#1kh_naghs').removeAttr('checked');
                            $('#1kh_amraz').removeAttr('checked');
                            $('#1kh_omr').removeAttr('checked');
                            $('#1kh_hadese').removeAttr('checked');
            }
            else
            {
                selectedArray = typesArrayDefault;
                $("#1kh_ati").val('');
                $("#1kh_ati").attr('readonly', 'readonly');
            }
            $.each(selectedArray, function (val, text)
            {
                htmlString += "<option value='" + text + "'>" + text + "</option>";
            });
            $('#1khesarat_type_list').html(htmlString);
        }
        </script>