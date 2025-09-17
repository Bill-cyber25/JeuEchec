import java.util.ArrayList;

public class Pion {
    public enum Type{Cavalier,Pion,Tour,Reine,Fou,Roi}
    private int idJ;
    protected Case position;
    protected Type type;
    protected Plateau p;
    public boolean firstMove ;
    public Pion(int id, Case c, Plateau p){
        this.idJ = id;
        this.position = c;
        this.p = p;
        this.type = Type.Pion;
        firstMove = true;
    }
   /* public Pion(int id, Case c){
        this.position = c;
        this.idJ = id;
    }*/
    public Type getType(){ return type;}
    public int getIdJ(){ return idJ; }
    public Case getPosition(){ return position; }

    // verifier qu'il ne saut pas des pions

    public boolean verifieLigne(int lig){
        if(idJ == 0){
            for(int i = position.getX()+1; i <= lig; i++){
                if(p.plateau[i][position.getY()].contientPion()) {
                    System.out.println("dejà un joueur à cette place " +  p.plateau[lig-1][i].getX() );
                    return false;
                }
                System.out.println("posi " + p.plateau[i][position.getY()].getX() + " ok");

            }
        }
        else if(idJ == 1){
            for(int i = position.getX()-1; i >= lig; i--){
                if(p.plateau[i][position.getY()].contientPion()) {
                    System.out.println("dejà un joueur à cette place joueur 1 ::  " +  p.plateau[lig+1][i].getX() );
                    return false;
                }
                System.out.println("posi " + p.plateau[i][position.getY()].getX() + " ok");

            }
        }
        return true;
    }

    // verifie que le pion ne fait pas de machine arriere
    private boolean verifieDeplacement(Case c){
        if(peutManger(c)) return true;
        if(c.getX() < position.getX() && this.idJ == 0){
            System.out.println("ici probleme de position de 0 ");
            return false;
        }
        if(c.getX() > position.getX() && this.idJ == 1) return false;
        System.out.println(c.getX());
        if(!verifieLigne(c.getX())) return false;
        System.out.println("ici sans probleme " + c.getX() + " dep " + c.getY());

        return true;
    }

    public  boolean peutManger(Case c){
        if( !c.contientPion() || c.getPion().getIdJ() == position.getPion().getIdJ()) {
            return false;
        }
        if(position.getPion().getIdJ()== 0 &&
                (c.getX()  == position.getX() + 1  && position.getY() - 1 == c.getY()
                || c.getX()  == position.getX() + 1  && position.getY() + 1 == c.getY()
                )
        ) return true;
        if(position.getPion().getIdJ()== 1 &&
                (c.getX()  == position.getX() - 1  && position.getY() - 1 == c.getY()
                        || c.getX()  == position.getX() - 1  && position.getY() + 1 == c.getY()
                )
        ) return true;
        return false;
    }

    public  boolean seDeplacer(Case c){
        if(c == position) return false;
        if(peutSedeplacer(c) && verifieDeplacement(c)){
            deplace(c);
            return true;
        }
        return false;
    }
    public int otherJ(){
        if(this.idJ == 0) return 1;
        return 0;
    }
    public boolean peutSedeplacer(Case c){
        return  peutManger(c) || (c.getY() == this.position.getY() && Math.abs(c.getX() - position.getX()) <= 2) ;
    }
    public  ArrayList<Case> deplacementsPossibles(){
        ArrayList<Case> deplacements = new ArrayList<>();
        // pour les deplacements normaux
        int signe = 1;
        if(this.getIdJ() == 1 ) signe = -1;
        Case c = p.plateau[position.getX()+signe][position.getY()];
        if(!c.contientPion()) deplacements.add(c);
         c = p.plateau[position.getX()+signe][position.getY()];
        if(peutManger(c) && position.getX()+signe > -1) deplacements.add(c);
        c = p.plateau[position.getX()+signe][position.getY()];
        if(peutManger(c) && position.getX()+signe < 8) deplacements.add(c);
        // le premier mouvement d'un pion
        if(firstMove){
            System.out.println("on est venu voir firstMove ");
            signe = -2;
            if(idJ == 0) signe = 2;
            c = p.plateau[position.getX()+signe][position.getY()];
            if(!c.contientPion() && position.getX()+signe < 8) deplacements.add(c);
        }
        // les cases qu'on peut eventuellement manger
        int x = position.getX();
        int y = position.getY();
        if(idJ == 0 ) {
            if( x+1 < 8 && y -1> -1){
                c = p.plateau[x+ 1][y - 1];
                if (peutManger(c)) deplacements.add(c);
            }
            if( x+1 < 8 && y + 1 < 8){
                c = p.plateau[x+ 1][y +1];
                if (peutManger(c)) deplacements.add(c);
            }
        }
        if(idJ == 1 ) {
            if( x-1 > -1 && y -1> -1){
                c = p.plateau[x- 1][y - 1];
                if (peutManger(c)) deplacements.add(c);
            }
            if( x-1 > -1 && y + 1 < 8){
                c = p.plateau[x- 1][y +1];
                if (peutManger(c)) deplacements.add(c);
            }
        }


        if(position.getPion().getIdJ()== 1 &&
                (c.getX()  == position.getX() - 1  && position.getY() - 1 == c.getY()
                        || c.getX()  == position.getX() - 1  && position.getY() + 1 == c.getY()
                )
        ) ;

        return deplacements;
    }
    public void deplace(Case c){
        this.position.setPion(null);
        position = c;
        c.setPion(this);
    }


}

