import java.util.Arrays;
import java.util.Scanner;

public class CoffeeShopAccountInventoryManager {

    static final Scanner scan = new Scanner(System.in);
    final static String[] items = {"cups", "coffee beans", "lids", "towel", "sleeves"};
    static int[] quantity = new int[items.length];
    static double[] price = new double[items.length];

    public static void main(String[] args) {
        loop();
    }

    public static void loop() {
        setupInventory();
    }

    /**
     * Initial set up for inventory values.
     * <p>
     * Prompt user to set starting stock quantities and values for each items in
     * the stock. Amount and type of items are defined by {@link #items}.
     */
    private static void setupInventory() {
        for (int i = 0; i < items.length; i++) {
            String prompt = String.format("Enter the number of %s in stock", items[i]);
            quantity[i] = (int) promptInventoryValue(prompt);
            prompt = String.format("Enter the cost for %s", items[i]);
            price[i] = promptInventoryValue(prompt);

            System.out.printf("Recorded %d %s at $%.2f each in stock.%n%n", quantity[i], items[i], price[i]);
        }

        System.out.printf("Stock quantities: %s%n", Arrays.toString(quantity));
        System.out.printf("Inventory prices: %s%n", Arrays.toString(price));
    }

    /**
     * Prompt user for value to use in inventory. Re-prompts until a valid input
     * has been entered (>= 0).
     *
     * @param prompt The message to display.
     * @return A valid quantity/price value.
     */
    private static double promptInventoryValue(String prompt) {
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
