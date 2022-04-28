public class Esteira extends Thread {

    private int conteudo;
    private boolean disponivel;
    private int TRANSCICAO = 1;

    public synchronized void set(String nomeDoCliente, int quantidadePacote) {
        while (disponivel == true) {
            try {
                wait();
                join(TRANSCICAO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        conteudo = quantidadePacote;
        disponivel = true;
        notifyAll();
    }

    public synchronized int get(String nomeDoCliente) {
        while (disponivel == false) {
            try {
                wait();
                join(TRANSCICAO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        disponivel = false;
        notifyAll();
        return conteudo;
    }

}
