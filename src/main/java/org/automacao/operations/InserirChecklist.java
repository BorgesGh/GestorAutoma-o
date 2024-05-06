package org.automacao.operations;

import org.automacao.control.GerenciadorDePlanilha;
import org.automacao.control.GestorWebDriver;
import org.automacao.control.KeyboardControl;
import org.automacao.domain.Formulario;
import org.automacao.interfaces.Tarefa;
import org.automacao.operations.menu.AcessarFomulario;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class InserirChecklist implements Tarefa {

    List<Formulario> questionario = new ArrayList<>();

    public InserirChecklist() {
        carregarFormularios();

    }

    @Override
    public void executar(GestorWebDriver driver) {

        //Chegar na tela de Formulario
        Tarefa acessarChecklist = new AcessarFomulario();
        acessarChecklist.executar(driver);
        inserirDados(driver);



    }


    private void carregarFormularios(){
        questionario.add(new Formulario("Foram recolhidos os materiais obrigatórios?",
                                    "Acessórios (bucha, parafuso, luva); Cabo CA; Caixa de passagem; Conectores; Discos; Disjuntor; DPS; Fitas isolantes; Grampo de Aterramento; Haste Terra; Quadro de Distribuição; Tubulação",
                                        true));
        questionario.add(new Formulario("Foram recolhidos os materiais específicos para solo ou estrutura?",
                                        "Areia, brita e cimento; Eletrodo; Perfil; Tábua de Madeira; Tinta",
                                        true));
        questionario.add(new Formulario("Foram recolhidas as ferramentas obrigatórias?",
                                        "Câmera termográfica; Chave de fenda; Chave Philips; Alicate; Chave de Boca; Drone; Esmerilhadeira; Furadeira; Marreta; Multímetro; Parafusadeira; Terrômetro",
                                        true));
        questionario.add(new Formulario("Foram recolhidas as ferramentas para solo ou estrutura?",
                                        "Máquina de Solda; Máscara de Solda; Perfurador de Solo?",
                                        true));
    }

    private void inserirDados(GestorWebDriver driver){
        //Esse tipo provavelmente será retirado, mas os tipos não variam de acordo com o processo
        String tipo = "Alternador";

        KeyboardControl teclado = new KeyboardControl(driver.getInstanceDriver());

        List<String> processos = GerenciadorDePlanilha.getInstance().getProcessos();

        for(String processo : processos) {
            for(Formulario formulario : questionario) {

                WebElement botaoNovo = driver.encontrarElemento("/html/body/app-root/app-admin/app-gs-viewport/div/div/gs-usuario-table/gs-table/div/p-toolbar/div/div/div/button[1]");
                botaoNovo.click();

                //Selecionar Processo
                WebElement boxProcesso = driver.encontrarElementoWait("//*[@id=\"pr_id_8_label\"]",1);
                boxProcesso.click();
                driver.esperarEmSegundos(1);
                teclado.digitarTexto(processo); //Digitar o processo

                WebElement primeiroProcessoBox = driver.encontrarElementoWait("/html/body/app-root/app-admin/app-gs-viewport/div/div/gs-verificacao-form/div/gs-form/form/div[1]/gs-input/span[1]/p-dropdown/div/div[3]/div[2]/ul/p-dropdownitem[1]/li",1);
                primeiroProcessoBox.click();
                System.out.println("Passou da qui...");

                //Selecionar Tipo
                teclado.teclar(Keys.TAB);
                teclado.teclar(Keys.SPACE);
                driver.esperarEmSegundos(1);
                teclado.digitarTexto(tipo);

                WebElement primeiroTipoBox = driver.encontrarElementoWait("/html/body/app-root/app-admin/app-gs-viewport/div/div/gs-verificacao-form/div/gs-form/form/div[2]/gs-input/span[1]/p-dropdown/div/div[3]/div[2]/ul/p-dropdownitem[1]/li",1);
                primeiroTipoBox.click();

                //Escrever tipo
                teclado.teclar(Keys.TAB);
                teclado.digitarTexto(formulario.getTipo());

                //Escrever Descrição
                teclado.teclar(Keys.TAB);
                teclado.digitarTexto(formulario.getDescricao());

                //verificar True ou False
                teclado.teclar(Keys.TAB);
                if(!formulario.isResposta()){
                    teclado.teclar(Keys.SPACE);
                }

                //Clicar em salvar
                teclado.teclar(Keys.TAB);
                teclado.teclar(Keys.SPACE);

            }
        }
    }
}
