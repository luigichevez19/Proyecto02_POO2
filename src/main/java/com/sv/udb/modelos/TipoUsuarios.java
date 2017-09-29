/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "tipo_usuarios", catalog = "vaso_leche", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoUsuarios.findAll", query = "SELECT t FROM TipoUsuarios t")
    , @NamedQuery(name = "TipoUsuarios.findByCodiTipo", query = "SELECT t FROM TipoUsuarios t WHERE t.codiTipo = :codiTipo")
    , @NamedQuery(name = "TipoUsuarios.findByNombTipo", query = "SELECT t FROM TipoUsuarios t WHERE t.nombTipo = :nombTipo")})
public class TipoUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_tipo")
    private Integer codiTipo;
    @Basic(optional = false)
    @Column(name = "nomb_tipo")
    private int nombTipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiTipo", fetch = FetchType.EAGER)
    private Collection<Usuarios> usuariosCollection;

    public TipoUsuarios() {
    }

    public TipoUsuarios(Integer codiTipo) {
        this.codiTipo = codiTipo;
    }

    public TipoUsuarios(Integer codiTipo, int nombTipo) {
        this.codiTipo = codiTipo;
        this.nombTipo = nombTipo;
    }

    public Integer getCodiTipo() {
        return codiTipo;
    }

    public void setCodiTipo(Integer codiTipo) {
        this.codiTipo = codiTipo;
    }

    public int getNombTipo() {
        return nombTipo;
    }

    public void setNombTipo(int nombTipo) {
        this.nombTipo = nombTipo;
    }

    @XmlTransient
    public Collection<Usuarios> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuarios> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiTipo != null ? codiTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoUsuarios)) {
            return false;
        }
        TipoUsuarios other = (TipoUsuarios) object;
        if ((this.codiTipo == null && other.codiTipo != null) || (this.codiTipo != null && !this.codiTipo.equals(other.codiTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelos.TipoUsuarios[ codiTipo=" + codiTipo + " ]";
    }
    
}
