package org.automacao.control;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GestorWebDriver{

    private final WebDriver driver;

    public GestorWebDriver(){
        WebDriverManager.chromedriver().driverVersion("124.0.6367.119").setup(); // Deixe o WebDriverManager gerenciar a versão do ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        System.out.println("Abriu...");
        try {
            driver.get("http://aplicacao.gestordeservico.com/");
        }catch (NullPointerException e){
            System.out.println("Nao foi possivel abrir a url!");
        }

        atualizarFrame();
    }

    public WebElement encontrarElemento(String indetificador){
        int tentativas = 0;
        WebElement elemento = null;

        try{
            elemento = driver.findElement(By.xpath(indetificador));
        }catch (Exception ignored){
            tentativas++;
        }
        try{
            elemento = driver.findElement(By.id(indetificador));
        }catch (Exception  ignored){
            tentativas++;
        }
        try{
            elemento = driver.findElement(By.linkText(indetificador));
        }catch (Exception  ignored){
            tentativas++;
        }
        try{
            elemento = driver.findElement(By.cssSelector(indetificador));
        }catch (Exception  ignored){
            tentativas++;
        }
        try{
            elemento = driver.findElement(By.className(indetificador));
        }catch (Exception  ignored){
            tentativas++;
        }
        try{
            elemento = driver.findElement(By.tagName(indetificador));
        }catch (Exception  ignored){
            tentativas++;
        }

        if(tentativas == 6){
            throw new NoSuchElementException("Botão não encontrado na página!");
        }
        return elemento;
    }

    public WebElement encontrarElementoWait(String indetificador,int segundos){
        int tentativas = 0;
        WebElement elemento = null;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(segundos));

        try{
            elemento = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(indetificador)));
        }catch (Exception ignored){
            tentativas++;
        }
        try{
            elemento = wait.until(ExpectedConditions.elementToBeClickable(By.id(indetificador)));
        }catch (Exception  ignored){
            tentativas++;
        }
        try{
            elemento = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(indetificador)));
        }catch (Exception  ignored){
            tentativas++;
        }
        try{
            elemento = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(indetificador)));
        }catch (Exception  ignored){
            tentativas++;
        }
        try{
            elemento = wait.until(ExpectedConditions.elementToBeClickable(By.className(indetificador)));
        }catch (Exception  ignored){
            tentativas++;
        }
        try{
            elemento = wait.until(ExpectedConditions.elementToBeClickable(By.tagName(indetificador)));
        }catch (Exception  ignored){
            tentativas++;
        }

        if(tentativas == 6){
            throw new NoSuchElementException("Botão não encontrado na página!");
        }
        return elemento;
    }

    public void esperarEmSegundos(int segundos){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(segundos));
    }
    public void esperarMeioSegundo(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }

    public void atualizarFrame(){
        driver.switchTo().frame(0);
    }

    public WebDriver getInstanceDriver(){
        return driver;
    }

}
