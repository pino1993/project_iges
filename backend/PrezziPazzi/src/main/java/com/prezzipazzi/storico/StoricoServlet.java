/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prezzipazzi.storico;

import com.google.gson.Gson;
import com.prezzipazzi.acquisto.AcquistoServlet;
import com.prezzipazzi.autenticazione.AuthException;
import com.prezzipazzi.bean.Catalogo;
import com.prezzipazzi.bean.Offerte;
import com.prezzipazzi.bean.Utente;
import com.prezzipazzi.manager.ManagmentStorico;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
                  
                    
                    Utente u=(Utente) request.getSession().getAttribute("utente");
                    ManagmentStorico mStorico = new ManagmentStorico();
                  
                              
             
                     request.getSession().setAttribute("cat",mStorico.getStoricoOfferte(u.getEmail()));
                    
         
                    getServletContext()
                    .getRequestDispatcher("/www/public/html/storico.jsp")
                    .forward(request, response);
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(AcquistoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
             Logger.getLogger(StoricoServlet.class.getName()).log(Level.SEVERE, null, ex);
         } catch (AuthException ex) {
             Logger.getLogger(StoricoServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
         
      
    }
}
