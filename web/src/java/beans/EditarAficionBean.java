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
    private Aficiones aficion;
    private String hidden;
    private Usuario u;
    
    public EditarAficionBean() {
        
    }
    
    @PostConstruct
    public void init(){
        this.u=sb.obtenerUsuario();
        this.aficion = new Aficiones();
    }
            
    
    public String editar(String aficion){
        this.aficion = af.obtenerAficionConIdyNombre(sb.getUsuarioID(), aficion);
        this.nombre = aficion;
        this.hidden = aficion;
        return "editarAficion";
    }

    public Aficiones getAficion() {
        return aficion;
    }

    public void setAficion(Aficiones aficion) { 
        this.aficion = aficion;
    }
    
    public String doGuardar(){
  
        this.aficion = af.obtenerAficionConIdyNombre(u.getId(), hidden);
        System.out.println(hidden);
        u.getAficionesCollection().remove(aficion);
        
        aficion.getAficionesPK().setNombre(hidden);
        
    
        u.getAficionesCollection().add(aficion);

        
        uf.edit(u);
        
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
