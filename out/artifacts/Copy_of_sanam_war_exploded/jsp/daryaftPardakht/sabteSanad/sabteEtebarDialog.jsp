<%@ page import="com.bitarts.parsian.model.asnadeSodor.Credebit" %>
<%@ page import="com.bitarts.parsian.Core.Constant" %>
<%@ page import="com.bitarts.parsian.Core.BankNameConstant" %>
<%@page import="com.bitarts.common.util.DateUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron1
  Date: 8/17/11
  Time: 5:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
var current_date = '<%=DateUtil.getCurrentDate()%>';
    var sabteEtebareDastiData;
    var credebitTypes_divNames={DARYAFTE_ELECTRONICI:"#elecEtebarInfo",
        DARYAFTE_CHECK:"#checkEtebarInfo",
        DARYAFTE_CHECK_NAMAYANDE:"#checkEtebarInfo",
        DARYAFTE_FISH:"#fishEtebarInfo",
        DARYAFTE_FISH_NAMAYANDE:"#fishEtebarInfo",
        PARDAKHT_SHENASEDAR:"#fishAutomobilEtebarInfo",
        CASH:"#cashEtebarInfo",
        CASH_NAMAYANDE:"#cashEtebarInfo",
        VEHICLE_HAGHBIME_BARGASHTI:"#haghbimenameBargashyiEtebarInfo",
        HESAB_FI_MA_BEYN:"#hesabFimabeynEtebarInfo",
        AZ_MAHALLE_TABLIGHAT:"#azMahalTabInfo",
        AZ_MAHALLE_TABLIGHAT_MANUAL:"#azMahalTabManualInfo",
        KASR_AZ_HOGHUGH:"#kasrAzHaghInfo",
        AZ_MAHALE_HAZINE_BIME_DARAYIHA:"#azMahaleHazineBimeDarayihaInfo",
        ETEBAR_DARMAN_KHANEVADE:"#darmanKhanevadeInfo",
        KASR_AZ_KARMOZD: "#kasrAzKarmozdInfo"}

    var credebitTypes_formNames={DARYAFTE_ELECTRONICI:"#sabteEtebareDasti_electronic",
        DARYAFTE_CHECK:"#sabteEtebareDasti_check",
        DARYAFTE_CHECK_NAMAYANDE:"#sabteEtebareDasti_check",
        DARYAFTE_FISH:"#sabteEtebareDasti_fish",
        DARYAFTE_FISH_NAMAYANDE:"#sabteEtebareDasti_fish",
        PARDAKHT_SHENASEDAR:"#sabteEtebareDasti_fishAutomobil",
        CASH:"#sabteEtebareDasti_cash",
        CASH_NAMAYANDE:"#sabteEtebareDasti_cash",
        VEHICLE_HAGHBIME_BARGASHTI:"#sabteEtebareDasti_haghbimenameBargashyi",
        HESAB_FI_MA_BEYN:"#sabteEtebareDasti_hesabFimabeyn",
        AZ_MAHALLE_TABLIGHAT:"#sabteEtebareDasti_azMahalTab",
        AZ_MAHALLE_TABLIGHAT_MANUAL:"#sabteEtebareDasti_azMahalTab_manual",
        KASR_AZ_HOGHUGH:"#sabteEtebareDasti_kasrAzHagh",
        AZ_MAHALE_HAZINE_BIME_DARAYIHA:"#sabteEtebareDasti_azMahaleHazineBimeDarayiha",
        ETEBAR_DARMAN_KHANEVADE:"#sabteEtebareDasti_darmanKhanevade",
        KASR_AZ_KARMOZD:"#sabteEtebareDasti_kasrAzKarmozd"}
    function chg_etebarType(type){
        //b-h
        for(var key in credebitTypes_divNames) {
            if (credebitTypes_divNames.hasOwnProperty(key)){
                if(key == type)
                  $(credebitTypes_divNames[key]).show();
                else
                    $(credebitTypes_divNames[key]).hide();
            }
        }
        if(type == "DARYAFTE_CHECK" || type == "DARYAFTE_CHECK_NAMAYANDE")	$(credebitTypes_divNames[type]).show();
        if(type == "DARYAFTE_FISH" || type == "DARYAFTE_FISH_NAMAYANDE")	$(credebitTypes_divNames[type]).show();
        if(type == "CASH" || type == "CASH_NAMAYANDE")	$(credebitTypes_divNames[type]).show();

        $('#elecEtebarInfo #fishAmountElec').attr('disabled', true);
        $('#fishEtebarInfo #fishAmount').removeAttr('disabled');
        if(type=='DARYAFTE_ELECTRONICI') {
            $('#elecEtebarInfo #fishAmountElec').removeAttr('disabled');
            $('#fishEtebarInfo #fishAmount').attr('disabled', true);
         }
        if(type=='DARYAFTE_CHECK')
            $('#fishEtebarInfo #fishAmount').attr('disabled', true);
        if(type=='DARYAFTE_FISH_NAMAYANDE')
            $('#sh_fsh').show();
        sabteEtebareDastiData = $(credebitTypes_formNames[type]).serializeArray();
        sabteEtebareDastiData.push({name: 'credebit.namayande.id', value:$('#nama_id_etebar_add').val()});
        sabteEtebareDastiData.push({name: 'credebit.namayande.name', value:$('#nama_name_etebar_add').val()});
        <%--if(type == '<%=Credebit.CredebitType.DARYAFTE_ELECTRONICI%>') //sabte etebar dasti electronic--%>
        <%--{--%>
            <%--$('#cashEtebarInfo').hide();--%>
            <%--$('#hesabFimabeynEtebarInfo').hide();--%>
            <%--$('#haghbimenameBargashyiEtebarInfo').hide();--%>
            <%--$('#fishAutomobilEtebarInfo').hide();--%>
            <%--$('#fishEtebarInfo').hide();--%>
            <%--$('#kasrAzHaghInfo').hide();--%>
            <%--$('#azMahaleHazineBimeDarayihaInfo').hide();--%>
            <%--$('#darmanKhanevadeInfo').hide();--%>
            <%--$('#checkEtebarInfo').hide();--%>
            <%--$('#azMahalTabInfo').hide();--%>
            <%--$('#azMahalTabManualInfo').hide();--%>
            <%--$('#elecEtebarInfo').show();--%>
            <%--$('#elecEtebarInfo #fishAmountElec').removeAttr('disabled');--%>
            <%--$('#fishEtebarInfo #fishAmount').attr('disabled', true);--%>
            <%--sabteEtebareDastiData = $('#sabteEtebareDasti_electronic').serializeArray();--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.id', value:$('#nama_id_etebar_add').val()});--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.name', value:$('#nama_name_etebar_add').val()});--%>
        <%--}else if(type == '<%=Credebit.CredebitType.DARYAFTE_CHECK%>'|| type == '<%=Credebit.CredebitType.DARYAFTE_CHECK_NAMAYANDE%>'){--%>
            <%--$('#cashEtebarInfo').hide();--%>
            <%--$('#hesabFimabeynEtebarInfo').hide();--%>
            <%--$('#haghbimenameBargashyiEtebarInfo').hide();--%>
            <%--$('#fishAutomobilEtebarInfo').hide();--%>
            <%--$('#fishEtebarInfo').hide();--%>
            <%--$('#checkEtebarInfo').show();--%>
            <%--$('#azMahalTabInfo').hide();--%>
            <%--$('#azMahalTabManualInfo').hide();--%>
            <%--$('#kasrAzHaghInfo').hide();--%>
            <%--$('#azMahaleHazineBimeDarayihaInfo').hide();--%>
            <%--$('#darmanKhanevadeInfo').hide();--%>
            <%--$('#elecEtebarInfo').hide();--%>
            <%--$('#elecEtebarInfo #fishAmountElec').attr('disabled',true);--%>
            <%--$('#fishEtebarInfo #fishAmount').attr('disabled', true);--%>
            <%--sabteEtebareDastiData = $('#sabteEtebareDasti_check').serializeArray();--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.id', value: $('#nama_id_etebar_add').val()});--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.name', value: $('#nama_name_etebar_add').val()});--%>
        <%--}else if (type == '<%=Credebit.CredebitType.DARYAFTE_FISH%>'){--%>
            <%--$('#cashEtebarInfo').hide();--%>
            <%--$('#hesabFimabeynEtebarInfo').hide();--%>
            <%--$('#haghbimenameBargashyiEtebarInfo').hide();--%>
            <%--$('#fishAutomobilEtebarInfo').hide();--%>
            <%--$('#fishEtebarInfo').show();--%>
            <%--$('#elecEtebarInfo').hide();--%>
            <%--$('#checkEtebarInfo').hide();--%>
            <%--$('#sh_fsh').show();--%>
            <%--$('#kasrAzHaghInfo').hide();--%>
            <%--$('#azMahaleHazineBimeDarayihaInfo').hide();--%>
            <%--$('#darmanKhanevadeInfo').hide();--%>
            <%--$('#azMahalTabInfo').hide();--%>
            <%--$('#azMahalTabManualInfo').hide();--%>
            <%--$('#elecEtebarInfo #fishAmountElec').attr('disabled', true);--%>
            <%--$('#fishEtebarInfo #fishAmount').removeAttr('disabled');--%>
            <%--$('#daryaft_fish_serialized').val('<%=Credebit.CredebitType.DARYAFTE_FISH.toString()%>');--%>
            <%--sabteEtebareDastiData = $('#sabteEtebareDasti_fish').serialize();--%>
        <%--}else if (type == '<%=Credebit.CredebitType.DARYAFTE_FISH_NAMAYANDE%>')--%>
        <%--{--%>
            <%--$('#cashEtebarInfo').hide();--%>
            <%--$('#hesabFimabeynEtebarInfo').hide();--%>
            <%--$('#haghbimenameBargashyiEtebarInfo').hide();--%>
            <%--$('#sh_fsh').show();--%>
            <%--$('#fishAutomobilEtebarInfo').hide();--%>
            <%--$('#fishEtebarInfo').show();--%>
            <%--$('#kasrAzHaghInfo').hide();--%>
            <%--$('#azMahaleHazineBimeDarayihaInfo').hide();--%>
            <%--$('#darmanKhanevadeInfo').hide();--%>
            <%--$('#elecEtebarInfo').hide();--%>
            <%--$('#azMahalTabInfo').hide();--%>
            <%--$('#azMahalTabManualInfo').hide();--%>
            <%--$('#checkEtebarInfo').hide();--%>
            <%--$('#elecEtebarInfo #fishAmountElec').attr('disabled', true);--%>
            <%--$('#fishEtebarInfo #fishAmount').removeAttr('disabled');--%>
            <%--$('#daryaft_fish_serialized').val('<%=Credebit.CredebitType.DARYAFTE_FISH_NAMAYANDE.toString()%>');--%>
            <%--sabteEtebareDastiData = $('#sabteEtebareDasti_fish').serializeArray();--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.id', value: $('#nama_id_etebar_add').val()});--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.name', value: $('#nama_name_etebar_add').val()});--%>
        <%--}else if (type == "<%=Credebit.CredebitType.PARDAKHT_SHENASEDAR%>"){--%>
            <%--$('#cashEtebarInfo').hide();--%>
            <%--$('#hesabFimabeynEtebarInfo').hide();--%>
            <%--$('#haghbimenameBargashyiEtebarInfo').hide();--%>
            <%--$('#fishAutomobilEtebarInfo').show();--%>
            <%--$('#kasrAzHaghInfo').hide();--%>
            <%--$('#azMahaleHazineBimeDarayihaInfo').hide();--%>
            <%--$('#darmanKhanevadeInfo').hide();--%>
            <%--$('#fishEtebarInfo').hide();--%>
            <%--$('#elecEtebarInfo').hide();--%>
            <%--$('#azMahalTabInfo').hide();--%>
            <%--$('#azMahalTabManualInfo').hide();--%>
            <%--$('#checkEtebarInfo').hide();--%>
            <%--$('#elecEtebarInfo #fishAmountElec').attr('disabled', true);--%>
            <%--$('#fishEtebarInfo #fishAmount').removeAttr('disabled');--%>
            <%--sabteEtebareDastiData = $('#sabteEtebareDasti_fishAutomobil').serializeArray();--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.id', value: $('#nama_id_etebar_add').val()});--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.name', value: $('#nama_name_etebar_add').val()});--%>
        <%--}--%>
        <%--else if (type == "<%=Credebit.CredebitType.CASH%>" || type == "<%=Credebit.CredebitType.CASH_NAMAYANDE%>"){--%>
            <%--$('#cashEtebarInfo').show();--%>
            <%--$('#hesabFimabeynEtebarInfo').hide();--%>
            <%--$('#haghbimenameBargashyiEtebarInfo').hide();--%>
            <%--$('#fishAutomobilEtebarInfo').hide();--%>
            <%--$('#fishEtebarInfo').hide();--%>
            <%--$('#elecEtebarInfo').hide();--%>
            <%--$('#kasrAzHaghInfo').hide();--%>
            <%--$('#azMahaleHazineBimeDarayihaInfo').hide();--%>
            <%--$('#darmanKhanevadeInfo').hide();--%>
            <%--$('#azMahalTabInfo').hide();--%>
            <%--$('#azMahalTabManualInfo').hide();--%>
            <%--$('#checkEtebarInfo').hide();--%>
            <%--$('#elecEtebarInfo #fishAmountElec').attr('disabled', true);--%>
            <%--$('#fishEtebarInfo #fishAmount').removeAttr('disabled');--%>
            <%--sabteEtebareDastiData = $('#sabteEtebareDasti_cash').serializeArray();--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.id', value: $('#nama_id_etebar_add').val()});--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.name', value: $('#nama_name_etebar_add').val()});--%>
        <%--}else if (type == "<%=Credebit.CredebitType.VEHICLE_HAGHBIME_BARGASHTI%>"){--%>
            <%--$('#cashEtebarInfo').hide();--%>
            <%--$('#hesabFimabeynEtebarInfo').hide();--%>
            <%--$('#haghbimenameBargashyiEtebarInfo').show();--%>
            <%--$('#fishAutomobilEtebarInfo').hide();--%>
            <%--$('#fishEtebarInfo').hide();--%>
            <%--$('#elecEtebarInfo').hide();--%>
            <%--$('#azMahalTabInfo').hide();--%>
            <%--$('#azMahalTabManualInfo').hide();--%>
            <%--$('#kasrAzHaghInfo').hide();--%>
            <%--$('#azMahaleHazineBimeDarayihaInfo').hide();--%>
            <%--$('#darmanKhanevadeInfo').hide();--%>
            <%--$('#checkEtebarInfo').hide();--%>
            <%--$('#elecEtebarInfo #fishAmountElec').attr('disabled', true);--%>
            <%--$('#fishEtebarInfo #fishAmount').removeAttr('disabled');--%>
            <%--sabteEtebareDastiData = $('#sabteEtebareDasti_haghbimenameBargashyi').serializeArray();--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.id', value: $('#nama_id_etebar_add').val()});--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.name', value: $('#nama_name_etebar_add').val()});--%>
        <%--}else if (type == "<%=Credebit.CredebitType.HESAB_FI_MA_BEYN%>"){--%>
            <%--$('#cashEtebarInfo').hide();--%>
            <%--$('#hesabFimabeynEtebarInfo').show();--%>
            <%--$('#haghbimenameBargashyiEtebarInfo').hide();--%>
            <%--$('#darmanKhanevadeInfo').hide();--%>
            <%--$('#fishAutomobilEtebarInfo').hide();--%>
            <%--$('#fishEtebarInfo').hide();--%>
            <%--$('#elecEtebarInfo').hide();--%>
            <%--$('#azMahalTabInfo').hide();--%>
            <%--$('#azMahalTabManualInfo').hide();--%>
            <%--$('#checkEtebarInfo').hide();--%>
            <%--$('#kasrAzHaghInfo').hide();--%>
            <%--$('#azMahaleHazineBimeDarayihaInfo').hide();--%>
            <%--$('#elecEtebarInfo #fishAmountElec').attr('disabled', true);--%>
            <%--$('#fishEtebarInfo #fishAmount').removeAttr('disabled');--%>
            <%--sabteEtebareDastiData = $('#sabteEtebareDasti_hesabFimabeyn').serializeArray();--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.id', value: $('#nama_id_etebar_add').val()});--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.name', value: $('#nama_name_etebar_add').val()});--%>
        <%--}--%>
        <%--else if (type == "<%=Credebit.CredebitType.AZ_MAHALLE_TABLIGHAT%>")--%>
        <%--{--%>
            <%--$('#cashEtebarInfo').hide();--%>
            <%--$('#hesabFimabeynEtebarInfo').hide();--%>
            <%--$('#haghbimenameBargashyiEtebarInfo').hide();--%>
            <%--$('#fishAutomobilEtebarInfo').hide();--%>
            <%--$('#fishEtebarInfo').hide();--%>
            <%--$('#elecEtebarInfo').hide();--%>
            <%--$('#checkEtebarInfo').hide();--%>
            <%--$('#kasrAzHaghInfo').hide();--%>
            <%--$('#azMahaleHazineBimeDarayihaInfo').hide();--%>
            <%--$('#darmanKhanevadeInfo').hide();--%>
            <%--$('#azMahalTabInfo').show();--%>
            <%--$('#azMahalTabManualInfo').hide();--%>
            <%--$('#elecEtebarInfo #fishAmountElec').attr('disabled', true);--%>
            <%--$('#fishEtebarInfo #fishAmount').removeAttr('disabled');--%>
            <%--sabteEtebareDastiData = $('#sabteEtebareDasti_azMahalTab').serializeArray();--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.id', value: $('#nama_id_etebar_add').val()});--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.name', value: $('#nama_name_etebar_add').val()});--%>
        <%--}--%>
        <%--else if (type == "<%=Credebit.CredebitType.AZ_MAHALLE_TABLIGHAT_MANUAL%>")--%>
        <%--{--%>
            <%--$('#cashEtebarInfo').hide();--%>
            <%--$('#hesabFimabeynEtebarInfo').hide();--%>
            <%--$('#haghbimenameBargashyiEtebarInfo').hide();--%>
            <%--$('#fishAutomobilEtebarInfo').hide();--%>
            <%--$('#fishEtebarInfo').hide();--%>
            <%--$('#elecEtebarInfo').hide();--%>
            <%--$('#checkEtebarInfo').hide();--%>
            <%--$('#kasrAzHaghInfo').hide();--%>
            <%--$('#azMahaleHazineBimeDarayihaInfo').hide();--%>
            <%--$('#darmanKhanevadeInfo').hide();--%>
            <%--$('#azMahalTabInfo').hide();--%>
            <%--$('#azMahalTabManualInfo').show();--%>
            <%--$('#elecEtebarInfo #fishAmountElec').attr('disabled', true);--%>
            <%--$('#fishEtebarInfo #fishAmount').removeAttr('disabled');--%>
            <%--sabteEtebareDastiData = $('#sabteEtebareDasti_azMahalTab_manual').serializeArray();--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.id', value: $('#nama_id_etebar_add').val()});--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.name', value: $('#nama_name_etebar_add').val()});--%>
        <%--}--%>
        <%--else if (type == "<%=Credebit.CredebitType.KASR_AZ_HOGHUGH%>")--%>
        <%--{--%>
            <%--$('#cashEtebarInfo').hide();--%>
            <%--$('#hesabFimabeynEtebarInfo').hide();--%>
            <%--$('#haghbimenameBargashyiEtebarInfo').hide();--%>
            <%--$('#fishAutomobilEtebarInfo').hide();--%>
            <%--$('#fishEtebarInfo').hide();--%>
            <%--$('#elecEtebarInfo').hide();--%>
            <%--$('#checkEtebarInfo').hide();--%>
            <%--$('#kasrAzHaghInfo').show();--%>
            <%--$('#azMahaleHazineBimeDarayihaInfo').hide();--%>
            <%--$('#darmanKhanevadeInfo').hide();--%>
            <%--$('#azMahalTabInfo').hide();--%>
            <%--$('#azMahalTabManualInfo').hide();--%>
            <%--$('#elecEtebarInfo #fishAmountElec').attr('disabled', true);--%>
            <%--$('#fishEtebarInfo #fishAmount').removeAttr('disabled');--%>
            <%--sabteEtebareDastiData = $('#sabteEtebareDasti_kasrAzHagh').serializeArray();--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.id', value: $('#nama_id_etebar_add').val()});--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.name', value: $('#nama_name_etebar_add').val()});--%>
        <%--}--%>
        <%--else if (type == "<%=Credebit.CredebitType.AZ_MAHALE_HAZINE_BIME_DARAYIHA%>")--%>
        <%--{--%>
            <%--$('#cashEtebarInfo').hide();--%>
            <%--$('#hesabFimabeynEtebarInfo').hide();--%>
            <%--$('#haghbimenameBargashyiEtebarInfo').hide();--%>
            <%--$('#fishAutomobilEtebarInfo').hide();--%>
            <%--$('#fishEtebarInfo').hide();--%>
            <%--$('#elecEtebarInfo').hide();--%>
            <%--$('#checkEtebarInfo').hide();--%>
            <%--$('#kasrAzHaghInfo').hide();--%>
            <%--$('#azMahaleHazineBimeDarayihaInfo').show();--%>
            <%--$('#darmanKhanevadeInfo').hide();--%>
            <%--$('#azMahalTabInfo').hide();--%>
            <%--$('#azMahalTabManualInfo').hide();--%>
            <%--$('#elecEtebarInfo #fishAmountElec').attr('disabled', true);--%>
            <%--$('#fishEtebarInfo #fishAmount').removeAttr('disabled');--%>
            <%--sabteEtebareDastiData = $('#sabteEtebareDasti_azMahaleHazineBimeDarayiha').serializeArray();--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.id', value: $('#nama_id_etebar_add').val()});--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.name', value: $('#nama_name_etebar_add').val()});--%>
        <%--}--%>
        <%--else if (type == "<%=Credebit.CredebitType.ETEBAR_DARMAN_KHANEVADE%>")--%>
        <%--{--%>
            <%--$('#cashEtebarInfo').hide();--%>
            <%--$('#hesabFimabeynEtebarInfo').hide();--%>
            <%--$('#haghbimenameBargashyiEtebarInfo').hide();--%>
            <%--$('#fishAutomobilEtebarInfo').hide();--%>
            <%--$('#fishEtebarInfo').hide();--%>
            <%--$('#elecEtebarInfo').hide();--%>
            <%--$('#checkEtebarInfo').hide();--%>
            <%--$('#kasrAzHaghInfo').hide();--%>
            <%--$('#azMahaleHazineBimeDarayihaInfo').hide();--%>
            <%--$('#darmanKhanevadeInfo').show();--%>
            <%--$('#azMahalTabInfo').hide();--%>
            <%--$('#azMahalTabManualInfo').hide();--%>
            <%--$('#elecEtebarInfo #fishAmountElec').attr('disabled', true);--%>
            <%--$('#fishEtebarInfo #fishAmount').removeAttr('disabled');--%>
            <%--sabteEtebareDastiData = $('#sabteEtebareDasti_darmanKhanevade').serializeArray();--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.id', value: $('#nama_id_etebar_add').val()});--%>
            <%--sabteEtebareDastiData.push({name: 'credebit.namayande.name', value: $('#nama_name_etebar_add').val()});--%>
        <%--}--%>
    }

    function editCheckEtebarat()
    {
        $('#noeEtebat').hide();
        $('#namayandeW').hide();
        $('#cashEtebarInfo').hide();
        $('#hesabFimabeynEtebarInfo').hide();
        $('#haghbimenameBargashyiEtebarInfo').hide();
        $('#fishAutomobilEtebarInfo').hide();
        $('#fishEtebarInfo').hide();
        $('#checkEtebarInfo').show();
        $('#azMahalTabInfo').hide();
        $('#azMahalTabManualInfo').hide();
        $('#kasrAzHaghInfo').hide();
        $('#elecEtebarInfo').hide();
        $('#elecEtebarInfo #fishAmountElec').attr('disabled',true);
        $('#fishEtebarInfo #fishAmount').attr('disabled', true);
        sabteEtebareDastiData = $('#sabteEtebareDasti_check').serializeArray();
        sabteEtebareDastiData.push({name: 'credebit.namayande.id', value: $('#nama_id_etebar_add').val()});
        sabteEtebareDastiData.push({name: 'credebit.namayande.name', value: $('#nama_name_etebar_add').val()});

    }
    function clearAllEtebar(){
        $("#sahebeEtebarCash").val("");
        $("#cashVosoulDate").val("");
        $("#cashAmount").val("");
        $("#cashTozihateEtebar").val("");
        $("#sahebeEtebarHBB").val("");
        $("#sarresidDateHBB").val("");
        $("#amountHBB").val("");
        $("#identifierEtebarHBB").val("");
        $("#tozihateEtebarHBB").val("");
        $("#sahebeEtebarAutomobil").val("");
        $("#shenaseMoshtariAutomobil").val("");
        $("#shomareMoshtariAutomobil").val("");
        $("#bankAutomobil").val("");
        $("#fishAmountAutomobil").val("");
        $("#tozihateEtebarAutomobil").val("");
        $("#sahebeEtebar").val("");
        $("#bank").val("");
        $("#fishAmount").val("");
        $("#createdDate").val("");
        $("#saatepardakht").val("");
        $("#kodeShobe").val("");
        $("#tozihateEtebar").val("");
        $("#tarikh").val("");
        $("#shenaseMoshtariElec").val("");
        $("#bankElec").val("");
        $("#fishAmountElec").val("");
        $("#hesabBanki").val("");
        $("#shomareCheck").val("");
        $("#tarikhSarresid").val("");
        $("#checkAmount").val("");
        $("#rcptName").val("");
        $("#tozihateCheck").val("");
        $("#branchNameCheck").val("");
        $("#accountOwnerNameCheck").val("");
        $("#seriCheck").val("");
        $("#bankNameCheck").val("");
        $("#branchCodeCheck").val("");
        $("#vosulDateCheck").val("");
        $("#hesabFimabeynAmount").val("");
        $("#hesabFimabeynTozihateEtebar").val("");

    }
    function loadEtebarFish(msg){
        var myAttributes = msg.split("@")[0].split("#");
        var gr = jQuery("#etebarat_tbl").jqGrid('getGridParam', 'selrow');
        if (gr != null) {
            var gr_data = jQuery("#etebarat_tbl").jqGrid('getRowData', gr);
        }

        if (myAttributes[1] != undefined){
            if (myAttributes[1].split("=")[1].contains("17123130")){
                $("#bankMaghsad").val("تجارت - سپهبد قرنی (17123130)");
            } else if (myAttributes[1].split("=")[1].contains("81011989")){
                $("#bankMaghsad").val("پارسیان - ونک (81011989)");
            }else if (myAttributes[1].split("=")[1].contains("0200234164006")){
                $("#bankMaghsad").val("پارسیان - بلوار کشاورز (0200234164006)");
            }else if (myAttributes[1].split("=")[1].contains("0201136462000")){
                $("#bankMaghsad").val("تجارت الکترونیک - میرداماد شرقی (0201136462000)");
            }

            $("#noeEtebareDarHaleSabt").val(myAttributes[0].split("=")[1].trim());

            $("#sahebeEtebar").val(myAttributes[2].split("=")[1]);
            $("#shenaseMoshtari").val(myAttributes[3].split("=")[1]);
            $("#bank").val(myAttributes[4].split("=")[1]);
            $("#fishAmount").val(myAttributes[5].split("=")[1]);
            $("#createdDate").val(myAttributes[6].split("=")[1]);
            $("#saatepardakht").val(myAttributes[7].split("=")[1]);
            $("#kodeShobe").val(myAttributes[8].split("=")[1]);
            $("#tozihateEtebar").val(myAttributes[9].split("=")[1]);
            $("#namayandeNameEtebar").val(myAttributes[10].split("=")[1]);
        }
    }

    function loadEtebarTejaratElec(msg){
        var myAttributes = msg.split("@")[1].split("#");
        if (myAttributes[0] != undefined){
            $("#tarikh").val(myAttributes[0].split("=")[1]);
            $("#shenaseMoshtariElec").val(myAttributes[1].split("=")[1]);
            $("#bankElec").val(myAttributes[2].split("=")[1]);
            $("#fishAmountElec").val(myAttributes[3].split("=")[1]);
        }
    }

    function loadEtebarCheck(msg){
        var myAttributes = msg.split("@")[2].split("#");
        if (myAttributes[0] != undefined){
            $("#hesabBanki").val(myAttributes[0].split("=")[1]);  //hesab banki
            $("#shomareCheck").val(myAttributes[1].split("=")[1]); //shomare check
            $("#tarikhSarresid").val(myAttributes[2].split("=")[1]);  //tarikh sarresid
            $("#checkAmount").val(myAttributes[3].split("=")[1]);
            $("#darVajh").val(myAttributes[4].split("=")[1]);
            $("#tozihateCheck").val(myAttributes[5].split("=")[1]);
        }
    }
    function loadEtebarCheckEdit(msg) //edit etebar dar safhe liste etebarat va bedehiha
    {
        var myAttributes = msg.split("@")[2].split("#");
        if (myAttributes[0] != undefined){
            $("#hesabBanki").val(myAttributes[0].split("=")[1]);  //hesab banki
            $("#shomareCheck").val(myAttributes[1].split("=")[1]); //shomare check
            $("#tarikhSarresid").val(myAttributes[2].split("=")[1]);  //tarikh sarresid
            $("#checkAmount").val(myAttributes[3].split("=")[1]); //mablagh
        }
        var myAttributes6 = msg.split("@")[6].split("#");
        $("#seriCheck").val(myAttributes6[2].split("=")[1]);  //seri
        $("#branchNameCheck").val(myAttributes6[3].split("=")[1]);  //name shobe
        $("#branchCodeCheck").val(myAttributes6[4].split("=")[1]);  //code shobe
        $("#accountOwnerNameCheck").val(myAttributes6[5].split("=")[1]);  //name darande check
        $('#checkEtebarInfo #rcptName').val( msg.split("@")[3].split("#")[0].split("=")[1]); //taraf hesab
       // $('#nama_name_etebar_add').val(myAttributes6[7].split("=")[1]); //name namayande
        //----------------
        var myAttributes5 = msg.split("@")[5].split("#");
        $("#tozihateCheck").val(myAttributes5[4].split("=")[1]); //tozihat
         //--------------
        //name bank
        if (myAttributes6[6].split("=")[1].indexOf($('#sepahId').val())>-1) {
            $('#sepahId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#postbankId').val())>-1) {
            $('#postbankId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#bankmeliiId').val())>-1) {
            $('#bankmeliiId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#banksaderatId').val())>-1) {
            $('#banksaderatId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#sanatId').val())>-1) {
            $('#sanatId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#keshavarziId').val())>-1) {
            $('#keshavarziId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#maskanId').val())>-1) {
            $('#maskanId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#taavonId').val())>-1) {
            $('#taavonId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#ayandeId').val())>-1) {
            $('#ayandeId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#eghtesadId').val())>-1) {
            $('#eghtesadId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#bankparsianId').val())>-1) {
            $('#bankparsianId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#karafarinId').val())>-1) {
            $('#karafarinId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#samanId').val())>-1) {
            $('#samanId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#bankpasargadId').val())>-1) {
            $('#bankpasargadId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#sarmayeId').val())>-1) {
            $('#sarmayeId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#bankshahrId').val())>-1) {
            $('#bankshahrId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#sinaId').val())>-1) {
            $('#sinaId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#deyId').val())>-1) {
            $('#deyId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#ansarId').val())>-1) {
            $('#ansarId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#hekmatId').val())>-1) {
            $('#hekmatId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#ghavaminId').val())>-1) {
            $('#ghavaminId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#mehreghtesadId').val())>-1) {
            $('#mehreghtesadId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#tejaratId').val())>-1) {
            $('#tejaratId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#refahId').val())>-1) {
            $('#refahId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#saderatId').val())>-1) {
        $('#saderatId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#mellatId').val())>-1) {
            $('#mellatId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#gardeshgariId').val())>-1) {
            $('#gardeshgariId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#iranzaminId').val())>-1) {
            $('#iranzaminId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#khavaremianeId').val())>-1) {
            $('#khavaremianeId').attr('selected',true);
        } else if (myAttributes6[6].split("=")[1].indexOf($('#gharzolhasaneId').val())>-1) {
            $('#gharzolhasaneId').attr('selected',true);
        }
