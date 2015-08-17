/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.servlets;

import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
 * @author Mr marios
 */
@WebServlet(name = "Songs", urlPatterns = {"/Songs"})
public class Song extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    String itemQuery;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        StringWriter ostr = new StringWriter();
        JsonWriter jWriter = new JsonWriter(ostr);
        String artistNo =request.getParameter("ArtistNumber");
        PrintWriter writer = response.getWriter();
        String json ="";
        
        
        if (artistNo!=null){
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet results = null;
            
            String password = System.getProperty("badnoise.password");
            String user = System.getProperty("badnoise.user");
            String url = System.getProperty("badnoise.url");
            connection = DriverManager.getConnection(url, user, password);
            String itemQuery = "select * from songs where ArtistNumber=?";

            preparedStatement = connection.prepareStatement(itemQuery);
            
            preparedStatement.setString(1, artistNo);
            results= preparedStatement.executeQuery();

            jWriter.beginArray();
            while (results.next()) {
                jWriter.beginObject();
                jWriter.name("Title").value(results.getString("Title"));
                jWriter.name("ArtistNumber").value(results.getString("ArtistNumber"));
                jWriter.name("TrackTime").value(results.getString("TrackTime"));
                jWriter.name("Composer").value(results.getString("Composer"));
                jWriter.name("Lyricist").value(results.getString("Lyricist"));
                jWriter.name("FileLocation").value(results.getString("FileLocation"));
                jWriter.name("free").value(results.getString("free"));
                jWriter.name("download").value(results.getString("download"));
                jWriter.endObject();
                
            }
            jWriter.endArray();
            
            //ystem.out.println(json);
            json = ostr.toString();
            writer.println(json);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Song.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(Song.class.getName()).log(Level.SEVERE, null, ex);
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
