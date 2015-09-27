<%@ page import="com.bitarts.parsian.model.asnadeSodor.Ghest" %>
<%@ page import="com.bitarts.parsian.model.asnadeSodor.GhestBandi" %>
<%@ page import="com.bitarts.parsian.model.bimename.Bimename" %>
<%@ page import="org.apache.axis.encoding.ser.ArrayDeserializer" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<s:actionmessage/>

<form id="createSodurElhaghie" name="formElhaghie" action="/sodooreElhaghie.action" method="post"
      accept-charset="UTF-8">

<c:if test="${darkhastBazkharid.darkhastType=='TAGHIRKOD'}">
    <input type="hidden" name="bazarYabUserName" value="<%=(String)session.getAttribute("bazarYabUserNameSes")%>"/>
</c:if>
<s:if test="%{darkhastTaghirat!=null}">
    <input type="hidden" name="darkhastTaghirat.id" value="${darkhastTaghirat.id}"/>
</s:if>
<s:elseif test="%{darkhastBazkharid != null}">
    <input type="hidden" name="darkhastBazkharid.id" value="${darkhastBazkharid.id}"/>
    <input type="hidden" name="darkhastBazkharid.darkhastType" value="${darkhastBazkharid.darkhastType}"/>
</s:elseif>
<input type="hidden" id="transidbimename" name="transitionId"/>
<input type="hidden" id="sodurbimlgmsg" name="logmessage" value="الحاقیه صادر شد"/>

<input type="hidden" id="NafareEmza"/>

<input type="hidden" name="elhaghiye.id" value="${elhaghiye.id}"/>


<input type="hidden" name="tozih" value="${tozih}"/>

<div id="tblEmza" style="display: none">
    <table cellpadding="3" cellspacing="3" border="0"
           style="margin-left:auto;margin-right:auto;align:center;direction: rtl;">
        <tr>
            <td colspan="5">جستحو شخص امضا کننده</td>
        </tr>
        <tr>
            <td>کد پرسنلی</td>
            <td><input type="text" id="emzaPersonalCode"/></td>
            <td>نام</td>
            <td><input type="text" id="emzaName"/></td>
            <td><input type="button" value="جستجو" id="btnSearch"
                       onclick="searchEmaz()"></td>
        </tr>
    </table>
    <div id="searchResualt"></div>
</div>
<table class="mystrippedtable" id="table4sodourelhaghie" dir="rtl" cellpadding="2px" cellspacing="0px"
       style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
<tr class="odd">
    <td>
        <label>شماره الحاقیه:</label>
    </td>
    <td>
        <input type="text" readonly="readonly" value="${elhaghiye.shomareElhaghiye}"/>
    </td>
    <td>
        <label>ردیف الحاقیه:</label>
    </td>
    <td>
        <input type="text" readonly="readonly" value="${elhaghiye.radifElhaghiye}"/>
    </td>
    <td>
        <label>وضعیت الحاقیه:</label>
    </td>
    <td>
        <input type="text" readonly="readonly" value="${elhaghiye.state.stateName}"/>
    </td>
