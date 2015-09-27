<%@ page import="com.bitarts.parsian.model.bimename.Darkhast" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<c:out value="${bimename.id}"/>--%>

<input type="hidden" name="bimename.id" value="<c:out value='${bimename.id}'/>"/>
<s:actionerror/>
<form class="mainFrame" id="mainForm4Darkhast" method="post" action="/sabteDarkhasteBazkharid">
<div id="moshakhasat">
    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
        مشخصات
    </p>
    <input type="hidden" name="darkhast.darkhastType" value="${darkhast.darkhastType}"/>
    <input type="hidden" name="bimename.id" value="${bimename.id}"/>

    <table class="mystrippedtable" dir="rtl" cellpadding="2px" cellspacing="0px"
           style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <tr class="odd">
            <td>
                <label>نام:</label>
            </td>
            <td>
                <input type="text" readonly="readonly" name="darkhastBazkharid.nameBimeShode"
                    <c:if test="${darkhast.darkhastType=='EBTAL' || darkhast.darkhastType=='BAZKHARID'}">
                       value="<c:out value='${bimename.pishnehad.bimeGozar.shakhs.name}'/>"
                    </c:if>
                    <c:if test="${darkhast.darkhastType!='EBTAL' && darkhast.darkhastType!='BAZKHARID'}">
                        value="<c:out value='${bimename.pishnehad.bimeShode.shakhs.name}'/>"
                    </c:if>
                />
            </td>
            <td>
                <label>نام خانوادگی:</label>
            </td>
            <td>
                <input type="text" readonly="readonly" name="darkhastBazkharid.familyBimeShode"
                    <c:if test="${darkhast.darkhastType=='EBTAL' || darkhast.darkhastType=='BAZKHARID'}">
                       value="<c:out value='${bimename.pishnehad.bimeGozar.shakhs.nameKhanevadegi}'/>"
                    </c:if>
                    <c:if test="${darkhast.darkhastType!='EBTAL' && darkhast.darkhastType!='BAZKHARID'}">
                       value="<c:out value='${bimename.pishnehad.bimeShode.shakhs.nameKhanevadegi}'/>"
                   </c:if>
                />
            </td>
            <td>
                <label>نام پدر:</label>
            </td>
            <td>
                <input type="text" readonly="readonly" name="darkhastBazkharid.namePedarBimeShode"
                    <c:if test="${darkhast.darkhastType=='EBTAL' || darkhast.darkhastType=='BAZKHARID'}">
                       value="<c:out value='${bimename.pishnehad.bimeGozar.shakhs.namePedar}'/>"
                    </c:if>
                    <c:if test="${darkhast.darkhastType!='EBTAL' && darkhast.darkhastType!='BAZKHARID'}">
                       value="<c:out value='${bimename.pishnehad.bimeShode.shakhs.namePedar}'/>"
                    </c:if>
                />
            </td>
        </tr>
        <tr class="odd">
            <td>
                <label>کد ملی:</label>
            </td>
            <td>
                <input type="text" readonly="readonly" name="darkhastBazkharid.kodeMeliBimeShode"
                    <c:if test="${darkhast.darkhastType=='EBTAL' || darkhast.darkhastType=='BAZKHARID'}">
                       value="<c:out value='${bimename.pishnehad.bimeGozar.shakhs.kodeMelliShenasayi}'/>"
                    </c:if>
                    <c:if test="${darkhast.darkhastType!='EBTAL' && darkhast.darkhastType!='BAZKHARID'}">
                       value="<c:out value='${bimename.pishnehad.bimeShode.shakhs.kodeMelliShenasayi}'/>"
                   </c:if>
                />
            </td>
            <td>
                <label>شماره شناسنامه:</label>
            </td>
            <td>
                <input type="text" readonly="readonly" name="darkhastBazkharid.shomareShenasnameBimeShode"
                   <c:if test="${darkhast.darkhastType=='EBTAL' || darkhast.darkhastType=='BAZKHARID'}">
                       value="<c:out value='${bimename.pishnehad.bimeGozar.shakhs.shomareShenasnameh}'/>"
                   </c:if>
                   <c:if test="${darkhast.darkhastType!='EBTAL' && darkhast.darkhastType!='BAZKHARID'}">
                       value="<c:out value='${bimename.pishnehad.bimeShode.shakhs.shomareShenasnameh}'/>"
                  </c:if>
                   />
            </td>
            <td>
                <label>تاریخ تولد:</label>
            </td>
            <td>
                <input type="text" readonly="readonly" name="darkhastBazkharid.tarikhTavallodBimeShode"
                    <c:if test="${darkhast.darkhastType=='EBTAL' || darkhast.darkhastType=='BAZKHARID'}">
                       value="<c:out value='${bimename.pishnehad.bimeGozar.shakhs.tarikheTavallod}'/>"
                    </c:if>
                    <c:if test="${darkhast.darkhastType!='EBTAL' && darkhast.darkhastType!='BAZKHARID'}">
                       value="<c:out value='${bimename.pishnehad.bimeShode.shakhs.tarikheTavallod}'/>"
                   </c:if>
                       />

            </td>
        </tr>
        <tr class="odd">
            <td>
                <label>تلفن ثابت بیمه شده: </label>
            </td>
            <td colspan="2">
                <input type="text" name="telephonSabetBimeShode" value="<c:out value='${telephonSabetBimeShode}'/>">
                <input type="text" name="telephonSabetBimeShodeKod"
                       value="<c:out value='${telephonSabetBimeShodeKod}'/>" style="width: 20px">
            </td>
            <td>
                <label>تلفن همراه:</label>
            </td>
            <td colspan="3">
                <input type="text" name="darkhastBazkharid.telephonHamrahBimeShode" id="telephone_hamrah"
                       value="<c:out value='${darkhastBazkharid.telephonHamrahBimeShode}'/>">
            </td>
        </tr>
    </table>
