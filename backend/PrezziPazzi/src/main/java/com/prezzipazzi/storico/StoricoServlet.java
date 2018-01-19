/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prezzipazzi.storico;

import com.prezzipazzi.acquisto.AcquistoServlet;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author giuse
 */

@WebServlet(urlPatterns = {"/Storico"})
public class StoricoServlet extends HttpServlet {
    
    
     public StoricoServlet() {
        super();
    }
     
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
         
           try {
            switch (request.getServletPath()) {
                case "/Storico":
                    String idProduct = request.getParameter("idProduct");
                    
                    System.out.println("Id product = "+idProduct);
                    
                    getServletContext()
                    .getRequestDispatcher("/www/public/html/purchase.jsp")
                    .forward(request, response);
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(AcquistoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
         
      
    }
}
