import java.util.Random;

public class PlateauDeJeu {
    private int taille;
    private char[][] grille;
    private Navire[] navires;

    public PlateauDeJeu(int taille, Navire[] navires) {
        this.taille = taille;
        this.grille = new char[taille][taille];
        this.navires = navires;
        initialiserGrille();
    }

    private void initialiserGrille() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grille[i][j] = ' ';
            }
        }
    }

    public void afficherGrille(boolean afficherNavires) {
        System.out.print("  ");
        for (int i = 0; i < taille; i++) {
            System.out.print((i + 1) + " ");
        }
        System.out.println();

        char rowLabel = 'A';
        for (int i = 0; i < taille; i++) {
            System.out.print(rowLabel + " ");
            for (int j = 0; j < taille; j++) {
                char symbol = grille[i][j];
                if (symbol == 'S' && !afficherNavires) {
                    System.out.print("  ");
                } else {
                    System.out.print(symbol + " ");
                }
            }
            System.out.println();
            rowLabel++;
        }
    }

    public boolean estPlacementValide(Navire navire, int x, int y) {
        int tailleNavire = navire.getTaille();
        if (x < 0 || x >= taille || y < 0 || y + tailleNavire > taille) {
            return false;
        }

        for (int i = 0; i < tailleNavire; i++) {
            if (grille[x][y + i] != ' ') {
                return false;
            }
        }

        for (int i = 0; i < tailleNavire; i++) {
            grille[x][y + i] = 'S';
        }

        return true;
    }

    public boolean tirer(int x, int y) {
        if (grille[x][y] == 'S') {
            grille[x][y] = 'C';
            return true;
        } else if (grille[x][y] == ' ') {
            grille[x][y] = 'M';
        }
        return false;
    }

    public boolean tousCoules() {
        for (Navire navire : navires) {
            if (!navire.estCoule()) {
                return false;
            }
        }
        return true;
    }

    public void placerNaviresAleatoirement() {
        Random random = new Random();
        for (Navire navire : navires) {
            boolean place = false;
            while (!place) {
                int x = random.nextInt(taille);
                int y = random.nextInt(taille - navire.getTaille() + 1);
                if (estPlacementValide(navire, x, y)) {
                    navire.setPosition(x, y);
                    place = true;
                }
            }
        }
    }

    public Navire[] getNavires() {
        return navires;
    }
}
