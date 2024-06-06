package org.automacao;

import org.automacao.control.GerenciadorDePlanilha;
import org.automacao.control.GestorWebDriver;
import org.automacao.interfaces.Tarefa;
import org.automacao.operations.login.LoginRoute;
import org.automacao.operations.processos.InserirChecklist;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        System.out.println(GerenciadorDePlanilha.getInstance().getProcessos());

        GestorWebDriver driver = new GestorWebDriver();

        Tarefa login = new LoginRoute();
        login.executar(driver);

        Tarefa inserirCheckList = new InserirChecklist();
        inserirCheckList.executar(driver);

        System.out.println("Programa Finalizado!!");

    }
}