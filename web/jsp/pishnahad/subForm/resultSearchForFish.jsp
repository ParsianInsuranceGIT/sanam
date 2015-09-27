<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bitarts.parsian.model.BankInfo" %>
<%@ page import="com.bitarts.parsian.model.asnadeSodor.Credebit" %>
<%
    List<Credebit> foundFishes = (List<Credebit>) request.getAttribute("foundFishes");
%>


<%
    if(foundFishes.size()>0){
    int resSearchCounter = 1;
    for (Credebit foundFish : foundFishes) {

%>


<tr>
    <input type="hidden" id="foundfishid<%=resSearchCounter%>" value="<%=foundFish.getId()%>"/>
    <td><%=resSearchCounter%></td>
    <% if (resSearchCounter == 1){ %>
    <td><input type="radio" checked="checked" name="selectedFound" id="selected_found<%=resSearchCounter%>"/></td>
    <%}else{%>
    <td><input type="radio" name="selectedFound" id="selected_found<%=resSearchCounter%>"/></td>
    <%}%>
    <td id="foundfishshomare<%=resSearchCounter%>"><%=foundFish.getDaryafteFish()!= null?foundFish.getDaryafteFish().getShomareFish():""%></td>
    <td id="foundfishnamebank<%=resSearchCounter%>"><%=foundFish.getDaryafteFish()!= null?foundFish.getDaryafteFish().getBank():""%></td>
    <td id="foundfishnameshobe<%=resSearchCounter%>"><%=foundFish.getDaryafteFish()!= null?foundFish.getDaryafteFish().getKodeShobe():""%></td>
    <td id="foundfishtozihat<%=resSearchCounter%>"><%=foundFish.getDaryafteFish().getTozihat()%></td>
    <td id="foundfishsanad<%=resSearchCounter%>"><%=foundFish.getDaryafteFish().getShomareSanadBank()%></td>
    <td id="foundfishmablagh<%=resSearchCounter%>"><%=foundFish.getAmount()%></td>
    <td id="foundfishtarikh<%=resSearchCounter%>"><%=foundFish.getDaryafteFish()!= null?foundFish.getDaryafteFish().getTarikh():""%></td>
    <td id="foundfishtime<%=resSearchCounter%>"><%=foundFish.getDaryafteFish()!= null?foundFish.getDaryafteFish().getTime():""%></td>
</tr>

<%  resSearchCounter++;}
}else{%>
<tr>
    <td colspan="7">نتیجه ای یافت نشد!</td>
</tr>
<%
    }
%>
