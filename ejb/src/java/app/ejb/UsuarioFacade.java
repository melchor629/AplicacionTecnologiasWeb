package app.ejb;

import app.entity.Usuario;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

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
        // NO BORRAR NINGUN COMENTARIO, esto sirve a modo de repaso. Se muestran 3
        // formas alternativas de hacer lo mismo
        
        // Otra forma usando el namedQuery de la entidad usuario
        Query consulta = getEntityManager().createNamedQuery("Usuario.findById", Usuario.class);
        consulta.setParameter("id", idUsuario);
        Usuario usuario = (Usuario) consulta.getSingleResult();
        
        // O usando find del gestor de entidades
        
        /*EntityManager gestorEntidades = getEntityManager();

        Usuario usuario = gestorEntidades.find(Usuario.class, idUsuario);
        /*
        
        // O creando un query
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

    @SuppressWarnings("unchecked")
    public List <Usuario> buscarUsuarios(String datos) {
        //Explicación del razonamiento de Melchor para esto:
        //Primero, lo unico que he hecho es reordenar el u.id = e.usuario.id y e.usuario.id = s.usuario.id
        //para que hagan de control de ejecución de la parte de buscar en cada una de las otras tablas.
        //Es decir, si os fijais hay la linea 5 del SQL (sin contar los comentarios) y la 6 se juntan con la
        //primera parte del WHERE (que usan todos u.loQueSea) con un OR, haciendo que salgan mas resultados
        //por ser menos restrictivo. Luego para que se ejecute el otro lado del AND (y realizar la busqueda
        //en la tabla) se comprueba que el ID del usuario sea igual al de la tabla, es como hacer:
        //  if(u.id está en ExperienciaLaboral) añadirTodosLosResultadosQueConcuerden();
        //  if(u.id está en Estudios) añadirTodosLosResultadosQueConcuerden();
        //Antes estaba de la forma
        //  if(u.id está en ExperienciaLaboral && e.usuario.id está en Estudios) hacerElRestoDelSQL();
        //De la forma que tenia Edu, forzaba a que hubiera algo en Estudios y en ExperienciaLaboral y eso
        //no es correcto, porque lo mismo es un analfabeto, pero es un electricista de puta madre; pero
        //nadie le encontrará por la restricción de que tenia que tener algo en ambos (el AND del principio).

        //Probad este SQL en la BD, vereis que solo sale Antonio, por lo que la búsqueda solo se realizaba con sus cosas
        //SELECT u.* FROM Usuario u, ExperienciaLaboral e, Estudios s WHERE (u.id = e.idUsuario AND u.id = s.idUsuario)
        //Corresponde a la primera linea del PJSQL de Edu, pero en MySQL

        //Espero que quede claro what happens here.
        Query q = this.em.createQuery("SELECT DISTINCT u FROM Usuario u, ExperienciaLaboral e, Estudios s WHERE"
                + "(u.nombre LIKE :datos OR u.apellidos LIKE :datos "
                + "OR u.twitter LIKE :datos OR u.instagram LIKE :datos OR u.web LIKE :datos OR u.correo LIKE :datos "
                + "OR u.nombreUsuario LIKE :datos)" +
                  //Esto busca en la tabla de ExperienciaLaboral solo si hay usuarios allí (creo)
                  "OR (u.id = e.usuario.id AND (e.empresa LIKE :datos OR e.puesto LIKE :datos))" +
                  //Esto busca en la tabla de Estudios solo si hay usuarios allí (igual que arriba, creo)
                  "OR (u.id = s.usuario.id AND (s.ubicacion LIKE :datos OR s.descripcion LIKE :datos))");

        q.setParameter("datos", datos + "%");

        return (List) q.getResultList();
    }
}
