/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelos;

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

/**
 *
 * @author gustavoduarte
 */
@Entity
@Table(name = "a_usuario_grupo")
@NamedQueries({@NamedQuery(name = "AUsuarioGrupo.findAll", query = "SELECT a FROM AUsuarioGrupo a")})
public class AUsuarioGrupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AUsuarioGrupoPK aUsuarioGrupoPK;
    @Basic(optional = false)
    @Column(name = "data_inicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Column(name = "data_fim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    @JoinColumn(name = "grupo_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Grupo grupo;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public AUsuarioGrupo() {
    }

    public AUsuarioGrupo(AUsuarioGrupoPK aUsuarioGrupoPK) {
        this.aUsuarioGrupoPK = aUsuarioGrupoPK;
    }

    public AUsuarioGrupo(AUsuarioGrupoPK aUsuarioGrupoPK, Date dataInicio) {
        this.aUsuarioGrupoPK = aUsuarioGrupoPK;
        this.dataInicio = dataInicio;
    }

    public AUsuarioGrupo(int usuarioId, int grupoId) {
        this.aUsuarioGrupoPK = new AUsuarioGrupoPK(usuarioId, grupoId);
        dataInicio = new Date();
    }

    public AUsuarioGrupoPK getAUsuarioGrupoPK() {
        return aUsuarioGrupoPK;
    }

    public void setAUsuarioGrupoPK(AUsuarioGrupoPK aUsuarioGrupoPK) {
        this.aUsuarioGrupoPK = aUsuarioGrupoPK;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
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
        hash += (aUsuarioGrupoPK != null ? aUsuarioGrupoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AUsuarioGrupo)) {
            return false;
        }
        AUsuarioGrupo other = (AUsuarioGrupo) object;
        if ((this.aUsuarioGrupoPK == null && other.aUsuarioGrupoPK != null) || (this.aUsuarioGrupoPK != null && !this.aUsuarioGrupoPK.equals(other.aUsuarioGrupoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testejpa3.AUsuarioGrupo[aUsuarioGrupoPK=" + aUsuarioGrupoPK + "]";
    }

}
