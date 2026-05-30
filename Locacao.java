import java.util.ArrayList;

public class Locacao {
    // atributos
    private Veiculo veiculo;
    private String cliente;
    private String origem;
    private String destino;
    private int km_rodado;
    private int qt_dias_reserva;
    private int qt_dias_realizado;

    // construtor vazio
    public Locacao() {
    }

    // getters e setters
    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getKm_rodado() {
        return km_rodado;
    }

    public void setKm_rodado(int km_rodado) {
        this.km_rodado = km_rodado;
    }

    public int getQt_dias_reserva() {
        return qt_dias_reserva;
    }

    public void setQt_dias_reserva(int qt_dias_reserva) {
        this.qt_dias_reserva = qt_dias_reserva;
    }

    public int getQt_dias_realizado() {
        return qt_dias_realizado;
    }

    public void setQt_dias_realizado(int qt_dias_realizado) {
        this.qt_dias_realizado = qt_dias_realizado;
    }

    // método para serializar locação
    public String serializar() {

        return veiculo.getCodigo() + "\t" +
                cliente + "\t" +
                origem + "\t" +
                destino + "\t" +
                km_rodado + "\t" +
                qt_dias_reserva + "\t" +
                qt_dias_realizado;
    }

    // método para desserializar locação
    public void desserializar(String linha, ArrayList<Veiculo> veiculos) {

        String[] dados = linha.split("\t");

        int codigoVeiculo = Integer.parseInt(dados[0]);

        // procura o veículo pelo código
        for (Veiculo v : veiculos) {

            if (v.getCodigo() == codigoVeiculo) {

                veiculo = v;
                break;
            }
        }

        cliente = dados[1];
        origem = dados[2];
        destino = dados[3];
        km_rodado = Integer.parseInt(dados[4]);
        qt_dias_reserva = Integer.parseInt(dados[5]);
        qt_dias_realizado = Integer.parseInt(dados[6]);
    }
}
