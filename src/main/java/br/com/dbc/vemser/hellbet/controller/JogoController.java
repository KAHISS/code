package main.java.br.com.dbc.vemser.hellbet.controller;

import main.java.br.com.dbc.vemser.hellbet.model.*;
import main.java.br.com.dbc.vemser.hellbet.utils.Soundtrack;
import main.java.br.com.dbc.vemser.hellbet.view.JogoView;

import javax.sound.sampled.Clip;
import java.util.ArrayList;
import java.util.Random;

public class JogoController {
    private Jogador jogador;
    private Dealer dealer;
    private Baralho baralho;
    private JogoView jogoView;
    private static final Random RANDOM = new Random();
    private Clip soundtrack;

    public JogoController() {
        jogador = new Jogador("Jogador", 3, new ArrayList<Carta>(), 0);
        dealer = new Dealer(3, new ArrayList<Carta>());
        baralho = new Baralho();
        jogoView = new JogoView();
        soundtrack = Soundtrack.tocarSoundtrack("assets/sound/Blackjack.wav", -22.5f);
    }

    public void menu(){
        boolean sairDoJogo = false;
        while(!sairDoJogo){
            jogoView.exibirMenu();
            int opcaoJogador;

            try {
                opcaoJogador = jogoView.lerOpcaoJogador();
            } catch (Exception e) {
                jogoView.exibirMensagem("Entrada inválida! digite apenas números");
                continue;
            }

            switch (opcaoJogador){
                case 1:
                    Clip gameover = comecarJogo();

                    if (gameover != null) {
                        gameover.stop();
                        Soundtrack.tocarSoundtrack("assets/sound/Blackjack.wav", -22.5f);
                    }
                    break;
                case 2:
                    configurarJogador();
                    break;
                case 3:
                    jogoView.exibirMensagem("Saindo do Jogo...");
                    sairDoJogo = true;
                    break;
                default:
                    jogoView.exibirMensagem("Opção Incorreta...");
                    break;
            }
        }
    }

    public void prepararRodada(){
        Soundtrack.reproduzirEfeito("assets/sound/embaralhamento" + RANDOM.nextInt(1, 2) + ".wav", -0.0f);
        jogoView.exibirMensagem("Embaralhando...");

        baralho.resetar();
        baralho.embaralhar();

        jogador.setParou(false);
        dealer.setParou(false);

        ArrayList<Carta> cartasJogador = baralho.comprarCarta(2);
        jogador.setMao(cartasJogador);

        ArrayList<Carta> cartasDealer = baralho.comprarCarta(2);
        dealer.setMao(cartasDealer);
    }

    public void prepararJogo(){
        jogador.setVida(3);
        jogador.setFicha(0);

        dealer.setVida(3);

        prepararRodada();
    }

    public Clip comecarJogo() {
        prepararJogo();

        while (jogador.getVida() > 0 && dealer.getVida() > 0) {
            executarRodada();

            if (jogador.getVida() > 0 && dealer.getVida() > 0) {
                prepararRodada();
            }
        }

        if(jogador.getVida() > dealer.getVida()){
            Soundtrack.reproduzirEfeito("assets/sound/ganhar.wav", -0.0f);
            jogoView.exibirMensagem(jogador.getNome() + " venceu o jogo!");
        }else if(jogador.getVida() < dealer.getVida()){
            Soundtrack.reproduzirEfeito("assets/sound/risadaFinal.wav", -0.0f);
            Clip gameOver = Soundtrack.reproduzirEfeito("assets/sound/gameOver.wav", -0.0f);
            soundtrack.stop();
            jogoView.exibirMensagem("Dealer venceu o jogo!");
            return gameOver;
        }else{
            jogoView.exibirMensagem("Empatou...");
        }

        return null;
    }

