<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.bitarts.parsian.service.calculations.BimeNaamehVaSarmayehGozari" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
    if(throwable instanceof BimeNaamehVaSarmayehGozari.CustomValidationException) {
        BimeNaamehVaSarmayehGozari.CustomValidationException exception = (BimeNaamehVaSarmayehGozari.CustomValidationException) throwable;
        out.write(exception.getMyMessage() + "<br/>");
    }
%>
There was an error in your operation. Please inform the admin.<br/>
Server time: <%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())%>