class Cavalier extends Pion{

    public Cavalier(int id, Case c, Plateau p){
        super(id,c,p);
        position = c;
        position.setPion(this) ;
        this.type = Type.Cavalier;
    }
    /*public Cavalier(int id, Case c){

        super(id,c);
        this.type = Type.Cavalier;
    }*/
    @Override
    public boolean seDeplacer(Case c) {
        if(c == position) return false;
        if(deplacementsPossibles().isEmpty()) throw new IllegalArgumentException(" Deplacement impossible ");
        if(contains(c)) {
           deplace(c);
            return true;
        }
       return false;
    }
    public boolean contains(Case c){
        return deplacementsPossibles().contains(c);
    }
    public boolean peutSedeplacer(Case c){
        return contains(c);
    }

    public boolean peutManger(Case c){
        return this.getIdJ() != c.getPion().getIdJ();
    }
    @Override

    public ArrayList<Case> deplacementsPossibles() {
        ArrayList<Case> deplacementPossibles = new ArrayList<>();
        for(Case c : voisins()){
           if(!c.contientPion() || (c.contientPion() && peutManger(c)) ) deplacementPossibles.add(c);
        }
        return deplacementPossibles;
    }

    public ArrayList<Case> voisins(){
        ArrayList<Case> deplacements = new ArrayList<>();
        int x = position.getX();
        int y = position.getY();
        if(x-1 > -1 && y + 2 < 8) deplacements.add(p.getCase(x -1, y + 2));
        if(x+2 < 8 && y -1 > -1 ) deplacements.add(p.getCase(x +2, y -1));
        if(x-1 > -1 && y - 2 > -1) deplacements.add(p.getCase(x -1, y - 2));
        if(x-2 > -1 && y -1 > -1) deplacements.add(p.getCase(x -2, y -1));
        if(x+1 < 8 && y - 2 >-1) deplacements.add(p.getCase(x +1, y - 2));
        if(x-2 > -1 && y + 1 < 8) deplacements.add(p.getCase(x -2, y + 1));
        if(x+1 < 8 && y + 2 < 8) deplacements.add(p.getCase(x +1, y + 2));
        if(x+2 < 8 && y + 1 < 8) deplacements.add(p.getCase(x +2 , y + 1));
        return  deplacements;
    }
}

class Tour extends Pion{
    public Tour(int id, Case c, Plateau p){

        super(id,c, p);
        position =c;
        position.setPion(this);
        this.type = Type.Tour;

    }
    public boolean seDeplacer(Case c){
        if(c == position) return false;

        if(c.getY() == position.getY() ){
            System.out.println("meme X");

            deplace(c);
            return true;

        }
        if (c.getX() == position.getX()){
            System.out.println("meme Y");
            deplace(c);
            return true;
        }

        return false;
    }

    public  boolean peutManger(Case c){
        return c.getPion().getIdJ() != this.getIdJ();
    }
    public boolean peutSedeplacer(Case c){
        return deplacementsPossibles().contains(c);
    }
    @Override
    public ArrayList<Case> deplacementsPossibles() {
        return deplacementTour(position, p, this);
    }

    public static ArrayList<Case> deplacementTour(Case position, Plateau p, Pion tour){
        ArrayList<Case> cases = new ArrayList<>();
        for(int x = position.getX()+1; x < Plateau.TAILLE; x++){
            Case c = p.plateau[x][position.getY()];
            if(c.contientPion() && !tour.peutManger(c)) break;
            cases.add(c);
            if(c.contientPion()) break;

        }
        for(int y = position.getY()+1; y < Plateau.TAILLE; y++){
            Case c = p.plateau[position.getX()][y];
            if(c.contientPion() && !tour.peutManger(c)) break;
            cases.add(c);
            if(c.contientPion()) break;
        }
        for(int x = position.getX()-1; x>=0; x--){
            Case c = p.plateau[x][position.getY()];
            if(c.contientPion()&& !tour.peutManger(c)) break;
            cases.add(c);
            if(c.contientPion()) break;
        }
        for(int y = position.getY()-1; y>=0; y--){
            Case c = p.plateau[position.getX()][y];
            if(c.contientPion()&& !tour.peutManger(c)) break;
            cases.add(c);
            if(c.contientPion()) break;
        }

        return cases;
    }
}

