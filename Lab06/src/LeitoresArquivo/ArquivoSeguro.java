package LeitoresArquivo;

import java.util.ArrayList;

import ClassesSeguradora.Seguro;

public class ArquivoSeguro implements I_Arquivo <ArrayList<Seguro>, Boolean>{
    
    @Override
    public boolean gravarArquivo() {
        // TODO
        return false;
    }

    @Override
    public ArrayList<Seguro> lerArquivo(Boolean b) {
        // Implementacao deste metodo nesta classe nao faz parte do lab06
        return new ArrayList<Seguro>();
    }

}
