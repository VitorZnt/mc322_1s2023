import java.util.ArrayList;

public class ListagemFrotas {
    private ArrayList<Frota> listaFrotas;
    
    public ListagemFrotas() {
        listaFrotas = new ArrayList<Frota>();
    }
    
    //Retorna a frota de indice i. Assume que sua existencia ja foi verificada
    public Frota getFrota(int i) {
        return listaFrotas.get(i);
    }
    
    //Busca e retorna o indice da frota com codigo dado
    public int buscarFrota(String codigo) {
        
        for (int i = 0; i < listaFrotas.size(); i++) {
            if (listaFrotas.get(i).getCodigo().equals(codigo))
                return i;
        }
        return -1;
    }
    
    public ArrayList<Veiculo> getVeiculosPorFrota(String codigo) {
        
        int i = buscarFrota(codigo);
        if (i == -1)
            return null;
        Frota f = listaFrotas.get(i);
        ListagemVeiculos v = f.getListaVeic();
        ArrayList<Veiculo> l = new ArrayList<Veiculo>();
        for (i = 0; i < v.getQtdVeiculos(); i++) {
            l.add(v.getVeiculo(i));
        }
        return l;
    }
    
    /* Adiciona uma serie de veiculos como uma frota na listagem. Retorna false e nao cria
     * caso algum veiculo do parametro ja existe em alguma frota da mesma listagem.
     */
    public boolean cadastrarFrota(String codigo, Veiculo... listaVeic) {
        
        if (listaVeic.length == 0)
            return false;
        
        for (Frota f : listaFrotas)
            if (f.getCodigo().equals(codigo))
                return false;
        
        for (Veiculo v : listaVeic) {
            for (Frota f : listaFrotas) {
                if(f.getListaVeic().buscarVeiculo(v.getPlaca()) != -1)
                    return false;
            }
        }
        Frota f = new Frota(codigo, listaVeic);
        listaFrotas.add(f);
        return true;
    }
    
    //Metodos de "atualizarFrota"
    
    public boolean addVeiculoFrota(String codigo, Veiculo veic) {
        
        int i = buscarFrota(codigo);
        if (i == -1)
            return false;
        
        for (Frota f : listaFrotas)
            if(f.getListaVeic().buscarVeiculo(veic.getPlaca()) != -1)
                return false;
        
        return getFrota(i).adicionaVeiculo(veic);
    }
    
    public boolean removerFrota(String codigo) {
        
        int i = buscarFrota(codigo);
        if (i == -1)
            return false;
        listaFrotas.remove(i);
        return true;
    }
    
    public boolean removerVeiculoFrota(String codigo, Veiculo veic) {
        
        int i = buscarFrota(codigo);
        if (i == -1)
            return false;
        return getFrota(i).removeVeiculo(veic.getPlaca());
    }

    
    @Override
    public String toString() {
        
        String s = new String();
        for (int i = 0; i < listaFrotas.size(); i++) {
            
            ListagemVeiculos listaVeic = listaFrotas.get(i).getListaVeic();
            s += String.format("Frota %d, codigo %s:\n", i, listaFrotas.get(i).getCodigo());
            
            for (int j = 0; j < listaVeic.getQtdVeiculos(); j++) {
                s += listaVeic.toString();
            }
        }
        return s;
    }

}
