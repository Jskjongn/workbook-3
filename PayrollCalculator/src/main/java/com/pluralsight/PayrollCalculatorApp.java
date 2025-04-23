package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;

public class PayrollCalculatorApp {

    public static void main(String[] args) {

        try {
            // finds the file in the correct folders and creates file reader and buffered reader
            FileReader fileReader = new FileReader("src/main/resources/employees.csv");
            BufferedReader bufferReader = new BufferedReader(fileReader);

            // "gets rid of"/ reads header row 1 on the csv file which is all strings of data
            bufferReader.readLine();

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
            }
            // catch to stop the program and display if the file cant be found or type in wrong
        } catch (Exception e) {
            System.out.println("Cannot find the file!");
        }


    }

}
