package main.java.br.com.dbc.vemser.hellbet.model;

import java.util.ArrayList;

public class Jogador extends Participante implements ParticipanteMetodos {

    public Jogador(String nome, int vida, ArrayList<Carta> mao, int ficha) {
        super(nome, vida, mao, ficha);
    }

    public void adicionarAMao(Carta cartaComprada) {

        getMao().add(cartaComprada);
    }

    @Override
    public int contarPontosNaMao() {
        int somaMao = 0;
        for (Carta carta : getMao()) {
            if(carta.getValor() > 10) {

                somaMao += 10;
            } else {
                somaMao += carta.getValor();
            }
        }
        return somaMao;
    }

    @Override
    public boolean validarEstouro(){
        return  contarPontosNaMao() > 21;
    }

    @Override
    public void perderVida(){
        setVida(getVida() - 1);
    }

    @Override
    public void limparMao(){
        getMao().clear();
    }

    public void ativarDesfazerUltimaJogada(){
        getMao().remove(getMao().toArray().length - 1);
    }

    public void ativarRecuperarVida() {
        setVida(getVida() + 1);
    }

    public void adicionarFicha(){
        setFicha(getFicha() + 1);
    }

    public boolean temFicha(){
        return getFicha() > 0;
    }

    public boolean isVidaMaxima(){
        return getVida() == 3;
    }

    public boolean isMaoVazia(){
        return  getMao().isEmpty();
    }

    public void gastarFicha() {
        setFicha(getFicha() - 1);
    }

}


