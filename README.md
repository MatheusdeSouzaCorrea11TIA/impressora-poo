==== IMPRESSORA ELGIN ====

-> COMO BAIXAR O PROJETO:

# Abra o repositório no github https://github.com/MatheusdeSouzaCorrea11TIA/impressora-poo

# Clique em "<> Code" e faça o download o ZIP

# Extraia a pasta

# Abra o IntelliJ e clique em Open para abrir um PROJETO

# Selecione a pasta extraida

# Selecione a classe Main execute o projeto em "RUN" ou clique F5

-> COMO USAR A IMPRESSORA:

# Primeiro configure a conexão

# Após isso abra a conexão

# Selecione a função desejada e siga as instruções

# Após finalizar feche a conexão

-> COMO FUNCIONA CADA FUNÇÃO:

## OBS ##
A maioria das funções verifica se a conexão está aberta antes de
executar suas funções

# ConfigurarConexão()
 //Configura a conexão e atribui valores às variáveis que vamos usar de parâmetros na hora de abrir conexão

# int AbreConexaoImpressora(int tipo, String modelo, String conexao, int param)
 //Abre a conexão utilizando as variáveis declaradas na configuração

# int FechaConexaoImpressora();
 //Fecha a conexão com a impressora caso esteja aberta

# int ImpressaoTexto(String dados, int posicao, int estilo, int tamanho);
 //Imprime um texto no papel da impressora escolhido pelo usuário

# int Corte(int avanco);
 //Corta o papel para poder destacar da impressora

# int ImpressaoQRCode(String dados, int tamanho, int nivelCorrecao);
 //Imprime um QR code que leva ao texto escolhido pelo usuário

# int ImpressaoCodigoBarras(int tipo, String dados, int altura, int largura, int HRI);
 //Imprime um código de barras

# int AvancaPapel(int linhas);
 //Empurra o papel para cima para poder imprimir outras coisas

# int AbreGavetaElgin();
 //Abre a gaveta da impressora

# int AbreGaveta(int pino, int ti, int tf);
 //Também abre a Gaveta da impressora

# int SinalSonoro(int qtd, int tempoInicio, int tempoFim);
 //Emite um sinal sonoro por um período de tempo

# int ImprimeXMLSAT(String dados, int param);
 //Imprime um recibo

# int ImprimeXMLCancelamentoSAT(String dados, String assQRCode, int param);String modelo, String conexao, int param);
 //Imprime um recibo de cancelamento com um QR code

-> AUTORES:
# Bianca
# David Costa
# Gustavo Henrique
# Matheus de Souza Correa
# Pedro de Oliveira
