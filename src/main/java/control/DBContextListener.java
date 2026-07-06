package control;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

//@WebListener dice a Tomcat che questa classe deve "ascoltare"
@WebListener
public class DBContextListener implements ServletContextListener {

    // Metodo eseguito una volta
    @Override
    public void contextInitialized(ServletContextEvent evento) {

        ServletContext bachecaGlobale = evento.getServletContext();

        DataSource connessioneDB = null;

        try {
            // Apro l'archivio delle risorse configurate in context.xml
            Context archivio = new InitialContext();
            //recupero datasource dal context.xml
            Context scaffaleRisorse = (Context) archivio.lookup("java:comp/env");

            // Prendo la connessione che avevo chiamato "jdbc/demarcaf" nel context.xml
            connessioneDB = (DataSource) scaffaleRisorse.lookup("jdbc/demarcaf");

        } catch (NamingException e) {
            System.out.println("Errore nel recupero del DataSource: " + e.getMessage());
        }

        
        // Da qui in poi ogni Servlet potra' prenderla 
        bachecaGlobale.setAttribute("DataSource", connessioneDB);
    }

    @Override
    public void contextDestroyed(ServletContextEvent evento) {
    }
}