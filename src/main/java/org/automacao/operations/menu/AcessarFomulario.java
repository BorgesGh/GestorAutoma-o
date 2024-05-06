package org.automacao.operations.menu;

import org.automacao.control.GestorWebDriver;
import org.automacao.interfaces.Tarefa;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openxmlformats.schemas.drawingml.x2006.chart.STHPercentWithSymbol;

public class AcessarFomulario implements Tarefa {
    @Override
    public void executar(GestorWebDriver driver) {
        try {
            WebElement menu = driver.encontrarElementoWait("/html/body/app-root/app-admin/app-gs-viewport/app-gs-topbar/div/button",1);
            menu.click();
        }catch(Exception e) {
            throw new NoSuchElementException("Não foi possível clicar no Menu!");
        }

        try {
            WebElement infraestrutura = driver.encontrarElemento("/html/body/app-root/app-admin/app-gs-viewport/div/app-gs-menu/div[1]/app-gs-menu-item[2]/div/div");
            infraestrutura.click();
        }catch(Exception e) {
            throw new NoSuchElementException("Não foi possível clicar na Infraestrutura!");
        }

        try {
            WebElement formulario = driver.encontrarElemento("/html/body/app-root/app-admin/app-gs-viewport/div/app-gs-menu/div[1]/app-gs-menu-item[2]/div/div[2]/app-gs-menu-item[9]/div/div");
            formulario.click();
        }catch (Exception e) {
            throw new NoSuchElementException("Não foi possível clicar no Formulário!");
        }
    }
}
