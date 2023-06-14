package LeitoresArquivo;

public interface I_Arquivo {
    
    // Grava os arquivos de Seguro e Sinistro da seguradora que chamar a funcao
    public boolean gravarArquivo();
    
    /* Le os arquivos de ClienPF, ClienPJ, Frotas e Veiculos e adiciona-os na seguradora
     * que chamar essa funcao */
    public void lerArquivo();

}
