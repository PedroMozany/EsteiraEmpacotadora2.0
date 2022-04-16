import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;


public class FilaPedido {


    private static Queue<Pedido> filaPedido = new PriorityQueue<>();
    private static Pedido pedido;


    public static void lerAquivo(String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] items = linha.split(";");
                addPedido(new Pedido(items[0], Integer.parseInt(items[1]), Integer.parseInt(items[2])));
                linha = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado!" + e.getMessage());
        }
    }


    public static void addPedido(Pedido pedido) {
        filaPedido.add(pedido);
    }

    public static Pedido primeiro() {
        return filaPedido.poll();

    }


    public static String percorrerFila() {
        StringBuilder sb = new StringBuilder();
        for (Pedido pedido : filaPedido) {
            sb.append(pedido + "\n");
        }
        return sb.toString();
    }

    public static void processar() {
        Esteira esteira = new Esteira();
        Pedido pedido = primeiro();
        System.out.println(pedido);
        new Embalar(pedido, esteira);
        new Empacotar(pedido, esteira);
    }

}


