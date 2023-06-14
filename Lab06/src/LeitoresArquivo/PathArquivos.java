package LeitoresArquivo;

public enum PathArquivos {
    
    PATH_COND ("src/lab06-seguradora_arquivos/condutores.csv"),
    PATH_CLIENPF ("src/lab06-seguradora_arquivos/clientesPF.csv"),
    PATH_CLIENPJ ("src/lab06-seguradora_arquivos/clientesPJ.csv"),
    PATH_VEIC ("src/lab06-seguradora_arquivos/veiculos.csv"),
    PATH_FROTA ("src/lab06-seguradora_arquivos/frotas.csv");
    
    private String path;
    
    PathArquivos(String path) {
        this.path = path;
    }
    
    public String getPath() {
        return path;
    }
    
}
