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
    private String hidden;
    private Usuario u;
    private String error;
    
    public EditarAficionBean() {
        
    }
    
    @PostConstruct
    public void init(){
        this.u = this.sb.obtenerUsuario();
        error = null;
    }
            
    
    public String editar(Aficiones aficion){
        this.nombre = aficion.getAficionesPK().getNombre();
        this.hidden = aficion.getAficionesPK().getNombre();
        
        return "editarAficion";
    }

 
    
    public String doGuardar(){
        
        if (error != null) {

            error = "Ha ocurrido un Error" + error;
            return "editarAficion";
        } else {
            error = null;
            
            Aficiones aficionNueva;
            Aficiones aficionOriginal;
            
            aficionOriginal = af.obtenerAficionConIdyNombre(this.u.getId(), this.hidden);
            this.u.getAficionesCollection().remove(aficionOriginal);
            
            this.af.remove(aficionOriginal);
            this.uf.edit(u);
            
            aficionNueva = new Aficiones(this.u.getId(), nombre);
            aficionNueva.setUsuario(u);
            
            
            this.af.create(aficionNueva);
            this.u.getAficionesCollection().add(aficionNueva);
            this.uf.edit(u);
        }
        
        return "perfil";
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
