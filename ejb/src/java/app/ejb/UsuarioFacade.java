package app.ejb;

import app.entity.PeticionAmistad;
import app.entity.Usuario;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Antonio, Edu
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario obtenerUsuario(String usuario, String pass) {
        Query q;
        q = this.em.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :usuario AND u.contraseña = :password ");
        String hashPasado = app.cosas.Hash.hash(usuario + ":" + pass);
        q.setParameter("usuario", usuario);
        q.setParameter("password", hashPasado);
        List<Usuario> lista = (List) q.getResultList();
        //System.out.println(pass + " " + hashPasado);

        if (lista.size() > 0) {
            return lista.get(0);
        } else {
            return null;
        }

    }

    public Usuario obtenerUsuarioPorId(int idUsuario) {
        EntityManager gestorEntidades = getEntityManager();

        Usuario usuario = gestorEntidades.find(Usuario.class, idUsuario);

        /* Query q;
        q = gestorEntidades.createQuery("SELECT u FROM Usuario u WHERE u.id = :idUsuario");
        q.setParameter("idUsuario", idUsuario);
        Usuario usuario = (Usuario) q.getSingleResult(); */
        return usuario;
    }

    public boolean sonAmigos(int id1, int id2) {
        /* Query consulta = getEntityManager().createQuery("SELECT amistad FROM Contactos amistad WHERE (amistad.idUsuario = :id1 AND amistad.Amigo = :id2) OR (amistad.idUsuario = :id2 AND amistad.Amigo = :id1)");
        consulta.setParameter("id1", id1);
        consulta.setParameter("id2", id2);
        int resultados = consulta.getResultList().size(); */
        Usuario usuario1 = getEntityManager().find(Usuario.class, id1);
        Usuario usuario2 = getEntityManager().find(Usuario.class, id2);

        Collection<Usuario> coleccion1 = usuario1.getUsuarioCollection();
        Collection<Usuario> coleccion2 = usuario1.getUsuarioCollection1();

        return coleccion1.contains(usuario2) || coleccion2.contains(usuario2);
    }

    // Le permite al usuario con id idDesde aceptar una peticion de amistad
    // al usuario idHacia
    public void aceptarPeticionAmistad(int idDesde, int idHacia) {

        // El usuario que menda la peticion
        Usuario usuario = getEntityManager().find(Usuario.class, idDesde);
        Usuario futuraAmistad = getEntityManager().find(Usuario.class, idHacia);
        // Se añade el usuario a su lista de amigos
        usuario.getUsuarioCollection().add(futuraAmistad);
        // Se actualiza la entidad
        this.edit(usuario);
    }
    
    
    // Permite borrar una amistad entre dos usuarios
    public void borrarAmistad(int idDesde, int idHacia) {

        // El usuario que menda la peticion
        Usuario usuario1 = getEntityManager().find(Usuario.class, idDesde);
        Usuario usuario2 = getEntityManager().find(Usuario.class, idHacia);
        
        Collection<Usuario> coleccion1 = usuario1.getUsuarioCollection();
        Collection<Usuario> coleccion2 = usuario1.getUsuarioCollection1();
        
        Collection<Usuario> coleccion3 = usuario2.getUsuarioCollection();
        Collection<Usuario> coleccion4 = usuario2.getUsuarioCollection1();
        
        if(coleccion1.contains(usuario2)){
            coleccion1.remove(usuario2);
        }
        else{
            coleccion2.remove(usuario2);
        }
        
        if(coleccion3.contains(usuario1)){
            coleccion3.remove(usuario1);
        }
        else{
            coleccion4.remove(usuario1);
        }
        
        // Actualizar entidad
        this.edit(usuario1);
        this.edit(usuario2);
    }
    
     public List <Usuario> buscarUsuarios(String datos) {
        //EntityManager gestorEntidades = getEntityManager();
        Query q;
        Usuario u;
        
        Query qaux;
        
        
    
        //Esta Query funciona si habéis ejecutado el SQL de Update Exp.sql 
          q = this.em.createQuery("SELECT DISTINCT u FROM Usuario u, ExperienciaLaboral e, Estudios s WHERE (u.id = e.usuario.id) AND (e.usuario.id = s.usuario.id) "
                + "AND (u.nombre LIKE :datos OR u.apellidos LIKE :datos "
                + "OR u.twitter LIKE :datos OR u.instagram LIKE :datos OR u.web LIKE :datos OR u.correo LIKE :datos "
                + "OR u.nombreUsuario LIKE :datos OR e.empresa LIKE :datos OR e.puesto LIKE :datos OR s.ubicacion LIKE :datos "
                + "OR s.descripcion LIKE :datos)");
        
        
        
        q.setParameter("datos", datos + "%");
                
        //q.setParameter("buscar", datos);
        
        List<Usuario> lista = (List) q.getResultList();
     
        return lista;
     }
}
