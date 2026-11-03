package main.java.br.com.dbc.vemser.hellbet.utils;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;

public class Soundtrack {

    public static Clip tocarSoundtrack(String caminhoArquivo, float volume) {
        try {
            File arquivo = new File(caminhoArquivo);

            if (!arquivo.exists()) {
                System.out.println("Arquivo não encontrado: " + caminhoArquivo);
                return null;
            }

            try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(arquivo)) {
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);

                if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                    FloatControl controleVolume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    controleVolume.setValue(volume);
                }

                clip.addLineListener(evento -> {
                    if (evento.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                });

                clip.loop(Clip.LOOP_CONTINUOUSLY);

                return clip;
            }

        } catch (Exception e) {
            System.out.println("Erro ao tocar trilha: " + e.getMessage());
            return null;
        }
    }

    public static Clip reproduzirEfeito(String caminhoArquivo, float volume) {
        try {
            File arquivo = new File(caminhoArquivo);

            if (!arquivo.exists()) {
                System.out.println("Efeito sonoro não encontrado: " + caminhoArquivo);
                return null;
            }

            try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(arquivo)) {
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);

                if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                    FloatControl controleVolume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    controleVolume.setValue(volume);
                }

                clip.addLineListener(evento -> {
                    if (evento.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                });

                clip.start();

                return clip;
            }

        } catch (Exception e) {
            System.out.println("Erro ao tocar efeito: " + e.getMessage());
            return null;
        }
    }
}
