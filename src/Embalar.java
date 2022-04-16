public class Embalar extends Thread {


    private static final int TEMPO = 5;
    private Pedido pedido;
    private Esteira esteira;

    public Embalar(Pedido pedido, Esteira esteira) {
        this.pedido = pedido;
        this.esteira = esteira;
        start();
    }

    public void run() {
        try {
            for (int i = 0; i < pedido.getQuatPedido(); i++) {
                esteira.set(pedido.getNomCleint(), i);
                sleep(TEMPO);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Empacotamento dos produtos do cliente: " + pedido.getNomCleint() + " concluido!");
    }

}




