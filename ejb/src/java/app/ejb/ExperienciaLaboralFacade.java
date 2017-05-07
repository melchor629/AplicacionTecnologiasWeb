package app.ejb;

import app.entity.Aficiones;
import app.entity.Estudios;
import app.entity.ExperienciaLaboral;
import app.entity.ExperienciaLaboralPK;
import app.entity.Usuario;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Antonio Ángel Cruzado Castillo
 * //Francisco Reyes Sánchez
 * 
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
     
        public ExperienciaLaboral obtenerTrabajoConIdyFecha(String id, String fecha) throws ParseException{
       
       DateFormat format= new SimpleDateFormat("dd/MM/yyyy");
       Query q;
       q = this.em.createQuery("SELECT e FROM ExperienciaLaboral e WHERE e.experienciaLaboralPK.idUsuario = :idUsuario AND e.experienciaLaboralPK.fechaComienzo = :fechaComienzo");
       q.setParameter("idUsuario", Integer.parseInt(id));
       q.setParameter("fechaComienzo", format.parse(fecha));
       
       ExperienciaLaboral e= (ExperienciaLaboral) q.getSingleResult();
       
       
       return e;
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
