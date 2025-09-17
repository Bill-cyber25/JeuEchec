import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VueCase extends JPanel implements MouseListener {
    public static final int SCALE = 2;
    public static final int TAILLE_CASE = 30*SCALE;
    private static final int TAILLE_JOUEUR = 10*SCALE;
    private static final int TAILLE_BORD_HIGHLIGHT = (int) (TAILLE_JOUEUR/6.0) +1;
    private Controleur controleur;
    public  Case c;
    private VuePlateau vp;
    public VueCase(Case c, VuePlateau vp, Controleur controleur){
        setPreferredSize(new Dimension(TAILLE_CASE, TAILLE_CASE));
        this.c = c;
        this.vp = vp;
        this.controleur = controleur;
        this.addMouseListener(this);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
            boolean estClair = (c.getX() + c.getY()) % 2 == 0;
            g.setColor(estClair ? new Color(200, 200, 200) : new Color(0, 100, 0));
            g.fillRect(TAILLE_BORD_HIGHLIGHT, TAILLE_BORD_HIGHLIGHT,
                TAILLE_CASE-TAILLE_BORD_HIGHLIGHT*2, TAILLE_CASE-TAILLE_BORD_HIGHLIGHT*2);
        // afficher l'evenetuel pion present dans la case
        if(controleur != null && controleur.selectionnees.contains(c)){
            g.setColor(new Color(20, 20, 20, 120)); // Jaune semi-transparent
            g.fillOval(c.getX() + 20, c.getY()+ 20, TAILLE_CASE/3, TAILLE_CASE/3);
        }

        if(c.contientPion()) {
            // pour preciser le pion selectionné
            if(controleur.pionActuel == c.getPion()) g.setColor(new Color(255,200,0));
            else if(c.getPion().getIdJ() == 0)
                g.setColor(new Color(100,100,100));
            else
                g.setColor(new Color(0,0,0));
            affichePion(g);
            //g.fillOval(TAILLE_CASE/2 -TAILLE_JOUEUR/2, TAILLE_CASE/2-TAILLE_JOUEUR/2, TAILLE_JOUEUR, TAILLE_JOUEUR);
        }

    }

    public void affichePion(Graphics g){
        String type ="" ;
        switch (c.getPion().getType()){
            case Pion -> type ="♟";
            case Fou ->  type ="♝";
            case Tour -> type = "♜";
            case Reine -> type = "♛";
            case Roi -> type = "♚";
            case Cavalier -> type = "♞";
        }
        g.setFont(new Font("Serif", Font.PLAIN, TAILLE_CASE/2));
        g.drawString(type,TAILLE_CASE/2-15,TAILLE_CASE/2+10);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(controleur.active ){

            if(c.contientPion()){

                Pion pi = c.getPion();
                if( controleur.pionActuel == null) {
                    controleur.pionActuel = pi;
                    controleur.selectionnees = pi.deplacementsPossibles();
                }
                else {
                    if (pi.getIdJ() == controleur.pionActuel.getIdJ()) {
                        controleur.pionActuel = pi;
                        controleur.selectionnees = pi.deplacementsPossibles();

                    } else if (controleur.pionActuel.peutManger(c)) {
                        controleur.mange(c);
                        controleur.pionActuel = null;
                        controleur.active = false;
                    }
                }
            }
            else if(controleur.peutDeplacer(c)){
                controleur.selectionnees = controleur.pionActuel.deplacementsPossibles();
                if(controleur.selectionnees.isEmpty()) System.out.println("vide");
                controleur.deplacerPion(c);
                controleur.pionActuel = null;
                controleur.active = false;
            }
        }
        else{
            if (c.contientPion() && c.getPion().getIdJ() == controleur.idJ) {
                controleur.pionActuel = c.getPion();
                controleur.selectionnees = controleur.pionActuel.deplacementsPossibles();
                controleur.active = true;
            }
        }
        vp.repaint();

    }


    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
