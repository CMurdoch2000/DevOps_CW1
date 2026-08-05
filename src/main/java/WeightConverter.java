import java.text.DecimalFormat;

@SuppressWarnings("java:S106") // Clears the System.out code smell for console apps
public class WeightConverter {
    public static void main(String[] args) {
        
        if (args.length < 2) {
            System.out.println("Error Please provide a weight and a unit.");
            System.out.println("Example java WeightConverter 25 kilograms");
            return; // Replaced System.exit to clear code smell
        }

        double weight = 0;
        DecimalFormat f = new DecimalFormat("##.##");

        try {
            weight = Double.parseDouble(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Error Please enter a valid number for the weight.");
            return;
        }

        String unit = args[1].toLowerCase();
        double pounds = 0;
        double grams = 0;
        double kilograms = 0;

        switch(unit) {
            case "kilograms":
                pounds = weight * 2.205;
                grams = weight * 1000;
                System.out.println(weight + " Kilograms = " + f.format(pounds) + " Pounds");
                System.out.println(weight + " Kilograms = " + f.format(grams) + " Grams");
                break;
            case "pounds":
                kilograms = weight * 0.454;
                grams = kilograms * 1000;
                System.out.println(weight + " Pounds = " + f.format(kilograms) + " Kilograms");
                System.out.println(weight + " Pounds = " + f.format(grams) + " Grams");
                break;
            case "grams":
                kilograms = weight / 1000;
                pounds = kilograms * 2.205;
                System.out.println(weight + " Grams = " + f.format(kilograms) + " Kilograms");
                System.out.println(weight + " Grams = " + f.format(pounds) + " Pounds");
                break;
            default:
                System.out.println("Error Unknown unit. Please use kilograms, pounds or grams.");
                return;
        }
        
        System.out.println("Thank you for using the weight converter.");
    }
}
