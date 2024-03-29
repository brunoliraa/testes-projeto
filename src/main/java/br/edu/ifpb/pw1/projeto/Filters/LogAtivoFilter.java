package br.edu.ifpb.pw1.projeto.Filters;

import br.edu.ifpb.pw1.projeto.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LogAtivoFilter", urlPatterns = "")
public class LogAtivoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        HttpSession session = httpRequest.getSession();


        User login = (User )session.getAttribute("login");
        if (login != null) {
        User usuarioLogado = (User) session.getAttribute("login");
        if (usuarioLogado != null) {

            HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;
            httpRequest.getRequestDispatcher("./usuario/usuario.jsp").forward(httpRequest,httpResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    }

    @Override
    public void destroy() {

    }
}
