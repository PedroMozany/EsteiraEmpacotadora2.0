public class Pedido implements Comparable<Pedido> {

    private static final int TAMANHO_MAX_DO_PACOTE = 5000;
    private static final int TAMANHO_DO_PRODUTO = 250;
    private static final double TEMPO_PARA_EMPACOTAR = 5.0;
    private static final double TEMPO_PARA_RETIRAR_PACOTE_DA_ESTEIRA = 10.0;
    private String nomeDoCliente;
    private int quantidadeDeProdutosNoPedido;
    private int prazoDeEmpacotamento;

    public Pedido(String nomeDoCliente, int quantidadeDeProdutosNoPedido, int prazoDeEmpacotamento) {
        this.nomeDoCliente = nomeDoCliente;
        this.quantidadeDeProdutosNoPedido = quantidadeDeProdutosNoPedido;
        this.prazoDeEmpacotamento = prazoDeEmpacotamento;
    }

    public String getNomeDoCliente() {
        return nomeDoCliente;
    }

    public int getQuantidadeDeProdutosNoPedido() {
        return quantidadeDeProdutosNoPedido;
    }

    public int getQuantidadeDePacotesNoPedido() {
        return (int) Math
                .ceil(((double) quantidadeDeProdutosNoPedido * TAMANHO_DO_PRODUTO) / TAMANHO_MAX_DO_PACOTE);
    }

    public double getTempoParaSerEmpacotadoEmSegundos() {
        return getQuantidadeDePacotesNoPedido() * (TEMPO_PARA_EMPACOTAR + TEMPO_PARA_RETIRAR_PACOTE_DA_ESTEIRA);
    }

    public double getTempoParaSerEmpacotadoEmMinutos() {
        return getTempoParaSerEmpacotadoEmSegundos() / 60;
    }

    public double getTempoParaSerEmpacotadoEmHoras() {
        return getTempoParaSerEmpacotadoEmMinutos() / 60;
    }

    public boolean foiEmpacotadoDentroDoPrazo() {
        if (prazoDeEmpacotamento == 0) {
            return true;
        }
        return getTempoParaSerEmpacotadoEmHoras() <= prazoDeEmpacotamento;
    }

    public int getPrazoDeEmpacotamento() {
        return prazoDeEmpacotamento;
    }

    public boolean igual(Pedido outro) {
        return this.prazoDeEmpacotamento == outro.prazoDeEmpacotamento;

    }

    public int compareTo(Pedido outro) {
        if (this.igual(outro)) {
            if (this.quantidadeDeProdutosNoPedido > outro.quantidadeDeProdutosNoPedido) {
                return 0;
            } else {
                return 1;
            }
        } else if (prazoDeEmpacotamento < outro.prazoDeEmpacotamento) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return this.nomeDoCliente + "," + this.quantidadeDeProdutosNoPedido + "," + this.prazoDeEmpacotamento;
    }

}