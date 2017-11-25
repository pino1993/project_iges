/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prezzipazzi.autenticazione;

import com.google.gson.Gson;
import com.prezzipazzi.bean.Admin;
import com.prezzipazzi.bean.TipoUtente;
import com.prezzipazzi.bean.User;
import com.prezzipazzi.bean.Utente;
import com.prezzipazzi.manager.ManagerAutenticazione;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andrea
 */
@WebServlet(urlPatterns = {"/Login", "/Registrazione", "/Logout"})
public class AutenticazioneServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutenticazioneServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> options = new LinkedHashMap<>();

        try {
            switch (request.getServletPath()) {
                case "/Login":
                    String mail = request.getParameter("email");
                    String pass = request.getParameter("password");
                    TipoUtente tipo = controllaTipo(mail);
                    Utente utente = null;
                    if (tipo == TipoUtente.ADMIN) {
                        utente = loginAdmin(mail, pass);

                    } else if (tipo == TipoUtente.USER) {
                        utente = loginUser(mail, pass);

                    } else {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.getWriter().write("Email non valida!");
                        return;
                    }
                    request.getSession().setAttribute("utente", utente);
                    request.getSession().setAttribute("tipoUtente", tipo);
                    options.put("messaggio", "Login effettuato!");
                    break;
                case "/Registrazione":
                    break;
                case "/Logout":
                    break;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            // System.out.println(e.getErrorCode());
            // Errore di query
            if (e.getErrorCode() == 1062) {
                response.getWriter().write("Email già utilizzata!");
            } else {
                response.getWriter().write(e.getMessage());
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } catch (AuthException ae) {
            // Errore di registrazione
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ae.getMessage());
            return;
        }
        String json = new Gson().toJson(options);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private TipoUtente controllaTipo(String email) {
        if (email != null) {
            email = email.trim().toLowerCase();
            if (email.endsWith("@prezzipazzi.com")) {
                return TipoUtente.ADMIN;
            } else {
                return TipoUtente.USER;
            }
        }
        return null;
    }

    private synchronized Admin loginAdmin(String email, String password)
            throws SQLException, AuthException {
        ManagerAutenticazione mAutenticazione = new ManagerAutenticazione();
        if (email != null && password != null) {
            email = email.trim().toLowerCase();
            if (!email.equals("") && !password.contains(" ")) {

                return mAutenticazione.loginAdmin(email, password);

            } else {
                throw new AuthException("Campi non validi!");

            }

        } else {
            throw new AuthException("Non fare il furbetto!");

        }
    }

    private synchronized User loginUser(String email, String password)
            throws SQLException, AuthException {

        ManagerAutenticazione mAutenticazione = new ManagerAutenticazione();
        if (email != null && password != null) {
            email = email.trim().toLowerCase();
            if (!email.equals("") && !password.contains(" ")) {

                return mAutenticazione.loginUser(email, password);

            } else {
                throw new AuthException("Campi non validi!");

            }

        } else {
            throw new AuthException("Non fare il furbetto!");

        }

    }
}
