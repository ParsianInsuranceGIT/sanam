<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<c:if test="${logicaldocIndicator != null}">
    <table dir="rtl" cellpadding="0" cellspacing="0" style="border-spacing:1px;margin-left:auto;margin-right:auto;"
           border="0">
        <tr>
            <td>
                <p style="color: red;">
                    سرویس مخزن اسناد دچار مشکل شده است. لطفا تا دقایقی دیگر مجددا تلاش نمایید.
                </p>
            </td>
        </tr>
    </table>
</c:if>
<c:if test="${darkhastBazkharid.state.id==640 || darkhastBazkharid.state.id==12110 ||darkhastBazkharid.state.id==12130 || darkhastBazkharid.state.id==1300 || darkhastBazkharid.state.id==11040 || darkhastBazkharid.state.id==10000 || darkhastBazkharid.state.id==11060 || darkhastBazkharid.state.id==10060 || darkhastBazkharid.state.id==10040 || darkhastBazkharid.state.id==1000 || darkhastBazkharid.state.id==1400 || darkhastBazkharid.state.id == 11000 || darkhastBazkharid.state.id == 12010|| darkhastBazkharid.state.id == 12050|| darkhastBazkharid.state.id == 12070 || darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9040 || darkhastTaghirat.state.id == 9130 || darkhastTaghirat.state.id == 9150}">
    <form id="zamaemDarkhastForm" name="form4dish"
          onsubmit="$('#uploadedfilehiddendarkhast').val($('#uploadedFiledarkhast').val());"
          action="/uploadZamaemDarkhast" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
        <c:if test="${darkhastBazkharid != null}">
            <input type="hidden" name="darkhastBazkharid.id" value="<c:out value='${darkhastBazkharid.id}'/>"/>
        </c:if>
        <c:if test="${darkhastTaghirat != null}">
            <input type="hidden" name="darkhastTaghirat.id" value="<c:out value='${darkhastTaghirat.id}'/>"/>
        </c:if>
        <input type="hidden" id="takhsiskharshenalstransidfordakhast" name="transitionId" value="1003"/>
        <input type="hidden" name="logmessage" value="test"/>
        <table id="tableforzamaemfordarkhast" dir="rtl" cellpadding="0" cellspacing="0"
               style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
            <tbody>
            <c:if test="${errorsMap != null}">
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
                <c:if test="${errorsMap['nofilesuploaded']!=null}">
                    <tr>
                        <td colspan="2">
                            <p style="color:red;">${errorsMap['nofilesuploaded']}</p>
                        </td>
                    </tr>
                </c:if>
            </c:if>
            <tr>
                <td colspan="4">
                    <p style="text-align: right; color: #000000;">
                        خواهشمند است به منظور كاهش حجم فايلهاي آپلودي،
