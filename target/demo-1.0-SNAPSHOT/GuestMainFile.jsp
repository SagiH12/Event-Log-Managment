

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="mainCSS.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Event Management</title>
    </head>
    <body style="background-image: url(eventsPicture.jpg); background-size: 100%; background-repeat: no-repeat; background-attachment: fixed;border: 0.5px solid #333"/>
     <% session.setAttribute("USER_NAME", "Guest"); %>
     <h5 id="name" style="font-size: small;text-align: left;">Welcome : <%= (String)session.getAttribute("USER_NAME") + ", "  %> <%= session.getId() %> </h5>
        <form action="LogOut_Servlet">
            <br>
                <input type='submit' name='logout' value='Finish'>
            <br>
        </form>
       <center style="background: grey; margin-top: 1%; color: #f1f1f1; font-size: larger">Welcome To The Event Management Page</center>
            <ul style="background-color: #58c7c7">
                <li><form action="SearchResultFileAfterUpdate.jsp">
                        <input type="submit" name="searchevent" value="Search Event">
                    </form>
                </li>
            </ul>
    </body>
</html>
