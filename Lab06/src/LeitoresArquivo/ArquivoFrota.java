package LeitoresArquivo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import VeiculosEfrotas.ListagemFrotas;
import VeiculosEfrotas.ListagemVeiculos;
import VeiculosEfrotas.Veiculo;

public class ArquivoFrota implements I_Arquivo<ListagemFrotas, ListagemVeiculos> {

    @Override
    public boolean gravarArquivo() {
        // Implementacao deste metodo nesta classe nao faz parte do lab06
        return false;
    }

    @Override
    public ListagemFrotas lerArquivo(ListagemVeiculos lista) {
        
        ListagemFrotas listaFrotas = new ListagemFrotas();
        
        try (Scanner arqFrota = new Scanner(new File(PathArquivos.PATH_FROTA.getPath()))) {
            
            String[] linha;
            Veiculo veic1, veic2, veic3;
            
            arqFrota.nextLine(); //Descarta header
            
            while (arqFrota.hasNextLine()) {
                
                linha = (arqFrota.nextLine()).split(",");
                
                veic1 = lista.getVeiculo(linha[1]);
                veic2 = lista.getVeiculo(linha[2]);
                veic3 = lista.getVeiculo(linha[3]);
                
                listaFrotas.cadastrarFrota(linha[0], veic1, veic2, veic3);
                
            }
            
        } catch (IOException e) {
            System.out.println("Caminho para arquivo de frotas nao encontrado.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Arquivo de frotas nao esta padronizado de acordo com a convencao.");
        }
        
        return listaFrotas;

    }

}
