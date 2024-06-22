public class Navire {
    private int taille;
    private int posX;
    private int posY;
    private boolean estCoule;

    public Navire(int taille) {
        this.taille = taille;
        this.posX = -1; // Valeur par défaut indiquant que le navire n'est pas encore placé
        this.posY = -1;
        this.estCoule = false;
    }

    public int getTaille() {
        return taille;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean estCoule() {
        return estCoule;
    }

    public void setEstCoule(boolean estCoule) {
        this.estCoule = estCoule;
    }

    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
    }
}
