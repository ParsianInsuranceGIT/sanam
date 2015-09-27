<%@ page import="com.bitarts.parsian.model.pishnahad.Zamaem" %>
<%@ page import="com.bitarts.parsian.model.pishnahad.Pishnehad" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
    Pishnehad pishnehadRun = (Pishnehad) request.getAttribute("pishnehadRun");
%>
<c:if test="${logicaldocIndicator != null}">
    <table dir="rtl" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <tr>
            <td>
                <p style="color: red;">
                سرویس مخزن اسناد دچار مشکل شده است. لطفا تا دقایقی دیگر مجددا تلاش نمایید.
                </p>
            </td>
        </tr>
    </table>
</c:if>

<form id="zamaemfishform" name="form4dish" onsubmit="$('#uploadedfilehidden').val($('#uploadedFile').val());" action="/uploadFishMakeFinal.action" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
    <input type="hidden" name="pishnehad.id" value="<%=pishnehadRun.getId()%>"/>
    <input type="hidden" id="transid" name="transitionId" value=""/>
    <input type="hidden" name="logmessage" value="test"/>
    <c:if test="${!readOnlyMode}">
    <table id="table4fishinzamaem" dir="rtl" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
        <tbody>
        <tr>
                <td colspan="4">
                    <p style="text-align: right; color: #000000;">
                        خواهشمند است به منظور كاهش حجم فايلهاي آپلودي،
<u><b><a href="../extra/scan_manual.pdf">راهنماي كاربري اسكن و آپلود فايلها</a></b></u>
 را حتماً مطالعه فرماييد. لازم به ذكر است كه براي ثبت پيشنهادات انفرادي ميبايست يك فايل pdf حاوي كليه فرم هاي لازم و براي ثبت پيشنهادات خانواده، يك فايل pdf حاوي كليه فرم هاي لازم و يك فايل excel 2003 (فرمت xls) آپلود نماييد.
                    </p>
                </td>
            </tr>

        <tr>
                <td colspan="4">
                    <p style="color: #000000;"><b>توجه: حداکثر حجم هر فایل 1 مگابایت می باشد</b></p>
                    <%--<p style="text-align: right; color: red;"><b>--%>
                        <%--نمایندگان محترم، بنا به دستورالعمل بیمه مرکزی ایران اسکن مدارک شناسایی بیمه گذار (کارت ملی) را در سیستم آپلود نمایید.--%>
                    <%--</b></p>--%>
                </td>
            </tr>
            <tr>
                <td>
