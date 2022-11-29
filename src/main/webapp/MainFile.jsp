

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="mainCSS.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Event Management</title>
    </head>
    <body style="background-image: url(eventsPicture.jpg); background-size: 100%; background-repeat: no-repeat; background-attachment: fixed;border: 0.5px solid #333"/>
     <h5 id="name" style="font-size: small;text-align: left;">Welcome : <%= (String)session.getAttribute("USER_NAME")%> </h5>
        <form action="LogOut_Servlet">
            <br>
                <input type='submit' name='logout' value='Finish'>
            <br>
        </form>
       <center style="background: grey; margin-top: 1%; color: #f1f1f1; font-size: larger">Welcome To The Event Management Page</center>
            <ul style="background-color: #58c7c7">
                <li style="background-color: #333; margin-right: 1%" class="dropdown"><a class="dropbtn">Manager Event </a>
                    <div class="dropdown-content">
                            <form action="addEventServlet">
                                Event Description:<input type="text" name="eventdesc"> &nbsp; Event Day:<input type="text" name="day">  &nbsp; Event Hour:<input type="text" name="hour">   &nbsp; Event Type:<input type="text" name="type">
                                <input type="submit" value="Add Event">
                            </form>
                     </div>
                </li>
                <li><form action="Search_Servlet">
                        <input style="padding: 15px 15px; margin-top: 1%" type="submit" name="searchevent" value="Search Event">
                    </form>
                </li>
            </ul>
    </body>
</html>
