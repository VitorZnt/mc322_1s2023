import java.time.LocalDate;
import java.util.ArrayList;

public class Seguradora {
    
    // Atributos de instancia
    private final String CNPJ;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Seguro> listaSeguros;
    private ArrayList<Cliente> listaClientes;
    
    
    // Construtor
    public Seguradora(String CNPJ, String nome, String telefone, String email, String endereco) {
        
        this.CNPJ = CNPJ;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        listaSeguros = new ArrayList<Seguro>();
        listaClientes = new ArrayList<Cliente>();
    }

    // Metodos de Get e Set
    public String getCNPJ() {
        return CNPJ;
    }
    
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
    
    
    //Recebe um id e retorna o objeto Seguro procurado. Retorna NULL caso nao encontrado.
    public Seguro getSeguro(int id) {
        
        int i = buscarSeguro(id);
        if (i == -1)
            return null;
        else
            return listaSeguros.get(i);
    }
    
    private int buscarSeguro(int id) {
        
        for (int i = 0; i < listaSeguros.size(); i++) {
            if (listaSeguros.get(i).getId() == id)
                return i;
        }
        return -1;
    }
    
    
    /*Recebe o CPF/CNPJ de um cliente e retorna uma lista com seus seguros. Caso
     * nao haja esse cliente, retorna null.
     */
    public ArrayList<Seguro> getSegurosCliente(String num) {

        Cliente clien = getCliente(num);
        if (clien == null) { //cliente nao existe
            return null;
        }
        return clien.getListaSeguros();
    }
    
    /*Recebe o CPF/CNPJ de um cliente e retorna uma lista com seus sinistros. Caso
     * nao haja esse cliente, retorna null.
     */
    public ArrayList<Sinistro> getSinistrosCliente(String num) {
        
        ArrayList<Sinistro> listaSini = new ArrayList<Sinistro>();
        
        Cliente clien = getCliente(num);
        if (clien == null) { //cliente nao existe
            return null;
        }
        
        for (Seguro seg : clien.getListaSeguros()) {
            listaSini.addAll(seg.getListaSinistros());
        }
        return listaSini;
    }
    
     
    public boolean listarSegurosCliente(String num) {
        
        ArrayList<Seguro> listaSeg = getSegurosCliente(num);
        
        if (listaSeg == null) {
            System.out.println("Cliente inexistente.");
            return false;
            
        } else {
            for (Seguro seg : listaSeg)
                System.out.println(String.format("Seguro: %s\n", seg.toString()));
            return true;
        }
        
    }
    