<u><b><a href="../extra/scan_manual.pdf">راهنماي كاربري اسكن و آپلود فايلها</a></b></u>
 را حتماً مطالعه فرماييد. لازم به ذكر است كه براي ثبت درخواست الحاقیه ميبايست يك فايل pdf حاوي كليه فرم هاي لازم آپلود نماييد.
                    </p>
                </td>
            </tr>

        <tr>
                <td colspan="2">
                    <p style="color: #000000;"><b>توجه: حداکثر حجم هر فایل 1 مگابایت می باشد</b></p>
                </td>
            </tr>
            <tr>
                <td>
                    فایل:
                </td>
                <td>
                    <input type="file" id="uploadedFiledarkhast" name="uploadedFile"/>
                    <input type="hidden" id="uploadedfilehiddendarkhast" name="uploadedFileName"/>
                </td>
                <td>
                    نوع فایل
                </td>
                <td>
                    <select name="noeFile" id="noeFile_upload_drkh" onchange="updateTozihat();">
                        <c:if test="${darkhastTaghirat==null&& darkhastBazkharid.darkhastType!='KHESARAT' && darkhastBazkharid.state.id!=12110 && darkhastBazkharid.state.id!=12130&&darkhastBazkharid.state.id!=10060&&darkhastBazkharid.state.id!=11060&&darkhastBazkharid.state.id!=12010}">
                            <option value="bahremandi">فرم درخواست بهره مندی از منافع</option>
                            <c:if test="${darkhastBazkharid.darkhastType.toString().equals('EBTAL') || darkhastBazkharid.darkhastType.toString().equals('BAZKHARID')}">
                                <option value="adameTataboghEmza">فرم عدم تطابق امضاء</option>
                                <option value="sayer">سایر</option>
                            </c:if>
                            <c:if test="${darkhastBazkharid==null || (!darkhastBazkharid.darkhastType.toString().equals('TASVIE_PISH_AZ_MOEDE_VAM')&&!darkhastBazkharid.darkhastType.toString().equals('VAM')&&!darkhastBazkharid.darkhastType.toString().equals('BARDASHT_AZ_ANDOKHTE'))}">
                                <option value="mafghudi">فرم اعلام مفقودی بیمه نامه</option>
                                <option value="aagahi">آگهی چاپ شده در روزنامه</option>
                            </c:if>
                        </c:if>
                        <c:if test="${darkhastTaghirat==null&& darkhastBazkharid.darkhastType=='KHESARAT'}">
                            <option value="elam_khesarat">فرم اعلام خسارت </option>
                            <option value="sayer">سایر</option>
                        </c:if>
                        <c:if test="${darkhastBazkharid.state.id==10060||darkhastBazkharid.state.id==11060}">
                            <option value="ehraz">فرم احراز هویت</option>
                        </c:if>
                        <c:if test="${darkhastBazkharid.state.id==12010}">
                            <option value="bahremandi">فرم درخواست بهره مندی از منافع</option>
                        </c:if>
                        <c:if test="${darkhastTaghirat.state.id==9010 || darkhastTaghirat.state.id == 9040 || darkhastTaghirat.state.id == 9130 || darkhastTaghirat.state.id == 9150}">
                            <%--<option value="elhaghiye">فرم درخواست الحاقیه</option>--%>
                            <%--<option value="akharinVaziat">فرم آخرین صورت وضعیت بیمه نامه</option>--%>
                            <%--<option value="shenasnameYaMelliBimegozarJadid">کپي كارت ملي يا صفحه اول شناسنامه بيمه گذار--%>
                                <%--جديد--%>
                            <%--</option>--%>
                            <%--<option value="shenasnameYaMelliBimegozarGhadim">كپي كارت ملي يا صفحه اول شناسنامه بیمه--%>
                                <%--گذار--%>
                            <%--</option>--%>
                            <%--<option value="shenasnameYaMelliBimeshodeGhadim">كپي كارت ملي يا صفحه اول شناسنامه بیمه--%>
                                <%--شده--%>
                            <%--</option>--%>
                            <option value="kollieMadarek">کلیه مدارک الحاقیه
                            </option>
                        </c:if>
                        <c:if test="${darkhastBazkharid.state.id==12110 || darkhastBazkharid.state.id==12130}">
                            <option value="fish_bedehi_vam">فيش پرداخت بدهي وام</option>
                        </c:if>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    توضیحات:
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <textarea rows="5"  cols="65" name="tozihat" id="tozihat_upload_drkh"></textarea>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="button" onclick="decideWhichUpload();" value="آپلود فایل"/>
                </td>

            </tr>
            </tbody>
        </table>

    </form>
</c:if>
<br/>
<c:if test="${darkhastBazkharid!=null||darkhastTaghirat!=null}">
<table id="tableshowingfishes" dir="rtl" cellpadding="0" cellspacing="0" class="jtable"
       style="width:80%;border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
<c:if test="${darkhastBazkharid.zamaemDarkhast.fileElamKhesaratId!=null ||darkhastBazkharid.zamaemDarkhast.fileDarkhastBahremandiId!=null || darkhastBazkharid.zamaemDarkhast.fileElameMafghudiId!=null || darkhastBazkharid.zamaemDarkhast.fileAgahiChapId!=null
                || darkhastBazkharid.zamaemDarkhast.fileSayerId!=null|| darkhastTaghirat.zamaemDarkhast.fileElhaghiyeId!=null || darkhastTaghirat.zamaemDarkhast.fileAkharinVaziatId!=null || darkhastTaghirat.zamaemDarkhast.fileShenaseBimegozarJadidId!=null
                || darkhastTaghirat.zamaemDarkhast.fileShenaseBimegozarGhadimId!=null || darkhastTaghirat.zamaemDarkhast.fileShenaseBimeshodeGhadimId!=null||darkhastTaghirat.zamaemDarkhast.fileKollieId!=null}">
    <tr>
        <th>
            نوع فایل
        </th>
        <th>
            شماره صفحه
        </th>
        <th>
            توضیحات
        </th>
        <th>
            لینک فایل
        </th>
        <c:if test="${darkhastBazkharid.state.id==640 ||darkhastBazkharid.state.id==10000 || darkhastBazkharid.state.id==10040 || darkhastBazkharid.state.id == 11000 || darkhastBazkharid.state.id == 11040 ||darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9040 || darkhastTaghirat.state.id == 9130 || darkhastTaghirat.state.id == 9150}">
            <th>
                حذف
            </th>
        </c:if>
    </tr>
</c:if>
<c:if test="${darkhastTaghirat.zamaemDarkhast.fileElhaghiyeId!=null}">

    <tr>
        <td>
            فرم درخواست الحاقیه
        </td>
        <td>
            -
        </td>
        <td>${darkhastTaghirat.zamaemDarkhast.fileElhaghiyeDescription}</td>
        <td>
            <a href="javascript:void(0);"
               onclick="window.location='/downloadFile?fileId=${darkhastTaghirat.zamaemDarkhast.fileElhaghiyeId}'">لینک
                دانلود فایل</a>
        </td>
        <c:if test="${darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9040 || darkhastTaghirat.state.id == 9130 || darkhastTaghirat.state.id == 9150}">
            <td>
                <a href="javascript:void(0);"
                   onclick="window.location='/removeZamaemDarkhastTaghirat?darkhastTaghirat.id=${darkhastTaghirat.id}&zamaemDarkhast.id=${darkhastTaghirat.zamaemDarkhast.id}&fileId=${darkhastTaghirat.zamaemDarkhast.fileElhaghiyeId}'">حذف
                    فایل</a>
            </td>
        </c:if>
    </tr>
