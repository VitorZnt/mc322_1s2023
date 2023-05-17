import java.time.LocalDate;

public class SeguroPJ extends Seguro {
    
    private Frota frota;
    private ClientePJ cliente;
    
    public SeguroPJ(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora,
                    Frota frota, ClientePJ cliente) {
        
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.cliente = cliente;
    }

    public Frota getFrota() {
        return frota;
    }
    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public ClientePJ getCliente() {
        return cliente;
    }
    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }
    
    public double calcularValor() {
        
    }
}
