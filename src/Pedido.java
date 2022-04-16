public class Pedido implements Comparable<Pedido> {
    private String nomCleint;
    private int quatPedido;
    private int tempo;


    public Pedido(String nomCleint, int quatPedido, int tempo) {
        this.nomCleint = nomCleint;
        this.quatPedido = quatPedido;
        this.tempo = tempo;
    }


    public String getNomCleint() {
        return nomCleint;
    }

    public int getQuatPedido() {
        return quatPedido;
    }

    public int getTempo() {
        return tempo;
    }

    public boolean equals(Pedido outro) {
        return this.tempo == outro.tempo;

    }

    public int compareTo(Pedido outro) {
        if (this.equals(outro)) {
            if (this.quatPedido > outro.quatPedido) {
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
        return this.nomCleint + "," + this.quatPedido + "," + this.tempo;
    }


}