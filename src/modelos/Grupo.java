/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author gustavoduarte
 */
@Entity
@Table(name = "grupo")
@NamedQueries({@NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g")})
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @SequenceGenerator(name="id", sequenceName="grupo_id_seq")
    @GeneratedValue(strategy=GenerationType.AUTO ,generator="id")
    private Integer id;
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupo")
    private List<AUsuarioGrupo> aUsuarioGrupoCollection;

    public Grupo() {
    }

    public Grupo(Integer id) {
        this.id = id;
    }

    public Grupo(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Grupo(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<AUsuarioGrupo> getAUsuarioGrupoCollection() {
        return aUsuarioGrupoCollection;
    }

    public void setAUsuarioGrupoCollection(List<AUsuarioGrupo> aUsuarioGrupoCollection) {
        this.aUsuarioGrupoCollection = aUsuarioGrupoCollection;
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
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testejpa3.Grupo[id=" + id + "]";
    }

}
