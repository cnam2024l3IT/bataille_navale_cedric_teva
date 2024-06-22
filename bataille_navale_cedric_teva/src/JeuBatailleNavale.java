import java.util.Random;
import java.util.Scanner;

public class JeuBatailleNavale {
    private PlateauDeJeu plateauJoueur;
    private PlateauDeJeu plateauOrdinateur;
    private Scanner scanner;

    public JeuBatailleNavale(int taille) {
        Navire[] naviresJoueur = {
                new Navire(2),
                new Navire(3)
        };
        Navire[] naviresOrdinateur = {
                new Navire(2),
                new Navire(3)
        };
        this.plateauJoueur = new PlateauDeJeu(taille, naviresJoueur);
        this.plateauOrdinateur = new PlateauDeJeu(taille, naviresOrdinateur);
        this.scanner = new Scanner(System.in);
    }

    public void placerNaviresJoueur() {
        System.out.println("Placement des navires du joueur :");

        for (Navire navire : plateauJoueur.getNavires()) {
            plateauJoueur.afficherGrille(true);

            System.out.println("Placez le navire de taille " + navire.getTaille());
            System.out.print("Entrez la ligne (A-J) : ");
            String ligneInput = scanner.next().toUpperCase();
            int ligne = ligneInput.charAt(0) - 'A';

            System.out.print("Entrez la colonne (1-10) : ");
            int colonne = scanner.nextInt() - 1;

            if (plateauJoueur.estPlacementValide(navire, ligne, colonne)) {
                navire.setPosition(ligne, colonne);
                System.out.println("Navire placé avec succès !");
            } else {
                System.out.println("Placement invalide. Réessayez.");
            }
        }
    }

    public void demarrerJeu() {
        placerNaviresJoueur();
        plateauOrdinateur.placerNaviresAleatoirement();

        boolean jeuTermine = false;
        while (!jeuTermine) {
            tourJoueur();
            if (plateauOrdinateur.tousCoules()) {
                System.out.println("Félicitations ! Vous avez coulé tous les navires de l'ordinateur.");
                jeuTermine = true;
                break;
            }

            tourOrdinateur();
            if (plateauJoueur.tousCoules()) {
                System.out.println("Désolé, l'ordinateur a coulé tous vos navires.");
                jeuTermine = true;
            }
        }
        scanner.close();
    }

    private void tourJoueur() {
        System.out.println("Votre tour de tirer :");
        System.out.print("Entrez la ligne (A-J) : ");
        String ligneInput = scanner.next().toUpperCase();
        int ligne = ligneInput.charAt(0) - 'A';

        System.out.print("Entrez la colonne (1-10) : ");
        int colonne = scanner.nextInt() - 1;

        boolean tirReussi = plateauOrdinateur.tirer(ligne, colonne);

        if (tirReussi) {
            System.out.println("Touché et coulé !");
        } else {
            System.out.println("Manqué !");
        }

        plateauOrdinateur.afficherGrille(false);
    }

    private void tourOrdinateur() {
        System.out.println("Tour de l'ordinateur de tirer :");
        Random random = new Random();
        int ligne = random.nextInt(plateauJoueur.getNavires().length);
        int colonne = random.nextInt(plateauJoueur.getNavires().length);

        boolean tirReussi = plateauJoueur.tirer(ligne, colonne);

        if (tirReussi) {
            System.out.println("L'ordinateur a touché et coulé un de vos navires !");
        } else {
            System.out.println("L'ordinateur a manqué !");
        }

        plateauJoueur.afficherGrille(true);
    }

    public static void main(String[] args) {
        JeuBatailleNavale jeu = new JeuBatailleNavale(10);
        jeu.demarrerJeu();
    }
}
