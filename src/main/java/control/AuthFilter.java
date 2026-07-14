package control;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String risorsa = req.getRequestURI();

        if (risorsa.contains("Admin")) {

            HttpSession sessione = req.getSession(false);

            String ruolo = null;
            if (sessione != null) {
                ruolo = (String) sessione.getAttribute("ruolo");
            }

            if (ruolo == null || !ruolo.equals("admin")) {
                res.sendRedirect(req.getContextPath() + "/login.jsp?errore=nonAutorizzato");
                return; 
            }
            
        }

        chain.doFilter(request, response);
    }
    
   
}