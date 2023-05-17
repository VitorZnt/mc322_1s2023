import java.time.LocalDate;
import java.time.Period;

public enum CalcSeguro {
    
    VALOR_BASE(10.0),
    FATOR_18_30(1.25),
    FATOR_30_60(1.0),
    FATOR_60_90(1.5);
    
    private final double valor;
    
    CalcSeguro(double valor) {
        this.valor = valor;
    }
    
    public double getValor() {
        return valor;
    }
    
    public static double fatorIdade(LocalDate dataNascimento) {
        Period Idade = Period.between(dataNascimento, LocalDate.now());
        int anosIdade = Idade.getYears();
        if (anosIdade < 30)
            return FATOR_18_30.getValor();
        else if (anosIdade < 60)
            return FATOR_30_60.getValor();
        else
            return FATOR_60_90.getValor();
    }
    
}
