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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "usuarios", catalog = "vaso_leche", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")
    , @NamedQuery(name = "Usuarios.findByCodiUsua", query = "SELECT u FROM Usuarios u WHERE u.codiUsua = :codiUsua")
    , @NamedQuery(name = "Usuarios.findByNombUsua", query = "SELECT u FROM Usuarios u WHERE u.nombUsua = :nombUsua")
    , @NamedQuery(name = "Usuarios.findByContra", query = "SELECT u FROM Usuarios u WHERE u.contra = :contra")
    , @NamedQuery(name = "Usuarios.findByEsta", query = "SELECT u FROM Usuarios u WHERE u.esta = :esta")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_usua")
    private Integer codiUsua;
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "nomb_usua")
    private String nombUsua;
    @Basic(optional = false)
    @Size(min = 1, max = 300)
    @Column(name = "contra")
    private String contra;
    @Basic(optional = false)
    @Column(name = "esta")
    private boolean esta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiUsua", fetch = FetchType.EAGER)
    private Collection<Entregas> entregasCollection;
    @JoinColumn(name = "codi_tipo", referencedColumnName = "codi_tipo")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TipoUsuarios codiTipo;

    public Usuarios() {
    }

    public Usuarios(Integer codiUsua) {
        this.codiUsua = codiUsua;
    }

    public Usuarios(Integer codiUsua, String nombUsua, String contra, boolean esta) {
        this.codiUsua = codiUsua;
        this.nombUsua = nombUsua;
        this.contra = contra;
        this.esta = esta;
    }

    public Integer getCodiUsua() {
        return codiUsua;
    }

    public void setCodiUsua(Integer codiUsua) {
        this.codiUsua = codiUsua;
    }

    public String getNombUsua() {
        return nombUsua;
    }

    public void setNombUsua(String nombUsua) {
        this.nombUsua = nombUsua;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public boolean getEsta() {
        return esta;
    }

    public void setEsta(boolean esta) {
        this.esta = esta;
    }

    @XmlTransient
    public Collection<Entregas> getEntregasCollection() {
        return entregasCollection;
    }

    public void setEntregasCollection(Collection<Entregas> entregasCollection) {
        this.entregasCollection = entregasCollection;
    }

    public TipoUsuarios getCodiTipo() {
        return codiTipo;
    }

    public void setCodiTipo(TipoUsuarios codiTipo) {
        this.codiTipo = codiTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiUsua != null ? codiUsua.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.codiUsua == null && other.codiUsua != null) || (this.codiUsua != null && !this.codiUsua.equals(other.codiUsua))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelos.Usuarios[ codiUsua=" + codiUsua + " ]";
    }
    
}