</c:if>
<%--<c:if test="${darkhastTaghirat!=null&&darkhastTaghirat.zamaemDarkhast.fileElhaghiyeId==null}">--%>
    <%--<tr>--%>
        <%--<c:if test="${darkhastTaghirat.state.id==9010 || darkhastTaghirat.state.id == 9040 || darkhastTaghirat.state.id == 9130}">--%>
        <%--<td colspan="5">--%>
            <%--</c:if>--%>
            <%--<c:if test="${darkhastTaghirat.state.id!=9010}">--%>
        <%--<td colspan="4">--%>
            <%--</c:if>--%>
            <%--<p style="color:red">--%>
                <%--فرم درخواست الحاقیه آپلود نشده است.--%>
            <%--</p>--%>
        <%--</td>--%>
    <%--</tr>--%>
<%--</c:if>--%>
<c:if test="${darkhastTaghirat.zamaemDarkhast.fileAkharinVaziatId!=null}">

    <tr>
        <td>
            فرم آخرین صورت وضعیت بیمه نامه
        </td>
        <td>
            -
        </td>
        <td>${darkhastTaghirat.zamaemDarkhast.fileAkharinVaziatDescription}</td>
        <td>
            <a href="javascript:void(0);"
               onclick="window.location='/downloadFile?fileId=${darkhastTaghirat.zamaemDarkhast.fileAkharinVaziatId}'">لینک
                دانلود فایل</a>
        </td>
        <c:if test="${darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9040 || darkhastTaghirat.state.id == 9130 || darkhastTaghirat.state.id == 9150}">
            <td>
                <a href="javascript:void(0);"
                   onclick="window.location='/removeZamaemDarkhastTaghirat?darkhastTaghirat.id=${darkhastTaghirat.id}&zamaemDarkhast.id=${darkhastTaghirat.zamaemDarkhast.id}&fileId=${darkhastTaghirat.zamaemDarkhast.fileAkharinVaziatId}'">حذف
                    فایل</a>
            </td>
        </c:if>
    </tr>
</c:if>
<%--<c:if test="${darkhastTaghirat!=null&&darkhastTaghirat.zamaemDarkhast.fileAkharinVaziatId==null}">--%>
    <%--<tr>--%>
        <%--<c:if test="${darkhastTaghirat.state.id==9010 || darkhastTaghirat.state.id == 9040 || darkhastTaghirat.state.id == 9130}">--%>
        <%--<td colspan="5">--%>
            <%--</c:if>--%>
            <%--<c:if test="${darkhastTaghirat.state.id!=9010}">--%>
        <%--<td colspan="4">--%>
            <%--</c:if>--%>
            <%--<p style="color:red">--%>
                <%--فرم آخرین صورت وضعیت بیمه نامه آپلود نشده است.--%>
            <%--</p>--%>
        <%--</td>--%>
    <%--</tr>--%>
<%--</c:if>--%>
<c:if test="${darkhastTaghirat.zamaemDarkhast.fileShenaseBimegozarJadidId!=null}">

    <tr>
        <td>
            کپی کارت ملی و یا شناسنامه ی بیمه گذار جدید
        </td>
        <td>
            -
        </td>
        <td>${darkhastTaghirat.zamaemDarkhast.fileShenaseBimegozarJadidDescription}</td>
        <td>
            <a href="javascript:void(0);"
               onclick="window.location='/downloadFile?fileId=${darkhastTaghirat.zamaemDarkhast.fileShenaseBimegozarJadidId}'">لینک
                دانلود فایل</a>
        </td>
        <c:if test="${darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9040 || darkhastTaghirat.state.id == 9130 || darkhastTaghirat.state.id == 9150}">
            <td>
                <a href="javascript:void(0);"
                   onclick="window.location='/removeZamaemDarkhastTaghirat?darkhastTaghirat.id=${darkhastTaghirat.id}&zamaemDarkhast.id=${darkhastTaghirat.zamaemDarkhast.id}&fileId=${darkhastTaghirat.zamaemDarkhast.fileShenaseBimegozarJadidId}'">حذف
                    فایل</a>
            </td>
        </c:if>
    </tr>