</div>
<div id="noedarkhast">
    <br class="clear"/>

    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
        نوع درخواست
    </p>
    <input type="hidden" name="mablagh" value="${mablagh}"/>
    <c:choose>
        <c:when test="${darkhast.darkhastType== 'VAM'||darkhastBazkharid.darkhastType== 'VAM'}">
            <c:if test="${arzesheBazkharid!=null}">
                <input type="hidden" name="darkhastBazkharid.arzeshBazkharid" value="${arzesheBazkharid}"/>
            </c:if>
            <c:if test="${darkhastBazkharid.arzeshBazkharid!=null}">
                <input type="hidden" name="darkhastBazkharid.arzeshBazkharid"
                       value="${darkhastBazkharid.arzeshBazkharid}"/>
            </c:if>
        </c:when>
        <c:when test="${darkhast.darkhastType== 'BARDASHT_AZ_ANDOKHTE'||darkhastBazkharid.darkhastType== 'BARDASHT_AZ_ANDOKHTE'}">
            <c:if test="${darkhastBazkharid.mablaghDarkhastiBardasht!=null}">
                <input type="hidden" name="darkhastBazkharid.mablaghDarkhastiBardasht" value="${darkhastBazkharid.mablaghDarkhastiBardasht}"/>
            </c:if>
            <c:if test="${darkhastBazkharid.mablaghDarkhastiBardasht==null && mablagh!=null}">
                <input type="hidden" name="darkhastBazkharid.mablaghDarkhastiBardasht"
                       value="${mablagh}"/>
            </c:if>
        </c:when>
        <c:otherwise>
            <c:if test="${arzesheBazkharid!=null}">
                <input type="hidden" name="darkhastBazkharid.arzeshBazkharid" value="${arzesheBazkharid}"/>
            </c:if>
            <c:if test="${darkhastBazkharid.arzeshBazkharid!=null}">
                <input type="hidden" name="darkhastBazkharid.arzeshBazkharid"
                       value="${darkhastBazkharid.arzeshBazkharid}"/>
            </c:if>
        </c:otherwise>
    </c:choose>
    <table class="mystrippedtable" dir="rtl" cellpadding="2px" width="60%" cellspacing="0px"
           style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <c:if test="${darkhast.darkhastType== 'VAM'||darkhastBazkharid.darkhastType== 'VAM'}">
            <tr class="odd">
                <td>
                    <input type="radio" ${darkhast.darkhastType== 'VAM'||darkhastBazkharid.darkhastType== 'VAM'? "checked='checked'" :"disabled='disabled'"}
                           name="darkhast.darkhastType" value="<%=Darkhast.DarkhastType.VAM%>"/>
                </td>
                <td colspan="5">
                    <label>دریافت وام از اندوخته به مبلغ
                        <c:if test="${mablagh!=null}">${mablagh}</c:if>
                        <c:if test="${darkhastBazkharid.mablagheVamedarkhasti!=null}">${darkhastBazkharid.mablagheVamedarkhasti}</c:if>
                        ریال
                    </label>
                </td>
            </tr>
        </c:if>
        <c:if test="${darkhast.darkhastType== 'TASVIE_PISH_AZ_MOEDE_VAM'||darkhastBazkharid.darkhastType== 'TASVIE_PISH_AZ_MOEDE_VAM'}">
            <tr class="odd">
                <td>
                    <input type="radio" ${darkhast.darkhastType== 'TASVIE_PISH_AZ_MOEDE_VAM'||darkhastBazkharid.darkhastType== 'TASVIE_PISH_AZ_MOEDE_VAM'? "checked='checked'" :"disabled='disabled'"}
                           name="darkhast.darkhastType" value="<%=Darkhast.DarkhastType.TASVIE_PISH_AZ_MOEDE_VAM%>"/>
                </td>
                <td colspan="5">
                    <input type="hidden" name="vamId" value="${vamId}"/>
                    <label>تسویه وام به صورت پیش از موعد</label>
                </td>
            </tr>
        </c:if>
        <c:if test="${darkhast.darkhastType== 'BARDASHT_AZ_ANDOKHTE'||darkhastBazkharid.darkhastType== 'BARDASHT_AZ_ANDOKHTE'}">
            <tr class="odd">
                <td>
                    <input type="radio" ${(darkhast.darkhastType== 'BARDASHT_AZ_ANDOKHTE'||darkhastBazkharid.darkhastType== 'BARDASHT_AZ_ANDOKHTE')? "checked='checked'" :"disabled='disabled'"}
                           name="darkhast.darkhastType" value="<%=Darkhast.DarkhastType.BARDASHT_AZ_ANDOKHTE%>"/>
                </td>
                <td colspan="5">
                        <%--<label>برداشت از اندوخته بیمه نامه به مبلغ .......... ریال</label>--%>
                    <c:if test="${darkhastBazkharid.mablaghDarkhastiBardasht==null && arzesheBazkharid!=null}">
                        <label>برداشت از اندوخته بیمه نامه به مبلغ&nbsp;${mablagh}&nbsp;ریال</label>
                    </c:if>
                    <c:if test="${darkhastBazkharid.mablaghDarkhastiBardasht!=null}">
                        <label>برداشت از اندوخته بیمه نامه به
                            مبلغ&nbsp;${darkhastBazkharid.mablaghDarkhastiBardasht}&nbsp;ریال</label>
                    </c:if>
                </td>
            </tr>
        </c:if>
        <c:if test="${darkhast.darkhastType== 'BAZKHARID'||darkhastBazkharid.darkhastType== 'BAZKHARID'}">
            <tr class="odd">
                <td>
                    <input type="radio" ${(darkhast.darkhastType== 'BAZKHARID'||darkhastBazkharid.darkhastType== 'BAZKHARID')? "checked='checked'" :"disabled='disabled'"}
                           name="darkhast.darkhastType" value="<%=Darkhast.DarkhastType.BAZKHARID%>"/>
                </td>
                <td colspan="5">
                    <c:if test="${arzesheBazkharid!=null}">
                        <label>بازخرید بیمه نامه به ارزش بازخریدی به مبلغ ${mablagh}&nbsp;&nbsp;ریال</label>
                    </c:if>
                    <c:if test="${darkhastBazkharid.arzeshBazkharid!=null}">
                        <label>بازخرید بیمه نامه به ارزش بازخریدی به مبلغ ${darkhastBazkharid.arzeshBazkharid}&nbsp;&nbsp;ریال</label>
                    </c:if>
                </td>
            </tr>
        </c:if>
        <c:if test="${darkhast.darkhastType == 'EBTAL'||darkhastBazkharid.darkhastType== 'EBTAL'}">
            <tr class="odd">
                <td>
                    <input type="radio" disabled="disabled" checked="checked" name="darkhast.darkhastType" value="<%=Darkhast.DarkhastType.EBTAL%>"/>
                </td>
                <td colspan="5">
                    <label>ابطال بیمه نامه (صرفا تا 30 روز از تاریخ صدور بیمه نامه امکان پذیر می باشد.)</label>
                </td>
            </tr>
        </c:if>
    </table>
