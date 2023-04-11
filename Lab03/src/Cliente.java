public class Cliente {
    // Atributos de instancia
    private String nome;
    private String endereco;
    private Date dataLicenca;
    private String educacao;
    private String genero;
    private String classeEconomica;
    //IMPLEMENTAR LISTA CARROS

    // Construtor
    public Cliente(String nome, String endereco, Date dataLicenca, String educacao, 
                   String genero, String classeEconomica) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.genero = genero;
        this.classeEconomica = classeEconomica;
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

    public Date getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(Date dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClasseEconomica() {
        return classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

    // Metodos especificos
    @Override
    public String toString() {
        String str = String.format("Nome: %s\nEndereco: %s\nData licenca: %s\nEducacao: %s\nGenero: %s\nClasse economica: \n",
                                    nome, endereco, dataLicenca, educacao, genero, classeEconomica);
        //COLOCAR CARROS AQUI TAMBEM
        return str;
    }


}
