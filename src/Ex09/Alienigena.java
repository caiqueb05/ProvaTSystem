package Ex09;

import java.time.LocalDateTime;

public class Alienigena {
    private int id;
    private String nome;
    private Especie especie;
    private int nivelPericulosidade;
    private LocalDateTime dataHoraEntrada;
    private boolean emQuarentena;

    public Alienigena(int id, String nome, Especie especie, LocalDateTime dataHoraEntrada, boolean emQuarentena) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.dataHoraEntrada = dataHoraEntrada;
        this.emQuarentena = emQuarentena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public int getNivelPericulosidade() {
        return nivelPericulosidade;
    }

    public void setNivelPericulosidade(int nivelPericulosidade) {
        this.nivelPericulosidade = nivelPericulosidade;
    }

    public LocalDateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public boolean isEmQuarentena() {
        return emQuarentena;
    }

    public void setEmQuarentena(boolean emQuarentena) {
        this.emQuarentena = emQuarentena;
    }
}
