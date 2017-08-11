/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.logging.Logger;

/**
 *
 * @author asortega
 */
//@WebServlet(urlPatterns = {"/Servlet"})

@WebServlet (name = "Servlet" + "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger("Producer");
    
    @Inject 
    private Producer mandador;
    @Inject
    private Consumer recibidor;
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String itemId = request.getParameter("itemId");
        String customerId = request.getParameter("customerId");
        String orderId = request.getParameter("orderId");
        String quantityId = request.getParameter("quantityId");
        
        
        logger.log(Logger.Level.INFO, "Start sending order request..");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>JMS PRACTICA</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Mandar / Recibir Mensajes  </h1>");
            String order = ("<orderId = " + orderId + " itemId = " + itemId + " customerId = " + customerId + " quantityId = " + quantityId + ">");
            mandador.mandarMensaje(order);
            logger.log(Logger.Level.INFO, " Orden mandada: " + order);
            out.println("Mensaje mandado a myOrder");
            recibidor.recibirMensaje();
            out.println("<p><a href=\"" + request.getContextPath() + "\" Send Another</p>");
            out.println("</body>");
            out.println("</html>");
        }
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
