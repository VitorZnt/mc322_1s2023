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
        System.out.println("Listando seguros clien2:");
        minhaSeg.listarSegurosCliente(clien2.getCNPJ());
        System.out.println("Listando seguros clien3:");
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
    
    private static String subcomandos(int i) {
        
        String aviso = "Todos os campos devem ser compostos de apenas uma palavra. Use Underlines '_' para separar "
                + "nomes, como em 'Joao_da_Silva'. Datas sao no formato AAAA-MM-DD";
        String comandos_principais = "Menu:\n1 - Cadastros\n2 - Atualizar frota\n3 - Gerar sinistro/seguro\n"
                + "4 - Autorizar/desautorizar condutor\n5 - Listar infos\n6 - Cancelar seguro\n"
                + "7 - Remover cliente, veiculo ou frota\n8 - Calcular receita seguradora\n0 - Sair\n";
        String subcomandos_1 = "Cadastrar:\n1 - Cliente PF\n2 - Cliente PJ\n3 - Veiculo para cliente PF\n"
                + "4 - Frota para cliente PJ\n5 - Seguradora\n6 - Voltar";
        String subcomandos_2 = "1 - Adicionar veiculo frota\n2 - Remover veiculo frota\n3 - Remover frota\n4 - Voltar\n";
        String subcomandos_3 = "Gerar:\n1 - Sinistro\n2 - Seguro PF\n3 - Seguro PJ\n4 - Voltar\n";
        String subcomandos_4 = "1 - Autorizar\n2 - Desautorizar\n3 - Voltar";
        String subcomandos_5 = "Listar:\n1 - Cliente\n2 - Seguros do cliente\n3 - Sinistros do cliente\n"
                + "4 - Veiculos do cliente PF\n5 - Frotas do cliente PJ\n6 - Voltar";
        String subcomandos_6 = "";
        String subcomandos_7 = "Remover:\n1 - Cliente\n2 - Veiculo\n3 - Frota\n4 - Voltar";
        String subcomandos_8 = ""; 
        String comando_invalido = "Comando invalido\n";
        
        switch (i) {
        case -1:
            return aviso;
        case 0:
            return comandos_principais;
        case 1:
            return subcomandos_1;
        case 2:
            return subcomandos_2;
        case 3:
            return subcomandos_3;
        case 4:
            return subcomandos_4;
        case 5:
            return subcomandos_5;
        case 6:
            return subcomandos_6;
        case 7:
            return subcomandos_7;
        case 8:
            return subcomandos_8;
        case 9:
            return comando_invalido;
        default:
            return "";
        }
    }
    
    /*main com objetivo de teste das classes e seus metodos*/
    public static void main(String[] args) {
        
        TesteMain();
        
        Scanner entrada = new Scanner(System.in);
        
        /*Informacoes usadas no loop do programa*/

        System.out.println();
        System.out.println("------Inicio da entrada de dados do programa------");
        System.out.println();
        
        
        boolean flag = true;
        ArrayList<String> param;

        
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