/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "entregas", catalog = "vaso_leche", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entregas.findAll", query = "SELECT e FROM Entregas e")
    , @NamedQuery(name = "Entregas.findByCodiEntr", query = "SELECT e FROM Entregas e WHERE e.codiEntr = :codiEntr")
    , @NamedQuery(name = "Entregas.findByFecha", query = "SELECT e FROM Entregas e WHERE e.fecha = :fecha")})
public class Entregas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codi_entr")
    private Integer codiEntr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "codi_lote", referencedColumnName = "codi_lote")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Lotes codiLote;
    @JoinColumn(name = "codi_escu", referencedColumnName = "codi_escu")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Escuelas codiEscu;
    @JoinColumn(name = "codi_esta", referencedColumnName = "codi_esta")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadosEntrega codiEsta;
    @JoinColumn(name = "codi_usua", referencedColumnName = "codi_usua")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuarios codiUsua;

    public Entregas() {
    }

    public Entregas(Integer codiEntr) {
        this.codiEntr = codiEntr;
    }

    public Entregas(Integer codiEntr, Date fecha) {
        this.codiEntr = codiEntr;
        this.fecha = fecha;
    }

    public Integer getCodiEntr() {
        return codiEntr;
    }

    public void setCodiEntr(Integer codiEntr) {
        this.codiEntr = codiEntr;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Lotes getCodiLote() {
        return codiLote;
    }

    public void setCodiLote(Lotes codiLote) {
        this.codiLote = codiLote;
    }

    public Escuelas getCodiEscu() {
        return codiEscu;
    }

    public void setCodiEscu(Escuelas codiEscu) {
        this.codiEscu = codiEscu;
    }

    public EstadosEntrega getCodiEsta() {
        return codiEsta;
    }

    public void setCodiEsta(EstadosEntrega codiEsta) {
        this.codiEsta = codiEsta;
    }

    public Usuarios getCodiUsua() {
        return codiUsua;
    }

    public void setCodiUsua(Usuarios codiUsua) {
        this.codiUsua = codiUsua;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiEntr != null ? codiEntr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entregas)) {
            return false;
        }
        Entregas other = (Entregas) object;
        if ((this.codiEntr == null && other.codiEntr != null) || (this.codiEntr != null && !this.codiEntr.equals(other.codiEntr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelos.Entregas[ codiEntr=" + codiEntr + " ]";
    }
    
}
