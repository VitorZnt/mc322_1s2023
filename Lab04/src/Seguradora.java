import java.time.LocalDate;
import java.util.ArrayList;

public class Seguradora {
    
    // Atributos de instancia
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Cliente> listaClientes;
    
    
    // Construtor
    public Seguradora(String nome, String telefone, String email, String endereco) {
        
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        listaSinistros = new ArrayList<Sinistro>();
        listaClientes = new ArrayList<Cliente>();
    }

    // Metodos de Get e Set
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    //Recebe um CPF/CNPJ e retorna o objeto Cliente procurado. Retorna NULL caso nao encontrado.
    public Cliente getCliente(String num) {
        int i_cliente = buscarCliente(num);
        boolean flag_sucesso = (i_cliente == -1? false : true);
        
        if (!flag_sucesso) 
            return null;
        
        return listaClientes.get(i_cliente);
    }
    
    
    //Metodos especificos
    
    
    
    /* Busca um cliente de num CPF/CNPJ fornecidos. Se existir, retorna sua posicao na ArrayList.
     * Caso contrario, retorna -1 
     */
    private int buscarCliente(String num) {
        
        int num_len = num.length();
        Cliente aux;
        
        
        for (int i = 0; i < listaClientes.size(); i++) {
            aux = listaClientes.get(i);
            
            if (num_len == 14 && (aux instanceof ClientePF) && 
               ((ClientePF)aux).getCPF().equals(num)) { //usa casting para acessar atributos da subclasse
                
                return i;
            } else if (num_len == 18 && (aux instanceof ClientePJ) && 
               ((ClientePJ)aux).getCNPJ().equals(num)){
                
                return i;
            }
        }
        
        return -1;
    }
    
    /*Recebe o CPF/CNPJ de um cliente e mostra seus sinistros na tela. Caso
     * nao haja esse cliente ou nao tenha sinistros, imprime essas mensagens.
     */
    public boolean visualizarSinistros(String num) {
        
        Cliente clien = getCliente(num);
        if (clien == null) { //cliente nao existe
            System.out.println("Cliente inexistente.");
            return false;
        }
        
        int i = 0;
        for (Sinistro sini: listaSinistros) {
            if (sini.getCliente().equals(clien)) {
                i++;
                System.out.println(String.format("Sinistro %d:", i));
                System.out.println(sini.toString());
            }
            System.out.println();
        }
        if (i == 0) {
            System.out.println("Nao ha sinistros vinculados a esse cliente.");
            return false;
        }
        return true;
    }
    
    /*Recebe o CPF/CNPJ de um cliente e mostra seus veiculos na tela. Caso
     * nao haja esse cliente ou nao tenha veiculos, imprime essas mensagens.
     */
    public boolean visualizarVeiculos(String num) {
        
        Cliente clien = getCliente(num);
        if (clien == null) { //cliente nao existe
            System.out.println("Cliente inexistente.");
            return false;
        } else if (clien.getQtdVeiculos() == 0) {
            System.out.println("Nao ha veiculos vinculados a esse cliente.");
            return false;
        } else {
            System.out.println(clien.listarVeiculos());
        }
        return true;
    }
    
    //Imprime os sinistros e suas informacoes na tela
    public void listarSinistros() {
        
        int i = 0;
        for (Sinistro aux: listaSinistros) {
            i++;
            System.out.println(String.format("Sinistro %d:", i));
            System.out.println(aux.toString());
            System.out.println();
        }
    }
    
    //Imprime todos os clientes e suas informacoes
    public void listarClientes() {
        
        int i = 0;
        for (Cliente aux: listaClientes) {
            i++;
            System.out.println(String.format("Cliente %d:", i));
            if (aux instanceof ClientePF)
                System.out.println(((ClientePF)aux).toString());
            else
                System.out.println(((ClientePJ)aux).toString());
            System.out.println();
        }
    }
    
    //Imprime os veiculos da seguradora e suas informacoes na tela
    public void listarVeiculos() {
        
        for (Cliente aux : listaClientes)
            System.out.println(aux.listarVeiculos());
    }
    
    
    
    //Cadastra um cliente PF. Caso ja exista um mesmo cliente ja cadastrado, ou se o CPF for invalido, retorna false.
    public boolean cadastrarCliente(String nome, String endereco, String educacao, String genero, String classeEconomica,
                                      String CPF, LocalDate dataNascimento, LocalDate dataLicenca) {
        
        //Cliente ja existente ou CPF invalido
        if ((Validacao.validarCPF(CPF) == false) || (buscarCliente(CPF) != -1))
            return false;

        else {
            ClientePF novo = new ClientePF(nome, endereco, educacao, genero, classeEconomica, CPF, dataNascimento, dataLicenca);
            listaClientes.add(novo);
            return true;
        }
    }
    
