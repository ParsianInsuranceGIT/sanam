<%@ page import="java.util.List" %>
<%@ page import="com.bitarts.parsian.model.constantItems.Tarh" %>
<%@ page import="com.bitarts.parsian.model.bimename.Gharardad" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/josteju/searchCity.jsp" %>
<form action="/sabtGharardad" method="post" enctype="multipart/form-data">
<s:hidden key="gharardad.id" label="" theme="simple"/>
<s:hidden name="selectedMenu" value="1"/>
<%  List<Tarh> tarhs = (List<Tarh>) request.getAttribute("tarhs");
    Gharardad gharardadRun = (Gharardad) request.getAttribute("gharardad"); %>
<div>
    <table style="margin: 0 auto;">
        <tr>
            <td>شماره قرارداد:</td>
            <td><s:textfield readonly="true" disabled="true" key="gharardad.shomare" label="" theme="simple"/></td>
            <td>تاریخ قرارداد:</td>
            <td><s:textfield readonly="true" disabled="true" key="gharardad.createdDate" label="" theme="simple"/></td>
            <td>نوع طرح:</td>
            <td><select id="gharardad_nameTarh" name="gharardad.tarh.id">
                    <%for (Tarh tarh : tarhs) { %>
                    <option <%if(tarh.getId() == null){%>value=""<%}if(tarh.getId()!=null){%>value="<%=tarh.getId()%>" <%} if ((gharardadRun != null && gharardadRun.getTarh() != null && gharardadRun.getTarh().getId().equals(tarh.getId()))
                            || ((gharardadRun == null || gharardadRun.getTarh() == null)) && tarh.getId() == null)  { %>
                            selected="selected" <% } %> >
                        <%=tarh.getName()%>
                    </option>
                    <%}%>
                </select></td>
        </tr>
    </table>
</div>
<div id="etelaateBimegar">
    <br class="clear"/>

    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
        اطلاعات بیمه گر
    </p>
    <table style="margin: 0 auto;">
        <tr>
            <td>نام شرکت:</td>
            <td><input type="text" readonly="readonly" value="شرکت بیمه پارسیان"></td>
            <td>شماره ثبت:</td>
            <td><input type="text" readonly="readonly" value="200722"></td>
        </tr>
        <tr>
            <td>تاریخ ثبت:</td>
            <td><input type="text" readonly="readonly" value="1382/01/09"></td>
            <td>شماره روزنامه:</td>
            <td><input type="text" readonly="readonly" value="16929"></td>
        </tr>
        <tr>
            <td>نشانی:</td>
            <td colspan="3"><input type="text" readonly="readonly"
                                   value=" تهران - خیابان آفریقا - خیابان صانعی شماره ۱۵" style="width: 380px"></td>
        </tr>
        <tr>
            <td>شماره تلفن:</td>
            <td><input type="text" readonly="readonly" value="88642700-30"></td>
            <td>فکس:</td>
            <td><input type="text" readonly="readonly" value=""></td>
        </tr>
    </table>
</div>
<div id="etelaateSherkat">
    <br class="clear"/>

    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
        اطلاعات شرکت
    </p>
    <table style="margin: 0 auto;">
        <tr>
            <td>نام شرکت:</td>
            <td><s:textfield key="gharardad.nameSherkat" cssClass="disableIfNotGranted" label="" theme="simple"/></td>
            <td>شماره ثبت:</td>
            <td><s:textfield key="gharardad.shomareSabt" cssClass="disableIfNotGranted" label="" theme="simple"/></td>
        </tr>
        <tr>
            <td>تاریخ ثبت:</td>
            <td><s:textfield key="gharardad.tarikhSabt" cssClass="disableIfNotGranted datePkr" label="" theme="simple"/></td>
            <td>شماره روزنامه:</td>
            <td><s:textfield key="gharardad.shomareRuzname" cssClass="disableIfNotGranted" label="" theme="simple"/></td>
        </tr>
        <tr>
            <td>نشانی:</td>
            <td colspan="3"><s:textfield key="gharardad.neshani" cssClass="disableIfNotGranted" label="" theme="simple" cssStyle="width: 380px" id="gh_neshani"/></td>
        </tr>
        <tr>
            <td>شهر:</td>
            <td colspan="3">
                <input type="text" name="gharardad.shahr.cityName" id="shahr" value="${gharardad.shahr.cityName}" readonly="readonly" disabled="disabled"/>
                <input type="hidden" name="gharardad.shahr.cityId" id="shahrId" value="${gharardad.shahr.cityId}"/>
                <input type="text" name="gharardad.ostan.cityName" id="ostan" value="${gharardad.ostan.cityName}" readonly="readonly"  disabled="disabled"/>
                <input type="hidden" name="gharardad.ostan.cityId" id="ostanId" value="${gharardad.ostan.cityId}"/>
                <input type="button" id="btnOstanShahrSelector" class="disableIfNotGranted" value="انتخاب"
                       onclick="fillShahrOstan('shahrId','shahr','ostanId','ostan','gh_telphone')"
                       style="margin:0 0px; position: absolute;"/>
            </td>
        </tr>
        <tr>
            <td>شماره تلفن:</td>
            <td><s:textfield key="gharardad.telphone" cssClass="disableIfNotGranted" id="gh_telphone" label="" theme="simple"/></td>
            <td>فکس:</td>
            <td><s:textfield key="gharardad.fax" cssClass="disableIfNotGranted" label="" theme="simple"/></td>
        </tr>
    </table>
