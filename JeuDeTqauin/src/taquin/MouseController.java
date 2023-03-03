package taquin;

import java.awt.Image;
import java.sql.PreparedStatement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MouseController implements MouseMotionListener, MouseListener {

    private Forme forme;

    public MouseController(Forme forme) {
        this.forme = forme;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (forme.isDemarrer() && !forme.isArreter()) {
                int xMouse = e.getX(), yMouse = e.getY(), xBlanc, yBlanc, xCarre, yCarre, t;
                Carreaux[][] carreaux = forme.getCarreaux();
                if (xMouse >= (forme.getTaille() + 4) && xMouse <= (forme.getTaille() * forme.getNbCarreaux() + forme.getTaille() + 4)) {
                    if (yMouse >= (forme.getTaille() + 50) && yMouse <= (forme.getTaille() * forme.getNbCarreaux() + forme.getTaille()) + 50) {
                        //calculer le num�ro de la colonne dans laquelle le carr� selectionn� appartient
                        xCarre = (xMouse - forme.getTaille() - 4) / forme.getTaille();
                        //calculer le num�ro de la ligne dans laquelle le carr� selectionn� appartient
                        yCarre = (yMouse - forme.getTaille() - 50) / forme.getTaille();

                        t = carreaux[forme.getNbCarreaux() - 1][forme.getNbCarreaux() - 1].getNvEmplacement();
                        //calculer le num�ro de la colonne dans laquelle le carr� blanc appartient
                        xBlanc = t % (forme.getNbCarreaux());
                        //calculer le num�ro de la ligne dans laquelle le carr� blanc appartient
                        yBlanc = t / (forme.getNbCarreaux());

                        if (xBlanc == xCarre || yBlanc == yCarre) {
                            int x = 0, y = 0;

                            //xBlanc < xCarre
                            if (xBlanc < xCarre) {
                                while (!(xBlanc == xCarre)) {
                                    for (int i = 0; i < forme.getNbCarreaux(); i++) {
                                        for (int j = 0; j < forme.getNbCarreaux(); j++) {
                                            if (carreaux[i][j].getNvEmplacement() == yBlanc * forme.getNbCarreaux() + xBlanc + 1) {
                                                x = i;
                                                y = j;
                                            }
                                        }
                                    }
                                    carreaux[forme.getNbCarreaux() - 1][forme.getNbCarreaux() - 1].setNvEmplacement(carreaux[x][y].getNvEmplacement());
                                    carreaux[x][y].setNvEmplacement(t);
                                    t++;
                                    xBlanc++;
                                }
                            }//

                            //xBlanc > xCarre
                            if (xBlanc > xCarre) {
                                while (!(xBlanc == xCarre)) {
                                    for (int i = 0; i < forme.getNbCarreaux(); i++) {
                                        for (int j = 0; j < forme.getNbCarreaux(); j++) {
                                            if (carreaux[i][j].getNvEmplacement() == yBlanc * forme.getNbCarreaux() + xBlanc - 1) {
                                                x = i;
                                                y = j;
                                            }
                                        }
                                    }
                                    carreaux[forme.getNbCarreaux() - 1][forme.getNbCarreaux() - 1].setNvEmplacement(carreaux[x][y].getNvEmplacement());
                                    carreaux[x][y].setNvEmplacement(t);
                                    t--;
                                    xBlanc--;
                                }
                            }//

                            //yBlanc < yCarre
                            if (yBlanc < yCarre) {
                                while (!(yBlanc == yCarre)) {
                                    for (int i = 0; i < forme.getNbCarreaux(); i++) {
                                        for (int j = 0; j < forme.getNbCarreaux(); j++) {
                                            if (carreaux[i][j].getNvEmplacement() == yBlanc * forme.getNbCarreaux() + xBlanc + forme.getNbCarreaux()) {
                                                x = i;
                                                y = j;
                                            }
                                        }
                                    }
                                    carreaux[forme.getNbCarreaux() - 1][forme.getNbCarreaux() - 1].setNvEmplacement(carreaux[x][y].getNvEmplacement());
                                    carreaux[x][y].setNvEmplacement(t);
                                    t += forme.getNbCarreaux();
                                    yBlanc++;
                                }
                            }//

                            //yBlanc > yCarre
                            if (yBlanc > yCarre) {
                                while (!(yBlanc == yCarre)) {
                                    for (int i = 0; i < forme.getNbCarreaux(); i++) {
                                        for (int j = 0; j < forme.getNbCarreaux(); j++) {
                                            if (carreaux[i][j].getNvEmplacement() == yBlanc * forme.getNbCarreaux() + xBlanc - forme.getNbCarreaux()) {
                                                x = i;
                                                y = j;
                                            }
                                        }
                                    }
                                    carreaux[forme.getNbCarreaux() - 1][forme.getNbCarreaux() - 1].setNvEmplacement(carreaux[x][y].getNvEmplacement());
                                    carreaux[x][y].setNvEmplacement(t);
                                    t -= forme.getNbCarreaux();
                                    yBlanc--;
                                }
                            }//

                        }//fin de if(xBlanc==xCarre || ...)

                        //redissiner le panel
                        forme.pan.repaint();

                        //gagner
                        boolean gagner = true;
                        for (int i = 0; i < forme.getNbCarreaux(); i++) {
                            for (int j = 0; j < forme.getNbCarreaux(); j++) {
                                if (!carreaux[i][j].isSonEmplacement()) {
                                    gagner = false;
                                }
                            }
                        }
                        if (gagner == true) {
                            Compteur.pauseTime();
                            forme.mLancer.setEnabled(false);

                            String sql = "UPDATE users SET score= ? WHERE id =?";
                            int s = 0;
                            int smax = 0;
                            try (PreparedStatement pstmt = ConnectionDB.getConnection().prepareStatement(sql)) {
                                int max;
                                if ("3x3".equals(forme.getTyype())) {
                                    max = 420;
                                    s = (int) ((max - ((Compteur.getTempEcouleSecondes()) + 60 * (Compteur.getTempEcouleMinutes()))));
                                    smax = (int) ((max - ((Compteur.getTempEcouleSecondes()) + 60 * (Compteur.getTempEcouleMinutes()))) + forme.getScore());
                                    forme.setScore(smax);
                                } else if ("4x4".equals(forme.getTyype())) {
                                    max = 1200;
                                    s = (int) ((max - (2 * (Compteur.getTempEcouleSecondes()) + 120 * (Compteur.getTempEcouleMinutes()))));
                                    smax = (int) ((max - (2 * (Compteur.getTempEcouleSecondes()) + 120 * (Compteur.getTempEcouleMinutes()))) + forme.getScore());
                                    forme.setScore(smax);
                                } else if ("5x5".equals(forme.getTyype()) || forme.getTyype() == "Autre ...") {
                                    max = 3600;
                                    s = (int) ((max - (4 * (Compteur.getTempEcouleSecondes()) + 240 * (Compteur.getTempEcouleMinutes()))));
                                    smax = (int) ((max - (4 * (Compteur.getTempEcouleSecondes()) + 240 * (Compteur.getTempEcouleMinutes()))) + forme.getScore());
                                    forme.setScore(smax);
                                } else if ("Autre ...".equals(forme.getTyype())) {
                                    max = 9600;
                                    s = (int) ((max - (8 * (Compteur.getTempEcouleSecondes()) + 480 * (Compteur.getTempEcouleMinutes()))));
                                    smax = (int) ((max - (8 * (Compteur.getTempEcouleSecondes()) + 480 * (Compteur.getTempEcouleMinutes()))) + forme.getScore());
                                    forme.setScore(smax);
                                }
                                // Replace the placeholders with actual values
                                pstmt.setInt(1, smax);
                                pstmt.setInt(2, forme.getId());

                                // Execute the update statement
                                int rowsUpdated = pstmt.executeUpdate();
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }
                            Clip clip = null;
                            try {
                                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./Ressources/Bravo.wav"));
                                clip = AudioSystem.getClip();
                                clip.open(audioInputStream);
                            } catch (Exception ex) {
                                System.out.println("Error with playing sound.");
                            }
                            clip.start();

                            ImageIcon icon = new ImageIcon("Ressources/Icons/winner.png");
                            Image image = icon.getImage();
                            Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                            ImageIcon scaledIcon = new ImageIcon(scaledImage);
                            int rep = JOptionPane.showConfirmDialog(null, "Gratulation!!!"
                                    + "\nYour score : " + s
                                    + "\nDo you want to play again ?", "Win!!", JOptionPane.YES_NO_OPTION,
                                    JOptionPane.INFORMATION_MESSAGE, scaledIcon);

                            if (rep == JOptionPane.OK_OPTION) {
                                forme.go();
                            }
                            if (rep == JOptionPane.NO_OPTION) {
                                forme.diviserImage();
                                forme.initialiser();
                                forme.setDemarrer(false);
                                Panneau.compteur.setText("00:00");
                                forme.mStart.setEnabled(false);
                                forme.mStop.setEnabled(false);
                                forme.mLancer.setEnabled(false);
                                forme.mRejouer.setEnabled(true);
                            }
                        }
                    }//fin de if(yMouse>..)
                }//fin de if(xMouse>..)
            }//fin de if(demarrer)
        }
    }//mousePressed

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
