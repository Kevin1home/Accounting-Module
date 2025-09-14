package app;

import io.FileReader;
import model.*;
import service.ReportEngine;

import java.util.*;

/**
 * Entry point for the Accounting Module.
 *
 * A console-based system for reading, validating, and analyzing monthly and yearly financial reports.
 */
public class ReportingApp {
    private static final FileReader fileReader = new FileReader();
    private static final MonthTotalPerYear monthTotalPerYear = new MonthTotalPerYear();
    private static final YearlyReport yearlyReport = new YearlyReport();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Financial Reporting System!");

        while (true) {
            System.out.println("\nSelect an option:");
            printMenu();

            String inputCommand = scanner.next();
            int command = Integer.parseInt(inputCommand);

            switch (command) {
                case 1 -> loadMonthlyReports();
                case 2 -> loadYearlyReport();
                case 3 -> compareReports();
                case 4 -> printMonthlyReports();
                case 5 -> printYearlyReport();
                case 6 -> quitApp();
                default -> System.out.println("Unknown command. Try again.");
            }
        }
    }

    private static void loadMonthlyReports() {
        for (int i = 1; i <= 3; i++) {
            List<String> lines = fileReader.readFileContents("m.20210" + i + ".csv");
            if (lines.isEmpty()) continue;

            MonthlyReport monthlyReport = new MonthlyReport();
            for (int j = 1; j < lines.size(); j++) {
                String[] lineContents = lines.get(j).split(",");
                Transaction transaction = new Transaction(lineContents[0], Integer.parseInt(lineContents[2]),
                        Integer.parseInt(lineContents[3]));
                if (lineContents[1].equalsIgnoreCase("TRUE")) {
                    monthlyReport.saveExpenses(transaction); // saving expenses
                }
                else if (lineContents[1].equalsIgnoreCase("FALSE")) {
                    monthlyReport.saveProfits(transaction); // saving profits
                }
                else {
                    System.out.println("File error");
                    break;
                }
            }
            monthTotalPerYear.saveMonthlyReports(monthlyReport);
        }
        System.out.println("Monthly reports loaded successfully");
    }

    private static void loadYearlyReport() {
        List<String> lines = fileReader.readFileContents("y.2021.csv");
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] lineContents = line.split(",");
            if (lineContents[2].equals("true")) {
                yearlyReport.saveExpenses(Integer.parseInt(lineContents[0]),
                        Integer.parseInt(lineContents[1])); // saving expenses
            }
            else if (lineContents[2].equals("false")) {
                yearlyReport.saveProfits(Integer.parseInt(lineContents[0]),
                        Integer.parseInt(lineContents[1])); // saving profits
            }
            else {
                System.out.println("File error");
                break;
            }
        }
        System.out.println("Yearly report loaded successfully");
    }

    private static void compareReports() {
        if (monthTotalPerYear.monthlyReports.isEmpty() || yearlyReport.amountsExpenses.isEmpty()) {
            System.out.println("Monthly and/or yearly reports missing.");
            System.out.println("Please load both monthly and yearly reports first.");
        }
        else {
            ReportEngine reportEngine = new ReportEngine(monthTotalPerYear, yearlyReport);
            reportEngine.compareReports();
        }
    }

    private static void printMonthlyReports() {
        if (monthTotalPerYear.monthlyReports.isEmpty()) {
            System.out.println("No monthly reports loaded.");
        }
        else {
            for (int i = 0; i <3; i++) {
                System.out.println("Year 2021. Month " + (i+1) + ".");

                MonthlyReport monthlyReport = monthTotalPerYear.monthlyReports.get(i);

                Map<String, Integer> maxProfitGood = monthlyReport.findMaxProfitGood();
                System.out.println("Highest profit service: " + maxProfitGood.keySet());
                System.out.println("Amount: " + maxProfitGood.values());

                Map<String, Integer> maxExpense = monthlyReport.findMaxExpenseCost();
                System.out.println("Highest expense: " + maxExpense.keySet());
                System.out.println("Amount: " + maxExpense.values());
            }
        }
    }

    private static void printYearlyReport() {
        if (yearlyReport.amountsExpenses.isEmpty()) {
            System.out.println("No yearly report loaded.");
        }
        else {
            System.out.println("Year 2021.");
            yearlyReport.findProfitEachMonth();
            yearlyReport.findAverageExpense();
            yearlyReport.findAverageProfit();
        }
    }

    private static void printMenu() {
        System.out.println("1. Load all monthly reports");
        System.out.println("2. Load yearly report");
        System.out.println("3. Reconcile monthly and yearly reports");
        System.out.println("4. Print monthly reports summary");
        System.out.println("5. Print yearly report summary");
        System.out.println("6. Exit");
        System.out.print("> ");
    }

    private static void quitApp() {
        System.out.println("Type EXIT to confirm quitting: ");
        System.out.print("> ");
        String exit = scanner.next();
        if (exit.equalsIgnoreCase("EXIT")) {
            System.out.println("Exiting program...");
            System.exit(0);
        }
        else {
            System.out.println("Returning to program.");
        }
    }
}
