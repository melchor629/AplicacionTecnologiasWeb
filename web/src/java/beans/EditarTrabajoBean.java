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
    
    @Inject SesionBean sesionBean;
    
    //DESCOMENTAR CUANDO ESTE CREADO
    //@Inject PerfilBean perfilBean;
    
    Usuario usuario;
    ExperienciaLaboral trabajo,trabajoOriginal;
    String error=null;
    
    
    
       ExperienciaLaboral trabajoSeleccionado;

    public ExperienciaLaboral getTrabajoSeleccionado() {
        return trabajoSeleccionado;
    }

    public void setTrabajoSeleccionado(ExperienciaLaboral trabajoSeleccionado) {
        this.trabajoSeleccionado = trabajoSeleccionado;
    }
    
    public void EditarTrabajoBean(){
        
    }
    
    
    public String getError(){
        return error;
    }
    
    public void setError(String error){
        this.error=error;
    }
    
    public ExperienciaLaboral getTrabajo(){
        return trabajo;
    }
    
    public void setTrabajo(ExperienciaLaboral trabajo){
        this.trabajo=trabajo;
    }
    
    public String doGuardar(){
        
        //si hay un error
        if(error!=null){
            
            error="Ha ocurrido un Error";
            return "editarTrabajo";
        }else{
            
            error=null;
            
            //como no puedo editar el trabajo creo borro el que tengo y creo uno nuevo
            
            fachadaTrabajo.borrarExperienciaLaboral(this.usuario.getId(), this.trabajoOriginal.getExperienciaLaboralPK().getFechaComienzo());
            usuario.getExperienciaLaboralCollection().remove(this.trabajoOriginal);
            usuario.getExperienciaLaboralCollection().add(this.trabajo);
            
           fachadaUsuario.edit(this.usuario);
            return "perfil";
        }
        
        
    }
    
        
    public String editarTrabajo(ExperienciaLaboral trabajo){
        
        this.trabajoSeleccionado= trabajo;
         this.usuario = this.sesionBean.obtenerUsuario();
        
        this.trabajo= trabajoSeleccionado;
        this.trabajoOriginal=trabajoSeleccionado;
        
        return "editarTrabajo";
    }
    
            
}