</c:if>
<%--<c:if test="${darkhastTaghirat!=null&&darkhastTaghirat.zamaemDarkhast.fileShenaseBimegozarJadidId==null}">--%>
    <%--<tr>--%>
        <%--<c:if test="${darkhastTaghirat.state.id==9010 || darkhastTaghirat.state.id == 9040 || darkhastTaghirat.state.id == 9130}">--%>
        <%--<td colspan="5">--%>
            <%--</c:if>--%>
            <%--<c:if test="${darkhastTaghirat.state.id!=9010}">--%>
        <%--<td colspan="4">--%>
            <%--</c:if>--%>
            <%--<p style="color:red">--%>
                <%--کپی کارت ملی و یا شناسنامه ی بیمه گذار جدید آپلود نشده است.--%>
            <%--</p>--%>
        <%--</td>--%>
    <%--</tr>--%>
<%--</c:if>--%>
<c:if test="${darkhastTaghirat.zamaemDarkhast.fileShenaseBimegozarGhadimId!=null}">

    <tr>
        <td>
            کپی کارت ملی و یا شناسنامه ی بیمه گذار
        </td>
        <td>
            -
        </td>
        <td>${darkhastTaghirat.zamaemDarkhast.fileShenaseBimegozarGhadimDescription}</td>
        <td>
            <a href="javascript:void(0);"
               onclick="window.location='/downloadFile?fileId=${darkhastTaghirat.zamaemDarkhast.fileShenaseBimegozarGhadimId}'">لینک
                دانلود فایل</a>
        </td>
        <c:if test="${darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9040 || darkhastTaghirat.state.id == 9130 || darkhastTaghirat.state.id == 9150}">
            <td>
                <a href="javascript:void(0);"
                   onclick="window.location='/removeZamaemDarkhastTaghirat?darkhastTaghirat.id=${darkhastTaghirat.id}&zamaemDarkhast.id=${darkhastTaghirat.zamaemDarkhast.id}&fileId=${darkhastTaghirat.zamaemDarkhast.fileShenaseBimegozarGhadimId}'">حذف
                    فایل</a>
            </td>
        </c:if>
    </tr>
</c:if>
<%--<c:if test="${darkhastTaghirat!=null&&darkhastTaghirat.zamaemDarkhast.fileShenaseBimegozarGhadimId==null}">--%>
    <%--<tr>--%>
        <%--<c:if test="${darkhastTaghirat.state.id==9010 || darkhastTaghirat.state.id == 9040 || darkhastTaghirat.state.id == 9130}">--%>
        <%--<td colspan="5">--%>
            <%--</c:if>--%>
            <%--<c:if test="${darkhastTaghirat.state.id!=9010}">--%>
        <%--<td colspan="4">--%>
            <%--</c:if>--%>
            <%--<p style="color:red">--%>
                <%--کپی کارت ملی و یا شناسنامه ی بیمه گذار آپلود نشده است.--%>
            <%--</p>--%>
        <%--</td>--%>
    <%--</tr>--%>
<%--</c:if>--%>
<c:if test="${darkhastTaghirat.zamaemDarkhast.fileShenaseBimegozarGhadimId!=null}">

    <tr>
        <td>
            کپی کارت ملی و یا شناسنامه ی بیمه شده
        </td>
        <td>
            -
        </td>
        <td>${darkhastTaghirat.zamaemDarkhast.fileShenaseBimeshodeGhadimDescription}</td>
        <td>
            <a href="javascript:void(0);"
               onclick="window.location='/downloadFile?fileId=${darkhastTaghirat.zamaemDarkhast.fileShenaseBimeshodeGhadimId}'">لینک
                دانلود فایل</a>
        </td>
        <c:if test="${darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9040 || darkhastTaghirat.state.id == 9130 || darkhastTaghirat.state.id == 9150}">
            <td>
                <a href="javascript:void(0);"
                   onclick="window.location='/removeZamaemDarkhastTaghirat?darkhastTaghirat.id=${darkhastTaghirat.id}&zamaemDarkhast.id=${darkhastTaghirat.zamaemDarkhast.id}&fileId=${darkhastTaghirat.zamaemDarkhast.fileShenaseBimeshodeGhadimId}'">حذف
                    فایل</a>
            </td>
        </c:if>
    </tr>
</c:if>
<%--<c:if test="${darkhastTaghirat!=null&&darkhastTaghirat.zamaemDarkhast.fileShenaseBimeshodeGhadimId==null}">--%>
    <%--<tr>--%>
        <%--<c:if test="${darkhastTaghirat.state.id==9010 || darkhastTaghirat.state.id == 9040 || darkhastTaghirat.state.id == 9130}">--%>
        <%--<td colspan="5">--%>
            <%--</c:if>--%>
            <%--<c:if test="${darkhastTaghirat.state.id!=9010}">--%>
        <%--<td colspan="4">--%>
            <%--</c:if>--%>
            <%--<p style="color:red">--%>
                <%--کپی کارت ملی و یا شناسنامه ی بیمه شده آپلود نشده است.--%>
            <%--</p>--%>
        <%--</td>--%>
    <%--</tr>--%>
