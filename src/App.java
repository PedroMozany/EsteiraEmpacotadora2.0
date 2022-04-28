public class App {
    public static void main(String[] args) {
        String arquivo = "baseDados.txt";
        System.out.println("Processando...");
        FilaDePedidos.lerAquivo(arquivo);
        FilaDePedidos.processar();
    }

}
