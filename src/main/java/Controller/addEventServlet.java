/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.DAO;
import Model.User;
import Model.Event;


@WebServlet(name = "addEventServlet", urlPatterns = {"/addEventServlet"})
public class addEventServlet extends HttpServlet {

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
            throws ServletException, IOException {
        HttpSession session = request.getSession();
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
 //       DAO.addEvent(liu.getUserName(), desc , Integer.parseInt(day), Integer.parseInt(hour) , type );

         List<Event> event = DAO.getEventByUserName(liu.getUserName());
         for(int i = 0; i < event.size(); i++){
             if(event.get(i).getUserName().equals(liu.getUserName()) && event.get(i).getEventDay() == Integer.parseInt(day) && event.get(i).getEventHour() == Integer.parseInt(hour)){
                 request.setAttribute("error", "Event with the same day , time , username is already in the system.");
                 request.getRequestDispatcher("errorAfterAddEvent.jsp").forward(request, response);
                 return;
             }
         }
        DAO.addEvent(liu.getUserName(), desc , Integer.parseInt(day), Integer.parseInt(hour) , type );
        int count = (Integer)session.getAttribute("userAddEventCounter");
        count++;
        session.setAttribute("userAddEventCounter" , count);
        request.getRequestDispatcher("AfterAddEvent.jsp").forward(request , response);

 //       Event event = DAO.eventGetUserByName(liu.getUserName());


 //       event.setUserName(liu.getUserName());
//        event.setEventDesc(desc);
 //       event.setEventDay(Integer.parseInt(day));
 //       event.setEventHour(Integer.parseInt(hour));
//        event.setEventPublicOrPrivate(type);

//        DAO.eventGetUserByName(liu.getUserName());
        /*
        if(event.getUserName().equals(liu.getUserName()) && event.getEventDay() == Integer.parseInt(day) && event.getEventHour() == Integer.parseInt(hour)){
            request.setAttribute("error", "Event with the same day , time , username is already in the system.");
            request.getRequestDispatcher("errorAfterAddEvent.jsp").forward(request, response);
            return;
        }
         */
//        request.getRequestDispatcher("AfterAddEvent.jsp").forward(request , response);
//        DAO.isEventExist();

    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