</div>
<c:if test="${darkhast.darkhastType != 'TASVIE_PISH_AZ_MOEDE_VAM' && darkhastBazkharid.darkhastType != 'TASVIE_PISH_AZ_MOEDE_VAM'}">
    <div id="etelaatebanki">
        <br class="clear"/>

        <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
            اطلاعات بانکی
        </p>
        <table class="mystrippedtable" dir="rtl" cellpadding="2px" cellspacing="0px"
               style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
            <tr class="odd">
                <td colspan="6">
                    <label>خواهشمند است مبلغ فوق به </label>
                </td>
            </tr>
            <tr class="odd">
                <td>
                    <label>شماره حساب:</label>
                </td>
                <td>
                    <input type="text" name="darkhastBazkharid.shomareHesab"
                           value="<c:out value='${darkhastBazkharid.shomareHesab}'/>"/>
                </td>
                <td>
                    <label>به شماره شبای:</label>
                </td>
                <td>
                    <input type="text" name="darkhastBazkharid.shomareShaba" id="shomareShaba" class="validate[custom[onlyNumber]]"
                           <%--class="validate[funcCall[shaba]]"--%>
                           value="<c:out value='${darkhastBazkharid.shomareShaba}'/>"/>
                </td>
                <td>
                    <label>نزد بانک:</label>
                </td>
                <td>
                    <input type="text" name="darkhastBazkharid.bankName"
                           value="<c:out value='${darkhastBazkharid.bankName}'/>"/>
                </td>
            </tr>
            <tr class="odd">
                <td>
                    <label>شعبه:</label>
                </td>
                <td>
                    <input type="text" name="darkhastBazkharid.shobeName"
                           value="<c:out value='${darkhastBazkharid.shobeName}'/>"/>
                </td>
                <td>
                    <label>کد شعبه:</label>
                </td>
                <td>
                    <input type="text" name="darkhastBazkharid.kodeShobe"
                           value="<c:out value='${darkhastBazkharid.kodeShobe}'/>"/>
                </td>
                <td>
                    <label>به نام آقای/خانم:</label>
                </td>
                <td>
                    <input type="text" name="darkhastBazkharid.sahebHesab"
                           value="<c:out value='${darkhastBazkharid.sahebHesab}'/>"/>
                </td>
            </tr>
            <tr class="odd">
                <td colspan="6">
                    <label>واریز گردد.</label>
                </td>
            </tr>
            <tr>
                <td colspan="6">
                    <label style="font-style:italic;color:red;font-weight:bold; font-size:10px;">
                        *مسئولیت هر گونه اشتباه در اعلام شماره حساب، نام صاحب حساب و سایر مشخصات حساب بانکی جهت واریز
                        مبلغ فوق الذکر، متوجه بیمه گذار خواهد بود.
                    </label>
                </td>
            </tr>
            <input type="hidden" name="darkhastBazkharid.bimenameIsMafqud" value="${bimenameIsMafqud}"/>
        </table>
    </div>
