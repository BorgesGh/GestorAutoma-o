package org.automacao;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class GestorWebDriver{

    private WebDriver driver;

    public GestorWebDriver(){
        try {
            driver.get("http://aplicacao.gestordeservico.com/");
        }catch (NullPointerException e){
            System.out.println("Nao foi possivel abrir a url!");
        }
    }

    public void clicarNoBotao(String indetificador){
        int tentativas = 0;

        try{
            driver.findElement(By.xpath(indetificador)).click();
        }catch (NoSuchElementException ignored){
            tentativas++;
        }
        try{
            driver.findElement(By.id(indetificador)).click();
        }catch (NoSuchElementException ignored){
            tentativas++;
        }
        try{
            driver.findElement(By.linkText(indetificador)).click();
        }catch (NoSuchElementException ignored){
            tentativas++;
        }
        if(tentativas == 3){
            throw new NoSuchElementException("Botão não encontrado na página!");
        }

    }

}