</tr>
<tr class="even">
    <td>
        <label>واحد صدور:</label>
    </td>
    <td>
        <s:if test="%{showElhaghiye}">
            <s:if test="%{darkhastBazkharid != null}">
                <%--<s:if test="%{darkhastBazkharid.namayande.vahedSodur.name != null && !darkhastBazkharid.namayande.vahedSodur.name.isEmpty()}">--%>
                    <input type="text" name="darkhastBazkharid.namayande.vahedSodur.name" readonly="readonly"
                           <%--value="${darkhastBazkharid.namayande.vahedSodur.name}"/>--%>
                    value="${darkhastBazkharid.bimename.pishnehad.karshenas.mojtamaSodoor.name}"/>
                <%--</s:if>--%>
                <%--<s:else>--%>
                    <%--<input type="text" name="darkhastBazkharid.namayande.vahedSodur.name" readonly="readonly"--%>
                           <%--value="-"/>--%>
                <%--</s:else>--%>
                <input type="hidden" name="darkhastBazkharid.namayande.vahedSodur.id"
                       <%--value="${darkhastBazkharid.namayande.vahedSodur.id}"/>--%>
                        value="${darkhastBazkharid.bimename.pishnehad.karshenas.mojtamaSodoor.id}"/>
            </s:if>
            <s:else>
                <input type="text" readonly="readonly" value="${darkhastBazkharid.bimename.pishnehad.karshenas.mojtamaSodoor.id}"/>
                       <%--value="${user.mojtamaSodoor.name}"/>--%>
            </s:else>
        </s:if>
        <s:else>
            <s:if test="%{darkhastBazkharid != null}">
                <s:if test="%{user.mojtamaSodoor.name != null && !user.mojtamaSodoor.name.isEmpty()}">
                    <input type="text" name="darkhastBazkharid.namayande.name" readonly="readonly"
                           value="${user.mojtamaSodoor.name}"/>
                </s:if>
                <s:else>
                    <input type="text" name="darkhastBazkharid.namayande.name" readonly="readonly" value="-"/>
                </s:else>
                <input type="hidden" name="darkhastBazkharid.namayande.id" value="${user.mojtamaSodoor.id}"/>
            </s:if>
            <s:else>
                <input type="text" readonly="readonly" value="${user.mojtamaSodoor.name}"/>
            </s:else>
        </s:else>
    </td>
    <td>
        <label>نمایندگی:</label>
    </td>
    <td>
        <s:if test="%{darkhastTaghirat!=null}">
            <input type="text" readonly="readonly" value="${darkhastTaghirat.namayande.name}"/>
        </s:if>
        <s:elseif test="%{darkhastBazkharid!=null}">
            <input type="text" readonly="readonly" value="${darkhastBazkharid.namayande.name}"/>
        </s:elseif>
    </td>
    <td>
        <label>کل حق بیمه:</label>
    </td>
    <td>
        <input type="text" readonly="readonly"
               <c:if test="${elhaghiye.mablagh!=null && elhaghiye.mablagh!=''}">
                   value="${elhaghiye.mablagh}"
                </c:if>
                <c:if test="${darkhastBazkharid.darkhastType=='BAZKHARID'&&(elhaghiye.mablagh==null || elhaghiye.mablagh=='')}">
                    value="-${darkhastBazkharid.bimename.haghBimeBazkharidi}"
                </c:if>
                <c:if test="${darkhastBazkharid.darkhastType=='KHESARAT'&&(elhaghiye.mablagh==null || elhaghiye.mablagh=='')}">
                    value="${darkhastBazkharid.currentKhesarat.koleHaghBimeField}"
                </c:if>
               name="elhaghiye.mablagh" id="elhaghie_mablagh"/>
    </td>
</tr>
<tr class="odd">
    <td>
        <label>تاریخ صدور:</label>
    </td>
    <td>
        <input type="text" readonly="readonly" name="elhaghiye.createdDate"
        <c:if test="${elhaghiye.createdDate==null}">
            value="<%=DateUtil.getCurrentDate()%>"
        </c:if>
        <c:if test="${elhaghiye.createdDate!=null}">
             value="${elhaghiye.createdDate}"
        </c:if>
        />

    </td>
    <td>
        <label>تاریخ اثر:</label>
    </td>
    <td>
        <s:if test="%{darkhastTaghirat!=null}">
            <input type="text" name="elhaghiye.tarikhAsar" id="tAsarEl" style="width:115px"
                   value="${elhaghiye.tarikhAsar}" readonly="readonly"/>
        </s:if>
        <s:elseif test="%{darkhastBazkharid!= null}">
            <c:if test="${tozih}">
                <input type="text" name="elhaghiye.tarikhAsar" id="tAsarEl" style="width:115px"
                       value="${elhaghiye.tarikhAsar}" readonly="readonly"/>
            </c:if>
            <c:if test="${darkhastBazkharid.darkhastType=='BAZKHARID'}">
                <s:if test="showElhaghiye">
                    <input type="text" name="elhaghiye.tarikhAsar" id="tAsarEl" style="width:115px" value="${elhaghiye.tarikhAsar}" readonly="readonly" />
                </s:if>
                <s:else>
                    <input type="text" name="elhaghiye.tarikhAsar" id="tAsarEl" style="width:115px"
                       value="<%=DateUtil.getCurrentDate()%>" class="datePkr validate[required,custom[date]]" onchange="checkTarikhAsar();"/>
                </s:else>
            </c:if>
            <c:if test="${!tozih && darkhastBazkharid.darkhastType !='BAZKHARID'&& darkhastBazkharid.darkhastType !='KHESARAT'}">
                <input type="text" name="elhaghiye.tarikhAsar" id="tAsarEl" style="width:115px"
                       value="${bimename.tarikhShorou}" readonly="readonly"/>
            </c:if>
            <c:if test="${darkhastBazkharid.darkhastType =='KHESARAT'}">
                <input type="text" name="elhaghiye.tarikhAsar" id="tAsarEl" style="width:115px"
                       <%--<c:if test="${elhaghiye.tarikhAsar!=null}">--%>
                            <%--value="${elhaghiye.tarikhAsar}"--%>
                       <%--</c:if>--%>
                       <%--<c:if test="${elhaghiye.tarikhAsar==null}">--%>
                            value="${darkhastBazkharid.currentKhesarat.calcTarikhAsar}"
                       <%--</c:if>--%>
                       class="validate[required,custom[date]]" readonly="readonly"/>
            </c:if>
        </s:elseif>
    </td>
    <td>
        <label>تاریخ اثر کارمزد:</label>
    </td>
    <td>
        <c:if test="${darkhastBazkharid.darkhastType=='TAGHIRKOD'}">
            <input type="text" readonly="readonly" value="${bimename.tarikhShorou}"/>
        </c:if>
        <c:if test="${darkhastBazkharid.darkhastType!='TAGHIRKOD'}">
            <input type="text" readonly="readonly" value="-"/>
        </c:if>
    </td>
