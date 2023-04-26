
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Principal {

    private static final BigDecimal SALARIO_MINIMO = BigDecimal.valueOf(1212.00);
    private static final List<Funcionario> FUNCIONARIOS = new ArrayList<>();

    public static void main(String[] args) throws UnsupportedEncodingException {

        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));

        inserirFuncionarios();
        removerFuncionario("João");
        imprimirFuncionarios();
        System.out.println("----------------------------------------------------------------------------------------");
        aumentoSalario(BigDecimal.valueOf(0.10));
        Map<String, List<Funcionario>> funcionariosPorFuncao = agruparFuncionariosPorFuncao();
        imprimirFuncionariosAgrupadosPorFuncao(funcionariosPorFuncao);
        System.out.println("----------------------------------------------------------------------------------------");
        imprimirAniversariantesMeses10e12();
        System.out.println("----------------------------------------------------------------------------------------");
        imprimirFuncionarioMaiorIdade();
        System.out.println("----------------------------------------------------------------------------------------");
        imprimirFuncionariosOrdemAlfabetica();
        System.out.println("----------------------------------------------------------------------------------------");
        imprimirTotalSalarios();
        System.out.println("----------------------------------------------------------------------------------------");
        imprimirQuantosSalariosMinimosGanhaCadaFuncionario();
    }

    private static void inserirFuncionarios() {
        FUNCIONARIOS
                .add(new Funcionario("Maria", LocalDate.parse("2000-10-18"), BigDecimal.valueOf(2009.44), "Operador"));
        FUNCIONARIOS
                .add(new Funcionario("João", LocalDate.parse("1990-05-12"), BigDecimal.valueOf(2284.38), "Operador"));
        FUNCIONARIOS.add(
                new Funcionario("Caio", LocalDate.parse("1961-05-02"), BigDecimal.valueOf(9836.14), "Coordenador"));
        FUNCIONARIOS
                .add(new Funcionario("Miguel", LocalDate.parse("1988-10-14"), BigDecimal.valueOf(19119.88), "Diretor"));
        FUNCIONARIOS.add(
                new Funcionario("Alice", LocalDate.parse("1995-01-05"), BigDecimal.valueOf(2234.68), "Recepcionista"));
        FUNCIONARIOS
                .add(new Funcionario("Heitor", LocalDate.parse("1999-11-19"), BigDecimal.valueOf(1582.72), "Operador"));
        FUNCIONARIOS
                .add(new Funcionario("Arthur", LocalDate.parse("1993-03-31"), BigDecimal.valueOf(4071.84), "Contador"));
        FUNCIONARIOS
                .add(new Funcionario("Laura", LocalDate.parse("1994-07-08"), BigDecimal.valueOf(3017.45), "Gerente"));
        FUNCIONARIOS.add(
                new Funcionario("Heloísa", LocalDate.parse("2003-05-24"), BigDecimal.valueOf(1606.85), "Eletricista"));
        FUNCIONARIOS
                .add(new Funcionario("Helena", LocalDate.parse("1996-09-02"), BigDecimal.valueOf(2799.93), "Gerente"));
    }

    private static void removerFuncionario(String nome) {
        FUNCIONARIOS.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }

    private static void imprimirFuncionarios() {
        FUNCIONARIOS.forEach(funcionario -> {
            System.out.println("Nome: " + funcionario.getNome() + " | Data Nascimento: "
                    + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    + " | Salário: R$"
                    + NumberFormat.getNumberInstance(new Locale("pt", "BR")).format(funcionario.getSalario())
                    + " | Funcão: "
                    + funcionario.getFuncao());
        });
    }

    private static void aumentoSalario(BigDecimal aumento) {
        FUNCIONARIOS.forEach(funcionario -> {
            BigDecimal valorAumento = funcionario.getSalario().multiply(aumento);
            funcionario.setSalario(funcionario.getSalario().add(valorAumento));
        });
    }

    private static Map<String, List<Funcionario>> agruparFuncionariosPorFuncao() {
        return FUNCIONARIOS.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    private static void imprimirFuncionariosAgrupadosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        funcionariosPorFuncao.forEach((funcao, funcionarios) -> {
            System.out.println("Função: " + funcao);
            funcionarios.forEach(funcionario -> {
                System.out.println("Nome: " + funcionario.getNome() + " | Data Nascimento: "
                        + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        + " | Salário: R$"
                        + NumberFormat.getNumberInstance(new Locale("pt", "BR")).format(funcionario.getSalario()));
            });
        });
    }

    private static void imprimirAniversariantesMeses10e12() {
        FUNCIONARIOS.forEach(funcionario -> {
            if (funcionario.getDataNascimento().getMonthValue() == 10
                    || funcionario.getDataNascimento().getMonthValue() == 12) {
                System.out.println("Nome: " + funcionario.getNome() + " | Data Nascimento: "
                        + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        + " | Salário: R$"
                        + NumberFormat.getNumberInstance(new Locale("pt", "BR")).format(funcionario.getSalario())
                        + " | Funcão: "
                        + funcionario.getFuncao());
            }
        });
    }

    private static void imprimirFuncionarioMaiorIdade() {
        Optional<Funcionario> funcionarioMaiorIdade = FUNCIONARIOS.stream().max(
                Comparator.comparing(funcionario -> funcionario.getDataNascimento().until(LocalDate.now()).getYears()));
        funcionarioMaiorIdade.ifPresent(funcionario -> {
            System.out.println("Nome: " + funcionario.getNome() + " | Idade: "
                    + funcionario.getDataNascimento().until(LocalDate.now()).getYears());
        });
    }

    private static void imprimirFuncionariosOrdemAlfabetica() {
        FUNCIONARIOS.stream().sorted(Comparator.comparing(Funcionario::getNome)).forEach(funcionario -> {
            System.out.println("Nome: " + funcionario.getNome() + " | Data Nascimento: "
                    + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    + " | Salário: R$"
                    + NumberFormat.getNumberInstance(new Locale("pt", "BR")).format(funcionario.getSalario())
                    + " | Funcão: "
                    + funcionario.getFuncao());
        });
    }

    private static void imprimirTotalSalarios() {
        BigDecimal totalSalarios = FUNCIONARIOS.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO,
                BigDecimal::add);
        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        System.out.println("Total Salários: R$" + nf.format(totalSalarios));
    }

    private static void imprimirQuantosSalariosMinimosGanhaCadaFuncionario() {
        FUNCIONARIOS.forEach(funcionario -> {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(SALARIO_MINIMO, RoundingMode.HALF_EVEN);
            System.out.println("Nome: " + funcionario.getNome() + " | Salários Minimos Ganhos: " + salariosMinimos);
        });
    }

}
