import java.time.LocalDate;

public class Sinistro {
    
    // Atributos de instancia
    private final int id;
    private LocalDate data;
    private String endereco;
    private Condutor condutor;
    private Seguro seguro;
    
    // Contador de sinistros (e gerador de Id's)
    private static int contadorId = 0;

    // Construtor
    public Sinistro(LocalDate data, String endereco, Condutor condutor, Seguro seguro) {
        
        this.data = data;
        this.endereco = endereco;
        this.condutor = condutor;
        this.seguro = seguro;
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
    
    public Condutor getCondutor() {
        return condutor;
    }
    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public Seguro getSeguro() {
        return seguro;
    }
    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    /* Retorna um Id proprio e unico, numerando por contagem cada sinistro registrado*/
    private int gerarId() {
        contadorId++;
        return contadorId;
    }
    
    @Override
    public String toString() {
        
        String str = String.format("Id: %d\nData: %s\nEndereco: %s\nCondutor: %s\nId do seguro: %s\n",
                                    id, data.toString(), endereco, condutor.getNome(), seguro.getId());
        return str;
    }
}
