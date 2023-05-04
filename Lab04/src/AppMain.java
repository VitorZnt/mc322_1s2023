import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class AppMain {
    
    private static ArrayList<Seguradora> listaSeguradoras;
    
    //Adiciona uma seguradora na lista de seguradoras do programa e retorna seu numero/posicao.
    private static int addSeg(String nome, String telefone, String email, String endereco) {
        
        Seguradora seg = new Seguradora(nome, telefone, email, endereco);
        listaSeguradoras.add(seg);
        return listaSeguradoras.size();
    }
    
    private static int addSeg(Seguradora seg) {

        listaSeguradoras.add(seg);
        return listaSeguradoras.size();
    }
    
    //Remove uma seguradora i da lista de seguradoras do programa.
    private static boolean removeSeg(int i) {
        
        if(!listaSeguradoras.isEmpty() && i <= listaSeguradoras.size()) {
            listaSeguradoras.remove(i);
            return true;
        } else
            return false;
    }

    
    //Retorna a referencia a uma seguradora com base na sua posicao i.
    private static Seguradora getSeg(int i) {
        return listaSeguradoras.get(i);
    }
    
    /*main com objetivo de teste das classes e seus metodos*/
    public static void main(String[] args) {
        
        Scanner entrada = new Scanner(System.in);
        listaSeguradoras = new ArrayList<Seguradora>();
        
        //Instanciacoes
        
        Seguradora minhaSeg = new Seguradora("HiperSegura.com", "(11)91234-5678", "hiper@seguros.com", "Av._Erico_Verissimo,50");
        
        ClientePF clien1 = new ClientePF("Vitor", "Rua_legal", "Ensino_superior", "Masculino", "Classe_media",
                "668.467.025-47", LocalDate.parse("2004-01-01"), LocalDate.parse("2028-09-09"));
        ClientePJ clien2 = new ClientePJ("Empresa1", "Rua_dinheiro", "82.846.568/0001-92", LocalDate.parse("2010-07-07"), 40);
        ClientePJ clien3 = new ClientePJ("Empresa2", "Rua_arvores","17.398.731/0001-30", LocalDate.parse("2009-01-01"), 100);;
        
        Veiculo vei1 = new Veiculo("123546", "fiat", "argo", 2010);
        Veiculo vei2 = new Veiculo("165498", "tesla", "truck", 2009);
        Veiculo vei3 = new Veiculo("165892", "fiat", "mobi", 2020);
        
        //Cadastrando cada um:
        
        addSeg(minhaSeg);
        
        clien1.cadastrarVeiculo(vei1);
        clien2.cadastrarVeiculo(vei2);
        clien3.cadastrarVeiculo(vei3);
        
        minhaSeg.cadastrarCliente(clien1);
        minhaSeg.cadastrarCliente(clien2);
        minhaSeg.cadastrarCliente(clien3);
        
        minhaSeg.gerarSinistro(LocalDate.parse("2021-02-02"), "Rua_do_acidente", "123546", "668.467.025-47");
        minhaSeg.gerarSinistro(LocalDate.parse("2019-08-08"), "Rua_do_acidente2", "165892", "17.398.731/0001-30");
        minhaSeg.gerarSinistro(LocalDate.parse("2017-09-09"), "Rua_do_acidente3", "165498", "82.846.568/0001-92");
        
        //Atualizando o valor dos seguros
        
        minhaSeg.atualizarPrecoClien(clien1);
        minhaSeg.atualizarPrecoClien(clien2);
        minhaSeg.atualizarPrecoClien(clien3);
        
        //Teste de metodos
        
        System.out.println("Listando clientes:");
        minhaSeg.listarClientes();
        System.out.println("Listando sinistros");
        minhaSeg.listarSinistros();
        System.out.println("Visualizando sinistros do cliente clien1:");
        minhaSeg.visualizarSinistros("668.467.025-47");
        System.out.println("Receita da seguradora:");
        System.out.println(minhaSeg.calcularReceita());
        
        
        /*Informacoes usadas no loop do programa*/

        System.out.println();
        System.out.println("------Inicio da entrada de dados do programa------");
        System.out.println();
        
        boolean flag = true;
        ArrayList<String> param;
        String comandos_principais = "1 - Cadastros\n2 - Listar\n3 - Excluir\n4 - Gerar sinistro\n"
                                   + "5 - Transferir seguro\n6 - Calcular receita seguradora\n0 - Sair";
        String subcomandos_1 = "Cadastrar:\n1 - Cliente PF\n2 - Cliente PJ\n3 - Veiculo\n"
                        + "4 - Seguradora\n5 - Voltar";
        String subcomandos_2 = "Listar:\n1 - Clientes por seguradora\n2 - Sinistros por seguradora\n"
                        + "3 - Veiculos por seguradora\n4 - Sinistros por cliente\n"
                        + "5 - Veiculos por cliente\n6 - Voltar";
        String subcomandos_3 = "Excluir:\n1 - Cliente\n2 - Veiculo\n3 - Sinistro\n4 - Voltar";
        String aviso = "Todos os campos devem ser compostos de apenas uma palavra. Use Underlines '_' para separar "
                     + "nomes, como em 'Joao_da_Silva'. Datas sao no formato AAAA-MM-DD";
        
        /*Loop do programa com comandos*/
        while (flag) {
            
            System.out.println(comandos_principais + aviso);
            param = new ArrayList<String>();
            int i = entrada.nextInt();
            int k;
            MenuOperacoes comando = MenuOperacoes.getOperacaoPorCodigo(i);
            MenuOperacoes comando2;
            boolean flag_sucesso = true;
            Seguradora seg;
            
            switch (comando) {
            
            
            
            
            
            case CADASTRAR:
                System.out.println(subcomandos_1);
                k = entrada.nextInt();
                comando2 = MenuOperacoes.getOperacaoPorCodigo(10*i + k);
                
                
                switch (comando2) {
                
                case CADASTRAR_PF:
                    System.out.println("Digite nessa sequencia: num. da seguradora, nome, endereco, educacao, genero, "
                                     + "classe economica, CPF, data de nascimento e data da licenca.");
                    for (int j = 0; j < 9; j++)
                        param.add(entrada.next());
                    seg = getSeg(Integer.parseInt(param.get(0)));
                    
                    if (seg == null) {
                        System.out.println("Seguradora nao encontrada");
                        break;
                    }
                    
                    flag_sucesso = minhaSeg.cadastrarCliente(param.get(1), param.get(2),
                            param.get(3), param.get(4), param.get(5), param.get(6), LocalDate.parse(param.get(7)), 
                            LocalDate.parse(param.get(8)));
                    
                    if (!flag_sucesso)
                        System.out.println("Cliente ja existente ou CPF invalido");
                    else
                        System.out.println("Cliente cadastrado com sucesso");
                    break;
                
                    
                    
                case CADASTRAR_PJ:
                    System.out.println("Digite nessa sequencia: num. da seguradora, nome, endereco, CNPJ, "
                                     + "data de fundacao e qtd funcionarios.");
                    
                    for (int j = 0; j < 5; j++)
                        param.add(entrada.next());
                    seg = getSeg(Integer.parseInt(param.get(0)));
                    
                    if (seg == null) {
                        System.out.println("Seguradora nao encontrada");
                        break;
                    }
                    
                    flag_sucesso = minhaSeg.cadastrarCliente(param.get(1), param.get(2),
                            param.get(3), LocalDate.parse(param.get(4)), Integer.parseInt(param.get(5)));
                    
                    if (!flag_sucesso)
                        System.out.println("Cliente ja existente ou CNPJ invalido");
                    else
                        System.out.println("Cliente cadastrado com sucesso");
                    break;  
                
                    
                    
                case CADASTRAR_VEICULO:
                    System.out.println("Digite nessa sequencia: num. da seguradora, CPF/CNPJ do cliente, placa, marca,"
                                     + "modelo e ano de fabricacao do veiculo.");
                   
                   for (int j = 0; j < 6; j++)
                       param.add(entrada.next());
                   seg = getSeg(Integer.parseInt(param.get(0)));
                   
                   if (seg == null) {
                       System.out.println("Seguradora nao encontrada");
                       break;
                   }
                   
                   Cliente clien = seg.getCliente(param.get(1));
                   
                   if (clien == null) {
                       System.out.println("Cliente nao existente ou CPF/CNPJ invalido");
                       break;
                   }
                   
                   flag_sucesso = clien.cadastrarVeiculo(param.get(2), param.get(3), param.get(4),
                                                         Integer.parseInt(param.get(5)));
                   if (!flag_sucesso)
                       System.out.println("Veiculo ja existente");
                   else
                       System.out.println("Veiculo cadastrado com sucesso");
                   break;
                
                   
                   
                case CADASTRAR_SEGURADORA:
                    System.out.println("Digite nessa sequencia: nome, telefone, email e endereco.");
                    for (int j = 0; j < 4; j++)
                        param.add(entrada.next());
                    for (Seguradora aux : listaSeguradoras)
                        if (aux.getNome().equals(param.get(0))) {
                            System.out.println("Seguradora ja existente");
                            flag_sucesso = false;
                        }
                    if (!flag_sucesso)
                        break;
                    
                    int num = addSeg(param.get(0), param.get(1), param.get(2), param.get(3));
                    System.out.println(String.format("Seguradora criada na posicao %d", num));
                    break;
                    
                    
                    
                case VOLTAR1:
                    break;
                default:
                    System.out.println("Comando invalido.");
                    break;
                }
                
                
                
                
                
            
            case LISTAR:
                System.out.println(subcomandos_2);
                k = entrada.nextInt();
                comando2 = MenuOperacoes.getOperacaoPorCodigo(10*i + k);
                
                switch (comando2) {
                
                
                
                case LISTAR_CLIENTES_SEGURADORA:
                    
                    
                    
                    
                    
                case LISTAR_SINISTROS_SEGURADORA:
                    
                    
                    
                    
                case LISTAR_VEICULOS_SEGURADORA:
                    
                    
                    
                    
                    
                case LISTAR_SINISTROS_CLIENTE:
                    System.out.println("Digite o CPF/CNPJ");
                    flag_sucesso = minhaSeg.visualizarSinistros(entrada.next());
                    
                    if (!flag_sucesso)
                        System.out.println("Cliente nao existente ou CPF/CNPJ invalido");
                    break;
                    
                    
                    
                case LISTAR_VEICULOS_CLIENTE:
                    
                    
                    
                    
                case VOLTAR2:
                    
                default:
                
                }
            
            case EXCLUIR:
                System.out.println(subcomandos_3);
                k = entrada.nextInt();
                comando2 = MenuOperacoes.getOperacaoPorCodigo(10*i + k);
                
                switch (comando2) {
                
                case EXCLUIR_CLIENTE:
                    
                    System.out.println("Digite o num. da seguradora e o CPF/CNPJ do cliente");
                    flag_sucesso = minhaSeg.removerCliente(entrada.next());
                    
                    if (!flag_sucesso)
                        System.out.println("Cliente nao existente ou CPF/CNPJ invalido");
                    else
                        System.out.println("Cliente removido com sucesso");
                    break;
                    
                case EXCLUIR_VEICULO:
                    System.out.println("Digite nessa sequencia: CPF/CNPJ do cliente e placa do veiculo.");
                    
                    for (int j = 0; j < 1; j++)
                        param.add(entrada.next());
                    
                    Cliente clien = minhaSeg.getCliente(param.get(1));
                    
                    if (clien == null) {
                        System.out.println("Cliente nao existente ou CPF/CNPJ invalido");
                        break;
                    }
                    
                    flag_sucesso = clien.removerVeiculo(param.get(2));
                    if (!flag_sucesso)
                        System.out.println("Veiculo nao existente");
                    else
                        System.out.println("Veiculo removido com sucesso");
                    break;
                    
                    
                case EXCLUIR_SINISTRO:
                    
                case VOLTAR3:
                    
                default:
                
                }
            
            case GERAR_SINISTRO:
                System.out.println("Digite nessa sequencia: data, endereco, placa e CPF/CNPJ do cliente");
                
                for (int j = 0; j < 4; j++)
                    param.add(entrada.next());
                
                flag_sucesso = minhaSeg.gerarSinistro(LocalDate.parse(param.get(0)),
                        param.get(1), param.get(2), param.get(3));
                
                if (!flag_sucesso) {
                    System.out.println("Cliente nao existente, ou CPF/CNPJ invalido, "
                                     + "ou carro nao pertence a esse cliente");
                } else
                    System.out.println("Sinistro gerado com sucesso");
                
                
            case TRANSFERIR_SEGURO:
                
                
                
            case CALCULAR_RECEITA_SEGURADORA:
                
                
                
            case SAIR:
                
                
                
            default:
                
                
            }
            
            
            

            
            
            
            
            
            
            
            
            
                
                
                
            /*
            case 4: Gerar ou visualizar sinistro
                
                
                } else if (param.get(0).equals("2")) { Visualizar
                    
                    System.out.println("Digite o CPF/CNPJ");
                    flag_sucesso = minhaSeg.visualizarSinistros(entrada.next());
                    
                    if (!flag_sucesso)
                        System.out.println("Cliente nao existente ou CPF/CNPJ invalido");
                }
                
                break;
                
            case 5: Listar clientes/sinistros/veiculos
                
                System.out.println(comando4);
                param.add(entrada.next());
                
                if (param.get(0).equals("1")) {        Listar clientes
                    
                    minhaSeg.listarClientes();
                    
                } else if (param.get(0).equals("2")) { Listar sinistros
                    
                    minhaSeg.listarSinistros();
                    
                } else if (param.get(0).equals("3")) {Listar veiculos
                    
                    System.out.println("Digite o CPF/CNPJ");
                    Cliente clien = minhaSeg.getCliente(entrada.next());
                    
                    if (clien == null) {
                        System.out.println("Cliente nao existente ou CPF/CNPJ invalido");
                        break;
                    }
                    
                    clien.listarVeiculos();
                } else {
                    System.out.println("Comando invalido.");
                }
                
                break;
            
            case SAIR:
                flag = false;
                break;
                
            default:
                System.out.println("Comando invalido.");
        }
        }
        */
        }
        entrada.close();
    }
}
