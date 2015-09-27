<%@ page import="com.bitarts.parsian.model.BankInfo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Arron2
  Date: Feb 22, 2011
  Time: 2:59:03 AM
--%>
<% BankInfo bankInfo = (BankInfo) request.getAttribute("bankInfo");%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<input type="hidden" id="resultForShenaseMoshtari" value="<%=bankInfo.getMainId()%>"/>

