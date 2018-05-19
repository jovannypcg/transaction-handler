package mx.jovannypcg.transaction.handler.cli.domain;

import com.google.gson.Gson;

import java.util.Objects;

public class Transaction {
    private int userId;
    private String date;
    private String description;
    private double amount;
    private String transactionId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Transaction)) {
            return false;
        }

        Transaction that = (Transaction) obj;

        return Objects.equals(date, that.date) &&
                Objects.equals(description, that.description) &&
                Objects.equals(transactionId, that.transactionId) &&
                userId == that.userId &&
                amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, date, description, amount, transactionId);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
