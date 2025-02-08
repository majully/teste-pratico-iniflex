import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void aumentarSalario(BigDecimal percentual) {
        this.salario = this.salario.add(this.salario.multiply(percentual.divide(BigDecimal.valueOf(100))));
    }

    public String getSalarioFormatadoBR() {
        NumberFormat formato = NumberFormat.getInstance(new Locale("pt", "BR"));
        formato.setMinimumFractionDigits(2);
        formato.setMaximumFractionDigits(2);
        return formato.format(salario);
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
                ", Dt Nascimento: " + getDataNascimentoFormatoBR() +
                ", Salário: R$ " + getSalarioFormatadoBR() +
                ", Função: " + funcao;
    }
}