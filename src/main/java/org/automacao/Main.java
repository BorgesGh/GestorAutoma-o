package org.automacao;

import org.automacao.control.NavegadorController;
import org.automacao.interfaces.Tarefa;
import org.automacao.operations.InserirProcesso;
import org.automacao.operations.Login;

public class Main {
    public static void main(String[] args) {

        Tarefa tarefa = new Login();
        tarefa.executar();
        tarefa = new InserirProcesso();
        tarefa.executar();


    }
}