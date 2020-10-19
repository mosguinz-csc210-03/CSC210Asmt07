import java.util.Scanner;

public class CoffeeShopAccountInventoryManager {

    static final Scanner scan = new Scanner(System.in);
    final static String[] items = {"cups", "coffee beans", "lids", "towel", "sleeves"};
    static double[] quantity = new double[items.length];
    static double[] price = new double[items.length];

    public static void main(String[] args) {
        loop();
    }

    public static void loop() {
        setupInventory();
    }

    private static void setupInventory() {
        for (int i = 0; i < items.length; i++) {

        }
    }

    /**
     * Prompt user for value to use in inventory. Re-prompts until a valid input
     * has been entered (>= 0).
     *
     * @param prompt The message to display.
     * @return A valid quantity/price value.
     */
    private static double promptValue(String prompt) {
        double value;
        while (true) {
            System.out.printf("%s: ", prompt);
            value = scan.nextDouble();
            if (value < 0) {
                System.out.println("Invalid amount. Enter a value of 0 or more.");
                continue;
            }
            return value;
        }
    }

}