فایل:
                </td>
                <td>
                        <input type="file" id="uploadedFile" name="uploadedFile"/>
                    <input type="hidden" id="uploadedfilehidden" name="uploadedFileName"/>
                </td>
                <td>
                    نوع فایل
                </td>
                <td>
                    <select name="noeFile" id="noeFile_upload" onchange="updateTozihat();">

                        <c:if test="${(pishnehad.state.id==10||pishnehad.state.id==20||pishnehad.state.id==50||pishnehad.state.id==230)&&( pishnehad.state.id!=220)&&( pishnehad.state.id!=252)&&( pishnehad.state.id!=310)&&( pishnehad.state.id!=370)}">
                        <%--<option value="katbi">پیشنهاد کتبی</option>--%>
                        <option value="katbi">فايل pdf پيشنهاد، فيش و گواهي اطلاع بيمه گذران</option>
                        </c:if>
                        <c:if test="${(pishnehad.state.id==20||pishnehad.state.id==50||pishnehad.state.id==230)&&( pishnehad.state.id!=220)&&( pishnehad.state.id!=252)&&( pishnehad.state.id!=310)&&( pishnehad.state.id!=370)&&(pishnehad.noeBimename=='خانواده')}">
                        <option value="excel_kh">فايل excel طرح خانواده</option>
                        </c:if>
                        <%--<c:if test="${(pishnehad.naghsPishnehad.naghsFish == '1'||pishnehad.state.id==20||pishnehad.state.id==50||pishnehad.state.id==90||pishnehad.state.id==230)&& (pishnehad.state.id!=220)&&( pishnehad.state.id!=310)&&( pishnehad.state.id!=370)}">--%>
                        <%--<option value="fish">فیش پرداختی</option>--%>
                        <%--</c:if>--%>
                        <c:if test="${pishnehad.state.id==220||pishnehad.state.id==310||pishnehad.state.id==252}">
                            <%--<option value="elamhesab">فرم اعلام حساب</option>--%>
                            <option value="elamhesab">فايل pdf مدارك برگشت حق بيمه</option>
                        </c:if>
                        <%--<c:if test="${pishnehad.state.id==310 || pishnehad.state.id==230 || pishnehad.state.id==370}">--%>
                            <%--<option value="enseraf">فرم انصراف</option>--%>
                        <%--</c:if>--%>
                        <c:if test="${pishnehad.state.id==10||pishnehad.state.id==20||pishnehad.state.id==50||pishnehad.state.id==170||pishnehad.state.id==230}">
                            <%--<option value="moshavere_pezeshk">درخواست مشاوره پزشک متخصص</option>--%>
                            <option value="moshavere_pezeshk">فايل pdf مدارك پزشكي</option>
                        </c:if>
                        <c:if test="${pishnehad.state.id==165}">
                            <option value="moshavere_pezeshk_javab">فايل pdf درخواست مشاوره پزشك متخصص</option>
                        </c:if>
                        <c:if test="${pishnehad.state.id==10||pishnehad.state.id==20||pishnehad.state.id==50||pishnehad.state.id==90||pishnehad.state.id==170||pishnehad.state.id==220||pishnehad.state.id==252||pishnehad.state.id==230||pishnehad.state.id==310}">
                            <option value="sayer">فايل pdf ساير مدارك</option>
                        </c:if>
                    </select>
                </td>
            </tr>
            <%--<tr>--%>
                <%--<td></td><td></td><td>شماره صفحه</td>--%>
                <%--<td>--%>
                    <%--<select name="shomareSafhe" id="shomareSafhe_upload" onchange="updateTozihat();">--%>
                        <%--<option value="1">1</option>--%>
                        <%--<option value="2">2</option>--%>
                        <%--<option value="3">3</option>--%>
                        <%--<option value="4">4</option>--%>
                        <%--<option value="5">5</option>--%>
                    <%--</select>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <tr>
                <td>
                    توضیحات:
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <textarea rows="5" cols="65" name="tozihat" id="tozihat_upload"></textarea>
                </td>
            </tr>
            <tr>
                <td colspan="2">

                    <c:if test="${ pishnehad.state.id == 50 }">
                        <sec:authorize ifAnyGranted="ROLE_NAMAYANDE">
                            <input type="button" value="آپلود فایل" onclick="confirmMessage('در صورتي كه مشخصات پیشنهاد را ویرایش كرده اید و تا کنون ذخیره نکرده اید با زدن دکمه انصراف ابتدا تغییرات خود را ذخیره نمایید  ',function(){
                                $('#zamaemfishform').submit();
                            });" />
                        </sec:authorize>
                        <%--<sec:authorize ifNotGranted="ROLE_NAMAYANDE">--%>
                            <%--<input type="submit" value="آپلود فایل" />--%>
                        <%--</sec:authorize>--%>
                    </c:if>
                    <c:if test="${ pishnehad.state.id != 50 }">
                            <input type="submit" value="آپلود فایل" />
                    </c:if>

                </td>

            </tr>
        </tbody>
    </table>
    </c:if>

</form>
<br/>
<c:if test="${backfromupload == 'true'}">
<c:if test="${errorsMap == null}">
<c:if test="${logicaldocIndicator == null}">

    <table width="100%">
        <tr>
            <td>
                <p style="color:#006400;">فایل شما با موفقیت آپلود شد!</p>
            </td>
        </tr>
    </table>
</c:if>
</c:if>
</c:if>

<c:if test="${backfromupload == 'true_rem'}">
    <table width="100%">
        <tr>
            <td>
                <p style="color:#006400;">فایل شما با موفقیت حذف شد!</p>
            </td>
        </tr>
    </table>
