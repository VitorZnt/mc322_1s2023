public class Sinistro {
    // Atributos de instancia
    private int id;
    private String data;
    private String endereco;
    // Contador de sinistros (e gerador de Id's)
    private static int contadorId = 0;

    // Construtor
    public Sinistro(String data, String endereco) {
        this.data = data;
        this.endereco = endereco;
        this.id = gerarId();
    }

    // Metodos de Get e Set
    // Obs: o Id eh gerado internamente e nao esta disponivel para alteracao via set()
    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /* Retorna um Id proprio e unico, numerando por contagem cada sinistro registrado*/
    private int gerarId() {
        contadorId++;
        return contadorId;
    }
}
