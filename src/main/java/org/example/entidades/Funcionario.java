package org.example.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa {

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public Funcionario(String nome, LocalDate dataNascimento) {
        super(nome, dataNascimento);
    }

    private BigDecimal salario;

    private String funcao;

    public Funcionario() {
        super();
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }


}
