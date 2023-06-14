package LeitoresArquivo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import VeiculosEfrotas.ListagemVeiculos;
import VeiculosEfrotas.Veiculo;

public class ArquivoVeiculo  implements I_Arquivo<ListagemVeiculos, Boolean> {

    @Override
    public boolean gravarArquivo() {
        // Implementacao deste metodo nesta classe nao faz parte do lab06
        return false;
    }

    @Override
    public ListagemVeiculos lerArquivo(Boolean lista) {
        
        ListagemVeiculos listaVeic = new ListagemVeiculos();
        
        try (Scanner arqVeic = new Scanner(new File(PathArquivos.PATH_VEIC.getPath()))) {
            
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
        } catch (NumberFormatException e) {
            System.out.println("Arquivo de veiculos nao esta padronizado de acordo com a convencao.");
        }
        
        return listaVeic;

    }
    
}
