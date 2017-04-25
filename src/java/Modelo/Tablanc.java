/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "tablanc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tablanc.findAll", query = "SELECT t FROM Tablanc t"),
    @NamedQuery(name = "Tablanc.findByIdLanc", query = "SELECT t FROM Tablanc t WHERE t.idLanc = :idLanc"),
    @NamedQuery(name = "Tablanc.findByDataLanc", query = "SELECT t FROM Tablanc t WHERE t.dataLanc = :dataLanc"),
    @NamedQuery(name = "Tablanc.findByValorLanc", query = "SELECT t FROM Tablanc t WHERE t.valorLanc = :valorLanc"),
    @NamedQuery(name = "Tablanc.findByHistLanc", query = "SELECT t FROM Tablanc t WHERE t.histLanc = :histLanc")})
public class Tablanc implements Serializable,Comparable<Tablanc> {
    
    public static final String LISTAR_L = "Tablanc.findAll";
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLanc")
    private Integer idLanc;
    @Basic(optional = false)
    @Column(name = "dataLanc")
    @Temporal(TemporalType.DATE)
    private Date dataLanc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorLanc")
    private Double valorLanc;
    @Column(name = "histLanc")
    private String histLanc;
    @JoinColumn(name = "idContaD", referencedColumnName = "idConta")
    @ManyToOne
    private Tabconta idContaD;
    @JoinColumn(name = "idContaC", referencedColumnName = "idConta")
    @ManyToOne
    private Tabconta idContaC;

    public Tablanc() {
    }

    public Tablanc(Integer idLanc) {
        this.idLanc = idLanc;
    }

    public Tablanc(Integer idLanc, Date dataLanc) {
        this.idLanc = idLanc;
        this.dataLanc = dataLanc;
    }

    public Integer getIdLanc() {
        return idLanc;
    }

    public void setIdLanc(Integer idLanc) {
        this.idLanc = idLanc;
    }

    public Date getDataLanc() {
        return dataLanc;
    }

    public void setDataLanc(Date dataLanc) {
        this.dataLanc = dataLanc;
    }

    public Double getValorLanc() {
        return valorLanc;
    }

    public void setValorLanc(Double valorLanc) {
        this.valorLanc = valorLanc;
    }

    public String getHistLanc() {
        return histLanc;
    }

    public void setHistLanc(String histLanc) {
        this.histLanc = histLanc;
    }

    public Tabconta getIdContaD() {
        return idContaD;
    }

    public void setIdContaD(Tabconta idContaD) {
        this.idContaD = idContaD;
    }

    public Tabconta getIdContaC() {
        return idContaC;
    }

    public void setIdContaC(Tabconta idContaC) {
        this.idContaC = idContaC;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLanc != null ? idLanc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tablanc)) {
            return false;
        }
        Tablanc other = (Tablanc) object;
        if ((this.idLanc == null && other.idLanc != null) || (this.idLanc != null && !this.idLanc.equals(other.idLanc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idLanc:" + idLanc + "\nData: " + dataLanc + "\nValor: " + valorLanc;
    }

    @Override
    public int compareTo(Tablanc o) {
        return this.dataLanc.compareTo(o.getDataLanc());
    }
    
}
