public class Empacotar extends Thread {


    private static final int TAM_PACOTE = 5000;
    private static final int TAM_PRODUTO = 250;
    private final int TEMPO = 5000;
    private static int quatPacote;
    private int tamTotal;
    private static Pedido pedido;
    private Esteira esteira;

    public Empacotar(Pedido pedido, Esteira esteira) {
        this.pedido = pedido;
        this.esteira = esteira;
        start();
    }

    public static int getQuatPacote() {
        return quatPacote;
    }

    public static int calQuantPacote(Pedido pedido) {
        return quatPacote = (int) Math.ceil(((double) pedido.getQuatPedido() * TAM_PRODUTO) / TAM_PACOTE);
    }


    public void run() {
        try {
            for (int i = 0; i < quatPacote; i++) {
                sleep(TEMPO);
                esteira.get(pedido.getNomCleint());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(quatPacote + " pacotes do cliente " + pedido.getNomCleint() + " em transporte");
        FilaPedido.processar();

    }


}




