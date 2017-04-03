/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author antonio
 */
@Entity
@Table(name = "ExperienciaLaboral")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExperienciaLaboral.findAll", query = "SELECT e FROM ExperienciaLaboral e")
    , @NamedQuery(name = "ExperienciaLaboral.findByIdUsuario", query = "SELECT e FROM ExperienciaLaboral e WHERE e.experienciaLaboralPK.idUsuario = :idUsuario")
    , @NamedQuery(name = "ExperienciaLaboral.findByFechaComienzo", query = "SELECT e FROM ExperienciaLaboral e WHERE e.experienciaLaboralPK.fechaComienzo = :fechaComienzo")
    , @NamedQuery(name = "ExperienciaLaboral.findByFechaFinalizacion", query = "SELECT e FROM ExperienciaLaboral e WHERE e.fechaFinalizacion = :fechaFinalizacion")
    , @NamedQuery(name = "ExperienciaLaboral.findByEmpresa", query = "SELECT e FROM ExperienciaLaboral e WHERE e.empresa = :empresa")
    , @NamedQuery(name = "ExperienciaLaboral.findByPuesto", query = "SELECT e FROM ExperienciaLaboral e WHERE e.puesto = :puesto")
    , @NamedQuery(name = "ExperienciaLaboral.findByWebEmpresa", query = "SELECT e FROM ExperienciaLaboral e WHERE e.webEmpresa = :webEmpresa")})
public class ExperienciaLaboral implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ExperienciaLaboralPK experienciaLaboralPK;
    @Column(name = "Fecha_Finalizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinalizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Empresa")
    private String empresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Puesto")
    private String puesto;
    @Size(max = 45)
    @Column(name = "Web_Empresa")
    private String webEmpresa;
    @JoinColumn(name = "idUsuario", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public ExperienciaLaboral() {
    }

    public ExperienciaLaboral(ExperienciaLaboralPK experienciaLaboralPK) {
        this.experienciaLaboralPK = experienciaLaboralPK;
    }

    public ExperienciaLaboral(ExperienciaLaboralPK experienciaLaboralPK, String empresa, String puesto) {
        this.experienciaLaboralPK = experienciaLaboralPK;
        this.empresa = empresa;
        this.puesto = puesto;
    }

    public ExperienciaLaboral(int idUsuario, Date fechaComienzo) {
        this.experienciaLaboralPK = new ExperienciaLaboralPK(idUsuario, fechaComienzo);
    }

    public ExperienciaLaboralPK getExperienciaLaboralPK() {
        return experienciaLaboralPK;
    }

    public void setExperienciaLaboralPK(ExperienciaLaboralPK experienciaLaboralPK) {
        this.experienciaLaboralPK = experienciaLaboralPK;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getWebEmpresa() {
        return webEmpresa;
    }

    public void setWebEmpresa(String webEmpresa) {
        this.webEmpresa = webEmpresa;
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
        hash += (experienciaLaboralPK != null ? experienciaLaboralPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExperienciaLaboral)) {
            return false;
        }
        ExperienciaLaboral other = (ExperienciaLaboral) object;
        if ((this.experienciaLaboralPK == null && other.experienciaLaboralPK != null) || (this.experienciaLaboralPK != null && !this.experienciaLaboralPK.equals(other.experienciaLaboralPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.entity.ExperienciaLaboral[ experienciaLaboralPK=" + experienciaLaboralPK + " ]";
    }
    
}
