import java.time.LocalDate;

public class ClientePF extends Cliente {
    
    //Atributos de instancia proprios da subclasse
    private final String CPF;
    private String genero;
    private String educacao;
    private LocalDate dataNascimento;
    private ListagemVeiculos listaVeic;
    
    //Construtor
    public ClientePF(String nome, String telefone,  String endereco, String email,
            String CPF, String genero, String educacao, LocalDate dataNascimento) {
        
        super(nome, telefone, endereco, email);
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
        this.educacao = educacao;
        this.genero = genero;
        listaVeic = new ListagemVeiculos();
    }


    
    //Metodos Get e Set da subclasse
    public String getCPF() {
        return CPF;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEducacao() {
        return educacao;
    }
    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
    public ListagemVeiculos getListaVeic() {
        return listaVeic;
    }
    
    
    @Override
    public double calculaScore() {
        
        double valorBase = CalcSeguro.VALOR_BASE.getValor();
        double valorIdade = CalcSeguro.fatorIdade(dataNascimento);
        return valorBase * valorIdade * listaVeic.getQtdVeiculos();
    }
    
    
    @Override
    public String toString() {
        
        String str = super.toString() + 
                     String.format("CPF: %s\nData de nascimento: %s\nEducacao: %s\nGenero: %s\nClasse economica: %s\n"
                                 + "Data licenca: %s\n", CPF, dataNascimento, educacao, genero, classeEconomica, dataLicenca)
                     + "Veiculos:\n" + listarVeiculos();
        return str;
    }
}
