import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Controller {

    private static String relatorio = "nome do cliente,quantidade de produtos no pedido,prazo de empacotamento,tempo para ser empacotado em minutos,dentro do prazo?\n";


    /**
     * Metodo que vai realizar a leitura do arquivo
     * @param arquivo
     */
    public static void lerAquivo(String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] items = linha.split(";");
                ListaDePedidos.adicionarListPedidos(new Pedido(items[0], Integer.parseInt(items[1]), Integer.parseInt(items[2]), Integer.parseInt(items[3])));
                linha = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado!" + e.getMessage());
        }
    }


    /**
     * Meto que vai salvar o arquivo em csv
     */
    public static void salvarRelatorioEmCsv() {
        FileWriter arquivo = null;
        try {
            arquivo = new FileWriter("relatorio.csv");
            arquivo.write(relatorio);
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo!" + e.getMessage());
        } finally {
            try {
                arquivo.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar arquivo!" + e.getMessage());
            }
        }
    }

    /**
     * Metodo para salvar o arquivo em .txt
     */
    public static void salvarRelatorioEmTxt() {
        FileWriter arquivo = null;
        try {
            arquivo = new FileWriter("relatorio.txt");
            arquivo.write(relatorio);
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo!" + e.getMessage());
        } finally {
            try {
                arquivo.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar arquivo!" + e.getMessage());
            }
        }
    }

    /**
     * Metodo para efetuar o registro das infomações obtidas a longo do processos dos pedidods
     * @param pedido
     */
    public static void registrarProcessamento(Pedido pedido) {
        String status = pedido.foiEmpacotadoDentroDoPrazo() ? "dentro do prazo" : "fora do prazo";
        relatorio += pedido.getNomeDoCliente() + "," + pedido.getQuantidadeDeProdutosNoPedido() + ","
                + pedido.getPrazoDeEmpacotamento() + "," + pedido.getTempoParaSerEmpacotadoEmMinutos() + "," + status
                + "\n";
    }

    /**
     * Metodo para realizar o processamento e iniciar as threads e ao final salvar a informações nos arquivos
     */
    public static void processar() {
        Esteira esteira = new Esteira();
        Pedido pedido = ListaDePedidos.buscarPedidos();
        if (pedido != null) {
            new Empacotar(pedido, esteira);
            new Embalar(pedido, esteira);
            registrarProcessamento(pedido);
            ListaDePedidos.removePedido(pedido);
        } else {
            salvarRelatorioEmCsv();
            salvarRelatorioEmTxt();
            System.out.println("\n" + "Fim do Processo \n" + "Relatorio salvo em relatorio.csv e relatorio.txt :D");
            System.out.println("Fila de pedidos vazia!");
            System.exit(0);
        }

    }





}
