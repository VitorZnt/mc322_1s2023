import java.time.LocalDate;
import java.time.Period;

public class Validacao {
    
    //Funcao de classe (static) para verificar se um CPF eh valido. Retorna true caso sim.
    public static boolean validarCPF(String cpf) {
        
        cpf = cpf.replaceAll("\\.", "");
        cpf = cpf.replaceAll("-", "");
        // Verifica o tamanho
        if (cpf.length() != 11 || verificarDigitosIguaisCPF(cpf)) {
            return false;
        } else {
            return verificarDigitosCPF(cpf);
        }
    }

    public static boolean validarCNPJ(String cnpj) {
        
        cnpj = cnpj.replaceAll("\\.", "");
        cnpj = cnpj.replaceAll("-", "");
        cnpj = cnpj.replaceAll("/", "");
        // Verifica o tamanho
        if (cnpj.length() != 14 || verificarDigitosIguaisCNPJ(cnpj)) {
            return false;
        } else {
            return verificarDigitosCNPJ(cnpj);
        }
    }
    
    
    public static boolean validarNome(String nome) {
        if (nome.isEmpty() || nome.isBlank()) {
            return false;
        } else {
            char[] caracteres = nome.toCharArray(); 
            for (char i : caracteres) {   //verifica se ha numeros no nome
                if (Character.isDigit(i))
                    return false;
            }
            return true;
        }
    }
    
    
    public static boolean validarIdade(LocalDate dataNascimento) {
        Period Idade = Period.between(dataNascimento, LocalDate.now());
        int anosIdade = Idade.getYears();
        if (anosIdade < 18 || anosIdade > 90) {
            return false;
        }
        return true;
    }
    
    public static boolean validarDadosPF(String cpf, String nome, LocalDate dataNascimento) {
        return validarCPF(cpf) && validarNome(nome) && validarIdade(dataNascimento);
    }
    
    public static boolean validarDadosPJ(String cnpj, String nome) {
        return validarCNPJ(cnpj) && validarNome(nome);
    }
    
    /* Recebe uma string numerica de tamanho 11. Verifica se o cpf eh
     * composto de 11 digitos iguais e retorna true caso sim.*/
    private static boolean verificarDigitosIguaisCPF(String cpf) {
        
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
    private static boolean verificarDigitosCPF(String cpf) {
        
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
    
    
    /* Recebe uma string numerica de tamanho 14. Verifica se o cnpj eh
     * composto de 14 digitos iguais e retorna true caso sim.*/
    private static boolean verificarDigitosIguaisCNPJ(String cnpj) {
        
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
    private static boolean verificarDigitosCNPJ(String cnpj) {
        
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

    
}
