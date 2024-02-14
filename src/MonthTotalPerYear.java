import java.util.ArrayList;

public class MonthTotalPerYear {
    ArrayList<MonthlyReport> monthlyReports = new ArrayList<>();

    void saveMonthlyReports(MonthlyReport monthlyReport){
        monthlyReports.add(monthlyReport);
    }
}
