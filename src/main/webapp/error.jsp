

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean scope="request" id="error" type="String"/>
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
        <h1><%= error %></h1>
        <p><a href="LoginFile.html">Back To Login Page</a></p>
    </body>
</html>
