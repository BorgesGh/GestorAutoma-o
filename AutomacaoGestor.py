import pyautogui as pg
import time
import openpyxl

#Acessar área de inserção dos tempos

time.sleep(5)#Tempo para acessar outra tela, no caso o navegador

#A planilha de processos e tempos devem estar Na seguinte formatação (00:30) -> 30 minutos
# Coluna A  / Coluna B 
# Pintura  /   00:30 
# Trocar  /    01:30 
# ....        ......

planilhaProcessos = openpyxl.load_workbook('Teste.xlsx')
sheet = planilhaProcessos['Sheet1']

OrdemDeServico = [] #Nome da OS e tempo

def leituraDeProcessos(sheet):
    Processos = []

    linhas = sheet.max_row

    for i in range(1,linhas + 1):
        celula = sheet.cell(row = i,column = 1)
        Processos.append(celula.value)
        
    return Processos

def leituraDeTempos(sheet):
    
    Tempos = []

    linhas = sheet.max_row

    for i in range(1,linhas + 1):
        celula = sheet.cell(row = i,column = 2)
        Tempos.append(celula.value + ":00")
        
    return Tempos


def adicionarProcesso(Processos):

    #Botao "novo"
    botaoNovo = pg.position(x=411, y=224)
    

    for i,e in enumerate(Processos):
        #Clicar n botão "Novo"
        
        pg.click(botaoNovo)
        #Digitar nome do processo...
        pg.write(Processos[i])
        #Pressionar Enter para salvar...
        pg.press('enter')
        #Agurdar alguns segundos de save...
        time.sleep(3)
        
def adicionarTempoProcesso(Processos, Tempos):


    #botao de selecionar o processo
    botaoProcesso = pg.position(x=744, y=260)

    #Barra de pesquisa
    barraPesquisa = pg.position(x=759, y=305)

    #Primeira opção dos processos
    primeiraOp = pg.position(x=755, y=366)

    #Area de tempo padrao
    areaTempo = pg.position(x=1704, y=309)

    #Botão salvar
    salvar = pg.position(x=1148, y=934)
    #Pressionando tab e seguida, é possivel cofirmar o tempo padrão
    
    for i,e in enumerate(Processos):

        pg.click(botaoProcesso)
        time.sleep(0.5)
        pg.write(Processos[i]) #Escreve o nome do processo como esta na planilha
        time.sleep(0.5)
        pg.click(primeiraOp)

        pg.click(areaTempo)
        pg.write(Tempos[i]) #Escreve o tempo padrão
        time.sleep(0.5)
        pg.press("tab") #Acessar o botão de confirmação
        pg.press("enter") 

        time.sleep(0.5)
        pg.click(salvar)
        time.sleep(3)

        #Desmarcar o processo
        pg.click(botaoProcesso)
        pg.click(primeiraOp)

        #Apagar o processo que havia escrito
        pg.click(barraPesquisa)
        pg.keyDown('ctrl')
        pg.press('a')
        pg.press('delete')
        pg.keyUp('ctrl')

        pg.click(botaoProcesso)

def manipularSheet(Processos):
    
    for i,e in enumerate(Processos):
        Processos[i] = Processos[i].capitalize() #Transforma os processos com nome padrao
        sheet["A" + str(i + 1)] = Processos[i]

    planilhaProcessos.save('Teste.xlsx')

#main =====
Processos = leituraDeProcessos(sheet)
Tempos = leituraDeTempos(sheet)

#Descomentar apenas o que será feito

#adicionarProcesso(Processos)
#adicionarTempoProcesso(Processos,Tempos)

