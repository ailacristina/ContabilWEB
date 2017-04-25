/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author aila
 */
public class Razao implements Comparable<Razao>{
    private Date dataRazao;
    private String histRazao;
    private Double debRazao;
    private Double credRazao;
    private Double saldoRazao;

    public Razao(Date dataRazao, String histRazao, Double debRazao, Double credRazao, Double saldoRazao) {
        this.dataRazao = dataRazao;
        this.histRazao = histRazao;
        this.debRazao = debRazao;
        this.credRazao = credRazao;
        this.saldoRazao = saldoRazao;
    }
        
    public Date getDataRazao() {
        return dataRazao;
    }

    public void setDataRazao(Date dataRazao) {
        this.dataRazao = dataRazao;
    }

    public String getHistRazao() {
        return histRazao;
    }

    public void setHistRazao(String histRazao) {
        this.histRazao = histRazao;
    }

    public Double getDebRazao() {
        return debRazao;
    }

    public void setDebRazao(Double debRazao) {
        this.debRazao = debRazao;
    }

    public Double getCredRazao() {
        return credRazao;
    }

    public void setCredRazao(Double credRazao) {
        this.credRazao = credRazao;
    }

    public Double getSaldoRazao() {
        return saldoRazao;
    }

    public void setSaldoRazao(Double saldoRazao) {
        this.saldoRazao = saldoRazao;
    }

    @Override
    public String toString() {
        return "Razao{" + "dataRazao=" + dataRazao + ", histRazao=" + histRazao + ", debRazao=" + debRazao + ", credRazao=" + credRazao + ", saldoRazao=" + saldoRazao + '}';
    }
      

    @Override
    public int compareTo(Razao o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
