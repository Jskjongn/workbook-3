package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class PayrollCalculatorApp {

    // creates scanner for user input
    static Scanner userScanner = new Scanner(System.in);

    public static void main(String[] args) {

        // prompts user for employee file name and the name of the new file to create (payroll-april-2025.csv)
        System.out.println("When enter file please ensure you include the file extension!\nExample: .csv");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.print("Enter the name of the employee file to process: ");
        String employeeFile = userScanner.nextLine();
        System.out.print("Enter the name of the payroll file to create: ");
        String newPayrollFile = userScanner.nextLine();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        try {
            // finds the file in the specific path and creates file reader and buffered reader scanners to read the file
            FileReader fileReader = new FileReader("src/main/resources/" + employeeFile);
            BufferedReader bufferReader = new BufferedReader(fileReader);

            // creates file in specific path to create the file writer and buffered writer scanners to create a file
            FileWriter fileWriter = new FileWriter("src/main/resources/" + newPayrollFile);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

            // "gets rid of"/ reads header row one on the csv file which is all strings of data
            bufferReader.readLine();

            // creates a header row one on the new csv file before displays the data
            bufferWriter.write("id" + "|" + "name" + "|" + "gross pay" + "\n");

            // variable to store buffered reader lines
            String employeeData;
            // reads all lines from file until it returns null
            while((employeeData = bufferReader.readLine()) != null){
                // splits the data from the csv file into parts by the pipe ( | )
                String[] dataParts = employeeData.split("\\|");

                // creates new Employee object from the constructor to assign the list of parts into the parameters
                Employee dataDetails = new Employee(Integer.parseInt(dataParts[0]), dataParts[1], Double.parseDouble(dataParts[2]), Double.parseDouble(dataParts[3]));

                // gets the gross pay which calculates the hours times pay rate
                double grossTotal = dataDetails.getGrossPay();

                // gets the rest of the data from the Class and assigns the correct parts to the correct %
                System.out.printf("Employee ID: %d, Employee name: %s, Number of hours worked: %.2f, Employee pay rate: $%.2f, Gross pay: $%.2f\n"
                        , dataDetails.getId(), dataDetails.getName(), dataDetails.getHoursWorked(), dataDetails.getPayRate(), grossTotal);

                // gets the data to create new lines in the new file that's created using the buffered writer and reformats it
                String payrollFile = dataDetails.getId() + "|" + dataDetails.getName() + "|" + dataDetails.getGrossPay() + "\n";
                // writes out the data details from the employees file
                bufferWriter.write(payrollFile);
            }

            // closes the buffer reader and writer scanners
            bufferReader.close();
            bufferWriter.close();

            // catch to run instead of stopping the program and display if the file cant be found or if file was typed in wrong
        } catch (Exception e) {
            System.out.println("Cannot find the file!");
        }

    }

}
