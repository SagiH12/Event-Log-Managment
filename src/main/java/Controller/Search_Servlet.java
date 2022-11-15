/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet(name = "Search_Servlet", urlPatterns = {"/Search_Servlet"})
public class Search_Servlet extends HttpServlet {

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
        
        response.setContentType("text/html;charset=UTF-8");

        Event event = new Event();
        String username = "";
        HttpSession session = request.getSession();
        if(session.getAttribute("USER_NAME")!=null)
            username = (String)session.getAttribute("USER_NAME");

        String searchEvent = (String)request.getParameter("searchevent");
        String searchstring = request.getParameter("daysearchbox");
        String searchstring2 = request.getParameter("hoursearchbox");

        //execute when user pressed 'Search' Button
        if(searchEvent != null)
        {
//           request.setAttribute("LendingMessage", "Try To Search");
           RequestDispatcher view = request.getRequestDispatcher("SearchFile.jsp");
           view.forward(request, response);
        }
        
        //return for Search Book page Available books
        if(searchstring != null && searchstring2 != null)
        {          
     //       request.setAttribute("SearchEventResults", event.searchEvent(Integer.parseInt(searchstring) , Integer.parseInt(searchstring2)));
            request.setAttribute("searchstring", searchstring);
            request.setAttribute("searchstring2", searchstring2);
            RequestDispatcher view = request.getRequestDispatcher("SearchResultFile.jsp");
            view.forward(request, response);
        }
 //       else if( (!searchstring.equals("") && searchstring == null) && searchstring2 != null){
 //           request.setAttribute("searchstring2", searchstring2);
  //      }
    }

    public List<Event> searchEvent(int day , int hour)
    {
        List<Event> foundEvents = new ArrayList<>();
        List<Event> allfindEvents = DAO.getEvents();

        for(int i=0;i< allfindEvents.size();i++)
        {
            if(allfindEvents.get(i).getEventDay() == day && allfindEvents.get(i).getEventHour() == hour)
            {
                foundEvents.add(allfindEvents.get(i));
            }
        }
        return foundEvents;
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Search_Servlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Search_Servlet.class.getName()).log(Level.SEVERE, null, ex);
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
