package pl.coderslab.MicroFirm.filters;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
//In the example below, our filter is registered by default for all the URL's in our application.
public class LoginFilter implements Filter {
    
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();
        Object loggedUser = session.getAttribute("loggedUser");
        if (loggedUser !=null) {
            chain.doFilter(req, resp);
        } else {
            request.getRequestDispatcher("/login/login").forward(request, response);  //wg mnie (i też działa)
            //response.sendRedirect(request.getContextPath() + "/login/login");  //wg Anha (jak najbardziej działa)
            //response.sendRedirect("/login/login");
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
