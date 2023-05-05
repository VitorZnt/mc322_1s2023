import java.util.Map;
import java.util.HashMap;

public enum MenuOperacoes {
    
    //Operacoes principais
    SAIR(0),
    CADASTRAR(1),
    LISTAR(2),
    EXCLUIR(3),
    GERAR_SINISTRO(4),
    TRANSFERIR_SEGURO(5),
    CALCULAR_RECEITA_SEGURADORA(6),
    
    //Operacoes secundarias
    CADASTRAR_PF(11),
    CADASTRAR_PJ(12),
    CADASTRAR_VEICULO(13),
    CADASTRAR_SEGURADORA(14),
    VOLTAR1(15),
    
    LISTAR_CLIENTES_SEGURADORA(21),
    LISTAR_SINISTROS_SEGURADORA(22),
    LISTAR_VEICULOS_SEGURADORA(23),
    LISTAR_SINISTROS_CLIENTE(24),
    LISTAR_VEICULOS_CLIENTE(25),
    VOLTAR2(26),
    
    EXCLUIR_CLIENTE(31),
    EXCLUIR_VEICULO(32),
    EXCLUIR_SINISTRO(33),
    VOLTAR3(34);
    
    private final int codigo;
    private static final Map<Integer, MenuOperacoes> operacaoPorCodigo = new HashMap<>();
    
    MenuOperacoes(int codigo) {
        this.codigo = codigo;
    }
    
    static {
        for (MenuOperacoes op : values()) {
            operacaoPorCodigo.put(op.codigo, op);
        }
    }
    
    public static MenuOperacoes getOperacaoPorCodigo(int i) {
        return operacaoPorCodigo.get(i);
    }
    
    
}
