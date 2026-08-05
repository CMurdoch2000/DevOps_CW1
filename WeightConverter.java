import java.util.*;
import java.text.DecimalFormat;

public class WeightConverter {
    public static void main(String[] args) {
        double weight;
        DecimalFormat f = new DecimalFormat("##.##");
        weight = Double.parseDouble(args[0]);
        String unit = args[1];

        switch(unit) {
            case "kilograms":
                double pounds = weight * 2.205;
                double grams = weight * 1000;
                System.out.println(weight + " Kilograms = " + f.format(pounds) + " Pounds");
                System.out.println(weight + " Kilograms = " + f.format(grams) + " Grams");
                break;
            case "pounds":
                double kilograms = weight * 0.454;
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
        }
        System.out.println("Thank you for using the weight converter.");
    }
}
