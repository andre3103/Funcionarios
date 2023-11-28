package org.example;

import org.example.entidades.Funcionario;
import org.example.controller.FuncionarioController;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        FuncionarioController fc = new FuncionarioController();

        List<Funcionario> funcionarios = fc.inserirFuncionarios();

        funcionarios = fc.removeFuncionarioPorNome(funcionarios, "João");

        funcionarios = fc.atualizarSalarios(funcionarios, 10.0);

        fc.imprimeFuncionarios(funcionarios);

        Map<String, List<Funcionario>>  funcionariosPorFuncao = fc.mapearFuncionariosPor(funcionarios);

        fc.imprimeFuncionariosPorFuncao(funcionariosPorFuncao);

        fc.imprimeFuncionariosPorAniversario(funcionarios);
        fc.imprimeFuncionarioDeMaiorIdade(funcionarios);
        fc.imprimeFuncionariosOrdemAlfabetica(funcionarios);
        fc.imprimeTotalSalarios(funcionarios);
        fc.imprimeTotalSalariosMinimos(funcionarios);

    }






}