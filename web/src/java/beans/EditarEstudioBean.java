

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import app.ejb.EstudiosFacade;
import app.ejb.UsuarioFacade;
import app.entity.Estudios;
import app.entity.Usuario;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author user
 */
@Named(value = "editarEstudioBean")
@RequestScoped
public class EditarEstudioBean implements Serializable {
    
    private String ubicacion;
    private String descripcion;
    private Date fechaComienzo, fechaComienzoOriginal;
    private Date fechaFinalizacion;
    private Estudios estudio;
    private String msgError;
    private Usuario usuario;
    
    @EJB
    private EstudiosFacade estudiosFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    
    @Inject
    private SesionBean sesionBean;
    @Inject
    private PerfilBean perfilBean;
    private String fechaComienzoStr;
    
    @PostConstruct
    public void init () {
        
        
        this.usuario = this.sesionBean.obtenerUsuario();
        
        
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public void setEstudio(Estudios estudio) {
        this.estudio = estudio;
    }

    public Estudios getEstudio() {
        return estudio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaComienzo() {
        return fechaComienzo;
    }

    public void setFechaComienzo(Date fechaComienzo) {
        this.fechaComienzo = fechaComienzo;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }
    
    public String goEdit (Estudios e) {
        estudio = e;        
        return "editarEstudio.jsf";
    }
    
    

    public String editar (){
        
        Estudios estudioNuevo, estudioOriginal;
        int i = usuario.getId();
        estudioOriginal = (Estudios) estudiosFacade.obtenerEstudio(usuario.getId(), fechaComienzoOriginal);
        //usuario = sesionBean.obtenerUsuario();
        usuario.getEstudiosCollection().remove(estudioOriginal);
        this.estudiosFacade.remove(estudioOriginal);
        
        estudioNuevo = new Estudios(usuario.getId(), fechaComienzo);
        estudioNuevo.setFechaFinalizacion(fechaFinalizacion);
        estudioNuevo.setDescripcion(descripcion);
        estudioNuevo.setUbicacion(ubicacion);
        
        this.estudiosFacade.create(estudioNuevo);
        usuario.getEstudiosCollection().add(estudioNuevo);
        this.usuarioFacade.edit(usuario);
        
        return "perfil?faces-redirect=true";
       
    }
    


    public String getFechaComienzoStr() {
        return fechaComienzoStr;
    }

    public void setFechaComienzoStr(String fechaComienzoStr) {
        this.fechaComienzoStr = fechaComienzoStr;
    }

    public Date getFechaComienzoOriginal() {
        return fechaComienzoOriginal;
    }

    public void setFechaComienzoOriginal(Date fechaComienzoOriginal) {
        this.fechaComienzoOriginal = fechaComienzoOriginal;
    }
    
    
    
     public String goEditEstudio(Estudios e) {
         
         this.usuario = this.sesionBean.obtenerUsuario();
        
        this.descripcion = e.getDescripcion();
        this.ubicacion = e.getUbicacion();
        this.fechaComienzo = e.getEstudiosPK().getFechaComienzo();
        this.fechaComienzoOriginal = e.getEstudiosPK().getFechaComienzo();
        this.fechaFinalizacion = e.getFechaFinalizacion();
                
        return "editarEstudio";
    }
    
}
