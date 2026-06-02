import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Locadora {
    //atributos
    ArrayList<Veiculo> veiculos = new ArrayList<>();
    ArrayList<Locacao> locacoes = new ArrayList<>();
    // variável para controle
    boolean encontrado = false;

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

    // método para salvar dados nos arquivos
    public void salvaDados(){
        try {
            FileWriter writerVeiculos = new FileWriter("veiculos.txt");
            BufferedWriter bufferVeiculos = new BufferedWriter(writerVeiculos);
            FileWriter writerLocacoes = new FileWriter("locacoes.txt");
            BufferedWriter bufferLocacoes = new BufferedWriter(writerLocacoes);
            
            // salva veiculos no txt
            for (Veiculo v : veiculos) {
                bufferVeiculos.write(v.serializar());
                bufferVeiculos.newLine();
            }

            // salva locacoes no txt
            for (Locacao l : locacoes) {
                bufferLocacoes.write(l.serializar());
                bufferLocacoes.newLine();
            }

            bufferLocacoes.close();
            bufferVeiculos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // método para consultar veiculos 
    public void consultaVeiculo(){
        System.out.println("Opções de pesquisa:");
        System.out.println("1- Modelo\n2- Cor\n3- Ano\n4- Cidade");

        int escolha = Teclado.leInt("Digite sua escolha: ");

        switch (escolha) {
            // pesquisa por modelo
            case 1:
                encontrado = false;
                String modelo = Teclado.leString("Modelo: ");

                for (Veiculo v : veiculos) {
                    if (v.getModelo().equalsIgnoreCase(modelo)) {
                        System.out.println(v);
                        encontrado = true;
                    }
                }

                if (encontrado == false) {
                    System.out.println("Modelo não encontrado.");
                }
                break;

            // pesquisa por cor
            case 2:
                encontrado = false;
                String cor = Teclado.leString("Cor: ");

                for(Veiculo v : veiculos){
                    if (v.getCor().equalsIgnoreCase(cor)) {
                        System.out.println(v);
                    }
                }

                if (encontrado == false) {
                    System.out.println("Cor não encontrada.");
                }
                break;

            // pesquisa por ano
            case 3:
                encontrado = false;
                int ano = Teclado.leInt("Ano: ");

                for(Veiculo v : veiculos){
                    if (v.getAno() == ano) {
                        System.out.println(v);
                    }
                }

                if (encontrado == false) {
                    System.out.println("Ano não encontrado.");
                }
                break;

            // pesquisa por cidade
            case 4:
                encontrado = false;
                String cidade = Teclado.leString("Cidade: ");

                for(Veiculo v : veiculos){
                    if (v.getCidade().equalsIgnoreCase(cidade)) {
                        System.out.println(v);
                    }
                }

                if (encontrado == false) {
                    System.out.println("Cor não encontrada.");
                }
                break;
            default:
                break;
        }
    }
}