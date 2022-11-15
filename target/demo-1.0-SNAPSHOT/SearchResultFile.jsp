<%@ page import="Model.Event" %><%--
  Created by IntelliJ IDEA.
  User: שגיא
  Date: 06/03/2022
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.List"%>
<%@page import="Model.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    table, th, td {
        border: 1px solid;
    }
</style>
<style>        a:link, a:visited {
    background-color: #808080;
    color: white;
    padding: 14px 25px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
}</style>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search Results</title>
</head>
<body>
<% String userWelcome = (String)session.getAttribute("USER_NAME"); %>
<% if(!userWelcome.equals("Guest")){ %>
<h5 id="name" style="font-size: small;text-align: left;">Welcome : <%= userWelcome%> </h5>
<% } else { %>
<h5 id="name" style="font-size: small;text-align: left;">Welcome : <%= userWelcome %> <%= " , " + session.getId() %> </h5>
<% } %>
<h1>The Results Are:</h1>
<table>
    <%  String userCheck = (String)session.getAttribute("USER_NAME"); %>
    <% if(!userCheck.equals("Guest")){ %>
    <tr>
        <th>Description</th><th>Day</th><th>Hour</th><th>Type</th><th>Name</th><th>Update</th><th>Delete</th>
    </tr>
    <%} else{ %>
    <tr>
        <th>Description</th><th>Day</th><th>Hour</th><th>Type</th><th>Name</th>
    </tr>
    <% } %>
    <%
        /*            if( (s.equals("") && s2.equals("")) || e.getEventDay() == Integer.parseInt(s)
            && e.getEventHour() == Integer.parseInt(s2)
            || e.getEventDay() == Integer.parseInt(s) || e.getEventHour() == Integer.parseInt(s2) )

         */
//        String isUpdateSuccess = (String)request.getAttribute("updateSuccess"); // success
        int eventsCounter = 0;
        String currentUser = (String)session.getAttribute("USER_NAME");
        String s = (String)request.getAttribute("searchstring"); //day search field
        String s2 = (String)request.getAttribute("searchstring2"); //hour search field
        String dayField = ""; String hourField = "";
        if(s != null) //looks like its useless cuz s is "" or with value
            dayField = s;
        if(s2 != null) //same here
            hourField = s2;
        boolean isEmpty = true;
        int hiddenCounter = 1;
        List<Event> events = DAO.getEvents();
        for (Event e : events){
            //the if statement checking the search fields whether they are: 1)both empty , 2)both have values , 3)day field has value , hour field doesn't have value , 4)day field doesn't have value , hour field has value.
            if( (dayField.equals("") && hourField.equals(""))
                    || (!dayField.equals("") && !hourField.equals("")) && e.getEventDay() == Integer.parseInt(dayField) && e.getEventHour() == Integer.parseInt(hourField)
            || hourField.equals("") && e.getEventDay() == Integer.parseInt(dayField)
                    || dayField.equals("") && e.getEventHour() == Integer.parseInt(hourField) ){
                isEmpty = false;
                if(!currentUser.equals("Guest") || currentUser.equals("Guest") && e.getEventPublicOrPrivate().equals("public")){
    %>
    <tr>
        <td><%= e.getEventDesc() %></td>
        <td><%= e.getEventDay() %></td>
        <td><%= e.getEventHour() %></td>
        <td><%= e.getEventPublicOrPrivate() %></td>
        <td><%= e.getUserName() %></td>
        <% } %>
        <%if(currentUser.equals(e.getUserName()) && !currentUser.equals("Guest") || e.getEventPublicOrPrivate().equals("private") && !currentUser.equals("Guest")) {
            session.setAttribute("curEvent" , e);
            session.setAttribute("allEvents" , events);%>
        <% e.setHiddenID(hiddenCounter); %>
        <td><form action = "UpdateServlet">
            <input type="submit" name="update" value="Update">
            <input type="hidden" name="hiddenID" value="<%= e.getHiddenID() %>">
        </form></td>
        <td><form action = "UpdateServlet">
            <input type="submit" name="delete" value="Delete">
            <input type="hidden" name="hiddenID" value="<%= e.getHiddenID() %>">
        </form></td>
    </tr>
    <%
                hiddenCounter++;
            }
            eventsCounter++;
            }

    %>
    <%
        }
        int count = (Integer)session.getAttribute("userSearchEventCounter");
        count += eventsCounter;
        session.setAttribute("userSearchEventCounter" , count);
        if(isEmpty) {
            request.setAttribute("error", "Could not find any events in this search");
            request.getRequestDispatcher("errorAfterAddEvent.jsp").forward(request, response);
        }
    %>
</table>

<% if(!userCheck.equals("Guest")){ %>
<p><a href="MainFile.jsp">Back To The Main Page</a></p>
<%} else{ %>
<p><a href="GuestMainFile.jsp">Back To The Main Page</a></p>
<% } %>
</body>
</html>
