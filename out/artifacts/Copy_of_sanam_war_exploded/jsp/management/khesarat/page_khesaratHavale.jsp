<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    <%--$(document).ready--%>
    <%--(--%>
        <%--function ()--%>
        <%--{--%>
            <%--<c:if test="${darkhastBazkharid.khesaratI!=null && (darkhastBazkharid.khesaratI.havaleList == null || fn:length(darkhastBazkharid.khesaratI.havaleList)==0)}">--%>
                <%--$('#send_request_btn').hide();--%>
                <%--$('#dakhastTransitionSelector').hide();--%>
            <%--</c:if>--%>
            <%--<c:if test="${darkhastBazkharid.khesaratII!=null && (darkhastBazkharid.khesaratII.havaleList == null || fn:length(darkhastBazkharid.khesaratII.havaleList)==0)}">--%>
                <%--$('#send_request_btn').hide();--%>
                <%--$('#dakhastTransitionSelector').hide();--%>
            <%--</c:if>--%>

        <%--}--%>
    <%--)--%>
    function changeType()
    {
        if($('#add_type_radio_ck').is(':checked'))
            $('#shaba_div').hide();
        if($('#add_type_radio_ach').is(':checked'))
            $('#shaba_div').show();
    }
    function checkAmount()
    {
        var khRemaining = 0;
        if ($('#kh_num').val() == 'I')
        {
            khRemaining = '${darkhastBazkharid.khesaratI.remainingAmount}';
        }
        else if ($('#kh_num').val() == 'II')
        {
            khRemaining = '${darkhastBazkharid.khesaratII.remainingAmount}';
        }

        if (parseInt($('#havale_amount').val().replace(new RegExp(",", "gm"), "")) > parseInt(khRemaining.replace(new RegExp(",", "gm"), "")))
        {
            $('#havale_amount').val(khRemaining);
        }

    }
    function showHavaleDialog(arg,havale_id,name,family,amount,type,shomareShaba,codeMelli)
    {
        $('#kh_num').val(arg);
        if (havale_id != '')
        {
            $('#amount_havale_td').hide();

            $('#shomare_shaba').val(shomareShaba);

            $('#add_code_melli').val(codeMelli);

            $('#add_name').val(name);

            $('#add_family').val(family);

            if(type=='CHECK')
            {
                $('#add_type_radio_ach').remove('checked').change();
                $('#add_type_radio_ck').attr('checked', true).change();
            }
            else
            {
                $('#add_type_radio_ck').remove('checked').change();
                $('#add_type_radio_ach').attr('checked', true).change();
            }

        }
        else
        {
            $('#amount_havale_td').show();
            $('#shomare_shaba').val('');
            $('#add_code_melli').val('');
            $('#add_name').val('');
            $('#add_family').val('');
        }
        $('#kh_h_id').val(havale_id);
        $.validationEngine.closePrompt('.formError', true);
        dilg = $('#havale_dlg_div').dialog({
            modal: true,
            width: 940,
            resizable: false,
            closeText: "",
            title: "ایجاد حواله",
            close: close,
            buttons: {
                "ثبت": function ()
                {
                    if((havale_id == '') && ($('#havale_amount').val()==null || $('#havale_amount').val()=='' || parseInt($('#havale_amount').val().replace(new RegExp(",", "gm"), ""))<=0))
                    {
                        alertMessage("لطفا مبلغ صحیح حواله را وارد کنید.");
                    }
                    else if($('#add_code_melli').val()=='' || $('#add_name').val()=='' || $('#add_family').val()=='' || $('#add_code_melli').val()==null || $('#add_name').val()==null || $('#add_family').val()==null)
                    {
                        alertMessage("تمام اطلاعات مربوطه را وارد نمایید.");
                    }
                    else if($('#add_type_radio_ach').is(':checked') && ($('#shomare_shaba').val()=='' || $('#shomare_shaba').val()==null) )
                    {
                        alertMessage("در صورتی که قصد پرداخت از طریق ACH دارید شماره شبا الزامی است.")    ;
                    }
                    else
                    {
                        if (arg == 'I')
                            $('#kh_id').val('${darkhastBazkharid.khesaratI.id}');
                        else
                            $('#kh_id').val('${darkhastBazkharid.khesaratII.id}');

                        if ($.validationEngine.submitValidation($('#havale_dlg')) === false)
                        {
                            $('#havale_dlg').submit();
                        }
                    }
                },
                "انصراف": function ()
                {
                    $(this).dialog("close");
                }
            }
        });
    }

