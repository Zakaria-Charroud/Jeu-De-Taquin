package taquin;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

abstract public class Compteur implements ActionListener {

    private static Timer timer;

    private static int secondes;
    private static int minutes;

    private static int tempsEcouleSecondes;
    private static int tempsEcouleMinutes;

    private static int a = 0;
    private static Clip clip = null;

    private Compteur() {
    }

    public static void compter(final int lastSecond, final int lastMinute) {
        secondes = lastSecond;
        minutes = lastMinute;
        ActionListener compterTemps = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean rep = verifier();
                if (rep) {
                    pauseTime();
                    return;
                }

                if (secondes >= 59) {
                    secondes = 0;
                    minutes++;
                    if (minutes >= 59) {
                        minutes = 0;
                        secondes = 0;
                    }
                } else {
                    secondes++;
                }
                if (secondes < 10 && minutes < 10) {
                    Panneau.compteur.setText("0" + minutes + ":0" + secondes);
                } else if (secondes < 10 && minutes >= 10) {
                    Panneau.compteur.setText(minutes + ":0" + secondes);
                } else if (secondes >= 10 && minutes < 10) {
                    Panneau.compteur.setText("0" + minutes + ":" + secondes);
                } else {
                    Panneau.compteur.setText(minutes + ":" + secondes);
                }

            }
        };
        timer = new Timer(1000, compterTemps);
        timer.start();

    }

    public static void pauseTime() {
        if (timer.isRunning()) {
            Compteur.timer.stop();
            tempsEcouleSecondes = secondes;
            tempsEcouleMinutes = minutes;
        }
    }

    public static int getSecondes() {
        return secondes;
    }

    public static int getMinutes() {
        return minutes;
    }

    public static int getTempEcouleSecondes() {
        return tempsEcouleSecondes;
    }

    public static int getTempEcouleMinutes() {
        return tempsEcouleMinutes;
    }

    public static boolean verifier() {
        if (a < 1) {
            a += 1;
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./Ressources/perdu.wav"));
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
            } catch (Exception ex) {
                System.out.println("Error with playing sound.");
            }
        }

        int i = 0;
        if (Forme.temp.m3.isSelected()) {
            if (Compteur.getMinutes() == 7 && Compteur.getSecondes() == 0) {
                i = 1;
            }
        } else if (Forme.temp.m4.isSelected()) {
            if (Compteur.getMinutes() == 10 && Compteur.getSecondes() == 0) {
                i = 2;
            }
        } else if (Forme.temp.m5.isSelected()) {
            if (Compteur.getMinutes() == 15 && Compteur.getSecondes() == 0) {
                i = 3;
            }
        } else if (Forme.temp.mAutreType.isSelected()) {
            if (Compteur.getMinutes() == 20 && Compteur.getSecondes() == 0) {

                i = 4;
            }
        }
        if (i > 0) {
            Forme.temp.setDemarrer(false);
            Forme.temp.setArreter(true);
            Forme.temp.diviserImage();
            Forme.temp.initialiser();
            Compteur.pauseTime();
            clip.start();
            ImageIcon icon = new ImageIcon("Ressources/Icons/gameover.png");
            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JOptionPane pane = new JOptionPane("Vous avez perdu :(", JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, scaledIcon, new Object[]{});
            JDialog dialog = pane.createDialog(Forme.temp, "Perdu :(");
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
        return false;

    }
}
