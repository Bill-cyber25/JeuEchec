import javax.swing.*;
import java.awt.*;

public class Exemple extends JPanel {

    // Matrice représentant les pièces
    private String[][] pieces = {
            {"\u265C","\u265E","\u265D","\u265B","\u265A","\u265D","\u265E","\u265C"},
            {"\u265F","\u265F","\u265F","\u265F","\u265F","\u265F","\u265F","\u265F"},
            {"","","","","","","",""},
            {"","","","","","","",""},
            {"","","","","","","",""},
            {"","","","","","","",""},
            {"\u2659","\u2659","\u2659","\u2659","\u2659","\u2659","\u2659","\u2659"},
            {"\u2656","\u2658","\u2657","\u2655","\u2654","\u2657","\u2658","\u2656"}
    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int tailleCase = Math.min(getWidth(), getHeight()) / 8;

        for (int ligne = 0; ligne < 8; ligne++) {
            for (int col = 0; col < 8; col++) {
                // Couleur de la case
                if ((ligne + col) % 2 == 0) {
                    g.setColor(new Color(240, 217, 181)); // clair
                } else {
                    g.setColor(new Color(181, 136, 99)); // foncé
                }
                g.fillRect(col * tailleCase, ligne * tailleCase, tailleCase, tailleCase);

                // Dessin de la pièce
                if (!pieces[ligne][col].equals("")) {
                    g.setColor(Color.BLACK); // Couleur du texte
                    g.setFont(new Font("Serif", Font.PLAIN, tailleCase - 10));
                    g.drawString(pieces[ligne][col], col * tailleCase + 10, (ligne + 1) * tailleCase - 10);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Échiquier avec paintComponent");
        Exemple panel = new Exemple();
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
