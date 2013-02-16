import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

public class GildedRoseTest {

    @Test
    public void shouldPrintSameOutputForFirstDay() throws IOException {
        ByteArrayOutputStream out = interceptOutput();
        GildedRose.main(null);
        assertEquals(Days.DAYS.get(0), out.toString());
    }

    @Test
    public void shouldPrintSameOutputForNextFiveDays() throws IOException {
        GildedRose.main(null);
        for (int day = 1; day <= 5; day++) {
            ByteArrayOutputStream out = interceptOutput();
            GildedRose.updateQuality();
            GildedRose.printItemsOnDay();
            assertEquals(Days.DAYS.get(day), out.toString());
        }
    }

    private ByteArrayOutputStream interceptOutput() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        return out;
    }
}
