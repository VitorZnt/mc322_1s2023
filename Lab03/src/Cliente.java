import java.util.ArrayList;

public class Cliente {
    
    // Atributos de instancia
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos;

    // Construtor
    public Cliente(String nome, String endereco) {
        
        this.nome = nome;
        this.endereco = endereco;
        listaVeiculos = new ArrayList<Veiculo>();
    }

    // Metodos de Get e Set
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    
    // Metodos especificos
    
    /* Recebe os dados de um veiculo a ser criado e o adiciona a um cliente, retornando true.
     * Se o veiculo de placa dada ja existir nesse cliente, retorna false e nao o adiciona.
     */
    public boolean cadastrarVeiculo(String placa, String marca, String modelo, int anoFabricacao) {
        
        if (buscarVeiculo(placa) != -1)
            return false;
        Veiculo novo = new Veiculo(placa, marca, modelo, anoFabricacao);
        listaVeiculos.add(novo);
        return true;
    }
    
    
    /*Busca um veiculo de placa fornecida. Se existir, retorna sua posicao na ArrayList.
     * Caso contrario, retorna -1 
     */
    public int buscarVeiculo(String placa) {
        
        Veiculo veic;
        for (int i = 0; i < listaVeiculos.size(); i++) {
            veic = listaVeiculos.get(i);
            if (veic.getPlaca() == placa)
                return i;
        }
        return -1;
    }
    
    //Tenta remover um veiculo de dada placa. Retorna true caso bem sucedido.
    public boolean removerVeiculo(String placa) {
        
        int i = buscarVeiculo(placa);
        if (i == -1)
            return false;
        else {
            listaVeiculos.remove(i);
            return true;
        }
    }

    
    public String listarVeiculos() {
        
        String str = new String();
        for (Veiculo i:listaVeiculos) {
            str = str + i.toString() + "\n";
        }
        return str;
    }

    
    /*Recebe um indice de veiculo do cliente e o retorna. Assume que a existencia desse
     *veiculo e seu indice ja foram verificados por buscarVeiculo
    */
    public Veiculo getVeiculo(int i) {
        return listaVeiculos.get(i);
    }


    @Override
    public String toString() {
        
        String str = String.format("Nome: %s\nEndereco: %s\n", nome, endereco);
        return str;
    }


}
