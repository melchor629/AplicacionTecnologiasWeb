/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import app.ejb.UsuarioFacade;
import app.entity.Estudios;
import app.entity.ExperienciaLaboral;
import app.entity.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Rodrii
 */
@Named
@RequestScoped
public class buscarBean {

    /**
     * Creates a new instance of buscarBean
     */
    @EJB
    private UsuarioFacade uf;
    
    private String busqueda;
    private List<Usuario> resultadoBusqueda;
    
    public buscarBean() {
    }

    public String buscar(){
        this.resultadoBusqueda = uf.buscarUsuarios(this.busqueda);
        return "busqueda";
    }
    
    public ExperienciaLaboral obtenerExperienciaLaboral(Usuario u){
        ArrayList<ExperienciaLaboral> experiencia = new ArrayList<>(u.getExperienciaLaboralCollection());
        Collections.sort(experiencia, new Comparator<ExperienciaLaboral>() {            
            @Override
            public int compare(ExperienciaLaboral o1, ExperienciaLaboral o2) {
                                return o2.getExperienciaLaboralPK().getFechaComienzo().compareTo(o1.getExperienciaLaboralPK().getFechaComienzo());
            }
        });
        return experiencia.iterator().next();
    }
    
    public Estudios obtenerEstudio(Usuario u){
        ArrayList<Estudios> estudios = new ArrayList<>(u.getEstudiosCollection());
        Collections.sort(estudios, new Comparator<Estudios>() {
            @Override
            public int compare(Estudios o1, Estudios o2) {
                return o2.getEstudiosPK().getFechaComienzo().compareTo(o1.getEstudiosPK().getFechaComienzo());
            }
        });
        return estudios.iterator().next();
    }
    
     public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public List<Usuario> getResultadoBusqueda() {
        return resultadoBusqueda;
    }

    public void setResultadoBusqueda(List<Usuario> resultadoBusqueda) {
        this.resultadoBusqueda = resultadoBusqueda;
    }
    
    
}
