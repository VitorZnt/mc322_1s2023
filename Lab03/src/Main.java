import java.util.Scanner;

class Main {
    /*main com objetivo de teste das classes e seus metodos*/
    public static void main(String[] args) {
        // Instanciacao dos objetos
        Cliente meu_cliente = new Cliente("Vitor", "000.000.000-00", "27/11/2004", 18, "minha casa");
        Seguradora minha_seguradora = new Seguradora("HiperSegura.com", "(11)91234-5678",
                "hiper@seguros.com", "Av. Erico Verissimo, 50");
        Veiculo meu_veiculo = new Veiculo("ABC-1122", "Ford", "Fusion");
        Sinistro meu_sinistro1 = new Sinistro("30/02/2023", "Rua Saturnino de Brito");
        Sinistro meu_sinistro2 = new Sinistro("31/02/2023", "Rua Albert Einstein");
        Scanner entrada = new Scanner(System.in);


        // Teste do Cliente
        System.out.println("Teste Cliente");
        System.out.println("Escreva um CPF: ");
        String cpf = new String(entrada.next());
        meu_cliente.setCpf(cpf);
        System.out.println("Cpf lido: " + meu_cliente.getCpf());
        boolean validade_cpf = meu_cliente.validarCPF(meu_cliente.getCpf());
        System.out.println("Validade do cpf: " + validade_cpf);
        String teste_to_string = meu_cliente.toString();
        System.out.println(teste_to_string);

        // Teste Seguradora
        System.out.println("Teste Seguradora");
        System.out.println("Telefone: " + minha_seguradora.getTelefone());
        System.out.println("Nome antigo: " + minha_seguradora.getNome());
        minha_seguradora.setNome("MegaHiperSegura.com.br");
        System.out.println("Nome novo: " + minha_seguradora.getNome());
        System.out.println();

        // Teste Veiculo
        System.out.println("Teste Veiculo");
        System.out.println("Marca: " + meu_veiculo.getMarca());
        System.out.println("Placa antiga: " + meu_veiculo.getPlaca());
        meu_veiculo.setPlaca("XYZ-3344");
        System.out.println("Placa antiga: " + meu_veiculo.getPlaca());
        System.out.println();

        // Teste Sinistro
        System.out.println("Teste Sinistro");
        System.out.println("Id e endereco primeiro sinistro: " + meu_sinistro1.getId() + " - " + meu_sinistro1.getEndereco());
        System.out.println("Id e endereco segundo sinistro: " + meu_sinistro2.getId() + " - " + meu_sinistro2.getEndereco());
        entrada.close();
    }
}
