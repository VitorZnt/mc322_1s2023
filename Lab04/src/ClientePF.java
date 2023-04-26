import java.time.LocalDate;

public class ClientePF extends Cliente {
    
    //Atributos de instancia proprios da subclasse
    private final String CPF;
    private LocalDate dataNascimento;
    private String educacao;
    private String genero;
    private String classeEconomica;
    private LocalDate dataLicenca;
    
    
    //Construtor
    public ClientePF(String nome, String endereco, String educacao, String genero,
                     String classeEconomica, String CPF, LocalDate dataNascimento, LocalDate dataLicenca) {
        
        super(nome, endereco);
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
        this.educacao = educacao;
        this.genero = genero;
        this.classeEconomica = classeEconomica;
        this.dataLicenca = dataLicenca;
    }


    
    //Metodos Get e Set da subclasse
    public String getCPF() {
        return CPF;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClasseEconomica() {
        return classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }
    
    public LocalDate getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(LocalDate dataLicenca) {
        this.dataLicenca = dataLicenca;
    }  
    
    @Override
    public double calculaScore() {
        
        double valorBase = CalcSeguro.VALOR_BASE.getValor();
        double valorIdade = CalcSeguro.fatorIdade(dataNascimento);
        return valorBase * valorIdade * this.getQtdCarros();
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
