package main.java.br.com.dbc.vemser.hellbet.model;

import java.util.ArrayList;

public class Dealer extends Participante implements ParticipanteMetodos {

    public Dealer(int vida, ArrayList<Carta> mao) {
        super(vida, mao);
    }

    @Override
    public void adicionarAMao(Carta cartaComprada) {
        getMao().add(cartaComprada);
    }


    @Override
    public int contarPontosNaMao() {
        int somaMao = 0;
        for (Carta carta : getMao()) {
            int valorDaCarta = carta.getValor();

            if (valorDaCarta > 10) {
                somaMao += 10;
            } else {
                somaMao += valorDaCarta;
            }
        }
        return somaMao;
    }

    @Override
    public boolean validarEstouro() {
        return contarPontosNaMao() > 21;
    }

    @Override
    public void perderVida() {
        if (getVida() > 0) {
            setVida(getVida() - 1);
        }
    }

    @Override
    public void limparMao() {
        getMao().clear();
    }

    public boolean escolherComprar() {
        if( contarPontosNaMao() < 17 ){
            return true;
        }
        return false;
    }

    public void ativarReceberDano() {
        perderVida();
    }

    public void ativarForcarCompra(Carta cartaComprada) {
        adicionarAMao(cartaComprada);
    }
}
