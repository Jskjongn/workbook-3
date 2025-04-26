package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class StoreApp {

    // creates scanner for user input
    static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        // calls get inventory method to get products
        ArrayList<Product> inventory = getInventory();

        boolean appRunning = true;

        while (appRunning) {

            // calls main menu method to store to use for switch
            int userChoice = mainMenu();

            switch (userChoice) {
                case 1:
                    // calls loop method to display all products in inventory
                    listOfProducts(inventory);

                    break;
                case 2:
                    // calls methods of grabbing ID from user and then displaying the product by ID
                    int productID = productsByID();
                    listOfProductsID(inventory, productID);

                    break;
                case 3:
                    // calls method of price range and displays inventory based on the range
                    priceRange(inventory);

                    break;
                case 4:
                    // calls method to add a new product to inventory
                    addProduct(inventory);

                    break;
                case 5:
                    // stops loop if user chooses to exit
                    System.out.println("==============================================");
                    System.out.println("You have quit the Store App, have a nice day!");
                    appRunning = false;

                    break;
                default:
                    // user didn't choose in between 1-5
                    System.out.println("Invalid choice - Please Choose Numbers 1-5! : )");
            }
        }

    }

    // displays main menu
    public static int mainMenu() {

        // asks user what they want to do and enter a number to do something
        System.out.println("==============================================");
        System.out.println("What do you want to do?");
        System.out.println("\t1-List all products");
        System.out.println("\t2-Lookup a product by its id");
        System.out.println("\t3-Find all products within a price range");
        System.out.println("\t4-Add a new product");
        System.out.println("\t5-Quit the application");
        System.out.print("Enter command: ");

        return userInput.nextInt();
    }

    // loops over all the products to display
    public static void listOfProducts(ArrayList<Product> inventory){

        System.out.println("==============================================");
        // loops over products to display
        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);
            System.out.printf("id: %d %s - Price: $%.2f\n",
                    p.getId(), p.getName(), p.getPrice());
        }

    }

    // searches for product by ID
    public static int productsByID(){

        int productID = 0;

        while (true){
            // asks user for the product ID to search for
            System.out.println("Please enter the product ID you wish to search for: ");

            // if the user's input has an integer then it puts their number into the product ID
            if (userInput.hasNextInt()){
                productID = userInput.nextInt();
                break;
            } else {
                System.out.println("Only enter a product ID that contains numbers!\n");
                userInput.next();
            }
        }
        return productID;
    }

    // displays the product from the ID the user entered
    public static void listOfProductsID(ArrayList<Product> inventory, int id){

        System.out.println("\nHere are products with a matching ID of " + id + " listed below:");

        // loops through the inventory and if the ID the user entered equals
        // one of the IDs in the inventory list then it gets the details to display
        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);
            if (p.getId() == id) {
                System.out.printf("id: %d %s - Price: $%.2f\n",
                        p.getId(), p.getName(), p.getPrice());
            }
        }

    }

    // displays the price range
    public static void priceRange(ArrayList<Product> inventory){

        System.out.println("==============================================");

        // asks user for price range and stores the user input
        System.out.println("Please enter your price range: ");
        float range = userInput.nextFloat();

        // loops through inventory and displays the products that's price is less than the price range
        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);
            if (p.getPrice() < range) {
                System.out.printf("id: %d %s - Price: $%.2f\n",
                        p.getId(), p.getName(), p.getPrice());
            }

        }

    }

    // adds new product to inventory and displays it
    public static void addProduct(ArrayList<Product> inventory){

        //try and catch for filer and buffered writer
        try {
            // file and buffered writer to now write to the inventory and append new products to the list
            FileWriter addingProduct = new FileWriter("src/main/resources/inventory.csv", true);
            BufferedWriter bufferWriter = new BufferedWriter(addingProduct);

            System.out.println("==============================================");

            // asks user to enter product details and stores the user input
            System.out.println("Please enter the details of the product below:");
            System.out.print("Product ID: ");
            int productID = userInput.nextInt();
            userInput.nextLine();

            System.out.print("Product name: ");
            String productName = userInput.nextLine();

            System.out.print("Product Price: ");
            float productPrice = userInput.nextFloat();
            userInput.nextLine();

            // takes user input and concatenates them together with pipes to write to the file
            bufferWriter.write("\n" + productID + "|" + productName + "|" + productPrice);

            // displays new product that was added
            System.out.println("\nNew Product: " + productID + "|" + productName + "|" + productPrice + " was successfully added!");

            // closes buffered writer
            bufferWriter.close();

            // to reload inventory memory to display the new product to the inventory list
            inventory.clear();
            inventory.addAll(getInventory());

            // displays list of inventory with new product added
            listOfProducts(inventory);

        } catch (Exception e) {
            System.out.println("New Product was not successfully added " + e.getMessage());
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
            System.out.println("Could not find file " + e.getMessage());
        }

        return inventory;
    }
}