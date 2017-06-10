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
    private AficionesPK aficionPK;
    private Aficiones aficion;
    private String nombre;
    
    public EditarAficionBean() {
        this.aficion = null;
    }
    
    
            
    
    public String editar(AficionesPK aficionpk){
        this.aficion = null;
        this.aficionPK = aficionpk;
        this.aficion = af.find(aficionpk);
        return "editarAficion";
    }

    public Aficiones getAficion() {
        return aficion;
    }

    public void setAficion(Aficiones aficion) {
        this.aficion = aficion;
    }
    
    public String doGuardar(){
        aficionPK.setNombre(this.nombre);
        aficion.setAficionesPK(aficionPK);
        af.edit(aficion);
        return "perfil";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
