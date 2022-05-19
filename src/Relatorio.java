import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Relatorio {

    public static ArrayList<String> lerAquivo(String arquivo) {
        ArrayList<String> pedidosNoRelatorio = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] items = linha.split(",");
                if (!items[0].equals("nome do cliente")) {
                    pedidosNoRelatorio.add(items[0]);
                }
                linha = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado!" + e.getMessage());
        }

        return pedidosNoRelatorio;
    }

    public static void analisar() {
        ArrayList<String> esteira1 = lerAquivo("relatorio1.txt");

        ArrayList<String> esteira2 = lerAquivo("relatorio2.txt");

        int esteira1Limite12h = esteira1.indexOf("12:00");

        int esteira2Limite12h = esteira2.indexOf("12:00");

        int esteira1Limite17h = esteira1.indexOf("17:00");

        int esteira2Limite17h = esteira2.indexOf("17:00");

        int esteira1ProduzidosAte12h = esteira1.subList(0, esteira1Limite12h).size();
        int esteira2ProduzidosAte12h = esteira2.subList(0, esteira2Limite12h).size();

        int esteira1ProduzidosAte17h = esteira1.subList(esteira1Limite12h, esteira1Limite17h).size();
        int esteira2ProduzidosAte17h = esteira2.subList(esteira2Limite12h, esteira2Limite17h).size();

        int esteira1NaoProduzidos = esteira1.subList(esteira1Limite17h, esteira1.size()).size();
        int esteira2NaoProduzidos = esteira2.subList(esteira2Limite17h, esteira2.size()).size();

        System.out.println(
                "Esteira 1 produzidos até 12h: " + esteira1ProduzidosAte12h + "\n" +
                        "Esteira 2 produzidos até 12h: " + esteira2ProduzidosAte12h + "\n" +
                        "Esteira 1 produzidos até 17h: " + (esteira1ProduzidosAte12h + esteira1ProduzidosAte17h - 1)
                        + "\n"
                        +
                        "Esteira 2 produzidos até 17h: " + (esteira2ProduzidosAte12h + esteira2ProduzidosAte17h - 1)
                        + "\n"
                        +
                        "Esteira 1 não produzidos: " + esteira1NaoProduzidos + "\n" +
                        "Esteira 2 não produzidos: " + esteira2NaoProduzidos + "\n" +
                        "Total produzidos: " + (esteira1ProduzidosAte12h + esteira2ProduzidosAte12h
                                + esteira1ProduzidosAte17h + esteira2ProduzidosAte17h - 2)
                        + "\n");
    }

}
