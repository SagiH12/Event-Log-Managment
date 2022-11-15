package Model;
import java.util.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    private static String connString = "jdbc:derby:C:/Users/שגיא/Desktop/לימודים/חומר ללימודים/תכנות בסיסי באינטרנט/demo/my_db";

    public static User getUserByNamePassword(String name)
    {
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection   cn = DriverManager.getConnection(connString,"root", "root");
            Statement  st = cn.createStatement();
            ResultSet  rs = st.executeQuery("SELECT * FROM APP.\"USER\"  where USERNAME='"+name+"'");
            if (rs.next()){
                User user = new User();
                user.setUserName(rs.getString("UserName"));
                user.setPassword(rs.getString("Password"));
                st.close();
                return user;
            }
            return null;

        }
        catch (Throwable t){
            return null;
        }
    }

    public static void addEvent(String username , String desc , int day , int hour , String type){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection   cn = DriverManager.getConnection(connString, "root", "root");
            Statement  st = cn.createStatement();

            int res = st.executeUpdate("INSERT INTO APP.EVENT (USERNAME , EVENTDESCRIPTION, EVENTDAY, EVENTHOUR, EVENTTYPE) \n" +
                    "	VALUES ('"+username+"' , '" + desc + "', "+day+","+hour+", '"+type+"')\n");
            cn.close();
        } catch (SQLException | ClassNotFoundException e) {
        }


    }

    public static List<Event> getEventByUserName(String name)
    {
        List<Event> result = new ArrayList<Event>();
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection   cn = DriverManager.getConnection(connString,"root", "root");
            Statement  st = cn.createStatement();
            ResultSet  rs = st.executeQuery("SELECT * FROM APP.\"EVENT\"  where USERNAME='"+name+"'");
            while (rs.next()){
                Event event = new Event();
                event.setUserName(rs.getString("USERNAME"));
                event.setEventDay(rs.getInt("EVENTDAY"));
                event.setEventHour(rs.getInt("EVENTHOUR"));
                result.add(event);
            }
            st.close();
        }
        catch (Throwable t){
            return null;
        }
        return result;
    }

    public static List<Event> getEvents()
    {
        List<Event> result = new ArrayList<Event>();
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection   cn = DriverManager.getConnection(connString,"root", "root");
            Statement  st = cn.createStatement();
            ResultSet  rs = st.executeQuery("SELECT * FROM APP.\"EVENT\"");
            while (rs.next()){
                Event event = new Event();
                event.setUserName(rs.getString("USERNAME"));
                event.setEventDay(rs.getInt("EVENTDAY"));
                event.setEventHour(rs.getInt("EVENTHOUR"));
                event.setEventDesc(rs.getString("EVENTDESCRIPTION"));
                event.setEventPublicOrPrivate(rs.getString("EVENTTYPE"));
                result.add(event);
            }
            st.close();
        }
        catch (Throwable t){
            return null;
        }
        return result;
    }

    public static void updateEvent(Event newEvent , Event oldEvent) throws ClassNotFoundException
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection   cn = DriverManager.getConnection(connString,"root", "root");
            Statement st = cn.createStatement();
            String SqlString = "UPDATE APP.EVENT SET \"EVENTDESCRIPTION\" = '"+newEvent.getEventDesc()+"', \"EVENTDAY\" = "+newEvent.getEventDay()+" , \"EVENTHOUR\" = "+newEvent.getEventHour()+" , \"EVENTTYPE\" = '"+newEvent.getEventPublicOrPrivate()+"' , \"USERNAME\" = '"+newEvent.getUserName()+
                    "' WHERE \"EVENTDESCRIPTION\" = '"+oldEvent.getEventDesc()+"' AND \"EVENTDAY\" = "+oldEvent.getEventDay()+" AND \"EVENTHOUR\" = "+oldEvent.getEventHour()+" AND \"EVENTTYPE\" = '"+oldEvent.getEventPublicOrPrivate()+"' AND \"USERNAME\" = '"+oldEvent.getUserName()+"'";
            int rowEffected = st.executeUpdate(SqlString);
        }
        catch(Exception e){}
    }

    public static void deleteEvent(Event event) throws ClassNotFoundException
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection   cn = DriverManager.getConnection(connString,"root", "root");
            Statement st = cn.createStatement();
            String SqlString = "DELETE FROM APP.EVENT WHERE \"EVENTDESCRIPTION\" = '"+event.getEventDesc()+"' AND \"EVENTDAY\" = "+event.getEventDay()+" AND \"EVENTHOUR\" = "+event.getEventHour()+" AND \"EVENTTYPE\" = '"+event.getEventPublicOrPrivate()+"' AND \"USERNAME\" = '"+event.getUserName()+"'";
            int rowEffected = st.executeUpdate(SqlString);
        }
        catch(Exception e){}
    }



   /*
    public static Event eventGetUserByName(String name)
    {
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection   cn = DriverManager.getConnection(connString,"root", "root");
            Statement  st = cn.createStatement();
            ResultSet  rs = st.executeQuery("SELECT * FROM APP.\"EVENT\"  where USERNAME='"+name+"'");
            if (rs.next()){
                Event event = new Event();
                event.setUserName(rs.getString("USERNAME"));
                event.setEventDay(rs.getInt("EVENTDAY"));
                event.setEventHour(rs.getInt("EVENTHOUR"));
                st.close();
                return event;
            }
            return null;

        }
        catch (Throwable t){
            return null;
        }
    }
    */


}
