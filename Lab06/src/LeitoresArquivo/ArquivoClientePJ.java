package LeitoresArquivo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import ClassesSeguradora.ClientePJ;
import VeiculosEfrotas.ListagemFrotas;
import VeiculosEfrotas.Veiculo;


public class ArquivoClientePJ implements I_Arquivo<ArrayList<ClientePJ>, ListagemFrotas> {
    
    private static final int qtdPadraoFuncionariosPJ = 50;
    
    @Override
    public boolean gravarArquivo() {
        // Implementacao deste metodo nesta classe nao faz parte do lab06
        return false;
    }

    @Override
    public ArrayList<ClientePJ> lerArquivo(ListagemFrotas lista) {
        
        ArrayList<ClientePJ> listaPJ = new ArrayList<>();
        
        try (Scanner arqClienPJ = new Scanner(new File(PathArquivos.PATH_CLIENPJ.getPath()))) {
            
            String[] linha;
            ArrayList<Veiculo> veicFrota;
            ClientePJ clien;
            
            arqClienPJ.nextLine(); //Descarta header
            
            while (arqClienPJ.hasNextLine()) {
                
                linha = (arqClienPJ.nextLine()).split(",");
                
                clien = new ClientePJ(linha[1], linha[2], linha[3], linha[4], linha[0], 
                        LocalDate.parse(linha[5]), qtdPadraoFuncionariosPJ);
                
                veicFrota = lista.getVeiculosPorFrota(linha[6]);
                
                clien.cadastrarFrota(linha[6], veicFrota.get(0), veicFrota.get(1), veicFrota.get(2));
                
                listaPJ.add(clien);
                
            }
            
        } catch (IOException e) {
            System.out.println("Caminho para arquivo de clientesPJ nao encontrado.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Arquivo de clientesPJ nao esta padronizado de acordo com a convencao.");
        } catch (DateTimeParseException e) {
            System.out.println("Arquivo de clientesPJ nao esta padronizado de acordo com a convencao.");
        }
        
        return listaPJ;

    }

}
