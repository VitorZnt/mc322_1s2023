public class Veiculo {
    // Atributos de instancia
    private String placa;
    private String marca;
    private String modelo;
    private int anoFabricacao;

    // Construtor
    public Veiculo(String placa, String marca, String modelo, int anoFabricacao) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
    }

    // Metodos de Get e Set
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }
    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }
    
    @Override
    public String toString() {
        String str = String.format("Placa: %s\nMarca: %s\nModelo: %s\nAno de fabricacao: %d\n",
                                    placa, marca, modelo, anoFabricacao);
        return str;
    }
    

}
