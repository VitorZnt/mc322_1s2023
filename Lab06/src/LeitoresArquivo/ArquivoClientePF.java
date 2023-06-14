package LeitoresArquivo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import ClassesSeguradora.ClientePF;
import VeiculosEfrotas.ListagemVeiculos;
import VeiculosEfrotas.Veiculo;

public class ArquivoClientePF implements I_Arquivo<ArrayList<ClientePF>, ListagemVeiculos> {
    
    @Override
    public boolean gravarArquivo() {
        // Implementacao deste metodo nesta classe nao faz parte do lab06
        return false;
    }

    @Override
    public ArrayList<ClientePF> lerArquivo(ListagemVeiculos lista) {
        
        ArrayList<ClientePF> listaPF = new ArrayList<>();
        
        try (Scanner arqClienPF = new Scanner(new File(PathArquivos.PATH_CLIENPF.getPath()))) {
            
            String[] linha;
            Veiculo veic1;
            ClientePF clien;
            
            arqClienPF.nextLine(); //Descarta header
            
            while (arqClienPF.hasNextLine()) {
                
                linha = (arqClienPF.nextLine()).split(",");
                
                veic1 = lista.getVeiculo(linha[8]);
                
                clien = new ClientePF(linha[1], linha[2], linha[3], linha[4], linha[0], 
                                      linha[5], linha[6], LocalDate.parse(linha[7]));
                clien.cadastrarVeiculo(veic1);
                
                listaPF.add(clien);
                
            }
            
        } catch (IOException e) {
            System.out.println("Caminho para arquivo de clientesPF nao encontrado.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Arquivo de clientesPF nao esta padronizado de acordo com a convencao.");
        } catch (DateTimeParseException e) {
            System.out.println("Arquivo de clientesPF nao esta padronizado de acordo com a convencao.");
        }
        
        return listaPF;
    }

}
