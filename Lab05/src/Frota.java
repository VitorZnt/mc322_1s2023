public class Frota {
    
    private String codigo;
    private ListagemVeiculos listaVeic;
    
    public Frota (String codigo, Veiculo[] listaV) {
        
        this.codigo = codigo;
        listaVeic = new ListagemVeiculos();
        for (Veiculo v : listaV)
            listaVeic.cadastrarVeiculo(v);
    }

    public String getCodigo() {
        return codigo;
    }

    public ListagemVeiculos getListaVeic() {
        return listaVeic;
    }
    
    
    public boolean adicionaVeiculo(Veiculo veic) {
        return listaVeic.cadastrarVeiculo(veic);
    }
    
    
    public boolean removeVeiculo(String placa) {
        return listaVeic.removerVeiculo(placa);
    }
    
    @Override
    public String toString() {
        return listaVeic.toString();
    }
}
