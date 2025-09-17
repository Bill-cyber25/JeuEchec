import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Game {
    private static final int DELAY = 40;
    private VuePlateau vp;
    private JFrame frame;
    private Timer timer;
    private Controleur cont;
    public String message ;
    public String nomJ1 = "A";
    public String nomJ2 = "B";
    public Game(String mes, String n1, String n2){
        this.message = mes;
        nomJ1 = n1;
        nomJ2 = n2;
        this.frame = new JFrame("Bienvenue à ce match entre : " + message);
        Plateau p = new Plateau();
        cont = new Controleur(p);
        vp   = new VuePlateau(p,cont);
        frame.add(vp);
        frame.add(new JLabel(nomJ1), BorderLayout.NORTH);
        frame.add(new JLabel(nomJ2), BorderLayout.SOUTH);
        affiche();
        startTimer();

    }
    private void startTimer(){
        timer = new Timer(DELAY, (ActionEvent e) -> {
            if (over()) {
                frame.dispose();
                new Fin().setVisible(true);
                timer.stop();
            }
        });
        timer.start();
    }
    private void affiche() {
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public boolean over(){
        return false;
    }


}
