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
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author aila
 */
@ManagedBean
public class ControladorRelatorios {

    private List<Tablanc> lancamentos = new ArrayList<>();
    private List<Razao> lancrazao = new ArrayList<>();
    private List<Tabconta> balancetes = new ArrayList<>();
    private List<Tablanc> lancdiario = new ArrayList<>(); 
    private ContaDAO contadao = new ContaDAO();
    private LancDAO lancdao = new LancDAO();

    private List<Tabconta> listaContas;

    private Date dataI, dataF;
    private Tabconta ct;

    private Double saldoF;

    @PostConstruct
    public void relatorios() {

        setListaContas(getContadao().listar());
        //Tabconta conta = new Tabconta();
        //conta = getContadao().filtrarCt(43);

    }

    public void buscarLancamentos() {
        setLancamentos(getLancdao().diario());
    }

    //Primeiro método para realizar razão. Gerencia os outros
    public void fazerRazao() {

        //calcular saldo inicial
        Double saldoI = encontrarSaldoInicial();

        //calcular razão
        razao(ct, saldoI);

    }

    public Double encontrarSaldoInicial() {

        Double dR, cR, saldo = 0.0;
        List<Tablanc> lancPorData = new ArrayList<>();
        buscarLancamentos();
        Collections.sort(lancamentos);

        //Remove os lançamentos posteriores a data inicial   
        int posAntes = 0;
        if (lancamentos.size() >= 1) {
            while (lancamentos.get(posAntes).getDataLanc().compareTo(dataI) < 0) {
                posAntes++;
            }
            lancPorData = lancamentos.subList(0, posAntes);
        } else {
            lancPorData = lancamentos;
        }

        for (int i = 0; i < lancPorData.size(); i++) {
            if ((ct.getTipoConta() == true) && (lancPorData.get(i).getIdContaD().equals(ct))) {
                dR = lancPorData.get(i).getValorLanc();
                cR = 0.0;
                saldo = saldo + dR - cR;
            }
            if ((ct.getTipoConta() == false) && (lancPorData.get(i).getIdContaD().equals(ct))) {
                dR = lancPorData.get(i).getValorLanc();
                cR = 0.0;
                saldo = saldo - dR + cR;
            }
            if ((ct.getTipoConta() == true) && (lancPorData.get(i).getIdContaC().equals(ct))) {
                dR = 0.0;
                cR = lancPorData.get(i).getValorLanc();
                saldo = saldo + dR - cR;
            }
            if ((ct.getTipoConta() == false) && (lancPorData.get(i).getIdContaC().equals(ct))) {
                dR = 0.0;
                cR = lancPorData.get(i).getValorLanc();
                saldo = saldo - dR + cR;
            }

        }
        return saldo;

    }

    public void razao(Tabconta conta, Double saldoI) {
        List<Tablanc> lancPorData = new ArrayList<>();

        //Ordena a lista de lançamentos        
        buscarLancamentos();
        Collections.sort(lancamentos);

        //Ajusta a lista para a data desejada
        lancPorData = ajustarDatas();

        //Realiza razão, passa a conta recebida pelo JSF, e a lista de lançamentos na data ajustada      
        razaoPorConta(conta, lancPorData, saldoI);

        //Ajusta saldo inicial e o total
        setarInicialeTotal(saldoI);
    }

    public List<Tablanc> ajustarDatas() {
        List<Tablanc> lancPorData = new ArrayList<>();

        //Remove os lançamentos anteriores a data inicial   
        int posAntes = 0;
        while (lancamentos.get(posAntes).getDataLanc().compareTo(dataI) < 0) {
            posAntes++;
        }
        lancPorData = lancamentos.subList(posAntes, lancamentos.size());

        //Remove os lançamentos posteriores a data final        
        int posDepois = lancPorData.size() - 1;  //pegando a última posição da lista
        if (posDepois <= 0) {
            posDepois = 0;
        } else {
            System.out.println("pos: " + posDepois);
            while (lancPorData.get(posDepois).getDataLanc().compareTo(dataF) > 0) {
                posDepois--;
            }
            lancPorData = lancPorData.subList(0, posDepois + 1);
        }
        return lancPorData;
    }

