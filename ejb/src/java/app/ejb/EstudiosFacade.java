package app.ejb;

import app.entity.Estudios;
import app.entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author antonio
 */
@Stateless
public class EstudiosFacade extends AbstractFacade<Estudios> {

    @PersistenceContext(unitName = "ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudiosFacade() {
        super(Estudios.class);
    }
    
    public List<Estudios> obtenerEstudios(Usuario u){
        
        Query q;
        
        q = this.em.createNamedQuery("Estudios.findByIdUsuario");
        q.setParameter("idUsuario", u.getId());
        
        return q.getResultList();
    }
    
      public Aficiones obtenerAficionConIdyNombre(String id, String nombre){
       
       Query q;
       q = this.em.createQuery("SELECT a FROM Aficiones a WHERE a.aficionesPK.idUsuario = :idUsuario AND a.aficionesPK.nombre = :nombre");
       q.setParameter("idUsuario", id);
       q.setParameter("nombre", nombre);
       
       Aficiones a= (Aficiones) q.getSingleResult();
       
       
       return a;
   }
}
