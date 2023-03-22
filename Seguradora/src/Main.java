import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		String cpf = new String(entrada.next());
	    System.out.println("You entered string " + cpf);
	    Cliente meu_cliente = new Cliente("Vitor", cpf, "27/11/2004", 18, "minha casa");
	    boolean validade_cpf = meu_cliente.validarCPF(cpf);
	    System.out.println("cpf verified: " + validade_cpf);
	    entrada.close();
	}
}
