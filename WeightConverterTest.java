import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class WeightConverterTest {
    public static void main(String[] args) {
        int failedTests = 0;
        
        System.out.println("Starting Unit Tests...");
        
        // Save the original standard output so we can restore it later
        PrintStream originalOut = System.out;
        
        // TEST 1: Missing Arguments Task 5a
        System.out.println("Running Test 1: Checking missing arguments");
        try {
            String[] emptyArgs = {};
            WeightConverter.main(emptyArgs);
            // If the code does not crash we need to check if it printed a friendly warning
        } catch (Exception e) {
            System.out.println("Test 1 Failed: The programme crashed with an error instead of handling missing inputs gracefully.");
            failedTests++;
        }

        // TEST 2: Wrong Format Task 5a
        System.out.println("Running Test 2: Checking incorrect number formats");
        try {
            String[] wrongFormatArgs = {"twenty", "kilograms"};
            WeightConverter.main(wrongFormatArgs);
        } catch (Exception e) {
            System.out.println("Test 2 Failed: The programme crashed when given text instead of numbers.");
            failedTests++;
        }

        // TEST 3: Case Sensitivity Task 5b
        System.out.println("Running Test 3: Checking upper case units");
        try {
            // We capture the output to see what the programme actually prints
            ByteArrayOutputStream capturedOutput = new ByteArrayOutputStream();
            System.setOut(new PrintStream(capturedOutput));
            
            String[] upperCaseArgs = {"25", "POUNDS"};
            WeightConverter.main(upperCaseArgs);
            
            // Restore normal printing
            System.setOut(originalOut);
            String output = capturedOutput.toString();
            
            if (!output.contains("Kilograms")) {
                System.out.println("Test 3 Failed: The programme ignored the unit because it was in all capitals.");
                failedTests++;
            }
        } catch (Exception e) {
            System.setOut(originalOut);
            System.out.println("Test 3 Failed: The programme crashed during the case sensitivity test.");
            failedTests++;
        }

        // TEST 4: Conversion Accuracy Task 5c
        System.out.println("Running Test 4: Checking Kilogram conversion maths");
        try {
            ByteArrayOutputStream capturedOutput = new ByteArrayOutputStream();
            System.setOut(new PrintStream(capturedOutput));
            
            // Testing 1 Kilogram conversion
            String[] mathArgs = {"1", "kilograms"};
            WeightConverter.main(mathArgs);
            
            System.setOut(originalOut);
            String output = capturedOutput.toString();
            
            // Checking if the exact conversion numbers from the specification appear in the output
            if (!output.contains("2.205") || !output.contains("1000")) {
                System.out.println("Test 4 Failed: The mathematical conversion for Kilograms is inaccurate.");
                failedTests++;
            }
        } catch (Exception e) {
            System.setOut(originalOut);
            System.out.println("Test 4 Failed: The programme crashed during the maths test.");
            failedTests++;
        }

        // FINAL RESULT EVALUATION
        System.out.println("--------------------------------");
        if (failedTests > 0) {
            System.out.println("BUILD FAILED: " + failedTests + " tests did not pass.");
            // This command throws an error code to Jenkins forcing the build to fail and stop
            System.exit(1); 
        } else {
            System.out.println("BUILD SUCCESSFUL: All tests passed!");
            System.exit(0);
        }
    }
}
