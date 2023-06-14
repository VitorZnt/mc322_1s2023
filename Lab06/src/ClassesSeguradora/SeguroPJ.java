package ClassesSeguradora;

import VeiculosEfrotas.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class SeguroPJ extends Seguro {
    
    private Frota frota;
    private ClientePJ cliente;
    
    public SeguroPJ(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora,
                    Frota frota, ClientePJ cliente) {
        
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.cliente = cliente;
    }

    public Frota getFrota() {
        return frota;
    }
    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public ClientePJ getCliente() {
        return cliente;
    }
    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }
    
    @Override
    public boolean gerarSinistro(LocalDate data, String endereco, String CPF) {
        
        if (super.gerarSinistro(data, endereco, CPF)) {
            cliente.addSinistro();
            return true;
        } else
            return false;
    }
    
    public double calcularValor() {
        
        int qtdFunc = cliente.getQtdFuncionarios();
        int qtdSinistrosClien = cliente.getQtdSinistros();
        int qtdVeiculos = frota.getListaVeic().getQtdVeiculos();
        double valorBase = CalcSeguro.VALOR_BASE.getValor();
        int anosPosFundacao = Period.between(cliente.getDataFundacao(), LocalDate.now()).getYears();
        
        int qtdSinistrosCondut = 0;
        ArrayList<Condutor> listaCondut = this.getListaCondutores();
        
        for (int i = 0; i < listaCondut.size(); i++) {
            i += listaCondut.get(i).getQtdSinistros();
        }
        
        return valorBase * (10 + qtdFunc/10) * (1 + 1/(qtdVeiculos + 2)) * (1 + 1/(anosPosFundacao + 2))
                * (2 + qtdSinistrosClien/10) * (5 + qtdSinistrosCondut/10);
    }
}