    public boolean listarSinistrosCliente(String num) {
        
        ArrayList<Sinistro> listaSini = getSinistrosCliente(num);
        
        if (listaSini == null) {
            System.out.println("Cliente inexistente.");
            return false;
            
        } else {
            for (Sinistro sini : listaSini)
                System.out.println(String.format("Sinistro: %s\n", sini.toString()));
            return true;
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
    
    
    //Cadastra um cliente PF. Caso ja exista um mesmo cliente ja cadastrado, retorna false.
    public boolean cadastrarCliente(String nome, String telefone,  String endereco, String email,
                                    String CPF, String genero, String educacao, LocalDate dataNascimento) {
        
        //Cliente ja existente
        if (buscarCliente(CPF) != -1)
            return false;

        else {
            ClientePF novo = new ClientePF(nome, telefone, endereco, email, CPF, genero, educacao, dataNascimento);
            listaClientes.add(novo);
            return true;
        }
    }
    
    //Cadastra um cliente PJ. Caso ja exista um mesmo cliente ja cadastrado, retorna false.
    public boolean cadastrarCliente(String nome, String telefone,  String endereco, String email,
                                    String CNPJ, LocalDate dataFundacao, int qtdFuncionarios) {
        
        //Cliente ja existente
        if (buscarCliente(CNPJ) != -1)
            return false;
        
        else {
            ClientePJ novo = new ClientePJ(nome, telefone, endereco, email, CNPJ, dataFundacao, qtdFuncionarios);
            listaClientes.add(novo);
            return true;
        }
    }
    
    public boolean cadastrarCliente(ClientePF clien) {
        
        //Cliente ja existente
        if (buscarCliente(clien.getCPF()) != -1)
            return false;

        else {
            listaClientes.add(clien);
            return true;
        }
    }
    
    public boolean cadastrarCliente(ClientePJ clien) {
        
        //Cliente ja existente
        if (buscarCliente(clien.getCNPJ()) != -1)
            return false;

        else {
            listaClientes.add(clien);
            return true;
        }
    }
    
    
    /* Gera um seguroPF. Caso o cliente nao exista, ou a placa nao seja
     * de um carro seu, retorna false e nao o gera.
     */
    public boolean gerarSeguroPF(LocalDate dataInicio, LocalDate dataFim, String CPF, String placa) {
        
        Cliente clien = getCliente(CPF);
        if (clien == null || clien instanceof ClientePJ)
            return false;
        
        ListagemVeiculos listaV = ((ClientePF) clien).getListaVeic();
        int i = listaV.buscarVeiculo(placa);
        
        if (i == -1)
            return false;
        
        Veiculo veic = listaV.getVeiculo(i);
        SeguroPF seg = new SeguroPF(dataInicio, dataFim, this, veic, (ClientePF)clien);
        clien.adicionarSeguro(seg);
        clien.atualizarValorSeguro();
        return true;
    }
    
    // Gera um seguroPJ. Caso o cliente nao exista, ou a frota nao seja sua, retorna false e nao o gera.
    public boolean gerarSeguroPJ(LocalDate dataInicio, LocalDate dataFim, String CNPJ, Frota frota) {
        
        Cliente clien = getCliente(CNPJ);
        if (clien == null || clien instanceof ClientePF || frota == null)
            return false;
        
        ListagemFrotas listaF = ((ClientePJ) clien).getListaFrotas();
        int i = listaF.buscarFrota(frota.getCodigo());
        if (i == -1)
            return false;
        
        SeguroPJ seg = new SeguroPJ(dataInicio, dataFim, this, frota, (ClientePJ) clien);
        ((ClientePJ) clien).adicionarSeguro(seg);
        ((ClientePJ) clien).atualizarValorSeguro();
        return true;
    }
    
    
    //Recebe o CPF/CNPJ do cliente a ser removido. Retorna true caso haja sucesso, ou false se ele nao existir
    public boolean removerCliente(String num) {
        
        int i_clien = buscarCliente(num);
        if (i_clien != -1) {
            Cliente clien = listaClientes.get(i_clien);
            removerSegurosCliente(clien);
            listaClientes.remove(i_clien);
            return true;
        } else
            return false;
    }
    
    
    //Remove todos os seguros de um cliente e os sinistros relacionados ao seguro
    public void removerSegurosCliente(Cliente clien) {
        ArrayList<Seguro> ListaSeg = clien.getListaSeguros();

        for (Seguro seg : ListaSeg) {
            ArrayList<Condutor> listaCond = seg.getListaCondutores();

            //removendo os sinistros desse seguro de cada condutor relacionado
            for (int i = 0; i < listaCond.size(); i++) {
                Condutor cond = listaCond.get(i);
                ArrayList<Sinistro> listaSini = cond.getListaSinistros();

                for (Sinistro sini : seg.getListaSinistros()) {

                    for (int j = cond.getQtdSinistros() - 1; j >= 0; j--) {

                        if (sini.getId() == listaSini.get(j).getId())
                            cond.removerSinistro(j);
                    }
                }
            }
        }
        ArrayList<Seguro> listaSeg = clien.getListaSeguros();
        for (int i = listaSeg.size() - 1; i >= 0; i--)
            clien.getListaSeguros().remove(i);
        }
    
    
    //Tenta remover um seguro de dado id, retorna falso se nao existir ou for do cliente
    public boolean cancelarSeguro(Cliente clien, int id) {

        ArrayList<Seguro> listaSeg = clien.getListaSeguros();
        for (int i = listaSeg.size() - 1; i >= 0; i--) {

            Seguro seg = listaSeg.get(i);
            if (seg.getId() == id) { //seguro encontrado

                ArrayList<Condutor> listaCond = seg.getListaCondutores();

                //removendo os sinistros desse seguro de cada condutor relacionado
                for (int k = 0; k < listaCond.size(); k++) {
                    Condutor cond = listaCond.get(k);
                    ArrayList<Sinistro> listaSini = cond.getListaSinistros();

                    for (Sinistro sini : seg.getListaSinistros()) {

                        for (int j = cond.getQtdSinistros() - 1; j >= 0; j--) {

                            if (sini.getId() == listaSini.get(j).getId())
                                cond.removerSinistro(j);
                        }
                    }
                }
                clien.removerSeguro(id);
                return true;
            }
        }
        return false;
    }
    
    
    //Calcula e devolve a receita total da seguradora
    public double calcularReceita() {
        
        double receita = 0.0;
        for (Cliente aux : listaClientes)
            receita += aux.getValorSeguro();
        return receita;
    }
    
    

    @Override
    public String toString() {
        
        String str = String.format("Nome: %s\nTelefone: %s\nEmail: %s\nEndereco: %s\n", 
                                   nome, telefone, email, endereco);
        return str;
    }
    
}
