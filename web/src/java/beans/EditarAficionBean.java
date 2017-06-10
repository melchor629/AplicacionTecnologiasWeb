/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import app.ejb.AficionesFacade;
import app.ejb.UsuarioFacade;
import app.entity.Aficiones;
import app.entity.AficionesPK;
import app.entity.ExperienciaLaboral;
import app.entity.Usuario;
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
@Named
@RequestScoped
public class EditarAficionBean {

    /**
     * Creates a new instance of editarAficionBean
     */
    @Inject
    private SesionBean sb;
    @EJB
    private AficionesFacade af;
    @EJB
    private UsuarioFacade uf;
    
    private String nombre;
    private Aficiones aficionSeleccionada;
    Aficiones aficion,aficionOriginal;
    private String hidden;
    private Usuario usuario;
    String error = null;
    
    public EditarAficionBean() {
        
    }
    
    @PostConstruct
    public void init(){
        this.usuario = sb.obtenerUsuario();
        this.aficion= aficionSeleccionada;
        this.aficionOriginal=aficionSeleccionada;
    }
            
    
    public String editar(Aficiones aficion){
        this.aficionSeleccionada = aficion;
        return "editarAficion";
    }

    public Aficiones getAficion() {
        return aficion;
    }

    public void setAficion(Aficiones aficion) { 
        this.aficion = aficion;
    }
    
    public String doGuardar(){
        
        //si hay un error
        if(error!=null){
            
            error="Ha ocurrido un Error";
            return "editarTrabajo";
        }else{
            
            error=null;
            
            //como no puedo editar el trabajo creo borro el que tengo y creo uno nuevo
            
            af.borrarAficion(this.usuario.getId(), this.aficionOriginal.getAficionesPK().getNombre()); 
            usuario.getAficionesCollection().remove(this.aficion);
            usuario.getAficionesCollection().add(aficion);
            
            uf.edit(usuario);
            return "perfil";
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }
    
    
}
