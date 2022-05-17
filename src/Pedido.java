public class Pedido implements Comparable<Pedido> {

    private static final int TAMANHO_MAX_DO_PACOTE = 5000;
    private static final int TAMANHO_DO_PRODUTO = 250;
    private static final double TEMPO_PARA_EMPACOTAR = 5.0;
    private static final double TEMPO_PARA_RETIRAR_PACOTE_DA_ESTEIRA = 10.0;
    private String nomeDoCliente;
    private int quantidadeDeProdutosNoPedido;
    private int prazoDeEmpacotamento;
    private int chegada;


    public Pedido(String nomeDoCliente, int quantidadeDeProdutosNoPedido, int prazoDeEmpacotamento, int chegada) {
        this.nomeDoCliente = nomeDoCliente;
        this.quantidadeDeProdutosNoPedido = quantidadeDeProdutosNoPedido;
        this.prazoDeEmpacotamento = prazoDeEmpacotamento;
        this.chegada = chegada;
    }


    public String getNomeDoCliente() {
        return nomeDoCliente;
    }

    public int getQuantidadeDeProdutosNoPedido() {
        return quantidadeDeProdutosNoPedido;
    }

    /**
     * Metodo que vai realizar o calculo de quantos pedido cabe dentro de um pacote de tamanho fixo
     * @return total de pacotes que um clinte vai ter.
     */
    public int getQuantidadeDePacotesNoPedido() {
        return (int) Math.ceil(((double) quantidadeDeProdutosNoPedido * TAMANHO_DO_PRODUTO) / TAMANHO_MAX_DO_PACOTE);
    }

    /**
     * Metodo que vai relizar o calculo do tempo com base na quantidade de pacontes que um cliente tem.
     * @return vai retunar a soma total de tempo que levar para quantidade especifica de paconte serem empacotada
     */
    public double getTempoParaSerEmpacotadoEmSegundos() {
        return getQuantidadeDePacotesNoPedido() * (TEMPO_PARA_EMPACOTAR + TEMPO_PARA_RETIRAR_PACOTE_DA_ESTEIRA);
    }

    /**
     * metodo para transformar segundo em minutos
     * @return vai returna os minutos
     */
    public double getTempoParaSerEmpacotadoEmMinutos() {
        return getTempoParaSerEmpacotadoEmSegundos() / 60;
    }

    /**
     * Metodo para transformar minutos em horas
     * @return vai returna as horas.
     */
    public double getTempoParaSerEmpacotadoEmHoras() {
        return getTempoParaSerEmpacotadoEmMinutos() / 60;
    }

    /**
     * Metodo para saber se um pedido esta dentro do prazo ou não com base nas horas.
     * @return vai returna true se caso estiver dentro do prazo e falso para não
     */
    public boolean foiEmpacotadoDentroDoPrazo() {
        if (prazoDeEmpacotamento == 0) {
            return true;
        }
        return getTempoParaSerEmpacotadoEmHoras() <= prazoDeEmpacotamento;
    }

    public int getPrazoDeEmpacotamento() {
        return prazoDeEmpacotamento;
    }


    /**
     * Metodo para comparar um pedido/ chegada ja existente na lista com outro que sera add .
     * @param outro
     * @return true caso tiver falso caso nao tiver.
     */

    public boolean equals(Pedido outro) {
        return this.chegada == outro.chegada;
    }

    /**
     * Metodo para comparar um pedido/ prazoDeEmpacotamento ja existente na lista com outro que sera add .
     * @param outro
     * @return true caso tiver falso caso nao tiver.
     */
    public boolean igual(Pedido outro) {
        return this.prazoDeEmpacotamento == outro.prazoDeEmpacotamento;

    }


    /**
     * metodo para compara se um pedido tem o maior tempo que
     * outro se caso o tempo forem iguais vai olhar quatidade de pedido
     */

    public int compareTo(Pedido outro) {
        if (this.equals(outro)) {
            if (this.igual(outro)) {
                if (this.quantidadeDeProdutosNoPedido < outro.quantidadeDeProdutosNoPedido) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (prazoDeEmpacotamento > outro.prazoDeEmpacotamento) {
                return -1;
            } else {
                return 1;
            }
        } else if (chegada > outro.chegada) {
            return 1;
        } else {
            return -1;
        }


    }


    @Override
    public String toString() {
        return this.nomeDoCliente + ", "
                + this.quantidadeDeProdutosNoPedido + ", "
                + this.prazoDeEmpacotamento + ", "
                + this.chegada;
    }

}