</tr>
<tr class="even">
    <td>
        <label>نوع درخواست:</label>
    </td>
    <td>
        <s:if test="%{darkhastTaghirat!= null}">
            <input type="text" readonly="readonly" value="تغییر"/>
        </s:if>
        <s:elseif test="%{darkhastBazkharid!= null}">
            <input type="text" readonly="readonly" value="${darkhastBazkharid.getDarkhstTypeFarsi()}"/>
        </s:elseif>
    </td>
    <td>
        <label>نوع خاص:</label>
    </td>
    <td>
        <s:if test="%{elhaghiye != null && elhaghiye.getElhaghiyeType().toString().equals('KHESARAT')}">
            <input type="text" readonly="readonly" value="${elhaghiye.khesarat.typeFarsi}"/>
        </s:if>
        <s:elseif test="%{darkhastBazkharid != null}">
            <input type="text" readonly="readonly" value="${darkhastBazkharid.getNoeKhasFarsiString()}"/>
        </s:elseif>
        <s:else>
            <input type="text" readonly="readonly" value="تغییرات بیمه نامه"/>
        </s:else>
    </td>
    <td>
        <label>جزئیات:</label>
    </td>
    <td>
        <s:if test="%{darkhastBazkharid != null}">
            <input type="text" name="darkhastTaghirat.joziat" readonly="readonly"
                   value="${darkhastBazkharid.getJoziatFarsiString()}"/>
        </s:if>
        <s:else>
            <input type="text" name="darkhastTaghirat.joziat" value="${darkhastTaghirat.joziat}"/>
        </s:else>
    </td>
</tr>
<tr class="odd">
    <td colspan="1">
        امضا کننده اول:
    </td>
    <td colspan="2">
        <s:if test="showElhaghiye">
            <input type="text" readonly="readonly" id="elhaghiye_emzaKonandeAval" style="width:100px"
                   value="${elhaghiye.emzaKonandeAval.user.firstName} ${elhaghiye.emzaKonandeAval.user.lastName}"/>
            <input type="hidden" name="elhaghiye.emzaKonandeAval.id" id="elhaghiye_emzaKonandeAval_id"
                   value="${elhaghiye.emzaKonandeAval.id}"/>

        </s:if>
        <s:else>
            <input type="text" id="elhaghiye_emzaKonandeAval" style="width:100px"
                   value="${user.firstName} ${user.lastName}"/>
            <input type="hidden" name="elhaghiye.emzaKonandeAval.id" id="elhaghiye_emzaKonandeAval_id"
                   value="${user.emzaKonande.id}"/>
            <input type="button" value="جستجو"
                   onclick="$('#NafareEmza').val('#elhaghiye_emzaKonandeAval');fillEmze();"/>
        </s:else>

    </td>
    <td colspan="1">
        امضا کننده دوم:
    </td>
    <td colspan="2">
        <s:if test="showElhaghiye">
            <input type="text" readonly="readonly" id="elhaghiye_emzaKonandeDovom" style="width:100px"
                   class="validate[required]"
                   value="${elhaghiye.emzaKonandeDovom.user.firstName} ${elhaghiye.emzaKonandeDovom.user.lastName}"/>
            <input type="hidden" name="elhaghiye.emzaKonandeDovom.id" id="elhaghiye_emzaKonandeDovom_id"
                   value="${elhaghiye.emzaKonandeDovom.id}"/>
        </s:if>
        <s:else>
            <input type="text" id="elhaghiye_emzaKonandeDovom" style="width:100px" class="validate[required]"
                   <c:if test="${darkhastBazkharid.darkhastType=='KHESARAT'}">value="${elhaghiye.emzaKonandeDovom.user.firstName} ${elhaghiye.emzaKonandeDovom.user.lastName}"</c:if>/>
            <input type="hidden" name="elhaghiye.emzaKonandeDovom.id" id="elhaghiye_emzaKonandeDovom_id"/>
            <input type="button" value="جستجو"
                   onclick="$('#NafareEmza').val('#elhaghiye_emzaKonandeDovom');fillEmze(); "/>
        </s:else>
    </td>
