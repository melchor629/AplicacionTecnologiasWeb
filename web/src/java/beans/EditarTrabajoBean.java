/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import app.ejb.ExperienciaLaboralFacade;
import app.ejb.UsuarioFacade;
import app.entity.ExperienciaLaboral;
import app.entity.Usuario;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Lucia y Francis
 */
@Named
@RequestScoped
public class EditarTrabajoBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    UsuarioFacade fachadaUsuario;

    @EJB
    ExperienciaLaboralFacade fachadaTrabajo;

    @Inject
    SesionBean sesionBean;

    //DESCOMENTAR CUANDO ESTE CREADO
    //@Inject PerfilBean perfilBean;
    Usuario usuario;
    ExperienciaLaboral trabajoSeleccionado;
    Date fechaComienzo, fechaFinalizacion, fechaComienzoOriginal;
    String error;
    
    private String puesto;
    private String empresa;
    private String web;

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @PostConstruct
    public void init(){
        
        this.usuario = this.sesionBean.obtenerUsuario();
        error= null;
        
    }
    

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    

    public ExperienciaLaboral getTrabajoSeleccionado() {
        return trabajoSeleccionado;
    }

    public void setTrabajoSeleccionado(ExperienciaLaboral trabajoSeleccionado) {
        this.trabajoSeleccionado = trabajoSeleccionado;
    }

    public void EditarTrabajoBean() {

    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String doGuardar() {

        
        //si hay un error
        if (error != null) {

            error = "Ha ocurrido un Error" + error;
            return "editarTrabajo";
        } else {

            error = null;

            //como no puedo editar el trabajo creo borro el que tengo y creo uno nuevo
            ExperienciaLaboral trabajo, trabajoOriginal;
            
            
           
            trabajoOriginal= (ExperienciaLaboral) fachadaTrabajo.obtenerExperienciaLaboral(this.usuario.getId(), this.fechaComienzoOriginal);

            this.usuario.getExperienciaLaboralCollection().remove(trabajoOriginal);
            this.fachadaTrabajo.remove(trabajoOriginal);
            
            
            trabajo= new ExperienciaLaboral(this.usuario.getId(), fechaComienzo);
            trabajo.setFechaFinalizacion(fechaFinalizacion);
            trabajo.setPuesto(puesto);
            trabajo.setWebEmpresa(web);
            trabajo.setEmpresa(empresa);
            
            this.fachadaTrabajo.create(trabajo);
            this.usuario.getExperienciaLaboralCollection().add(trabajo);

            this.fachadaUsuario.edit(this.usuario);
            
            return "perfil";
        }

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

    public String editarTrabajo(ExperienciaLaboral trabajo) {

        this.usuario = this.sesionBean.obtenerUsuario();
        
        
        this.fechaComienzoOriginal= trabajo.getExperienciaLaboralPK().getFechaComienzo();
        this.fechaComienzo= trabajo.getExperienciaLaboralPK().getFechaComienzo();
        this.fechaFinalizacion= trabajo.getFechaFinalizacion();
        this.empresa= trabajo.getEmpresa();
        this.puesto= trabajo.getPuesto();
        this.web= trabajo.getWebEmpresa();
        
        return "editarTrabajo";
    }

    public Date getFechaComienzoOriginal() {
        return fechaComienzoOriginal;
    }

    public void setFechaComienzoOriginal(Date fechaComienzoOriginal) {
        this.fechaComienzoOriginal = fechaComienzoOriginal;
    }

}
