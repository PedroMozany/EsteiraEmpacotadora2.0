public class Empacotar extends Thread {


    private final int TAM_PACOTE = 5000;
    private final int TAM_PRODUTO = 250;
    private int quatPacote;
    private int tamTotal;
    private Pedido pedido;
    private Esteira esteira;

    public Empacotar(Pedido pedido, Esteira esteira) {
        this.pedido = pedido;
        this.esteira = esteira;
        start();
    }

    private int calQuantPacote() {
        return quatPacote = Math.round((pedido.getQuatPedido() * TAM_PRODUTO) / TAM_PACOTE);
    }


    public void run() {
        for (int i = 0; i < pedido.getQuatPedido(); i++) {
            esteira.get(pedido.getNomCleint());
        }
        System.out.println(calQuantPacote() + " pacotes do cliente " + pedido.getNomCleint() + " em transporte");
        FilaPedido.processar();
    }
}



