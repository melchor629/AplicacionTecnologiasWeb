package app.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author antonio
 */
@Entity
@Table(name = "Mensaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensaje.findAll", query = "SELECT m FROM Mensaje m")
    , @NamedQuery(name = "Mensaje.findById", query = "SELECT m FROM Mensaje m WHERE m.id = :id")
    , @NamedQuery(name = "Mensaje.findByTexto", query = "SELECT m FROM Mensaje m WHERE m.texto = :texto")
    , @NamedQuery(name = "Mensaje.findByLeido", query = "SELECT m FROM Mensaje m WHERE m.leido = :leido")
    , @NamedQuery(name = "Mensaje.findByTitulo", query = "SELECT m FROM Mensaje m WHERE m.titulo = :titulo")})
public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 999)
    @Column(name = "Texto")
    private String texto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Leido")
    private boolean leido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Titulo")
    private String titulo;
    @JoinColumn(name = "idEmisor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario idEmisor;
    @JoinColumn(name = "idReceptor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario idReceptor;

    public Mensaje() {
    }

    public Mensaje(Integer id) {
        this.id = id;
    }

    public Mensaje(Integer id, String texto, boolean leido, String titulo) {
        this.id = id;
        this.texto = texto;
        this.leido = leido;
        this.titulo = titulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean getLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Usuario getIdEmisor() {
        return idEmisor;
    }

    public void setIdEmisor(Usuario idEmisor) {
        this.idEmisor = idEmisor;
    }

    public Usuario getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(Usuario idReceptor) {
        this.idReceptor = idReceptor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensaje)) {
            return false;
        }
        Mensaje other = (Mensaje) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.entity.Mensaje[ id=" + id + " ]";
    }
    
}
