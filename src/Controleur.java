import java.util.ArrayList;

public class Controleur {
    private Plateau p;
    int idJ;
    public boolean active;
    public Pion pionActuel;
    public ArrayList<Case> selectionnees ;
    public Controleur(Plateau p){
        this.p = p;
        idJ = 0;
        pionActuel = null;
        active = false;
        selectionnees =new ArrayList<>();
    }

    public void deplacerPion(Case c){
        Joueur j = p.joueurs[idJ];
        //if(pionActuel.getIdJ() != idJ) throw  new IllegalArgumentException("on ne deplace que ses joueurs"); pour les tests
        if(pionActuel.getIdJ() == idJ && pionActuel.seDeplacer(c)) {
            idJ = j.otherJ();
            if(pionActuel.getType() == Pion.Type.Pion) pionActuel.firstMove = false;
        }
        deSelectionner();
        System.out.println("joueur " + idJ);
    }
    public boolean peutDeplacer(Case c){
        return pionActuel.peutSedeplacer(c);
    }

    public void mange(Case c){
        if(pionActuel.peutManger(c)){
            deplacerPion(c);
            System.out.println("le pion est mangé " );
        }
        else
            System.out.println("on ne peut pas manger dans cette situation");
    }

    // selectionner des cases

    public void selectionner(){
        selectionnees = pionActuel.deplacementsPossibles();
    }

    public void deSelectionner(){
        selectionnees = new ArrayList<>();
    }


}
