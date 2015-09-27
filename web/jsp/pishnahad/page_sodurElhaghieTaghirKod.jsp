<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<c:out value="${bimename.id}"/>--%>
<s:actionmessage/>
<div align="center">
    <s:if test="%{darkhastBazkharid.state.id==9170}">
        <s:set name="dis" value="true" id="dis"/>

    </s:if>
    <s:else>
        <s:set name="dis" value="false" id="dis"/>

    </s:else>
    <s:if test="%{darkhastBazkharid.state.id==9170}">
    <form action="/sabtTaiidDarkhastTaghirKod.action" method="post">
        </s:if>
        <s:elseif test="%{darkhastBazkharid.darkhastType.name().equals('TAGHIRKOD')}">
        <form action="/taiidDarkhastTaghirKod" method="post">
            </s:elseif>
            <s:elseif test="%{darkhastBazkharid.darkhastType.name().equals('TOZIH')}">
            <form action="/taiidDarkhastTozih" method="post">
                </s:elseif>
                <s:hidden key="darkhastBazkharid.id" label=""/>
                <table>
                    <tr>
                        <td>شماره درخواست</td>
                        <td style="color: blue"><s:property value="darkhastBazkharid.shomareDarkhast"/></td>
                        <td>وضعیت درخواست</td>
                        <td style="color: #0000ff;"><s:property value="darkhastBazkharid.state.stateName"/></td>
                        <td>واحد صدور</td>
                        <td style="color: green"><s:property
                                value="darkhastBazkharid.bimename.pishnehad.namayande.vahedSodur.kodeNamayandeKargozar"/></td>
                        <td>نمایندگی</td>
                        <s:if test="%{darkhastBazkharid.darkhastType.name().equals('TAGHIRKOD')}">
                            <td style="color: red"><s:property value="darkhastBazkharid.taghirKodBe"/></td>
                        </s:if><s:else>
                        <td style="color: green;"><s:property
                                value="darkhastBazkharid.bimename.pishnehad.namayande.kodeNamayandeKargozar"/></td>
                    </s:else>
                    </tr>
                    <tr>
                        <td>نوع درخواست</td>
                        <td style="color: red">
                            <s:if test="%{darkhastBazkharid.darkhastType.name().equals('TAGHIRKOD')}">
                                تغییر
                            </s:if>
                            <s:if test="%{darkhastBazkharid.darkhastType.name().equals('TOZIH')}">
                                الحاقیه توضیح
                            </s:if>
                        </td>
                        <td>نوع خاص</td>
                        <td style="color: red">
                            <s:if test="%{darkhastBazkharid.darkhastType.name().equals('TAGHIRKOD')}">
                                تغییر کد نمایندگی
                            </s:if>
                            <s:if test="%{darkhastBazkharid.darkhastType.name().equals('TOZIH')}">
                                الحاقیه توضیح
                            </s:if>
                        </td>
                        <td>جرئیات</td>
                        <td style="color: red">
                            <s:if test="%{darkhastBazkharid.darkhastType.name().equals('TAGHIRKOD')}">
                                <s:if test="%{darkhastBazkharid.bimename.pishnehad.namayande.kodeNamayandeKargozar.substring(0,6).equals('111120')}">
                                    تغییر کد موقت
                                </s:if>
                                <s:else>
                                    تغییر نمایندگی
                                </s:else>
                            </s:if>
                        </td>
                        <td>کل حق بیمه</td>
                        <td>0</td>
                    </tr>
                    <tr>
                        <td>تاریخ صدور</td>
                        <td style="color: green"><s:property value="darkhastBazkharid.tarikhDarkhast"/></td>
                        <td>تاریخ اثر</td>
                        <td style="color: green"><s:property value="darkhastBazkharid.bimename.tarikhShorou"/></td>
                        <td>تاریخ اثر کارمزد</td>
                        <td></td>
                        <td>موضوع الحاقیه</td>
                        <s:if test="dis==true">
                            <td><input type="text" name="darkhastBazkharid.mozuElhaghie"
                                       value="<s:property value="darkhastBazkharid.mozuElhaghie"/>" disabled="${dis}"/>
                            </td>
                        </s:if><s:else>
                        <td><input type="text" name="darkhastBazkharid.mozuElhaghie"
                                   value="<s:property value="darkhastBazkharid.mozuElhaghie"/>"/></td>
                    </s:else>
                    </tr>
                    <tr>
                        <td>امضاکننده اول</td>
                        <td>
                            <s:if test="%{darkhastBazkharid.state.id==9170}">
                                <input type="text" disabled="true"
                                       value="<s:property value="darkhastBazkharid.emzaKonandeAval.user.fullName"/>"
                                       style="width:100px"/>
                            </s:if><s:else>
                            <input type="text"
                                   <s:if test="dis==true">disabled="${dis}"</s:if> id="bimename_emzaKonandeAval2"
                                   style="width:100px"/>
                            <input type="hidden"
                                   <s:if test="dis==true">disabled="${dis}"</s:if>
                                   name="darkhastBazkharid.emzaKonandeAval.id" id="bimename_emzaKonandeAval2_id"/>
                            <input type="button" value="جستجو"
                                   <s:if test="dis==true">disabled="${dis}"</s:if>
                                   onclick="$('#NafareEmza').val('#bimename_emzaKonandeAval2');fillEmze(); "/>
                        </s:else>
                        </td>
                        <td>امضاکننده دوم</td>
                        <td>
                            <s:if test="%{darkhastBazkharid.state.id==9170}">
                                <input type="text" disabled="true"
                                       value="<s:property value="darkhastBazkharid.emzaKonandeDovom.user.fullName"/>"
                                       style="width:100px"/>
                            </s:if><s:else>
                            <input type="text"
                                   <s:if test="dis==true">disabled="${dis}"</s:if> id="bimename_emzaKonandeDovom2"
                                   style="width:100px"/>
                            <input type="hidden"
                                   <s:if test="dis==true">disabled="${dis}"</s:if>
                                   name="darkhastBazkharid.emzaKonandeDovom.id" id="bimename_emzaKonandeDovom2_id"/>
                            <input type="button"
                                   <s:if test="dis==true">disabled="${dis}"</s:if> value="جستجو"
                                   onclick="$('#NafareEmza').val('#bimename_emzaKonandeDovom2');fillEmze(); "/>
                        </s:else>
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>متن الحاقیه</td>
                        <td colspan="7"><textarea name="darkhastBazkharid.matnElhaghie" cols="140" rows="20"
                                      <s:if test="dis==true">disabled="${dis}"</s:if>>
                            <s:property value="darkhastBazkharid.matnElhaghie"/>
                        </textarea></td>
                    </tr>
                    <tr>
                        <s:if test="%{darkhastBazkharid.state.id==9170}">
                            <td><s:submit value="صدور الحاقیه" theme="simple"/></td>
                        </s:if>
                        <s:elseif test="%{darkhastBazkharid.darkhastType.name().equals('TAGHIRKOD')}">
                            <td><s:submit value="اعلام به مالی" theme="simple"/></td>
                        </s:elseif>
                        <s:elseif test="%{darkhastBazkharid.darkhastType.name().equals('TOZIH')}">
                            <td><s:submit value="ثبت" theme="simple"/></td>
                        </s:elseif>
                        <td><input type="button" value="حذف درخواست"
                                   onclick="window.location='/hazfDarkhast?darkhastBazkharid.id=<s:property value="darkhastBazkharid.id"/>'">
                        </td>
                    </tr>
                </table>
            </form>
