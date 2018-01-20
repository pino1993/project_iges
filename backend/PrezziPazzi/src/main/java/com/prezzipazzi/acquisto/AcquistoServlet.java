/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prezzipazzi.acquisto;
import com.prezzipazzi.autenticazione.AuthException;
import com.prezzipazzi.bean.Catalogo;
import com.prezzipazzi.bean.Offerte;
import com.prezzipazzi.bean.User;
import com.prezzipazzi.bean.Utente;
import com.prezzipazzi.manager.ManagerAcquisto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andrea
 */
@WebServlet(urlPatterns = {"/Purchase"})
public class AcquistoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcquistoServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            switch (request.getServletPath()) {
                case "/Purchase":
                    String idProduct = request.getParameter("idProduct");
                    
                    //System.out.println("Id product = "+idProduct);
                    Utente utente = (Utente) request.getSession().getAttribute("utente");
                    User u = null ;
                    
                    if(!utente.getEmail().endsWith("@prezzipazzi.com")){
                        u = (User) request.getSession().getAttribute("utente");
                    }
                    else{
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.getWriter().write("Acquisto non valido per Admin");
                        return;
                    }
                    //System.out.println("Email utente = "+u.getEmail());
                    
                    Catalogo cat = (Catalogo) request.getSession().getAttribute("catalogo");
                    
                    Offerte o = cat.getItemFromId(Integer.parseInt(idProduct));
                    //System.out.println("prezzo = "+o.getPrezzo());
                    //System.out.println("credito = "+u.getCredito());
                    
                    if(o.eAcquistabile()){
                        if(u.getCredito() >= o.getPrezzo()){//credito disponibile
                            ManagerAcquisto mAcq = new ManagerAcquisto();
                            double residuo = (u.getCredito() - o.getPrezzo());
                            mAcq.purchase(u.getEmail(), o.getId(),residuo);
                        }
                        else{//credito inssuficiente
                                
                        }
                    }
                    else{//offerta non acquistabile
                        
                    }
                    
                    
                    
                    getServletContext()
                    .getRequestDispatcher("/www/public/html/purchase.jsp")
                    .forward(request, response);
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(AcquistoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcquistoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AuthException ex) {
            Logger.getLogger(AcquistoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
