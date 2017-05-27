/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import app.entity.Aficiones;
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
    
    private Aficiones aficion;
    
    public editarAficionBean() {
    }
    
    public String editar(String nombre){
        for(Aficiones af : sb.obtenerUsuario().getAficionesCollection()){
            if(af.)
        }
    }
    
}
