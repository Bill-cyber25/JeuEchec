import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestCase{
    Case c44, c36, c63, c23, c32, c25,c52, c56, c65;
    Case c55, c66;
    @Before
    public void init(){
        c44 = new Case(4,4);
        c36 = new Case(3,6);
        c63 = new Case(6,3);
        c23 = new Case(2,3);
        c32 = new Case(3,2);
        c25 = new Case(2,5);
        c52 = new Case(5,2);
        c56 = new Case(5,6);
        c65 = new Case(6,5);
        c55 = new Case(5,5);
        c66 = new Case(6,6);

    }
    @Test
    public void testGet(){
        assertEquals(4,c44.getX());
        assertEquals(4,c44.getX());
        assertEquals(6,c56.getY());
    }
    @Test
    public void testDeplacer(){
        Cavalier cav = new Cavalier(0, c44);
        assertEquals(c44,cav.position);
        assertEquals(cav,c44.getPion());
        cav.seDeplacer(c56);
        assertEquals(cav,c56.getPion());
        assertEquals(c56,cav.position);
        assertNull(c44.getPion());
    }
    @Test
    public void testPeutSeplacer(){
        Cavalier cav = new Cavalier(0,c44);
        ArrayList<Case> cases = cav.deplacementsPossibles();
        assertEquals(8,cases.size());
        assertTrue(cav.contains(c36));
        assertTrue(cav.contains(c56));
        assertFalse(cav.contains(c55));
        assertFalse(cav.contains(c66));
    }
    @Test
    public void testSetPion(){
        assertNull(c44.getPion());
        Cavalier p = new Cavalier(0,c44);
        c44.setPion(p);
        assertEquals(p,c44.getPion());
        assertEquals(0,c44.getPion().getIdJ());
        c56.setPion(p);
        assertEquals(0,c56.getPion().getIdJ());
        p.seDeplacer(c56);
        assertNull(c44.getPion());
    }
}
