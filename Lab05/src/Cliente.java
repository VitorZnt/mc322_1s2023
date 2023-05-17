public abstract class Cliente {
    
    // Atributos de instancia
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private int qtdSinistros;
    private double valorSeguro;
    
    // Construtor
    public Cliente(String nome, String telefone, String endereco, String email) {
        
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        qtdSinistros = 0;
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
    
    

    public int getQtdSinistros() {
        return qtdSinistros;
    }
    
    public double getValorSeguro() {
        return valorSeguro;
    }
    public void setValorSeguro(double valor) {
        valorSeguro = valor;
        return;
    }

    
    // Metodos especificos
    
    //Adiciona 1 ao contador de sinistros
    public void addSinistro() {
        qtdSinistros++;
        return;
    }
    //Subtrai 1 do contador de sinistros
    public void remSinistro() {
        qtdSinistros--;
        return;
    }
    
    
    @Override
    public String toString() {
        
        String str = String.format("Nome: %s\nEndereco: %s\n", nome, endereco);
        return str;
    }
}
