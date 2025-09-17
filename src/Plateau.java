import java.util.ArrayList;

public class Plateau {
    public static final int TAILLE = 8;
    public static final int NB_PIONS = 8;
    public ArrayList<Pion> pions;
    public Case[][] plateau;
    public Joueur[] joueurs;
    public Plateau(){
        pions = new ArrayList<>();
        plateau = new Case[TAILLE][TAILLE];
        joueurs = new Joueur[2];
        initialisePlateau();
    }
    public Case getCase(int i, int j){
        return plateau[i][j];
    }
    public void initialisePlateau(){
        for(int i = 0 ; i < TAILLE; i++){
            for(int j = 0; j < TAILLE ; j++){
                Case c = new Case(i,j);
                plateau[i][j] = c;
            }
        }
        // ajouter les pions dans le plateau
        placePionsJoueur(0,0);
        placePions(0,1);
        placePionsJoueur(1,7);
        placePions(1,6);
        addJoueur();
    }

    private void placePionsJoueur(int idJ, int lig){

        addTour(idJ,plateau[lig][0]);
        addTour(idJ,plateau[lig][7]);

        addCavalier(idJ,plateau[lig][1]);
        addCavalier(idJ,plateau[lig][6]);

        addFou(idJ,plateau[lig][2]);
        addFou(idJ,plateau[lig][5]);

        addRoi(idJ,plateau[lig][3]);

        addReine(idJ,plateau[lig][4]);



    }

    private void placePions(int idJ, int lig){
        for(int i = 0; i < 8; i++){
            addPion(idJ,plateau[lig][i]);
        }
    }

    public void addJoueur(){
        Joueur j1 = new Joueur(0,this);
        Joueur j2 = new Joueur(1, this);
        joueurs[0] = j1;
        joueurs[1] = j2;
    }
    public  void addCavalier(int idj, Case c){
        Pion p = new Cavalier(idj,c,this);
        plateau[c.getX()][c.getY()].setPion(p);
        pions.add(p);
    }
    public  void addTour(int idj, Case c){
        Pion p = new Tour(idj,c, this);
        plateau[c.getX()][c.getY()].setPion(p);
        pions.add(p);
    }
    public void addFou(int idJ, Case c){
        Pion p = new Fou(idJ,c,this);
        plateau[c.getX()][c.getY()].setPion(p);
        pions.add(p);
    }
    public void addReine(int idJ, Case c){
        Pion p = new Reine(idJ,c,this);
        plateau[c.getX()][c.getY()].setPion(p);
        pions.add(p);
    }
    public void addRoi(int idJ, Case c){
        Pion p = new Roi(idJ,c,this);
        plateau[c.getX()][c.getY()].setPion(p);
        pions.add(p);
    }

    public void addPion(int idJ, Case c){
        Pion p = new Pion(idJ,c, this);
        plateau[c.getX()][c.getY()].setPion(p);
        pions.add(p);
    }

    public static void main(String[] args ){
        Plateau p = new Plateau();

    }

    public void retirePion(Case c) {
        pions.remove(c.getPion());
    }
}
