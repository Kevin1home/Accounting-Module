package model;

/**
 * Represents a single financial transaction (profit or expense).
 */
public class Transaction {
    private final String name;
    private final int quantity;
    private final int unitPrice;
    public Transaction (String name, int quantity, int unitPrice) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }
}
