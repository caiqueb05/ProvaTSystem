package Ex09;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SistemaControle implements AvaliacaoPericulosidade, QuarentenaMonitoramento, RelatorioEntradas, FuncoesExtras {

    private List<Alienigena> alienigenas = new ArrayList<>();
    private List<Especie> especies = new ArrayList<>();


    @Override
    public void avaliarPericulosidade(Alienigena alienigena) {
        int nivelPericulosidadeBase = alienigena.getEspecie().getNivelPericulosidade();
        int nivelPericulosidadeFinal = nivelPericulosidadeBase;
        alienigena.setNivelPericulosidade(nivelPericulosidadeFinal);
    }

    @Override
    public void colocarEmQuarentena(Alienigena alienigena) {
        int nivelPericulosidade = alienigena.getEspecie().getNivelPericulosidade();
        if (nivelPericulosidade > 5) {
            alienigena.setEmQuarentena(true);
            System.out.println("Alienígena " + alienigena.getNome() + " colocado em quarentena.");
        } else {
            alienigena.setEmQuarentena(false);
            System.out.println("O alienígena " + alienigena.getNome() + " não possui periculosidade suficiente para quarentena.");
        }
    }

    @Override
    public void monitorarQuarentena() {
        System.out.println("Alienígenas em quarentena: ");
        for (Alienigena alienigena : alienigenas) {
            if (alienigena.isEmQuarentena()) {
                System.out.println("Nome: " + alienigena.getNome() + ", Espécie: " + alienigena.getEspecie().getNome() + ", Nível de Periculosidade: " + alienigena.getNivelPericulosidade());
            }
        }
    }

    @Override
    public void gerarRelatorio(ArrayList<Alienigena> alienigenas) {
        System.out.println("Relatório de Alienígenas: ");
        for (Alienigena alienigena : alienigenas) {
            System.out.println("Nome: " + alienigena.getNome() + ", Espécie: " + alienigena.getEspecie().getNome() + ", Nível de Periculosidade: " + alienigena.getEspecie().getNivelPericulosidade() + ", Em Quarentena: " + (alienigena.isEmQuarentena() ? "Sim" : "Não"));
        }
    }


    @Override
    public void rankingPericulosidade() {
        alienigenas.stream()
                .sorted(Comparator.comparingInt(Alienigena::getNivelPericulosidade).reversed())
                .forEach(a -> System.out.println(a.getNome() + " - " + a.getNivelPericulosidade()));
    }

    @Override
    public void verificarTodosCadastrados() {
        System.out.println("Espécies e seus planetas de origem:");
        especies.forEach(e -> System.out.println(e.getNome() + " - " + e.getPlanetaOrigem()));
        System.out.println("Alienígenas cadastrados:");
        alienigenas.forEach(a -> System.out.println(a.getNome() + " - " + a.getEspecie().getNome()));
    }

    @Override
    public void verificarQuarentena() {
        alienigenas.stream()
                .filter(Alienigena::isEmQuarentena)
                .forEach(a -> System.out.println(a.getNome()));
    }

    @Override
    public void mostrarEspecieAlienigenas(String especieNome) {
        alienigenas.stream()
                .filter(a -> a.getEspecie().getNome().equalsIgnoreCase(especieNome))
                .forEach(a -> System.out.println(a.getNome()));
    }

    @Override
    public void mostrarAlienigenasUltimos6Meses() {
        alienigenas.stream()
                .filter(a -> a.getDataHoraEntrada().isAfter(LocalDateTime.now().minus(6, ChronoUnit.MONTHS)))
                .forEach(a -> System.out.println(a.getNome()));
    }

    public Especie buscarEspeciePorNome(String nomeEspecie) {
        for (Especie especie : especies) {
            if (especie.getNome().equalsIgnoreCase(nomeEspecie)) {
                return especie;
            }
        }
        return null;
    }

    public void adicionarAlienigena(Alienigena alienigena) {
        alienigenas.add(alienigena);
    }

    public void adicionarEspecie(Especie especie) {
        especies.add(especie);
    }

    public ArrayList<Especie> getEspecies() {
        return new ArrayList<>(especies);
    }

    public void mostrarEspecies() {
        System.out.println("Espécies cadastradas:");
        for (Especie especie : especies) {
            System.out.println("Nome: " + especie.getNome() + ", Planeta de Origem: " + especie.getPlanetaOrigem() + ", Nível de Periculosidade: " + especie.getNivelPericulosidade());
        }
    }

    public ArrayList<Alienigena> getAlienigenas() {
        return new ArrayList<>(alienigenas);
    }
}