</script>
<center>
    <%--<s:actionerror/>--%>
    <%--<s:actionmessage/>--%>
    <table class="jtable resultDets">
        <tr dir="rtl">
            <th>
               *
            </th>
            <th>
                نوع خسارت
            </th>
            <th>
                مبلغ خسارت
            </th>
            <th>
                نام و نام خانوادگی
            </th>
            <th>
                کد ملی / شماره شناسایی
            </th>
            <th>
                درصد سهم
            </th>
            <th>
                          مبلغ حواله (ریال)
            </th>
            <th>
                          نوع حواله
            </th>
            <th>عملیات</th>
            <c:if test="${darkhastBazkharid.state.id!=656}">
                <th>افزودن حواله</th>
            </c:if>
        </tr>

        <c:if test="${darkhastBazkharid.khesaratI!=null && (darkhastBazkharid.khesaratI.havaleList == null || fn:length(darkhastBazkharid.khesaratI.havaleList)==0)}">
            <tr>
                <th >خسارت اول</th>
                <th>${darkhastBazkharid.khesaratI.typeFarsi}</th>
                <th>${darkhastBazkharid.khesaratI.amountTaidShode}</th>

                <td colspan="6"></td>
                <td>
                    <c:if test="${darkhastBazkharid.khesaratI.showAddBtn}">
                        <input type="button" value="+"
                               onclick="showHavaleDialog('I','','','','','','','');">
                    </c:if>
                </td>
            </tr>
        </c:if>
        <s:iterator  id="h" value="darkhastBazkharid.khesaratI.havaleList" status="stat">
            <tr>
                <s:if test="%{#stat.index==0}">
                    <th rowspan="${fn:length(darkhastBazkharid.khesaratI.havaleList)}">خسارت اول</th>
                    <th rowspan="${fn:length(darkhastBazkharid.khesaratI.havaleList)}">${darkhastBazkharid.khesaratI.typeFarsi}</th>
                    <th rowspan="${fn:length(darkhastBazkharid.khesaratI.havaleList)}">${darkhastBazkharid.khesaratI.amountTaidShode}</th>
                </s:if>
                <td><s:property value="#h.getFullName()"/></td>
                <td><s:property value="#h.getCodeMelli()"/></td>
                <td><s:property value="#h.getPercent()"/></td>
                <td><s:property value="#h.getAmountHavale()"/></td>
                <td><s:property value="#h.getTypeFa()"/></td>
                <td>
                        <%--<s:if test="elamBeMaliShode==false">--%>
                        <%--<input type="button" value="ویرایش"--%>
                        <%--onclick="window.location='/addKhesaratHavale?khesarat.id=<s:property value="khesarat.id"/>&khesaratHavale.id=<s:property value="id"/>'">--%>
                        <c:if test="${darkhastBazkharid.state.id!=656}">
                    <span class="ui-icon ui-icon-trash" title="حذف"    style="float: left;"
                           onclick="confirmMessage('آیا از حذف حواله اطمینان دارید؟',
                                   '', function(){window.location='/delKhesaratHavale?darkhastBazkharid.id=${darkhastBazkharid.id}&khesarat.id=<s:property value="#h.getKhesarat().getId()"/>&khesaratHavale.id=<s:property value="#h.getId()"/>';})">&nbsp;</span>
                    <span class="ui-icon ui-icon-pencil" title="ویرایش" style="float: left;"
                          onclick="showHavaleDialog('I',
                                  '<s:property value="#h.getId()"/>',
                                  '<s:property value="#h.getName()"/>',
                                  '<s:property value="#h.getFamily()"/>',
                                  '<s:property value="#h.getAmountHavale()"/>',
                                  '<s:property value="#h.getType()"/>',
                                  '<s:property value="#h.getShomareShaba()"/>',
                                  '<s:property value="#h.getCodeMelli()"/>'
                                  );">&nbsp;</span>
                        </c:if>
                    <span class="ui-icon ui-icon-print" title="پرینت" style="float: left;"
                           onclick="window.open('/printeHavaleKhesarat?pishnehadReport.darkhastBazkharid.id=${darkhastBazkharid.id}&pishnehadReport.khesarat.id=<s:property value="#h.getKhesarat().getId()"/>&pishnehadReport.havale.id=<s:property value="#h.getId()"/>')">&nbsp;</span>
                    <%--<input type="button"--%>
                           <%--onclick="window.location='/elamBeMaliHavale.action?khesaratHavale.id=<s:property--%>
                                   <%--value="id"/>'" value="اعلام به مالی"/>--%>
                        <%--</s:if>--%>
                </td>
                <c:if test="${darkhastBazkharid.state.id!=656}">
                    <s:if test="%{#stat.index==0}">
                        <td rowspan="${fn:length(darkhastBazkharid.khesaratI.havaleList)}">
                            <%--<c:if test="${khesarat.koleDarsadHavaleha<100}">--%>
                            <c:if test="${darkhastBazkharid.khesaratI.showAddBtn}">
                                <input type="button" value="+"
                                       onclick="showHavaleDialog('I','','','','','','','');"/>
                            </c:if>
                            <%--</c:if>--%>
                        </td>
                    </s:if>
                </c:if>
            </tr>
        </s:iterator>
        <c:if test="${darkhastBazkharid.khesaratII!=null && (darkhastBazkharid.khesaratII.havaleList == null || fn:length(darkhastBazkharid.khesaratII.havaleList)==0)}">
            <tr>
                <th>خسارت دوم</th>
                <th>${darkhastBazkharid.khesaratII.typeFarsi}</th>
                <th>${darkhastBazkharid.khesaratII.amountTaidShode}</th>

                <td colspan="6"></td>
                <td>
                    <c:if test="${darkhastBazkharid.khesaratII.showAddBtn}">
                        <input type="button" value="+"
                               onclick="showHavaleDialog('II','','','','','','','');">
                    </c:if>
                </td>
            </tr>
        </c:if>
        <s:iterator  id="h1" value="darkhastBazkharid.khesaratII.havaleList" status="stat1">
            <tr>
                <s:if test="%{#stat1.index==0}">
                    <th rowspan="${fn:length(darkhastBazkharid.khesaratII.havaleList)}">خسارت دوم</th>
                    <th rowspan="${fn:length(darkhastBazkharid.khesaratII.havaleList)}">${darkhastBazkharid.khesaratII.typeFarsi}</th>
                    <th rowspan="${fn:length(darkhastBazkharid.khesaratII.havaleList)}">${darkhastBazkharid.khesaratII.amountTaidShode}</th>
                </s:if>
                <td><s:property value="#h1.getFullName()"/></td>
                <td><s:property value="#h1.getCodeMelli()"/></td>
                <td><s:property value="#h1.getPercent()"/></td>
                <td><s:property value="#h1.getAmountHavale()"/></td>
                <td><s:property value="#h1.getTypeFa()"/></td>
                <td>
                        <%--<s:if test="elamBeMaliShode==false">--%>
                        <%--<input type="button" value="ویرایش"--%>
                        <%--onclick="window.location='/addKhesaratHavale?khesarat.id=<s:property value="khesarat.id"/>&khesaratHavale.id=<s:property value="id"/>'">--%>
                    <c:if test="${darkhastBazkharid.state.id!=656}">
                        <span class="ui-icon ui-icon-trash" title="حذف" style="float: left;"
                           onclick="confirmMessage('آیا از حذف حواله اطمینان دارید؟',
                                   '',
                                   function(){window.location='/delKhesaratHavale?darkhastBazkharid.id=${darkhastBazkharid.id}&khesarat.id=<s:property value="#h1.getKhesarat().getId()"/>&khesaratHavale.id=<s:property
                                    value="#h1.getId()"/>';})">&nbsp;</span>
                        <span class="ui-icon ui-icon-pencil" title="ویرایش" style="float: left;"
                      onclick="showHavaleDialog('II',
                                                '<s:property value="#h1.getId()"/>',
                                                '<s:property value="#h1.getName()"/>',
                                                '<s:property value="#h1.getFamily()"/>',
                                                '<s:property value="#h1.getAmountHavale()"/>',
                                                '<s:property value="#h1.getType()"/>',
                                                '<s:property value="#h1.getShomareShaba()"/>',
                                                '<s:property value="#h1.getCodeMelli()"/>'
                                               );"
                    >&nbsp;</span>
                    </c:if>
                    <span class="ui-icon ui-icon-print" title="پرینت" style="float: left;"
                          onclick="window.open('/printeHavaleKhesarat?pishnehadReport.darkhastBazkharid.id=${darkhastBazkharid.id}&pishnehadReport.khesarat.id=<s:property value="#h1.getKhesarat().getId()"/>&pishnehadReport.havale.id=<s:property value="#h1.getId()"/>')">&nbsp;</span>
                    <%--<input type="button"--%>
                           <%--onclick="window.location='/elamBeMaliHavale.action?khesaratHavale.id=<s:property--%>
                                   <%--value="id"/>'" value="اعلام به مالی"/>--%>
                        <%--</s:if>--%>
                </td>
                <c:if test="${darkhastBazkharid.state.id!=656}">
                    <s:if test="%{#stat1.index==0}">
                        <td rowspan="${fn:length(darkhastBazkharid.khesaratII.havaleList)}">
                            <%--<c:if test="${khesarat.koleDarsadHavaleha<100}">--%>
                            <c:if test="${darkhastBazkharid.khesaratII.showAddBtn}">
                                <input type="button" value="+" onclick="showHavaleDialog('II','','','','','','','');"/>
                            </c:if>
                            <%--</c:if>--%>
                        </td>
                    </s:if>
                </c:if>



            </tr>
        </s:iterator>

    </table>
    <%--<input type="button" value="اعلام به مالی"--%>
           <%--onclick="window.location='/sabtKhesaratHavale?type=1&khesarat.id=<s:property value="khesarat.id"/>'">--%>
    <%--<input type="button" value="انصراف"--%>
           <%--onclick="window.location='/sabtKhesaratHavale?type=2&khesarat.id=<s:property value="khesarat.id"/>'">--%>
    <%--<input type="button" value="تسویه عیر قابل پرداخت"--%>
           <%--onclick="window.location='/sabtKhesaratHavale?type=3&khesarat.id=<s:property value="khesarat.id"/>'">--%>
