public class Main {
    public static void main(String[] args) {

        // cria o objeto locadora e carrega os dados sempre que o sistema abre
        Locadora locadora = new Locadora();
        locadora.carregaDados();

        // iniciando menu
        int escolha = 0;

        while (escolha != 8) {
            System.out.print("\033\143");

            System.out.println("\n===== LOCADORA DE VEÍCULOS =====");
            System.out.println("1 - Consultar veículos");
            System.out.println("2 - Realizar locação");
            System.out.println("3 - Realizar devolução");
            System.out.println("4 - Consultar locações");
            System.out.println("5 - Resumo");
            System.out.println("6 - Salvar");
            System.out.println("7 - Integrantes");
            System.out.println("8 - Sair");

            escolha = Teclado.leInt("Digite sua escolha: ");

            switch (escolha) {
                case 1:
                    System.out.print("\033\143"); // limpa tela do terminal
                    locadora.consultaVeiculo();
                    System.out.println("Pressione enter para voltar ao menu...");
                    Teclado.leString();
                    break;

                case 2:
                    System.out.print("\033\143"); // limpa tela do terminal
                    locadora.realizaLocacao();
                    System.out.println("Pressione enter para voltar ao menu...");
                    Teclado.leString();
                    break;

                case 3:
                    System.out.print("\033\143"); // limpa tela do terminal
                    locadora.realizaDevolucao();
                    System.out.println("Pressione enter para voltar ao menu...");
                    Teclado.leString();
                    break;

                case 4:
                    System.out.println("\033\143");
                    locadora.consultaLocacao();
                    System.out.println("Pressione enter para voltar ao menu...");
                    Teclado.leString();
                    break;

                case 5:
                    System.out.println("Em construção...");
                    break;

                case 6:
                    System.out.print("\033\143"); // limpa tela do terminal
                    locadora.salvaDados();
                    System.out.println("Dados salvos com sucesso.");
                    System.out.println("Pressione enter para voltar ao menu...");
                    Teclado.leString();
                    break;

                case 7:
                    mostrarNomes();
                    break;

                case 8:
                    locadora.salvaDados();
                    System.out.println("\nObrigado por usar nossos serviços.");
                    System.out.println("Fechando o sistema...\n");
                    break;
                default:
                    break;
            }
        }
    }

    //função para mostrar os nomes dos participantes 
    public static void mostrarNomes() {
        System.out.print("\033\143"); // limpa tela do terminal
        System.out.println("------------------------------------------------");
        System.out.println("|                  Integrantes                 |");
        System.out.printf("| %-44s |\n", "Arthur Ferreira da Silva");
        System.out.printf("| %-44s |\n", "Leonardo Fernandes");
        System.out.printf("| %-44s |\n", "Lucas Furquim Jardim");
        System.out.printf("| %-44s |\n", "João Pedro Ouriques Severo");
        System.out.println("------------------------------------------------");

        System.out.println("\nPressione ENTER para voltar ao menu...");
        Teclado.leString();
    }
}