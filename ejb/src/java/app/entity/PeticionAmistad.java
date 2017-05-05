package app.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author antonio
 */
@Entity
@Table(name = "PeticionAmistad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeticionAmistad.findAll", query = "SELECT p FROM PeticionAmistad p")
    , @NamedQuery(name = "PeticionAmistad.findByIdEmisor", query = "SELECT p FROM PeticionAmistad p WHERE p.peticionAmistadPK.idEmisor = :idEmisor")
    , @NamedQuery(name = "PeticionAmistad.findByIdReceptor", query = "SELECT p FROM PeticionAmistad p WHERE p.peticionAmistadPK.idReceptor = :idReceptor")
    , @NamedQuery(name = "PeticionAmistad.findByMensaje", query = "SELECT p FROM PeticionAmistad p WHERE p.mensaje = :mensaje")})
public class PeticionAmistad implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PeticionAmistadPK peticionAmistadPK;
    @Size(max = 499)
    @Column(name = "Mensaje")
    private String mensaje;
    @JoinColumn(name = "idEmisor", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "idReceptor", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario1;

    public PeticionAmistad() {
    }

    public PeticionAmistad(PeticionAmistadPK peticionAmistadPK) {
        this.peticionAmistadPK = peticionAmistadPK;
    }

    public PeticionAmistad(int idEmisor, int idReceptor) {
        this.peticionAmistadPK = new PeticionAmistadPK(idEmisor, idReceptor);
    }

    public PeticionAmistadPK getPeticionAmistadPK() {
        return peticionAmistadPK;
    }

    public void setPeticionAmistadPK(PeticionAmistadPK peticionAmistadPK) {
        this.peticionAmistadPK = peticionAmistadPK;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (peticionAmistadPK != null ? peticionAmistadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeticionAmistad)) {
            return false;
        }
        PeticionAmistad other = (PeticionAmistad) object;
        if ((this.peticionAmistadPK == null && other.peticionAmistadPK != null) || (this.peticionAmistadPK != null && !this.peticionAmistadPK.equals(other.peticionAmistadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.entity.PeticionAmistad[ peticionAmistadPK=" + peticionAmistadPK + " ]";
    }
    
}
