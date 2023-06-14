package LeitoresArquivo;

import java.util.ArrayList;

import ClassesSeguradora.Seguro;

public class ArquivoSeguro implements I_Arquivo {
    
    @Override
    public boolean gravarArquivo() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ArrayList<Seguro> lerArquivo() {
        // Implementacao deste metodo nesta classe nao faz parte do lab06
        return new ArrayList<Seguro>();
    }

}
