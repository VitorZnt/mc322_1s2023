import java.time.LocalDate;

public class ClientePF extends Cliente {
    
    //Atributos de instancia proprios da subclasse
    private final String CPF;
    private String genero;
    private String educacao;
    private LocalDate dataNascimento;
    private ListagemVeiculos listaVeic; //metodos cadastrar e remover veiculos estao nessa classe
    
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
    
    public boolean cadastrarVeiculo(String placa, String marca, String modelo, int anoFabricacao) {
        return listaVeic.cadastrarVeiculo(placa, marca, modelo, anoFabricacao);
    }
    
    public boolean cadastrarVeiculo(Veiculo veic) {
        return listaVeic.cadastrarVeiculo(veic);
    }
    
    public boolean removerVeiculo(String placa) {
        return listaVeic.removerVeiculo(placa);
    }
    
    @Override
    public String toString() {
        
        String str = super.toString() + 
                     String.format("CPF: %s\nGenero: %s\nEducacao: %s\nData de nascimento: %s\n",
                                   CPF, genero, educacao, dataNascimento)
                     + "Veiculos:\n" + listaVeic.toString();
        return str;
    }
}