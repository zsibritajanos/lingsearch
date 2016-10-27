<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="ws.WebServiceInterface" %>
<%@ page import="ws.WebServiceImpService" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title></title>
</head>
<body>

<%

    WebServiceImpService webServiceImpService = new WebServiceImpService();
    WebServiceInterface webServiceInterface = webServiceImpService.getWebServiceImpPort();

    String res = webServiceInterface.hello("Janos");

%>
<p>
<pre>
        <%= res %>

</body>
</html>
