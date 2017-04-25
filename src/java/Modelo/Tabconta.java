/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "tabconta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tabconta.findAll", query = "SELECT t FROM Tabconta t")
    ,
    @NamedQuery(name = "Tabconta.findByIdConta", query = "SELECT t FROM Tabconta t WHERE t.idConta = :idConta")
    ,
    @NamedQuery(name = "Tabconta.findByCodConta", query = "SELECT t FROM Tabconta t WHERE t.codConta = :codConta")
    ,
    @NamedQuery(name = "Tabconta.findByDescrConta", query = "SELECT t FROM Tabconta t WHERE t.descrConta = :descrConta")
    ,
    @NamedQuery(name = "Tabconta.findByClasseConta", query = "SELECT t FROM Tabconta t WHERE t.classeConta = :classeConta")
    ,
    @NamedQuery(name = "Tabconta.findByTipoConta", query = "SELECT t FROM Tabconta t WHERE t.tipoConta = :tipoConta")})
public class Tabconta implements Serializable, Comparable<Tabconta> {

    @OneToMany(mappedBy = "idContaD")
    private List<Tablanc> tablancList;
    @OneToMany(mappedBy = "idContaC")
    private List<Tablanc> tablancList1;

    public static final String LISTAR_T = "Tabconta.findAll";
    //public static final String LISTAR_POR_CLASSE = "Tabconta.findByClasseConta";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConta")
    private Integer idConta;
    @Column(name = "codConta")
    private String codConta;
    @Column(name = "descrConta")
    private String descrConta;
    @Column(name = "classeConta")
    private Boolean classeConta;
    @Column(name = "tipoConta")
    private Boolean tipoConta;
    @Column(name = "ultSaldo")
    private Double ultSaldo;

    public Tabconta() {
    }

    public Tabconta(Integer idConta) {
        this.idConta = idConta;
    }

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }

    public String getCodConta() {
        return codConta;
    }

    public void setCodConta(String codConta) {
        this.codConta = codConta;
    }

    

    public String getDescrConta() {
        return descrConta;
    }

    public void setDescrConta(String descrConta) {
        this.descrConta = descrConta;
    }

    public Boolean getClasseConta() {
        return classeConta;
    }

    public void setClasseConta(Boolean classeConta) {
        this.classeConta = classeConta;
    }

    public Boolean getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(Boolean tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Double getUltSaldo() {
        return ultSaldo;
    }

    public void setUltSaldo(Double ultSaldo) {
        this.ultSaldo = ultSaldo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConta != null ? idConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tabconta)) {
            return false;
        }
        Tabconta other = (Tabconta) object;
        if ((this.idConta == null && other.idConta != null) || (this.idConta != null && !this.idConta.equals(other.idConta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codConta + " - " + descrConta;
    }

    @XmlTransient
    public List<Tablanc> getTablancList() {
        return tablancList;
    }

    public void setTablancList(List<Tablanc> tablancList) {
        this.tablancList = tablancList;
    }

    @XmlTransient
    public List<Tablanc> getTablancList1() {
        return tablancList1;
    }

    public void setTablancList1(List<Tablanc> tablancList1) {
        this.tablancList1 = tablancList1;
    }

    @Override
    public int compareTo(Tabconta o) {
        return this.codConta.compareTo(o.codConta);
    }
}
