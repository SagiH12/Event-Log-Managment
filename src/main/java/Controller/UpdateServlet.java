/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.DAO;
import Model.User;
import Model.Event;


@WebServlet(name = "UpdateServlet", value = "/UpdateServlet")
public class UpdateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {

        HttpSession session = request.getSession();
        String updateClick = request.getParameter("update");
        String deleteClick = request.getParameter("delete");

        if (updateClick != null || deleteClick != null) {
            int id = Integer.parseInt(request.getParameter("hiddenID"));
            List<Event> events = (List<Event>)session.getAttribute("allEvents");
            Event currentEvent = getClickedEvent(events , id);
            if(updateClick != null){
                request.setAttribute("updateEvent", currentEvent);
                RequestDispatcher view = request.getRequestDispatcher("FillEventUpdate.jsp");
                view.forward(request, response);
            }
            else{
                DAO.deleteEvent(currentEvent);
                int count = (Integer)session.getAttribute("userDeleteEventCounter");
                count++;
                session.setAttribute("userDeleteEventCounter" , count);
                RequestDispatcher view = request.getRequestDispatcher("SearchResultFileAfterUpdate.jsp");
                view.forward(request, response);
            }
        }

        String afterUpdateClick = request.getParameter("afterupdateevent");
//        Event e  = (Event)session.getAttribute("afterFilledUpdateEvent");
        if(afterUpdateClick != null){
            Event e  = (Event)session.getAttribute("afterFilledUpdateEvent");
            Event oldEventData = new Event(e.getEventDesc() , e.getEventDay() , e.getEventHour() , e.getEventPublicOrPrivate() , e.getUserName());
       //     Event oldEventData = e; //object shallow copy
            String desc = request.getParameter("edesc");
            String day = request.getParameter("eday"); // int
            String hour = request.getParameter("ehour"); // int
            String type = request.getParameter("etype");
            int eday = Integer.parseInt(day);
            int ehour = Integer.parseInt(hour);
            e.setEventDesc(desc);
            e.setEventDay(eday);
            e.setEventHour(ehour);
            e.setEventPublicOrPrivate(type);
            DAO.updateEvent(e , oldEventData);
            int count = (Integer)session.getAttribute("userUpdateEventCounter");
            count++;
            session.setAttribute("userUpdateEventCounter" , count);
 //           String s = "success";
 //           request.setAttribute("updateSuccess" , s);
            RequestDispatcher view = request.getRequestDispatcher("SearchResultFileAfterUpdate.jsp");
            view.forward(request, response);

 //           RequestDispatcher vieww = request.getRequestDispatcher("FillEventUpdate.jsp");
  //          vieww.forward(request, response);
        }
    }
    public Event getClickedEvent(List<Event> allEvents , int id){
        Event event = new Event();
        for(int i = 0; i < allEvents.size(); i++){
            if(allEvents.get(i).getHiddenID() == id) {
                event = allEvents.get(i);
                break;
            }
        }
        return event;
    }
        /*
        List<Event> events = DAO.getEvents();
        Event event;
        for(int i = 0; i < events.size(); i++){
            if(events.get(i).getHiddenID() == id)
                event = events.get(i);
        }
        */
//        Event event = (Event)session.getAttribute("curEvent");


        /*
        Event userEvent = new Event();
        request.getat
        userEvent.getHiddenID();
        userEvent.getUserName();
        userEvent.getEventDay();
        userEvent.getEventHour();
        userEvent.getEventDesc();
        userEvent.getEventPublicOrPrivate();
*/


/*
        User liu = new User();
        liu.setUserName((String)session.getAttribute("USER_NAME"));
        if (liu == null){
            request.setAttribute("error", "You must be logged in to Add Event.");
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
        String desc = request.getParameter("eventdesc");
        String day = request.getParameter("day"); // int
        String hour = request.getParameter("hour"); // int
        String type = request.getParameter("type");

        List<Event> event = DAO.getEventByUserName(liu.getUserName());
        for(int i = 0; i < event.size(); i++){
            if(event.get(i).getUserName().equals(liu.getUserName()) && event.get(i).getEventDay() == Integer.parseInt(day) && event.get(i).getEventHour() == Integer.parseInt(hour)){
                request.setAttribute("error", "Event with the same day , time , username is already in the system.");
                request.getRequestDispatcher("errorAfterAddEvent.jsp").forward(request, response);
                return;
            }
        }
        DAO.addEvent(liu.getUserName(), desc , Integer.parseInt(day), Integer.parseInt(hour) , type );
        request.getRequestDispatcher("AfterAddEvent.jsp").forward(request , response);

 */


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
