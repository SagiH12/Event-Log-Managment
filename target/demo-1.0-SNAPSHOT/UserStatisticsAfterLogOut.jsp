

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="mainCSS.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Statistics</title>
</head>
<body>
<form action="LoginFile.html">
    <br>
    <h1>  Thank you <%= (String)session.getAttribute("USER_NAME")%> for visiting the website , your data is:  </h1>
    The amount of events added : <%= (Integer)session.getAttribute("userAddEventCounter")%> <br>
    The amount of events deleted : <%= (Integer)session.getAttribute("userDeleteEventCounter")%> <br>
    The amount of events updated : <%= (Integer)session.getAttribute("userUpdateEventCounter")%> <br>
    The amount of events searched : <%= (Integer)session.getAttribute("userSearchEventCounter")%> <br>
    <input type='submit' name='main' value='Back to LogIn Page'>
    <br>
</form>
</body>
</html>
