package control;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class DBContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent evento) {

        ServletContext context  = evento.getServletContext();

        DataSource connessioneDB = null;

        try {
            Context initialContext  = new InitialContext();
            Context envContext = (Context) initialContext.lookup("java:comp/env");

            connessioneDB = (DataSource) envContext.lookup("jdbc/demarcaf");

        } catch (NamingException e) {
            System.out.println("Errore nel recupero del DataSource: " + e.getMessage());
        }

        
        context.setAttribute("DataSource", connessioneDB);
    }

    @Override
    public void contextDestroyed(ServletContextEvent evento) {
    }
}