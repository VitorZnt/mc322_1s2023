package LeitoresArquivo;

import java.util.ArrayList;

import ClassesSeguradora.Sinistro;

public class ArquivoSinistro implements I_Arquivo  <ArrayList<Sinistro>, Boolean> {

    @Override
    public boolean gravarArquivo() {
        // TODO
        return false;
    }

    @Override
    public ArrayList<Sinistro> lerArquivo(Boolean b) {
        // Implementacao deste metodo nesta classe nao faz parte do lab06
        return new ArrayList<Sinistro>();
    }

}