//        b-h
        else if (myAttributes6[6].split("=")[1].indexOf($('#mehriranId').val())>-1) {
            $('#mehriranId').attr('selected',true);
        }
        else if (myAttributes6[6].split("=")[1].indexOf($('#resalatId').val())>-1) {
            $('#resalatId').attr('selected',true);
        }
        else if (myAttributes6[6].split("=")[1].indexOf($('#iranvenezoelaId').val())>-1) {
            $('#iranvenezoelaId').attr('selected',true);
        }

    }

    function loadEtebarCash(msg){
        var myAttributes = msg.split("@")[3].split("#");
        if (myAttributes[0] != undefined && myAttributes[1] != undefined){
            $("#sahebeEtebarCash").val(myAttributes[0].split("=")[1]);
            $("#cashVosoulDate").val(myAttributes[1].split("=")[1]);
            $("#cashAmount").val(myAttributes[2].split("=")[1]);
            $("#cashTozihateEtebar").val(myAttributes[3].split("=")[1]);
        }
    }

    function loadEtebarFishByCodemoshtari(msg){
        var myAttributes = msg.split("@")[4].split("#");
        if (myAttributes[1] != undefined && myAttributes[4] != undefined){
            $("#bankMaghsadAutomobil").val(myAttributes[0].split("=")[1]);
            $("#shomareMoshtariAutomobil").val(myAttributes[1].split("=")[1]);
            $("#sahebeEtebarAutomobil").val(myAttributes[2].split("=")[1]);
            $("#fishAmountAutomobil").val(myAttributes[3].split("=")[1]);
            $("#tozihateEtebarAutomobil").val(myAttributes[4].split("=")[1]);
        }
    }

    function loadEtebarHBB(msg){
        var myAttributes = msg.split("@")[5].split("#");
        $("#sahebeEtebarHBB").val(myAttributes[0].split("=")[1]);
        $("#sarresidDateHBB").val(myAttributes[1].split("=")[1]);
        $("#amountHBB").val(myAttributes[2].split("=")[1]);
        $("#identifierEtebarHBB").val(myAttributes[3].split("=")[1]);
        $("#tozihateEtebarHBB").val(myAttributes[4].split("=")[1]);
    }

    function loadEtebarHesabFiMabeyn(msg){
        var myAttributes = msg.split("@")[6].split("#");
        $("#hesabFimabeynAmount").val(myAttributes[0].split("=")[1]);
        $("#hesabFimabeynTozihateEtebar").val(myAttributes[1].split("=")[1]);
    }

    function getBankName(){
        var bankNum =  $("#bankMaghsadAutomobil").val();
        if (bankNum.contains("0201136462000")) {
            $("#bankAutomobil").val("تجارت الکترونیک - میرداماد شرقی (0201136462000)");
        } else if (bankNum.contains("0200234164006")) {
            $("#bankAutomobil").val("پارسیان - بلوار کشاورز (0200234164006)");
        } else if (bankNum.contains("81011989")) {
            $("#bankAutomobil").val("پارسیان - ونک (81011989)");
        } else if (bankNum.contains("17123130")) {
            $("#bankAutomobil").val("تجارت - سپهبد قرنی (17123130)");
        } else if (bankNum.contains("4757575763")) {
            $("#bankAutomobil").val("ملت - ونک (4757575763)");
        } else if (bankNum.contains("2177777733")) {
            $("#bankAutomobil").val("ملت - اسکان (2177777733)");
        }
    }

    function assignOptionNoeEtebarAdd(){
        $.ajax({
            type: "POST",
            async : false,
            url: "isUserRoleNamayandeAjax",
            success: function (msg) {
                if (msg == 'ADMIN'){
                    $("#noeEtebareDarHaleSabt").empty();
                    $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.CASH%>').text("اعتبار نقدی"));
                    $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.HESAB_FI_MA_BEYN%>').text("حساب فی ما بین"));
                    $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.DARYAFTE_FISH%>').text("اعتبار فیش"));
                    $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.PARDAKHT_SHENASEDAR%>').text("پرداخت شناسه دار"));
                    $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.DARYAFTE_ELECTRONICI%>').text("اعتبار تجارت الکترونیک"));
                    $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.DARYAFTE_CHECK%>').text("اعتبار چک"));
                    $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.KASR_AZ_HOGHUGH%>').text("کسر از حقوق"));
                    $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.KASR_AZ_KARMOZD%>').text("كسر از كارمزد"));
                    $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.AZ_MAHALE_HAZINE_BIME_DARAYIHA%>').text("از محل هزينه بيمه دارايي ها"));
                    $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.ETEBAR_DARMAN_KHANEVADE%>').text("اعتبار درمان خانواده"));
                    $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.AZ_MAHALLE_TABLIGHAT_MANUAL%>').text("از محل تبلیغات(دستی)"));
                }
                else {
                if (msg.contains('ROLE_NAMAYANDE')){
                    $("#noeEtebareDarHaleSabt").empty();
                    $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.PARDAKHT_SHENASEDAR%>').text("پرداخت شناسه دار"));
                } if (msg.contains('ROLE_HESAB_FIMABEYN')){
                    $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.HESAB_FI_MA_BEYN%>').text("حساب فی ما بین"));
                }
                if (msg.contains('ROLE_MALI_ETEBAR_CHECK')){
                    $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.DARYAFTE_CHECK%>').text("اعتبار چک"));
                }
                if (msg.contains('ROLE_ETEBARAT_KHAS'))
                {
                    $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.CASH_NAMAYANDE%>').text("اعتبار نقدی (نمایندگی)"));
                    $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.DARYAFTE_FISH_NAMAYANDE%>').text("اعتبار فیش (نمایندگی)"));
                    $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.DARYAFTE_CHECK_NAMAYANDE%>').text("اعتبار چک (نمایندگی)"));
                }
            }
          }
        });
    }

    function addOptionNoeEtebarEdit(){
        $("#noeEtebareDarHaleSabt").empty();
        $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.CASH%>').text("اعتبار نقدی"));
        $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.VEHICLE_HAGHBIME_BARGASHTI%>').text("حق بیمه برگشتی (اتومبیل)"));
        $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.HESAB_FI_MA_BEYN%>').text("حساب فی ما بین"));
        $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.DARYAFTE_FISH%>').text("اعتبار فیش"));
        $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.PARDAKHT_SHENASEDAR%>').text("پرداخت شناسه دار"));
        $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.DARYAFTE_ELECTRONICI%>').text("اعتبار تجارت الکترونیک"));
        <%--$('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.DARYAFTE_CHECK%>').text("اعتبار چک"));--%>
        $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.AZ_MAHALLE_TABLIGHAT%>').text("از محل تبلیغات"));
        $('#noeEtebareDarHaleSabt').append($("<option></option>").attr("value", '<%=Credebit.CredebitType.KASR_AZ_HOGHUGH%>').text("کسر از حقوق"));
    }



