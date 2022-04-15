import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String arquivo = "baseDados.txt";


        FilaPedido.lerAquivo(arquivo);
        System.out.println(FilaPedido.imprimir());





        sc.close();
    }
}
