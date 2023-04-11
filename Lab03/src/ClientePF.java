public class ClientePF extends Cliente {
    //Atributos de instancia proprios da subclasse
    private final String CPF;
    private Date dataNascimento;
    
    
    //Construtor
    public ClientePF(String nome, String endereco, Date dataLicenca, String educacao, 
                     String genero, String classeEconomica, String CPF, Date dataNascimento) {
        super(nome, endereco, dataLicenca, educacao, genero, classeEconomica);
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
    }


    
    //Metodos Get e Set da subclasse
    public String getCPF() {
        return CPF;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    //Metodos especificos

    /* Recebe uma string numerica de tamanho 11. Verifica se o cpf eh
     * composto de 11 digitos iguais e retorna true caso sim.*/
    private boolean verificarDigitosIguais(String cpf) {
        boolean flag = true;
        int i = 0;
        while (flag && i < 10) {
            if (cpf.charAt(i) - '0' != cpf.charAt(i+1) - '0')
                flag = false;
            i++;
        }
        return flag;
    }

    /* Recebe um cpf em forma de string e checa se seus digitos verificadores
     * sao correspondentes. Caso seja um cpf valido, retorna true.*/
    private boolean verificarDigitos(String cpf) {
        int soma = 0;
        int digito1, digito2;

        // Calculo do digito1
        for (int i = 0; i < 9; i++)
            soma += (cpf.charAt(i) - '0') * (10-i);
        soma = soma % 11;
        if (11 - soma < 10)
            digito1 = 11 - soma;
        else
            digito1 = 0;

        // Calculo do digito2
        soma = 0;
        for (int i = 0; i < 9; i++)
            soma += (cpf.charAt(i) - '0') * (11-i);
        soma += digito1 * 2;
        soma = soma % 11;
        if (11 - soma < 10)
            digito2 = 11 - soma;
        else
            digito2 = 0;

        // Comparacao dos digitos esperados e dos recebidos
        if (digito1 == cpf.charAt(9) - '0' && digito2 == cpf.charAt(10) - '0')
            return true;
        else
            return false;
    }

    public boolean validarcpf(String cpf) {
        cpf = cpf.replaceAll("\\.", "");
        cpf = cpf.replaceAll("-", "");
        // Verifica o tamanho
        if (cpf.length() != 11 || verificarDigitosIguais(cpf)) {
            return false;
        } else {
            return verificarDigitos(cpf);
        }
    }

    @Override
    public String toString() {
        String str = super.toString() + String.format("CPF: %s\nData de nascimento: %s\n", CPF, dataNascimento);
    }
}
