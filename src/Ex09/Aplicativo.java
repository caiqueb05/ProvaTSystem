package Ex09;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Aplicativo {

    private SistemaControle sistemaControle = new SistemaControle();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        boolean rodando = true;
        while (rodando) {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Cadastrar Espécie");
            System.out.println("2. Cadastrar Alienígena");
            System.out.println("3. Mostrar Espécies");
            System.out.println("4. Mostrar Alienígenas");
            System.out.println("5. Ranking de Periculosidade");
            System.out.println("6. Verificar todos planetas, espécies e alienígenas cadastrados");
            System.out.println("7. Verificar alienígenas em quarentena");
            System.out.println("8. Mostrar todos os alienígenas de uma espécie específica");
            System.out.println("9. Mostrar todos os alienígenas que entraram no planeta nos últimos 6 meses");
            System.out.println("10. Relatório de Entradas");
            System.out.println("11. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarEspecie();
                        break;
                    case 2:
                        cadastrarAlienigena();
                        break;
                    case 3:
                        sistemaControle.mostrarEspecies();
                        break;
                    case 4:
                        sistemaControle.verificarTodosCadastrados();
                        break;
                    case 5:
                        sistemaControle.rankingPericulosidade();
                        break;
                    case 6:
                        sistemaControle.verificarTodosCadastrados();
                        break;
                    case 7:
                        sistemaControle.verificarQuarentena();
                        break;
                    case 8:
                        System.out.print("Digite o nome da espécie: ");
                        String especieNome = scanner.nextLine();
                        sistemaControle.mostrarEspecieAlienigenas(especieNome);
                        break;
                    case 9:
                        sistemaControle.mostrarAlienigenasUltimos6Meses();
                        break;
                    case 10:
                        gerarRelatorioEntradas();
                        break;
                    case 11:
                        rodando = false;
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine();
            }
        }
    }

    private void gerarRelatorioEntradas() {
        sistemaControle.gerarRelatorio(sistemaControle.getAlienigenas());
    }

    private void cadastrarAlienigena() {
        System.out.println("Cadastro de Alienígena");
        System.out.print("ID do Alienígena: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nome do Alienígena: ");
        String nome = scanner.nextLine();
        Especie especie = null;
        System.out.println("Espécies cadastradas:");
        ArrayList<Especie> especiesCadastradas = sistemaControle.getEspecies();
        if (especiesCadastradas.isEmpty()) {
            System.out.println("Nenhuma espécie cadastrada. Por favor, cadastre uma espécie.");
            return;
        }
        for (int i = 0; i < especiesCadastradas.size(); i++) {
            System.out.println(i + " - " + especiesCadastradas.get(i).getNome() + " - Periculosidade: " + especiesCadastradas.get(i).getNivelPericulosidade());
        }
        System.out.println("Escolha a espécie pelo índice ou digite qualquer outro número para sair:");
        int indiceEspecie = scanner.nextInt();
        scanner.nextLine();
        if (indiceEspecie >= 0 && indiceEspecie < especiesCadastradas.size()) {
            especie = especiesCadastradas.get(indiceEspecie);
        } else {
            System.out.println("Operação cancelada ou índice inválido.");
            return;
        }
        LocalDateTime dataHoraEntrada = LocalDateTime.now();
        Alienigena alienigena = new Alienigena(id, nome, especie, dataHoraEntrada, false);
        sistemaControle.adicionarAlienigena(alienigena);
        sistemaControle.avaliarPericulosidade(alienigena);
        sistemaControle.colocarEmQuarentena(alienigena);
        System.out.println("Alienígena cadastrado com sucesso.");
    }

    private void cadastrarEspecie() {
        System.out.println("Cadastro de Espécie");
        System.out.print("Nome da Espécie: ");
        String nomeEspecie = scanner.nextLine();
        System.out.print("Planeta de Origem: ");
        String planetaOrigem = scanner.nextLine();
        int nivelPericulosidade;
        do {
            System.out.print("Nível de Periculosidade 1-10: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                scanner.next();
            }
            nivelPericulosidade = scanner.nextInt();
            scanner.nextLine();
            if (nivelPericulosidade < 1 || nivelPericulosidade > 10) {
                System.out.println("O nível de periculosidade deve estar entre 1 e 10.");
            }
        } while (nivelPericulosidade < 1 || nivelPericulosidade > 10);

        Especie especieExistente = sistemaControle.buscarEspeciePorNome(nomeEspecie);
        if (especieExistente != null) {
            System.out.println("Espécie já cadastrada.");
            return;
        }

        Especie novaEspecie = new Especie(nomeEspecie, planetaOrigem, nivelPericulosidade);
        sistemaControle.adicionarEspecie(novaEspecie);
        System.out.println("Espécie cadastrada com sucesso.");
    }
}
