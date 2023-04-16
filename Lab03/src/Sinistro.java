import java.time.LocalDate;

public class Sinistro {
    
    // Atributos de instancia
    private final int id;
    private LocalDate data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;
    
    // Contador de sinistros (e gerador de Id's)
    private static int contadorId = 0;

    // Construtor
    public Sinistro(LocalDate data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
        
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.id = gerarId();
    }

    // Metodos de Get e Set
    // Obs: o Id eh gerado internamente e nao esta disponivel para alteracao via set()
    public int getId() {
        return id;
    }

    public String getData() {
        return data.toString();
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public Seguradora getSeguradora() {
        return seguradora;
    }
    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }
    
    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    /* Retorna um Id proprio e unico, numerando por contagem cada sinistro registrado*/
    private int gerarId() {
        contadorId++;
        return contadorId;
    }
    
    @Override
    public String toString() {
        
        String str = String.format("Id: %d\nData: %s\nEndereco: %s\nSeguradora: %s\nVeiculo: %s\nCliente: %s\n",
                                    id, data.toString(), endereco, seguradora.getNome(), veiculo.getPlaca(), cliente.getNome());
        return str;
    }
}