</div>
<div id="etelaatebanki">
    <br class="clear"/>

    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
        اطلاعات بانکی
    </p>
    <table class="mystrippedtable" dir="rtl" cellpadding="2px" cellspacing="0px"
           style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <tr class="odd">
            <td>
                <label>شماره حساب:</label>
            </td>
            <td>
                <input type="text" class="disableIfNotGranted" name="gharardad.hesab_shomare"
                       value="<c:out value='${gharardad.hesab_shomare}'/>"/>
            </td>
            <td>
                <label>به شماره شبای IR:</label>
            </td>
            <td>
                <input type="text" name="gharardad.hesab_sheba" id="gh_hesab_shomare" class="disableIfNotGranted validate[required,custom[shaba]]"
                       value="<c:out value='${gharardad.hesab_sheba}'/>"/>
            </td>
            <td>
                <label>نزد بانک:</label>
            </td>
            <td>
                <input type="text" class="disableIfNotGranted" name="gharardad.hesab_bank"
                       value="<c:out value='${gharardad.hesab_bank}'/>"/>
            </td>
        </tr>
        <tr class="odd">
            <td>
                <label>شعبه:</label>
            </td>
            <td>
                <input type="text" class="disableIfNotGranted" name="gharardad.hesab_shobe"
                       value="<c:out value='${gharardad.hesab_shobe}'/>"/>
            </td>
            <td>
                <label>کد شعبه:</label>
            </td>
            <td>
                <input type="text" class="disableIfNotGranted" name="gharardad.hesab_kodShobe"
                       value="<c:out value='${gharardad.hesab_kodShobe}'/>"/>
            </td>
            <td>
                <label>به نام آقای/خانم:</label>
            </td>
            <td>
                <input type="text" class="disableIfNotGranted" name="gharardad.hesab_name"
                       value="<c:out value='${gharardad.hesab_name}'/>"/>
            </td>
        </tr>
        <tr class="odd">
            <td colspan="6">
                <label>درج شماره حساب بانکی ایران ( شبا ) الزامی می باشد.</label>
            </td>
        </tr>
        <%--<tr>--%>
        <%--<td colspan="6">--%>
        <%--<label style="font-style:italic;color:red;font-weight:bold; font-size:10px;">--%>
        <%--*مسئولیت هر گونه اشتباه در اعلام شماره حساب، نام صاحب حساب و سایر مشخصات حساب بانکی جهت واریز--%>
        <%--مبلغ فوق الذکر، متوجه بیمه گذار خواهد بود.--%>
        <%--</label>--%>
        <%--</td>--%>
        <%--</tr>--%>
    </table>
</div>
<div>
    <br class="clear"/>

    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
        اطلاعات نماینده
    </p>
    <table style="margin: 0 auto;width:300px">
        <tr>
            <td>نام نماینده:</td>
            <td colspan="3">
                <span class="help ui-icon ui-icon-search disableIfNotGranted" onclick="if(!$(this).attr('disabled'))fillNamayandegi('namayandeId','namayandeName','namayandeName');" style="float:left;" title="جستجو"></span>
                <s:textfield theme="simple" cssClass="disableIfNotGranted" id="namayandeName" name="gharardad.namayande.name" cssStyle="float:left"/>
                <s:hidden id="namayandeId" name="gharardad.namayande.id"/>
                <%--<s:select list="namayandeList" listKey="id" label="" theme="simple" key="namayandeName" id="namayandeName"--%>
                <%--listValue="name_kod" cssStyle="width: 400px"/>--%>
            </td>

        </tr>
        <%--        <tr>
            <td>نشانی:</td>
            <td colspan="3"><s:textfield theme="simple" label="" key="" </td>

        </tr>
        <tr>
            <td>شماره تلفن:</td>
            <td></td>
            <td>فکس:</td>
            <td></td>
        </tr>--%>
    </table>
</div>
<div id="uploadFile">
    <br class="clear"/>

    <p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
        آپلود فایل
    </p>
    <c:if test="${gharardad != null && gharardad.fileId != null}">
        <div>${gharardad.fileId}</div>
    </c:if>
    <table style="margin: 0 auto;">
        <tr>
            <td>آپلود فایل pdf یا word:</td>
            <td><s:file name="uploadFile" cssClass="disableIfNotGranted" theme="simple"/></td>
        </tr>
        <tr>
            <td>آپلود فایل اسکن شده فيش/چك پرداختی:</td>
            <td><s:file name="uploadFileFish" cssClass="disableIfNotGranted" theme="simple"/></td>
        </tr>
        <tr>
            <td>توضیحات:</td>
            <td><s:textarea key="tozih" cssClass="disableIfNotGranted" theme="simple" label="" cols="30"/></td>
        </tr>
        <c:choose>
            <c:when test="${gharardad == null || gharardad.userCreator.id == null || user.id==gharardad.userCreator.id}">
                <tr>
                    <td colspan="2">
                        <input type="submit" value="ثبت">
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                  <script type="text/javascript">
                        $(document).ready(function () {
                            $('.disableIfNotGranted').attr('readonly', 'readonly')
                            $('.disableIfNotGranted').attr('disabled', true)
                        });
                    </script>
            </c:otherwise>
        </c:choose>
    </table>
</div>
</form>
<%@include file="/jsp/josteju/searchNamayandegi.jsp" %>