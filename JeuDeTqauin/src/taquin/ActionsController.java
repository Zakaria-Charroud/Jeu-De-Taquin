package taquin;

import java.awt.BorderLayout;
import java.awt.Image;
import java.sql.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ActionsController implements ActionListener, WindowListener {

    private Object o;

    public ActionsController(Object o) {
        this.o = o;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (o.getClass() == Forme.class) {
            Forme forme = (Forme) o;

            if (e.getSource() == forme.mOuvrir) {
                if (forme.isDemarrer() && !forme.isArreter()) {
                    Compteur.pauseTime();
                    Panneau.compteur.setText("00:00");
                    new FileChooser(forme);
                    forme.mLancer.setEnabled(true);
                    forme.mRejouer.setEnabled(false);
                    forme.mStart.setEnabled(false);
                    forme.mRejouer.setEnabled(false);
                    forme.mStart.setEnabled(false);
                    forme.mStop.setEnabled(false);
                }else{
                    new FileChooser(forme);
                }
            } else if (e.getSource() == forme.mLancer) {
                if (forme.mRejouer.isEnabled()) {
                    Compteur.pauseTime();
                    Panneau.compteur.setText("00:00");
                }
                forme.identifier();
                forme.go();
                forme.mRejouer.setEnabled(true);
                forme.mStart.setEnabled(false);
                forme.mStop.setEnabled(true);

            } else if (e.getSource() == forme.mRejouer) {
                Compteur.pauseTime();
                forme.identifier();
                forme.go();
                forme.mStart.setEnabled(false);
                forme.mStop.setEnabled(true);
            } else if (e.getSource() == forme.mStart) {
                Compteur.compter(Compteur.getTempEcouleSecondes(), Compteur.getTempEcouleMinutes());
                forme.setArreter(false);
                forme.mStart.setEnabled(false);
                forme.mStop.setEnabled(true);
            } else if (e.getSource() == forme.mStop) {
                Compteur.pauseTime();
                forme.setArreter(true);
                forme.mStart.setEnabled(true);
                forme.mStop.setEnabled(false);
            } else if (e.getSource() == forme.mScore) {

                String sql = "SELECT id, username, score , "
                        + "RANK() OVER (ORDER BY score DESC) AS rank "
                        + "FROM users "
                        + "ORDER BY score DESC";
                try (PreparedStatement pstmt = ConnectionDB.getConnection().prepareStatement(sql)) {
                    ResultSet rs = pstmt.executeQuery();
                    JDialog dialog = new JDialog();
                    dialog.setTitle(" Rank ");
                    dialog.setModal(true);
                    String[] columnNames = {"Rank", "Username", "Score"};
                    int numberOfUsers = 0;
                    ResultSet rss = null;
                    try (Statement st = ConnectionDB.getConnection().createStatement()) {
                        String sq2 = "SELECT COUNT(*) AS count FROM users";
                        rss = st.executeQuery(sq2);
                        if (rss.next()) {
                            numberOfUsers = rss.getInt("count");
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                    rss.close();
                    Object[][] data = new Object[numberOfUsers][3];
                    int row = 0;
                    while (rs.next() && row < numberOfUsers) {
                        int rank = rs.getInt("rank");
                        System.out.println(rank);
                        String username = rs.getString("username");
                        int score = rs.getInt("score");
                        data[row] = new Object[]{rank, username, score};
                        row++;
                    }
                    JTable table = new JTable(data, columnNames);
                    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                    JScrollPane scrollPane = new JScrollPane(table);
                    dialog.add(scrollPane);
                    dialog.pack();
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Ranking Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == forme.mQuiter) {
                Toolkit.getDefaultToolkit().beep();
                int rep = JOptionPane.showConfirmDialog(forme, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (rep == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }//
            else if (e.getSource() == forme.mHowToUse) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(forme, "suiver les étapes suivants :\n"
                        + "             1- importer une image cliquant sur 'Importer une image' \n"
                        + "             2- Choisir le type du jeu (3x3, 4x4, 5x5 ou Autres).\n"
                        + "             3- Choisir la taille du carré (la taille des sous images 50px, 75px et 100px).\n"
                        + "             4- Cliquer sur 'lancer le jeu'\n",
                        "Comment jouer ?", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == forme.mInfosDetails) {
                JDialog dialog = new JDialog();
                dialog.setTitle("User Information");

                JPanel panel = new JPanel(new BorderLayout());

                JLabel label = new JLabel("<html><b>Account name:</b> " + forme.getNom() + "<br><b>Username:</b> " + forme.getUsername() + "<br><b>Password:</b> " + forme.getPassword() + "<br><b>Mail:</b> " + forme.getMail() + "<br><b>Score:</b> " + forme.getScore() + "</html>");
                panel.add(label, BorderLayout.CENTER);
                ImageIcon imageIcon = null;
                File imageFile = new File(forme.getImg());
                if (imageFile.exists()) {
                    imageIcon = new ImageIcon(imageFile.getPath());
                    imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                }
                if (imageIcon != null) {
                    JLabel imageLabel = new JLabel(imageIcon);
                    panel.add(imageLabel, BorderLayout.WEST);
                }
                dialog.add(panel);
                dialog.pack();
                dialog.setVisible(true);
                dialog.setLocationRelativeTo(null);

            } else if (e.getSource() == forme.m50) {
                forme.setTaille(50);
                forme.pan = new Panneau(forme.getImage(), forme.getNbCarreaux(), forme.getTaille(), forme.getCarreaux());
                forme.initialiser();
            }
            else if (e.getSource() == forme.m75) {
                forme.setTaille(75);
                forme.pan = new Panneau(forme.getImage(), forme.getNbCarreaux(), forme.getTaille(), forme.getCarreaux());
                forme.initialiser();
            }
            else if (e.getSource() == forme.m100) {
                forme.setTaille(100);
                forme.pan = new Panneau(forme.getImage(), forme.getNbCarreaux(), forme.getTaille(), forme.getCarreaux());
                forme.initialiser();
            }
            else if (e.getSource() == forme.m3) {
                if (forme.isDemarrer() && !forme.isArreter()) {
                    forme.setNbCarreaux(3);
                    Compteur.pauseTime();
                    Panneau.compteur.setText("00:00");
                    forme.diviserImage();
                    forme.initialiser();
                    forme.identifier();
                    forme.go();
                    forme.mStart.setEnabled(false);
                    forme.mStop.setEnabled(true);
                } else {
                    forme.setNbCarreaux(3);
                    forme.setDemarrer(false);
                    forme.diviserImage();
                    forme.initialiser();
                }
            }
            else if (e.getSource() == forme.m4) {
                if (forme.isDemarrer() && !forme.isArreter()) {
                    forme.setNbCarreaux(4);
                    Compteur.pauseTime();
                    Panneau.compteur.setText("00:00");
                    forme.diviserImage();
                    forme.initialiser();
                    forme.identifier();
                    forme.go();
                    forme.mStart.setEnabled(false);
                    forme.mStop.setEnabled(true);
                } else {
                    forme.setNbCarreaux(4);
                    forme.setDemarrer(false);
                    forme.diviserImage();
                    forme.initialiser();
                }
            }
            else if (e.getSource() == forme.m5) {
                if (forme.isDemarrer() && !forme.isArreter()) {
                    forme.setNbCarreaux(5);
                    Compteur.pauseTime();
                    Panneau.compteur.setText("00:00");
                    forme.diviserImage();
                    forme.initialiser();
                    forme.identifier();
                    forme.go();
                    forme.mStart.setEnabled(false);
                    forme.mStop.setEnabled(true);
                } else {
                    forme.setNbCarreaux(5);
                    forme.setDemarrer(false);
                    forme.diviserImage();
                    forme.initialiser();
                }
            }
            else if (e.getSource() == forme.mAutreType) {
                String nbcar = null;
                int valeur = 0, rep = -1;
                do {
                    try {
                        Toolkit.getDefaultToolkit().beep();
                        nbcar = JOptionPane.showInputDialog(forme, "entrer le nombre des cases (superieur a 5) :",
                                "Entrer", JOptionPane.PLAIN_MESSAGE);
                        if (nbcar != null) {
                            valeur = Integer.parseInt(nbcar);
                            if (valeur > 5) {
                                forme.setNbCarreaux(valeur);
                                break;
                            }
                            throw new Exception();
                        } else {
                            break;
                        }

                    } catch (Exception ex) {
                        rep = JOptionPane.showConfirmDialog(forme, "Vous n'avez pas entrer une valeur entier\n"
                                + "ou vous n'avez rien entrer.\n"
                                + "Cliquer sur OK pour entrer une autre valeur.",
                                "Erreur", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                    }

                } while (rep == JOptionPane.OK_OPTION || valeur < 5);
                if (forme.isDemarrer() && !forme.isArreter()) {
                    Compteur.pauseTime();
                    Panneau.compteur.setText("00:00");
                    forme.diviserImage();
                    forme.initialiser();
                    forme.identifier();
                    forme.go();
                    forme.mStart.setEnabled(false);
                    forme.mStop.setEnabled(true);
                } else {
                    forme.setDemarrer(false);
                    forme.diviserImage();
                    forme.initialiser();
                }
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Forme forme = (Forme) o;
        Toolkit.getDefaultToolkit().beep();
        int rep = JOptionPane.showConfirmDialog(forme, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (rep == JOptionPane.OK_OPTION) {
            System.exit(0);
        } else {
            forme.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
