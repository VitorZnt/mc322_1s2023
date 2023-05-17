import java.time.LocalDate;
import java.util.ArrayList;

public class ClientePJ extends Cliente {
    
    //Atributos de instancia proprios da subclasse
    private final String CNPJ;
    private LocalDate dataFundacao;
    private int qtdFuncionarios;
    private ListagemFrotas listaFrotas;
    private ArrayList<SeguroPJ> listaSeguros;
    
    //Construtor
    public ClientePJ(String nome, String telefone,  String endereco, String email,
            String CNPJ, LocalDate dataFundacao, int qtdFuncionarios) {
        
        super(nome, telefone, endereco, email);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
        this.qtdFuncionarios = qtdFuncionarios;
        listaFrotas = new ListagemFrotas();
        listaSeguros = new ArrayList<SeguroPJ>();
    }
    
    
    //Metodos Get e Set da subclasse
    public String getCNPJ() {
        return CNPJ;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }
    
    public int getQtdFuncionarios() {
        return qtdFuncionarios;
    }
    public void setQtdFuncionarios(int num) {
        qtdFuncionarios = num;
    }
    
    public ListagemFrotas getListaFrotas() {
        return listaFrotas;
    }
    
    public ArrayList<SeguroPJ> getListaSeguros() {
        return listaSeguros;
    }

    
    public void adicionarSeguro(SeguroPJ seguro) {
        listaSeguros.add(seguro);
    }
    public boolean removerSeguro(int id) {
        
        for (int i = 0; i < listaSeguros.size(); i++) {
            if (listaSeguros.get(i).getId() == id) {
                listaSeguros.remove(i);
                return true;
            }
        }
        return false; //nao encontrado
    }
    

    @Override
    public String toString() {
        
        String str = super.toString() + String.format("CNPJ: %s\nData de fundacao: %s\n", CNPJ, dataFundacao.toString())
                     + "Veiculos nas frotas:\n" + listaFrotas.toString();
        return str;
    }
    

    
}
