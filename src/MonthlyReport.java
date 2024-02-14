import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    ArrayList<Transaction> transactionsExpenses = new ArrayList<>();
    ArrayList<Transaction> transactionsProfits = new ArrayList<>();

    void saveExpenses(Transaction transaction) {
        transactionsExpenses.add(transaction);
    }
    void saveProfits(Transaction transaction) {
        transactionsProfits.add(transaction);
    }

    HashMap<String, Integer> findMaxProfitGood() {
        HashMap<String, Integer> returnMaxProfitGood = new HashMap<>();
        String maxProfitGood = "";
        int maxProfit = 0;

        for (Transaction transaction : transactionsProfits) {
            if (maxProfit < (transaction.quantity * transaction.unitPrice)) {
                maxProfit = transaction.quantity * transaction.unitPrice;
                maxProfitGood = transaction.name;
            }
        }
        returnMaxProfitGood.put(maxProfitGood, maxProfit);
        return returnMaxProfitGood;
    }

    HashMap<String, Integer> findMaxExpenseCost() {
        HashMap<String, Integer> returnMaxExpenseCost = new HashMap<>();
        String maxExpenseCost = "";
        int maxExpense = 0;

        for (Transaction transaction : transactionsExpenses) {
            if (maxExpense < (transaction.quantity * transaction.unitPrice)) {
                maxExpense = transaction.quantity * transaction.unitPrice;
                maxExpenseCost = transaction.name;
            }
        }
        returnMaxExpenseCost.put(maxExpenseCost, maxExpense);
        return returnMaxExpenseCost;
    }

}
