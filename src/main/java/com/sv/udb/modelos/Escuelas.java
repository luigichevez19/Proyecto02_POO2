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
@Table(name = "escuelas", catalog = "vaso_leche", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Escuelas.findAll", query = "SELECT e FROM Escuelas e")
    , @NamedQuery(name = "Escuelas.findByCodiEscu", query = "SELECT e FROM Escuelas e WHERE e.codiEscu = :codiEscu")
    , @NamedQuery(name = "Escuelas.findByNombEscu", query = "SELECT e FROM Escuelas e WHERE e.nombEscu = :nombEscu")
    , @NamedQuery(name = "Escuelas.findByNombEnca", query = "SELECT e FROM Escuelas e WHERE e.nombEnca = :nombEnca")
    , @NamedQuery(name = "Escuelas.findByCantAlum", query = "SELECT e FROM Escuelas e WHERE e.cantAlum = :cantAlum")
    , @NamedQuery(name = "Escuelas.findByEsta", query = "SELECT e FROM Escuelas e WHERE e.esta = :esta")})
public class Escuelas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_escu")
    private Integer codiEscu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nomb_escu")
    private String nombEscu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nomb_enca")
    private String nombEnca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cant_alum")
    private int cantAlum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta")
    private boolean esta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiEscu", fetch = FetchType.EAGER)
    private Collection<Entregas> entregasCollection;
    @JoinColumn(name = "codi_depa", referencedColumnName = "codi_depa")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Departamentos codiDepa;

    public Escuelas() {
    }

    public Escuelas(Integer codiEscu) {
        this.codiEscu = codiEscu;
    }

    public Escuelas(Integer codiEscu, String nombEscu, String nombEnca, int cantAlum, boolean esta) {
        this.codiEscu = codiEscu;
        this.nombEscu = nombEscu;
        this.nombEnca = nombEnca;
        this.cantAlum = cantAlum;
        this.esta = esta;
    }

    public Integer getCodiEscu() {
        return codiEscu;
    }

    public void setCodiEscu(Integer codiEscu) {
        this.codiEscu = codiEscu;
    }

    public String getNombEscu() {
        return nombEscu;
    }

    public void setNombEscu(String nombEscu) {
        this.nombEscu = nombEscu;
    }

    public String getNombEnca() {
        return nombEnca;
    }

    public void setNombEnca(String nombEnca) {
        this.nombEnca = nombEnca;
    }

    public int getCantAlum() {
        return cantAlum;
    }

    public void setCantAlum(int cantAlum) {
        this.cantAlum = cantAlum;
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

    public Departamentos getCodiDepa() {
        return codiDepa;
    }

    public void setCodiDepa(Departamentos codiDepa) {
        this.codiDepa = codiDepa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiEscu != null ? codiEscu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Escuelas)) {
            return false;
        }
        Escuelas other = (Escuelas) object;
        if ((this.codiEscu == null && other.codiEscu != null) || (this.codiEscu != null && !this.codiEscu.equals(other.codiEscu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelos.Escuelas[ codiEscu=" + codiEscu + " ]";
    }
    
}