</div>
<%--<input type="hidden" id="NafareEmza2" />
<div id="tblEmza2" style="display: none">
    <table cellpadding="3" cellspacing="3" border="0"
           style="margin-left:auto;margin-right:auto;align:center;direction: rtl;">
        <tr>
            <td colspan="5">جستحو شخص امضا کننده</td>
        </tr>
        <tr>
            <td>کد پرسنلی</td>
            <td><input type="text" id="emzaPersonalCode2"/></td>
            <td>نام</td>
            <td><input type="text" id="emzaName2"/></td>
            <td><input type="button" value="جستجو" id="btnSearch2"
                       onclick="searchEmaz()"></td>
        </tr>
    </table>
    <div id="searchResualt2"></div>
</div>
<script type="text/javascript">
    function searchEmaz()
    {
        var emzaName = $('#emzaName2').val();
        var emzaPersonalCode = $('#emzaPersonalCode2').val();


        $.post("/findEmzaKonande.action?user.firstName="+ emzaName + "&user.personalCode=" + emzaPersonalCode   , function(msg){
            $('#searchResualt2').html(msg);
        });

        fillEmze();
    }
    function fillEmze()
    {
        $('#tblEmza2').dialog('open');
    }
    function selectRow(id, fn, ln) {
        alert('a');
        var ctrlId = $('#NafareEmza2').val();
        $(ctrlId).val(fn + " " + ln);
        $(ctrlId + '_id').val(id);

        hideEmzaModal();
    }

    function hideEmzaModal() {
        $('#emzaName2').val('');
        $('#emzaPersonalCode2').val('');
        $('#tblEmza2').dialog('close');
        $('#searchResualt2').html('');
    }
</script>--%>
