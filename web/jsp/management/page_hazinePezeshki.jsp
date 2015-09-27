<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>
    ثبت هزینه پزشکی
</title>
<head>

</head>
<body>
<s:actionmessage/>
<form action="/searchHazinePezashki" method="post">
    <table dir="rtl" align="center">
        <tr>
            <td>
                <label>از تاریخ صدور معرفی نامه</label>
                <input type="text" name="azTarikhSodur" id="azTarikhSodur" onkeyup=""
                       value='${azTarikhSodur}' class="validate[required,custom[date]] datePkr"/>
            </td>
            <td>
                <label>تا تاریخ صدور معرفی نامه</label>
                <input type="text" name="taTarikhSodur" id="taTarikhSodur" onkeyup=""
                       value='${taTarikhSodur}' class="datePkr"/>
            </td>
        </tr>
        <tr>
            <td>
                <label>کد رهگیری پیشنهاد</label>
                <input type="text" name="kodeRahgiri" id="kodeRahgiri" value="${kodeRahgiri}"/>
            </td>
            <td>
                <label>کد معرفی نامه</label>
                <input type="text" name="kodeMoarefi" id="kodeMoarefi" value="${kodeMoarefi}"
                       style="margin-right: 20px"/>
            </td>
        </tr>
        <tr>
            <td>
                <label>نام بیمه شده</label>
                <input type="text" name="nameBimeShode" id="nameBimeShode" value="${nameBimeshode}"
                       style="margin-right: 30px"/>
            </td>
            <td>
                <label style="margin-right: -20px">نام خانوادگی بیمه شده</label>
                <input type="text" name="nameKhanevadegiBimeShode" id="nameKhanevadegiBimeShode"
                       value="${nameKhanevadegiBimeShode}"/>
            </td>
        </tr>
        <tr>
            <td>
                <label>کد ملی بیمه شده</label>
                <input type="text" name="kodeMelli" id="kodeMelli" value="${kodeMelli}" style="margin-right: 8px"/>
            </td>
            <td>
                <label>وضعیت معرفی نامه</label>
                <select name="vaziatMoarefi">
                    <option value="0">در جریان</option>
                    <option value="1">باطل شده</option>
                    <option value="2">هر دو</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <label>نام کلینیک</label>
                <s:select list="clinicList" listValue="clinicName" listKey="id" name="clinicSearch" theme="simple"/>
            </td>
            <td>
                <input type="submit" value="جستجو">
            </td>
        </tr>
    </table>
</form>
<s:if test="searchResult.size()>0">
    <br/>
    <br/>
    <br/>
    <table class="jtable resultDets" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th>ردیف</th>
            <th>کد رهگیری پیشنهاد</th>
            <th>کد معرفی نامه</th>
            <th>نام کلینیک</th>
            <th>نام بیمه شده</th>
            <th>نام خانوادگی بیمه شده</th>
            <th>کد ملی بیمه شده</th>
            <th>وضعیت معرفی نامه</th>
            <th>تاریخ صدور معرفی نامه</th>
            <th>عملیات</th>
        </tr>
        </thead>
        <s:iterator value="searchResult" status="stat">


            <tr>

                <td><s:property value="%{#stat.index+1}"/></td>
                <td><s:property value="pishnehad.radif"/></td>
                <td><s:property value="code"/></td>
                <td><s:property
                        value="%{clinicNameSayer==null || clinicNameSayer.equals('')?clinic.clinicName:clinicNameSayer}"/></td>
                <td><s:property value="pishnehad.bimeShode.shakhs.name"/></td>
                <td><s:property value="pishnehad.bimeShode.shakhs.nameKhanevadegi"/></td>
                <td><s:property value="pishnehad.bimeShode.shakhs.kodeMelliShenasayi"/></td>
                <td><s:property value="vaziatFarsi()"/></td>
                <td><s:property value="tarikhSodur"/></td>
                <td>
                    <s:if test="%{!hazinePezeshki.equals('')}">
                        <input type="button" value="ویرایش"
                               onclick="window.location='/detailHazinePezashki?moarefiname.id=<s:property value="id"/>'"
                               style="color: #aa6666;">
                    </s:if>
                    <s:else>
                        <input type="button" value="نمایش"
                               onclick="window.location='/detailHazinePezashki?moarefiname.id=<s:property value="id"/>'">
                    </s:else>

                </td>

            </tr>
        </s:iterator>
    </table>
</s:if>
</body>
</html>