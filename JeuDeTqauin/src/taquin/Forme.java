package taquin;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public final class Forme extends JFrame {

    private static final long serialVersionUID = 1L;
    private int id;
    private String nomm, username, password, mail, tyype;
    private String image;
    private int scoore;
    private JMenuBar principale;
    private JMenu mJeu, mHelp, mType, mTaille, mInfos;
    public JMenuItem mQuiter, mOuvrir, mLancer, mRejouer, mHowToUse, mStop, mStart, mScore, mInfosDetails, mLancerPauser;
    ;
    public JRadioButtonMenuItem mWindows, mNimbus, mWindowsClassic, mMetal, mCDEMotif, mSystem, m3, m4, m5, mAutreType, m50, m75, m100;
    ;

    String chemin = null;

    JPanel pan;

    private int taille = 75, nbCarreaux = 3;

    private boolean demarrer = false;
    private boolean arreter = false;

    private Carreaux[][] carreaux;

    private Image img = null;

    public static Forme temp;

    static String type = null;

    public Forme() {
        this.setTitle("Jeu du Taquin");
        this.setSize((getNbCarreaux() + 2) * getTaille() + 4, (getNbCarreaux() + 2) * getTaille() + 50);
        this.setLocationRelativeTo(null);

        temp = this;

        pan = new Panneau(null, getNbCarreaux(), getTaille(), carreaux);
        this.setContentPane(pan);

        initialiserComponents();

        createMenu();

        try {

            this.setIconImage(Toolkit.getDefaultToolkit().getImage("Ressources/Images/puzzle.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }//

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        //actualiser toutes les composantes de la fen�tre
        SwingUtilities.updateComponentTreeUI(this);

        this.addMouseMotionListener(new MouseController(this));//

        this.addMouseListener(new MouseController(this));

        this.addWindowListener(new ActionsController(this));
    }

    public void initialiserComponents() {
        principale = new JMenuBar();
            mJeu = new JMenu("Jeu du Taquin");
            mOuvrir = new JMenuItem("Importer une image");
            mLancer = new JMenuItem("Nouvelle partie");
            mRejouer = new JMenuItem("Rejouer");
            mStop = new JMenuItem("Suspendre le jeu");
            mStart = new JMenuItem("Reprendre le jeu");
            mScore = new JMenuItem("Score");
            mType = new JMenu("Type de jeu");
                m3 = new JRadioButtonMenuItem("3x3");
                m4 = new JRadioButtonMenuItem("4x4");
                m5 = new JRadioButtonMenuItem("5x5");
                mAutreType = new JRadioButtonMenuItem("Autre ...");
            mTaille = new JMenu("Taille de carreau");
                m50 = new JRadioButtonMenuItem("Taille 50px");
                m75 = new JRadioButtonMenuItem("Taille 75px");
                m100 = new JRadioButtonMenuItem("Taille 100px");
            mQuiter = new JMenuItem("Quitter");
            mInfos = new JMenu("informations");
                mInfosDetails = new JMenuItem("Profile");
            mHelp = new JMenu("Aide");
                mHowToUse = new JMenuItem("Comment jouer ?");
    }

    public void createMenu() {

        this.setJMenuBar(principale);
            principale.add(mJeu);
            
                mJeu.add(mOuvrir);
                    mOuvrir.setIcon(new ImageIcon("Ressources/Icons/import.png"));
                    mOuvrir.addActionListener(new ActionsController(this));
                mJeu.addSeparator();
                
                mJeu.add(mLancer);
                    mLancer.setEnabled(false);
                    mLancer.setIcon(new ImageIcon("Ressources/Icons/go.png"));
                    mLancer.addActionListener(new ActionsController(this));
                    
                mJeu.add(mRejouer);
                    mRejouer.setEnabled(false);
                    mRejouer.setIcon(new ImageIcon("Ressources/Icons/repeat.png"));
                    mRejouer.addActionListener(new ActionsController(this));
                    
                mJeu.add(mStart);
                    mStart.setEnabled(false);
                    mStart.setIcon(new ImageIcon("Ressources/Icons/start.png"));
                    mStart.addActionListener(new ActionsController(this));

                mJeu.add(mStop);
                    mStop.setEnabled(false);
                    mStop.setIcon(new ImageIcon("Ressources/Icons/pause.png"));
                    mStop.addActionListener(new ActionsController(this));
                mJeu.addSeparator();
                
                mJeu.add(mScore);
                    mScore.setIcon(new ImageIcon("Ressources/Icons/score.png"));
                    mScore.addActionListener(new ActionsController(this));
                
                mJeu.add(mType);
                    ButtonGroup g = new ButtonGroup();
                    g.add(m3);
                    g.add(m4);
                    g.add(m5);
                    g.add(mAutreType);
                        mType.add(m3);
                            m3.setSelected(true);
                            m3.addActionListener(new ActionsController(this));
                        mType.add(m4);
                            m4.addActionListener(new ActionsController(this));
                        mType.add(m5);
                            m5.addActionListener(new ActionsController(this));
                        mType.add(mAutreType);
                            mAutreType.addActionListener(new ActionsController(this));
                        
                mJeu.add(mTaille);
                    ButtonGroup g1 = new ButtonGroup();
                    g1.add(m50);
                    g1.add(m75);
                    g1.add(m100);
                        mTaille.add(m50);
                            m50.addActionListener(new ActionsController(this));
                        mTaille.add(m75);
                            m75.setSelected(true);
                            m75.addActionListener(new ActionsController(this));
                        mTaille.add(m100);
                            m100.addActionListener(new ActionsController(this));
                mJeu.addSeparator();
                
                mJeu.add(mQuiter);
                    mQuiter.setIcon(new ImageIcon("Ressources/Icons/fermer.png"));
                    mQuiter.addActionListener(new ActionsController(this));
                    
            principale.add(mInfos);
                mInfos.add(mInfosDetails);
                    mInfosDetails.setIcon(new ImageIcon("Ressources/Icons/how.png"));
                    mInfosDetails.addActionListener(new ActionsController(this));
                
            principale.add(mHelp);
                mHelp.add(mHowToUse);
                    mHowToUse.setIcon(new ImageIcon("Ressources/Icons/help.png"));
                    mHowToUse.addActionListener(new ActionsController(this));
                    
    }

    public void initialiser() {
        this.setContentPane(pan);
        this.setSize((getNbCarreaux() + 2) * getTaille() + 4, (getNbCarreaux() + 2) * getTaille() + 50);
        this.setLocationRelativeTo(null);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void diviserImage() {
        DiviserImage diviser = new DiviserImage(img, getNbCarreaux());
        carreaux = diviser.diviser();
        pan = new Panneau(img, getNbCarreaux(), getTaille(), carreaux);
    }
    
    public void go() {
        if (img != null) {
            setDemarrer(true);
            setArreter(false);
            ArrayList<Carreaux> arr = new ArrayList<>();
            for (Carreaux[] c : carreaux) {
                for (Carreaux b : c) {
                    arr.add(b);
                }
            }
            Random ran = new Random();
            int indice;
            for (int i = getNbCarreaux() * getNbCarreaux(); i > 0; i--) {
                indice = ran.nextInt(i);
                arr.get(indice).setNvEmplacement(i - 1);
                arr.remove(indice);
            }

            arr.clear();
            pan.repaint();
            Compteur.compter(0, 0);
        } else {
            JOptionPane.showMessageDialog(this, "Importer une image", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void identifier() {
        if (m3.isSelected()) {
            type = m3.getText();
            JOptionPane.showMessageDialog(this, "Vous avez 7 min", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (m4.isSelected()) {
            type = m4.getText();
            JOptionPane.showMessageDialog(this, "Vous avez 10 min", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (m5.isSelected()) {
            type = m5.getText();
            JOptionPane.showMessageDialog(this, "Vous avez 15 min", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            type = mAutreType.getText();
            JOptionPane.showMessageDialog(this, "Vous avez 20 min", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    Image getImage() {
        return img;
    }
    
    void setImage(Image img) {
        this.img = img;
    }

    String getImg() {
        return image;
    }

    void setImg(String img) {
        this.image = img;
    }
    
    Carreaux[][] getCarreaux() {
        return carreaux;
    }
    
    public int getTaille() {
        return taille;
    }
    
    public void setTaille(int taille) {
        this.taille = taille;
    }
    
    public int getNbCarreaux() {
        return nbCarreaux;
    }
    
    public void setNbCarreaux(int nbCarreaux) {
        this.nbCarreaux = nbCarreaux;
    }
    
    public boolean isDemarrer() {
        return demarrer;
    }
    
    public void setDemarrer(boolean demarrer) {
        this.demarrer = demarrer;
    }
    
    public boolean isArreter() {
        return arreter;
    }
    
    public void setArreter(boolean arreter) {
        this.arreter = arreter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNom() {
        return nomm;
    }

    public void setNom(String nomm) {
        this.nomm = nomm;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    //
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    //

    public int getScore() {
        return scoore;
    }

    public void setScore(int scoore) {
        this.scoore = scoore;
    }

    public String getTyype() {
        tyype = type;
        return tyype;
    }

    void setInfo(int id) {
        this.id = id;
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM users WHERE id=?";
        try {
            ps = ConnectionDB.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                this.nomm = rs.getString("name");
                this.username = rs.getString("username");
                this.password = rs.getString("password");
                this.mail = rs.getString("mail");
                this.scoore = rs.getInt("score");
                this.image = rs.getString("image");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }
}
