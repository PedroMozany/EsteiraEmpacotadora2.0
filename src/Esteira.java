public class Esteira extends Thread {

    private int conteudo;
    private boolean disponivel;
    private int TRANSCICAO = 1;
    
    
    

/* Uso de synchronized em um m?todo garante que a execu??o deste, seja realizada apenas por uma Thread por vez, 
 * utilizando um mecanismo de lock. A Thread que come?a a executar o m?todo ?pega/metodo get()? o lock, 
 * liberando-o ao t?rmino da execu??o do m?todo. Threads que n?o possuem o lock devem aguardar a libera??o para poder 
 * invocar o m?todo. Mecanismo que vai pocibilidade de ter mais de uma esteira ao mesmo tempo. 
 */
    
  
    
    /**
     * Metodo que vai realizar a entrada do pedido na esteira
     * @param nomeDoCliente
     * @param quantidadePacote
     */
   
    public synchronized void set(String nomeDoCliente, int quantidadePacote) {
        while (disponivel == true) {
            try {
            	System.out.println("Pedidos do cliente: " + nomeDoCliente + ", sendo embalados...");
                wait();
                join(TRANSCICAO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        conteudo = quantidadePacote;
        System.out.println("Pacote: " + (conteudo + 1) + " do " + nomeDoCliente);
        disponivel = true;
        notifyAll();
    }

    
    
    /**
     * Metodo que vai realizar a retirada do processo da esteira
     * @param nomeDoCliente
     * @return
     */
    public synchronized int get(String nomeDoCliente) {
        while (disponivel == false) {
            try {
            	System.out.println("Pacote:" + (conteudo + 1) + " do clinte " + nomeDoCliente + " esperado...");
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Pacote: " + (conteudo + 1) + " empacotado" );
        disponivel = false;
        notifyAll();
        return conteudo;
    }

}
