package main.java.br.com.dbc.vemser.hellbet.model;

import java.util.ArrayList;

public abstract class Participante {

    private String nome;
    private int vida;
    private ArrayList<Carta> mao;
    private int ficha;
    private boolean parou;

    public boolean isParou() {
        return parou;
    }

    public void setParou(boolean parou) {
        this.parou = parou;
    }

    public Participante(String nome, int vida, ArrayList<Carta> mao, int ficha) {
        this.nome = nome;
        this.vida = vida;
        this.mao = mao;
        this.ficha = ficha;
        this.parou = false;
    }

    public Participante(int vida, ArrayList<Carta> mao) {
        this.nome = "Dealer";
        this.vida = vida;
        this.mao = mao;
        this.ficha = 0;
        this.parou = false;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public ArrayList<Carta> getMao() {
        return mao;
    }

    public int getFicha() {
        return ficha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setMao(ArrayList<Carta> mao) {
        this.mao = mao;
    }

    public void setFicha(int ficha) {
        this.ficha = ficha;
    }

}