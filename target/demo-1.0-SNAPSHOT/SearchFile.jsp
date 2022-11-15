

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <style>
            input,button{
            padding: 12px 20px;
            margin: 8px 0;
            box-sizing: border-box;
            box-sizing: border-box;
            border: none;
            background-color: #333;
            color: white;}
        </style>
    </head>
    <body>
    <% String userWelcome = (String)session.getAttribute("USER_NAME"); %>
    <% if(!userWelcome.equals("Guest")){ %>
    <h5 id="name" style="font-size: small;text-align: left;">Welcome : <%= userWelcome%> </h5>
    <% } else { %>
    <h5 id="name" style="font-size: small;text-align: left;">Welcome : <%= userWelcome %> <%= " , " + session.getId() %> </h5>
    <% } %>
        <div id="search">
            <form action="Search_Servlet">
            <br>
            <input type="text" name="daysearchbox" placeholder="Type Day..">
                <input type="text" name="hoursearchbox" placeholder="Type Hour..">
            <input type="submit" name="search" value="Search">
            <br>
            </form>
        </div>
    </body>
</html>