</script>
<c:set var="currentDate" value="<%=DateUtil.getCurrentDate() %>" />
<table class="inputList" border="0" cellspacing="1" cellpadding="5" style="width:100%">
    <col class="inputCol"><col class="inputCol">
    <tr>
        <td id="noeEtebat">
            <span class="noThing"></span>
            <select id="noeEtebareDarHaleSabt" onchange="chg_etebarType(this.value);">
            </select>
            &nbsp;<label>نوع</label>
        </td>
        <td id="namayandeW">
            <span class="help ui-icon ui-icon-search" onclick="fillNamayandegi('nama_id_etebar_add','nama_name_etebar_add', '');" style="float:left;" title="جستجو"></span>
            <input type=text name="credebit.namayande.name" id="nama_name_etebar_add"  readonly="readonly" />
            <input type=hidden name="credebit.namayande.id" id="nama_id_etebar_add" />
            &nbsp;<label>نماینده</label>
        </td>
    </tr>
</table>
<form id="sabteEtebareDasti_hesabFimabeyn">
    <div id="hesabFimabeynEtebarInfo">
        <table class="inputList" border="0" cellspacing="1" cellpadding="5" style="width:100%;">
            <col class="inputCol"><col class="inputCol">
            <tr>
                <td>
                    <input type="hidden" name="credebit.credebitType" value="<%=Credebit.CredebitType.HESAB_FI_MA_BEYN.toString()%>">
                    <span class="noThing"></span>
                    <input type="text" name="credebit.amount" id="hesabFimabeynAmount" class="validate[required] digitSeparators ">
                    &nbsp;<label>مبلغ</label>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.description" id="hesabFimabeynTozihateEtebar" class="">
                    &nbsp;<label>توضیحات</label>
                </td>
            </tr>
        </table>
    </div>
