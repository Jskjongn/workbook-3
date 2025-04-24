package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class StoreApp {

    public static void main(String[] args) {

        // calls get inventory method to get products
        ArrayList<Product> inventory = getInventory();

        // creates scanner for user input
        Scanner scanner = new Scanner(System.in);

        // displays what there is
        System.out.println("We carry the following inventory: ");

        // loops over products to display
        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);
            System.out.printf("id: %d %s - Price: $%.2f\n",
                    p.getId(), p.getName(), p.getPrice());
        }
    }

    // getInventory method that creates an array list of products to return
    public static ArrayList<Product> getInventory() {
        ArrayList<Product> inventory = new ArrayList<Product>();

        try {

            // creates file reader and buffered reader to read the lines of the file
            FileReader inventoryList = new FileReader("src/main/resources/inventory.csv");
            BufferedReader bufferReader = new BufferedReader(inventoryList);

            // variable to store the lines of the file it reads
            String inventoryFile;

            // reads the files until there are none left to read and returns null
            while ((inventoryFile = bufferReader.readLine()) != null) {
                // splits the file at the pipe | and puts them into a list
                String[] inventoryParts = inventoryFile.split("\\|");

                // assigns the parts of the list using the product constructor
                Product inventoryDetails = new Product(Integer.parseInt(inventoryParts[0]), inventoryParts[1], Float.parseFloat(inventoryParts[2]));

                // adds the details into the inventory array list
                inventory.add(inventoryDetails);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return inventory;
    }
}