<%--</c:if>--%>
<c:if test="${darkhastBazkharid.zamaemDarkhast.fileDarkhastBahremandiId!=null}">

    <tr>
        <td>
            فرم درخواست بهره مندی از منافع
        </td>
        <td>
            -
        </td>
        <td>${darkhastBazkharid.zamaemDarkhast.fileDarkhastBahremandiDescription}</td>
        <td>
            <a href="javascript:void(0);"
               onclick="window.location='/downloadFile?fileId=${darkhastBazkharid.zamaemDarkhast.fileDarkhastBahremandiId}'">لینک
                دانلود فایل</a>
        </td>

        <c:if test="${darkhastBazkharid.state.id==1100 || darkhastBazkharid.state.id==1300 || darkhastBazkharid.state.id==1400 || darkhastBazkharid.state.id==1000 || darkhastBazkharid.state.id==10000 || darkhastBazkharid.state.id==10040|| darkhastBazkharid.state.id == 11000 || darkhastBazkharid.state.id == 11040|| darkhastBazkharid.state.id == 12010}">
            <td>
                <a href="javascript:void(0);"
                   onclick="window.location='/removeZamaemDarkhast?darkhastBazkharid.id=${darkhastBazkharid.id}&zamaemDarkhast.id=${darkhastBazkharid.zamaemDarkhast.id}&fileId=${darkhastBazkharid.zamaemDarkhast.fileDarkhastBahremandiId}'">حذف
                    فایل</a>
            </td>
        </c:if>
        <c:if test="${darkhastBazkharid.darkhastType=='EBTAL'}">
            <c:if test="${darkhastBazkharid.state.id==1000 || darkhastBazkharid.state.id==1400|| darkhastBazkharid.state.id == 1300}">
                <td>
                    <a href="javascript:void(0);"
                       onclick="window.location='/removeZamaemDarkhast?darkhastBazkharid.id=${darkhastBazkharid.id}&zamaemDarkhast.id=${darkhastBazkharid.zamaemDarkhast.id}&fileId=${darkhastBazkharid.zamaemDarkhast.fileDarkhastBahremandiId}'">حذف
                        فایل</a>
                </td>
            </c:if>
        </c:if>
    </tr>
</c:if>
<c:if test="${darkhastBazkharid.zamaemDarkhast.fileFishBedehiVamId!=null}">

    <tr>
        <td>
            فيش پرداخت بدهي وام
        </td>
        <td>
            -
        </td>
        <td>${darkhastBazkharid.zamaemDarkhast.fileFishBedehiVamDescription}</td>
        <td>
            <a href="javascript:void(0);"
               onclick="window.location='/downloadFile?fileId=${darkhastBazkharid.zamaemDarkhast.fileFishBedehiVamId}'">لینک
                دانلود فایل</a>
        </td>

        <c:if test="${darkhastBazkharid.state.id==12110 || darkhastBazkharid.state.id==12130}">
            <td>
                <a href="javascript:void(0);"
                   onclick="window.location='/removeZamaemDarkhast?darkhastBazkharid.id=${darkhastBazkharid.id}&zamaemDarkhast.id=${darkhastBazkharid.zamaemDarkhast.id}&fileId=${darkhastBazkharid.zamaemDarkhast.fileFishBedehiVamId}'">حذف
                    فایل</a>
            </td>
        </c:if>
    </tr>
</c:if>
<c:if test="${darkhastBazkharid.zamaemDarkhast.fileElamKhesaratId!=null}">

    <tr>
        <td>
            فرم اعلام خسارت
        </td>
        <td>
            -
        </td>
        <td>${darkhastBazkharid.zamaemDarkhast.fileElamKhesaratDescription}</td>
        <td>
            <a href="javascript:void(0);"
               onclick="window.location='/downloadFile?fileId=${darkhastBazkharid.zamaemDarkhast.fileElamKhesaratId}'">لینک
                دانلود فایل</a>
        </td>

        <c:if test="${darkhastBazkharid.state.id==640}">
            <td>
                <a href="javascript:void(0);"
                   onclick="window.location='/removeZamaemDarkhast?darkhastBazkharid.id=${darkhastBazkharid.id}&zamaemDarkhast.id=${darkhastBazkharid.zamaemDarkhast.id}&fileId=${darkhastBazkharid.zamaemDarkhast.fileElamKhesaratId}'">حذف
                    فایل</a>
            </td>
        </c:if>
    </tr>
</c:if>
<%--<c:if test="${darkhastTaghirat==null&&darkhastBazkharid.zamaemDarkhast.fileDarkhastBahremandiId==null}">--%>
    <%--<tr>--%>
        <%--<c:if test="${darkhastBazkharid.state.id==10000 || darkhastBazkharid.state.id==10040 || darkhastBazkharid.state.id == 11000 || darkhastBazkharid.state.id == 11040}">--%>
        <%--<td colspan="5">--%>
            <%--</c:if>--%>
            <%--<c:if test="${darkhastBazkharid.state.id!=10000 && darkhastBazkharid.state.id!=10040 && darkhastBazkharid.state.id != 11000 && darkhastBazkharid.state.id != 11040}">--%>
        <%--<td colspan="4">--%>
            <%--</c:if>--%>
            <%--<p style="color:red">--%>
                <%--فرم درخواست بهره مندی از منافع آپلود نشده است.--%>
            <%--</p>--%>
        <%--</td>--%>
    <%--</tr>--%>
