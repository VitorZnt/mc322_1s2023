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
    
}