</tr>
<tr class="even">
    <td colspan="1">
        موضوع الحاقیه:
    </td>
    <s:if test="showElhaghiye">
    <td colspan="5">
        <textarea readonly="readonly" rows="1" cols="130" style="text-align:right;"
                  name="elhaghiye.mozoo">${elhaghiye.mozoo}</textarea>
        </s:if>
        <s:else>
    <td colspan="5">
        <textarea rows="1" cols="130" style="text-align:right;"
                  name="elhaghiye.mozoo">${elhaghiye.mozoo}</textarea>
        </s:else>
    </td>
</tr>
<tr class="odd">
    <td colspan="1">
        متن الحاقیه:
    </td>
    <td colspan="5">
        <s:if test="showElhaghiye">
            <textarea readonly="readonly" rows="5" cols="130" style="text-align:right;"
                      name="elhaghiye.matn">${elhaghiye.matn}</textarea>
        </s:if>
        <s:else>
            <textarea rows="5" cols="130" style="text-align:right;"
                      name="elhaghiye.matn">${elhaghiye.matn}</textarea>
        </s:else>
    </td>
</tr>
<c:if test="${darkhastBazkharid.darkhastType=='EBTAL'}">
    <tr class="even">
    <s:if test="showElhaghiye">
        <td colspan="1">
         هزینه پزشکی:
        </td>
        <td colspan="2">
                <input type="text" value="${elhaghiye.hazinePezeshki}" id="hazinePezeshki" name="elhaghiye.hazinePezeshki" readonly="readonly"/>
        </td>
        <td colspan="1">
            جمع پرداختها:
        </td>
        <td colspan="2">
            <input type="text" value="${elhaghiye.mablaghPardakhtiBimegozar}" id="paymentVal" readonly="readonly" />
        </td>
    </s:if>
    <s:else>
        <td colspan="1">
         جمع پرداختها:
            </td>
            <td colspan="2">
                <input type="text" value="${bimename.jameMabaleghePardakhti}" id="paymentVal" name="elhaghiye.mablaghPardakhtiBimegozar"/>
            </td>
            <td colspan="1">
         هزینه پزشکی:
            </td>
            <td colspan="2">
                <input type="text" id="hazinePezeshki" name="elhaghiye.hazinePezeshki" class="validate[required,custom[long]] digitSeparators"/>
            </td>
        </tr>
    </s:else>
</c:if>
<c:if test="${darkhastBazkharid.darkhastType=='BAZKHARID'|| (darkhastBazkharid.darkhastType=='KHESARAT' && ((darkhastBazkharid.khesaratII!=null && darkhastBazkharid.khesaratI.elhaghiye!=null) || darkhastBazkharid.khesaratI.khesaratType=='OMR' || darkhastBazkharid.khesaratI.khesaratType=='HADESE')) }">
    <tr id="andukhtejat_tr" class="even">
            <td colspan="1">
         اندوخته قطعی:
        </td>
        <td colspan="2">
                <input type="text" value="${darkhastBazkharid.andukhteGhatie}" id="sodor_andukhte" name="darkhastBazkharid.andukhteGhatie" readonly="readonly"/>
        </td>
        <td colspan="1">
            ارزش بازخریدی قطعی:
        </td>
        <td colspan="2">
            <input type="text"
                <%--<c:if test="${darkhastBazkharid.arzeshBazkharid!=null && darkhastBazkharid.arzeshBazkharid<0}">--%>
        <%--<%--%>
            <%--Long arzeshBazkharidi= request.getAttribute("darkhastBazkharid.arzeshBazkharid")!=null?Long.parseLong(((String)request.getAttribute("darkhastBazkharid.arzeshBazkharid")).replaceAll(",","").trim()):0;--%>
            <%--if(arzeshBazkharidi<=0)--%>
            <%--{--%>
        <%--%>--%>
                    <%--value="0"--%>
                <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
        <%--<%--%>
            <%--}--%>
            <%--else--%>
            <%--{--%>
        <%--%>--%>
                <%--&lt;%&ndash;<c:if test="${darkhastBazkharid.arzeshBazkharid!=null && darkhastBazkharid.arzeshBazkharid>0}">&ndash;%&gt;--%>
                    <%--value="${darkhastBazkharid.arzeshBazkharid}"--%>
        <%--<%--%>
            <%--}--%>
        <%--%>--%>
                <%--</c:if>--%>
                   id="sodor_arzeshBazkharid" name="darkhastBazkharid.arzeshBazkharid" readonly="readonly"/>
        </td>
