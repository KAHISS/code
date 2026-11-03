package main.java.br.com.dbc.vemser.hellbet.model;

import java.util.*;

public class Baralho {
    private Deque<Carta> cartas;

    public Baralho() {
        this.cartas = new ArrayDeque<>();
    }

    public Deque<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(Deque<Carta> cartas) {
        this.cartas = cartas;
    }

    public Carta comprarCarta() {
        return cartas.pop();
    }

    public ArrayList<Carta> comprarCarta(int quantidade) {
        ArrayList<Carta> cartasCompradas = new ArrayList<>();

        for (int i = 0; i < quantidade; i++) {
            cartasCompradas.add(cartas.pop());
        }

        return  cartasCompradas;
    }

    public void embaralhar() {
        List<Carta> listaTemp = new ArrayList<>(cartas);

        Collections.shuffle(listaTemp);

        cartas.clear();
        for (int i = listaTemp.size() - 1; i >= 0; i--) {
            cartas.push(listaTemp.get(i));
        }
    }

    public void resetar() {
        String[] nipes = {"♥", "♣", "♠", "♦"};

        cartas.clear();
        for (String nipe: nipes) {
            for (int i = 1; i<=13; i++) {
                cartas.push(new Carta(i, nipe));
            }
        }
    }
}
