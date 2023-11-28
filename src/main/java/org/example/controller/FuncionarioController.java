package org.example.controller;

import org.example.entidades.Funcionario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.*;

public class FuncionarioController {

    TemporalField tf;

    public List<Funcionario> inserirFuncionarios(){
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 05, 12), BigDecimal.valueOf(2284.38), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 05, 2), BigDecimal.valueOf(9836.14), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 01, 05), BigDecimal.valueOf(2234.68), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 03, 31), BigDecimal.valueOf(4071.84), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 07, 8), BigDecimal.valueOf(3017.45), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), BigDecimal.valueOf(1606.85), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), BigDecimal.valueOf(2799.93), "Gerente"));

        return funcionarios;
    }

    public List<Funcionario> removeFuncionarioPorNome( List<Funcionario> funcionarios, String nome){

        funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));

        return funcionarios;
    }

    public void imprimeFuncionarios( List<Funcionario> funcionarios){
        funcionarios.forEach( funcionario -> {
            System.out.println(
                    funcionario.getNome() + " | " +
                    formatarData(funcionario.getDataNascimento()) + " | " +
                    formatarBigDecimal(funcionario.getSalario()) + " | " +
                    funcionario.getFuncao()
            );
        });
    }

    public List<Funcionario> atualizarSalarios( List<Funcionario> funcionarios, Double porcentagem){
        final Integer[] index = {0};
        Double aumento = 1.0+ porcentagem/ 100.0;
        funcionarios.forEach( funcionario -> {
            funcionarios.get(index[0]).setSalario(funcionario.getSalario().multiply(new BigDecimal(aumento)));
            index[0]++;
        });

        return funcionarios;
    }

    public Map mapearFuncionariosPor(List<Funcionario> funcionarios){
        Map<String, List<Funcionario>> funcionariosPorFuncao = new TreeMap<>();
        funcionarios.forEach( funcionario -> {
            if(funcionariosPorFuncao.containsKey(funcionario.getFuncao())){
                List<Funcionario> lista = funcionariosPorFuncao.get(funcionario.getFuncao());
                lista.add(funcionario);
                funcionariosPorFuncao.replace(funcionario.getFuncao(), lista);
            }else{
                List<Funcionario> lista = new ArrayList<>();
                lista.add(funcionario);
                funcionariosPorFuncao.put(funcionario.getFuncao(), lista);
            }
        });
        return funcionariosPorFuncao;
    }

    public void imprimeFuncionariosPorFuncao( Map<String, List<Funcionario>> funcionariosPorFuncao){
        System.out.println("\n\n");
        funcionariosPorFuncao.forEach((funcao, funcionarios) -> {
            System.out.println("------------ "+funcao+" -------------");
            imprimeFuncionarios(funcionarios);
        });
    }

    public void imprimeFuncionariosPorAniversario( List<Funcionario> funcionarios){
        List<Funcionario> funcionariosSelecionados = new ArrayList<>();
        System.out.println("\n\n############# Funcionarios Por Aniversário ####################");

        funcionarios.forEach(funcionario -> {
            if(funcionario.getDataNascimento().getMonth().getValue() == 10 || funcionario.getDataNascimento().getMonth().getValue() == 12){
                funcionariosSelecionados.add(funcionario);
            }
        });
        imprimeFuncionarios(funcionariosSelecionados);
    }

    public void imprimeFuncionarioDeMaiorIdade( List<Funcionario> funcionarios){
        Funcionario funcionarioSelecionado = new Funcionario();
        System.out.println("\n\n############# Funcionário de Maior Idade ####################");
        funcionarioSelecionado = funcionarios.get(0);
        for (Funcionario funcionario : funcionarios) {
            if(funcionario.getDataNascimento().isBefore(funcionarioSelecionado.getDataNascimento())){
                funcionarioSelecionado = funcionario;
            }
        }
        System.out.println(
                funcionarioSelecionado.getNome() + " | " +
                idade(funcionarioSelecionado.getDataNascimento()) + " anos"
        );
    }

    public void imprimeFuncionariosOrdemAlfabetica( List<Funcionario> funcionarios){
        System.out.println("\n\n############# Funcionários Por Ordem Alfabetica ####################");
        funcionarios.sort(new Comparator<Funcionario>() {
            @Override
            public int compare(Funcionario o1, Funcionario o2) {
                return o1.getNome().compareTo(o2.getNome());
            }
        });
        imprimeFuncionarios(funcionarios);
    }

    public void imprimeTotalSalarios( List<Funcionario> funcionarios){
        System.out.println("\n\n############# Total dos Salarios ####################");
        BigDecimal totalSalarios = new BigDecimal(0);
        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }
        System.out.println(formatarBigDecimal(totalSalarios));
    }

    public void imprimeTotalSalariosMinimos( List<Funcionario> funcionarios){
        System.out.println("\n\n############# Salarios Por Funcionario ####################");
        Map<String, String> salariominimoPorFuncionario = new HashMap<>();
        for (Funcionario funcionario : funcionarios) {
            salariominimoPorFuncionario.put(funcionario.getNome(), salariosMinimo(funcionario.getSalario()));
        }
        salariominimoPorFuncionario.forEach( (s, s2) -> {
            System.out.println(s + " | " + s2);
        });

    }

    public String formatarData(LocalDate data){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return  formatador.format(data);
    }

    public String formatarBigDecimal(BigDecimal valor){
        DecimalFormat formatador = new DecimalFormat();
        formatador.applyPattern("#,#00.0#");
        return formatador.format(valor);
    }

    public String formatarDouble(Double valor){
        DecimalFormat formatador = new DecimalFormat();
        formatador.applyPattern("#,##0.0#");
        return formatador.format(valor);
    }

    public String idade(LocalDate dataNascimento){
        Period tempoDeVida = dataNascimento.until(LocalDate.now());

        return String.valueOf(tempoDeVida.getYears());
    }

    public String salariosMinimo(BigDecimal valor){
        Double salarioMinimo = 1212.0;
        Double salario = valor.doubleValue(), qtdeSalarios;
        qtdeSalarios = salario/salarioMinimo;


        return formatarDouble(qtdeSalarios);
    }
}
