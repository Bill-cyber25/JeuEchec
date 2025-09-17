import java.util.ArrayList;

public class Joueur {

    public Pion[] pions;
    int idJ;
    private Plateau p;
    public Joueur(int idJ, Plateau p){
        this.idJ = idJ;
        this.p = p;
        pions = new Pion[Plateau.NB_PIONS];
        initialisePions();
    }
    public boolean deplacer(Case c){
       // if(c.getPion().getIdJ() == this.idJ) throw new IllegalArgumentException("il y 'a déjà un pion du même joueur")
        return pions[idJ].seDeplacer(c);
    }
    public void initialisePions(){
        for(int i = 0; i < Plateau.NB_PIONS; i++){
            pions[i] = p.pions.get(i);
        }
    }
    public int otherJ(){
        if(idJ == 0) return  1;
        return 0;
    }
    public void retire(int index){
        pions[index] = null;
    }
}
