/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author antonio
 */
@Entity
@Table(name = "Estudios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudios.findAll", query = "SELECT e FROM Estudios e")
    , @NamedQuery(name = "Estudios.findByIdUsuario", query = "SELECT e FROM Estudios e WHERE e.estudiosPK.idUsuario = :idUsuario")
    , @NamedQuery(name = "Estudios.findByFechaComienzo", query = "SELECT e FROM Estudios e WHERE e.estudiosPK.fechaComienzo = :fechaComienzo")
    , @NamedQuery(name = "Estudios.findByFechaFinalizacion", query = "SELECT e FROM Estudios e WHERE e.fechaFinalizacion = :fechaFinalizacion")
    , @NamedQuery(name = "Estudios.findByDescripcion", query = "SELECT e FROM Estudios e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "Estudios.findByUbicacion", query = "SELECT e FROM Estudios e WHERE e.ubicacion = :ubicacion")})
public class Estudios implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EstudiosPK estudiosPK;
    @Column(name = "Fecha_Finalizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinalizacion;
    @Size(max = 255)
    @Column(name = "Descripcion")
    private String descripcion;
    @Size(max = 455)
    @Column(name = "Ubicacion")
    private String ubicacion;
    @JoinColumn(name = "IdUsuario", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Estudios() {
    }

    public Estudios(EstudiosPK estudiosPK) {
        this.estudiosPK = estudiosPK;
    }

    public Estudios(int idUsuario, Date fechaComienzo) {
        this.estudiosPK = new EstudiosPK(idUsuario, fechaComienzo);
    }

    public EstudiosPK getEstudiosPK() {
        return estudiosPK;
    }

    public void setEstudiosPK(EstudiosPK estudiosPK) {
        this.estudiosPK = estudiosPK;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estudiosPK != null ? estudiosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudios)) {
            return false;
        }
        Estudios other = (Estudios) object;
        if ((this.estudiosPK == null && other.estudiosPK != null) || (this.estudiosPK != null && !this.estudiosPK.equals(other.estudiosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.entity.Estudios[ estudiosPK=" + estudiosPK + " ]";
    }
    
}