</form>
<form id="sabteEtebareDasti_azMahalTab">
    <div id="azMahalTabInfo">
        <table class="inputList" border="0" cellspacing="1" cellpadding="5" style="width:100%;">
            <col class="inputCol"><col class="inputCol">
            <tr>
                <td>
                    <input type="hidden" name="credebit.credebitType" value="<%=Credebit.CredebitType.AZ_MAHALLE_TABLIGHAT.toString()%>">
                    <span class="noThing"></span>
                    <input type="text" name="credebit.amount" id="azMahalTabAmount" class="validate[required] digitSeparators ">
                    &nbsp;<label>مبلغ</label>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.description" id="azMahalTabTozihateEtebar" class="">
                    &nbsp;<label>توضیحات</label>
                </td>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.rcptName" id="azMahalTabSahebeEtebar" class="">
                    &nbsp;<label>صاحب اعتبار</label>
                </td>
            </tr>
            </tr>
        </table>
    </div>
</form>
<form id="sabteEtebareDasti_azMahalTab_manual">
    <div id="azMahalTabManualInfo">
        <table class="inputList" border="0" cellspacing="1" cellpadding="5" style="width:100%;">
            <col class="inputCol"><col class="inputCol">
            <tr>
                <td>
                    <input type="hidden" name="credebit.credebitType" value="<%=Credebit.CredebitType.AZ_MAHALLE_TABLIGHAT_MANUAL.toString()%>">
                    <span class="noThing"></span>
                    <input type="text" name="credebit.amount" id="azMahalTabManualAmount" class="validate[required] digitSeparators ">
                    &nbsp;<label>مبلغ</label>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.description" id="azMahalTabManualTozihateEtebar" class="">
                    &nbsp;<label>توضیحات</label>
                </td>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.rcptName" id="azMahalTabManualSahebeEtebar" class="">
                    &nbsp;<label>صاحب اعتبار</label>
                </td>
            </tr>
            </tr>
        </table>
    </div>
