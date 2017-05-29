/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import app.ejb.AficionesFacade;
import app.ejb.UsuarioFacade;
import app.entity.Aficiones;
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
public class editarAficionBean {

    /**
     * Creates a new instance of editarAficionBean
     */
    @Inject
    private SesionBean sb;
    @EJB
    private AficionesFacade af;
    private String nombre;
    private Aficiones aficion;
    
    public editarAficionBean() {
    }
    
    public String editar(String nombre){
        this.aficion = af.obtenerAficionConIdyNombre(sb.obtenerUsuario().getId(), nombre);
        this.nombre = aficion.getAficionesPK().getNombre();
        return "editarAficion";
    }

    public Aficiones getAficion() {
        return aficion;
    }

    public void setAficion(Aficiones aficion) {
        this.aficion = aficion;
    }
    
    public String doGuardar(){
        aficion.getAficionesPK().setNombre(nombre);
        af.edit(aficion);
        return "perfil.jsf";
    }
    
}
