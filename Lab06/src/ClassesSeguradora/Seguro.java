package ClassesSeguradora;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Seguro {

    private final int id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Seguradora seguradora;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Condutor> listaCondutores;
    private double valorMensal;
    
    // Contador de seguros (e gerador de Id's)
    private static int contadorId = 0;
    
    
    public Seguro(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora) {
        
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        listaSinistros = new ArrayList<Sinistro>();
        listaCondutores = new ArrayList<Condutor>();
        id = gerarId();
        valorMensal = 0;
    }
    
    
    
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }
    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }
    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public ArrayList<Condutor> getListaCondutores() {
        return listaCondutores;
    }
    public void setListaCondutores(ArrayList<Condutor> listaCondutores) {
        this.listaCondutores = listaCondutores;
    }

    public double getValorMensal() {
        atualizarValorMensal();
        return valorMensal;
    }
    public void setValorMensal(int valorMensal) {
        this.valorMensal = valorMensal;
    }

    public int getId() {
        return id;
    }

    /* Retorna um Id proprio e unico, numerando por contagem cada seguro registrado*/
    private int gerarId() {
        contadorId++;
        return contadorId;
    }
    
    
    
    //Adiciona um condutor. Retorna false e nao o adiciona caso ja exista na lista
    public boolean autorizarCondutor(Condutor c) {
        
        for (Condutor cond : listaCondutores)
            if (cond.getCPF().equals(c.getCPF()))
                return false;

        listaCondutores.add(c);
        return true;
    }
    
    public boolean autorizarCondutor(String CPF, String nome, String telefone,
                      String endereco, String email, LocalDate dataNascimento) {
        
        for (Condutor cond : listaCondutores)
            if (cond.getCPF().equals(CPF))
                return false;
        
        Condutor c = new Condutor(CPF, nome, telefone, endereco, email, dataNascimento);
        listaCondutores.add(c);
        return true;
    }
    
    
    
    //Remove um condutor da lista, retornando true caso exista e tenha sido removido.
    public boolean desautorizarCondutor(String CPF) {
        
        for (int i = 0; i < listaCondutores.size(); i++) {
            if (listaCondutores.get(i).getCPF().equals(CPF)) {
                listaCondutores.remove(i);
                return true;
            }
        }
        return false;
    }
    
    /* Gera um sinistro com as informacoes dadas e o CPF do condutor. Se o condutor nao
     * estiver na lista de condutores do seguro, nao cria e retorna false
     */
    public boolean gerarSinistro(LocalDate data, String endereco, String CPF) {
        
        boolean flag = false;
        Condutor condutor = null;
        
        for (int i = 0; i < listaCondutores.size(); i++) {
            condutor = listaCondutores.get(i);
            if (condutor.getCPF().equals(CPF)) {
                flag = true;
                break;
            }
        }
        
        if (!flag)
            return false; //Condutor nao autorizado

        Sinistro sini = new Sinistro(data, endereco, condutor, this);
        listaSinistros.add(sini);
        condutor.adicionarSinistro(sini);
        return true;
    }
    
    
    public void atualizarValorMensal() {
        valorMensal = calcularValor();
    }
    
    public abstract double calcularValor();
    
    
    @Override
    public String toString() {
        return String.format("Id: %d\nDataInicio: %s\nDataFim: %s\nSeguradora: %s\nValor mensal: %f\n",
                id, dataInicio.toString(), dataFim.toString(), seguradora.getNome(), valorMensal);
    } 
}
