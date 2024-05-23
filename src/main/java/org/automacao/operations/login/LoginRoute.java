package org.automacao.operations.login;

import org.automacao.control.GestorWebDriver;
import org.automacao.interfaces.Tarefa;
import org.openqa.selenium.WebElement;

public class LoginRoute implements Tarefa {
    @Override
    public void executar(GestorWebDriver driver) {
        WebElement email = driver.encontrarElemento("/html/body/app-root/app-login/div/div[1]/form/div[2]/input");
        email.sendKeys("administrador");

        WebElement senha = driver.encontrarElemento("/html/body/app-root/app-login/div/div[1]/form/div[4]/p-password/div/input");
        senha.sendKeys("123");

        WebElement entrar = driver.encontrarElemento("/html/body/app-root/app-login/div/div[1]/form/div[5]/p-button/button/span[1]");
        entrar.click();
    }
}
