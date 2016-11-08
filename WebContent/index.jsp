<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="ws.WebServiceInterface" %>
<%@ page import="ws.WebServiceImpService" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title></title>
</head>
<body>

<form action="index.jsp" method="POST">
    <!--lemma: <input type="text" name="lemma"><br> -->
    dep rel: <input type="text" name="dep">
    <input type="submit" value="SEARCH">
</form>


<%
    request.setCharacterEncoding("UTF-8");
    // String content = request.getParameter("lemma");
    String depQuery = request.getParameter("dep");

    String toScreen = "";

    String parsed = null;

//    if (content != null && content.trim().length() > 0) {
//        WebServiceImpService webServiceImpService = new WebServiceImpService();
//        WebServiceInterface webServiceInterface = webServiceImpService.getWebServiceImpPort();
//
//        /**
//         * parsed
//         */
//        parsed = webServiceInterface.hello(content);
//    }

    if (depQuery != null && depQuery.trim().length() > 0) {
        WebServiceImpService webServiceImpService = new WebServiceImpService();
        WebServiceInterface webServiceInterface = webServiceImpService.getWebServiceImpPort();

        /**
         * parsed
         */

        parsed = webServiceInterface.searchByDep(depQuery);


//        for (String p : parsed) {
//            toScreen += p + "<br />";
//        }
    }


%>

<p>
<pre>
<%=
    parsed
%>
</pre>

</body>
</html>
