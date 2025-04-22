package com.pluralsight;

import java.io.FileInputStream;
import java.util.Scanner;

public class BedtimeStories {

    // creates scanner for user input
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // asks user what bedtime story they want and lists file names
        System.out.println("What bedtime story would you like to read?");
        System.out.print("- goldilocks.txt\n- hansel_and_gretel.txt\n- mary_had_a_little_lamb.txt\n");

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        System.out.print("Enter story name including the (.txt): ");

        // stores the file name user chose
        String userChoice = scanner.nextLine();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // try/catch to use for FileInputStream to read the file
        try {
            // creates file input stream to bring file into the code
            FileInputStream files = new FileInputStream("src/main/resources/" + userChoice);
            // creates scanner for reading out the file
            Scanner bedtimeStoryName = new Scanner(files);

            // line number to count lines along story
            int lineNumber = 1;

            // reads the file out until there is no lines left
            while(bedtimeStoryName.hasNextLine()){
                String story = bedtimeStoryName.nextLine();
                // prints out line number and the text on that line in the story
                System.out.println(lineNumber + " - " + story);
                // increments line number by 1
                lineNumber++;

            }

        }
        // if file was wrong or entered a different file name would catch and display error message
        catch (Exception e) {
            System.out.println("Story was not found, please make sure there " +
                    "is correcting spelling, underscores, and follows the .txt file extension!");
        }

    }

}
