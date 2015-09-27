<%@ page import="com.bitarts.parsian.model.pishnahad.Shakhs" %>
<%@include file="/jsp/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%Shakhs shakhs = ((Shakhs)request.getAttribute("shakhs"));%>
SHAKHS_INFO
<c:if test="${shakhs != null}">
,<%=shakhs.getId()%>,<%=shakhs.getName()%> <%=shakhs.getNameKhanevadegi()!=null ? shakhs.getNameKhanevadegi(): ""%>,<%=shakhs.getTarikheTavallod()%>,<%=shakhs.getJensiat()%>,,${shakhs.name},${shakhs.nameKhanevadegi},${shakhs.shomareShenasnameh},${shakhs.shomareSabt},${shakhs.kodeMelliShenasayi},${shakhs.kodeEghtesadi},${shakhs.tarikheTavallod},${shakhs.tarikheSabt},${shakhs.mahalleTavallod.cityId},<%=shakhs.getNameKhanevadegi()!=null ? (shakhs.getMahalleSodoreShenasnameh() != null ? shakhs.getMahalleSodoreShenasnameh().getCityId(): 20254): shakhs.getMahalleSabt().getCityId() %>,${shakhs.namePedar},${shakhs.jensiat},${shakhs.vaziateTaahol},,${shakhs.iraniOrAtbaeKhareji},${shakhs.shoghleAsli},${shakhs.shoghleFarei},${shakhs.dolatiKhososi},${shakhs.noeFaaliat},${shakhs.type},${shakhs.pishvand},${shakhs.mahalleTavallod.cityName},${shakhsIsHoghughi},<%=shakhs.getNameKhanevadegi()!=null ? (shakhs.getMahalleSodoreShenasnameh() != null ? shakhs.getMahalleSodoreShenasnameh().getCityName() : "-"): shakhs.getMahalleSabt().getCityName() %>,${shakhs.jensiat}
</c:if>