<%--</c:if>--%>
<c:if test="${darkhastBazkharid.zamaemDarkhast.fileElameMafghudiId!=null}">
    <tr>
        <td>
            فرم اعلام مفقودی بیمه نامه
        </td>
        <td>
            -
        </td>
        <td>${darkhastBazkharid.zamaemDarkhast.fileElameMafghudiDescription}</td>
        <td>
            <a href="javascript:void(0);"
               onclick="window.location='/downloadFile?fileId=${darkhastBazkharid.zamaemDarkhast.fileElameMafghudiId}'">لینک
                دانلود فایل</a>
        </td>
        <c:if test="${darkhastBazkharid.state.id==1100 || darkhastBazkharid.state.id==1300 || darkhastBazkharid.state.id==1400 || darkhastBazkharid.state.id==1000 || darkhastBazkharid.state.id==10000 || darkhastBazkharid.state.id==10040|| darkhastBazkharid.state.id == 11000 || darkhastBazkharid.state.id == 11040}">
            <td>
                <a href="javascript:void(0);"
                   onclick="window.location='/removeZamaemDarkhast?darkhastBazkharid.id=${darkhastBazkharid.id}&zamaemDarkhast.id=${darkhastBazkharid.zamaemDarkhast.id}&fileId=${darkhastBazkharid.zamaemDarkhast.fileElameMafghudiId}'">حذف
                    فایل</a>
            </td>
        </c:if>
    </tr>
</c:if>
<%--<c:if test="${darkhastTaghirat==null&&darkhastBazkharid.zamaemDarkhast.fileElameMafghudiId==null&&darkhastBazkharid.darkhastType!='TASVIE_PISH_AZ_MOEDE_VAM'}">--%>
    <%--<c:if test="${darkhastBazkharid==null || (!darkhastBazkharid.darkhastType.toString().equals('VAM')&&!darkhastBazkharid.darkhastType.toString().equals('BARDASHT_AZ_ANDOKHTE'))}">--%>
        <%--<tr>--%>
            <%--<c:if test="${darkhastBazkharid.state.id==10000 || darkhastBazkharid.state.id==10040 || darkhastBazkharid.state.id == 11000 || darkhastBazkharid.state.id == 11040}">--%>
            <%--<td colspan="5">--%>
                <%--</c:if>--%>
                <%--<c:if test="${darkhastBazkharid.state.id!=10000&& darkhastBazkharid.state.id!=10040 && darkhastBazkharid.state.id != 11000 && darkhastBazkharid.state.id != 11040}">--%>

            <%--<td colspan="4">--%>
                <%--</c:if>--%>
                <%--<p style="color:red">--%>
                    <%--فرم اعلام مفقودی بیمه نامه آپلود نشده است.--%>
                <%--</p>--%>
            <%--</td>--%>

        <%--</tr>--%>
    <%--</c:if>--%>
<%--</c:if>--%>
<c:if test="${darkhastBazkharid.zamaemDarkhast.fileAdameTataboghEmzaId!=null}">
    <tr>
        <td>
            فرم عدم تطابق امضاء
        </td>
        <td>
            -
        </td>
        <td>${darkhastBazkharid.zamaemDarkhast.fileAdameTataboghEmzaDescription}</td>
        <td>
            <a href="javascript:void(0);"
               onclick="window.location='/downloadFile?fileId=${darkhastBazkharid.zamaemDarkhast.fileAdameTataboghEmzaId}'">لینک
                دانلود فایل</a>
        </td>
        <c:if test="${darkhastBazkharid.darkhastType=='EBTAL' || darkhastBazkharid.darkhastType=='BAZKHARID' }">
            <c:if test="${darkhastBazkharid.state.id==1000 || darkhastBazkharid.state.id==1400|| darkhastBazkharid.state.id == 1300}">
                <td>
                    <a href="javascript:void(0);"
                       onclick="window.location='/removeZamaemDarkhast?darkhastBazkharid.id=${darkhastBazkharid.id}&zamaemDarkhast.id=${darkhastBazkharid.zamaemDarkhast.id}&fileId=${darkhastBazkharid.zamaemDarkhast.fileAdameTataboghEmzaId}'">حذف
                        فایل</a>
                </td>
            </c:if>
        </c:if>

    </tr>
</c:if>
<c:if test="${darkhastBazkharid.zamaemDarkhast.fileSayerId!=null}">
    <tr>
        <td>
