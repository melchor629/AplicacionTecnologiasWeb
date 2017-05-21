package beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

/**
 * @author Melchor Alejo Garau Madrigal
 */
@RequestScoped
@Named
public class Error505Bean implements Serializable {
    private HttpServletRequest r;

    public Throwable getThrowable() {
        return (Throwable) r.getAttribute("javax.servlet.error.exception");
    }

    public String getServletName() {
        String servletName = (String) r.getAttribute("javax.servlet.error.servlet_name");
        if(servletName == null) {
            servletName = "Desconocido";
        }
        return servletName;
    }

    public String getRequestUri() {
        String requestUri = (String) r.getAttribute("javax.servlet.error.request_uri");
        if(requestUri == null) {
            requestUri = "Desconocido";
        }
        return requestUri;
    }

    public String getStackTrace() {
        Throwable throwable = getThrowable();
        if(throwable.getStackTrace() != null && throwable.getStackTrace().length > 0) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            throwable.printStackTrace(pw);
            pw.close();
            return sw.getBuffer().toString();
        } else {
            return "";
        }
    }

    @PostConstruct public void init() {
        r = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
}
