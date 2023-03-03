package taquin;

public class Carreaux {

    private int imgX; 
    private int imgY; 
    private int imgX1;
    private int imgY1;
    private int origEmplacement;
    private int nvEmplacement; 

    
    public Carreaux(int x, int y, int x1, int y1, int orig) {
        this.imgX = x;
        this.imgY = y;
        this.imgX1 = x1;
        this.imgY1 = y1;
        this.origEmplacement = orig;
        this.nvEmplacement = orig;
    }

    
    public int getImgX() {
        return imgX;
    }

    
    public void setImgX(int imgX) {
        this.imgX = imgX;
    }

    
    public int getImgY() {
        return imgY;
    }

    
    public void setImgY(int imgY) {
        this.imgY = imgY;
    }

    
    public int getImgX1() {
        return imgX1;
    }

    
    public void setImgX1(int imgX1) {
        this.imgX1 = imgX1;
    }

    
    public int getImgY1() {
        return imgY1;
    }

    
    public void setImgY1(int imgY1) {
        this.imgY1 = imgY1;
    }

    
    public int getOrigEmplacement() {
        return origEmplacement;
    }

    
    public void setOrigEmplacement(int origEmplacement) {
        this.origEmplacement = origEmplacement;
    }

 
    public int getNvEmplacement() {
        return nvEmplacement;
    }

    
    public void setNvEmplacement(int nvEmplacement) {
        this.nvEmplacement = nvEmplacement;
    }
 
    
    public boolean isSonEmplacement() {
        if (this.origEmplacement == this.nvEmplacement) {
            return true;
        } else {
            return false;
        }
    }

}
