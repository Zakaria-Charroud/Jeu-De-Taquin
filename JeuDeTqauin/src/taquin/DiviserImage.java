package taquin;

import java.awt.Image;


public class DiviserImage {
	private Image img;
	private int nbCarreaux;

	public DiviserImage(Image img,int nbCarreaux){
		this.img=img;
		this.nbCarreaux=nbCarreaux;
	}
	public Carreaux[][] diviser(){
		Carreaux[][] carr=new Carreaux[nbCarreaux][nbCarreaux];
		int wCarre,hCarre;
		if(img!=null){
			wCarre=img.getWidth(null)/nbCarreaux;
			hCarre=img.getHeight(null)/nbCarreaux;
			for(int i=0;i<nbCarreaux;i++)
				for(int j=0;j<nbCarreaux;j++)
					carr[i][j]=new Carreaux(i*wCarre, j*hCarre, (i+1)*wCarre, (j+1)*hCarre,j*nbCarreaux+i);
		}

		return carr;
	}
}