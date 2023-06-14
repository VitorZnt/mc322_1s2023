package LeitoresArquivo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import ClassesSeguradora.Condutor;

public class ArquivoCondutor implements I_Arquivo<ArrayList<Condutor>, Boolean> {
    
    @Override
    public boolean gravarArquivo() {
        // Implementacao deste metodo nesta classe nao faz parte do lab06
        return false;
    }

    @Override
    public ArrayList<Condutor> lerArquivo(Boolean lista) {
        
        ArrayList<Condutor> listaLeituraCond = new ArrayList<>();
        
        try (Scanner arqCond = new Scanner(new File(PathArquivos.PATH_COND.getPath()))) {
            
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
        } catch (DateTimeParseException e) {
            System.out.println("Arquivo de condutores nao esta padronizado de acordo com a convencao.");
        }
        
        return listaLeituraCond;

    }

}