</form>
<form id="sabteEtebareDasti_kasrAzHagh">
    <div id="kasrAzHaghInfo">
        <table class="inputList" border="0" cellspacing="1" cellpadding="5" style="width:100%;">
            <col class="inputCol"><col class="inputCol">
            <tr>
                <td>
                    <input type="hidden" name="credebit.credebitType" value="<%=Credebit.CredebitType.KASR_AZ_HOGHUGH.toString()%>">
                    <span class="noThing"></span>
                    <input type="text" name="credebit.amount" id="kasrAzHaghAmount" class="validate[required] digitSeparators ">
                    &nbsp;<label>مبلغ</label>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.description" id="kasrAzHaghTozihateEtebar" class="">
                    &nbsp;<label>توضیحات</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.rcptName" id="kasrAzHaghSahebeEtebar" class="">
                    &nbsp;<label>صاحب اعتبار</label>
                </td>
            </tr>
        </table>
    </div>
</form>
<form id="sabteEtebareDasti_azMahaleHazineBimeDarayiha">
    <div id="azMahaleHazineBimeDarayihaInfo">
        <table class="inputList" border="0" cellspacing="1" cellpadding="5" style="width:100%;">
            <col class="inputCol"><col class="inputCol">
            <tr>
                <td>
                    <input type="hidden" name="credebit.credebitType" value="<%=Credebit.CredebitType.AZ_MAHALE_HAZINE_BIME_DARAYIHA.toString()%>">
                    <span class="noThing"></span>
                    <input type="text" name="credebit.amount" id="azMahaleHazineBimeDarayihaAmount" class="validate[required] digitSeparators ">
                    &nbsp;<label>مبلغ</label>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.description" id="azMahaleHazineBimeDarayihaTozihateEtebar" class="">
                    &nbsp;<label>توضیحات</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.rcptName" id="azMahaleHazineBimeDarayihaSahebeEtebar" class="">
                    &nbsp;<label>صاحب اعتبار</label>
                </td>
            </tr>
        </table>
    </div>
