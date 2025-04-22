package com.pluralsight;

import java.util.Scanner;

public class FamousQuotes {

    // static scanner to take in user input anywhere in class
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // string array of 10 quotes
        String[] quotes = {
                "\"We suffer more often in imagination than in reality.\" – Seneca",
                "\"It is not things themselves that disturb us, but our opinions about them.\" – Epictetus",
                "\"The happiness of your life depends upon the quality of your thoughts.\" – Marcus Aurelius",
                "\"He who is brave is free.\" – Seneca",
                "\"How much time he gains who does not look to see what his neighbor says or does or thinks.\" – Marcus Aurelius",
                "\"Don’t explain your philosophy. Embody it.\" – Epictetus",
                "\"Waste no more time arguing what a good man should be. Be one.\" – Marcus Aurelius",
                "\"The greatest wealth is to live content with little.\" – Epictetus",
                "\"If you want to improve, be content to be thought foolish and stupid.\" – Epictetus",
                "\"The best revenge is not to be like your enemy.\" – Marcus Aurelius"
        };

        // variable to loop the while
        boolean askAgain = true;

        // while loop to continue loop if user chooses to
        while (askAgain) {
            try {
                // asks user to enter a number 1-10
                System.out.println("Select a number between 1-10: ");
                System.out.println("-----------------------------------------");

                // stores user input to print out the number quote they entered and subtracted by 1
                // since array is 0-9, user chooses 1 or 10 its actually 0 or 9
                int quoteChoice = scanner.nextInt();
                System.out.println(quotes[quoteChoice - 1]);

                // asks if they want another quote and prompts them with either yes or no
                System.out.println("-----------------------------------------");
                System.out.println("Do you want to see another Stoic saying?");
                System.out.println("Y - More wisdom");
                System.out.println("N - You have enough wisdom");
                System.out.println("-----------------------------------------");

                // eats leftover
                scanner.nextLine();

                // stores answer to yes or no question
                String anotherQuote = scanner.nextLine();

                // if user chose yes (Y) then it restarts the loop to ask user again what quote they want
                if (!anotherQuote.equalsIgnoreCase("Y")) {
                    askAgain = false;
                }

                // If the number the user gave was not between 1-10 displays error message
            } catch (Exception e) {
                System.out.println("Sorry there's only 10 quotes, please choose again between 1-10!");
            }

        }

    }

}