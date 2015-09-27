<%@ taglib prefix="dispay" uri="http://displaytag.sf.net" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <script type="text/javascript">
        $(function(){
        $('#checkBoxSelectAllBimename').bind("change" ,function(){
            $('input[name="checkBoxBimename"]').each(function(){
                $(this).attr('checked', $('#checkBoxSelectAllBimename').attr('checked'));
            })
        });
        });
    </script>
    <form id="bimenameListGharardad" action="">
        <s:hidden key="gharardad.id" label="" theme="simple"/>
        <s:hidden name="selectedMenu" value="3"/>
        <table>
            <tr>
                <td>
                    <input id="checkBoxSelectAllBimename" type="checkbox" name="checkBoxSelectAllBimename" value="selected"/>
                </td>
                <td>
                    <label for="checkBoxSelectAllBimename">
                        انتخاب همه
                        &nbsp;${bimenamePaginatedList.getFullListSize()}&nbsp;
                        بیمه نامه
                    </label>
                </td>
            </tr>
        </table>
        <display:table uid="rpViewResult" name="bimenamePaginatedList" style="margin: 0 auto; width: 100%;"
                   form="bimenameListGharardad" excludedParams="_chk selectedMenu gharardad.id pishnehadSearch*"
                   decorator="org.displaytag.decorator.CheckboxTableDecorator"
                   export="true" id="rpViewResult"
                   sort="external" htmlId="rpViewResult"
                   requestURI="" clearStatus="true" keepStatus="false">

            <display:column title=""
                            media="html">
                <input type="checkbox" name="checkBoxBimename" value="${rpViewResult.id}"/>
            </display:column>
            <display:column media="html" title="عملیات">
                <a href="/editShowFormReadOnly?pishnehad.id=${rpViewResult.pishnehad.id}" style="padding:7px; display:block;">نمايش</a>
            </display:column>
            <display:column title="ردیف">
                ${rpViewResult_rowNum}
            </display:column>
            <display:column property="shomare" title="شماره بیمه نامه"/>
            <display:column property="tarikhSodour" title="تاریخ صدور"/>
            <display:column property="tarikhEngheza" title="تاریخ انقضا"/>
            <display:column property="state.stateName" title="وضعیت"/>
            <display:column property="pishnehad.bimeGozar.shakhs.fullName" title="نام بیمه گذار"/>
            <%--<display:column property="radif" title="نام بیمه شده"/>--%>
        </display:table>
    </form>

<br/><br/>
<table style="margin: 0 auto;">
    <tr>
        <td>
            <input type="button" value="چاپ قرارداد"
                   onclick="window.open('/downloadFile?fileId=<s:property value="gharardad.fileId"/>')"
                   <s:if test="gharardad.fileId==null">disabled="disabled"</s:if> style="width: 150px;">
        </td>
        <sec:authorize ifAnyGranted="ROLE_KARSHENAS_MASOUL,ROLE_RAEIS_SODUR,ROLE_KARSHENAS_BAYEGANI,ROLE_KARSHENAS_SODUR,ROLE_JAMI_KHAS,ROLE_SUPERVISOR">
        <td>
            <input type="button" value="چاپ بیمه نامه"
                   onclick="$('#serialPrint').show()" style="width: 150px;">
                   <!--onclick="window.open('/printBimenameList?'+$('#bimenameListGharardad').serialize())"-->
        </td>
        <td>
            <input type="button" value="چاپ نامه ضمیمه"
                   onclick="window.location='/printZamimeBimenameList?gharardad.id=<s:property value="gharardad.id"/>'" style="width: 150px;">
        </td>
        </sec:authorize>
        <td>
            <input type="button" value="چاپ جدول محاسبات ریاضی"
                   onclick="window.open('/printMohasebateRiaziList?'+$('#bimenameListGharardad').serialize())" style="width: 150px;">
        </td>
    </tr>
</table>

<div id="serialPrint" style="display: none;margin-top: 50px">
    <form id="serialPrintForm" action="/printBimenameList">
        <table style="margin: 0 auto; width: 50%;">
            <tr>
                <td>
                    سریال ابتدا:
                </td>
                <td>
                    <s:textfield key="serialStart" id="serialStart" label="" theme="simple"
                                 cssStyle="width: 176px;float:right;margin-right:3px;" cssClass="validate[required,custom[integer]]"/>
                </td>
                <td>
                    <!--s:submit value="چاپ" theme="simple"/-->
                    <input type="button" value="چاپ بیمه نامه"
                           onclick="window.open('/printBimenameList?'+$('#bimenameListGharardad').serialize()+'&'+$('#serialPrintForm').serialize())" style="width: 150px;">
                </td>
            </tr>
        </table>
    </form>

</div>