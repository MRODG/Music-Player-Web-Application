/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author Mr marios
 */
@WebListener
public class ServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ServletContext sc = sce.getServletContext();
        // add HPCLabs properties to System and load driver 
        Properties props = System.getProperties();
        try{
            try(InputStream input = sc.getResourceAsStream("/WEB-INF/badnoise.properties")){
                props.load(input);
            }
            Class.forName(System.getProperty("badnoise.driver"));
            }catch(IOException | ClassNotFoundException pop){
            //log the error condition
            sc.log(pop.getMessage(), pop);
        }    
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