</form>
<form id="sabteEtebareDasti_darmanKhanevade">
    <div id="darmanKhanevadeInfo">
        <table class="inputList" border="0" cellspacing="1" cellpadding="5" style="width:100%;">
            <col class="inputCol"><col class="inputCol">
            <tr>
                <td>
                    <input type="hidden" name="credebit.credebitType" value="<%=Credebit.CredebitType.ETEBAR_DARMAN_KHANEVADE.toString()%>">
                    <span class="noThing"></span>
                    <input type="text" name="credebit.amount" id="darmanKhanevadeAmount" class="validate[required] digitSeparators ">
                    &nbsp;<label>مبلغ</label>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.description" id="darmanKhanevadeTozihateEtebar" class="">
                    &nbsp;<label>توضیحات</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.rcptName" id="darmanKhanevadeSahebeEtebar" class="">
                    &nbsp;<label>صاحب اعتبار</label>
                </td>
            </tr>
        </table>
    </div>
</form>
<form id="sabteEtebareDasti_cash">
    <div id="cashEtebarInfo">
        <table class="inputList" border="0" cellspacing="1" cellpadding="5" style="width:100%;">
            <col class="inputCol"><col class="inputCol">
            <tr>
                <td>
                    <input type="hidden" name="credebit.credebitType" value="<%=Credebit.CredebitType.CASH.toString()%>">
                    <span class="noThing"></span>
                    <input type="text" name="credebit.rcptName" id="sahebeEtebarCash" class="">
                    &nbsp;<label>صاحب اعتبار</label>
                </td>
                <td>

                    <input type="text" name="credebit.dateFish" id="cashVosoulDate" class="validate[custom[date]] datePkr" value="${current_date}">
                    &nbsp;<label>تاریخ وصول</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.amount" id="cashAmount" class="validate[required] digitSeparators ">
                    &nbsp;<label>مبلغ</label>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.description" id="cashTozihateEtebar" class="">
                    &nbsp;<label>توضیحات</label>
                </td>
            </tr>
        </table>
    </div>
</form>
<form id="sabteEtebareDasti_haghbimenameBargashyi">
    <div id="haghbimenameBargashyiEtebarInfo">
        <table class="inputList" border="0" cellspacing="1" cellpadding="5" style="width:100%;">
            <col class="inputCol"><col class="inputCol">
            <tr>
                <td>
                    <input type="hidden" name="credebit.credebitType" value="<%=Credebit.CredebitType.VEHICLE_HAGHBIME_BARGASHTI.toString()%>">
                    <span class="noThing"></span>
                    <input type="text" name="credebit.rcptName" id="sahebeEtebarHBB" class="">
                    &nbsp;<label>نام بیمه گذار</label>
                </td>
                <td>
                    <input type="text" name="credebit.sarresidDate" id="sarresidDateHBB" class="datePkr">
                    &nbsp;<label>تاریخ سر رسید</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.amount" id="amountHBB" class="validate[required] digitSeparators ">
                    &nbsp;<label>مبلغ</label>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.identifier" id="identifierEtebarHBB" class="">
                    &nbsp;<label>شماره بیمه نامه</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.description" id="tozihateEtebarHBB" class="">
                    &nbsp;<label>توضیحات</label>
                </td>
            </tr>
        </table>
    </div>
</form>
<form id="sabteEtebareDasti_fishAutomobil">
    <div id="fishAutomobilEtebarInfo">
        <table class="inputList" border="0" cellspacing="1" cellpadding="5" style="width:100%;">
            <col class="inputCol"><col class="inputCol">
            <tr>
                <td>
                    <span class="noThing"></span>
                    <select name="credebit.bankName" id="bankMaghsadAutomobil" class="validate[required]">
                        <option value="">انتخاب کنید</option>
                        <option value="<%=Constant.CREDEBIT_BANK_TEJARAT_EL_MIRDAMAD%>"><%=Constant.CREDEBIT_BANK_TEJARAT_EL_MIRDAMAD%></option>
                        <option value="<%=Constant.CREDEBIT_BANK_TEJARAT_SEPAHBOD_GHARANI_GHEIRE_OMR%>"><%=Constant.CREDEBIT_BANK_TEJARAT_SEPAHBOD_GHARANI_GHEIRE_OMR%></option>
                        <option value="<%=Constant.CREDEBIT_BANK_MELAT_VANAK%>"><%=Constant.CREDEBIT_BANK_MELAT_VANAK%></option>

                    </select>
                    <label>شماره حساب</label>
                </td>
                <td>
                    <%--<span class="ui-icon ui-icon-refresh" onclick="generateShomareMoshtari()" style="float:left;margin-top:5px;"></span>--%>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.shomareMoshtari" id="shomareMoshtariAutomobil" readonly="true" >
                    <label id="shomareMoshtariAutomobilLbl">شناسه پرداخت</label>

                </td>
            </tr>
            <tr>
                <td>
                    <input type="hidden" name="credebit.credebitType" value="<%=Credebit.CredebitType.PARDAKHT_SHENASEDAR.toString()%>">
                    <span class="noThing"></span>
                    <input type="text" name="credebit.daryafteFish.sahebeEtebar" id="sahebeEtebarAutomobil" class="">
                    &nbsp;<label>صاحب اعتبار</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.amount" id="fishAmountAutomobil" class="validate[required] digitSeparators ">
                    &nbsp;<label>مبلغ</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.daryafteFish.tozihat" id="tozihateEtebarAutomobil" class="">
                    &nbsp;<label>توضیحات</label>
                </td>
            </tr>
        </table>
    </div>