</c:if>
</table>
<br/><br/>
<td>
<c:if test="${darkhastBazkharid.darkhastType=='TAGHIRKOD'}">
    <s:if test="%{darkhastBazkharid.elhaghiye!=null}">
        <input type="button" onclick="chapFinalA5();" value="چاپ A5" style="float:right;margin-right:5px;"/>   <input type="text" value="${elhaghiye.id}">
        <input type="button" onclick="chapFinalA4();" value="چاپ A4" style="float:right;margin-right:5px;"/>
    </s:if>
    <s:else>
        <c:if test="${darkhastBazkharid.darkhastType=='TAGHIRKOD'}">
            <input type="button"
                   onclick="window.location='/hazfDarkhast.action?darkhastBazkharid.id=${darkhastBazkharid.id}'"
                   value="حــذف" style="float:right;margin-right:5px;"/>
        </c:if>
        <input type="button" id="btnSodoor" onclick="sodoureElhaghie();" value="صدور"
               style="float:right;margin-right:5px;"/>
        <input type="button" onclick="chapAzmayeshiA5();" value="چاپ آزمایشی A5" style="float:right;margin-right:5px;"/>
        <input type="button" onclick="chapAzmayeshiA4();" value="چاپ آزمایشی A4" style="float:right;margin-right:5px;"/>
    </s:else>
</c:if>
<c:if test="${darkhastBazkharid.darkhastType!='TAGHIRKOD'}">
    <s:if test="showElhaghiye">
        <input type="button" onclick="chapFinalA5();" value="چاپ A5" style="float:right;margin-right:5px;"/>
        <input type="button" onclick="chapFinalA4();" value="چاپ A4" style="float:right;margin-right:5px;"/>
    </s:if>
    <s:else>
        <c:if test="${darkhastBazkharid.darkhastType=='TAGHIRKOD'}">
            <input type="button"
                   onclick="window.location='/hazfDarkhast.action?darkhastBazkharid.id=${darkhastBazkharid.id}'"
                   value="حــذف" style="float:right;margin-right:5px;"/>
            </c:if>
        <input type="button" id="btnSodoor" onclick="sodoureElhaghie();" value="صدور"
               style="float:right;margin-right:5px;"/>
        <c:if test="${darkhastBazkharid.darkhastType!='KHESARAT'}">
            <input type="button" onclick="chapAzmayeshiA5();" value="چاپ آزمایشی A5" style="float:right;margin-right:5px;"/>
            <input type="button" onclick="chapAzmayeshiA4();" value="چاپ آزمایشی A4" style="float:right;margin-right:5px;"/>
        </c:if>
    </s:else>
</c:if>
</td>
</form>

