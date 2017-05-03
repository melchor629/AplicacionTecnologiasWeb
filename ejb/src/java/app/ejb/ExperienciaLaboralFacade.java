package app.ejb;

import app.entity.Aficiones;
import app.entity.Estudios;
import app.entity.ExperienciaLaboral;
import app.entity.ExperienciaLaboralPK;
import app.entity.Usuario;
import java.util.Date;
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

    public List<Estudios> obtenerTrabajos(Usuario u) {

        Query q;

        q = this.em.createNamedQuery("ExperienciaLaboral.findByIdUsuario");
        q.setParameter("idUsuario", u.getId());

        return q.getResultList();
    }

    public Aficiones obtenerAficionConIdyNombre(String id, String nombre) {

        Query q;
        q = this.em.createQuery("SELECT a FROM Aficiones a WHERE a.aficionesPK.idUsuario = :idUsuario AND a.aficionesPK.nombre = :nombre");
        q.setParameter("idUsuario", id);
        q.setParameter("nombre", nombre);

        Aficiones a = (Aficiones) q.getSingleResult();

        return a;
    }

    public ExperienciaLaboral obtenerExperienciaLaboral(int idUsuario, Date fechaComienzo) {
        ExperienciaLaboralPK clave = new ExperienciaLaboralPK(idUsuario, fechaComienzo);
        ExperienciaLaboral experiencia = getEntityManager().find(ExperienciaLaboral.class, clave);
        return experiencia;
    }

    public void borrarExperienciaLaboral(int idUsuario, Date fecha) {
        getEntityManager().remove(obtenerExperienciaLaboral(idUsuario, fecha));
    }
}
