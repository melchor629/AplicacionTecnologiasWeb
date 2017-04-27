package app.ejb;

import app.entity.Aficiones;
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
}
