public class Empacotar extends Thread {

    private final int TEMPO_PARA_EMPACOTAR = 1;
    private Pedido pedido;
    private Esteira esteira;

    public Empacotar(Pedido pedido, Esteira esteira) {
        this.pedido = pedido;
        this.esteira = esteira;
        start();
    }

    
    /**    
     * Metodo que vai executar Thread com base looping para execuatar pela quantidade de pedido ? incremento 
     * a esteira get() fazendo o saida do processo iniciando o proximo processo dentro da esteira
     */
    public void run() {
        try {
            for (int i = 0; i < pedido.getQuantidadeDePacotesNoPedido(); i++) {
                sleep(TEMPO_PARA_EMPACOTAR);
                esteira.get(pedido.getNomeDoCliente());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println( pedido.getQuantidadeDePacotesNoPedido() + " pacotes do cliente " + pedido.getNomeDoCliente() + " em transporte");
        Controller.processar();

    }

}
