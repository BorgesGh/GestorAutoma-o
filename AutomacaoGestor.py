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
def limparConteudo():
    pg.keyDown('ctrl')
    pg.press('a')
    pg.press('delete')
    pg.keyUp('ctrl')
    time.sleep(1)


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
    # print(linhas)

    for i in range(1,linhas + 1):
        celula = sheet.cell(row = i,column = 2)
        celula = celula.value[1:] #Tirar o ' da frente do tempo
        # print(str(celula))
        Tempos.append(celula + ":00") #
        # print(Tempos)
        
        
    return Tempos



def adicionarProcesso(Processos):

    #Menu aberto, 90% de zoom e barra exposta

    Processos = Processos[374:] # Pegar apartir do ultimo que parei

    #Botao "novo"
    botaoNovo = pg.position(x=399, y=303)
    
    for i,e in enumerate(Processos):
        #Clicar no botão "Novo"
        
        pg.click(botaoNovo)
        #Digitar nome do processo...
        pg.write(Processos[i])
        #Pressionar Enter para salvar...
        pg.press('enter')
        #Agurdar alguns segundos de save...
        time.sleep(3)
        
def adicionarTempoProcesso(Processos, Tempos):

    #Menu fechado e 90% de zoom, Barra de tarefas expostas

    #botao de selecionar o processo
    botaoProcesso = pg.position(x=758, y=365)

    #Barra de pesquisa
    barraPesquisa = pg.position(x=748, y=323)

    #Primeira opção dos processos
    primeiraOp = pg.position(x=785, y=484)

    #Area de tempo padrao
    areaTempo = pg.position(x=1512, y=351)

    #Botão salvar
    salvar = pg.position(x=1010, y=865)
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
        time.sleep(6)

        #Desmarcar o processo
        pg.click(botaoProcesso)
        pg.click(primeiraOp)

        #Apagar o processo que havia escrito
        pg.click(barraPesquisa)
        limparConteudo()

        pg.click(botaoProcesso)

def manipularSheet(Processos):
    
    for i,e in enumerate(Processos):
        Processos[i] = Processos[i].capitalize() #Transforma os processos com nome padrao
        sheet["A" + str(i + 1)] = Processos[i]

    planilhaProcessos.save('Teste.xlsx')


def adicionarFeriados(indiceDoDia):

    #Menu retraido, 90% de zoom

    #Novo botao
    novo = pg.position(x=146, y=247)

    #Descricao
    descricao = pg.position(x=926, y=334)
   #Data (13/02)
    dataAba = pg.position(x=927, y=519)

    datas = []
    datas.append(pg.position(x=749, y=744)) #Dia 12
    datas.append(pg.position(x=873, y=735)) #Dia 13

    #Horario 10:15de inicio
    horaInicial = pg.position(x=813, y=616)

    #Duração
    duracao = pg.position(x=989, y=610)

    #Falta botão
    faltaBotao = pg.position(x=1302, y=614)

    pg.click(novo)
    time.sleep(2)
    pg.click(descricao)
    pg.write("Carnaval")

    pg.click(dataAba)
    pg.click(datas[indiceDoDia])
    
    time.sleep(2)
    pg.click(horaInicial)
    limparConteudo()
    pg.write("07:15")

    pg.click(duracao)
    time.sleep(1)
    pg.write("10:15") # 1 dia de trabalho

    pg.click(faltaBotao)

    #Tempo para selecionar e salvar
    time.sleep(10)

    return 0

def clickar(quantidade):
    
    for i in range(quantidade):
        pg.click()
        time.sleep(0.5)
        pg.press('enter')
        time.sleep(2)


#main ====== Carnaval
Processos = leituraDeProcessos(sheet) 
Tempos = leituraDeTempos(sheet)

#Descomentar apenas o que será feito
#adicionarProcesso(Processos)

adicionarTempoProcesso(Processos,Tempos)

# for i in Tempos:
#     print(i)
# for p in Processos:
#     print(p)

print(pg.position())