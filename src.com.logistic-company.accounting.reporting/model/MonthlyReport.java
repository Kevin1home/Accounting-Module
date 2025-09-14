package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Stores and analyzes financial transactions for a single month.
 */
public class MonthlyReport {
    public final List<Transaction> transactionsExpenses = new ArrayList<>();
    public final List<Transaction> transactionsProfits = new ArrayList<>();

    public void saveExpenses(Transaction transaction) {
        transactionsExpenses.add(transaction);
    }
    public void saveProfits(Transaction transaction) {
        transactionsProfits.add(transaction);
    }

    public Map<String, Integer> findMaxProfitGood() {
        Map<String, Integer> returnMaxProfitGood = new HashMap<>();
        String maxProfitGood = "";
        int maxProfit = 0;

        for (Transaction transaction : transactionsProfits) {
            if (maxProfit < (transaction.getQuantity() * transaction.getUnitPrice())) {
                maxProfit = transaction.getQuantity() * transaction.getUnitPrice();
                maxProfitGood = transaction.getName();
            }
        }
        returnMaxProfitGood.put(maxProfitGood, maxProfit);
        return returnMaxProfitGood;
    }

    public Map<String, Integer> findMaxExpenseCost() {
        Map<String, Integer> returnMaxExpenseCost = new HashMap<>();
        String maxExpenseCost = "";
        int maxExpense = 0;

        for (Transaction transaction : transactionsExpenses) {
            if (maxExpense < (transaction.getQuantity() * transaction.getUnitPrice())) {
                maxExpense = transaction.getQuantity() * transaction.getUnitPrice();
                maxExpenseCost = transaction.getName();
            }
        }
        returnMaxExpenseCost.put(maxExpenseCost, maxExpense);
        return returnMaxExpenseCost;
    }
}