ســــایر
        </td>
        <td>
            -
        </td>
        <td>${darkhastBazkharid.zamaemDarkhast.fileSayerDescription}</td>
        <td>
            <a href="javascript:void(0);"
               onclick="window.location='/downloadFile?fileId=${darkhastBazkharid.zamaemDarkhast.fileSayerId}'">لینک
                دانلود فایل</a>
        </td>
        <c:if test="${darkhastBazkharid.darkhastType=='EBTAL' || darkhastBazkharid.darkhastType=='BAZKHARID' }">
            <c:if test="${darkhastBazkharid.state.id==1000 || darkhastBazkharid.state.id==1400|| darkhastBazkharid.state.id == 1300}">
                <td>
                    <a href="javascript:void(0);"
                       onclick="window.location='/removeZamaemDarkhast?darkhastBazkharid.id=${darkhastBazkharid.id}&zamaemDarkhast.id=${darkhastBazkharid.zamaemDarkhast.id}&fileId=${darkhastBazkharid.zamaemDarkhast.fileSayerId}'">حذف
                        فایل</a>
                </td>
            </c:if>
        </c:if>
    </tr>
</c:if>
<c:if test="${darkhastBazkharid.zamaemDarkhast.fileAgahiChapId!=null}">
    <tr>
        <td>
            آگهی چاپ شده در روزنامه
        </td>
        <td>
            -
        </td>
        <td>${darkhastBazkharid.zamaemDarkhast.fileAgahiChapDescription}</td>
        <td>
            <a href="javascript:void(0);"
               onclick="window.location='/downloadFile?fileId=${darkhastBazkharid.zamaemDarkhast.fileAgahiChapId}'">لینک
                دانلود فایل</a>
        </td>
        <c:if test="${darkhastBazkharid.state.id==1100 || darkhastBazkharid.state.id==1300 || darkhastBazkharid.state.id==1400 || darkhastBazkharid.state.id==1000 || darkhastBazkharid.state.id==10000 || darkhastBazkharid.state.id==10040 || darkhastBazkharid.state.id == 11000 || darkhastBazkharid.state.id == 11040}">
            <td>
                <a href="javascript:void(0);"
                   onclick="window.location='/removeZamaemDarkhast?darkhastBazkharid.id=${darkhastBazkharid.id}&zamaemDarkhast.id=${darkhastBazkharid.zamaemDarkhast.id}&fileId=${darkhastBazkharid.zamaemDarkhast.fileAgahiChapId}'">حذف
                    فایل</a>
            </td>
        </c:if>
    </tr>
</c:if>
<%--<c:if test="${darkhastTaghirat==null&&darkhastBazkharid.zamaemDarkhast.fileAgahiChapId==null&&darkhastBazkharid.darkhastType!='TASVIE_PISH_AZ_MOEDE_VAM'}">--%>
    <%--<c:if test="${darkhastBazkharid==null || (!darkhastBazkharid.darkhastType.toString().equals('VAM')&&!darkhastBazkharid.darkhastType.toString().equals('BARDASHT_AZ_ANDOKHTE'))}">--%>
        <%--<tr>--%>
            <%--<c:if test="${darkhastBazkharid.state.id==10000 || darkhastBazkharid.state.id==10040 || darkhastBazkharid.state.id == 11000 || darkhastBazkharid.state.id == 11040}">--%>
            <%--<td colspan="5">--%>
                <%--</c:if>--%>
                <%--<c:if test="${darkhastBazkharid.state.id!=10000&& darkhastBazkharid.state.id!=10040 && darkhastBazkharid.state.id != 11000 && darkhastBazkharid.state.id != 11040}">--%>
            <%--<td colspan="4">--%>
                <%--</c:if>--%>
                <%--<p style="color:red">--%>
                    <%--آگهی چاپ شده در روزنامه، آپلود نشده است.--%>
                <%--</p>--%>
            <%--</td>--%>
        <%--</tr>--%>
    <%--</c:if>--%>
<%--</c:if>--%>
<c:if test="${darkhastBazkharid.zamaemDarkhast.fileEhrazId!=null}">
    <tr>
        <td>
            فرم احراز هویت
        </td>
        <td>
            -
        </td>
        <td>${darkhastBazkharid.zamaemDarkhast.fileEhrazDescription}</td>
        <td>
            <a href="javascript:void(0);"
               onclick="window.location='/downloadFile?fileId=${darkhastBazkharid.zamaemDarkhast.fileEhrazId}'">لینک
                دانلود فایل</a>
        </td>
        <c:if test="${darkhastBazkharid.state.id==10060 || darkhastBazkharid.state.id == 11060}">
            <td>
                <a href="javascript:void(0);"
                   onclick="window.location='/removeZamaemDarkhast?darkhastBazkharid.id=${darkhastBazkharid.id}&zamaemDarkhast.id=${darkhastBazkharid.zamaemDarkhast.id}&fileId=${darkhastBazkharid.zamaemDarkhast.fileEhrazId}'">حذف
                    فایل</a>
            </td>
        </c:if>
    </tr>
</c:if>
<c:if test="${darkhastTaghirat.zamaemDarkhast.fileKollieId!=null}">
    <tr>
        <td>