    public Tabconta razaoPorConta(Tabconta ct, List<Tablanc> lancPorData, Double saldoI) {
        //Porque aqui eu não recebo a conta do JSF logo de cara? 
        //Porque esse método é ultizado tanto para realizar razão quanto balancete
        //Razão chama esse método somente uma vez, porque é para uma conta específica
        //Balancete chama pra todas as contas que foram lançadas dentro de um intervalo pré definido
        //Por isso que também passamos por parâmetro o lancPorData, porque ele depende de cada conta.
        //O saldo inicial também depende se é razão ou balancete 

        Date dataR;
        String histR;
        Double dR, cR, saldo = saldoI;

        for (int i = 0; i < lancPorData.size(); i++) {
            if ((ct.getClasseConta() == true) && (lancPorData.get(i).getIdContaD().equals(ct))) {
                dataR = lancPorData.get(i).getDataLanc();
                histR = lancPorData.get(i).getHistLanc();
                dR = lancPorData.get(i).getValorLanc();
                cR = 0.0;
                saldo = saldo + dR - cR;

                lancrazao.add(new Razao(dataR, histR, dR, cR, saldo));
            }
            if ((ct.getClasseConta() == false) && (lancPorData.get(i).getIdContaD().equals(ct))) {
                dataR = lancPorData.get(i).getDataLanc();
                histR = lancPorData.get(i).getHistLanc();
                dR = lancPorData.get(i).getValorLanc();
                cR = 0.0;
                saldo = saldo - dR + cR;

                lancrazao.add(new Razao(dataR, histR, dR, cR, saldo));
            }
            if ((ct.getClasseConta() == true) && (lancPorData.get(i).getIdContaC().equals(ct))) {
                dataR = lancPorData.get(i).getDataLanc();
                histR = lancPorData.get(i).getHistLanc();
                dR = 0.0;
                cR = lancPorData.get(i).getValorLanc();
                saldo = saldo + dR - cR;

                lancrazao.add(new Razao(dataR, histR, dR, cR, saldo));
            }
            if ((ct.getClasseConta() == false) && (lancPorData.get(i).getIdContaC().equals(ct))) {
                dataR = lancPorData.get(i).getDataLanc();
                histR = lancPorData.get(i).getHistLanc();
                dR = 0.0;
                cR = lancPorData.get(i).getValorLanc();
                saldo = saldo - dR + cR;

                lancrazao.add(new Razao(dataR, histR, dR, cR, saldo));
            }

        }
        setSaldoF(saldo);
        return ct;
    }

    public void setarInicialeTotal(Double saldoI) {
        List<Razao> aux = new ArrayList<>();
        Double creditos = 0.0, debitos = 0.0;

        aux.add(new Razao(dataI, "Saldo Inicial", null, null, saldoI));

        for (Razao r : lancrazao) {
            aux.add(r);
            creditos = creditos + r.getCredRazao();
            debitos = debitos + r.getDebRazao();
        }

        aux.add(new Razao(null, "Totais", debitos, creditos, saldoF));

        lancrazao = aux;

    }

    public void balancete() {

        List<Tablanc> lancPorData = new ArrayList<>();
        List<Tabconta> contas = new ArrayList<>();
        Tabconta ctSaldo;
        Double saldoI = 0.0;

        //Ordena a lista de lançamentos        
        buscarLancamentos();
        Collections.sort(lancamentos);

        //Ajusta data de acordo com o desejado
        lancPorData = ajustarDatas();

        //lista de contas que foram lançadas
        contas = contasLancadas();

        //Para cada conta existente, é realizado o razão dentro da data desejada.
        for (Tabconta conta : contas) {
            ctSaldo = razaoPorConta(conta, lancPorData, saldoI);
            ctSaldo.setUltSaldo(lancrazao.get(lancrazao.size() - 1).getSaldoRazao());
            balancetes.add(ctSaldo);
        }

    }

    //Separa contas que não foram lançadas das que foram lançadas para fazer o balancete
    public List<Tabconta> contasLancadas() {
        List<Tabconta> contas = new ArrayList<>();

        for (Tablanc lanc : lancamentos) {
            contas.add(lanc.getIdContaC());
            contas.add(lanc.getIdContaD());
        }

        List<Tabconta> lista = new ArrayList(new HashSet(contas));

        return lista;
    }
    
    public void diario(){
         //Ordena a lista de lançamentos        
        buscarLancamentos();
        Collections.sort(lancamentos);
        
        List<Tablanc> lancPorData = ajustarDatas();
        System.out.println("sizeeeeeeeeeeeee: "+lancPorData.size());
              
        //Ordena a lista de lancPorData
        Collections.sort(lancPorData);
        
        setLancdiario(lancPorData);
    }

    public List<Tablanc> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Tablanc> lancamentos) {
        this.lancamentos = lancamentos;
    }

    public List<Razao> getLancrazao() {
        return lancrazao;
    }

    public List<Tabconta> getBalancetes() {
        return balancetes;
    }

    public ContaDAO getContadao() {
        return contadao;
    }

    public LancDAO getLancdao() {
        return lancdao;
    }

    public Date getDataI() {
        return dataI;
    }

    public void setDataI(Date dataI) {
        this.dataI = dataI;
    }

    public Date getDataF() {
        return dataF;
    }

    public void setDataF(Date dataF) {
        this.dataF = dataF;
    }

    public Tabconta getCt() {
        return ct;
    }

    public void setCt(Tabconta ct) {
        this.ct = ct;
    }

    public List<Tabconta> getListaContas() {
        return listaContas;
    }

    public void setListaContas(List<Tabconta> listaContas) {
        this.listaContas = listaContas;
    }

    public Double getSaldoF() {
        return saldoF;
    }

    public void setSaldoF(Double saldoF) {
        this.saldoF = saldoF;
    }

    public List<Tablanc> getLancdiario() {
        return lancdiario;
    }

    public void setLancdiario(List<Tablanc> lancdiario) {
        this.lancdiario = lancdiario;
    }

    
}