</form>
<form id="sabteEtebareDasti_fish">
    <div id="fishEtebarInfo">
        <table class="inputList" border="0" cellspacing="1" cellpadding="5" style="width:100%;">
            <col class="inputCol"><col class="inputCol">
            <tr>
                <td>
                    <span class="noThing"></span>
                    <select name="credebit.bankName" id="bankMaghsad" class="validate[required]">
                        <option value="">انتخاب كنيد</option>
                        <sec:authorize ifNotGranted="ROLE_ETEBARAT_KHAS">
                            <option value="<%=Constant.CREDEBIT_BANK_TEJARAT_SEPAHBOD_GHARANI_GHEIRE_OMR%>">تجارت (17123130)</option>
                            <option value="<%=Constant.CREDEBIT_BANK_PARSIAN_VANAK%>">پارسیان (810-1198-9)</option>
                            <option value="<%=Constant.CREDEBIT_BANK_PARSIAN_BOLVAR_KESHAVARZ%>">پارسیان (02-00234164-006)</option>
                            <option value="<%=Constant.CREDEBIT_BANK_TEJARAT_EL_MIRDAMAD%>">پارسیان-تجارت الکترونیک (02-01136462-000)</option>
                        </sec:authorize>
                        <sec:authorize ifAnyGranted="ROLE_ETEBARAT_KHAS">
                            <option value="<%=Constant.CREDEBIT_BANK_TEJARAT_SEPAHBOD_GHARANI_GHEIRE_OMR%>">تجارت (17123130)</option>
                            <option value="<%=Constant.CREDEBIT_BANK_PARSIAN_VANAK%>">پارسیان (810-1198-9)</option>
                            <option value="<%=Constant.CREDEBIT_BANK_PARSIAN_BOLVAR_KESHAVARZ%>">پارسیان (02-00234164-006)</option>
                            <option value="<%=Constant.CREDEBIT_BANK_TEJARAT_EL_MIRDAMAD%>">پارسیان-تجارت الکترونیک (02-01136462-000)</option>
                            <option value="33596127">33596127</option>
                            <option value="33679158">33679158</option>
                            <option value="33679315">33679315</option>
                            <option value="33695036">33695036</option>
                            <option value="33695038">33695038</option>
                            <option value="33695039">33695039</option>
                            <option value="33695041">33695041</option>
                            <option value="33700710">33700710</option>
                            <option value="33913521">33913521</option>
                            <option value="33913524">33913524</option>
                            <option value="33913527">33913527</option>
                            <option value="33913529">33913529</option>
                            <option value="33913531">33913531</option>
                            <option value="33913541">33913541</option>
                            <option value="33913542">33913542</option>
                            <option value="33913544">33913544</option>
                            <option value="33913545">33913545</option>
                            <option value="33913549">33913549</option>
                            <option value="33913550">33913550</option>
                            <option value="33913552">33913552</option>
                            <option value="33913554">33913554</option>
                            <option value="33913555">33913555</option>
                            <option value="33913557">33913557</option>
                            <option value="33913576">33913576</option>
                            <option value="33913578">33913578</option>
                            <option value="33913579">33913579</option>
                            <option value="33913580">33913580</option>
                            <option value="33957956">33957956</option>
                            <option value="33989503">33989503</option>
                            <option value="33975580">33975580</option>
                            <option value="33989976">33989976</option>
                            <option value="44040036">44040036</option>
                            <option value="0200299965003">0200299965003</option>
                            <option value="0200569084004">0200569084004</option>
                            <option value="0200569078002">0200569078002</option>
                            <option value="0200569097003">0200569097003</option>
                            <option value="0200567975004">0200567975004</option>
                            <option value="0200772617007">0200772617007</option>
                            <option value="1-3870-811">1-3870-811</option>
                            <option value="0200693078000">0200693078000-پارسيان شعبه مركزي</option>
                            <option value="0201375732004">0201375732004-پارسيان شعبه كيش</option>
                            <option value="0201329033001">0201329033001-پارسيان شعبه آرژانتين</option>
                            <option value="810-6350-6">810-6350-6-پارسيان شعبه گاندي</option>
                            <option value="811-3916-6">811-3916-6-پارسيان شعبه آرژانتين</option>
                            <option value="21-22072-4">21-22072-4-پارسيان شعبه گاندي</option>
                            <%--<c:forEach var="bnkVar" items="${user.mojtamaSodoor.bankAccount}" varStatus="i">--%>
                                <%--<option value="${bnkVar.bankAccount}">--%>
                                    <%--${bnkVar.bankAccount}--%>
                                <%--</option>--%>
                            <%--</c:forEach>--%>
                        </sec:authorize>
                    </select>
                    <label>شماره حساب</label>
                </td>
                <td/>
            </tr>
            <tr>
                <td>
                    <input type="hidden" id="daryaft_fish_serialized" name="credebit.credebitType" value="<%=Credebit.CredebitType.DARYAFTE_FISH.toString()%>">
                    <span class="noThing"></span>
                    <input type="text" name="credebit.daryafteFish.sahebeEtebar" id="sahebeEtebar" class="">
                    &nbsp;<label>صاحب اعتبار</label>
                </td>
                <td id="sh_fsh">
                    <span class="noThing"></span>
                    <input type="text" name="credebit.shomareMoshtari" id="shenaseMoshtari" class="validate[required]">
                    &nbsp;<label>شماره فیش</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.daryafteFish.bank" id="bank" class="">
                    &nbsp;<label>بانک</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.amount" id="fishAmount" class="validate[required] digitSeparators">
                    &nbsp;<label>مبلغ</label>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="credebit.dateFish" id="createdDate" class="validate[required,custom[date]] datePkr" readonly="readonly">
                    &nbsp;<label>تاریخ وصول</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.timeFish" id="saatepardakht" />
                    &nbsp;<label>ساعت وصول</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.daryafteFish.kodeShobe" id="kodeShobe" />
                    &nbsp;<label>کد شعبه</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.daryafteFish.tozihat" id="tozihateEtebar" class="">
                    &nbsp;<label>توضیحات</label>
                </td>
            </tr>
        </table>
    </div>
</form>
<form id="sabteEtebareDasti_electronic">
    <div id="elecEtebarInfo">
        <table class="inputList" border="0" cellspacing="1" cellpadding="5" style="width:100%;">
            <col class="inputCol"><col class="inputCol">
            <tr>
                <td>
                    <input type="hidden" name="credebit.credebitType" value="<%=Credebit.CredebitType.DARYAFTE_ELECTRONICI.toString()%>">
                    <span class="noThing"></span>
                    <input type="text" value="33034869" readonly="readonly" id="girande" class="">
                    &nbsp;<label>شماره پایانه</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" value="1" readonly="readonly" id="batch" class="">
                    &nbsp;<label>Batch</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.daryafteFish.sahebeEtebar" id="tarikh" class="">
                    &nbsp;<label>پرداخت کننده</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.shenaseCredebit" id="shenaseMoshtariElec" class="">
                    &nbsp;<label>شماره فیش</label>
                </td>
            </tr>
            <tr>

                <td>
                    <span class="noThing"></span>
                    <input type="text" value="20113646000" readonly="readonly" id="shommarehesab" class="">
                    &nbsp;<label>شماره حساب</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.daryafteFish.bank" id="bankElec" class="">
                    &nbsp;<label>بانک</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.amount" id="fishAmountElec" class="validate[required] digitSeparators">
                    &nbsp;<label>مبلغ</label>
                </td>
                <td></td>
            </tr>
        </table>
    </div>
