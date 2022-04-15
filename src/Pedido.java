public class Pedido implements Comparable<Pedido> {
    private String nomCleint;
    private int nunPedido;
    private int tempo;


    public Pedido(String nomCleint, int nunPedido, int tempo) {
        this.nomCleint = nomCleint;
        this.nunPedido = nunPedido;
        this.tempo = tempo;
    }

    public boolean equals(Pedido outro) {
        return this.tempo == outro.tempo;

    }

    public int compareTo(Pedido outro) {
        if (this.equals(outro)) {
            if (this.nunPedido > outro.nunPedido) {
                return 0;
            } else {
                return 1;
            }
        } else if (tempo < outro.tempo) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return this.nomCleint + "," + this.nunPedido + "," + this.tempo;
    }


}