package LeitoresArquivo;

import java.util.ArrayList;

import ClassesSeguradora.Sinistro;

public class ArquivoSinistro implements I_Arquivo {

    @Override
    public boolean gravarArquivo() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ArrayList<Sinistro> lerArquivo() {
        // Implementacao deste metodo nesta classe nao faz parte do lab06
        return new ArrayList<Sinistro>();
    }

}
