package pl.coderslab.MicroFirm.filters;

import pl.coderslab.MicroFirm.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(filterName = "AdminFilter")
public class AdminFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();
        User loggedUser = (User)session.getAttribute("loggedUser");

        if ("admin".equals(loggedUser.getLoginName())) {
            chain.doFilter(req, resp);
        } else {
            request.getRequestDispatcher("/home").forward(request, response);
        }
    }
}
