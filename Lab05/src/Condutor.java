import java.time.LocalDate;
import java.util.ArrayList;

public class Condutor {
    
    // Atributos de instancia
    private final String CPF;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private LocalDate dataNascimento;
    private ArrayList<Sinistro> listaSinistros;
    
    public Condutor(String CPF, String nome, String telefone,  String endereco,
                    String email, LocalDate dataNascimento) {
        
        this.CPF = CPF;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        listaSinistros = new ArrayList<Sinistro>();
    }
    
    // Metodos de Get e Set
    public String getCPF() {
        return CPF;
    }
    
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

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }
    
    
    public void adicionarSinistro(Sinistro sini) {
        listaSinistros.add(sini);
        return;
    }
    
}
