import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class GerenciadorArquivos {
    
    private static ArrayList<Condutor> listaLeituraCond;
    private static final String pathCond = "/src/lab06-seguradora_arquivos/condutores.csv";
    private static final String pathClienPF = "/src/lab06-seguradora_arquivos/clientesPF.csv";
    private static final String pathClienPJ = "/src/lab06-seguradora_arquivos/clientesPJ.csv";
    private static final String pathVeic = "/src/lab06-seguradora_arquivos/veiculos.csv";
    private static final String pathFrota = "/src/lab06-seguradora_arquivos/frotas.csv";
    
    
    public GerenciadorArquivos() {
        listaLeituraCond = new ArrayList<>();
        lerCondutores();
    }
    
    public ArrayList<Condutor> GetListaLeituraCond() {
        return listaLeituraCond;
    }
    
    private void lerCondutores() {
        
        try (Scanner arqCond = new Scanner(new File(pathCond))) {
            
            String[] linha;
            Condutor cond;
            
            arqCond.nextLine(); //Descarta header
            
            while (arqCond.hasNextLine()) {
                
                linha = (arqCond.nextLine()).split(",");
                
                cond = new Condutor(linha[0], linha[1], linha[2], linha[3], linha[4], LocalDate.parse(linha[5]));
                listaLeituraCond.add(cond);
            }
            
        } catch (IOException e) {
            System.out.println("Caminho para lista de condutores nao encontrado.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Arquivo de condutores nao esta padronizado de acordo com a convencao.");
        }
        
    }
    
    /* Le os arquivos de ClienPF, ClienPJ, Frotas e Veiculos e adiciona-os na seguradora
     * que chamar essa funcao
     */
    public static void lerArquivosClientes(Seguradora seg) {
        
        ListagemVeiculos listaVeic = lerVeic();
        ListagemFrotas listaFrotas = lerFrotas(listaVeic);
        ArrayList<ClientePF> listaClienPF = new ArrayList<>();
        ArrayList<ClientePJ> listaClienPJ = new ArrayList<>();
        
    }
    
    //Retorna a lista lida de Veiculos
    private static ListagemVeiculos lerVeic() {
        
        ListagemVeiculos listaVeic = new ListagemVeiculos();
        
        try (Scanner arqVeic = new Scanner(new File(pathVeic))) {
            
            String[] linha;
            Veiculo veic;
            
            arqVeic.nextLine(); //Descarta header
            
            while (arqVeic.hasNextLine()) {
                
                linha = (arqVeic.nextLine()).split(",");
                
                veic = new Veiculo(linha[0], linha[1], linha[2], Integer.parseInt(linha[3]));
                listaVeic.cadastrarVeiculo(veic);
            }
            
        } catch (IOException e) {
            System.out.println("Caminho para arquivo de veiculos nao encontrado.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Arquivo de veiculos nao esta padronizado de acordo com a convencao.");
        }
        
        return listaVeic;
    }
    
    /* Retorna a lista lida de Frotas, de acordo com os veiculos da lista recebida.
     * Assume que a lista recebida contem todos os veiculos declarados nas frotas,
     * e que cada frota possui 3 veiculos apenas.
     */
    private static ListagemFrotas lerFrotas(ListagemVeiculos listaVeic) {
        
        ListagemFrotas listaFrotas = new ListagemFrotas();
        
        try (Scanner arqFrota = new Scanner(new File(pathFrota))) {
            
            String[] linha;
            Veiculo veic1, veic2, veic3;
            
            arqFrota.nextLine(); //Descarta header
            
            while (arqFrota.hasNextLine()) {
                
                linha = (arqFrota.nextLine()).split(",");
                
                veic1 = listaVeic.getVeiculo(linha[1]);
                veic2 = listaVeic.getVeiculo(linha[2]);
                veic3 = listaVeic.getVeiculo(linha[3]);
                
                listaFrotas.cadastrarFrota(linha[0], veic1, veic2, veic3);
                
            }
            
        } catch (IOException e) {
            System.out.println("Caminho para arquivo de veiculos nao encontrado.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Arquivo de veiculos nao esta padronizado de acordo com a convencao.");
        }
        
        return listaFrotas;
    }
    
}
