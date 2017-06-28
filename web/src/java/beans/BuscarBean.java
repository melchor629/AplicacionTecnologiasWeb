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
import java.util.Random;
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
public class BuscarBean {

    /**
     * Creates a new instance of buscarBean
     */
    
    @Inject
    GravatarBean gb;
    
    @EJB
    private UsuarioFacade uf;
    
    private String busqueda;
    private List<Usuario> resultadoBusqueda;
    
    
    public BuscarBean() {
    }
    
    /*@PostConstruct
    public void init (){
             
    }*/
    
    public String fotoPerfil (int id) {
        Usuario u = uf.obtenerUsuarioPorId(id);
        
        return u.getFoto() == null ? imagenPorDefecto(u) : u.getFoto();
    }
    
     private String imagenPorDefecto(Usuario usuario) {
        /*if (usuario == null) {
            usuario = this.uf
        }*/

        return gb.imagenPorDefecto(usuario);
    }
    
    
    public String buscar(){
         if (busqueda.equals("")) {
            Random r = new Random();
            //= java.util.Random.nextInt();
            busqueda = r.nextInt()+ "R4ND0MV4LU3" + r.nextInt();
        }          
        
        this.resultadoBusqueda = uf.buscarUsuarios(this.busqueda);
        
        if (this.resultadoBusqueda.isEmpty()){
            busqueda = "";
        }
        
        
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
        return experiencia.get(0);
    }
    
    public Estudios obtenerEstudio(Usuario u){
        ArrayList<Estudios> estudios = new ArrayList<>(u.getEstudiosCollection());
        Collections.sort(estudios, new Comparator<Estudios>() {
            @Override
            public int compare(Estudios o1, Estudios o2) {
                return o2.getEstudiosPK().getFechaComienzo().compareTo(o1.getEstudiosPK().getFechaComienzo());
            }
        });
        return estudios.get(0);
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
