public class Embalar extends Thread {

    private Pedido pedido;
    private Esteira esteira;

    
   
    public Embalar(Pedido pedido, Esteira esteira) {
        this.pedido = pedido;
        this.esteira = esteira;
        start();
    }
    

    /**
     * Metodo que vai realizar o processo de embalar do pedido e add um pedido na esteira 
     * sendo que o braço esta embalando 20 produtos de uma vez e add dentro de um pacote
     */
    @Override
    public void run() {
        for (int i = 0; i < pedido.getQuantidadeDePacotesNoPedido(); i++) {
            esteira.set(pedido.getNomeDoCliente(), i);
        }
        System.out.println("Empacotamento dos produtos do cliente: " + pedido.getNomeDoCliente() + " concluido!");
    }
}
