import java.util.ArrayList;

public class YearlyReport {
    ArrayList<Integer> monthsExpenses = new ArrayList<>();
    ArrayList<Integer> amountsExpenses = new ArrayList<>();
    ArrayList<Integer> monthsProfits = new ArrayList<>();
    ArrayList<Integer> amountsProfits = new ArrayList<>();

    void saveExpenses(int month, int amount) {
        monthsExpenses.add(month);
        amountsExpenses.add(amount);
    }
    void saveProfits(int month, int amount) {
        monthsProfits.add(month);
        amountsProfits.add(amount);
    }

    void findProfitEachMonth() {
        for (int i = 0; i < monthsExpenses.size(); i++) {
            System.out.println("Прибыль в " + monthsExpenses.get(i) + "-м месяце составляет: " + (amountsProfits.get(i) - amountsExpenses.get(i)));
        }
    }
    void findAverageExpense() {
        int sumExpenses = 0;
        for (int i = 0; i < amountsExpenses.size(); i++) {
            sumExpenses += amountsExpenses.get(i);
        }
        int averageExpense = sumExpenses / amountsExpenses.size();
        System.out.println("Средний расход за год составляет: " + averageExpense);
    }
    void findAverageProfit() {
        int sumProfits = 0;
        for (int i = 0; i < amountsProfits.size(); i++) {
            sumProfits += amountsProfits.get(i);
        }
        int averageProfit = sumProfits / amountsProfits.size();
        System.out.println("Средний расход за год составляет: " + averageProfit);
    }
}
