public class Cliente {
    // Atributos de instancia
    private String nome;
    private String endereco;


    //IMPLEMENTAR LISTA CARROS

    // Construtor
    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
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





    // Metodos especificos
    @Override
    public String toString() {
        String str = String.format("Nome: %s\nEndereco: %s\nData licenca: %s\n", nome, endereco);
        //COLOCAR CARROS AQUI TAMBEM
        return str;
    }


}
