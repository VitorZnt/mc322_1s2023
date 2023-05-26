import java.util.Map;
import java.util.HashMap;

public enum MenuOperacoes {
    
    //Operacoes principais
    SAIR(0),
    CADASTRAR(1),
    ATUALIZAR_FROTA(2),
    GERAR(3),
    AUTORIZAR_DESAUT_CONDUTOR(4),
    LISTAR(5),
    CANCELAR_SEGURO(6),
    REMOVER(7),
    CALCULAR_RECEITA_SEGURADORA(8),
    
    //Operacoes secundarias
    CADASTRAR_PF(11),
    CADASTRAR_PJ(12),
    CADASTRAR_VEICULO_PF(13),
    CADASTRAR_FROTA_PJ(14),
    CADASTRAR_SEGURADORA(15),
    VOLTAR1(16),
    
    ADD_VEICULO_FROTA(21),
    REM_VEICULO_FROTA(22),
    REMOVER_FROTA(23),
    VOLTAR2(24),
    
    GERAR_SINISTRO(31),
    GERAR_SEGURO_PF(32),
    GERAR_SEGURO_PJ(33),
    VOLTAR3(34),
    
    AUTORIZAR_COND(41),
    DESAUTORIZAR_COND(42),
    VOLTAR4(43),
    
    LISTAR_INFOS_CLIENTE(51),
    LISTAR_SEGUROS(52),
    LISTAR_SINISTROS(53),
    LISTAR_VEICULOS_PF(54),
    LISTAR_FROTAS_PJ(55),
    VOLTAR5(56),
    
    REMOVER_CLIENTE(71),
    REMOVER_VEICULO(72),
    VOLTAR7(73);
    
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