کلیه مدارک الحاقیه
        </td>
        <td>
            -
        </td>
        <td>${darkhastTaghirat.zamaemDarkhast.fileKollieDescription}</td>
        <td>
            <a href="javascript:void(0);"
               onclick="window.location='/downloadFile?fileId=${darkhastTaghirat.zamaemDarkhast.fileKollieId}'">لینک
                دانلود فایل</a>
        </td>
        <c:if test="${darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9040 || darkhastTaghirat.state.id == 9130 || darkhastTaghirat.state.id == 9150}">
            <td>
                <a href="javascript:void(0);"
                   onclick="window.location='/removeZamaemDarkhastTaghirat?darkhastTaghirat.id=${darkhastTaghirat.id}&zamaemDarkhast.id=${darkhastTaghirat.zamaemDarkhast.id}&fileId=${darkhastTaghirat.zamaemDarkhast.fileKollieId}'">حذف
                    فایل</a>
            </td>
        </c:if>
    </tr>
</c:if>
</table>

<c:if test="${darkhastBazkharid.state.id==640 || darkhastBazkharid.state.id==11040 || darkhastBazkharid.state.id==10000 || darkhastBazkharid.state.id==11060 || darkhastBazkharid.state.id==10060 || darkhastBazkharid.state.id==10040 || darkhastBazkharid.state.id==1000 || darkhastBazkharid.state.id==1400 || darkhastBazkharid.state.id == 11000 || darkhastBazkharid.state.id == 12010|| darkhastBazkharid.state.id == 12050|| darkhastBazkharid.state.id == 12070 || darkhastTaghirat.state.id == 9010 || darkhastTaghirat.state.id == 9040 || darkhastTaghirat.state.id == 9130 || darkhastTaghirat.state.id == 9150}">
<table id="etmamuploadincase">
    <tr>
        <td colspan="4">
            <input type="button" onclick="checkForEtmamDarkhast();" value="اتمام فرآیند"/>
        </td>
    </tr>
</table>
</c:if>

</c:if>




<script type="text/javascript">
    function checkForEtmamDarkhast(){
        var checker = true;
        <c:if test="${(darkhastTaghirat.zamaemDarkhast.fileKollieId!=null)}">
            <c:if test="${darkhastTaghirat.state.id==9010}">
                $("#dakhastTransitionSelector").val(9001);
                submitTransitionForDarkhast();
            </c:if>
            <c:if test="${darkhastTaghirat.state.id==9130}">
                $("#dakhastTransitionSelector").val(9034);
                submitTransitionForDarkhast();
            </c:if>
            <c:if test="${darkhastTaghirat.state.id==9150}">
                $("#dakhastTransitionSelector").val(9041);
                submitTransitionForDarkhast();
            </c:if>
            checker = false;
        </c:if>
        <c:if test="${darkhastBazkharid!=null}">
            <c:if test="${darkhastBazkharid.darkhastType=='KHESARAT' || darkhastBazkharid.darkhastType=='EBTAL' || darkhastBazkharid.darkhastType=='TASVIE_PISH_AZ_MOEDE_VAM' || darkhastBazkharid.darkhastType=='BAZKHARID' || darkhastBazkharid.darkhastType=='BARDASHT_AZ_ANDOKHTE' || darkhastBazkharid.darkhastType=='VAM'}">
                checker=false;
                <c:if test="${darkhastBazkharid.darkhastType=='KHESARAT'}">
                    <c:if test="${(darkhastBazkharid.zamaemDarkhast.fileElamKhesaratId==null)}">
                        checker = true;
                    </c:if>
                    $("#dakhastTransitionSelector").val('600')
                </c:if>
                if (!checker)
                    submitTransitionForDarkhast();
            </c:if>
        </c:if>
        if(checker){
            <c:if test="${darkhastBazkharid.darkhastType=='KHESARAT'}">
                alertMessage("ضمائم مربوط به خسارت را آپلود کنید.");
            </c:if>
            <c:if test="${darkhastBazkharid.darkhastType!='KHESARAT'}">
                alertMessage("ضمائم مربوط به الحاقیه را آپلود کنید.");
            </c:if>
        }
    }

    function fillInput() {
        $("#uploadedfilehiddendarkhast").val($("#uploadedFiledarkhast").val());
        $("#zamaemDarkhastForm").submit();
    }

    function decideWhichUpload() {
    <c:if test="${darkhastBazkharid!=null}">
        $("#zamaemDarkhastForm").attr("action", "uploadZamaemDarkhast");
    </c:if>
    <c:if test="${darkhastTaghirat!=null}">
        $("#zamaemDarkhastForm").attr("action", "uploadZamaemDarkhastTaghirat");
    </c:if>
        $("#zamaemDarkhastForm").submit();
    }

    function updateTozihat()
    {
//        var str = "صفحه ";
//        str += $('#shomareSafhe_upload option:selected').text() + " ";
        var str = $('#noeFile_upload_drkh option:selected').text();
        $('#tozihat_upload_drkh').val(str);
    }
</script>
