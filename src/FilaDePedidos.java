import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;

public class FilaDePedidos {

    private static Queue<Pedido> filaDePedidos = new PriorityQueue<>();
    private static String relatorio = "nome do cliente,quantidade de produtos no pedido,prazo de empacotamento,tempo para ser empacotado em minutos,dentro do prazo?\n";

    public static void lerAquivo(String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] items = linha.split(";");
                adicionarPedidoNaFila(new Pedido(items[0], Integer.parseInt(items[1]), Integer.parseInt(items[2])));
                linha = br.readLine();

            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado!" + e.getMessage());
        }
    }

    public static void adicionarPedidoNaFila(Pedido pedido) {
        filaDePedidos.add(pedido);
    }

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

    public static Pedido retirarPrimeiroPedidoDaFila() {
        return filaDePedidos.poll();

    }

    public static String imprimirFilaDePedidos() {
        StringBuilder sb = new StringBuilder();
        for (Pedido pedido : filaDePedidos) {
            sb.append(pedido + "\n");
        }
        return sb.toString();
    }

    public static void registrarProcessamento(Pedido pedido) {
        String status = pedido.foiEmpacotadoDentroDoPrazo() ? "dentro do prazo" : "fora do prazo";
        relatorio += pedido.getNomeDoCliente() + "," + pedido.getQuantidadeDeProdutosNoPedido() + ","
                + pedido.getPrazoDeEmpacotamento() + ","
                + pedido.getTempoParaSerEmpacotadoEmMinutos() + ","
                + status + "\n";
    }

    public static void processar() {
        Esteira esteira = new Esteira();
        Pedido pedido = retirarPrimeiroPedidoDaFila();
        if (pedido != null) {
            new Empacotar(pedido, esteira);
            new Embalar(pedido, esteira);
            // System.out.println("================================================================================");
            // System.out
            // .println(pedido.getNomeDoCliente() + ": " +
            // pedido.getQuantidadeDeProdutosNoPedido()
            // + " produtos no pedido em " + pedido.getQuantidadeDePacotesNoPedido() + "
            // pacotes");
            // System.out.println(
            // "Tempo para empacotamento do pedido do cliente: " +
            // pedido.getPrazoDeEmpacotamento() + " minutos");
            // System.out.println("Tempo de empacotamento: " +
            // pedido.getTempoParaSerEmpacotadoEmMinutos() + " minutos");
            // if (pedido.foiEmpacotadoDentroDoPrazo()) {
            // System.out.println("Pedido foi empacotado dentro do prazo!");
            // } else {
            // System.out.println("Pedido não foi empacotado dentro do prazo!");
            // }
            // System.out.println("================================================================================\n");
            registrarProcessamento(pedido);
        } else {
            salvarRelatorioEmCsv();
            salvarRelatorioEmTxt();
            System.out.println("Relatório salvo em relatorio.csv e relatorio.txt :D");
            System.out.println("Fila de pedidos vazia!");
            System.exit(0);
        }
    }

}
