/*
 * Assignment 07
 * Description: Coffee shop inventory manager - refactored
 * Name: Kullathon "Mos" Sitthisarnwattanachai
 * Teammate: Amber Hartigan
 * ID: 921425216
 * Class: CSC 210-03
 * Semester: Fall 2020
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Refactored from ASMT-06.
 * <p>
 * See also: https://github.com/mosguinz-csc210-03/CSC210Asmt06
 */
public class CoffeeShopAccountInventoryManagerNew {

    static final Scanner scan = new Scanner(System.in);
    /* Part (1)(a) */
    final static String[] items = {"cups", "coffee beans", "lids", "towel", "sleeves"};
    /* Part (1)(b) */
    final static String[] labels = {"quantity", "price"};
    static int[] quantity, price;
    /* Part (1)(c) */
    static double[][] values = new double[items.length][labels.length];

    public static void main(String[] args) {
        getMenu();
    }

    /**
     * Performs the program's execution loop.
     * <p>
     * Renamed to getMenu to satisfy (2)(a).
     */
    public static void getMenu() {
        setupInventory();

        while (true) {
            switch (promptAction()) {
                case 1:
                    printInventory();
                    break;
                case 2:
                    checkInventory();
                    break;
                case 3:
                    displayTotalValue();
                    break;
                case 4:
                    minMaxInventory();
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
     * <p>
     * This method fulfills (1)(d).
     */
    private static void setupInventory() {
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < labels.length; j++) {
                values[i][j] = promptInventoryValue(labels[j], items[i]);
            }
        }

        for (int j = 0; j < labels.length; j++) {
            double[] thisArr = new double[items.length];
            for (int i = 0; i < items.length; i++) {
                thisArr[i] = values[i][j];
            }
            System.out.printf("Item %s: %s%n", labels[j], Arrays.toString(thisArr));
        }
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
     * @param valType The type of value to enter; either "quantity" or "price".
     * @param item    The name of the item.
     * @return A valid quantity/price value.
     */
    private static double promptInventoryValue(String valType, String item) {
        double value;
        while (true) {
            System.out.printf("Enter the %s of %s: ", valType, item);
            value = scan.nextDouble();
            if (value < 0) {
                System.out.println("Invalid amount. Enter a value of 0 or more.");
                continue;
            }
            System.out.printf("Recorded %s of %s as %.2f.%n%n", valType, item, value);
            return value;
        }
    }

    /**
     * Display the inventory. Prints in the format specified in (2)(b).
     */
    private static void printInventory() {
        for (int i = 0; i < labels.length; i++) {
            System.out.println(labels[i].substring(0, 1).toUpperCase() + labels[i].substring(1));
            for (int j = 0; j < items.length; j++) {
                System.out.printf("Item Name %s : %.2f%n", items[j], values[j][i]);
            }
        }
    }


    /**
     * Display low inventory. Prints in the format specified in (2)(c).
     * <p>
     * Probably could've unrolled the loop here to make it look nicer. However,
     * I've left it as-is to make sure that it's easily expandable, should
     * {@link #labels} ever grow.
     */
    private static void checkInventory() {
        for (int i = 0; i < labels.length; i++) {
            String valType = labels[i];
            if (!valType.equalsIgnoreCase("quantity")) {
                continue;
            }

            boolean hasLow = false;
            for (int j = 0; j < items.length; j++) {
                double val = values[j][i];
                if (val <= 5) {
                    System.out.printf("Item Name %s has low quantity: %.2f%n", items[j], val);
                    hasLow = true;
                }
            }

            if (!hasLow) {
                System.out.println("All stocked up! No items less than 5 in quantity.");
            }
        }
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
     * <p>
     * Here, we're just gonna assume that the zeroth and the first index of each
     * elements in {@link #values} are the quantity and prices, respectively.
     */
    private static void minMaxInventory() {
        double invalid = 1 / 0.0;
        double highestVal = invalid, lowestVal = invalid;
        ArrayList<String> highestItems = new ArrayList<String>();
        ArrayList<String> lowestItems = new ArrayList<String>();
        for (int i = 0; i < items.length; i++) {
            double val = values[i][0] * values[i][1];
            String item = items[i];

            // Set starting values
            if (highestVal == invalid) {
                highestVal = lowestVal = val;
                highestItems.add(item);
                lowestItems.add(item);
                continue;
            }

            if (val > highestVal) {
                highestVal = val;
                highestItems.clear();
                highestItems.add(item);
            } else if (val == highestVal) {
                highestItems.add(item);
            }

            if (val < lowestVal) {
                lowestVal = val;
                lowestItems.clear();
                lowestItems.add(item);
            } else if (val == lowestVal) {
                lowestItems.add(item);
            }
        }
        System.out.printf("Highest total value items are %s at $%.2f.%n" +
                        "Lowest total value items are %s at $%.2f%n",
                String.join(", ", highestItems),
                highestVal,
                String.join(", ", lowestItems),
                lowestVal);
    }
}
