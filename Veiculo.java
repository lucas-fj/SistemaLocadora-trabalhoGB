public class Veiculo {
    // atributos
    private int codigo;
    private String modelo;
    private String cor;
    private int ano;
    private int odometro;
    private String cidade;
    private boolean disponivel;
    private double valor_diaria;
    private double valor_km_rodado;

    // getters e setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getOdometro() {
        return odometro;
    }

    public void setOdometro(int odometro) {
        this.odometro = odometro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public double getValor_diaria() {
        return valor_diaria;
    }

    public void setValor_diaria(double valor_diaria) {
        this.valor_diaria = valor_diaria;
    }

    public double getValor_km_rodado() {
        return valor_km_rodado;
    }

    public void setValor_km_rodado(double valor_km_rodado) {
        this.valor_km_rodado = valor_km_rodado;
    }

    // método para desserializar veículo
    public void desserializar(String linha) {

        String[] dados = linha.split("\t");

        codigo = Integer.parseInt(dados[0]);
        modelo = dados[1];
        cor = dados[2];
        ano = Integer.parseInt(dados[3]);
        odometro = Integer.parseInt(dados[4]);
        cidade = dados[5];
        disponivel = Boolean.parseBoolean(dados[6]);
        valor_diaria = Double.parseDouble(dados[7]);
        valor_km_rodado = Double.parseDouble(dados[8]);
    }

    // método para serializar veículo
    public String serializar() {

        return codigo + "\t" +
                modelo + "\t" +
                cor + "\t" +
                ano + "\t" +
                odometro + "\t" +
                cidade + "\t" +
                disponivel + "\t" +
                valor_diaria + "\t" +
                valor_km_rodado;
    }

    // método toString
    @Override
    public String toString() {
        return "Código: " + codigo +
                " | Modelo: " + modelo +
                " | Cidade: " + cidade +
                " | Disponível: " + disponivel;
    }
}