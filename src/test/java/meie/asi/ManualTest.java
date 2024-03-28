package meie.asi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ManualTest {
    Külmkapp külmkapp = new Külmkapp(1);
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @Test
    public void addMoreThanAllowed() {
        külmkapp.lisaKülmkappi(new Ese("asi", new Date(), 2));
        equalsConsole("Ei mahtunud külmikusse. Söö ära.");
    }

    @Test
    public void testCanBeFound() {
        assertEquals(true, külmkapp.kasOnTühi());

        Ese uusEse = new Ese("asi2", new Date(), 1);
        külmkapp.lisaKülmkappi(uusEse);
        Ese ese = külmkapp.leiaEseNimetusega("asi2");
        assertEquals(uusEse, ese);
    }


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    public void equalsConsole(String expected) {
        assertEquals(expected.trim(), outContent.toString().trim());
    }

}
