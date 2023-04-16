import java.time.LocalDate;

public class ClientePJ extends Cliente {
    
    //Atributos de instancia proprios da subclasse
    private final String CNPJ;
    private LocalDate dataFundacao;
    
    
    //Construtor
    public ClientePJ(String nome, String endereco, String CNPJ, LocalDate dataFundacao) {
        
        super(nome, endereco);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
}
    
    
    //Metodos Get e Set da subclasse
    public String getCNPJ() {
        return CNPJ;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }
    
    
    //Metodos especificos
    
    /* Recebe uma string numerica de tamanho 14. Verifica se o cnpj eh
     * composto de 14 digitos iguais e retorna true caso sim.*/
    private static boolean verificarDigitosIguais(String cnpj) {
        
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
    private static boolean verificarDigitos(String cnpj) {
        
        int soma = 0;
        int digito1, digito2;

        // Calculo do digito1
        for (int i = 0; i < 4; i++) {
            soma += (cnpj.charAt(i) - '0') * (5-i);
        }
        for (int i = 4; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * (13-i);
        }
        soma = soma % 11;
        if (11 - soma < 10)
            digito1 = 11 - soma;
        else
            digito1 = 0;
        
        // Calculo do digito2
        soma = 0;
        for (int i = 0; i < 5; i++) {
            soma += (cnpj.charAt(i) - '0') * (6-i);
        }
        for (int i = 5; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * (14-i);
        }
        soma += digito1 * 2;
        soma = soma % 11;
        if (11 - soma < 10)
            digito2 = 11 - soma;
        else
            digito2 = 0;
        
        // Comparacao dos digitos esperados e dos recebidos
        if (digito1 == cnpj.charAt(12) - '0' && digito2 == cnpj.charAt(13) - '0')
            return true;
        else
            return false;
    }
        
    public static boolean validarCNPJ(String cnpj) {
        
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
        
        String str = super.toString() + String.format("CNPJ: %s\nData de fundacao: %s\n\n", CNPJ, dataFundacao.toString())
                     + "Veiculos:\n" + listarVeiculos();
        return str;
    }
    

    
}
