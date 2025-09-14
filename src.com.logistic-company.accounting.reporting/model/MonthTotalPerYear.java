package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Saves monthly reports.
 */
public class MonthTotalPerYear {
    public final List<MonthlyReport> monthlyReports = new ArrayList<>();

    public void saveMonthlyReports(MonthlyReport monthlyReport){
        monthlyReports.add(monthlyReport);
    }
}