    //Cadastra um cliente PJ. Caso ja exista um mesmo cliente ja cadastrado, ou se o CNPJ for invalido, retorna false.
    public boolean cadastrarCliente(String nome, String endereco, String CNPJ, LocalDate dataFundacao, int qtdFuncionarios) {
        
        //Cliente ja existente ou CNPJ invalido
        if ((Validacao.validarCNPJ(CNPJ) == false) || (buscarCliente(CNPJ) != -1))
            return false;
        
        else {
            ClientePJ novo = new ClientePJ(nome, endereco, CNPJ, dataFundacao, qtdFuncionarios);
            listaClientes.add(novo);
            return true;
        }
    }
    
    /*A partir de uma data, endereco, placa do veiculo e CPF/CNPJ, cria um sinistro e o adiciona na lista.
     *Caso o veiculo nao pertenca ao cliente, ou cliente nao existe, retorna false.
     */
    public boolean gerarSinistro(LocalDate data, String endereco, String placa, String num) {
        
        Cliente clien = getCliente(num);
        if (clien == null) //cliente nao existe
            return false;
        
        int indice_veic = clien.buscarVeiculo(placa);
        if (indice_veic == -1) //veiculo nao pertence ao cliente
            return false;
        
        Veiculo veic = clien.getVeiculo(indice_veic);
        Sinistro sini = new Sinistro(data, endereco, this, veic, clien);
        listaSinistros.add(sini);
        clien.addSinistro();
        return true;
    }
    
    
    
    //Recebe o ID de um sinistro e tenta remove-lo, retornando true em caso de sucesso.
    public boolean removerSinistro(int id) {
        
        for (int i = 0; i < listaSinistros.size(); i++) {
            Sinistro aux = listaSinistros.get(i);
            if (aux.getId() == id) {
                listaSinistros.get(i).getCliente().remSinistro();
                listaSinistros.remove(i);
                return true;
            }
        }
        return false;
    }
    
    //Recebe um cliente existente e remove todos os seus sinistros da lista.
    private void removerSinistrosCliente(Cliente clien) {
        Sinistro sini;
        for (int i = listaSinistros.size() - 1; i >= 0; i--) {
            sini = listaSinistros.get(i);
            if (sini.getCliente().equals(clien)) {
                listaSinistros.remove(i);
                clien.remSinistro();
            }
        }
        return;
    }
    
    //Recebe o CPF/CNPJ do cliente a ser removido. Retorna true caso haja sucesso, ou false se ele nao existir
    public boolean removerCliente(String num) {
        
        int i_clien = buscarCliente(num);
        if (i_clien != -1) {
            Cliente clien = listaClientes.get(i_clien);
            removerSinistrosCliente(clien);
            listaClientes.remove(i_clien);
            return true;
        } else
            return false;
    }
    
    
    //Calcula e devolve a receita total da seguradora
    public double calcularReceita() {
        
        double receita = 0.0;
        for (Cliente aux : listaClientes)
            receita += aux.getValorSeguro();
        return receita;
    }
    
    //Calcula e retorna o preco do seguro de um cliente.
    public double calcularPrecoSeguroCliente(Cliente clien) {
        return clien.calculaScore() * (1 + clien.getQtdSinistros());
    }
    
    
    
    /* Recebe um CPF/CNPJ do cliente origem e um CPF/CNPJ do cliente destinatario.
     * Transfere todos os carros segurados da origem para o destinatario. Retorna
     * true caso sucesso, false caso um deles nao existir
     */
    public boolean transferirSeguro(String num1, String num2) {
        Cliente origem = getCliente(num1);
        Cliente destino = getCliente(num2);
        if (origem == null || destino == null)
            return false;
        Veiculo aux;
        for (int i_carro = origem.getQtdVeiculos() - 1; i_carro >= 0; i_carro--) {
           aux = origem.getVeiculo(i_carro);
           destino.cadastrarVeiculo(aux);
           destino.addVeiculo();
           origem.removerVeiculo(aux.getPlaca());
           origem.remVeiculo();
       }
       return true;
    }
    
    

    @Override
    public String toString() {
        
        String str = String.format("Nome: %s\nTelefone: %s\nEmail: %s\nEndereco: %s\n", 
                                   nome, telefone, email, endereco);
        return str;
    }
    
}
