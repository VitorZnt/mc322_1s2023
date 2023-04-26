import java.time.LocalDate;

public class ClientePJ extends Cliente {
    
    //Atributos de instancia proprios da subclasse
    private final String CNPJ;
    private LocalDate dataFundacao;
    private int qtdFuncionarios;
    
    //Construtor
    public ClientePJ(String nome, String endereco, String CNPJ, LocalDate dataFundacao, int qtdFuncionarios) {
        
        super(nome, endereco);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
        this.qtdFuncionarios = qtdFuncionarios;
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
    
    
    @Override
    public double calculaScore() {
        double valorBase = CalcSeguro.VALOR_BASE.getValor();
        return valorBase * (1 + (this.getQtdFuncionarios())/100) * this.getQtdCarros();
    }
    
    
    @Override
    public String toString() {
        
        String str = super.toString() + String.format("CNPJ: %s\nData de fundacao: %s\n", CNPJ, dataFundacao.toString())
                     + "Veiculos:\n" + listarVeiculos();
        return str;
    }
    

    
}
