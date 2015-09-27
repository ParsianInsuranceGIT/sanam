<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: 4/7/11
  Time: 2:22 PM 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/estelam/estelam_js.jsp"%>
<script type="text/javascript" src="../../js/validation/estelam_valFuncs.js"></script>
<div style="display:none"><%@include file="/jsp/estelam/estelam_moshakasateBimeShode.jsp"%></div>
<div class="expandableTitleBar" id="poosheshasli">
    <p class="heading">
        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
        پوشش های اصلی</p>

    <div class="content_xx" id="poosheshasli_content">
        <%@include file="/jsp/estelam/estelam_moshakasateBimeName.jsp"%>
    </div>
</div>
<div class="expandableTitleBar">
    <p class="heading">
        <span class="ui-icon ui-icon-plus" style="float:right;"></span>
        پوشش های اضافی</p>

    <div class="content_xx" id="poosheshezafi_content">
        <%@include file="/jsp/estelam/estelam_posheshHayeEzafi.jsp"%>
    </div>
</div>
<input type="hidden" id="zelzele" name="estelam.pooshesh_fot_dar_asar_zelzele" value="no"/>
