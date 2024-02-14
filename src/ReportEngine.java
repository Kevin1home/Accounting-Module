import java.util.ArrayList;

public class ReportEngine {
    MonthTotalPerYear monthTotalPerYear;
    YearlyReport yearlyReport;
    ReportEngine(MonthTotalPerYear monthTotalPerYear, YearlyReport yearlyReport) {
        this.monthTotalPerYear = monthTotalPerYear;
        this.yearlyReport = yearlyReport;
    }

    void compareReports() {
        ArrayList<Integer> monthlyExpSumsM = monthlySumOfExpensesInMonthlyReports();
        ArrayList<Integer> monthlyProfSumsM = monthlySumOfProfitsInMonthlyReports();
        ArrayList<Integer> monthlyExpSumsY = monthlySumOfExpensesInYearlyReports();
        ArrayList<Integer> monthlyProfSumsY = monthlySumOfProfitsInYearlyReports();

        if (monthlyExpSumsM.equals(monthlyExpSumsY)) {
            System.out.println("Месячные и годовой отчёты сходятся по сумме расходов");
        }
        else {
            for (int i = 0; i < 3; i++) {
                if (!(monthlyExpSumsM.get(i).equals(monthlyExpSumsY.get(i)))) {
                    System.out.println("Месячные и годовой отчёты не сходятся по сумме расходов в " + (i+1) + "-м месяце");
                }
            }
        }
        if (monthlyProfSumsM.equals(monthlyProfSumsY)) {
            System.out.println("Месячные и годовые отчёты сходятся по доходам");
        }
        else {
            for (int i = 0; i < 3; i++) {
                if (!(monthlyProfSumsM.get(i).equals(monthlyProfSumsY.get(i)))) {
                    System.out.println("Месячные и годовой отчёты не сходятся по доходам в " + ++i + "-м месяце");
                }
            }
        }
    }

    ArrayList<Integer> monthlySumOfExpensesInMonthlyReports() {
        ArrayList<Integer> monthlyExpSumsM = new ArrayList<>(3);
        ArrayList<MonthlyReport> monthlyReports = monthTotalPerYear.monthlyReports;
        for (MonthlyReport monthlyReport : monthlyReports) {
            ArrayList<Transaction> transactionsExpenses = monthlyReport.transactionsExpenses; // Transaction (String name, int quantity, int unitPrice)
            int sumMonth = 0;
            for (Transaction transaction : transactionsExpenses) {
                sumMonth += (transaction.quantity * transaction.unitPrice);
            }
            monthlyExpSumsM.add(sumMonth);
        }
        return monthlyExpSumsM;
    }
    ArrayList<Integer> monthlySumOfProfitsInMonthlyReports() {
        ArrayList<Integer> monthlyProfSumsM = new ArrayList<>(3);
        ArrayList<MonthlyReport> monthlyReports = monthTotalPerYear.monthlyReports;
        for (MonthlyReport monthlyReport : monthlyReports) {
            ArrayList<Transaction> transactionsProfits = monthlyReport.transactionsExpenses; // Transaction (String name, int quantity, int unitPrice)
            int sumMonth = 0;
            for (Transaction transaction : transactionsProfits) {
                sumMonth += transaction.quantity * transaction.unitPrice;
            }
            monthlyProfSumsM.add(sumMonth);
        }
        return monthlyProfSumsM;
    }

    ArrayList<Integer> monthlySumOfExpensesInYearlyReports() {
        ArrayList<Integer> monthlyExpSumsY = yearlyReport.amountsExpenses;
        return monthlyExpSumsY;
    }
    ArrayList<Integer> monthlySumOfProfitsInYearlyReports() {
        ArrayList<Integer> monthlyProfSumsY = yearlyReport.amountsProfits;
        return monthlyProfSumsY;
    }
}
