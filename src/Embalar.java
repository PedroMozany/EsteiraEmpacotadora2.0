public class Embalar extends Thread {

    private Pedido pedido;
    private Esteira esteira;

    public Embalar(Pedido pedido, Esteira esteira) {
        this.pedido = pedido;
        this.esteira = esteira;
        start();
    }

    @Override
    public void run() {
        for (int i = 0; i < pedido.getQuantidadeDePacotesNoPedido(); i++) {
            esteira.set(pedido.getNomeDoCliente(), i);
        }
    }
}
