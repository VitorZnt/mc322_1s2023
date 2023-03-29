public class Cliente {
    // Atributos de instancia
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    // Construtor
    public Cliente (String nome, String cpf, String dataNascimento, int idade, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }

    // Metodos de Get e Set
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // Metodos especificos
    public String toString() {
        String str = String.format("Nome: %s\nCpf: %s\nData de nascimento: %s\nIdade: %d\nEndereco: %s\n",
                nome, cpf, dataNascimento, idade, endereco);
        return str;
    }

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

    public boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("\\.", "");
        cpf = cpf.replaceAll("-", "");
        // Verifica o tamanho
        if (cpf.length() != 11 || verificarDigitosIguais(cpf)) {
            return false;
        } else {
            return verificarDigitos(cpf);
        }
    }

}
