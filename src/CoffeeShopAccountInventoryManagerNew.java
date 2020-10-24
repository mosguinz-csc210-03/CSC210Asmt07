/*
 * Assignment 07
 * Description: Coffee shop inventory manager - refactored
 * Name: Kullathon "Mos" Sitthisarnwattanachai
 * Teammate: Amber Hartigan
 * ID: 921425216
 * Class: CSC 210-03
 * Semester: Fall 2020
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * Refactored from ASMT-06.
 * <p>
 * See also: https://github.com/mosguinz-csc210-03/CSC210Asmt06
 */
public class CoffeeShopAccountInventoryManagerNew {

    static final Scanner scan = new Scanner(System.in);

    /* Part (1)(a)(a) */
    final static String[] items = {"cups", "coffee beans", "lids", "towel", "sleeves"};
    /* Part (1)(a)(b) */
    final static String[] labels = {"quantity", "price"};
    /* Part (1)(a)(c) */
    static int[][] values = new int[items.length][labels.length];

    public static void main(String[] args) {
        loop();
    }

    public static void loop() {
        setupInventory();

        while (true) {
            switch (promptAction()) {
                case 1:
                    displayInventory();
                    break;
                case 2:
                    displayLowInventory();
                    break;
                case 3:
                    displayTotalValue();
                    break;
                case 4:
                    displayHighLowInventory();
                    break;
                case 5:
                    System.out.println("\nExiting... Goodbye!");
                    return;
            }
        }
    }

    /**
     * Initial set up for inventory values.
     * <p>
     * Prompt user to set starting stock quantities and values for each items in
     * the stock. Amount and type of items are defined by {@link #items}.
     */
    private static void setupInventory() {
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < labels.length; j++) {
                String item = items[i], valType = labels[j];
                final int value = values[i][j] = promptInventoryValue(String.format("Enter the %s of %s", item, valType));
                System.out.printf("Recorded %s of %s at %d", valType, item, value);
            }
        }

        System.out.printf("Stock quantities: %s%n", Arrays.toString(quantity));
        System.out.printf("Inventory prices: %s%n", Arrays.toString(price));
    }

    /**
     * Prompt the user for a menu option.
     *
     * @return Returns an `int` that corresponds to the menu option.
     */
    private static int promptAction() {
        int action;
        while (true) {
            System.out.println("\nChoose a number from the following menu:");
            System.out.println("1. Print inventory");
            System.out.println("2. Check for low inventory");
            System.out.println("3. Total inventory value");
            System.out.println("4. Highest and lowest inventory value");
            System.out.println("5. Exit");
            System.out.print(">>> ");
            action = scan.nextInt();
            if (action < 1 || action > 5) {
                System.out.println("\nInvalid selection. Please enter a valid option.");
                continue;
            }
            System.out.println();
            return action;
        }
    }

    /**
     * Prompt user for value to use in inventory. Re-prompts until a valid input
     * has been entered (>= 0).
     *
     * @param prompt The message to display.
     * @return A valid quantity/price value.
     */
    private static int promptInventoryValue(String prompt) {
        int value;
        while (true) {
            System.out.printf("%s: ", prompt);
            value = scan.nextInt();
            if (value < 0) {
                System.out.println("Invalid amount. Enter a value of 0 or more.");
                continue;
            }
            return value;
        }
    }

    /**
     * Display the inventory.
     *
     * @param lowOnly if {@code false}, it will only display items that are 5 or
     *                fewer in quantity.
     */
    private static void displayInventory(boolean lowOnly) {
        for (int i = 0; i < items.length; i++) {
            if (lowOnly && quantity[i] > 5) {
                continue;
            }
            System.out.printf("Item Name: %s, " +
                            "Quantity: %d, " +
                            "Price Per Piece: %.2f, " +
                            "Item Total Value: %.2f%n",
                    items[i], quantity[i], price[i], price[i] * quantity[i]
            );
        }
    }

    /**
     * Display all inventory.
     */
    private static void displayInventory() {
        displayInventory(false);
    }

    /**
     * Display low inventory.
     */
    private static void displayLowInventory() {
        displayInventory(true);
    }

    /**
     * Display total inventory value.
     */
    private static void displayTotalValue() {
        double val = 0;
        for (int i = 0; i < items.length; i++) {
            val += price[i] * quantity[i];
        }
        System.out.printf("Total value of items is: %.2f%n", val);
    }

    /**
     * Display highest/lowest value items.
     */
    private static void displayHighLowInventory() {
        double val, highestVal = price[0], lowestVal = price[0];
        String highestItem = items[0], lowestItem = items[0];
        for (int i = 1; i < items.length; i++) {
            val = price[i] * quantity[i];

            if (val > highestVal) {
                highestItem = items[i];
                highestVal = val;
            }
            if (val < lowestVal) {
                lowestItem = items[i];
                lowestVal = val;
            }
        }
        System.out.printf("Highest total value is %s at %.2f and " +
                        "lowest total value is %s at %.2f%n",
                highestItem, highestVal, lowestItem, lowestVal);
    }
}
