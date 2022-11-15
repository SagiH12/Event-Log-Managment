

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="mainCSS.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>After Additon</title>
</head>
<body>
<h5 id="name" style="font-size: small;text-align: left;">Welcome : <%= (String)session.getAttribute("USER_NAME")%> </h5>
<form action="MainFile.jsp">
    <br>
    <h1>  Event has been added successful! </h1>
    Add Event Counter: <%= (Integer)session.getAttribute("userAddEventCounter")%>
    <input type='submit' name='main' value='Back to Main Page'>
    <br>
</form>
</body>
</html>