</c:if>
<table id="tableshowingfishes" dir="rtl" cellpadding="0" cellspacing="0" class="jtable" style="width:88%;border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
    <c:if test="${errorsMap != null && darkhastTaghirat == null}">
        <c:if test="${errorsMap['duplicateupload']!=null}">
        <tr>
            <td colspan="5">

                <lable style="color:red;"><c:out value="${errorsMap['duplicateupload']}"/></lable>
            </td>
        </tr>
        </c:if>
        <c:if test="${errorsMap['maxsizereached']!=null}">
        <tr>
            <td colspan="5">
                <lable style="color:red;"><c:out value="${errorsMap['maxsizereached']}"/></lable>
            </td>
        </tr>
        </c:if>
    </c:if>
    <tr>
        <th>
            نوع فایل
        </th>
        <th>
            شماره صفحه
        </th>
        <th width="50%">
            توضیحات
        </th>
        <th>
            لینک فایل
        </th>
        <c:if test="${pishnehad.state.id<30 || pishnehad.state.id == 50 || pishnehad.state.id == 90 || pishnehad.state.id == 170}">
        <th>
            حذف فایل
        </th>
        </c:if>
    </tr>
    <%
        if(pishnehadRun!=null){
            Zamaem zamime = pishnehadRun.getZamaem();
            if(zamime!=null){
            if (zamime.getFilePishKatbiId1()!=null){%>
            <tr>
                <td>
فايل pdf پيشنهاد، فيش و گواهي اطلاع بيمه گذران
                </td>
                <td>
                    ۱
                </td>
                <td><%if(zamime.getFilePishKatbiDescription1()!=null){%>
                    <%=zamime.getFilePishKatbiDescription1()%>
                    <%}else{%>
                        -
                    <%}%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFilePishKatbiId1()%>'">لینک دانلود فایل</a>
                </td>
                <c:if test="${!readOnlyMode && (pishnehad.state.id==10||pishnehad.state.id==20||pishnehad.state.id==50||pishnehad.state.id==230)&&( pishnehad.state.id!=220)&&( pishnehad.state.id!=252)&&( pishnehad.state.id!=310)&&( pishnehad.state.id!=370)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFilePishKatbiId1()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
            <%}else{%>
                <%--<tr>--%>
                    <%--<td colspan="4">--%>
                        <%--<p style="color:red">--%>
                            <%--صفحه ی اول پشنهاد کتبی آپلود نشده است.--%>
                        <%--</p>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%}
            if (zamime.getFilePishKatbiId2()!=null){%>
            <tr>
                <td>
                    پشنهاد کتبی
                </td>
                <td>
                    ۲
                </td>
                <td><%if(zamime.getFilePishKatbiDescription2()!=null){%>
                    <%=zamime.getFilePishKatbiDescription2()%>
                    <%}else{%>
                        -
                    <%}%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFilePishKatbiId2()%>'">لینک دانلود فایل</a>
                </td>
                <c:if test="${!readOnlyMode && (pishnehad.state.id<30 || pishnehad.state.id == 50 || pishnehad.state.id == 90)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFilePishKatbiId2()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
            <%}else{%>
                <%--<tr>--%>
                    <%--<td colspan="4">--%>
                        <%--<p style="color:red">--%>
<%--صفحه ی دوم پیشنهاد کتبی آپلود نشده است.--%>
                        <%--</p>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%}

            if (zamime.getFilePishKatbiId3()!=null){%>
            <tr>
                <td>
                    پشنهاد کتبی
                </td>
                <td>
                    ۳
                </td>
                <td>
                    <%if(zamime.getFilePishKatbiDescription3()!=null){%>
                    <%=zamime.getFilePishKatbiDescription3()%>
                    <%}else{%>
                        -
                    <%}%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFilePishKatbiId3()%>'">لینک دانلود فایل</a>
                </td>
                <c:if test="${!readOnlyMode && (pishnehad.state.id<30 || pishnehad.state.id == 50 || pishnehad.state.id == 90)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFilePishKatbiId3()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
            <%}
            else{%>
                <%--<tr>--%>
                    <%--<td colspan="4">--%>
                        <%--<p style="color:red">--%>
<%--صفحه ی سوم پشنهاد کتبی آپلود نشده است.--%>
                        <%--</p>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%}
            if (zamime.getFilePishKatbiId4()!=null){%>
            <tr>
                <td>
                    پشنهاد کتبی
                </td>
                <td>
                    ۴
                </td>
                <td><%if(zamime.getFilePishKatbiDescription4()!=null){%>
                    <%=zamime.getFilePishKatbiDescription4()%>
                    <%}else{%>
                        -
                    <%}%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFilePishKatbiId4()%>'">لینک دانلود فایل</a>
                </td>
                <c:if test="${!readOnlyMode && (pishnehad.state.id<30 || pishnehad.state.id == 50 || pishnehad.state.id == 90)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFilePishKatbiId4()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
            <%}
            else{%>
                <%--<tr>--%>
                    <%--<td colspan="4">--%>
                        <%--<p style="color:red">--%>
<%--صفحه ی چهارم پیشنهاد کتبی آپلود نشده است.--%>
                        <%--</p>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%}
            if (zamime.getFilePishKatbiId5()!=null){%>
            <tr>
                <td>
                    پشنهاد کتبی
                </td>
                <td>
                    ۵
                </td>
                <td><%if(zamime.getFilePishKatbiDescription5()!=null){%>
                    <%=zamime.getFilePishKatbiDescription5()%>
                    <%}else{%>
                        -
                    <%}%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFilePishKatbiId5()%>'">لینک دانلود فایل</a>
                </td>
                <c:if test="${!readOnlyMode && (pishnehad.state.id<30 || pishnehad.state.id == 50 || pishnehad.state.id == 90)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFilePishKatbiId5()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
            <%}
            else{%>
                <%--<tr>--%>
                    <%--<td colspan="4">--%>
                        <%--<p style="color:red">--%>
<%--صفحه ی پنجم پشنهاد کتبی آپلود نشده است.--%>
                        <%--</p>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%}
            if (zamime.getFilePishDigitalId1()!=null){%>
            <tr>
                <td>
                    پیشنهاد الکترونیکی
                </td>
                <td>
                    ۱
                </td>
                <td><%if(zamime.getFilePishDigitalDescription1()!=null){%>
                    <%=zamime.getFilePishDigitalDescription1()%>
                    <%}else{%>
                        -
                    <%}%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFilePishDigitalId1()%>'">لینک دانلود فایل</a>
                </td>
                <c:if test="${!readOnlyMode && (pishnehad.state.id<30 || pishnehad.state.id == 50 || pishnehad.state.id == 90)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFilePishDigitalId1()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
            <%}
            else{%>
                <%--<tr>--%>
                    <%--<td colspan="4">--%>
                        <%--<p style="color:red">--%>
                            <%--صفحه ی اول پیشنهاد الکترونیکی آپلود نشده است.--%>
                        <%--</p>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%}
            if (zamime.getFilePishDigitalId2()!=null){%>
            <tr>
                <td>
                    پیشنهاد الکترونیکی
                </td>
                <td>
                    ۲
                </td>
                <td><%if(zamime.getFilePishDigitalDescription2()!=null){%>
                    <%=zamime.getFilePishDigitalDescription2()%>
                    <%}else{%>
                        -
                    <%}%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFilePishDigitalId2()%>'">لینک دانلود فایل</a>
                </td>
                <c:if test="${!readOnlyMode && (pishnehad.state.id<30 || pishnehad.state.id == 50 || pishnehad.state.id == 90)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFilePishDigitalId2()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
            <%}
            else{%>
                <%--<tr>--%>
                    <%--<td colspan="4">--%>
                        <%--<p style="color:red">--%>
<%--صفحه ی دوم پیشنهاد الکترونیکی آپلود نشده است.--%>
                        <%--</p>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%}
            if (zamime.getFilePishDigitalId3()!=null){%>
            <tr>
                <td>
                    پیشنهاد الکترونیکی
                </td>
                <td>
                    ۳
                </td>
                <td><%if(zamime.getFilePishDigitalDescription3()!=null){%>
                    <%=zamime.getFilePishDigitalDescription3()%>
                    <%}else{%>
                        -
                    <%}%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFilePishDigitalId3()%>'">لینک دانلود فایل</a>
                </td>
                <c:if test="${!readOnlyMode && (pishnehad.state.id<30 || pishnehad.state.id == 50 || pishnehad.state.id == 90)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFilePishDigitalId3()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
            <%}
            else{%>
                <%--<tr>--%>
                    <%--<td colspan="4">--%>
                        <%--<p style="color:red">--%>
                            <%--صفحه ی سوم پیشنهاد الکترونیکی آپلود نشده است.--%>
                        <%--</p>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%}
            if (zamime.getFilePishDigitalId4()!=null){%>
            <tr>
                <td>
                    پیشنهاد الکترونیکی
                </td>
                <td>
                    ۴
                </td>
                <td><%if(zamime.getFilePishDigitalDescription4()!=null){%>
                    <%=zamime.getFilePishDigitalDescription4()%>
                    <%}else{%>
                        -
                    <%}%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFilePishDigitalId4()%>'">لینک دانلود فایل</a>
                </td>
                <c:if test="${!readOnlyMode && (pishnehad.state.id<30 || pishnehad.state.id == 50 || pishnehad.state.id == 90)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFilePishDigitalId4()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
            <%}
            else{%>
                <%--<tr>--%>
                    <%--<td colspan="4">--%>
                        <%--<p style="color:red">--%>
                            <%--صفحه ی چهارم پیشنهاد الکترونیکی آپلود نشده است.--%>
                        <%--</p>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%}
            if (zamime.getFilePishDigitalId5()!=null){%>
            <tr>
                <td>
                    پیشنهاد الکترونیکی
                </td>
                <td>
                    ۵
                </td>
                <td><%if(zamime.getFilePishDigitalDescription5()!=null){%>
                    <%=zamime.getFilePishDigitalDescription5()%>
                    <%}else{%>
                        -
                    <%}%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFilePishDigitalId5()%>'">لینک دانلود فایل</a>
                </td>
                <c:if test="${!readOnlyMode && (pishnehad.state.id<30 || pishnehad.state.id == 50 || pishnehad.state.id == 90)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFilePishDigitalId5()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
            <%}
            else{%>
                <%--<tr>--%>
                    <%--<td colspan="4">--%>
                        <%--<p style="color:red">--%>
                            <%--صفحه ی پنجم پیشنهاد الکترونیکی آپلود نشده است.--%>
                        <%--</p>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%}
            if (zamime.getFileFishId1()!=null){%>
            <tr>
                <td>
                    فایل فیش
                </td>
                <td>
                    ۱
                </td>
                <td><%if(zamime.getFileFishDescription1()!=null){%>
                    <%=zamime.getFileFishDescription1()%>
                    <%}else{%>
                        -
                    <%}%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFileFishId1()%>'">لینک دانلود فایل</a>
                </td>
                <c:if test="${!readOnlyMode && (pishnehad.state.id<30 || pishnehad.state.id == 50 || pishnehad.state.id == 90)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFileFishId1()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
            <%}
            else{%>
                <%--<tr>--%>
                    <%--<td colspan="4">--%>
                        <%--<p style="color:red">--%>
<%--فایل فیش آپلود نشده است.--%>
                        <%--</p>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%}
            if (zamime.getFileFishId2()!=null){%>
            <tr>
                <td>
                    فایل فیش
                </td>
                <td>
                    ۲
                </td>
                <td><%if(zamime.getFileFishDescription2()!=null){%>
                    <%=zamime.getFileFishDescription2()%>
                    <%}else{%>
                        -
                    <%}%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFileFishId2()%>'">لینک دانلود فایل</a>
                </td>
                <c:if test="${!readOnlyMode && (pishnehad.state.id<30 || pishnehad.state.id == 50 || pishnehad.state.id == 90)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFileFishId2()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
            <%}
            if (zamime.getFileFishId3()!=null){%>
            <tr>
                <td>
                    فایل فیش
                </td>
                <td>
                    ۳
                </td>
                <td><%if(zamime.getFileFishDescription3()!=null){%>
                    <%=zamime.getFileFishDescription3()%>
                    <%}else{%>
                        -
                    <%}%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFileFishId3()%>'">لینک دانلود فایل</a>
                </td>
                <c:if test="${!readOnlyMode && (pishnehad.state.id<30 || pishnehad.state.id == 50 || pishnehad.state.id == 90)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFileFishId3()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
            <%}
            if (zamime.getFileFishId4()!=null){%>
            <tr>
                <td>
                    فایل فیش
                </td>
                <td>
                   ۴
                </td>
                <td><%if(zamime.getFileFishDescription4()!=null){%>
                    <%=zamime.getFileFishDescription4()%>
                    <%}else{%>
                        -
                    <%}%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFileFishId4()%>'">لینک دانلود فایل</a>
                </td>
                <c:if test="${!readOnlyMode && (pishnehad.state.id<30 || pishnehad.state.id == 50 || pishnehad.state.id == 90)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFileFishId4()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
            <%}
            if (zamime.getFileFishId5()!=null){%>
            <tr>
                <td>
                    فایل فیش
                </td>
                <td>
                    ۵
                </td>
                <td><%if(zamime.getFileFishDescription5()!=null){%>
                    <%=zamime.getFileFishDescription5()%>
                    <%}else{%>
                        -
                    <%}%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFileFishId5()%>'">لینک دانلود فایل</a>
                </td>
                <c:if test="${!readOnlyMode && (pishnehad.state.id<30 || pishnehad.state.id == 50 || pishnehad.state.id == 90)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFileFishId5()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
            <%}
            if (zamime.getFileSayerId1()!=null){%>
            <tr>
                <td>
                    فايل pdf ساير مدارك
                </td>
                <td>
                    ۱
                </td>
                <td>
                    <%=zamime.getFileSayerDescription1()%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFileSayerId1()%>'">لینک دانلود فایل</a>
                </td>

                <c:if test="${!readOnlyMode && (pishnehad.state.id==20||pishnehad.state.id==50||pishnehad.state.id==90||pishnehad.state.id==170||pishnehad.state.id==220||pishnehad.state.id==252||pishnehad.state.id==230||pishnehad.state.id==310)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFileSayerId1()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
    <%}
            if (zamime.getFileSayerId2()!=null){%>
            <tr>
                <td>
                    سایر فایلها
                </td>
                <td>
                    ۲
                </td>
                <td>
                    <%=zamime.getFileSayerDescription2()%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFileSayerId2()%>'">لینک دانلود فایل</a>
                </td>

                <c:if test="${!readOnlyMode && (pishnehad.state.id<30 || pishnehad.state.id == 50 || pishnehad.state.id == 90)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFileSayerId2()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
    <%}
            if (zamime.getFileSayerId3()!=null){%>
            <tr>
                <td>
                    سایر فایلها
                </td>
                <td>
                    ۳
                </td>
                <td>
                    <%=zamime.getFileSayerDescription3()%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFileSayerId3()%>'">لینک دانلود فایل</a>
                </td>

                <c:if test="${!readOnlyMode && (pishnehad.state.id<30 || pishnehad.state.id == 50 || pishnehad.state.id == 90)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFileSayerId3()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
    <%}
            if (zamime.getFileSayerId4()!=null){%>
            <tr>
                <td>
                    سایر فایلها
                </td>
                <td>
                    ۴
                </td>
                <td>
                    <%=zamime.getFileSayerDescription4()%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFileSayerId4()%>'">لینک دانلود فایل</a>
                </td>

                <c:if test="${!readOnlyMode && (pishnehad.state.id<30 || pishnehad.state.id == 50 || pishnehad.state.id == 90)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFileSayerId4()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
    <%}
            if (zamime.getFileSayerId5()!=null){%>
            <tr>
                <td>
                    سایر فایلها
                </td>
                <td>
                    ۵
                </td>
                <td>
                    <%=zamime.getFileSayerDescription5()%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFileSayerId5()%>'">لینک دانلود فایل</a>
                </td>

                <c:if test="${!readOnlyMode && (pishnehad.state.id<30 || pishnehad.state.id == 50 || pishnehad.state.id == 90)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFileSayerId5()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
            <%}
            if (zamime.getFileMoshaverePezeshkId()!=null){%>
            <tr>
                <td>
                    فايل pdf مدارك پزشكي
                </td>
                <td>
                    -
                </td>
                <td>
                    <%=zamime.getFileMoshaverePezeshkDesc()%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFileMoshaverePezeshkId()%>'">لینک دانلود فایل</a>
                </td>

                <c:if test="${!readOnlyMode && (pishnehad.state.id==20||pishnehad.state.id==50||pishnehad.state.id==170||pishnehad.state.id==230)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFileMoshaverePezeshkId()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
            <%}
            if (zamime.getFileMoshaverePezeshkJavabId()!=null){%>
            <tr>
                <td>
                    فايل pdf درخواست مشاوره پزشك متخصص
                </td>
                <td>
                    -
                </td>
                <td>
                    <%=zamime.getFileMoshaverePezeshkJavabDesc()%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFileMoshaverePezeshkJavabId()%>'">لینک دانلود فایل</a>
                </td>

                <c:if test="${!readOnlyMode && (pishnehad.state.id == 165)}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFileMoshaverePezeshkJavabId()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>
            <%}
            if (zamime.getFileElameHesabId()!=null){%>
            <tr>
                <td>
فايل pdf مدارك برگشت حق بيمه
                </td>
                <td>
                    -
                </td>
                <td>
                    <%=zamime.getFileElameHesabDescription()%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFileElameHesabId()%>'">لینک دانلود فایل</a>
                </td>

                <c:if test="${!readOnlyMode && (pishnehad.state.id==220||pishnehad.state.id==252||pishnehad.state.id==310)}">
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFileElameHesabId()%>'">حذف فایل</a>
                    </td>
                </c:if>
            </tr>
            <%}
            if (zamime.getFileEnserafId()!=null){%>
            <tr>
                <td>
پرینت فرم انصراف
                </td>
                <td>
                    -
                </td>
                <td>
                    <%=zamime.getFileEnserafDescription()%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFileEnserafId()%>'">لینک دانلود فایل</a>
                </td>

                <c:if test="${!readOnlyMode && (pishnehad.state.id ==310 || pishnehad.state.id ==370)}">
                    <td>
                        <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFileEnserafId()%>'">حذف فایل</a>
                    </td>
                </c:if>
            </tr>
             <%}
            if (zamime.getFileExcelKhanevadeId()!=null){%>
            <tr>
                <td>
فایل اکسل طرح خانواده
                </td>
                <td>
                    -
                </td>
                <td>
                    <%=zamime.getFileExcelKhanevadeDesc()%>
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/downloadFile.action?fileId=<%=zamime.getFileExcelKhanevadeId()%>'">لینک دانلود فایل</a>
                </td>

                <c:if test="${!readOnlyMode && (pishnehad.state.id==20||pishnehad.state.id==50||pishnehad.state.id==230)&&( pishnehad.state.id!=220)&&( pishnehad.state.id!=252)&&( pishnehad.state.id!=310)&&( pishnehad.state.id!=370)&&(pishnehad.noeBimename=='خانواده')}">
                <td>
                    <a href="javascript:void(0);" onclick="window.location='/removeFileZamime.action?pishnehad.id=<%=pishnehadRun.getId()%>&zamaem.id=<%=zamime.getId()%>&fileId=<%=zamime.getFileExcelKhanevadeId()%>'">حذف فایل</a>
                </td>
                </c:if>
            </tr>

            <%}
            if (zamime.getFileFishId1()==null){
                if(pishnehadRun.getPishpardakhtType()!=null){
                    if(pishnehadRun.getPishpardakhtType().equalsIgnoreCase("fish")){%>
                    <tr>
                        <td colspan="5">
                            <p style="color:red;">
                                فایل فیش آپلود نشده است.
                            </p>
                        </td>
                    </tr>


                <%  }
                }
            }
            if(zamime.getFilePishKatbiId1() == null){
                %>
                <tr>
                    <td colspan="5">
                        <p style="color:red;">
صفحه اول پشنهاد کتبي آپلود نشده است.
                        </p>
                    </td>
                </tr>
            <%
            }
        }

    }
    %>

