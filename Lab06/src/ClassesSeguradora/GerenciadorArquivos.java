package ClassesSeguradora;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import VeiculosEfrotas.*;

public class GerenciadorArquivos {
    
    private static ArrayList<Condutor> listaLeituraCond;
    private static final String pathCond = "src/lab06-seguradora_arquivos/condutores.csv";
    private static final String pathClienPF = "src/lab06-seguradora_arquivos/clientesPF.csv";
    private static final String pathClienPJ = "src/lab06-seguradora_arquivos/clientesPJ.csv";
    private static final String pathVeic = "src/lab06-seguradora_arquivos/veiculos.csv";
    private static final String pathFrota = "src/lab06-seguradora_arquivos/frotas.csv";
    private static final int qtdPadraoFuncionariosPJ = 50;
    
    
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
        } catch (DateTimeParseException e) {
            System.out.println("Arquivo de condutores nao esta padronizado de acordo com a convencao.");
        }
        
    }
    
    /* Le os arquivos de ClienPF, ClienPJ, Frotas e Veiculos e adiciona-os na seguradora
     * que chamar essa funcao
     */
    public static void lerArquivosClientes(Seguradora seg) {
        
        ListagemVeiculos listaVeic = lerVeic();
        ListagemFrotas listaFrotas = lerFrotas(listaVeic);
        ArrayList<ClientePF> listaClienPF = lerClienPF(listaVeic);
        ArrayList<ClientePJ> listaClienPJ = lerClienPJ(listaFrotas);
        
        for (ClientePF clien : listaClienPF) {
            seg.cadastrarCliente(clien);
        }
        for (ClientePJ clien : listaClienPJ) {
            seg.cadastrarCliente(clien);
        }
        return;
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
        } catch (NumberFormatException e) {
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
            System.out.println("Caminho para arquivo de frotas nao encontrado.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Arquivo de frotas nao esta padronizado de acordo com a convencao.");
        }
        
        return listaFrotas;
    }
    
    /* Retorna a lista de clienPF lida do arquivo, associando cada cliente ao seu
     * Veiculo. Assume que a lista de veiculos recebida possua todos os veiculos
     * necessarios e que cada cliente apenas possua 1 veiculo.
     * */
    private static ArrayList<ClientePF> lerClienPF(ListagemVeiculos listaVeic) {
        
        ArrayList<ClientePF> listaPF = new ArrayList<>();
        
        try (Scanner arqClienPF = new Scanner(new File(pathClienPF))) {
            
            String[] linha;
            Veiculo veic1;
            ClientePF clien;
            
            arqClienPF.nextLine(); //Descarta header
            
            while (arqClienPF.hasNextLine()) {
                
                linha = (arqClienPF.nextLine()).split(",");
                
                veic1 = listaVeic.getVeiculo(linha[8]);
                
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
    
    
    /* Retorna a lista de clienPJ lida do arquivo, associando cada cliente a sua
     * Frota. Assume que a lista de frotas recebida possua todas as frotas
     * necessarias e que cada cliente apenas possua 1 frota de 3 veiculos.
     * */
    private static ArrayList<ClientePJ> lerClienPJ(ListagemFrotas listaFrotas) {
        
        ArrayList<ClientePJ> listaPJ = new ArrayList<>();
        
        try (Scanner arqClienPJ = new Scanner(new File(pathClienPJ))) {
            
            String[] linha;
            ArrayList<Veiculo> veicFrota;
            ClientePJ clien;
            
            arqClienPJ.nextLine(); //Descarta header
            
            while (arqClienPJ.hasNextLine()) {
                
                linha = (arqClienPJ.nextLine()).split(",");
                
                clien = new ClientePJ(linha[1], linha[2], linha[3], linha[4], linha[0], 
                        LocalDate.parse(linha[5]), qtdPadraoFuncionariosPJ);
                
                veicFrota = listaFrotas.getVeiculosPorFrota(linha[6]);
                
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
