package org.automacao.control;

import org.automacao.GestorWebDriver;

public class NavegadorController {

    private static GestorWebDriver driverGestor;

    private NavegadorController() {
        driverGestor = new GestorWebDriver();
    }

    public static GestorWebDriver getDriver() {
        return driverGestor;
    }

}
