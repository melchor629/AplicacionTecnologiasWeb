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
}
