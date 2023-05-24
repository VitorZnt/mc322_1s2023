import java.time.LocalDate;
import java.util.ArrayList;

public class ClientePJ extends Cliente {
    
    //Atributos de instancia proprios da subclasse
    private final String CNPJ;
    private LocalDate dataFundacao;
    private int qtdFuncionarios;
    private ListagemFrotas listaFrotas;
    
    //Construtor
    public ClientePJ(String nome, String telefone,  String endereco, String email,
            String CNPJ, LocalDate dataFundacao, int qtdFuncionarios) {
        
        super(nome, telefone, endereco, email);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
        this.qtdFuncionarios = qtdFuncionarios;
        listaFrotas = new ListagemFrotas();
    }
    
    
    //Metodos Get e Set da subclasse
    public String getCNPJ() {
        return CNPJ;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }
    
    public int getQtdFuncionarios() {
        return qtdFuncionarios;
    }
    public void setQtdFuncionarios(int num) {
        qtdFuncionarios = num;
    }
    
    public ListagemFrotas getListaFrotas() {
        return listaFrotas;
    }
    
    public int buscarFrota(String codigo) {
        return listaFrotas.buscarFrota(codigo);
    }
    
    public Frota getFrota(String codigo) {
        
        int i = buscarFrota(codigo);
        if (i == -1)
            return null;
        return listaFrotas.getFrota(i);
    }
    
    public ArrayList<Veiculo> getVeiculosPorFrota(String codigo) {
        return listaFrotas.getVeiculosPorFrota(codigo);
    }
    
    public boolean cadastrarFrota(String codigo, Veiculo... listaVeic) {
        return listaFrotas.cadastrarFrota(codigo, listaVeic);
    }
    
    //Caso comando == 1, adiciona o veic. Caso seja 2, remove
    public boolean atualizarFrota(String codigo, Veiculo veic, int comando) {
        
        if (comando == 1) {
            return listaFrotas.addVeiculoFrota(codigo, veic);
        } else if (comando == 2) {
            return listaFrotas.removerVeiculoFrota(codigo, veic);
        } else {
            return false;
        }
    }
    
    //Caso comando == 3, remove a frota inteira
    public boolean atualizarFrota(String codigo, int comando) {
        
        if (comando == 3) {
            return listaFrotas.removerFrota(codigo);
        } else {
            return false;
        }
    }
    

    @Override
    public String toString() {
        
        String str = super.toString() + String.format("CNPJ: %s\nData de fundacao: %s\n", CNPJ, dataFundacao.toString())
                     + "Veiculos nas frotas:\n" + listaFrotas.toString();
        return str;
    }
    

    
}