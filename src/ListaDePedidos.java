import java.util.*;

public class ListaDePedidos {

    private static List<Pedido> listPedidos = new LinkedList<>();

    /**
     * Metodo para add um pedido em uma lista
     * 
     * @param pedido
     */
    public static void adicionarListPedidos(Pedido pedido) {
        listPedidos.add(pedido);
        ordenarLista();
    }

    /**
     * Metodo para buscar sempre o primeiro pedido da lista caso a lista estiver
     * fazia vai returna null
     * 
     * @return
     */
    public static Pedido buscarPedidos() {
        if (listPedidos.isEmpty()) {
            return null;
        } else {
            Pedido pedido = listPedidos.get(0);
            return pedido;
        }
    }

    /**
     * Metodo para remove o pedido especifico
     * 
     * @param pedido
     */
    public static void removePedido(Pedido pedido) {
        listPedidos.remove(pedido);
    }

    /**
     * Metodo para ordenar a lista com base no metodo compareTo() da classe pedido
     */
    public static void ordenarLista() {
        Collections.sort(listPedidos);
    }

    /**
     * Metodo vai pecorrer toda a lista de pedido e retunar todos os elementos que
     * tem.
     * 
     * @return
     */
    public static String imprimirListaDePedidos() {
        StringBuilder sb = new StringBuilder();
        for (Pedido pedido : listPedidos) {
            sb.append(pedido + "\n");
        }
        return sb.toString();
    }

}
