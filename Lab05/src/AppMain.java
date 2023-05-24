import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class AppMain {
    
    private static ArrayList<Seguradora> listaSeguradoras;
    
    //Adiciona uma seguradora na lista de seguradoras do programa e retorna seu numero/posicao.
    private static int addSeguradora(String CNPJ, String nome, String telefone, String email, String endereco) {
        
        Seguradora seg = new Seguradora(CNPJ, nome, telefone, email, endereco);
        listaSeguradoras.add(seg);
        return listaSeguradoras.size();
    }
    
    private static int addSeguradora(Seguradora seg) {

        listaSeguradoras.add(seg);
        return listaSeguradoras.size();
    }
    
    //Retorna a referencia a uma seguradora com base na sua posicao i (indice i - 1).
    private static Seguradora getSeg(int i) {
        if (i > listaSeguradoras.size())
            return null;
        return listaSeguradoras.get(i - 1);
    }
    
    /*main com objetivo de teste das classes e seus metodos*/
    private static void TesteMain() {
        
        Scanner entrada = new Scanner(System.in);
        listaSeguradoras = new ArrayList<Seguradora>();
        
        //Instanciacoes
        
        Seguradora minhaSeg = new Seguradora("94.768.278/0001-57", "HiperSegura.com", "(11)91234-5678", 
                                             "hiper@seguros.com", "Av._Erico_Verissimo,50");
        ClientePF clien1 = new ClientePF("Vitor", "65654-5465", "Rua_legal", "sim@gmail.com", "668.467.025-47",
                                         "Masculino", "Ensino_superior", LocalDate.parse("2004-01-01"));
        ClientePJ clien2 = new ClientePJ("Empresa1", "3165-4564", "Rua_dinheiro", "nao@gmail.com",
                                         "82.846.568/0001-92", LocalDate.parse("2010-07-07"), 40);
        ClientePJ clien3 = new ClientePJ("Empresa2", "7987-9879", "Rua_arvores", "talvez@gmail.com",
                                         "17.398.731/0001-30", LocalDate.parse("2009-01-01"), 100);
        
        Veiculo vei1 = new Veiculo("123546", "fiat", "argo", 2010);
        Veiculo vei2 = new Veiculo("165498", "tesla", "truck", 2009);
        Veiculo vei3 = new Veiculo("275892", "fiat", "mobi", 2020);
        Veiculo vei4 = new Veiculo("654656", "tesla", "super", 2022);

        
        //Cadastrando cada um:
        
        addSeguradora(minhaSeg);
        
        clien1.cadastrarVeiculo(vei1);
        clien2.cadastrarFrota("frota_patrulha", vei2, vei3);
        clien3.cadastrarFrota("frota_manutencao", vei4);
        
        minhaSeg.cadastrarCliente(clien1);
        minhaSeg.cadastrarCliente(clien2);
        minhaSeg.cadastrarCliente(clien3);
        
        
        //Gerando seguros:
        
        minhaSeg.gerarSeguroPF(LocalDate.parse("2009-01-01"), LocalDate.parse("2027-01-01"),
                               "668.467.025-47", "123546");
        minhaSeg.gerarSeguroPJ(LocalDate.parse("2009-01-01"), LocalDate.parse("2027-01-01"),
                               "82.846.568/0001-92", clien2.getFrota("frota_patrulha"));
        minhaSeg.gerarSeguroPJ(LocalDate.parse("2009-01-01"), LocalDate.parse("2027-01-01"),
                               "17.398.731/0001-30", clien3.getFrota("frota_manutencao"));

        
        //Gerando condutores:
        
        Condutor con1 = new Condutor("577.993.840-77", "Joao", "6546-8787", "casa", "agora@gmail.com",
                                     LocalDate.parse("2000-01-01"));
        Condutor con2 = new Condutor("164.924.730-35", "Maria", "6878-4987", "predio", "antes@gmail.com",
                                     LocalDate.parse("2002-01-01"));
        
        Seguro seg1 = minhaSeg.getSegurosCliente("668.467.025-47").get(0);
        seg1.autorizarCondutor(con1);
        
        Seguro seg2 = minhaSeg.getSegurosCliente("82.846.568/0001-92").get(0);
        seg2.autorizarCondutor(con2);
        
        Seguro seg3 = minhaSeg.getSegurosCliente("17.398.731/0001-30").get(0);
        seg3.autorizarCondutor(con1);
        seg3.autorizarCondutor(con2);

        
        //Gerando sinistros:

        
        seg1.gerarSinistro(LocalDate.parse("2021-02-02"), "Rua_do_acidente", "577.993.840-77");
        seg2.gerarSinistro(LocalDate.parse("2019-08-08"), "Rua_do_acidente2", "164.924.730-35");
        seg3.gerarSinistro(LocalDate.parse("2017-09-09"), "Rua_do_acidente3", "577.993.840-77");
        
        seg1.atualizarValorMensal();
        seg2.atualizarValorMensal();
        seg3.atualizarValorMensal();
        
        clien1.atualizarValorSeguro();
        clien2.atualizarValorSeguro();
        clien3.atualizarValorSeguro();
        
        //Teste de metodos
        
        System.out.println("Listando clientes:");
        minhaSeg.listarClientes();
        
        System.out.println("Listando seguros clien1:");
        minhaSeg.listarSegurosCliente(clien1.getCPF());
        minhaSeg.listarSegurosCliente(clien2.getCNPJ());
        minhaSeg.listarSegurosCliente(clien3.getCNPJ());
        
        System.out.println("Listando sinistros clien1");
        minhaSeg.listarSinistrosCliente(clien1.getCPF());
        
        System.out.println("Receita da seguradora:");
        System.out.println(minhaSeg.calcularReceita());
        
        System.out.println("Cancelando seguro do cliente 3:");
        minhaSeg.cancelarSeguro(clien3, seg3.getId());
        clien3.atualizarValorSeguro();
        
        System.out.println("Nova receita da seguradora:");
        System.out.println(minhaSeg.calcularReceita());
        
        System.out.println("Removendo o cliente 1:");
        minhaSeg.removerCliente(clien1.getCPF());
        
        System.out.println("Nova receita da seguradora:");
        System.out.println(minhaSeg.calcularReceita());
        
        entrada.close();
        
    }
    
    /*main com objetivo de teste das classes e seus metodos*/
    public static void main(String[] args) {
        
        TesteMain();
        
        Scanner entrada = new Scanner(System.in);
        
        /*Informacoes usadas no loop do programa*/

        System.out.println();
        System.out.println("------Inicio da entrada de dados do programa------");
        System.out.println();
        
        
        
        entrada.close();
        
        
    }
}