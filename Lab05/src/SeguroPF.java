import java.time.LocalDate;
import java.util.ArrayList;

public class SeguroPF extends Seguro {
    
    private Veiculo veiculo;
    private ClientePF cliente;
    
    public SeguroPF(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora,
                    Veiculo veiculo, ClientePF cliente) {
        
        super(dataInicio, dataFim, seguradora);
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public ClientePF getCliente() {
        return cliente;
    }
    public void setCliente(ClientePF cliente) {
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
        
        int qtdVeiculos = 1; //pelo diagrama, cada seguroPF tem apenas 1 veiculo
        int qtdSinistrosClien = cliente.getQtdSinistros();
        int qtdSinistrosCondut = 0;
        double valorBase = CalcSeguro.VALOR_BASE.getValor();
        double fatorIdade = CalcSeguro.fatorIdade(cliente.getDataNascimento());
        
        ArrayList<Condutor> listaCondut = this.getListaCondutores();
        
        for (int i = 0; i < listaCondut.size(); i++) {
            i += listaCondut.get(i).getQtdSinistros();
        }
        
        return valorBase * fatorIdade * (1 + 1/(qtdVeiculos + 2))
                * (2 + qtdSinistrosClien/10) * (5 + qtdSinistrosCondut/10);
    }
}