<script type="text/javascript">
    function sodoureElhaghie() {
        if
        (
            $('#elhaghiye_emzaKonandeDovom_id').val()=='' || $('#elhaghiye_emzaKonandeDovom_id').val()==null ||
            $('#elhaghiye_emzaKonandeAval_id').val()=='' || $('#elhaghiye_emzaKonandeAval_id').val()==null
        )
        {
            alertMessage("امضا کننده ها را مشخص نمایید.");
        }
        else
        {
            <s:if test="%{isTozih()}">
            soodooreElhaghiyeTozih();
            return;
            </s:if>
            <s:else>
        <c:if test="${darkhastTaghirat != null}">
        if (isNaN($('#elhaghie_mablagh').val().replace(/\,/g, '') / 1)) {
            alertMessage("مبلغ الحاقیه معتبر نمی باشد.");
            return;
        }
        var validated = true;
        $('.taeedElhaghieAshkhas').each(function (i, obj) {
            if($(this).val() == 'false') {
                validated = false;
            }
        });
        if (!validated) {
            alertMessage("لطفا تمامی بیمه نامه های الحاقیه را تایید کنید.");
            return;
        }
        </c:if>
        <%--<c:if test="${darkhastBazkharid.state.id==652}">--%>
//        $("#dakhastTransitionSelector").val(652);
//        submitTransitionForDarkhast();
        <%--</c:if>--%>

        <c:if test="${darkhastBazkharid.darkhastType=='KHESARAT'}">
            <c:if test="${darkhastBazkharid.state.id==657 || darkhastBazkharid.state.id==658 || darkhastBazkharid.state.id==654}">
                $('#createSodurElhaghie').submit();
            </c:if>
        </c:if>
        <c:if test="${darkhastBazkharid.darkhastType!='KHESARAT'}">
            <c:if test="${darkhastTaghirat.state.id==9080}">
            $("#dakhastTransitionSelector").val(9021);
//            submitTransitionForDarkhast();
            </c:if>
            <c:if test="${darkhastTaghirat.state.id==9160}">
            $("#dakhastTransitionSelector").val(9046);
//            submitTransitionForDarkhast();
            </c:if>
            <c:if test="${darkhastTaghirat.state.id==9030}">
            $("#dakhastTransitionSelector").val(9053);
//            submitTransitionForDarkhast();
            </c:if>
            <c:if test="${darkhastTaghirat.state.id==9140}">
            $("#dakhastTransitionSelector").val(9039);
//            submitTransitionForDarkhast();
            </c:if>
            <c:if test="${darkhastTaghirat.state.id==9050}">
            $("#dakhastTransitionSelector").val(9015);
//            submitTransitionForDarkhast();
            </c:if>
            <c:if test="${darkhastBazkharid.state.id==1100}">
            $("#dakhastTransitionSelector").val(1270);
            </c:if>
            <c:if test="${darkhastBazkharid.state.id==1200}">
            $("#dakhastTransitionSelector").val(1200);
            </c:if>
            <c:if test="${darkhastBazkharid.state.id==1450}">
            $("#dakhastTransitionSelector").val(1330);
            </c:if>
            <c:if test="${darkhastBazkharid.state.id==1020}">
            $("#dakhastTransitionSelector").val(1165);
            </c:if>

            openDialogBoxAndSubmitTo("sodurbimlgmsg", "createSodurElhaghie");
            </c:if>
        </s:else>
        }
    }

    function soodooreElhaghiyeTozih() {
        /*$('#pdarkhast_popup').dialog({
         modal:true,
         width:300,
         resizable:false,
         closeText:"",
         title:"صدور الحاقيه توضيح",
         buttons:{
         "بستن":function () {
         $(this).dialog("close");
         return false;
         },
         "انجام":function() {
         $(this).dialog("close");
         $("#createSodurElhaghie").submit();
         }
         }
         });*/
        $("#createSodurElhaghie").submit();
        return true;
    }

    function chapAzmayeshiElhaghie(A5OrA4)
    {
        var strParameters='';
        <c:if test="${darkhastTaghirat!=null}">
            <c:forEach var="row" items="${listBimenameTaghirVM}" varStatus="i">
                strParameters += 'darkhastTaghirat.darkhast.elhaghiyeList[${i.index+1}].mozoo=' + document.getElementById("elhaghie_info_subject${i.index+1}").value +
                                '&darkhastTaghirat.darkhast.elhaghiyeList[${i.index+1}].matn=' + document.getElementById("elhaghie_info_desc${i.index+1}").value +
                                '&darkhastTaghirat.darkhast.elhaghiyeList[${i.index+1}].tarikhAsar=' + document.getElementById("elhaghie_info_date${i.index+1}").value;

            if ($('#mod_elhaghie_info_new'+${i.index+1}).is(':checked'))
            {
                strParameters += '&infoElhaghie[${i.index+1}]=' + document.getElementById("mod_elhaghie_info_new${i.index+1}").value;
            }
            else
            {
                strParameters += '&infoElhaghie[${i.index+1}]=' + document.getElementById("mod_elhaghie_info_current${i.index+1}").value;
            }
            strParameters+="&";
            </c:forEach>
        </c:if>

        window.open("/printElhaghieAzmayeshi" + A5OrA4 + ".action?"+ strParameters + $('#createSodurElhaghie').serialize(), "_blank");
    }
    function chapAzmayeshiA5() {
        chapAzmayeshiElhaghie('A5');
    }

    function chapAzmayeshiA4() {
        chapAzmayeshiElhaghie('A4');
    }

    function chapFinalA5() {
        window.open("/printElhaghieFinalA5.action?" + $('#createSodurElhaghie').serialize(), "_blank");
    }

    function chapFinalA4() {
        window.open("/printElhaghieFinalA4.action?" + $('#createSodurElhaghie').serialize(), "_blank");
    }

    function searchEmaz() {
        var emzaName = $('#emzaName').val();
        var emzaPersonalCode = $('#emzaPersonalCode').val();


        $.post("/findEmzaKonande.action?" + "elhaghieReq=" + true + "&user.firstName=" + emzaName + "&user.personalCode=" + emzaPersonalCode + "&pishnehad.id=" + "${pishnehad.id}", function (msg) {
            $('#searchResualt').html(msg);
        });

        fillEmze();
    }
