/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.ContaDAO;
import DAO.LancDAO;
import Modelo.Razao;
import Modelo.Tabconta;
import Modelo.Tablanc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javafx.scene.control.TableColumn.CellEditEvent;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Daniel
 */

@ManagedBean
public class ControladorLanc {

    private List<Tablanc> lancamentos;
    private Tablanc lanc = new Tablanc();     
    private LancDAO lancdao = new LancDAO();
    
    private ContaDAO contadao = new ContaDAO();
    private Tabconta contaCred = new Tabconta();
    private Tabconta contaDeb = new Tabconta();
       
    
    private List<Tabconta> listaContas;
    private List<Tabconta> contas;
    

    @PostConstruct
    private void lerLancamentos() {
        setLancamentos(getLancdao().diario());
        Collections.sort(lancamentos);
        carregarContas();
    }
    
    private void carregarContas() {
        setListaContas(getContadao().listar());
        
        List<Tabconta> c = new ArrayList<>();
        
        for(Tabconta conta: listaContas){
            if (conta.getTipoConta()==false){
                c.add(conta);
            }
        }
                
        setContas(c);
       
    }
   
    public void lancar() {
        getLanc().setIdContaC(this.contaCred);
        getLanc().setIdContaD(this.contaDeb);
        getLancdao().lancar(lanc);
        lanc = new Tablanc();
        lerLancamentos();
    }

    public void estornar(Tablanc lancamento) {
        lancdao.estornar(lancamento);
        lerLancamentos();
    }

    public List<Tablanc> listaLanc() {
        List<Tablanc> lista;
        lista = getLancdao().diario();

        return lista;
    }
    
    public void editarCelula(Tablanc lancamento) {
        lancdao.atualizar(lancamento);
    }

    /**
     * @return the lancamentos
     */
    public List<Tablanc> getLancamentos() {
        return lancamentos;
    }

    /**
     * @param lancamentos the lancamentos to set
     */
    public void setLancamentos(List<Tablanc> lancamentos) {
        this.lancamentos = lancamentos;
    }

    /**
     * @return the lanc
     */
    public Tablanc getLanc() {
        return lanc;
    }

    /**
     * @param lanc the lanc to set
     */
    public void setLanc(Tablanc lanc) {
        this.lanc = lanc;
    }

    /**
     * @return the lancdao
     */
    public LancDAO getLancdao() {
        return lancdao;
    }

    public ContaDAO getContadao() {
        return contadao;
    }

    public Tabconta getContaCred() {
        return contaCred;
    }

    public void setContaCred(Tabconta contaCred) {
        this.contaCred = contaCred;
    }

    public Tabconta getContaDeb() {
        return contaDeb;
    }

    public void setContaDeb(Tabconta contaDeb) {
        this.contaDeb = contaDeb;
    }
  
    public List<Tabconta> getListaContas() {
        return listaContas;
    }

    public void setListaContas(List<Tabconta> listaContas) {
        this.listaContas = listaContas;
    }

    public List<Tabconta> getContas() {
        return contas;
    }

    public void setContas(List<Tabconta> contas) {
        this.contas = contas;
    }
    
    

}