    public boolean processarTurnoDealer() {
        jogoView.exibirMensagem("Vez do Dealer...");

        if(dealer.escolherComprar()){

            Carta cartaComprada = baralho.comprarCarta();
            dealer.adicionarAMao(cartaComprada);

            Soundtrack.reproduzirEfeito("assets/sound/pegarCarta" + RANDOM.nextInt(1, 2) + ".wav", -0.0f);
            jogoView.exibirMensagem("Dealer comprou");
            jogoView.exibirResultado(jogador, dealer, false);

            if(dealer.validarEstouro()){
                dealer.perderVida();
                Soundtrack.reproduzirEfeito("assets/sound/ganhaFicha.wav", -0.0f);
                jogador.adicionarFicha();

                Soundtrack.reproduzirEfeito("assets/sound/pegarCarta" + RANDOM.nextInt(1, 2) + ".wav", -0.0f);
                jogoView.exibirMensagem("Dealer Estourou!");
                jogoView.exibirResultado(jogador, dealer, true);

                return false;
            }
        }else{
            dealer.setParou(true);

            jogoView.exibirMensagem("Dealer parou");
            jogoView.exibirResultado(jogador, dealer, false);

            return true;
        }
        return true;
    }

    public boolean processarTurnoJogador() {
        jogoView.exibirMensagem("Vez de "+ jogador.getNome() + "...");
        boolean escolhaValida = false;

        while(!escolhaValida) {
            jogoView.exibirJogo(jogador, dealer);
            int opcaoJogador;

            try {
                opcaoJogador = jogoView.lerOpcaoJogador();
            } catch (Exception e) {
                jogoView.exibirMensagem("Entrada inválida! digite apenas números");
                continue;
            }

            switch (opcaoJogador) {
                case 1:
                    Carta cartaComprada = baralho.comprarCarta();
                    jogador.adicionarAMao(cartaComprada);

                    if (jogador.validarEstouro()) {
                        jogador.perderVida();
                        Soundtrack.reproduzirEfeito("assets/sound/risadaDealer.wav", -0.0f);
                        jogoView.exibirMensagem( jogador.getNome() + " estourou!");
                        jogoView.exibirResultado(jogador, dealer, true);

                        return false;
                    }else{
                        Soundtrack.reproduzirEfeito("assets/sound/pegarCarta" + RANDOM.nextInt(1, 2) + ".wav", -0.0f);
                        jogoView.exibirMensagem(jogador.getNome() + " comprou uma carta");
                        jogoView.exibirResultado(jogador, dealer, false);
                    }

                    escolhaValida = true;
                    break;
                case 2:
                    jogador.setParou(true);
                    escolhaValida = true;
                    break;
                case 3:
                    if (!jogador.temFicha()) {
                        jogoView.exibirMensagem(jogador.getNome() + " não tem fichas...");
                        break;
                    }

                    boolean escolheuHabilidadeValida = usarHabilidades();

                    if (!escolheuHabilidadeValida) {
                        jogoView.exibirJogo(jogador, dealer);
                        break;
                    }

                    if (!escolheuHabilidadeValida) {
                        break;
                    }

                    if (jogador.validarEstouro()) {
                        jogador.perderVida();

                        Soundtrack.reproduzirEfeito("assets/sound/risadaDealer.wav", -0.0f);
                        jogoView.exibirMensagem(jogador.getNome() + " Estourou!");
                        jogoView.exibirResultado(jogador, dealer, true);

                        return false;
                    }

                    if (dealer.validarEstouro()) {
                        Soundtrack.reproduzirEfeito("assets/sound/ganhaFicha.wav", -0.0f);
                        jogador.adicionarFicha();
                        dealer.perderVida();

                        jogoView.exibirMensagem("Dealer Estourou!");
                        jogoView.exibirResultado(jogador, dealer, true);

                        return false;
                    }

                    if (dealer.getVida() <= 0) {
                        return false;
                    }
                    escolhaValida = true;
                    break;
                default:

                    jogoView.exibirMensagem("Opção Invalida");
                    break;

            }
        }
        return true;
    }

    private void executarRodada(){
        boolean rodadaEmAndamento = true;

        jogador.setParou(false);
        dealer.setParou(false);

        while(rodadaEmAndamento && jogador.getVida() > 0 && dealer.getVida() > 0){

            if(!jogador.isParou()) {
                rodadaEmAndamento = processarTurnoJogador();
            }

            if(!rodadaEmAndamento){
                break;
            }

            if(!dealer.isParou()){
                rodadaEmAndamento = processarTurnoDealer();
            }

            if(!rodadaEmAndamento) {
                break;
            };

            if(jogador.isParou() && dealer.isParou()){
                avaliarFimDeRodada();
                break;
            }
        }
    }

