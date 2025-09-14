package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores and analyzes financial results for an entire year.
 */
public class YearlyReport {
    private final List<Integer> monthsExpenses = new ArrayList<>();
    private final List<Integer> monthsProfits = new ArrayList<>();
    public final List<Integer> amountsExpenses = new ArrayList<>();
    public final List<Integer> amountsProfits = new ArrayList<>();

    public void saveExpenses(int month, int amount) {
        monthsExpenses.add(month);
        amountsExpenses.add(amount);
    }
    public void saveProfits(int month, int amount) {
        monthsProfits.add(month);
        amountsProfits.add(amount);
    }

    public void findProfitEachMonth() {
        for (int i = 0; i < monthsExpenses.size(); i++) {
            System.out.println("Profit in month #" + monthsExpenses.get(i) + " is: " + (amountsProfits.get(i) - amountsExpenses.get(i)));
        }
    }
    public void findAverageExpense() {
        int sumExpenses = 0;
        for (Integer amountsExpens : amountsExpenses) {
            sumExpenses += amountsExpens;
        }
        int averageExpense = sumExpenses / amountsExpenses.size();
        System.out.println("Avarage expenses per Year is: " + averageExpense);
    }
    public void findAverageProfit() {
        int sumProfits = 0;
        for (int i = 0; i < amountsProfits.size(); i++) {
            sumProfits += amountsProfits.get(i);
        }
        int averageProfit = sumProfits / amountsProfits.size();
        System.out.println("Avarage profit per Year is: " + averageProfit);
    }
}
