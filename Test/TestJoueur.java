import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class TestJoueur {
    Joueur j1;
    Joueur j2;
    Plateau p;
    @Before
    public void init(){
         p = new Plateau();
        j1 = new Joueur(0, p);

    }
    @Test
    public void testCase(){
        for(int i = 0; i < j1.pions.length; i++){
            assertEquals(p.plateau[0][i],j1.pions[i].getPosition());
        }
    }
    @Test
    public void testPion(){
        for(int i = 0; i< j1.pions.length; i++)
            assertEquals(p.pions.get(i), j1.pions[i]);
    }
    @Test
    public void testOtherJ(){
        assertEquals(1,j1.otherJ());
    }
}
