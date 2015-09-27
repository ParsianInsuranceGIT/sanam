<%--
  Created by IntelliJ IDEA.
  User: ramtinb
  Date: 4/10/12
  Time: 10:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/jsp/taglibs.jsp" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<p class="heading ui-widget-header ui-corner-all ui-helper-clearfix">
    ایجاد پیشنهادات برای صدور جمعی موارد خاص
</p>
<form action="/sodureJamieKhas.action" method="post" enctype="multipart/form-data">
    <s:hidden key="gharardad.id"/>
    <s:hidden name="selectedMenu" value="5"/>
    <table class="inputList" style="width: 100%" align="right">
        <tr>
            <td colspan="2">
                <div style="width: 60%">
                    <input type="file" name="upload" id="jamiKhas_fileExcel" size="40">
                    &nbsp;<label>فایل اطلاعات پیشنهاد</label>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div style="width: 60%">
                    <input type="text" name="templatePishnehadRadif" id="templatePishnehadRadif" class="validate[required]" value=""/>
                    &nbsp;<label>کد رهگیری پیشنهاد به عنوان قالب</label>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="expandableTitleBar">
                    <p class="heading">
                        <span class="ui-icon ui-icon-plus" style="float:right;"></span>مشخصات بیمه گذار
                    </p>

                    <div class="content">
                        <table dir="rtl" cellpadding="2px" width="60%" cellspacing="0px"
                               style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
                            <tr>
                                <td>
                                    <input type="radio" id="mode11" name="bimeGozarSource" checked="checked"
                                           value="gharardad" />
                                </td>
                                <td>
                                    <label for="mode11">بیمه گذار همه پیشنهادها، شرکت طرف قرارداد باشد</label>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="radio" id="mode12" name="bimeGozarSource"
                                           value="excel" />
                                </td>
                                <td>
                                    <label for="mode12">مشخصات بیمه گذاران از طریق فایل اکسل در سیستم آپلود می شود</label>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="expandableTitleBar">
                    <p class="heading">
                        <span class="ui-icon ui-icon-plus" style="float:right;"></span>مشخصات بیمه شده
                    </p>

                    <div class="content">
                        <table dir="rtl" cellpadding="2px" width="60%" cellspacing="0px"
                               style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
                            <tr>
                                <td>
                                    <input type="radio" id="mode21" name="bimeShodeSource"
                                           value="bimegozar" />
                                </td>
                                <td>
                                    <label for="mode21">بیمه شده ها، همان بیمه گذاران پیشنهادها هستند</label>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="radio" id="mode22" name="bimeShodeSource" checked="checked"
                                           value="excel" />
                                </td>
                                <td>
                                    <label for="mode22">مشخصات بیمه شده ها از طریق فایل اکسل در سیستم آپلود می شود</label>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="expandableTitleBar">
                    <p class="heading">
                        <span class="ui-icon ui-icon-plus" style="float:right;"></span>مشخصات بیمه نامه
                    </p>

                    <div class="content">
                        <table dir="rtl" cellpadding="2px" width="60%" cellspacing="0px"
                               style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
                            <tr>
                                <td>
                                    <input type="radio" id="mode31" name="bimenameSource" checked="checked"
                                           value="template" />
                                </td>
                                <td>
                                    <label for="mode31">مشخصات بیمه نامه ها مطابق فایل قالب پر شود</label>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="radio" id="mode32" name="bimenameSource"
                                           value="excel" />
                                </td>
                                <td>
                                    <label for="mode32">مشخصات بیمه نامه ها از طریق فایل اکسل در سیستم آپلود می شود</label>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="expandableTitleBar">
                    <p class="heading">
                        <span class="ui-icon ui-icon-plus" style="float:right;"></span>سوالات عمومی از بیمه شده
                    </p>

                    <div class="content">
                        <table dir="rtl" cellpadding="2px" width="60%" cellspacing="0px"
                               style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
                            <tr>
                                <td>
                                    <input type="radio" id="mode41" name="soalateOmoomiSource" checked="checked"
                                           value="template" />
                                </td>
                                <td>
                                    <label for="mode41">سوالات عمومی مطابق فایل قالب پر شود</label>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="radio" id="mode42" name="soalateOmoomiSource"
                                           value="excel" />
                                </td>
                                <td>
                                    <label for="mode42">سوالات عمومی از طریق فایل اکسل در سیستم آپلود می شود</label>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="expandableTitleBar">
                    <p class="heading">
                        <span class="ui-icon ui-icon-plus" style="float:right;"></span>وضعیت سلامتی بیمه شده
                    </p>

                    <div class="content">
                        <table dir="rtl" cellpadding="2px" width="60%" cellspacing="0px"
                               style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
                            <tr>
                                <td>
                                    <input type="radio" id="mode51" name="vaziateSalamateBimeShodeSource" checked="checked"
                                           value="salem" />
                                </td>
                                <td>
                                    <label for="mode51">همه بیمه شده ها سالم می باشند</label>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="radio" id="mode52" name="vaziateSalamateBimeShodeSource"
                                           value="tafkik" />
                                </td>
                                <td>
                                    <label for="mode52">سوالات وضعیت سلامتی بیمه شده ها در هر پیشنهاد به تفکیک پاسخ داده می شود</label>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="expandableTitleBar">
                    <p class="heading">
                        <span class="ui-icon ui-icon-plus" style="float:right;"></span>وضعیت سلامتی خانواده بیمه شده
                    </p>

                    <div class="content">
                        <table dir="rtl" cellpadding="2px" width="60%" cellspacing="0px"
                               style="border-spacing:1px;margin-left:auto;margin-right:auto;" border="0">
                            <tr>
                                <td>
                                    <input type="radio" id="mode61" name="vaziateSalamateKhanevadeSource" checked="checked"
                                           value="parentexcel" />
                                </td>
                                <td>
                                    <label for="mode61"> وضعیت سلامتی پدر و مادر بیمه شده ها از طریق فایل اکسل در سیستم آپلود می شود</label>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="radio" id="mode62" name="vaziateSalamateKhanevadeSource"
                                           value="tafkik" />
                                </td>
                                <td>
                                    <label for="mode62">وضعیت سلامتی خانواده بیمه شده ها در هر پیشنهاد به تفکیک تعیین می گردد</label>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="ایجاد" />
            </td>
        </tr>
    </table>
</form>
