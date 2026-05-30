import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Locadora {
    //atributos
    ArrayList<Veiculo> veiculos = new ArrayList<>();
    ArrayList<Locacao> locacoes = new ArrayList<>();

    // método para carregar os dados dos arquivos "veiculos.txt" e "locacoes.txt"
    public void carregaDados(){
        // lendo os arquivos txt e salvando em objetos no arraylist
        try {
            FileReader readerVeiculos = new FileReader("veiculos.txt");
            BufferedReader bufferVeiculos = new BufferedReader(readerVeiculos);
            FileReader readerLocacoes = new FileReader("locacoes.txt");
            BufferedReader bufferLocacoes = new BufferedReader(readerLocacoes);
            
            String linhaV;
            String linhaL;
            bufferVeiculos.readLine();// ignora cabeçalho

            // while para ler veiculos.txt
            while ((linhaV = bufferVeiculos.readLine()) != null) {
                Veiculo v = new Veiculo();
                v.desserializar(linhaV);
                veiculos.add(v);
            }

            bufferLocacoes.readLine();// ignora cabeçalho
            //while para ler locacoes.txt
            while ((linhaL = bufferLocacoes.readLine()) != null) {
                Locacao l = new Locacao();
                l.desserializar(linhaL, veiculos);
                locacoes.add(l);
            }
            bufferVeiculos.close();
            bufferLocacoes.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}