public class Esteira extends Thread{

    private  int conteudo;
    private  boolean disponivel;
    private int TRANSCICAO = 500;


    public  synchronized void set(String nomCleint, int quantPacote) {
        while (disponivel == true) {
            try {
                System.out.println("Pedidos do cliente: " + nomCleint + ", sendo embalados...");
                wait();
                join(TRANSCICAO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        conteudo = quantPacote;
        System.out.println("Embalando produto: " +   (conteudo + 1)  + " do " + nomCleint);
        disponivel = true;
        notifyAll();
    }

    public synchronized int get(String nomCleint) {
        while (disponivel == false) {
            try {
                System.out.println("Produto:" + (conteudo + 1) + " do clinte " + nomCleint + " esperado...");
                wait();
                join(TRANSCICAO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Produto: " + (conteudo + 1)  + " empacotado" );
        disponivel = false;
        notifyAll();
        return conteudo;
    }

}
