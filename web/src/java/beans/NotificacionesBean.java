/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import app.ejb.MensajeFacade;
import app.entity.Mensaje;
import app.entity.Usuario;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Rodrii
 */
@Named(value = "notificacionesBean")
@RequestScoped
public class NotificacionesBean {

    /**
     * Creates a new instance of NotificacionesBean
     */
    
    private Usuario u;
    
    @Inject
    private SesionBean sesion;
    
    @EJB
    private MensajeFacade mf;
    
    private Collection<Mensaje> mensajeNoLeido;
    
    public NotificacionesBean() {
    }
    
    @PostConstruct
    public void init(){
        this.u = sesion.obtenerUsuario();
        this.mensajeNoLeido = mf.mensajesNoLeidoUsuario(0);
    }
    
    public String notificar(){
        return "notificaciones";
    }

    public Collection<Mensaje> getMensajeNoLeido() {
        return mensajeNoLeido;
    }

    public void setMensajeNoLeido(Collection<Mensaje> mensajeNoLeido) {
        this.mensajeNoLeido = mensajeNoLeido;
    }
    
    
    
}
