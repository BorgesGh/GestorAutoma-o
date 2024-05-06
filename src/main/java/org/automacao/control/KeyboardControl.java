package org.automacao.control;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class KeyboardControl {

    private WebDriver driver;
    public KeyboardControl(WebDriver driver) {
        this.driver = driver;
    }

    public void digitarTexto(String texto) {
        new Actions(driver).sendKeys(texto).perform();
    }

    public void teclar(Keys key) {
        new Actions(driver).sendKeys(key).perform();
    }
}