</table>
<c:if test="${!readOnlyMode}">
<table id="etmamuploadincase">
    <tr>
        <td colspan="4">
            <%--<sec:authorize ifNotGranted="ROLE_BAZARYAB">--%>
                <input type="button" onclick="checkForEtmam();" value="اتمام فرآیند"/>
            <%--</sec:authorize>--%>
        </td>
    </tr>
</table>
<br/><br/>
<table id="madarekMoredNiaz" style="border-color:black; " align="center">
    <tr style="border:black; background-color:#eaf2ff;">
        <th style="border-bottom-color:black;">        مدارک مورد نیاز جهت آپلود</th>
    </tr>
    <tr align="right"><td  >صفحه اول و دوم پیشنهاد کتبی بیمه گذار</td></tr>
    <tr align="right"><td>گواهی اطلاع بیمه گذار از شرایط بیمه های عمر</td></tr>
    <tr align="right"><td>صفحه اول کارت ملی بیمه گذار</td></tr>
    <tr align="right"><td>فیش پرداختی</td></tr>
</table>
</c:if>


<script type="text/javascript">
    function checkForEtmam(){
        var checker = true;
        <c:if test="${(pishnehad.zamaem.filePishDigitalId1!=null || pishnehad.zamaem.filePishDigitalId2!=null || pishnehad.zamaem.filePishDigitalId3!=null || pishnehad.zamaem.filePishDigitalId4!=null || pishnehad.zamaem.filePishDigitalId5!=null ||
                         pishnehad.zamaem.filePishKatbiId1!=null || pishnehad.zamaem.filePishKatbiId2!=null || pishnehad.zamaem.filePishKatbiId3!=null || pishnehad.zamaem.filePishKatbiId4!=null || pishnehad.zamaem.filePishKatbiId4!=null ||
                         pishnehad.zamaem.fileFishId1!=null || pishnehad.zamaem.fileSayerId1!=null)&&(pishnehad.fishs!=null)}">
            <c:if test="${pishnehad.state.id==90}">
                $("#transitionSelector").val(13);
                submitTransitionForm();
            </c:if>