</center>

<div id="havale_dlg_div" style="display: none;">
    <form action="/addKhesaratHavaleDo" method="post" id="havale_dlg">
        <input type="hidden" name="darkhastBazkharid.id" value="${darkhastBazkharid.id}"/>
        <input type="hidden" id="kh_id" name="khesarat.id" value=""/>
        <input type="hidden" id="kh_h_id" name="khesaratHavale.id"/>
        <table class="inputList" cellspacing="5" width="90%">
        <tr>
            <td>
                <span class="noThing"></span>
                <input type="text" name="khesaratHavale.name" id="add_name"/>
                &nbsp;<label>نام</label>
            </td>
            <td>
                <span class="noThing"></span>
                <input type="text" name="khesaratHavale.family" id="add_family"/>
                &nbsp;<label>نام خانوادگی</label>
            </td>
            <td>
                <span class="noThing"></span>
                <input type="text" name="khesaratHavale.codeMelli" id="add_code_melli" class="validate[custom[onlyNumber]]"/>
                &nbsp;<label>کد ملی / شماره شناسایی</label>
            </td>
        </tr>
        <tr>

            <%--<td>--%>
                <%--<span class="noThing"></span>--%>
                <%--<input type="text" name="khesaratHavale.family" id="add_darsad"/>--%>
                <%--&nbsp;<label>درصد حواله</label>--%>
            <%--</td>--%>
            <td id="amount_havale_td">
                <span class="noThing"></span>
                <input type="hidden" id="kh_num"/>
                <input type="text" name="khesaratHavale.amountHavale" class="digitSeparators validate[required,custom[long]]" id="havale_amount" onchange="checkAmount();"/>
                &nbsp;<label>مبلغ حواله</label>
            </td>
            <td style="white-space: nowrap;">
                <div class="dblRadio" id="add_type" style="padding-right: 30px;">
                    <input type="radio" name="khesaratHavale.type" id="add_type_radio_ck" onchange="changeType();"
                           value="CHECK"
                           title="چک"/>
                    <label id="add_type_radio_ck_lbl" for="add_type_radio_ck">چک</label>
                    <input type="radio" name="khesaratHavale.type" id="add_type_radio_ach" onchange="changeType();"
                           value="ACH"  checked="checked"
                           title="ACH"/>
                    <label id="add_type_radio_ach_lbl" for="add_type_radio_ach">ACH</label>
                </div>
                &nbsp;<label>نوع حواله</label>
            </td>
            <td id="shaba_div"style="white-space: nowrap;">
                <span class="noThing"></span>
                <input type="text" name="khesaratHavale.shomareShaba" id="shomare_shaba" class="validate[required,custom[onlyNumber]]"/>
                &nbsp;<label>شماره شبا</label>
            </td>
        </tr>
        </table>
    </form>
</div>
