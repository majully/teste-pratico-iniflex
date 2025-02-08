import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

//3 – Classe Principal para executar o projeto
public class Main {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        //3.1 – Inserindo funcionários no Array
        funcionarios.add(new Funcionario("Maria", LocalDate.of(1990, 10, 18), new BigDecimal("2000.00"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1992, 5, 12), new BigDecimal("3000.00"), "Gerente"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("4000.00"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("2500.00"), "Operador"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2500.00"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("3000.00"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("2000.00"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("4000.00"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("2500.00"), "Recepcionista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(2000, 9, 2), new BigDecimal("3000.00"), "Coordenador"));

        //Imprimindo todos os funcionários inseridos
        System.out.println("\n====== Funcionários ======");
        for (Funcionario funcionario : funcionarios) System.out.println(funcionario);
        System.out.println("==========================");

        //3.2 – Removendo funcionário João
        funcionarios.removeIf(f -> f.getNome().equals("João"));

        //3.3 – Imprimindo todos os funcionários
        System.out.println("\n====== Funcionários ======");
        funcionarios.forEach(System.out::println);
        System.out.println("==========================");

        //3.4 – Atribuindo aumento de salário em 10% para todos funcionários
        funcionarios.forEach(f -> f.aumentarSalario(BigDecimal.valueOf(10)));

        //3.5 – Agrupando funcionários por função MAP (Chave->Função / Valor->funcionários)
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        //3.6 – Imprimindo funcionários agrupados por cada função
        System.out.println("\n====== Funcionários Por Função ======");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(l -> System.out.println(
                    "Nome: " + l.getNome() + ", " +
                    "Dt Nascimento: " + l.getDataNascimentoFormatoBR() + ", " +
                    "Salário: " + l.getSalarioFormatadoBR()));
        });
        System.out.println("=======================================");

        //3.8 – Imprimindo aniversariantes do mês outubro e dezembro
        System.out.println("\n====== Funcionários Aniversariantes em Outubro e Dezembro ======");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonth() == Month.OCTOBER ||
                        f.getDataNascimento().getMonth() == Month.DECEMBER)
                .forEach(System.out::println);
        System.out.println("=================================================================");

        //3.9 – Imprimindo funcionário mais velho
        Funcionario maisVelho = Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
        int idade = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
        System.out.println("\n====== Funcionários com Maior Idade ======");
        System.out.println("Nome: " + maisVelho.getNome() + ", Idade: " + idade + " anos");
        System.out.println("============================================");

        //3.10 – Imprimindo lista de funcionários em ordem alfabética
        System.out.println("\n====== Funcionários em ordem alfabética ======");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);
        System.out.println("================================================");

        //3.11 – Imprimindo total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\n====== Total dos salários ======");
        System.out.println("R$ " + new Funcionario("", null, totalSalarios, "").getSalarioFormatadoBR());
        System.out.println("================================================");

        //3.12 – Imprimindo quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\n====== Quantidade de salários mínimos por funcionário ======");
        funcionarios.forEach(f -> {
            BigDecimal qtdSalariosMinimos = f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(f.getNome() + ": ganha " + qtdSalariosMinimos + " salários mínimos.");
        });
        System.out.println("===============================================================");

    }
}