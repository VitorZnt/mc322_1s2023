import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class Antiga {
    
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
    
    //Retorna a referencia a uma seguradora com base na sua posicao i (indice i - 1).
    private static Seguradora getSeg(int i) {
        if (i > listaSeguradoras.size())
            return null;
        return listaSeguradoras.get(i - 1);
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
        String comandos_principais = "Menu:\n1 - Cadastros\n2 - Listar\n3 - Excluir\n4 - Gerar sinistro\n"
                                   + "5 - Transferir seguro\n6 - Calcular receita seguradora\n0 - Sair\n";
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
            Cliente clien;
            
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
                    
                    flag_sucesso = Validacao.validarDadosPF(param.get(6), param.get(1), LocalDate.parse(param.get(7)));
                    
                    if (!flag_sucesso) {
                        System.out.println("Nome, CPF ou data de nascimento invalidos");
                        break;
                    }
                    
                    ClientePF clienPf = new ClientePF(param.get(1), param.get(2), param.get(3), param.get(4), param.get(5),
                                          param.get(6), LocalDate.parse(param.get(7)), LocalDate.parse(param.get(8)));
                    flag_sucesso = seg.cadastrarCliente(clienPf);
                    seg.atualizarPrecoClien(clienPf);
                    
                    if (!flag_sucesso)
                        System.out.println("Cliente ja existente");
                    else
                        System.out.println("Cliente cadastrado com sucesso");
                    break;
                
                    
                    
                    
                case CADASTRAR_PJ:
                    System.out.println("Digite nessa sequencia: num. da seguradora, nome, endereco, CNPJ, "
                                     + "data de fundacao e qtd funcionarios.");
                    
                    for (int j = 0; j < 6; j++)
                        param.add(entrada.next());
                    seg = getSeg(Integer.parseInt(param.get(0)));
                    
                    if (seg == null) {
                        System.out.println("Seguradora nao encontrada");
                        break;
                    }
                    
                    flag_sucesso = Validacao.validarDadosPJ(param.get(3), param.get(1));
                    
                    if (!flag_sucesso) {
                        System.out.println("Nome ou CNPJ invalidos");
                        break;
                    }
                        
                    ClientePJ clienPj = new ClientePJ(param.get(1), param.get(2), param.get(3),
                                       LocalDate.parse(param.get(4)), Integer.parseInt(param.get(5)));
                    flag_sucesso = seg.cadastrarCliente(clienPj);
                    seg.atualizarPrecoClien(clienPj);
                    
                    if (!flag_sucesso)
                        System.out.println("Cliente ja existente");
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
                   
                   clien = seg.getCliente(param.get(1));
                   if (clien == null) {
                       System.out.println("Cliente nao existente ou CPF/CNPJ invalido");
                       break;
                   }
                   flag_sucesso = clien.cadastrarVeiculo(param.get(2), param.get(3), param.get(4),
                                                         Integer.parseInt(param.get(5)));
                   seg.atualizarPrecoClien(clien);
                   
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
                break;
                
                
                
                
            
            case LISTAR:
                System.out.println(subcomandos_2);
                k = entrada.nextInt();
                comando2 = MenuOperacoes.getOperacaoPorCodigo(10*i + k);
                
                if (comando2.equals(MenuOperacoes.VOLTAR2))
                    break;
                
                System.out.println("Digite o num. da seguradora");
                seg = getSeg(Integer.parseInt(entrada.next()));
                if (seg == null) {
                    System.out.println("Seguradora nao encontrada");
                    break;
                }
                
                switch (comando2) {
                
                
                
                case LISTAR_CLIENTES_SEGURADORA:
                    seg.listarClientes();
                    break;
                    
                    
                    
                    
                case LISTAR_SINISTROS_SEGURADORA:
                    seg.listarSinistros();
                    break;
                    
                    
                    
                case LISTAR_VEICULOS_SEGURADORA:
                    seg.listarVeiculos();
                    break;
                    
                    
                    
                    
                case LISTAR_SINISTROS_CLIENTE:
                    System.out.println("Digite o CPF/CNPJ do cliente");
                    flag_sucesso = seg.visualizarSinistros(entrada.next());
                    break;
                    
                    
                    
                case LISTAR_VEICULOS_CLIENTE:
                    System.out.println("Digite o CPF/CNPJ do cliente");
                    clien = seg.getCliente(entrada.next());
                    if (clien == null) {
                        System.out.println("Cliente nao existente ou CPF/CNPJ invalido");
                        break;
                    }
                    System.out.println(clien.listarVeiculos());
                    break;
                    
                    
                default:
                    System.out.println("Comando invalido.");
                    break;
                }
                break;
                
                
            case EXCLUIR:
                System.out.println(subcomandos_3);
                k = entrada.nextInt();
                comando2 = MenuOperacoes.getOperacaoPorCodigo(10*i + k);
                
                if (comando2.equals(MenuOperacoes.VOLTAR3))
                    break;
                
                System.out.println("Digite o num. da seguradora:");
                seg = getSeg(Integer.parseInt(entrada.next()));
                
                if (seg == null) {
                    System.out.println("Seguradora nao encontrada");
                    break;
                }
                
                switch (comando2) {
                
                
                
                case EXCLUIR_CLIENTE:
                    
                    System.out.println("Digite o CPF/CNPJ do cliente");
                    flag_sucesso = seg.removerCliente(entrada.next());
                    
                    if (!flag_sucesso)
                        System.out.println("Cliente nao existente ou CPF/CNPJ invalido");
                    else
                        System.out.println("Cliente removido com sucesso");
                    break;
                    
                    
                    
                    
                case EXCLUIR_VEICULO:
                    System.out.println("Digite o CPF/CNPJ do cliente e placa do veiculo.");
                    
                    for (int j = 0; j < 2; j++)
                        param.add(entrada.next());
                    
                    clien = seg.getCliente(param.get(0));
                    
                    if (clien == null) {
                        System.out.println("Cliente nao existente ou CPF/CNPJ invalido");
                        break;
                    }
                    
                    flag_sucesso = clien.removerVeiculo(param.get(1));
                    seg.atualizarPrecoClien(clien);
                    if (!flag_sucesso)
                        System.out.println("Veiculo nao existente");
                    else
                        System.out.println("Veiculo removido com sucesso");
                    break;
                    
                    
                    
                    
                case EXCLUIR_SINISTRO:
                    System.out.println("Digite o id do sinistro.");
                    clien = seg.removerSinistro(Integer.parseInt(entrada.next()));
                    seg.atualizarPrecoClien(clien);
                    if (clien == null)
                        System.out.println("Sinistro nao existente");
                    else {
                        System.out.println("Sinistro removido com sucesso");
                        seg.atualizarPrecoClien(clien);
                    }
                    break;
                    
                    
                default:
                    System.out.println("Comando invalido.");
                    break;
                
                }
                break;
                
                
            case GERAR_SINISTRO:
                
                System.out.println("Digite o num. da seguradora");
                seg = getSeg(Integer.parseInt(entrada.next()));
                if (seg == null) {
                    System.out.println("Seguradora nao encontrada");
                    break;
                }
                
                System.out.println("Digite nessa sequencia: data, endereco, placa e CPF/CNPJ do cliente");
                
                for (int j = 0; j < 4; j++)
                    param.add(entrada.next());
                
                flag_sucesso = seg.gerarSinistro(LocalDate.parse(param.get(0)),
                        param.get(1), param.get(2), param.get(3));
                
                if (!flag_sucesso) {
                    System.out.println("Cliente nao existente, ou CPF/CNPJ invalido, "
                                     + "ou carro nao pertence a esse cliente");
                } else {
                    System.out.println("Sinistro gerado com sucesso");
                    seg.atualizarPrecoClien(seg.getCliente(param.get(3)));
                }
                break;
                
                
                
            case TRANSFERIR_SEGURO:
                System.out.println("Digite o num. da seguradora");
                seg = getSeg(Integer.parseInt(entrada.next()));
                if (seg == null) {
                    System.out.println("Seguradora nao encontrada");
                    break;
                }
                
                System.out.println("Digite o CPF/CNPJ do cliente origem e do cliente destinatario, nesta ordem:");
                for (int j = 0; j < 2; j++)
                    param.add(entrada.next());
                
                flag_sucesso = seg.transferirSeguro(param.get(0), param.get(1));
                if (!flag_sucesso) {
                    System.out.println("Um dos clientes (ou ambos) nao existente, ou CPF/CNPJ invalido.");
                } else {
                    seg.atualizarPrecoClien(seg.getCliente(param.get(0)));
                    seg.atualizarPrecoClien(seg.getCliente(param.get(1)));
                    System.out.println("Transferencia realizada com sucesso");
                }
                break;
                
                
            case CALCULAR_RECEITA_SEGURADORA:
                System.out.println("Digite o num. da seguradora");
                seg = getSeg(Integer.parseInt(entrada.next()));
                if (seg == null) {
                    System.out.println("Seguradora nao encontrada");
                    break;
                }
                System.out.println(String.format("A receita da seguradora eh de %f", seg.calcularReceita()));
                break;
                
            case SAIR:
                break;
                
            default:
                System.out.println("Comando invalido.");
                break;
                
            }
        }
        entrada.close();
    }
}
