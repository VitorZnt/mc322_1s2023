import java.util.ArrayList;

public abstract class Cliente {
    
    // Atributos de instancia
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private int qtdSinistros;
    private double valorSeguro;
    private ArrayList<Seguro> listaSeguros;
    
    // Construtor
    public Cliente(String nome, String telefone, String endereco, String email) {
        
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        qtdSinistros = 0;
        valorSeguro = 0;
        listaSeguros = new ArrayList<Seguro>();
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
    }

    public ArrayList<Seguro> getListaSeguros() {
        return listaSeguros;
    }
    
    public void adicionarSeguro(Seguro seguro) {
        listaSeguros.add(seguro);
    }
    
    public boolean removerSeguro(int id) {
        
        for (int i = 0; i < listaSeguros.size(); i++) {
            if (listaSeguros.get(i).getId() == id) {
                listaSeguros.remove(i);
                return true;
            }
        }
        return false; //nao encontrado
    }
    
    public void atualizarValorSeguro() {
        
        double i = 0;
        for (Seguro seguro : listaSeguros) {
            i += seguro.getValorMensal();
        }
        setValorSeguro(i);
    }
    
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