<s:if test="!showElhaghiye">
    $(document).ready(function () {
        <c:if test="${darkhastTaghirat!=null}">
            <c:if test="${darkhastTaghirat.oldPishnehad.bimename.haveKhesaratAmrazOrNaghs}">
                alertMessage("بيمه نامه داراي خسارت امراض يا نقص عضو مي باشد");
            </c:if>
        </c:if>
        $('#emzaName').val('');
        $('#emzaPersonalCode').val('');

        $('#tblEmza').dialog({
            modal: true, resizable: false, autoOpen: false,
            width: 700, maxHeight: 500, minHeight: 100,
            title: "جستحو شخص امضا کننده"});

        <%--<c:if test="${darkhastBazkharid.darkhastType=='KHESARAT'}">--%>
            <%--<c:if test="${darkhastBazkharid.khesaratII!=null}">--%>
                <%--<c:if test="${darkhastBazkharid.khesaratI.elhaghiye==null}">--%>
                    <%--$('#btnSodoor').val('صدور الحاق?ه خسارت اول');--%>
                <%--</c:if>--%>
                <%--<c:if test="${darkhastBazkharid.khesaratI.elhaghiye!=null && darkhastBazkharid.khesaratII.elhaghiye==null}">--%>
                    <%--$('#btnSodoor').val('صدور الحاق?ه خسارت دوم');--%>
                <%--</c:if>--%>
                <%--<c:if test="${darkhastBazkharid.khesaratII.elhaghiye!=null}">--%>
                    <%--$("#dakhastTransitionSelector option[value='614']").remove();--%>
                    <%--$("#dakhastTransitionSelector option[value='690']").remove();--%>
                    <%--$("#dakhastTransitionSelector option[value='622']").remove();--%>
                    <%--alertMessage("<p style='color: #ff0000;'>" + "الحاق?ه مربوط به خسارات ا?ن درخواست صادر شده است،هم اکنون م? توان?د نسبت به ثبت وضع?ت منتظر تا??د نها?? پرونده خسارت اقدام نما??د." + "</p>");--%>
                    <%--$('#btnSodoor').attr("disabled", 'disabled');--%>
                    <%--$('#btnSodoor').hide();--%>
                    <%--<c:if test="${darkhastBazkharid.state.id==654}">--%>
                    <%--$('#dakhastTransitionSelector').append($("<option></option>").attr("value", '616').text("منتظر تاييد نهايي پرونده خسارت"));--%>
            <%--&lt;%&ndash;                        $('#dakhastTransitionSelector').val(616);&ndash;%&gt;--%>
                    <%--</c:if>--%>
                    <%--&lt;%&ndash;<c:if test="${darkhastBazkharid.state.id==658}">&ndash;%&gt;--%>
<%--//                    $('#dakhastTransitionSelector').append($("<option></option>").attr("value", '626').text("منتظر تاييد نهايي پرونده خسارت"));--%>
                    <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                    <%--<c:if test="${darkhastBazkharid.state.id==657}">--%>
                    <%--$('#send_request_btn').addClass('noAnyDisable');--%>
                    <%--$('#dakhastTransitionSelector').addClass('noAnyDisable');--%>
                    <%--$('#dakhastTransitionSelector').append($("<option></option>").attr("value", '636').text("منتظر تاييد نهايي پرونده خسارت"));--%>
            <%--&lt;%&ndash;                        $('#dakhastTransitionSelector').val(636);&ndash;%&gt;--%>
                    <%--</c:if>--%>
                <%--</c:if>--%>
                <%--&lt;%&ndash;<c:if test="${darkhastBazkharid.darkhast.elhaghiyeList==null || fn:length(darkhastBazkharid.darkhast.elhaghiyeList)<2}">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;&ndash;%&gt;--%>
                <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
            <%--</c:if>--%>
            <%--<c:if test="${darkhastBazkharid.khesaratII==null}">--%>
                <%--<c:if test="${darkhastBazkharid.khesaratI.elhaghiye!=null}">--%>
                    <%--$("#dakhastTransitionSelector option[value='614']").remove();--%>
                    <%--$("#dakhastTransitionSelector option[value='690']").remove();--%>
                    <%--$("#dakhastTransitionSelector option[value='622']").remove();--%>
                    <%--alertMessage("<p style='color: #ff0000;'>" + "الحاق?ه مربوط به خسارات ا?ن درخواست صادر شده است،هم اکنون م? توان?د نسبت به ثبت وضع?ت منتظر تا??د نها?? پرونده خسارت اقدام نما??د." + "</p>");--%>
                    <%--$('#btnSodoor').attr("disabled", 'disabled') ;--%>
                    <%--$('#btnSodoor').hide();--%>
                    <%--<c:if test="${darkhastBazkharid.state.id==654}">--%>
                        <%--$('#dakhastTransitionSelector').append($("<option></option>").attr("value", '616').text("منتظر تاييد نهايي پرونده خسارت"));--%>
<%--//                        $('#dakhastTransitionSelector').val(616);--%>
                    <%--</c:if>--%>
                    <%--&lt;%&ndash;<c:if test="${darkhastBazkharid.state.id==658}">&ndash;%&gt;--%>
<%--//                        $('#dakhastTransitionSelector').append($("<option></option>").attr("value", '626').text("منتظر تاييد نهايي پرونده خسارت"));--%>
                    <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                    <%--<c:if test="${darkhastBazkharid.state.id==657}">--%>
                        <%--$('#send_request_btn').addClass('noAnyDisable');--%>
                        <%--$('#dakhastTransitionSelector').addClass('noAnyDisable');--%>
                        <%--$('#dakhastTransitionSelector').append($("<option></option>").attr("value", '636').text("منتظر تاييد نهايي پرونده خسارت"));--%>
<%--//                        $('#dakhastTransitionSelector').val(636);--%>
                    <%--</c:if>--%>
                <%--</c:if>--%>
            <%--</c:if>--%>
        <%--</c:if>--%>
    });
