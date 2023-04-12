import java.time.LocalDate;
import java.util.Scanner;

class Main {
    /*main com objetivo de teste das classes e seus metodos*/
    public static void main(String[] args) {
        ClientePJ meu_cliente = new ClientePJ("a", "a", "a", LocalDate.parse("2000-03-02"));
        Scanner entrada = new Scanner(System.in);
        System.out.println("Escreva um CNPJ: ");
        String cnpj = new String(entrada.next());
        System.out.println("Cpf lido: " + cnpj);
        boolean validade_cnpj = meu_cliente.validarCNPJ(cnpj);
        System.out.println("Validade do cnpj: " + validade_cnpj);
        String teste_to_string = meu_cliente.toString();
        System.out.println(teste_to_string);
        entrada.close();
    }
}
