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
                    System.out.println("Em construção...");

                case 5:
                    System.out.println("Em construção...");

                case 6:
                    System.out.println("Em construção...");

                case 7:
                    System.out.print("\033\143"); // limpa tela do terminal
                    System.out.println("------------------------------------------------");
                    System.out.println("|                  Integrantes                 |");
                    System.out.printf("| %-44s |\n", "Arthur Ferreira da Silva");
                    System.out.printf("| %-44s |\n", "Leonardo Fernandes");
                    System.out.printf("| %-44s |\n", "Lucas Furquim Jardim");
                    System.out.printf("| %-44s |\n", "João Schmidt");
                    System.out.println("------------------------------------------------");

                    System.out.println("\nPressione ENTER para voltar ao menu...");
                    Teclado.leString();
                    break;

                case 8:
                    System.out.println("\nObrigado por usar nossos serviços.");
                    System.out.println("Fechando o sistema...\n");

                    break;
                default:
                    break;
            }
        }
    }
}