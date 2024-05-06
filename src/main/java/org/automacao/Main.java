package org.automacao;

import org.automacao.control.GerenciadorDePlanilha;
import org.automacao.control.GestorWebDriver;
import org.automacao.interfaces.Tarefa;
import org.automacao.operations.InserirChecklist;
import org.automacao.operations.Login;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        GestorWebDriver driver = new GestorWebDriver();
//        driver.esperarEmSegundos(5);

        Tarefa login = new Login();
        login.executar(driver);

        Tarefa inserirCheckList = new InserirChecklist();
        inserirCheckList.executar(driver);


        System.out.println(GerenciadorDePlanilha.getInstance().getProcessos());



    }
}