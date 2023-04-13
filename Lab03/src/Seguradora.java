import java.time.LocalDate;
import java.util.ArrayList;

public class Seguradora {
    // Atributos de instancia
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Cliente> listaClientes;
    
    
    // Construtor
    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    // Metodos de Get e Set
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }
    public void set(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    
    //Metodos especificos
    
    
    /*Busca um cliente de nome e num CPF/CNPJ fornecidos. Se existir, retorna sua posicao na ArrayList.
     * Caso contrario, retorna -1 
     */
    private int buscarCliente(String nome, String num) {
    }
    
    //Cadastra um cliente PF. Caso ja exista um mesmo cliente ja cadastrado, ou se o CPF for invalido, retorna false.
    public boolean cadastrarCliente(String nome, String endereco, String educacao, String genero, String classeEconomica,
                                      String CPF, LocalDate dataNascimento, LocalDate dataLicenca) {
        
        //Cliente ja existente ou CPF invalido
        if ((ClientePF.validarCPF(CPF) == false) || (buscarCliente(nome, CPF) != -1))
            return false;

        else {
            ClientePF novo = new ClientePF(nome, endereco, educacao, genero, classeEconomica, CPF, dataNascimento, dataLicenca);
            listaClientes.add(novo);
            return true;
        }
    }
    
    //Cadastra um cliente PJ. Caso ja exista um mesmo cliente ja cadastrado, ou se o CNPJ for invalido, retorna false.
    public boolean cadastrarCliente(String nome, String endereco, String CNPJ, LocalDate dataFundacao) {
        
        //Cliente ja existente ou CNPJ invalido
        if ((ClientePJ.validarCNPJ(CNPJ) == false) || (buscarCliente(nome, CNPJ) != -1))
            return false;
        
        else {
            ClientePJ novo = new ClientePJ(nome, endereco, CNPJ, dataFundacao);
            listaClientes.add(novo);
            return true;
        }
    }
    
}
