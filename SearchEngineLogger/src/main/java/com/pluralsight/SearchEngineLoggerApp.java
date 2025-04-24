package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SearchEngineLoggerApp {

    // creates and formats the time stamps for the logger
    static DateTimeFormatter timeStampFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm:ss");

    // creates scanner for user input
    static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        // starts log file with launch when it starts up
        logger("launch");

        // variable to keep the app running or not
        boolean appRunning = true;

        // loop to keep app running
        while(appRunning){

            // displays question to user and stores their input
            System.out.println("Welcome to Search Engine App -- Happy searching!");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Enter a search term (X to exit): ");
            String searchTerm = userInput.nextLine();

            // if the user enters X then it stops the app by setting it to false and logging the exit
            if (searchTerm.equalsIgnoreCase("X")){
                logger("exit");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Search Engine Exited -- Have a nice day!");
                appRunning = false;
            } else {
                // logs the users input by adding the search in front when the user enters anything
                logger("search : " + searchTerm);
            }

        }

    }

    // method for logging the actions of the user
    public static void logger(String loggingActions){

        // tries to write to file
        try{

            // creates file writer and set to true so it continues to write to file not override it
            FileWriter logFile = new FileWriter("src/main/resources/logs.txt", true);
            // now writes to the file in a buffer
            BufferedWriter buffer = new BufferedWriter(logFile);

            //creates the current date and time
            LocalDateTime timeStamp = LocalDateTime.now();
            // creates line to write to logs file by concatenating the timestamp with the action
            buffer.write(timeStamp.format(timeStampFormatter) + " " +  loggingActions);

            // adds new line to file
            buffer.newLine();
            // closes buffer to write to file
            buffer.close();

        }
        // displays error if something happens when writing to the file
        catch (Exception e){
            System.out.println("Could not write to file: " + e.getMessage());
        }

    }

}
