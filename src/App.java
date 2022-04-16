public class App {
    public static void main(String[] args) {

        Esteira esteira = new Esteira();

        String arquivo = "baseDados.txt";
        FilaPedido.lerAquivo(arquivo);
        Pedido pedido = FilaPedido.primeiro();

        System.out.println(pedido);

       new Embalar(pedido, esteira);
       new Empacotar(pedido,esteira);
    }
}
