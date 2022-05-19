import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Controller {

    private static String relatorio1 = "nome do cliente,quantidade de produtos no pedido,prazo de empacotamento,tempo para ser empacotado em minutos,tempo de chegada,minutos em que foi finalizado,dentro do prazo?\n";
    private static double tempo1 = 0.0;
    private static boolean marcacaoDas12FoiFeita1 = false;
    private static boolean marcacaoDas17FoiFeita1 = false;
    private static String relatorio2 = "nome do cliente,quantidade de produtos no pedido,prazo de empacotamento,tempo para ser empacotado em minutos,tempo de chegada,minutos em que foi finalizado,dentro do prazo?\n";
    private static double tempo2 = 0.0;
    private static boolean marcacaoDas12FoiFeita2 = false;
    private static boolean marcacaoDas17FoiFeita2 = false;

    private static AtomicBoolean switchDeProcessar = new AtomicBoolean(true);

    /**
     * Metodo que vai realizar a leitura do arquivo
     * 
     * @param arquivo
     */
    public static void lerAquivo(String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] items = linha.split(";");
                ListaDePedidos.adicionarListPedidos(new Pedido(items[0], Integer.parseInt(items[1]),
                        Integer.parseInt(items[2]), Integer.parseInt(items[3])));
                linha = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado!" + e.getMessage());
        }
    }

    /**
     * Meto que vai salvar o arquivo em csv
     */
    public static void salvarRelatoriosEmCsv() {
        FileWriter arquivo1 = null;
        FileWriter arquivo2 = null;

        try {
            arquivo1 = new FileWriter("relatorio1.csv");
            arquivo1.write(relatorio1);
            arquivo2 = new FileWriter("relatorio2.csv");
            arquivo2.write(relatorio2);
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo!" + e.getMessage());
        } finally {
            try {
                arquivo1.close();
                arquivo2.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar arquivo!" + e.getMessage());
            }
        }
    }

    /**
     * Metodo para salvar o arquivo em .txt
     */
    public static void salvarRelatoriosEmTxt() {
        FileWriter arquivo1 = null;
        FileWriter arquivo2 = null;

        try {
            arquivo1 = new FileWriter("relatorio1.txt");
            arquivo1.write(relatorio1);
            arquivo2 = new FileWriter("relatorio2.txt");
            arquivo2.write(relatorio2);
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo!" + e.getMessage());
        } finally {
            try {
                arquivo1.close();
                arquivo2.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar arquivo!" + e.getMessage());
            }
        }
    }

    /**
     * Metodo para efetuar o registro das infomações obtidas a longo do processos
     * dos pedidods
     * 
     * @param pedido
     */
    public static void registrarProcessamento(Pedido pedido, int numeroEsteira) {

        if (numeroEsteira == 1) {
            tempo1 += pedido.getTempoParaSerEmpacotadoEmMinutos();

            String status = pedido.foiEmpacotadoDentroDoPrazo() ? "dentro do prazo" : "fora do prazo";
            relatorio1 += pedido.getNomeDoCliente() + "," + pedido.getQuantidadeDeProdutosNoPedido() + ","
                    + pedido.getPrazoDeEmpacotamento() + "," + pedido.getTempoParaSerEmpacotadoEmMinutos() + ","
                    + pedido.getTempoDeChegada() + ","
                    + tempo1
                    + "," + status
                    + "\n";
            if (tempo1 >= 240 & !marcacaoDas12FoiFeita1) {
                relatorio1 += "12:00,12:00,12:00,12:00,12:00,12:00\n";
                marcacaoDas12FoiFeita1 = true;
            }

            if (tempo1 >= 540 & !marcacaoDas17FoiFeita1) {
                relatorio1 += "17:00,17:00,17:00,17:00,17:00,17:00\n";
                marcacaoDas17FoiFeita1 = true;
            }
        } else {
            tempo2 += pedido.getTempoParaSerEmpacotadoEmMinutos();

            String status = pedido.foiEmpacotadoDentroDoPrazo() ? "dentro do prazo" : "fora do prazo";
            relatorio2 += pedido.getNomeDoCliente() + "," + pedido.getQuantidadeDeProdutosNoPedido() + ","
                    + pedido.getPrazoDeEmpacotamento() + "," + pedido.getTempoParaSerEmpacotadoEmMinutos() + ","
                    + pedido.getTempoDeChegada() + ","
                    + tempo2
                    + "," + status
                    + "\n";
            if (tempo2 >= 240 & !marcacaoDas12FoiFeita2) {
                relatorio2 += "12:00,12:00,12:00,12:00,12:00,12:00\n";
                marcacaoDas12FoiFeita2 = true;
            }

            if (tempo2 >= 540 & !marcacaoDas17FoiFeita2) {
                relatorio2 += "17:00,17:00,17:00,17:00,17:00,17:00\n";
                marcacaoDas17FoiFeita2 = true;
            }
        }
    }

    /**
     * Metodo para realizar o processamento e iniciar as threads e ao final salvar a
     * informações nos arquivos
     */
    public static void processar() {
        Esteira esteira = new Esteira();
        Pedido pedido = ListaDePedidos.buscarPedidos();
        if (pedido != null) {

            new Empacotar(pedido, esteira);
            new Embalar(pedido, esteira);

            if (switchDeProcessar.get()) {
                registrarProcessamento(pedido, 1);
                switchDeProcessar.set(false);
            } else {
                registrarProcessamento(pedido, 2);
                switchDeProcessar.set(true);
            }
            ListaDePedidos.removePedido(pedido);
        } else {
            salvarRelatoriosEmCsv();
            salvarRelatoriosEmTxt();
            System.out.println("\n" + "Fim do Processo \n" + "Relatorio salvo em relatorio.csv e relatorio.txt :D");
            Relatorio.analisar();
            System.exit(0);
        }

    }

}
