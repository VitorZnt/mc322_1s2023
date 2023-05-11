public class Frota {
    
    private String codigo;
    private ListagemVeiculos listaVeic;
    
    public Frota (String codigo, Veiculo veic) {
        
        this.codigo = codigo;
        listaVeic = new ListagemVeiculos();
        listaVeic.cadastrarVeiculo(veic);
    }

    public String getCodigo() {
        return codigo;
    }

    public ListagemVeiculos getListaVeic() {
        return listaVeic;
    }
    
    
    public boolean addVeiculo(Veiculo veic) {
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
