package taquin;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileChooser extends JFileChooser{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	public FileChooser(Forme forme) {
		super("..");
		
		this.setDialogTitle("Choisir une image");
		this.setMultiSelectionEnabled(false);		
		int rep=this.showOpenDialog(forme);
		if(rep!=JFileChooser.CANCEL_OPTION && this.getSelectedFile()!=null){
			forme.chemin=this.getSelectedFile().getAbsolutePath();
			if(forme.chemin!=null)
				try {
					forme.setImage(ImageIO.read(new File(forme.chemin)));
					forme.diviserImage();
					forme.initialiser();
					forme.setDemarrer(false);
					forme.mLancer.setEnabled(true);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(this,e1.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
				}
		}
	}
}
