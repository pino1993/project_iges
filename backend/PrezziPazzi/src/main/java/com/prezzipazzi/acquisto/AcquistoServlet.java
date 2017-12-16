/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prezzipazzi.acquisto;
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