</form>
<form id="sabteEtebareDasti_check">
    <div id="checkEtebarInfo">

        <table class="inputList" border="0" cellspacing="1" cellpadding="5" style="width:100%;">

            <col class="inputCol"><col class="inputCol">
            <tr>
                <td>
                    <input type="hidden" name="credebit.credebitType" value="<%=Credebit.CredebitType.DARYAFTE_CHECK.toString()%>">
                    <span class="noThing"></span>
                    <input type="text" name="credebit.daryafteCheck.hesabBanki" id="hesabBanki" class="validate[required]">
                    &nbsp;<label>شماره حساب</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.daryafteCheck.serial" id="shomareCheck" class="validate[required]">
                    &nbsp;<label>شماره چک</label>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="credebit.sarresidDate" id="tarikhSarresid"  class="datePkr validate[required]">
                    &nbsp;<label>تاریخ سررسید</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.daryafteCheck.seri" id="seriCheck" class="validate[required,custom[onlyNumber],length[1,6]]">
                    &nbsp;<label>سری</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <%--<input type="text" name="credebit.daryafteCheck.bankName" id="bankNameCheck" class="validate[required]">--%>
                    <select name="credebit.bankName" id="bankNameCheck" class="validate[required]">
                        <option value="">انتخاب کنید</option>
                        <option id="sepahId" value="بانک سپه-03">بانک سپه</option>
                        <option id="postbankId" value="پست بانک-16">پست بانک</option>
                        <option id="bankmeliiId" value="بانک ملی ایران-09">بانک ملی ایران</option>
                        <option id="banksaderatId" value="بانک توسعه صادرات ایران-10">بانک توسعه صادرات ایران</option>
                        <option id="sanatId" value="بانک صنعت و معدن-05">بانک صنعت و معدن</option>
                        <option id="keshavarziId" value="بانک کشاورزی-06">بانک کشاورزی</option>
                        <option id="maskanId" value="بانک مسکن-07">بانک مسکن</option>
                        <option id="taavonId" value="بانک توسعه تعاون-22">بانک توسعه تعاون</option>
                        <option id="ayandeId" value="بانک آینده-23">بانک آینده</option>
                        <option id="eghtesadId" value="بانک اقتصاد نوین-13">بانک اقتصاد نوین</option>
                        <option id="bankparsianId" value="بانک پارسیان-12">بانک پارسیان</option>
                        <option id="karafarinId" value="بانک کارآفرین-14">بانک کارآفرین</option>
                        <option id="samanId" value="بانک سامان-15">بانک سامان</option>
                        <option id="bankpasargadId" value="بانک پاسارگاد-17">بانک پاسارگاد</option>
                        <option id="sarmayeId" value="بانک سرمایه-18">بانک سرمایه</option>
                        <option id="bankshahrId" value="بانک شهر-24">بانک شهر</option>
                        <option id="sinaId" value="بانک سینا-20">بانک سینا</option>
                        <option id="deyId" value="بانک دی-25">بانک دی</option>
                        <option id="ansarId" value="بانک انصار-26">بانک انصار</option>
                        <option id="hekmatId" value="بانک حکمت ایرانیان-29">بانک حکمت ایرانیان</option>
                        <option id="ghavaminId" value="بانک قوامین-33">بانک قوامین</option>
                        <%--<option id="mehreghtesadId" value="بانک مهراقتصاد">بانک مهراقتصاد</option>--%>
                        <option id="tejaratId" value="بانک تجارت-01">بانک تجارت</option>
                        <option id="refahId" value="بانک رفاه کارگران-02">بانک رفاه کارگران</option>
                        <option id="saderatId" value="بانک صادرات ایران-04">بانک صادرات ایران</option>
                        <option id="mellatId" value="بانک ملت-08">بانک ملت</option>
                        <option id="gardeshgariId" value="بانک گردشگری-28">بانک گردشگری</option>
                        <option id="iranzaminId" value="بانک ایران زمین-30">بانک ایران زمین</option>
                        <option id="khavaremianeId" value="بانک خاورمیانه-32">بانک خاورمیانه</option>
                        <%--<option id="gharzolhasaneId" value="صندوق قرض الحسنه پارسیان">صندوق قرض الحسنه پارسیان</option>--%>
                        <%--b-h--%>
                        <option id="mehriranId" value="قرض الحسنه مهر ايران-20">قرض الحسنه مهر ايران</option>
                        <option id="resalatId" value="قرض الحسنه رسالت-31">قرض الحسنه رسالت</option>
                        <option id="iranvenezoelaId" value="اريان ونزوئلا-27">ايران ونزوئلا</option>
                    </select>
                    &nbsp;<label>نام بانک</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.rcptName" id="rcptName" class="validate[required]">
                    &nbsp;<label>طرف حساب</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.daryafteCheck.branchName" id="branchNameCheck" class="validate[required]">
                    &nbsp;<label>نام شعبه</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.daryafteCheck.branchCode" id="branchCodeCheck" class="validate[required]">
                    &nbsp;<label>کد شعبه</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.daryafteCheck.accountOwnerName" id="accountOwnerNameCheck" class="validate[required]">
                    &nbsp;<label>نام دارنده چک</label>
                </td>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.amount" id="checkAmount" class="validate[required] digitSeparators ">
                    &nbsp;<label>مبلغ</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.description" id="tozihateCheck" class="">
                    &nbsp;<label>توضیحات</label>
                </td>
            </tr>
        </table>
    </div>
</form>
<form id="sabteEtebareDasti_kasrAzKarmozd">
    <div id="kasrAzKarmozdInfo">
        <table class="inputList" border="0" cellspacing="1" cellpadding="5" style="width:100%;">
            <col class="inputCol"><col class="inputCol">
            <tr>
                <td>
                    <input type="hidden" name="credebit.credebitType" value="<%=Credebit.CredebitType.KASR_AZ_KARMOZD.toString()%>">
                    <span class="noThing"></span>
                    <input type="text" name="credebit.amount" id="kasrAzKarmozdAmount" class="validate[required] digitSeparators ">
                    &nbsp;<label>مبلغ</label>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.description" id="kasrAzKarmozdTozihateEtebar" class="">
                    &nbsp;<label>توضیحات</label>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="noThing"></span>
                    <input type="text" name="credebit.rcptName" id="kasrAzKarmozdSahebeEtebar" class="">
                    &nbsp;<label>صاحب اعتبار</label>
                </td>
            </tr>
        </table>
    </div>
</form>
<tr>
    <td>
        <div id="namayandeNameEtebarDiv" hidden="true">
            <span class="noThing"></span>
            <input type="text" name="credebit.namayande.name" id="namayandeNameEtebar" class="">
            &nbsp;<label>نام نماینده</label>
        </div>
    </td>
    <td></td>
</tr>
<script>
    function generateShomareMoshtari(){
            var bankName = document.getElementById("bankMaghsadAutomobil").value;
        var result = "";
            $.ajax({
                type: "POST",
                url: "generateCodeMoshtariCredebit?bankNameCodeMoshtari="+bankName,
                success: function (response) {
                    document.getElementById("shomareMoshtariAutomobil").value = response.trim();
                    result =  response.trim();
                }
            });
        return result;
    }
//    function validationEtebar() {
//        var tarikheSarresid =  document.getElementById("tarikhSarresid").value;
//        var result = "";
//       if(tarikheSarresid!="") {
//            $.ajax({
//                type: "POST",
//                url: "compareDate?tarikheSarresid="+tarikheSarresid,
//                success: function (response) {
//                    result =  response.trim();
//                   if( result==1)
//                       alert("تاریخ سررسید نمی تواند بیشتر یا برابر تاریخ روز باشد") ;
//                }
//            });
//             }
//    return result;
//    }
    function chg_etebarTypew(){
        var gr = jQuery("#etebarat_tbl").jqGrid('getGridParam', 'selrow');
        if (gr != null) {
            var gr_data = jQuery("#etebarat_tbl").jqGrid('getRowData', gr);
        }

        var type =  gr_data['type'];


        if (type == "دریافت نقدی")
            $("#noeEtebareDarHaleSabt").val("<%=Credebit.CredebitType.CASH%>");
        else if (type == "دریافت فیش")
            $("#noeEtebareDarHaleSabt").val("<%=Credebit.CredebitType.DARYAFTE_FISH%>");
        else if (type == "پرداخت شناسه دار")
            $("#noeEtebareDarHaleSabt").val("<%=Credebit.CredebitType.PARDAKHT_SHENASEDAR%>");
        else if (type == "دریافت الکترونیکی")
            $("#noeEtebareDarHaleSabt").val("<%=Credebit.CredebitType.DARYAFTE_ELECTRONICI%>");
        else if (type == "دریافت چک")
            $("#noeEtebareDarHaleSabt").val("<%=Credebit.CredebitType.DARYAFTE_CHECK%>");
        else if (type == "حق بیمه برگشتی (اتومبیل)")
            $("#noeEtebareDarHaleSabt").val("<%=Credebit.CredebitType.VEHICLE_HAGHBIME_BARGASHTI%>");

        chg_etebarType($("#noeEtebareDarHaleSabt").val());

    }
</script>