    public boolean usarHabilidades(){

        while(true){
            jogoView.exibirHabilidades();
            int habilidadeEscolhida;

            try {
                habilidadeEscolhida = jogoView.lerOpcaoJogador();
            } catch (Exception e) {
                jogoView.exibirMensagem("Entrada inválida! digite apenas números");
                continue;
            }

            switch(habilidadeEscolhida){
                case 1:
                    if(jogador.isMaoVazia()) {
                        jogoView.exibirMensagem(jogador.getNome() +" não tem a ultima jogada para reverter...");
                        break;
                    }

                    Soundtrack.reproduzirEfeito("assets/sound/usarHabilidade.wav", -0.0f);
                    jogoView.exibirMensagem(jogador.getNome() +" desfez a ultima jogada...");
                    jogador.ativarDesfazerUltimaJogada();
                    jogador.gastarFicha();
                    return true;
                case 2:
                    if(jogador.isVidaMaxima()){
                        jogoView.exibirMensagem(jogador.getNome() +" já está com a vida cheia...");
                        break;
                    }

                    Soundtrack.reproduzirEfeito("assets/sound/ganharVida.wav", -0.0f);
                    jogoView.exibirMensagem(jogador.getNome() +" recuperou um ponto de vida!");
                    jogador.ativarRecuperarVida();
                    jogador.gastarFicha();
                    return true;
                case 3:

                    Soundtrack.reproduzirEfeito("assets/sound/dano" + RANDOM.nextInt(1, 2) + ".wav", -0.0f);
                    jogoView.exibirMensagem(jogador.getNome() +" causou 1 de dano ao Dealer!");
                    dealer.perderVida();
                    jogador.gastarFicha();
                    return true;
                case 4:
                    dealer.ativarForcarCompra(baralho.comprarCarta());

                    Soundtrack.reproduzirEfeito("assets/sound/pegarCarta" + RANDOM.nextInt(1, 2) + ".wav", -0.0f);
                    jogoView.exibirMensagem(jogador.getNome() +" obrigou o dealer a comprar!");
                    jogoView.exibirResultado(jogador, dealer, false);

                    jogador.gastarFicha();
                    return true;
                case 5:
                    jogoView.exibirMensagem("Saindo da escolha de Habilidades...");
                    return false;
                default:
                    jogoView.exibirMensagem("Opcao invalida nas habilidades. Tente novamente");
                    break;


            }
        }
    }

    public void avaliarFimDeRodada() {
        int pontosJogador = jogador.contarPontosNaMao();
        int pontosDealer = dealer.contarPontosNaMao();

        if (pontosJogador > pontosDealer) {
            Soundtrack.reproduzirEfeito("assets/sound/ganhaFicha.wav", -0.0f);
            jogador.adicionarFicha();
            dealer.perderVida();

            Soundtrack.reproduzirEfeito("assets/sound/ganhar.wav", -0.0f);
            jogoView.exibirMensagem(jogador.getNome() + " venceu!");
            jogoView.exibirResultado(jogador, dealer, true);
        } else if (pontosJogador < pontosDealer) {
            jogador.perderVida();

            Soundtrack.reproduzirEfeito("assets/sound/risadaDealer.wav", -0.0f);
            jogoView.exibirMensagem("Dealer venceu!");
            jogoView.exibirResultado(jogador, dealer, true);
        } else {
            jogoView.exibirMensagem("Empate!");
            jogoView.exibirResultado(jogador, dealer, true);
        }
    }


    public void configurarJogador() {
        String nomeJogador = jogoView.exibirPreencherNome(jogador);
        String nomeJogadorTratado = nomeJogador.trim();
        if (nomeJogadorTratado.isEmpty()) {
            jogador.setNome("Jogador");
        } else {
            jogador.setNome(nomeJogadorTratado);
        }
        Soundtrack.reproduzirEfeito("assets/sound/trocarNome.wav", -0.0f);
        System.out.println("Nome Atualizado: " + jogador.getNome());
    }
}
