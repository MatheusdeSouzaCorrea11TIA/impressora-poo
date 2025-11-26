import com.sun.jna.Library;
import com.sun.jna.Native;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;

public class Main {

    // Interface que representa a DLL, usando JNA
    public interface ImpressoraDLL extends Library {

        // Caminho completo para a DLL
        ImpressoraDLL INSTANCE = (ImpressoraDLL) Native.load(
                "C:\\Users\\souza_correa\\Downloads\\Java-Aluno EM\\E1_Impressora01.dll",
                ImpressoraDLL.class
        );

        int AbreConexaoImpressora(int tipo, String modelo, String conexao, int param);

        int FechaConexaoImpressora();

        int ImpressaoTexto(String dados, int posicao, int estilo, int tamanho);

        int Corte(int avanco);

        int ImpressaoQRCode(String dados, int tamanho, int nivelCorrecao);

        int ImpressaoCodigoBarras(int tipo, String dados, int altura, int largura, int HRI);

        int AvancaPapel(int linhas);

        int StatusImpressora(int param);

        int AbreGavetaElgin();

        int AbreGaveta(int pino, int ti, int tf);

        int SinalSonoro(int qtd, int tempoInicio, int tempoFim);

        int ModoPagina();

        int LimpaBufferModoPagina();

        int ImprimeModoPagina();

        int ModoPadrao();

        int PosicaoImpressaoHorizontal(int posicao);

        int PosicaoImpressaoVertical(int posicao);

        int ImprimeXMLSAT(String dados, int param);

        int ImprimeXMLCancelamentoSAT(String dados, String assQRCode, int param);
    }

    private static boolean conexaoAberta = false;
    private static int tipo;
    private static String modelo;
    private static String conexao;
    private static int parametro;
    private static final Scanner scanner = new Scanner(System.in);

    private static String capturarEntrada(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public static void configurarConexao() { //Configura a conexão antes de abrir
        if (!conexaoAberta) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o tipo de conexão (ex: 1 para USB, 2 para serial, etc.): ");
            tipo = scanner.nextInt();

            System.out.println("Digite o modelo da impressora: ");
            modelo = scanner.next();

            System.out.println("Digite a conexão: ");
            conexao = scanner.next();

            System.out.println("Digite o parâmetro: ");
            parametro = scanner.nextInt();

            System.out.println("Configuração concluída!");
        } else {
            System.out.println("Conexão já está aberta!");
        }
    }

    public static void abrirConexao () { //Abre a conexão após configurar
        if (!conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(tipo, modelo, conexao, parametro);

            if (retorno == 0) {
                conexaoAberta = true;
                System.out.println("Conexão aberta com sucesso.");
            } else {
                System.out.println("Erro ao abrir conexão. Código de erro: " + retorno);
            }
        } else {
            System.out.println("Conexão já está aberta.");
        }
    }


