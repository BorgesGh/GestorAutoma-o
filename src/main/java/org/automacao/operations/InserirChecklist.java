package org.automacao.operations;

import org.automacao.control.GerenciadorDePlanilha;
import org.automacao.control.GestorWebDriver;
import org.automacao.control.KeyboardControl;
import org.automacao.domain.Formulario;
import org.automacao.interfaces.Tarefa;
import org.automacao.operations.menu.AcessarFomulario;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class InserirChecklist implements Tarefa {

    List<Formulario> questionario = new ArrayList<>();
    List<String> processos;
    KeyboardControl teclado;

    public InserirChecklist() {
        processos = GerenciadorDePlanilha.getInstance().getProcessos();
        carregarFormularios();

    }

    @Override
    public void executar(GestorWebDriver driver) {
        teclado = new KeyboardControl(driver.getInstanceDriver());
        //Chegar na tela de Formulario
        Tarefa acessarChecklist = new AcessarFomulario();
        acessarChecklist.executar(driver);
        inserirDados(driver);

    }

    private void inserirDados(GestorWebDriver driver){
        //Esse tipo provavelmente será retirado, mas os tipos não variam de acordo com o processo
        String tipo = "Alternador";

        for(String processo : processos) {
            for(Formulario formulario : questionario) {


                WebElement botaoNovo = driver.encontrarElemento("/html/body/app-root/app-admin/app-gs-viewport/div/div/gs-usuario-table/gs-table/div/p-toolbar/div/div/div/button[1]");
                botaoNovo.click(); //                                       /html/body/app-root/app-admin/app-gs-viewport/div/div/gs-usuario-table/gs-table/div/p-toolbar/div/div/div/button[1]
                System.out.println("Passou da qui");

                //Encontrar Processo
                inserirStringBoxList(driver,"/html/body/app-root/app-admin/app-gs-viewport/div/div/gs-verificacao-form/div/gs-form/form/div[1]/gs-input/span[1]/p-dropdown/div",
                                        processo);

                //Clicar na primeira opção
                driver.esperarMeioSegundo();
                WebElement primeiroProcessoBox = driver.encontrarElemento("/html/body/app-root/app-admin/app-gs-viewport/div/div/gs-verificacao-form/div/gs-form/form/div[1]/gs-input/span[1]/p-dropdown/div/div[3]/div[2]/ul/p-dropdownitem[1]/li");
                primeiroProcessoBox.click();

                //Encontrar Tipo
                inserirStringBoxList(driver,"/html/body/app-root/app-admin/app-gs-viewport/div/div/gs-verificacao-form/div/gs-form/form/div[2]/gs-input/span[1]/p-dropdown/div/span",
                                        tipo);

                //Clicar na primeira opção
                driver.esperarMeioSegundo();
                WebElement primeiroTipoBox = driver.encontrarElemento("/html/body/app-root/app-admin/app-gs-viewport/div/div/gs-verificacao-form/div/gs-form/form/div[2]/gs-input/span[1]/p-dropdown/div/div[3]/div[2]/ul/p-dropdownitem[1]/li");
                primeiroTipoBox.click();

                //Escrever tipo
                WebElement textTipo = driver.encontrarElemento("/html/body/app-root/app-admin/app-gs-viewport/div/div/gs-verificacao-form/div/gs-form/form/div[3]/gs-input/span[1]/input");
                textTipo.click();
                teclado.digitarTexto(formulario.getTipo());

                //Escrever Descrição
                WebElement textDescricao = driver.encontrarElemento("/html/body/app-root/app-admin/app-gs-viewport/div/div/gs-verificacao-form/div/gs-form/form/div[4]/gs-input/span[1]/input");
                textDescricao.click();
                teclado.digitarTexto(formulario.getDescricao());

                //verificar True ou False
                teclado.teclar(Keys.TAB);
                if(!formulario.isResposta()){
                    teclado.teclar(Keys.SPACE);
                }

                //Clicar em salvar
                teclado.teclar(Keys.TAB);
                teclado.teclar(Keys.SPACE);
                driver.esperarEmSegundos(1);
            }
        }
    }
    private void carregarFormularios(){
        questionario.add(new Formulario("Foram recolhidos os materiais obrigatórios?",
                "Acessórios (bucha, parafuso, luva); Cabo CA; Caixa de passagem; Conectores; Discos; Disjuntor; DPS; Fitas isolantes; Grampo de Aterramento; Haste Terra; Quadro de Distribuição; Tubulação",
                false));
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

    public void inserirStringBoxList(GestorWebDriver driver, String indetificador, String string){
        driver.esperarMeioSegundo();
        WebElement boxProcesso = driver.encontrarElemento(indetificador);
        boxProcesso.click();
        teclado.digitarTexto(string);
    }
}
