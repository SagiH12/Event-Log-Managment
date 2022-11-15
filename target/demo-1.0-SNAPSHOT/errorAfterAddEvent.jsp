

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean scope="request" id="error" type="String" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>        a:link, a:visited {
        background-color: #808080;
        color: white;
        padding: 14px 25px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
    }</style>
    <title>ERROR</title>
    <link href="InputButton.css" type="text/css" rel='stylesheet'>
</head>
<body>
<% String userWelcome = (String)session.getAttribute("USER_NAME"); %>
<% if(!userWelcome.equals("Guest")){ %>
<h5 id="name" style="font-size: small;text-align: left;">Welcome : <%= userWelcome%> </h5>
<% } else { %>
<h5 id="name" style="font-size: small;text-align: left;">Welcome : <%= userWelcome %> <%= " , " + session.getId() %> </h5>
<% } %>
<h1><%= error %></h1>
<% if(!userWelcome.equals("Guest")){ %>
<p><a href="MainFile.jsp">Back To The Main Page</a></p>
<%} else{ %>
<p><a href="GuestMainFile.jsp">Back To The Main Page</a></p>
<% } %>
</body>
</html>