import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Accueil extends JFrame {

    public Accueil() {
        super("Bienvenue");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        JPanel panelSaisie = new JPanel();
        panelSaisie.setLayout(new GridLayout(2,2, 10,5));
        JTextField textJ1 = new JTextField();
        JTextField textJ2 = new JTextField();
        JLabel l1 = new JLabel("Nom joueur 1 ");
        JLabel l2 = new JLabel("Nom joueur 1 ");
        panelSaisie.add(l1);
        panelSaisie.add(l2);
        panelSaisie.add(textJ1);
        panelSaisie.add(textJ2);
        JButton startButton = new JButton("Commencer le jeu");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new Game("Bienvenue", textJ1.getText(),  textJ2.getText()); // Lance le jeu
                dispose(); // Ferme la fenêtre d'accueil
            }
        });

        this.setLayout(new BorderLayout());
        this.add(panelSaisie, BorderLayout.NORTH);
        this.add(new JLabel("Bienvenue dans le jeu d’échecs", SwingConstants.CENTER), BorderLayout.CENTER);
        this.add(startButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Accueil a = new Accueil();
            a.setVisible(true);
        });
    }
}

 class Fin extends JFrame {

    public Fin() {
        super("Bienvenue");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        this.setLocationRelativeTo(null); // centre la fenêtre

        JButton startButton = new JButton("terminé");

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Ferme la fenêtre d'accueil
                System.exit(0);
            }
        });

        this.setLayout(new BorderLayout());
        this.add(new JLabel("Vous avez fini la partie", SwingConstants.CENTER), BorderLayout.CENTER);
        this.add(startButton, BorderLayout.SOUTH);
    }

}
