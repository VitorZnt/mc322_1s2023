public class ClientePJ extends Cliente {
    //Atributos de instancia proprios da subclasse
    private final String CNPJ;
    private Date dataFundacao;
    
    
    //Construtor
    public ClientePJ(String nome, String endereco, Date dataLicenca, String educacao, 
                     String genero, String classeEconomica, String CNPJ, Date dataFundacao) {
        super(nome, endereco, dataLicenca, educacao, genero, classeEconomica);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
}
    
    
    //Metodos Get e Set da subclasse
    public String getCNPJ() {
        return CNPJ;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }
    
    
    //Metodos especificos
    
    /* Recebe uma string numerica de tamanho 14. Verifica se o cnpj eh
     * composto de 14 digitos iguais e retorna true caso sim.*/
    private boolean verificarDigitosIguais(String cnpj) {
        boolean flag = true;
        int i = 0;
        while (flag && i < 13) {
            if (cnpj.charAt(i) - '0' != cnpj.charAt(i+1) - '0')
                flag = false;
            i++;
        }
        return flag;
    }
    
    /* Recebe um cnpj em forma de string e checa se seus digitos verificadores
     * sao correspondentes. Caso seja um cnpj valido, retorna true.*/
    private boolean verificarDigitos(String cnpj) {
        int soma = 0;
        int digito1, digito2;

        // Calculo do digito1
        for (int i = 0; i < 8; i++) {
            
        }
    
    public boolean validarCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("\\.", "");
        cnpj = cnpj.replaceAll("-", "");
        cnpj = cnpj.replaceAll("/", "");
        // Verifica o tamanho
        if (cnpj.length() != 14 || verificarDigitosIguais(cnpj)) {
            return false;
        } else {
            return verificarDigitos(cnpj);
        }
    }
    
    
    @Override
    public String toString() {
        String str = super.toString() + String.format("CNPJ: %s\nData de fundacao: %s\n", CNPJ, dataFundacao);
    }
    

    
}
