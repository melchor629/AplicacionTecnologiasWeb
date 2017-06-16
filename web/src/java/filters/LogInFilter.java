package filters;

import beans.SesionBean;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Melchor Alejo Garau Madrigal
 */
@WebFilter(filterName = "LogInFilter", urlPatterns = {"*.jsf"})
public class LogInFilter implements Filter {
    @Inject private SesionBean sb;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String url = httpServletRequest.getRequestURI();
        if(url.contains("index.jsf") || url.contains("registro.jsf") || url.endsWith(httpServletRequest.getContextPath() + "/")) {
            if(sb.haIniciadoSesion()) {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/perfil.jsf");
                return;
            }
        } else if(!url.contains("error404.jsf") && !url.contains("error500.jsf")) {
            if(!sb.haIniciadoSesion()) {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
