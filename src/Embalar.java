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

        for (int i = 0; i < Empacotar.getQuatPacote(); i++) {
            esteira.set(pedido.getNomCleint(), i);
        }
//
        System.out.println("Empacotamento dos produtos do cliente: " + pedido.getNomCleint() + " concluido!");

    }
}





