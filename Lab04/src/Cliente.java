import java.util.ArrayList;

public abstract class Cliente {
    
    // Atributos de instancia
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos;
    private int qtdVeiculos;
    private int qtdSinistros;
    private double valorSeguro;
    
    // Construtor
    public Cliente(String nome, String endereco) {
        
        this.nome = nome;
        this.endereco = endereco;
        listaVeiculos = new ArrayList<Veiculo>();
        qtdVeiculos = 0;
        qtdSinistros = 0;
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

    public int getQtdCarros() {
        return qtdVeiculos;
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
    
    //Adiciona 1 ao contador de veiculos
    public void addVeiculo() {
        qtdVeiculos++;
    }
    //Subtrai 1 do contador de veiculos
    public void remVeiculo() {
        qtdVeiculos--;
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
        addVeiculo();
        return true;
    }
    
    public boolean cadastrarVeiculo(Veiculo veic) {
        if (buscarVeiculo(veic.getPlaca()) != -1)
            return false;
        listaVeiculos.add(veic);
        addVeiculo();
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
    
    
    
    
    //Tenta remover um veiculo de dada placa. Retorna true caso bem sucedido.
    public boolean removerVeiculo(String placa) {
        
        int i = buscarVeiculo(placa);
        if (i == -1)
            return false;
        else {
            listaVeiculos.remove(i);
            remVeiculo();
            return true;
        }
    }

    
    
    public abstract double calculaScore();
    
    @Override
    public String toString() {
        
        String str = String.format("Nome: %s\nEndereco: %s\n", nome, endereco);
        return str;
    }


}
