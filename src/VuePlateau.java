import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VuePlateau extends JPanel implements MouseListener {

    public final VueCase[][] plateau;
    public static Plateau p;
    private Controleur controleur;

    public VuePlateau(Plateau p, Controleur controleur){
        setLayout(new GridLayout(8,8,1,1));
        plateau = new VueCase[Plateau.TAILLE][Plateau.TAILLE];
        this.p = p;
        this.controleur = controleur;
        for(int i = 0; i < Plateau.TAILLE; i++){
            for(int j = 0 ; j < Plateau.TAILLE; j++){
                VueCase vc = new VueCase(p.getCase(i,j),this, controleur);
                this.add(vc);
                plateau[i][j] = vc;
            }
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.WHITE);
        /**for(int i = 0; i < Plateau.TAILLE; i++) {
            for (int j = 0; j < Plateau.TAILLE; j++) {

                plateau[i][j].paintComponent(g);
            }
        }**/

    }

    public VueCase getVueCase(Case c){
        for(int i = 0 ;i< Plateau.TAILLE; i++){
            for(int j = 0 ;j< Plateau.TAILLE; j++){
                if(plateau[i][j].c == c) return plateau[i][j];
            }
        }
        return null;
    }
    public static void main (String [] args){
        JFrame fenetre = new JFrame("Echec");
        Plateau p = new Plateau();
        Controleur cont = new Controleur(p);
        VuePlateau vp= new VuePlateau(p,cont);
        fenetre.add(vp);
        fenetre.addMouseListener(vp);
        fenetre.setVisible(true);
        fenetre.pack();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
