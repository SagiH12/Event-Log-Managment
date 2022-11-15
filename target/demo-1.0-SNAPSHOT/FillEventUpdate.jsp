<%@ page import="Model.Event" %><%--
  Created by IntelliJ IDEA.
  User: שגיא
  Date: 09/03/2022
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>   input[type=submit] {
    background-color: #808080;
    color: white;
    padding: 14px 25px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
}</style>
<head>
    <title>Update Event</title>
</head>
<body>
    <% Event e = (Event)request.getAttribute("updateEvent"); %>
    <h5 id="name" style="font-size: small;text-align: left;">Welcome : <%= (String)session.getAttribute("USER_NAME")%> </h5>
        <form action="UpdateServlet">
            Username: <label><%= e.getUserName() %></label> <br>
            Event Description:<input type="text" name="edesc" value="<%= e.getEventDesc() %>"> &nbsp;    <br>
            Event Day:<input type="text" name="eday" value="<%= e.getEventDay() %>">  &nbsp;    <br>
            Event Hour:<input type="text" name="ehour" value="<%= e.getEventHour() %>">   &nbsp;    <br>
            Event Type:<input type="text" name="etype" value="<%= e.getEventPublicOrPrivate() %>">    <br><br>
            <input type="submit" name="afterupdateevent" value="Update Event" >    <br>
            <% session.setAttribute("afterFilledUpdateEvent" , e); %>
        </form>
</body>
</html>
