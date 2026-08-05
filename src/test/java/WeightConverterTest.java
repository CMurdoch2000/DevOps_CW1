import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class WeightConverterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restore() {
        System.setOut(originalOut);
    }

    @Test
    public void testMissingArguments() {
        WeightConverter.main(new String[]{});
        assertTrue(outContent.toString().contains("Error Please provide a weight and a unit."));
    }

    @Test
    public void testWrongFormat() {
        WeightConverter.main(new String[]{"twenty", "kilograms"});
        assertTrue(outContent.toString().contains("valid number"));
    }

    @Test
    public void testUpperCase() {
        WeightConverter.main(new String[]{"25", "POUNDS"});
        assertTrue(outContent.toString().contains("Kilograms"));
    }

    @Test
    public void testKilogramsMath() {
        WeightConverter.main(new String[]{"1", "kilograms"});
        assertTrue(outContent.toString().contains("2.205"));
        assertTrue(outContent.toString().contains("1000"));
    }

    @Test
    public void testCapitalised() {
        WeightConverter.main(new String[]{"25", "Grams"});
        assertTrue(outContent.toString().contains("Kilograms"));
    }

    @Test
    public void testPoundsMath() {
        WeightConverter.main(new String[]{"1", "pounds"});
        assertTrue(outContent.toString().contains("0.454"));
        assertTrue(outContent.toString().contains("454"));
    }

    @Test
    public void testGramsMath() {
        WeightConverter.main(new String[]{"1", "grams"});
        assertTrue(outContent.toString().contains("0.001"));
        assertTrue(outContent.toString().contains("0.002205"));
    }

    @Test
    public void testInvalidUnit() {
        WeightConverter.main(new String[]{"1", "stones"});
        assertTrue(outContent.toString().contains("Unknown unit"));
    }
}
