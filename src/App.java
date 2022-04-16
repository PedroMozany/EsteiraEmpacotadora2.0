public class App {
    public static void main(String[] args) {

        Esteira esteira = new Esteira();

        String arquivo = "baseDados.txt";
        FilaPedido.lerAquivo(arquivo);

        FilaPedido.processar();

    }

}
