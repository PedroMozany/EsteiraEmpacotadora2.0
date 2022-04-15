import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;


public class FilaPedido {


   private static Queue<Pedido> filaPedido =  new PriorityQueue<>();


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


    public static String imprimir() {
        StringBuilder sb = new StringBuilder();
        while(!filaPedido.isEmpty()){
            sb.append(filaPedido.poll() + "\n");
        }
        return sb.toString();
    }


}
