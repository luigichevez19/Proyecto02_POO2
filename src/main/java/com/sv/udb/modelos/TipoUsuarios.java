/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

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
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nomb_tipo")
    private String nombTipo;

    public TipoUsuarios() {
    }

    public TipoUsuarios(Integer codiTipo) {
        this.codiTipo = codiTipo;
    }

    public TipoUsuarios(Integer codiTipo, String nombTipo) {
        this.codiTipo = codiTipo;
        this.nombTipo = nombTipo;
    }

    public Integer getCodiTipo() {
        return codiTipo;
    }

    public void setCodiTipo(Integer codiTipo) {
        this.codiTipo = codiTipo;
    }

    public String getNombTipo() {
        return nombTipo;
    }

    public void setNombTipo(String nombTipo) {
        this.nombTipo = nombTipo;
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
