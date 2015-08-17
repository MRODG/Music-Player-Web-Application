/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mr marios
 */
public class loginservlet extends HttpServlet {
    String user=null;
    String pass=null;
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
        try {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;

            String dbPassword = System.getProperty("badnoise.password");
            String dnUrl = System.getProperty("badnoise.url");
            String dbUser = System.getProperty("badnoise.user");
            connection = DriverManager.getConnection(dnUrl, dbUser, dbPassword);
            String firstname= null;
            String surname = null;
            String username=null;
            String password=null;
            
            user = request.getParameter("username");
            pass = request.getParameter("password");
            
            System.out.println(request.getParameter("username"));
            System.out.println(request.getParameter("password"));
            
            if(user.equals("")||pass.equals("")){
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
                PrintWriter writer= response.getWriter();
                writer.println("<font color=red>Please fill all fields.</font>");
                rd.include(request, response);
                return;
            }

            String useerQuery = "select * from users where UserName=?";
            preparedStatement = connection.prepareStatement(useerQuery);
            preparedStatement.setString(1, user);
            result = preparedStatement.executeQuery();
            
            while (result.next()) {
                firstname = result.getString("FirstName");
                surname = result.getString("LastName");
                username = result.getString("UserName");
                password = result.getString("Password");
            }
            if(username==null){
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
                PrintWriter writer= response.getWriter();
                writer.println("<font color=red>Username does not exist!.</font>");
                rd.include(request, response);
                return;
            }
            String fullname = "Logged in "+firstname+" "+surname;
            System.out.println(username);
            System.out.println(password);

            if(username.equals(user)&&password.equals(pass)){
                request.getSession().setAttribute("UserName", username);
                request.getSession().setAttribute("FullName", fullname);
                response.sendRedirect("artists.jsp");
            }
            else{
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
                PrintWriter writer= response.getWriter();
                writer.println("<font color=red>Either user name or password is wrong.</font>");
                rd.include(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(loginservlet.class.getName()).log(Level.SEVERE, null, ex);
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
