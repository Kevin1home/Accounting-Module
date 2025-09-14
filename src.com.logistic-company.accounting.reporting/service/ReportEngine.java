package service;

import model.MonthTotalPerYear;
import model.MonthlyReport;
import model.Transaction;
import model.YearlyReport;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides reconciliation and validation between monthly and yearly reports.
 */
public class ReportEngine {
    private final MonthTotalPerYear monthTotalPerYear;
    private final YearlyReport yearlyReport;
    public ReportEngine(MonthTotalPerYear monthTotalPerYear, YearlyReport yearlyReport) {
        this.monthTotalPerYear = monthTotalPerYear;
        this.yearlyReport = yearlyReport;
    }

    public void compareReports() {
        List<Integer> monthlyExpSumsM = monthlySumOfExpensesInMonthlyReports();
        List<Integer> monthlyProfSumsM = monthlySumOfProfitsInMonthlyReports();
        List<Integer> monthlyExpSumsY = monthlySumOfExpensesInYearlyReports();
        List<Integer> monthlyProfSumsY = monthlySumOfProfitsInYearlyReports();

        if (monthlyExpSumsM.equals(monthlyExpSumsY)) {
            System.out.println("Monthly and yearly reports match in expenses.");
        }
        else {
            for (int i = 0; i < 3; i++) {
                if (!(monthlyExpSumsM.get(i).equals(monthlyExpSumsY.get(i)))) {
                    System.out.println("Monthly and yearly reports not match in expenses in month #" + (i+1));
                }
            }
        }
        if (monthlyProfSumsM.equals(monthlyProfSumsY)) {
            System.out.println("Monthly and yearly reports match in profit.");
        }
        else {
            for (int i = 0; i < 3; i++) {
                if (!(monthlyProfSumsM.get(i).equals(monthlyProfSumsY.get(i)))) {
                    System.out.println("Monthly and yearly reports not match in profit in month #" + (i+1));
                }
            }
        }
    }

    private List<Integer> monthlySumOfExpensesInMonthlyReports() {
        List<Integer> monthlyExpSumsM = new ArrayList<>(3);
        List<MonthlyReport> monthlyReports = monthTotalPerYear.monthlyReports;
        for (MonthlyReport monthlyReport : monthlyReports) {
            List<Transaction> transactionsExpenses = monthlyReport.transactionsExpenses; // model.Transaction (String name, int quantity, int unitPrice)
            int sumMonth = 0;
            for (Transaction transaction : transactionsExpenses) {
                sumMonth += (transaction.getQuantity() * transaction.getUnitPrice());
            }
            monthlyExpSumsM.add(sumMonth);
        }
        return monthlyExpSumsM;
    }
    private List<Integer> monthlySumOfProfitsInMonthlyReports() {
        List<Integer> monthlyProfSumsM = new ArrayList<>(3);
        List<MonthlyReport> monthlyReports = monthTotalPerYear.monthlyReports;
        for (MonthlyReport monthlyReport : monthlyReports) {
            List<Transaction> transactionsProfits = monthlyReport.transactionsProfits; // model.Transaction (String name, int quantity, int unitPrice)
            int sumMonth = 0;
            for (Transaction transaction : transactionsProfits) {
                sumMonth += transaction.getQuantity() * transaction.getUnitPrice();
            }
            monthlyProfSumsM.add(sumMonth);
        }
        return monthlyProfSumsM;
    }

    private List<Integer> monthlySumOfExpensesInYearlyReports() {
        List<Integer> monthlyExpSumsY = yearlyReport.amountsExpenses;
        return monthlyExpSumsY;
    }
    private List<Integer> monthlySumOfProfitsInYearlyReports() {
        List<Integer> monthlyProfSumsY = yearlyReport.amountsProfits;
        return monthlyProfSumsY;
    }
}
