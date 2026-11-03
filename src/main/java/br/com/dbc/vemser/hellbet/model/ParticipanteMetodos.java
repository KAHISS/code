package main.java.br.com.dbc.vemser.hellbet.model;

public interface ParticipanteMetodos {

    void adicionarAMao(Carta cartaComprada);
    boolean validarEstouro();
    void perderVida();
    void limparMao();
    int contarPontosNaMao();

}
