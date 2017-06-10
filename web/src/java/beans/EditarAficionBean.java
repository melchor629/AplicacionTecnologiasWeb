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
    
    public EditarAficionBean() {
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
        int id = sb.obtenerUsuario().getId();
        
        this.aficion = af.obtenerAficionConIdyNombre(id, hidden);
        sb.obtenerUsuario().getAficionesCollection().remove(aficion);
        
        Aficiones nueva = new Aficiones(id , nombre);
        sb.obtenerUsuario().getAficionesCollection().add(nueva);
        
        af.borrarAficion(id, hidden);
        
        af.create(nueva);
        
        uf.edit(sb.obtenerUsuario());
        System.out.println(sb.obtenerUsuario().getAficionesCollection().size());
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
