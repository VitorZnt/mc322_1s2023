import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class AppMain {
    
    private static ArrayList<Seguradora> listaSeguradoras;
    
    //Adiciona uma seguradora na lista de seguradoras do programa e retorna seu numero/posicao.
    private static int addSeg(String CNPJ, String nome, String telefone, String email, String endereco) {
        
        Seguradora seg = new Seguradora(CNPJ, nome, telefone, email, endereco);
        listaSeguradoras.add(seg);
        return listaSeguradoras.size();
    }
    
    private static int addSeg(Seguradora seg) {

        listaSeguradoras.add(seg);
        return listaSeguradoras.size();
    }
    
    //Retorna a referencia a uma seguradora com base na sua posicao i (indice i - 1).
    private static Seguradora getSeg(int i) {
        if (i > listaSeguradoras.size())
            return null;
        return listaSeguradoras.get(i - 1);
    }
    
    /*main com objetivo de teste das classes e seus metodos*/
    public static void main(String[] args) {
        
    }
}