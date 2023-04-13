import java.util.ArrayList;

public class Cliente {
    // Atributos de instancia
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos;

    //IMPLEMENTAR LISTA CARROS

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

    
    //Recebe os dados de um veiculo a ser criado e o adiciona a um cliente.
    public void cadastrarVeiculo(String placa, String marca, String modelo, int anoFabricacao) {
        Veiculo novo = new Veiculo(placa, marca, modelo, anoFabricacao);
        listaVeiculos.add(novo);
        return;
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




    // Metodos especificos
    @Override
    public String toString() {
        String str = String.format("Nome: %s\nEndereco: %s\n", nome, endereco);
        //COLOCAR CARROS AQUI TAMBEM
        return str;
    }


}
