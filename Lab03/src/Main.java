import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    
    /*main com objetivo de teste das classes e seus metodos*/
    public static void main(String[] args) {
        
        Scanner entrada = new Scanner(System.in);
        
        /*Instanciacoes e testes iniciais*/
        //CPF e CNPJ validos gerados aleatoriamente para teste
        
        System.out.println("------Testes do programa------");
        Seguradora minha_seguradora = new Seguradora("HiperSegura.com", "(11)91234-5678",
                "hiper@seguros.com", "Av._Erico_Verissimo,50");

        minha_seguradora.cadastrarCliente("Vitor", "Rua_legal", "Ensino_superior", "Masculino", "Classe_media",
                "668.467.025-47", LocalDate.parse("2004-01-01"), LocalDate.parse("2028-09-09"));
        minha_seguradora.cadastrarCliente("Empresa1", "Rua_dinheiro", "82.846.568/0001-92", LocalDate.parse("2010-07-07"));
        minha_seguradora.cadastrarCliente("Empresa2", "Rua_arvores","17.398.731/0001-30", LocalDate.parse("2009-01-01"));;
        
        
        minha_seguradora.listarClientes();
        System.out.println(ClientePF.validarCPF(((ClientePF)minha_seguradora.getCliente("668.467.025-47")).getCPF()));
        System.out.println(ClientePF.validarCPF("111.111.111-11"));
        System.out.println(ClientePJ.validarCNPJ("11.111.111/1111-11"));
        
        minha_seguradora.getCliente("668.467.025-47").cadastrarVeiculo("123546", "fiat", "argo", 2010);
        minha_seguradora.getCliente("82.846.568/0001-92").cadastrarVeiculo("165498", "tesla", "truck", 2009);
        minha_seguradora.getCliente("17.398.731/0001-30").cadastrarVeiculo("165892", "fiat", "mobi", 2020);
        
        minha_seguradora.removerCliente("17.398.731/0001-30");
        
        minha_seguradora.gerarSinistro(LocalDate.parse("2021-02-02"), "Rua_do_acidente", "123546", "668.467.025-47");
        
        /* toString dos carros esta contido no toString do clientePF e clientePJ
         * e o toString dos sinistros esta contido em Seguradora.listarSinistros.
         */
        System.out.println();
        System.out.println(minha_seguradora.toString());
        System.out.println(((ClientePF)minha_seguradora.getCliente("668.467.025-47")).toString());
        
        minha_seguradora.listarSinistros();
        minha_seguradora.visualizarSinistro("668.467.025-47");
        minha_seguradora.listarClientes();
        
        /*Informacoes usadas no loop do programa*/
        
        System.out.println();
        System.out.println("------Inicio da entrada de dados do programa------");
        System.out.println();
        
        boolean flag = true;
        int comando;
        ArrayList<String> param;
        String comandos_principais = "1 - Cadastrar ou remover cliente PF\n2 - Cadastrar ou remover cliente PJ\n"
                              + "3 - Cadastrar ou remover veiculo\n4 - Gerar ou visualizar sinistro\n"
                              + "5 - Listar clientes/sinistros/veiculos\n6 - Sair\n";
        String comando2 = "1 - Cadastrar\n2 - Remover";
        String comando3 = "1 - Gerar\n2 - Visualizar";
        String comando4 = "Listar:\n1 - Clientes\n2 - Sinistros\n3 - Veiculos";
        String aviso = "Todos os campos devem ser compostos de apenas uma palavra. Use Underlines '_' para separar "
                     + "nomes, como em 'Joao_da_Silva'. Datas sao no formato AAAA-MM-DD";
        
        /*Loop do programa com comandos*/
        while (flag) {
            
            System.out.println(comandos_principais + aviso);
            param = new ArrayList<String>();
            comando = entrada.nextInt();
            
            switch (comando) {
                
            case 1: //Cadastrar ou remover cliente PF
                
                System.out.println(comando2);
                param.add(entrada.next());
                
                if (param.get(0).equals("1")) { //Cadastrar
                    
                    System.out.println("Digite nessa sequencia: nome, endereco, educacao, genero, classe economica, "
                            + "CPF, data de nascimento e data da licenca.");
                    for (int i = 0; i < 8; i++)
                        param.add(entrada.next());
                    
                    boolean flag_sucesso = minha_seguradora.cadastrarCliente(param.get(1), param.get(2),
                            param.get(3), param.get(4), param.get(5), param.get(6), LocalDate.parse(param.get(7)), 
                            LocalDate.parse(param.get(8)));
                    
                    if (!flag_sucesso) {
                        System.out.println("Cliente ja existente ou CPF invalido");
                    } else {
                        System.out.println("Cliente cadastrado com sucesso");
                    }
                    
                } else if (param.get(0).equals("2")) { //Remover
                    
                    System.out.println("Digite o CPF");
                    boolean flag_sucesso = minha_seguradora.removerCliente(entrada.next());
                    
                    if (!flag_sucesso) {
                        System.out.println("Cliente nao existente ou CPF invalido");
                    } else {
                        System.out.println("Cliente removido com sucesso");
                    }
                }
                
                break;
                
                
            case 2: //Cadastrar ou remover cliente PJ
                
                System.out.println(comando2);
                param.add(entrada.next());
                
                if (param.get(0).equals("1")) { //Cadastrar
                    
                    System.out.println("Digite nessa sequencia: nome, endereco, CNPJ e data de fundacao.");
                    
                    for (int i = 0; i < 4; i++)
                        param.add(entrada.next());
                    
                    boolean flag_sucesso = minha_seguradora.cadastrarCliente(param.get(1), param.get(2),
                            param.get(3), LocalDate.parse(param.get(4)));
                    
                    if (!flag_sucesso) {
                        System.out.println("Cliente ja existente ou CNPJ invalido");
                    } else {
                        System.out.println("Cliente cadastrado com sucesso");
                    }
                    
                } else if (param.get(0).equals("2")) { //Remover
                    
                    System.out.println("Digite o CPF");
                    boolean flag_sucesso = minha_seguradora.removerCliente(entrada.next());
                    
                    if (!flag_sucesso) {
                        System.out.println("Cliente nao existente ou CPF invalido");
                    } else {
                        System.out.println("Cliente removido com sucesso");
                    }
                }
                
                break;
                
            case 3: //Cadastrar ou remover veiculo
                
                System.out.println(comando2);
                param.add(entrada.next());
                
                if (param.get(0).equals("1")) { //Cadastrar
                    
                    System.out.println("Digite nessa sequencia: CPF/CNPJ do cliente, placa, marca, modelo e "
                                     + "ano de fabricacao do veiculo.");
                    
                    for (int i = 0; i < 5; i++)
                        param.add(entrada.next());
                    
                    Cliente clien = minha_seguradora.getCliente(param.get(1));
                    
                    if (clien == null) {
                        System.out.println("Cliente nao existente ou CPF/CNPJ invalido");
                        break;
                    }
                    
                    boolean flag_sucesso = clien.cadastrarVeiculo(param.get(2), param.get(3), param.get(4),
                                                          Integer.parseInt(param.get(5)));
                    if (!flag_sucesso) {
                        System.out.println("Veiculo ja existente");
                    } else {
                        System.out.println("Veiculo cadastrado com sucesso");
                    }
                    
                } else if (param.get(0).equals("2")) { //Remover
                    
                    System.out.println("Digite nessa sequencia: CPF/CNPJ do cliente e placa do veiculo.");
                    
                    for (int i = 0; i < 1; i++)
                        param.add(entrada.next());
                    
                    Cliente clien = minha_seguradora.getCliente(param.get(1));
                    
                    if (clien == null) {
                        System.out.println("Cliente nao existente ou CPF/CNPJ invalido");
                        break;
                    }
                    
                    boolean flag_sucesso = clien.removerVeiculo(param.get(2));
                    if (!flag_sucesso) {
                        System.out.println("Veiculo nao existente");
                    } else {
                        System.out.println("Veiculo removido com sucesso");
                    }
                }
                
                break;
                
                
            case 4: //Gerar ou visualizar sinistro
                
                System.out.println(comando3);
                param.add(entrada.next());
                
                if (param.get(0).equals("1")) { //Gerar
                    System.out.println("Digite nessa sequencia: data, endereco, placa e CPF/CNPJ do cliente");
                    
                    for (int i = 0; i < 4; i++)
                        param.add(entrada.next());
                    
                    boolean flag_sucesso = minha_seguradora.gerarSinistro(LocalDate.parse(param.get(1)),
                            param.get(2), param.get(3), param.get(4));
                    
                    if (!flag_sucesso) {
                        System.out.println("Cliente nao existente, ou CPF/CNPJ invalido, "
                                         + "ou carro nao pertence a esse cliente");
                    } else {
                        System.out.println("Sinistro gerado com sucesso");
                    }
                
                } else if (param.get(0).equals("2")) { //Visualizar
                    
                    System.out.println("Digite o CPF/CNPJ");
                    boolean flag_sucesso = minha_seguradora.visualizarSinistro(entrada.next());
                    
                    if (!flag_sucesso)
                        System.out.println("Cliente nao existente ou CPF/CNPJ invalido");
                }
                
                break;
                
            case 5: // Listar clientes/sinistros/veiculos
                
                System.out.println(comando4);
                param.add(entrada.next());
                
                if (param.get(0).equals("1")) {        //Listar clientes
                    
                    minha_seguradora.listarClientes();
                    
                } else if (param.get(0).equals("2")) { //Listar sinistros
                    
                    minha_seguradora.listarSinistros();
                    
                } else if (param.get(0).equals("3")) { //Listar veiculos
                    
                    System.out.println("Digite o CPF/CNPJ");
                    Cliente clien = minha_seguradora.getCliente(entrada.next());
                    
                    if (clien == null) {
                        System.out.println("Cliente nao existente ou CPF/CNPJ invalido");
                        break;
                    }
                    
                    clien.listarVeiculos();
                } else {
                    System.out.println("Comando invalido.");
                }
                
                break;
            
            case 6: //Sair
                flag = false;
                break;
                
            default:
                System.out.println("Comando invalido.");
            }
        }
        entrada.close();
    }
}
