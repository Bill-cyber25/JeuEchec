public class Case {
    private int x,y;
    private Pion pion;
    public Case(int xx, int yy){
        x = xx;
        y = yy;
        pion = null;
    }
    public int getX(){return x; }
    public int getY(){return y; }
    public Pion getPion(){ return pion; }
    public void setPion(Pion p){ this.pion = p;}
    public boolean contientPion(){
        return pion != null;
    }

}
