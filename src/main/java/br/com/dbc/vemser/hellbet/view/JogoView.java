package main.java.br.com.dbc.vemser.hellbet.view;

import main.java.br.com.dbc.vemser.hellbet.model.Carta;
import main.java.br.com.dbc.vemser.hellbet.model.Dealer;
import main.java.br.com.dbc.vemser.hellbet.model.Jogador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class JogoView {
    public static final String RESET = "\u001B[0m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARELO = "\u001B[33m";
    public static final String CIANO = "\u001B[36m";

    private Scanner input;

    public JogoView() {
        this.input = new Scanner(System.in);
    }

    public void exibirMenu() {
        limparTela();
        System.out.println(VERMELHO +
                "   _  _ ___ _    _    ___  ___ _____ \n" +
                "  | || | __| |  | |  | _ )| __|_   _|\n" +
                "  | __ | _|| |__| |__| _ \\| _|  | |  \n" +
                "  |_||_|___|____|____|___/|___| |_|  \n" + RESET);
        System.out.print(VERDE +
                "  ┌───────────────────────────────────────────────────┐\n" +
                "  │=================[ MenuPrincipal ]=================│\n" +
                "  ├───────────────────────────────────────────────────┤\n" +
                "  │                                                   │\n" +
                "  │    [ 1 ] > Iniciar Jogo                           │\n" +
                "  │    [ 2 ] > Customizar Nome                        │\n" +
                "  │    [ 3 ] > Sair                                   │\n" +
                "  │                                                   │\n" +
                "  └───────────────────────────────────────────────────┘\n" +
                "  Selecione uma opção > " + RESET);
    }

    public int lerOpcaoJogador() {
        int opcao = Integer.parseInt(input.nextLine());
        return opcao;
    }

    public void exibirJogo(Jogador jogador, Dealer dealer) {
        limparTela();
        System.out.printf(VERDE +
                "┌───────────────────────────────────────────────────────────────────────┐\n" +
                "  │==============================[ MESA ]=============================│\n" +
                "                         --- STATUS DO DEALER ---                      \n" +
                "       DEALER: " + CIANO +"%s " + VERDE + " | " + "Vida: " + VERMELHO +" %d " + VERDE + "                                        \n" +
                "  ┌───────────────────────────────────────────────────────────────────┐\n" +
                "  │    Cartas do dealer:                                              │\n", dealer.getNome(), dealer.getVida());
        for (String linhaCarta : exibirCartasDealer(dealer.getMao(), false)) {
            System.out.printf("    %-63s\n", linhaCarta);
        }
        System.out.println(
                "  └───────────────────────────────────────────────────────────────────┘");
        System.out.print(
                "  ┌───────────────────────────────────────────────────────────────────┐\n" +
                "  │    Total de pontos atual: X                                       │\n" +
                "  └───────────────────────────────────────────────────────────────────┘\n");
        System.out.println("                                                                       \n" +
                "  │===================================================================│\n" +

                "\n                         --- STATUS DO JOGADOR ---                     \n" +
                "       JOGADOR: " + CIANO + jogador.getNome() + VERDE + " | " + "Vida: " + VERMELHO + jogador.getVida() + VERDE + " | Fichas: " + AMARELO +  jogador.getFicha() + VERDE + "                          \n" +
                "  ┌───────────────────────────────────────────────────────────────────┐\n" +
                "  │    Cartas do jogador:                                             │\n");
        for (String linhaCarta : exibirCartasJogador(jogador.getMao())) {
            System.out.printf("    %-63s\n", linhaCarta);
        }
        System.out.println(
                "  └───────────────────────────────────────────────────────────────────┘");
        System.out.printf(
                "  ┌───────────────────────────────────────────────────────────────────┐\n" +
                "  │    Total de pontos atual: %02d                                      │\n" +
                "  └───────────────────────────────────────────────────────────────────┘\n" +
                "└───────────────────────────────────────────────────────────────────────┘" + RESET, jogador.contarPontosNaMao());
        System.out.println();
        System.out.print(VERDE +
                "  ┌───────────────────────────────────────────────────┐\n" +
                "  │====================[ OPÇÕES ]=====================│\n" +
                "  ├───────────────────────────────────────────────────┤\n" +
                "  │                                                   │\n" +
                "  │    [ 1 ] > Solicitar Carta                        │\n" +
                "  │    [ 2 ] > Parar                                  │\n" +
                "  │    [ 3 ] > Usar habilidade                        │\n" +
                "  │                                                   │\n" +
                "  └───────────────────────────────────────────────────┘\n" +
                "  Selecione uma opção > " + RESET);
    }

    public void exibirResultado(Jogador jogador, Dealer dealer, boolean estourou) {
        limparTela();
        System.out.printf(VERDE +
                "┌───────────────────────────────────────────────────────────────────────┐\n" +
                "  │==============================[ MESA ]=============================│\n" +
                "                         --- STATUS DO DEALER ---                      \n" +
                "       DEALER: " + CIANO +"%s " + VERDE + " | " + "Vida: " + VERMELHO +" %d " + VERDE +"                                        \n" +
                "  ┌───────────────────────────────────────────────────────────────────┐\n" +
                "  │    Cartas do dealer:                                              │\n", dealer.getNome(), dealer.getVida());
        System.out.println();
        for (String linhaCarta : exibirCartasDealer(dealer.getMao(), estourou)) {
            System.out.printf("    %-63s\n", linhaCarta);
        }
        System.out.println(
                "  └───────────────────────────────────────────────────────────────────┘");
        if (!estourou) {
            System.out.print(
                    "  ┌───────────────────────────────────────────────────────────────────┐\n" +
                    "  │    Total de pontos atual: X                                       │\n" +
                    "  └───────────────────────────────────────────────────────────────────┘\n");
        } else {
            System.out.printf(
                    "  ┌───────────────────────────────────────────────────────────────────┐\n" +
                    "  │    Total de pontos atual: %02d                                      │\n" +
                    "  └───────────────────────────────────────────────────────────────────┘\n", dealer.contarPontosNaMao());
        }
        System.out.println("                                                                       \n" +
                    "  │===================================================================│\n" +

        "\n                         --- STATUS DO JOGADOR ---                     \n" +
                "       JOGADOR: " + CIANO + jogador.getNome() + VERDE + " | " + "Vida: " + VERMELHO + jogador.getVida() + VERDE + " | Fichas: " + AMARELO +  jogador.getFicha() + VERDE +  "                          \n" +
                "  ┌───────────────────────────────────────────────────────────────────┐\n" +
                "  │    Cartas do jogador:                                             │\n");
        for (String linhaCarta : exibirCartasJogador(jogador.getMao())) {
            System.out.printf("    %-63s\n", linhaCarta);
        }
        System.out.println(
                "  └───────────────────────────────────────────────────────────────────┘");
        System.out.printf(
                "  ┌───────────────────────────────────────────────────────────────────┐\n" +
                "  │    Total de pontos atual: %02d                                      │\n" +
                "  └───────────────────────────────────────────────────────────────────┘\n" +
                "└───────────────────────────────────────────────────────────────────────┘", jogador.contarPontosNaMao());
        System.out.println();
        System.out.println("  > Pressione ENTER para prosseguir..."+ RESET);
        input.nextLine();
    }

    public List<String> exibirCartasJogador(ArrayList<Carta> cartas) {
        String[] linhas = new String[] {"", "", "", "", "", "", ""};
        for (Carta carta : cartas) {
            String cartaJQK = String.valueOf(carta.getValor());

            if (carta.getValor() == 1) {
                cartaJQK = "A";
            } else if (carta.getValor() == 11) {
                cartaJQK = "J";
            } else if (carta.getValor() == 12) {
                cartaJQK = "Q";
            } else if (carta.getValor() == 13) {
                cartaJQK = "K";
            }

            linhas[0] += "┌─────────┐ ";
            linhas[1] += String.format("| " + VERMELHO+ "%s" + VERDE + "       | ", carta.getNaipe());
            linhas[2] += "|         | ";
            linhas[3] += String.format("│   " + RESET + "%2s"+ VERDE +"    │ ", cartaJQK);
            linhas[4] += "|         | ";
            linhas[5] += String.format("│       " + VERMELHO + "%s " + VERDE +  "│ ", carta.getNaipe());
            linhas[6] +=VERDE + "└─────────┘ ";
        }
        return Arrays.asList(linhas);
    }

    public List<String> exibirCartasDealer(ArrayList<Carta> cartas, boolean estourou) {
        String[] linhas = new String[] {"", "", "", "", "", "", ""};
        String cartaJQK;
        String naipe;
        for (Carta carta : cartas) {
            if (!estourou) {
                cartaJQK = "X";
                naipe = "X";
            } else {
                cartaJQK = String.valueOf(carta.getValor());
                naipe = carta.getNaipe();

                if (carta.getValor() == 1) {
                    cartaJQK = "A";
                } else if (carta.getValor() == 11) {
                    cartaJQK = "J";
                } else if (carta.getValor() == 12) {
                    cartaJQK = "Q";
                } else if (carta.getValor() == 13) {
                    cartaJQK = "K";
                }
            }

            linhas[0] += VERMELHO + "┌─────────┐ ";
            linhas[1] += String.format("| " + VERMELHO+ "%s" + VERMELHO + "       | ", naipe);
            linhas[2] += "|         | ";
            linhas[3] += String.format("│   " + RESET + "%2s"+ VERMELHO +"    │ ", cartaJQK);
            linhas[4] += "|         | ";
            linhas[5] += String.format("│       " + VERMELHO + "%s " + VERMELHO +  "│ ", naipe);
            linhas[6] +=VERMELHO + "└─────────┘ " + VERDE;
        }
        return Arrays.asList(linhas);
    }

    public void exibirHabilidades() {
        limparTela();
        System.out.print(VERDE +
                "  ┌───────────────────────────────────────────────────┐\n" +
                "  │==============[ LISTA DE HABILIDADES ]=============│\n" +
                "  ├───────────────────────────────────────────────────┤\n" +
                "  │                                                   │\n" +
                "  │    [ 1 ] > Desfazer última jogada do Jogador      │\n" +
                "  │    [ 2 ] > Recuperar vida do Jogador              │\n" +
                "  │    [ 3 ] > Causa dano ao Dealer                   │\n" +
                "  │    [ 4 ] > Forçar o Dealer a comprar uma carta    │\n" +
                "  │    [ 5 ] > Sair                                   │\n" +
                "  │                                                   │\n" +
                "  └───────────────────────────────────────────────────┘\n" +
                "  Selecione uma opção > " + RESET);
    }

    public String exibirPreencherNome(Jogador jogador) {
        limparTela();
        System.out.println("  ┌───────────────────────────────────────────────────┐");
        System.out.print("    > Digite o nome do jogador: ");
        String nome = input.nextLine().trim();
        System.out.println("  └───────────────────────────────────────────────────┘");
        return nome;
    }

    public void exibirMensagem(String mensagem) {
        limparTela();
        System.out.println(VERDE + "  ┌───────────────────────────────────────────────────┐");
        System.out.println("     " + AMARELO + mensagem + RESET);
        System.out.println(VERDE + "  └───────────────────────────────────────────────────┘" + RESET);
        System.out.println();
        System.out.println("  > Pressione ENTER para prosseguir..." + RESET);
        input.nextLine();
    }

    public void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.print("\n".repeat(50));

    }
}