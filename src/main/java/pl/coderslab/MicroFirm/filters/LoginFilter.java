package pl.coderslab.MicroFirm.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


//filtr ma zastosowanie do wszystkich adresów pasujących do '/*':
@WebFilter("/QQQQQQQQQ*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        //aby móc wyciągnąć dane z sesji  musimy zrzutować obiekt
        //'filtrowy' klasy 'ServletRequest' na obiekt 'servletowy' klasy 'HttpServletRequest':
        HttpServletRequest request = (HttpServletRequest) req;
        //aby móc zrobić przekierowanie musimy zrzutować obiekt
        //'filtrowy' klasy 'ServletResponse' na obiekt 'servletowy' klasy 'HttpServletResponse':
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();
        //Metoda 'getAttribute()' pobiera z sesji atrybut ustawiony pod kluczem 'username' i tworzy obiekt typu 'Object'
        //W przypadku braku atrybutu otrzymamy null.
        Object username = session.getAttribute("loginName");
        //jeśli w sesji nie ma atrybutu 'loginName' to przejdź do kolejnego filtra:
        if (username!=null) {
            chain.doFilter(req, resp);

        } else {
            //w przeciwnym razie przekieruj na stronę akcji  '/login':
            request.getRequestDispatcher("/login").forward(request, response);  //wg mnie (i też działa)
            //response.sendRedirect(request.getContextPath() + "/login");  //wg Anha (jak najbardziej działa)
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