    public static void fecharConexao () { //Fecha a conexão se estiver aberta
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.FechaConexaoImpressora();

            if (retorno == 0) {
                conexaoAberta = false;
                System.out.println("Conexão fechada com sucesso.");
            } else {
                System.out.println("Erro ao fechar conexão. Código de erro: " + retorno);
            }
        } else {
            System.out.println("Conexão já está fechada.");
        }
    }

    public static void impressaoTexto() { //Imprime um papel com o texto escolhido
        if (conexaoAberta) {
            System.out.println("Digite o texto a ser impresso: ");
            String dados = scanner.nextLine();

            int retorno = ImpressoraDLL.INSTANCE.ImpressaoTexto(dados, 1, 4, 0);

            if (retorno == 0) {
                System.out.println("Impressão concluída.");
            } else {
                System.out.println("Erro ao imprimir. Código de erro: " + retorno);
            }
        } else {
            System.out.println("A Conexão está fechada.");
        }
    }

    public static void impressaoQRCode() { //Imprime um QR code q leva ao texto escolhido
        if (conexaoAberta) {
            System.out.println("Digite o texto a ser impresso: ");
            String dados = scanner.nextLine();

            int retorno = ImpressoraDLL.INSTANCE.ImpressaoQRCode(dados, 6, 4);

            if (retorno == 0) {
                System.out.println("Impressão concluída.");
            } else {
                System.out.println("Erro ao imprimir. Código de erro: " + retorno);
            }
        } else {
            System.out.println("A Conexão está fechada.");
        }
    }

    public static void impressaoCodigoBarras() { //Imprime um codigo de barras
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(8, "{A012345678912", 100, 2, 3);

            if (retorno == 0) {
                System.out.println("Impressão concluída.");
            } else {
                System.out.println("Erro ao imprimir. Código de erro: " + retorno);
            }
        } else {
            System.out.println("A Conexão está fechada.");
        }
    }

    public static void avancaPapel() { //Empurra o papel
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.AvancaPapel(2);

            if (retorno == 0) {
                System.out.println("Avanço concluído.");
            } else {
                System.out.println("Erro ao avançar. Código de erro: " + retorno);
            }
        } else {
            System.out.println("A Conexão está fechada.");
        }
    }

    public static void abreGavetaElgin() { //Abre a gavetinha da impressora
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.AbreGavetaElgin();

            if (retorno == 0) {
                System.out.println("Gaveta aberta com sucesso!");
            } else {
                System.out.println("Erro ao abrir gaveta. Código de erro: " + retorno);
            }
        } else {
            System.out.println("A Conexão está fechada.");
        }
    }

    public static void abreGaveta() { //Abre a gavetinha também
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.AbreGaveta(1, 5, 10);

            if (retorno == 0) {
                System.out.println("Gaveta aberta com sucesso!");
            } else {
                System.out.println("Erro ao abrir gaveta. Código de erro: " + retorno);
            }
        } else {
            System.out.println("A Conexão está fechada.");
        }
    }

    public static void sinalSonoro() { //Emite um sinal sonoro
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.SinalSonoro(4,5,5);

            if (retorno == 0) {
                System.out.println("Sinal emitido com sucesso!");
            } else {
                System.out.println("Erro ao emitir sinal. Código de erro: " + retorno);
            }
        } else {
            System.out.println("A Conexão está fechada.");
        }
    }

    public static void imprimeXMLSAT() { //Imprime um recibo
        String dados = "path=C:\\Users\\souza_correa\\Downloads\\Java-Aluno EM\\XMLSAT.xml";

        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(dados,0);

            if (retorno == 0) {
                System.out.println("Impressão feita com sucesso!");
            } else {
                System.out.println("Erro ao imprimir. Código de erro: " + retorno);
            }
        } else {
            System.out.println("A Conexão está fechada.");
        }
    }

    public static void imprimeXMLCancelamentoSAT() { //Imprime um recibo de cancelamento com um QR code
        if (conexaoAberta) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(".")); // Diretório atual do programa
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos XML", "xml"));
            String assQRCode = 	"Q5DLkpdRijIRGY6YSSNsTWK1TztHL1vD0V1Jc4spo/CEUqICEb9SFy82ym8EhBRZjbh3btsZhF+sjHqEMR159i4agru9x6KsepK/q0E2e5xlU5cv3m1woYfgHyOkWDNcSdMsS6bBh2Bpq6s89yJ9Q6qh/J8YHi306ce9Tqb/drKvN2XdE5noRSS32TAWuaQEVd7u+TrvXlOQsE3fHR1D5f1saUwQLPSdIv01NF6Ny7jZwjCwv1uNDgGZONJdlTJ6p0ccqnZvuE70aHOI09elpjEO6Cd+orI7XHHrFCwhFhAcbalc+ZfO5b/+vkyAHS6CYVFCDtYR9Hi5qgdk31v23w==";

            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String path = selectedFile.getAbsolutePath();

                try {
                    String conteudoXML = lerArquivoComoString(path);
                    int retImpCanXMLSAT = ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(conteudoXML, assQRCode, 0);
                    ImpressoraDLL.INSTANCE.Corte(5);
                    System.out.println(retImpCanXMLSAT == 0 ? "Impressão de XML de Cancelamento realizada" : "Erro ao realizar a impressão do XML de Cancelamento SAT! Retorno: " + retImpCanXMLSAT);
                } catch (IOException e) {
                    System.out.println("Erro aob  ler o arquivo XML: " + e.getMessage());
                }
            } else {
                System.out.println("Nenhum arquivo selecionado.");
            }
        } else {
            System.out.println("Erro: Conexão não está aberta.");
        }
    }

    public static void main (String[]args){
        while (true) {
            //Printa o menu e roda o programa toda vez que uma função é escolhida e completa.
            System.out.println("\n*************************************************");
            System.out.println("**************** MENU IMPRESSORA ****************");
            System.out.println("*************************************************\n");

            System.out.println("1  - Configurar Conexao");
            System.out.println("2  - Abrir Conexao");
            System.out.println("3  - Impressao Texto");
            System.out.println("4  - Impressao QRCode");
            System.out.println("5  - Impressao Cod Barras");
            System.out.println("6  - Impressao XML SAT");
            System.out.println("7  - Impressao XML Canc SAT");
            System.out.println("8  -  Abrir Gaveta Elgin");
            System.out.println("9  - Abrir Gaveta");
            System.out.println("10 - Sinal Sonoro");
            System.out.println("0 - Fechar Conexao e Sair");

            //Captura a escolha do usuário
            String escolha = capturarEntrada("\nDigite a opção desejada: ");

            if (escolha.equals("0")) {
                fecharConexao();
                System.out.println("Programa encerrado.");
                break;
            }

            //Alterna entre as funções de acordo com a opção escolhida
            switch (escolha) {
                case "1":
                    configurarConexao();
                    break;
                case "2":
                    abrirConexao();
                    break;
                case "3":
                    impressaoTexto();
                    ImpressoraDLL.INSTANCE.Corte(5);
                    break;

                case "4":
                    impressaoQRCode();
                    ImpressoraDLL.INSTANCE.Corte(3);
                    break;
                case "5":
                    impressaoCodigoBarras();
                    ImpressoraDLL.INSTANCE.Corte(3);
                    break;
                case "6":
                    imprimeXMLSAT();
                    ImpressoraDLL.INSTANCE.Corte(3);
                    break;

                case "7":
                    imprimeXMLCancelamentoSAT();
                    ImpressoraDLL.INSTANCE.Corte(3);
                    break;

                case "8":
                    abreGavetaElgin();
                    break;
                case "9":
                    abreGaveta();
                    break;
                case "10":
                    sinalSonoro();
                    break;

                default:
                    System.out.println("OPÇÃO INVÁLIDA");
            }
        }

        scanner.close();
    }

    private static String lerArquivoComoString (String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        byte[] data = fis.readAllBytes();
        fis.close();
        return new String(data, StandardCharsets.UTF_8);
    }
}