//        <sec:authorize ifAnyGranted="ROLE_BAZARYAB" >
            <c:if test="${pishnehad.state.id==10}">
                $("#transitionSelector").val(1);
                submitTransitionForm();
            </c:if>
//        </sec:authorize>
            <c:if test="${pishnehad.state.id==20}">
                $("#transitionSelector").val(2);
                submitTransitionForm();
            </c:if>
            <c:if test="${pishnehad.state.id==50}">
                $("#transitionSelector").val(5);
                submitTransitionForm();
            </c:if>
            <c:if test="${pishnehad.state.id==220}">
                $("#transitionSelector").val(65);
                submitTransitionForm();
            </c:if>
            <c:if test="${pishnehad.state.id==252}">
                $("#transitionSelector").val(110);
                submitTransitionForm();
            </c:if>
            <c:if test="${pishnehad.state.id==170}">
                $("#transitionSelector").val(21);
                submitTransitionForm();
            </c:if>


            checker = false;
        </c:if>
        <c:if test="${pishnehad.state.id==310}">
            $("#transitionSelector").val(74);
            submitTransitionForm();
        </c:if>
        <c:if test="${pishnehad.state.id==370}">
            $("#transitionSelector").val(85);
            submitTransitionForm();
        </c:if>
        <c:if test="${pishnehad.state.id==165}">
            $("#transitionSelector").val(363);
            submitTransitionForm();
        </c:if>
        <c:if test="${pishnehad.state.id!=310 && pishnehad.state.id!=370}">
        if(checker==true){
//            alertMessage("برای ادامه باید حداقل یک فیش پرداخت نموده باشید و حتما حداقل یک فایل آپلود کرده باشید.");
            alertMessage("برای ادامه ثبت پیش پرداخت و آپلود فایل ضروری می باشد.");
        }
        </c:if>
    }
    <c:set var="readOnlyMode" value="${readOnlyMode}"/>
    <%
    if(pishnehadRun!=null){
        if(pishnehadRun.getState().getId()>=30 && (pishnehadRun.getState().getId()!=50) && (pishnehadRun.getState().getId()!=220)&& (pishnehadRun.getState().getId()!=252) && (pishnehadRun.getState().getId()!=90) && (pishnehadRun.getState().getId()!=170)&& (pishnehadRun.getState().getId()!=230)&&(pishnehadRun.getState().getId()!=165)&& (pishnehadRun.getState().getId()!=310)){
        %>
            $("#table4fishinzamaem").hide();
            $("#etmamuploadincase").hide();
            $("#madarekMoredNiaz").hide();
        <%}

    }
    %>
function fillInput(){
        $("#uploadedfilehidden").val($("#uploadedFile").val());
        $("#zamaemfishform").submit();
    }

    function updateTozihat()
    {
//        var str = "صفحه ";
//        str += $('#shomareSafhe_upload option:selected').text() + " ";
        var str = $('#noeFile_upload option:selected').text();
        $('#tozihat_upload').val(str);
    }

</script>