</c:if>
<div id="taeedvaehraz">
    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
        تایید درخواست و احراز هویت بیمه گذار
    </p>
    <table class="mystrippedtable" dir="rtl" cellpadding="2px" cellspacing="0px"
           style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <style type="text/css">
            .odd input, .odd label {
                float: right;
            }
        </style>
        <tr class="odd">
            <td width="31%">
                <%--<c:choose>--%>
                    <%--<c:when test="${user.namayandegi.type==1}">--%>
                        <%--<input type="checkbox" checked="checked" name=""/><label>مجتمع بیمه ای</label>--%>
                        <%--<input type="checkbox" name=""/><label>شعبه</label>--%>
                        <%--<input type="checkbox" name=""/><label>نمایندگی</label>--%>
                        <%--<input type="checkbox" name=""/><label>مجتمع بیمه ای</label>--%>
                    <%--</c:when>--%>
                    <%--<c:when test="${user.namayandegi.type==2}">--%>
                        <%--<input type="checkbox" name=""/><label>مجتمع بیمه ای</label>--%>
                        <%--<input type="checkbox" checked="checked" name=""/><label>شعبه</label>--%>
                        <%--<input type="checkbox" name=""/><label>نمایندگی</label>--%>
                        <%--<input type="checkbox" name=""/><label>مجتمع بیمه ای</label>--%>
                    <%--</c:when>--%>
                    <%--<c:when test="${user.namayandegi.type==3}">--%>
                        <%--<input type="checkbox" name=""/><label>مجتمع بیمه ای</label>--%>
                        <%--<input type="checkbox" name=""/><label>شعبه</label>--%>
                        <%--<input type="checkbox" checked="checked" name=""/><label>نمایندگی</label>--%>
                        <%--<input type="checkbox" name=""/><label>مجتمع بیمه ای</label>--%>
                    <%--</c:when>--%>
                    <%--<c:when test="${user.namayandegi.type==4}">--%>
                        <%--<input type="checkbox" name=""/><label>مجتمع بیمه ای</label>--%>
                        <%--<input type="checkbox" name=""/><label>شعبه</label>--%>
                        <%--<input type="checkbox" name=""/><label>نمایندگی</label>--%>
                        <%--<input type="checkbox" checked="checked" name=""/><label>ICD</label>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--<input type="checkbox" name=""/><label>مجتمع بیمه ای</label>--%>
                        <%--<input type="checkbox" name=""/><label>شعبه</label>--%>
                        <%--<input type="checkbox" name=""/><label>نمایندگی</label>--%>
                        <%--<input type="checkbox" name=""/><label>مجتمع بیمه ای</label>--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>
                <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
                    <input type="text" value="${user.namayandegi.namayandeTypeFarsi}" readonly="readonly"/>
                </sec:authorize>
                <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
                    <input type="text" value="${bimename.pishnehad.namayande.namayandeTypeFarsi}"readonly="readonly"/>
                </sec:authorize>
            </td>
            <td>
                <label>کد:</label>
            </td>
            <td>
                <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
                    <input type="text" name="darkhastBazkharid.kodeNamayandegi" readonly="readonly" value="<c:out value='${user.namayandegi.kodeNamayandeKargozar}'/>"/>
                </sec:authorize>
                <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
                    <input type="text" name="darkhastBazkharid.kodeNamayandegi" value="${bimename.pishnehad.namayande.kodeNamayandeKargozar}" readonly="readonly"/>
                </sec:authorize>

            </td>
            <td>
                <label>تلفن:</label>
            </td>
            <td>
                <%--<sec:authorize ifAnyGranted="ROLE_NAMAYANDE">--%>
                    <input type="text" name="darkhastBazkharid.telephoneNamayandegi"
                       value="${bimename.pishnehad.namayande.telephone}" />
                <%--</sec:authorize>--%>
                <%--<sec:authorize ifNotGranted="ROLE_NAMAYANDE">--%>
                    <%--<input type="text"  name="darkhastBazkharid.telephoneNamayandegi" value="${bimename.pishnehad.namayande.telephone}"/>--%>
                <%--</sec:authorize>--%>
            </td>
        </tr>
        <tr class="odd">
            <td colspan="5">
                اینجانب
                <%--<b><c:out value="${user.firstName}"/>&nbsp;<c:out value="${user.lastName}"/></b>--%>
                <b>${bimename.pishnehad.namayande.name}</b>
                ، ضمن احراز هویت بیمه گذار محترم
                <b><c:out value='${bimename.pishnehad.bimeGozar.shakhs.name}'/>&nbsp;<c:out
                        value='${bimename.pishnehad.bimeGozar.shakhs.nameKhanevadegi}'/></b>
                درخواست وی مبنی بر
                &nbsp;<b>
                <c:if test="${darkhast.darkhastType == 'EBTAL'||darkhastBazkharid.darkhastType== 'EBTAL'}">
                    ابطال
                </c:if>
                <c:if test="${darkhast.darkhastType == 'BARDASHT_AZ_ANDOKHTE'||darkhastBazkharid.darkhastType== 'BARDASHT_AZ_ANDOKHTE'}">
                    برداشت از اندوخته
                </c:if>
                <c:if test="${darkhast.darkhastType == 'BAZKHARID'||darkhastBazkharid.darkhastType== 'BAZKHARID'}">
                                    بازخرید
                </c:if>
                </b>&nbsp;
                بیمه نامه عمر انفرادی به شماره
                &nbsp;<b><c:out value='${bimename.shomare}'/></b>&nbsp;
                را تایید و اعلام می دارم. ضمنا توضیحات تکمیلی در خصوص درخواست بیمه گذار، مطابق با شرایط عمومی بهره مندی
                از منافع بیمه نامه های عمر انفرادی ارائه گردید.
            </td>
        </tr>
        <br class="clear"/>
        <br class="clear"/>
        <br class="clear"/>

        <tr>
            <td>
                <c:if test="${darkhastBazkharid.id != null}">
                    <c:choose>
                        <c:when test="${darkhastBazkharid.darkhastType== 'VAM' || darkhastBazkharid.darkhastType== 'BARDASHT_AZ_ANDOKHTE'}">
                            <%--<input type="button"--%>
                                   <%--onclick="window.open('/printeVam?pishnehadReport.darkhastBazkharid.id=${darkhastBazkharid.id}');"--%>
                                   <%--value="پرینت">--%>
                            <c:if test="${darkhastBazkharid.darkhastType== 'VAM' && darkhastBazkharid.ghestBandi != null}">
                                <%--<st:url action="printDaftarcheGhestVamParsian" id="printDaftarcheParsian">--%>
                                <%--<st:param name="darkhastBazkharid.id" value="darkhastBazkharid.id"/>--%>
                                <%--</st:url>--%>
                                <%--<st:url action="printDaftarcheGhestVamTejarat" id="printDaftarcheTejarat">--%>
                                <%--<st:param name="darkhastBazkharid.id" value="darkhastBazkharid.id"/>--%>
                                <%--</st:url>--%>
                                <c:if test="${darkhastBazkharid.state.id>=10110}">
                                    <%--<input type="button"--%>
                                           <%--onclick="window.open('/printDaftarcheGhestVamParsian.action?darkhastBazkharid.id=${darkhastBazkharid.id}');"--%>
                                           <%--style="float:left;margin-left:2px;"--%>
                                           <%--value="چاپ دفترچه اقساط وام پارسیان">--%>
                                    <%--<input type="button"--%>
                                           <%--onclick="window.open('/printDaftarcheGhestVamTejarat.action?darkhastBazkharid.id${darkhastBazkharid.id}');"--%>
                                           <%--style="float:left;margin-left:2px;"--%>
                                           <%--value="چاپ دفترچه اقساط وام تجارت">--%>
                                </c:if>
                                <%--<st:url action="printSuratVaziatGhestVam" id="printSuratVaziat">--%>
                                <%--<st:param name="darkhastBazkharid.id" value="darkhastBazkharid.id"/>--%>
                                <%--</st:url>--%>
                                <%--<input type="button"--%>
                                       <%--onclick="window.open('/printSuratVaziatGhestVam.action?darkhastBazkharid.id=${darkhastBazkharid.id}');"--%>
                                       <%--style="float:left;margin-left:2px;"--%>
                                       <%--value="صورت وضعیت اقساط وام">--%>
                            </c:if>
                        </c:when>
                        <c:when test="${darkhastBazkharid.darkhastType== 'TASVIE_PISH_AZ_MOEDE_VAM'}">
                            <input type="button" id="tsv_pish_moed_prnt"
                                   onclick="window.open('/printeTasvieyeVam?pishnehadReport.darkhastBazkharid.id=${darkhastBazkharid.id}');"
                                   value="پرینت تسویه پیش از موعد وام">
                        </c:when>
                        <c:otherwise>
                            <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
                                <input type="button"
                                   onclick="alertMessage('کاربر محترم لطفاً فرم مربوطه را از قسمت فرم ها و مستندات چاپ گرفته و پس از مهر و امضاء همراه با بقیه مدارک مربوطه، در فرمت pdf تهیه نموده و در قسمت ضمائم آپلود نمایید سپس دکمه اتمام آپلود را بفشارید تا وضعیت درخواست شما به جدید تغییر یابد.'); window.open('/printeBazkharid?pishnehadReport.darkhastBazkharid.id=${darkhastBazkharid.id}');"
                                   value="چاپ فرم">
                            </sec:authorize>
                            <sec:authorize ifNotGranted="ROLE_NAMAYANDE">
                                <input type="button"
                                   onclick="window.open('/printeBazkharid?pishnehadReport.darkhastBazkharid.id=${darkhastBazkharid.id}');"value="چاپ فرم">
                            </sec:authorize>
                        </c:otherwise>
                    </c:choose>
                </c:if>


            </td>
        </tr>

    </table>
</div>
</form>
<c:if test="${darkhastBazkharid.darkhastType== 'VAM' || darkhastBazkharid.darkhastType== 'BARDASHT_AZ_ANDOKHTE'}">
    <input type="button"
    onclick="go();window.open('/printeVam?pishnehadReport.darkhastBazkharid.id=${darkhastBazkharid.id}');"
    value="پرینت">
</c:if>
<script type="text/javascript">

    <c:if test="${darkhastBazkharid!=null && darkhastBazkharid.state.id>=10100}">
        $(document).ready(function ()
        {
            $('#moshakhasat :input').attr('disabled', 'disabled');
            $('#noedarkhast :input').attr('disabled', 'disabled');
            $('#etelaatebanki :input').attr('disabled', 'disabled');
            $('#taeedvaehraz :input').attr('disabled', 'disabled');
            <c:if test="${darkhastBazkharid.darkhastType=='TASVIE_PISH_AZ_MOEDE_VAM'}">
                $('#tsv_pish_moed_prnt').attr('disabled', '');
            </c:if>

        })
    </c:if>

    if($('#telephone_hamrah').val().length < 1)
        $('#telephone_hamrah').val('${bimename.pishnehad.addressBimeGozar.telephoneHamrah}');
</script>
