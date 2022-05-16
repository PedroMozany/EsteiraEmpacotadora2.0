import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;

public class FilaDePedidos {

	private static Queue<Pedido> filaDePedidos = new PriorityQueue<>();
	private static String relatorio = "nome do cliente,quantidade de produtos no pedido,prazo de empacotamento,tempo para ser empacotado em minutos,dentro do prazo?\n";

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
				adicionarPedidoNaFila(new Pedido(items[0], Integer.parseInt(items[1]), Integer.parseInt(items[2])));
				linha = br.readLine();

			}
		} catch (IOException e) {
			System.out.println("Arquivo n√£o encontrado!" + e.getMessage());
		}
	}

	/**
	 * Metodo para adicionar uma pedido na fila.Esse metodo vai parte do principe da
	 * implementaÁ„o que a classe pedido faz com "Comparable" realizando o processo
	 * de escalonamento automatico.
	 * 
	 * @param pedido
	 */
	public static void adicionarPedidoNaFila(Pedido pedido) {
		filaDePedidos.add(pedido);
	}

	/**
	 * Metodo para salvar o realtorio em um arquivo .csv
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
	 * Metodo para retirar sempre o primeiro pedido da fila
	 * 
	 * @return vai returno o primeiro pedido
	 */
	public static Pedido retirarPrimeiroPedidoDaFila() {
		return filaDePedidos.poll();

	}

	/**
	 * Metodo para pecorrer uma fila e imprimir todos os seus item adicionados
	 * 
	 * @return vai retorno pedido sobescrito pelo toString
	 */
	public static String imprimirFilaDePedidos() {
		StringBuilder sb = new StringBuilder();
		for (Pedido pedido : filaDePedidos) {
			sb.append(pedido + "\n");
		}
		return sb.toString();
	}

	/**
	 * Metodo que vai realizar o registro referente ao pedido do cleinte e salvar no
	 * relatorio
	 * 
	 * @param pedido
	 */
	public static void registrarProcessamento(Pedido pedido) {
		String status = pedido.foiEmpacotadoDentroDoPrazo() ? "dentro do prazo" : "fora do prazo";
		relatorio += pedido.getNomeDoCliente() + "," + pedido.getQuantidadeDeProdutosNoPedido() + ","
				+ pedido.getPrazoDeEmpacotamento() + "," + pedido.getTempoParaSerEmpacotadoEmMinutos() + "," + status
				+ "\n";
	}

	/**
	 * Metodo para executar o programa quando È chamando
	 * 
	 */
	public static void processar() {
		Esteira esteira = new Esteira();
		Pedido pedido = retirarPrimeiroPedidoDaFila();
		if (pedido != null) {
			new Empacotar(pedido, esteira);
			new Embalar(pedido, esteira);
		} else {
			salvarRelatorioEmCsv();
			salvarRelatorioEmTxt();
			System.out.println("\n" + "Fim do Processo \n" + "Relat√≥rio salvo em relatorio.csv e relatorio.txt :D");
			System.out.println("Fila de pedidos vazia!");
			System.exit(0);
		}
	}

}
