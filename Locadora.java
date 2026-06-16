import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Locadora {
    // atributos
    ArrayList<Veiculo> veiculos = new ArrayList<>();
    ArrayList<Locacao> locacoes = new ArrayList<>();
    // variável para controle
    boolean encontrado = false;

    // método para carregar os dados dos arquivos "veiculos.txt" e "locacoes.txt"
    public void carregaDados() {
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
            // while para ler locacoes.txt
            while ((linhaL = bufferLocacoes.readLine()) != null) {
                Locacao l = new Locacao();
                l.desserializar(linhaL, veiculos);
                locacoes.add(l);
            }

            // Ajusta disponibilidade dos veículos com base nas locações carregadas.
            // Regra aprovada: qt_dias_realizado == 0 => locação ativa (veículo
            // indisponível)
            // caso contrário => locação finalizada (veículo disponível)
            for (Locacao l : locacoes) {
                if (l == null || l.getVeiculo() == null)
                    continue;

                if (l.getQt_dias_realizado() == 0) {
                    l.getVeiculo().setDisponivel(false);
                } else {
                    l.getVeiculo().setDisponivel(true);
                }
            }

            bufferVeiculos.close();
            bufferLocacoes.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // método para salvar dados nos arquivos
    public void salvaDados() {
        try {
            FileWriter writerVeiculos = new FileWriter("veiculos.txt");
            BufferedWriter bufferVeiculos = new BufferedWriter(writerVeiculos);
            FileWriter writerLocacoes = new FileWriter("locacoes.txt");
            BufferedWriter bufferLocacoes = new BufferedWriter(writerLocacoes);

            // escreve cabecalho de veiculos (garante que carregaDados() nao pule a primeira linha de dados)
            bufferVeiculos.write("codigo\tmodelo\tcor\tano\todometro\tcidade\tdisponivel\tvalorDiaria\tvalorKmRodado");
            bufferVeiculos.newLine();

            // salva veiculos no txt
            for (Veiculo v : veiculos) {
                bufferVeiculos.write(v.serializar());
                bufferVeiculos.newLine();
            }

            // escreve cabecalho de locacoes
            bufferLocacoes.write("codigoVeiculo\tcliente\torigem\tdestino\tkmRodado\tqtDiasReserva\tqtDiasRealizado");
            bufferLocacoes.newLine();

            // salva locacoes no txt (pula locacoes com veiculo nulo para evitar NPE)
            for (Locacao l : locacoes) {
                if (l == null || l.getVeiculo() == null) continue;
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
    public void consultaVeiculo() {
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

                for (Veiculo v : veiculos) {
                    if (v.getCor().equalsIgnoreCase(cor)) {
                        System.out.println(v);
                        encontrado = true;
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

                for (Veiculo v : veiculos) {
                    if (v.getAno() == ano) {
                        System.out.println(v);
                        encontrado = true;
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

                for (Veiculo v : veiculos) {
                    if (v.getCidade().equalsIgnoreCase(cidade)) {
                        System.out.println(v);
                        encontrado = true;
                    }
                }

                if (encontrado == false) {
                    System.out.println("Cidade não encontrada.");
                }
                break;

            default:
                break;
        }
    }

    // método para realizar locação
    public void realizaLocacao() {
        String cidade = Teclado.leString("Em qual cidade você deseja alugar o veículo? ");
        encontrado = false;

        for (Veiculo v : veiculos) {
            if (v.getCidade().equalsIgnoreCase(cidade) && v.isDisponivel()) {
                System.out.println(v);
                encontrado = true;
            }
        }

        if (encontrado == false) {
            System.out.println("Veículos não encontrados em " + cidade);
            return;
        }

        int codigo = Teclado.leInt("Informe o código do veículo que deseja alugar: ");
        Veiculo veiculoSelecionado = null;

        for (Veiculo v : veiculos) {
            if (v.getCodigo() == codigo && v.isDisponivel()) {
                veiculoSelecionado = v;
                break;
            }
        }

        if (veiculoSelecionado == null) {
            System.out.println("Código inválido.");
            return;
        }

        // recebendo dados do usuário e criando a locação
        String nomeCliente = Teclado.leString("Digite seu nome: ");
        // verifica se o cliente possui algum aluguel em aberto
        for (Locacao l : locacoes) {
            if (l.getCliente().equalsIgnoreCase(nomeCliente) && !l.getVeiculo().isDisponivel()) {
                System.out.println("Este cliente já possui uma locação em aberto.");
                return;
            }
        }

        int qtDias = Teclado.leInt("Digite a quantidade de dias do aluguel: ");
        double valorDiarias = veiculoSelecionado.getValor_diaria() * qtDias;
        System.out.printf("Valor da diária: R$%.2f", veiculoSelecionado.getValor_diaria());
        System.out.printf("\nTotal: R$%.2f", valorDiarias);

        String aceita = Teclado.leString("\nDigite (s) para confirmar aluguel ou (n) para não alugar: ");
        if (aceita.equalsIgnoreCase("não") || aceita.equalsIgnoreCase("n")) {
            System.out.println("Aluguel cancelado.");
            return;
        }

        // cria locação
        Locacao locacao = new Locacao(veiculoSelecionado, nomeCliente, veiculoSelecionado.getCidade(), qtDias);
        locacoes.add(locacao); // adicionando a locação no arraylist
        veiculoSelecionado.setDisponivel(false);// seta veículo como indisponível
        System.out.println("Locação realizada com sucesso.");
    }

    // Devolução do veículo
    public void realizaDevolucao() {
        String nomeCliente = Teclado.leString("Digite o nome do cliente: ");
        String cidadeDevolucao = Teclado.leString("Digite a cidade de devolução: ");
        int kmPercorridos = Teclado.leInt("Digite a quilometragem percorrida: ");

        // Checa se o veículo tá indisponível (alugado)
        Locacao locacaoAberta = null;
        for (Locacao l : locacoes) {
            if (l.getCliente().equalsIgnoreCase(nomeCliente) && !l.getVeiculo().isDisponivel()) {
                locacaoAberta = l;
                break;
            }
        }

        if (locacaoAberta == null) {
            System.out.println("Não existe locação em aberto para esse cliente.");
            return;
        }

        int diasContratados = locacaoAberta.getQt_dias_reserva();
        System.out.println("Dias contratados: " + diasContratados);

        int diasRealizados = Teclado.leInt("Digite a quantidade real de dias que utilizou o carro: ");
        locacaoAberta.setQt_dias_realizado(diasRealizados);

        double valorDiaria = locacaoAberta.getVeiculo().getValor_diaria();
        double diariasContratadas = diasContratados * valorDiaria;

        double valorDiariasAjustadas;
        if (diasRealizados < diasContratados) {
            int diasNaoUtilizados = diasContratados - diasRealizados;
            double diariasNaoUtilizadas = diasNaoUtilizados * valorDiaria;
            double desconto = 0.20 * diariasNaoUtilizadas;
            valorDiariasAjustadas = diariasContratadas - desconto;
            System.out.printf("Desconto de 20%% sobre diárias não utilizadas: R$%.2f\n", desconto);
        } else if (diasRealizados > diasContratados) {
            int diasExtras = diasRealizados - diasContratados;
            double diariasExtras = diasExtras * valorDiaria;
            double multa = 0.30 * diariasExtras;
            valorDiariasAjustadas = diariasContratadas + diariasExtras + multa;
            System.out.printf("Multa de 30%% sobre diárias extras: R$%.2f\n", multa);
        } else {
            valorDiariasAjustadas = diariasContratadas;
        }

        // valores
        double valorKm = kmPercorridos * locacaoAberta.getVeiculo().getValor_km_rodado();
        double total = valorDiariasAjustadas + valorKm;

        System.out.printf("Valor das diárias ajustadas: R$%.2f\n", valorDiariasAjustadas);
        System.out.printf("Valor dos quilômetros rodados: R$%.2f\n", valorKm);
        System.out.printf("Total a pagar: R$%.2f\n", total);

        // atualiza dados e fecha o processo
        locacaoAberta.setDestino(cidadeDevolucao);
        locacaoAberta.setKm_rodado(kmPercorridos);

        Veiculo v = locacaoAberta.getVeiculo();
        v.setCidade(cidadeDevolucao);
        v.setOdometro(v.getOdometro() + kmPercorridos);
        v.setDisponivel(true);

        System.out.println("Devolução registrada com sucesso. Veículo liberado para nova locação.");
    }

    public void consultaLocacao() {

        System.out.println("Consultar locações por:");
        System.out.println("1 - Nome do cliente");
        System.out.println("2 - Modelo do veículo");

        int escolha = Teclado.leInt("Digite sua escolha: ");

        boolean encontrado = false;

        switch (escolha) {

            case 1:
                String cliente = Teclado.leString("Nome do cliente: ");

                for (Locacao l : locacoes) {

                    if (l.getCliente().equalsIgnoreCase(cliente)) {

                        System.out.println("\n===== LOCAÇÃO =====");

                        if (l.getQt_dias_realizado() > 0) {
                            System.out.println("Status: Finalizada");
                        } else {
                            System.out.println("Status: Em aberto");
                        }

                        System.out.println(l);
                        encontrado = true;
                    }
                }

                if (!encontrado) {
                    System.out.println("Nenhuma locação encontrada para este cliente.");
                }
                break;

            case 2:
                String modelo = Teclado.leString("Modelo do veículo: ");

                for (Locacao l : locacoes) {

                    if (l.getVeiculo().getModelo().equalsIgnoreCase(modelo)) {

                        System.out.println("\n===== LOCAÇÃO =====");

                        if (l.getQt_dias_realizado() > 0) {
                            System.out.println("Status: Finalizada");
                        } else {
                            System.out.println("Status: Em aberto");
                        }

                        System.out.println(l);
                        encontrado = true;
                    }
                }

                if (!encontrado) {
                    System.out.println("Nenhuma locação encontrada para este modelo.");
                }
                break;

            default:
                System.out.println("Opção inválida.");
                break;
        }
    }
      public void relatorioResumo() {
        int totalKmRodado = 0;
        int totalDiasContratados = 0;
        int totalDiasRealizados = 0;
        double totalValorDiariasContratadas = 0;
        double totalValorDiariasExtras = 0;
        double totalValorKmRodado = 0;
        double totalGeral = 0;
        int qtdFinalizadas = 0;

        for (Locacao l : locacoes) {
            // pula locações com veículo não encontrado
            if (l == null || l.getVeiculo() == null) continue;
            // locação finalizada = qt_dias_realizado > 0
            if (l.getQt_dias_realizado() > 0) {
                qtdFinalizadas++;

                totalKmRodado += l.getKm_rodado();
                totalDiasContratados += l.getQt_dias_reserva();
                totalDiasRealizados += l.getQt_dias_realizado();

                double diariasContratadas = l.getQt_dias_reserva() * l.getVeiculo().getValor_diaria();
                totalValorDiariasContratadas += diariasContratadas;

                double valorDiariasAjustadas = l.valorDiarias();
                totalValorDiariasExtras += (valorDiariasAjustadas - diariasContratadas);

                double valorKm = l.valorKmRodado();
                totalValorKmRodado += valorKm;

                totalGeral += valorDiariasAjustadas + valorKm;
            }
        }

        System.out.println("\n========== RESUMO DAS LOCAÇÕES FINALIZADAS ==========");

        if (qtdFinalizadas == 0) {
            System.out.println("Nenhuma locação finalizada encontrada.");
            return;
        }

        System.out.println("Locações finalizadas:              " + qtdFinalizadas);
        System.out.println("Total de km rodados:               " + totalKmRodado + " km");
        System.out.println("Total de dias contratados:         " + totalDiasContratados);
        System.out.println("Total de dias realizados:          " + totalDiasRealizados);
        System.out.printf("Valor das diárias contratadas:     R$%.2f\n", totalValorDiariasContratadas);
        System.out.printf("Valor das diárias extras:          R$%.2f\n", totalValorDiariasExtras);
        System.out.printf("Valor dos km rodados:              R$%.2f\n", totalValorKmRodado);
        System.out.printf("Valor total das locações:          R$%.2f\n", totalGeral);
        System.out.println("======================================================");
    }
}