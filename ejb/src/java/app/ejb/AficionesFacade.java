package app.ejb;

import app.entity.Aficiones;
import app.entity.AficionesPK;
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
public class AficionesFacade extends AbstractFacade<Aficiones> {

    @PersistenceContext(unitName = "ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AficionesFacade() {
        super(Aficiones.class);
    }
    
    public List<Aficiones> obtenerAficiones(Usuario u){
        
        Query q;
        q = this.em.createNamedQuery("Aficiones.findByIdUsuario");
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
   
   public void borrarAficion(int idUsuario, String textoAficion){
           AficionesPK clave = new AficionesPK(idUsuario, textoAficion);
           Aficiones aficion = getEntityManager().find(Aficiones.class, clave);
           getEntityManager().remove(aficion);
       }
}