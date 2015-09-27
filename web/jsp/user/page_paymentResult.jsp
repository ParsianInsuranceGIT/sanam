<%@ page import="com.bitarts.parsian.model.asnadeSodor.Credebit" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Credebit credebit = ((Credebit)request.getAttribute("credebit"));
    String isErr="false";
    if(credebit==null || credebit.getId()==null)
        isErr="true";
%>

<% if(isErr.equals("true"))
   {
%>
        ^^<%=isErr%>^^
<%
   }
   else
   {
%>
        ^^<%=isErr%>^^<%=credebit.getId()%>^^<%=credebit.getAmount()%>^^<%=credebit.getRemainingAmount()%>^^
        <%=credebit.getRcptName()%>^^<%=credebit.getSarresidDate()%>^^<%=credebit.getCredebitTypeFarsi()%>^^<%=credebit.getShomareMoshtari()%>^^
<%
    }
%>