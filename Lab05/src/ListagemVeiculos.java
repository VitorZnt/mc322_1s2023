import java.util.ArrayList;

//Classe auxiliar para funcoes de armazenagem de lista de veiculos
public class ListagemVeiculos {
    private ArrayList<Veiculo> listaVeiculos;
    private int qtdVeiculos;
    
    public ListagemVeiculos() {
        listaVeiculos = new ArrayList<Veiculo>();
        qtdVeiculos = 0;
    }
    
    
    
    
    //Getters
    
    public int getQtdVeiculos() {
        return qtdVeiculos;
    }
    
    /*Recebe um indice de veiculo e o retorna. Assume que a existencia desse
     *veiculo e seu indice ja foram verificados por buscarVeiculo
    */
    public Veiculo getVeiculo(int i) {
        return listaVeiculos.get(i);
    }
    
    
    //Adiciona 1 ao contador de veiculos
    public void addVeiculo() {
        qtdVeiculos++;
    }
    //Subtrai 1 do contador de veiculos
    public void remVeiculo() {
        qtdVeiculos--;
    }
    /* Recebe os dados de um veiculo a ser criado e o adiciona na lista, retornando true.
     * Se o veiculo de placa dada ja existir na lista, retorna false e nao o adiciona.
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
            if (veic.getPlaca().equals(placa))
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
            remVeiculo();
            return true;
        }
    }

    
    
    @Override
    public String toString() {
        
        String str = new String();
        for (Veiculo i : listaVeiculos) {
            str = str + i.toString() + "\n";
        }
        return str;
    }
}
