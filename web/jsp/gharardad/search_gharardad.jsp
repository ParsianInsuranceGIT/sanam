<%@ page import="com.bitarts.common.util.DateUtil" %>
<%@ page import="com.bitarts.parsian.Core.Constant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/taglibs.jsp"%>

<div id="allofem" class="listenTextFieldEnter" functionCall="searchGharardadAjax()">
    <form action="/searchForGharardads" id="form_search_for_gharardads" method="post">
        <input type="hidden" name="selectedTab" id="sel_sel_tab"/>
        <table class="inputList" cellspacing="5" width="90%">
            <tr>
                <td>
                    <span class="noThing"></span>
                    <s:textfield name="gharardadSearch.shomare" id="ghs_shomare" theme="simple"/>
                    &nbsp;<label>شماره قرارداد‌</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <s:textfield name="gharardadSearch.kodeNamayande" id="ghs_kodeNamayande"  theme="simple"/>
                    &nbsp;<label>کد نماینده/کارگزار</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <s:textfield name="gharardadSearch.ostan" id="ghs_ostan" theme="simple"/>
                    &nbsp;<label>استان نمایندگی</label>
                </td>
            </tr>
            <tr>
                <td>
                    <s:textfield name="gharardadSearch.aztarikh" id="ghs_aztarikh" value="1386/01/01" cssClass="datePkr validate[custom[date]]" theme="simple"/>
                    &nbsp;<label>از تاریخ (ثبت پیشنهاد)</label>
                </td>
                <td>
                    <s:textfield name="gharardadSearch.tatarikh" id="ghs_tatarikh" value="" cssClass="datePkr validate[custom[date]]" theme="simple"/>
                    &nbsp;<label>تا تاریخ (ثبت پیشنهاد)</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <s:textfield name="gharardadSearch.group" id="ghs_group" theme="simple"/>
                    &nbsp;<label>گروه</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <s:textfield name="gharardadSearch.userCreator" id="ghs_userCreator" theme="simple"/>
                    &nbsp;<label>كاربر ثبت كننده قرارداد</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <s:textfield name="gharardadSearch.numberOfPishnehads" id="ghs_numberOfPishnehads" theme="simple"/>
                    &nbsp;<label>تعداد پيشنهادات قرارداد</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <s:textfield name="gharardadSearch.numberOfBimename" id="ghs_numberOfBimename" theme="simple"/>
                    &nbsp;<label>تعداد بيمه نامه هاي قرارداد </label>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <script type="text/javascript">
                        function clearSeachFrom2()
                        {
                            $('#ghs_shomare').val('');
                            $('#ghs_kodeNamayande').val('');
                            $('#ghs_ostan').val('');
                            $('#ghs_aztarikh').val('1386/01/01');
                            $('#ghs_tatarikh').val('<%=DateUtil.getCurrentDate()%>');
                            $('#ghs_group').val('');
                            $('#ghs_userCreator').val('');
                            $('#ghs_numberOfPishnehads').val('');
                            $('#ghs_numberOfBimename').val('');
                        }
                    </script>
                    <input type="button" style="margin-left:25px" value="پاک کردن فرم" onclick="clearSeachFrom2()">
                    <%--<input type="submit" style="margin-left:25px" value="جستجو">--%>
                    <input type="button" onclick="searchGharardadAjax()" style="margin-left:25px" value="جستجو">
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    function searchGharardadAjax() {
        $.ajax({
            type: "POST",
            url: 'resultTab7.action',
            data: $('#form_search_for_gharardads').serialize(),
            success: function (response) {
                // we have the response
                $('#displayTab7').html(response);
            }
        });
    }


    function fillOstan2(ostanIdField, ostanNameField, nextField, parentId) {
        dialogFormWithFocus('searchCity', 'جستجوی استان', function () {
            if (parentId.toString().lastIndexOf("_viewview") > 0) {
                $('#' + ostanNameField + '_viewview').val($('#selectedOstan').val());
                $('#' + ostanIdField + '_viewview').val($('#ostanId').val());
            }
            else {
                $('#' + ostanNameField).val($('#selectedOstan').val());
                $('#' + ostanIdField).val($('#ostanId').val());
            }
        }, nextField);
    }

</script>