package LeitoresArquivo;

public interface I_Arquivo <T1, T2> {
    
    // Grava os arquivos de Seguro e Sinistro da seguradora que chamar a funcao
    public boolean gravarArquivo();
    
    /* Le os arquivos de ClienPF, ClienPJ, Frotas e Veiculos e adiciona-os na seguradora
     * que chamar essa funcao */
    public T1 lerArquivo(T2 lista);

}