class Fou extends Pion{
    public Fou(int id, Case c,Plateau p){
        super(id,c,p);
        position =c;
        position.setPion(this);
        this.type = Type.Fou;
    }

    public boolean peutSedeplacer(Case c){
        return Math.abs(c.getY() - position.getY()) == Math.abs(c.getX() - position.getX());
    }
    @Override
    public boolean seDeplacer(Case c) {
        if(c == position) return false;
        if(this.peutSedeplacer(c)){
            deplace(c);
            return true;
        }
        return false;
    }

    public boolean peutManger(Case c){
        return c.getPion().getIdJ() != this.getIdJ();
    }
    @Override
    public ArrayList<Case> deplacementsPossibles() {
        return deplacementsFou(position, p, this);
    }

    public static ArrayList<Case> deplacementsFou(Case position, Plateau p, Pion fou) {
        ArrayList<Case> cases = new ArrayList<>();
        int x = position.getX();
        int y = position.getY();
        for(int i = 1; i < Plateau.TAILLE; i++){
            if(x-i < 0 || y-i < 0) break;
            Case c = p.plateau[x-i][y-i];
            if(c.contientPion()&& !fou.peutManger(c)) break;
            cases.add(c);
            if(c.contientPion()) break;
        }

        for(int i = 1; i < Plateau.TAILLE; i++){
            if(x+i > 7 || y+i > 7) break;
            Case c = p.plateau[x+i][y+i];
            if(c.contientPion()&& !fou.peutManger(c)) break;
            cases.add(c);
            if(c.contientPion()) break;
        }

        for(int i = 1; i < Plateau.TAILLE; i++){
            if(x-i < 0 || y+i > 7) break;
            Case c = p.plateau[x-i][y+i];
            if(c.contientPion()&& !fou.peutManger(c)) break;
            cases.add(c);
            if(c.contientPion()) break;
        }

        for(int i = 1; i < Plateau.TAILLE; i++){
            if(x+i > 7 || y-i < 0) break;
            Case c = p.plateau[x+i][y-i];
            if(c.contientPion()&& !fou.peutManger(c)) break;
            cases.add(c);
            if(c.contientPion()) break;
        }

        return cases;
    }
}
class Reine extends Pion{
    public Reine(int idJ, Case c, Plateau p){

        super(idJ,c,p);
        this.type = Type.Reine;

    }
    public boolean peutSedeplacer(Case c){
        return Math.abs(c.getY() - position.getY()) == Math.abs(c.getX() - position.getX())
                || c.getY() == position.getY() ||c.getX() == position.getX();
    }
    public boolean peutManger(Case c){
        return c.getPion().getIdJ() != this.getIdJ();
    }
    @Override
    public boolean seDeplacer(Case c) {
        if(c == position) return false;
        if(this.peutSedeplacer(c)){
            deplace(c);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Case> deplacementsPossibles() {
        ArrayList<Case> c = Fou.deplacementsFou(position,p,this);
        c.addAll(Tour.deplacementTour(position,p,this));
        return c;
    }
}

class Roi extends Pion{
    public Roi(int idJ, Case c,Plateau p){
        super(idJ,c,p);
        this.type = Type.Roi;
    }
    public boolean peutSedeplacer(Case c){
        int i = position.getX();
        int j = position.getY();
        return deplacementsPossibles().contains(c);
    }
    public boolean peutManger(Case c){
        return c.contientPion() && c.getPion().getIdJ() != this.getIdJ();
    }
    @Override
    public boolean seDeplacer(Case c) {
        if(c == position) return false;
        if(this.peutSedeplacer(c)){
            deplace(c);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Case> deplacementsPossibles() {
        ArrayList<Case> dep = new ArrayList<>();
        int x = position.getX();
        int y = position.getY();
        for(int i = -1; i < 2;i++){
            for(int j = -1; j < 2;j++){
                if(!(x+i < 0 || x+i > 7 || y+j < 0 || y+j > 7)) {
                    Case c = p.plateau[x + i][y + j];
                    if (!c.contientPion()) dep.add(c);
                    if (c.contientPion() && peutManger(c)) dep.add(c);
                }

            }
        }
        return dep;
    }
}