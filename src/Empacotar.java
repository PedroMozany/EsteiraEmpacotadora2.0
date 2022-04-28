public class Empacotar extends Thread {

    private final int TEMPO_PARA_EMPACOTAR = 1;
    private Pedido pedido;
    private Esteira esteira;

    public Empacotar(Pedido pedido, Esteira esteira) {
        this.pedido = pedido;
        this.esteira = esteira;
        start();
    }

    public void run() {
        try {
            for (int i = 0; i < pedido.getQuantidadeDePacotesNoPedido(); i++) {
                sleep(TEMPO_PARA_EMPACOTAR);
                esteira.get(pedido.getNomeDoCliente());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FilaDePedidos.processar();

    }

}
