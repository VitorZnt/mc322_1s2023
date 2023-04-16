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
    
    
    /*Busca um cliente de num CPF/CNPJ fornecidos. Se existir, retorna sua posicao na ArrayList.
     * Caso contrario, retorna -1 
     */
    public int buscarCliente(String num) {
        
        int num_len = num.length();
        Cliente aux;
        
        
        for (int i = 0; i < listaClientes.size(); i++) {
            aux = listaClientes.get(i);
            
            if (num_len == 12 && (aux instanceof ClientePF) && 
               ((ClientePF)aux).getCPF().equals(num)) { //usa casting para acessar atributos da subclasse
                
                return i;
            } else if (num_len == 14 && (aux instanceof ClientePJ) && 
               ((ClientePJ)aux).getCNPJ().equals(num)){
                
                return i;
            }
        }
        
        return -1;
    }
    
    //Cadastra um cliente PF. Caso ja exista um mesmo cliente ja cadastrado, ou se o CPF for invalido, retorna false.
    public boolean cadastrarCliente(String nome, String endereco, String educacao, String genero, String classeEconomica,
                                      String CPF, LocalDate dataNascimento, LocalDate dataLicenca) {
        
        //Cliente ja existente ou CPF invalido
        if ((ClientePF.validarCPF(CPF) == false) || (buscarCliente(CPF) != -1))
            return false;

        else {
            ClientePF novo = new ClientePF(nome, endereco, educacao, genero, classeEconomica, CPF, dataNascimento, dataLicenca);
            listaClientes.add(novo);
            return true;
        }
    }
    
    //Cadastra um cliente PJ. Caso ja exista um mesmo cliente ja cadastrado, ou se o CNPJ for invalido, retorna false.
    public boolean cadastrarCliente(String nome, String endereco, String CNPJ, LocalDate dataFundacao) {
        
        //Cliente ja existente ou CNPJ invalido
        if ((ClientePJ.validarCNPJ(CNPJ) == false) || (buscarCliente(CNPJ) != -1))
            return false;
        
        else {
            ClientePJ novo = new ClientePJ(nome, endereco, CNPJ, dataFundacao);
            listaClientes.add(novo);
            return true;
        }
    }
    
    
    //Recebe o CPF/CNPJ do cliente a ser removido. Retorna true caso haja sucesso, ou false se ele nao existir
    public boolean removerCliente(String num) {
        
        int i = buscarCliente(num);
        if (i != -1) {
            listaClientes.remove(i);
            return true;
        } else
            return false;
    }
    
    //Imprime todos os clientes e suas informacoes
    public void listarClientes() {
        
        int i = 0;
        for (Cliente aux: listaClientes) {
            i++;
            System.out.println(String.format("Cliente &d:", i));
            if (aux instanceof ClientePF)
                System.out.println(((ClientePF)aux).toString());
            else
                System.out.println(((ClientePJ)aux).toString());
            System.out.println();
        }
    }
    
    /*A partir de uma data, endereco, placa do veiculo e CPF/CNPJ, cria um sinistro e o adiciona na lista.
     *Caso o veiculo nao pertenca ao cliente, ou cliente nao existe, retorna false.
     */
    public boolean gerarSinistro(LocalDate data, String endereco, String placa, String num) {
        
        int indice_clien = buscarCliente(num);
        if (indice_clien == -1) //cliente nao existe
            return false;
        Cliente clien = listaClientes.get(indice_clien);
        int indice_veic = clien.buscarVeiculo(placa);
        if (indice_veic == -1) //veiculo nao pertence ao cliente
            return false;
        Veiculo veic = clien.getVeiculo(indice_veic);
        Sinistro sini = new Sinistro(data, endereco, this, veic, clien);
        listaSinistros.add(sini);
        return true;
    }
    
    
    /*Recebe o CPF/CNPJ de um cliente e mostra seus sinistros na tela. Caso
     * nao haja esse cliente ou nao tenha sinistros, imprime essas mensagens.
     */
    public boolean visualizarSinistro(String num) {
        
        int indice_clien = buscarCliente(num);
        if (indice_clien == -1) { //cliente nao existe
            System.out.println("Cliente inexistente.");
            return false;
        }
        Cliente clien = listaClientes.get(indice_clien);
        
        int i = 0;
        for (Sinistro sini: listaSinistros) {
            if (sini.getCliente().equals(clien)) {
                i++;
                System.out.println(String.format("Sinistro &d:", i));
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
    
    
    //Imprime os sinistros e suas informacoes na tela
    public void listarSinistros() {
        
        int i = 0;
        for (Sinistro aux: listaSinistros) {
            i++;
            System.out.println(String.format("Sinistro &d:", i));
            System.out.println(aux.toString());
            System.out.println();
        }
    }
    
}
