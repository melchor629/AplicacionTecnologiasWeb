package app.ejb;

import app.entity.Estudios;
import app.entity.ExperienciaLaboral;
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
public class ExperienciaLaboralFacade extends AbstractFacade<ExperienciaLaboral> {

    @PersistenceContext(unitName = "ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExperienciaLaboralFacade() {
        super(ExperienciaLaboral.class);
    }
    
     public List<Estudios> obtenerTrabajos(Usuario u){
        
        Query q;
        
        q = this.em.createNamedQuery("ExperienciaLaboral.findByIdUsuario");
        q.setParameter("idUsuario", u.getId());
        
        return q.getResultList();
    }
}