</s:if>
    function fillEmze() {
        $('#tblEmza').dialog('open');
    }

    function selectRow(id, fn, ln) {
        var ctrlId = $('#NafareEmza').val();
        $(ctrlId).val(fn + " " + ln);
        $(ctrlId + '_id').val(id);

        hideEmzaModal();
    }

    function hideEmzaModal() {
        $('#emzaName').val('');
        $('#emzaPersonalCode').val('');
        $('#tblEmza').dialog('close');
        $('#searchResualt').html('');
    }
    function chapAzmayeshi() {
        window.open("/printBimename?pishnehadReport.pishnehad.id=${pishnehad.id}&" + $('#createSodurBimename').serialize(), "_blank");
    }
    function updateArzeshVaAndukhte() {
        <s:if test="!showElhaghiye">
        $.post('/calculateAndukhteEtcAjaxly.action?bimename.id=${darkhastBazkharid.bimename.id}&tarikhMabna='+$('#tAsarEl').val(),function (msg) {
                   $('#sodor_andukhte').val($(msg).filter('#ajaxcalc_andukhteGhati').val());
                   $('#sodor_arzeshBazkharid').val($(msg).filter('#ajaxcalc_arzeshGhati').val()<0?0: $(msg).filter('#ajaxcalc_arzeshGhati').val());
                });
        </s:if>
        <s:else>
            $('#sodor_arzeshBazkharid').val('${darkhastBazkharid.arzeshBazkharid}');
        </s:else>
    }

    function checkTarikhAsar()
    {
        var tarikhAsar=$('#tAsarEl').val();
        var tarikhSodurBime= '${darkhastBazkharid.bimename.tarikhSodour}';
        if(tarikhAsar.match("\\d{4}/\\d{2}/\\d{2}"))
        {
            if(parseInt(tarikhAsar.split('/').join('')) < parseInt(tarikhSodurBime.split('/').join('')))
            {
                alertMessage( "تاریخ اثر نباید قبل از "+ tarikhSodurBime + " باشد" );
                $('#tAsarEl').val('');
            }
            else
            {
                updateArzeshVaAndukhte();
            }
        }
        else if(tarikhAsar=='')
        {

        }
        else
        {
            alertMessage("فرمت وارد شده صحیح نیست")
        }
    }

<s:if test="showElhaghiye">
    $(document).ready
    (
        function ()
        {
            checkTarikhAsar();
            <c:if test="${elhaghiye.elhaghiyeType=='KHESARAT' && (elhaghiye.khesarat.khesaratType=='MOAFIAT'||elhaghiye.khesarat.khesaratType=='AMRAZ'||elhaghiye.khesarat.khesaratType=='NAGHSOZV')}">
                $('#andukhtejat_tr').hide();
            </c:if>

        }
    );
</s:if>
    <c:if test="${darkhastBazkharid.darkhastType=='KHESARAT' && (darkhastBazkharid.khesaratII!=null || darkhastBazkharid.khesaratI.khesaratType=='OMR' ||darkhastBazkharid.khesaratI.khesaratType=='HADESE')}">
        $(document).ready(function (){updateArzeshVaAndukhte();});
    </c:if>
</script>
