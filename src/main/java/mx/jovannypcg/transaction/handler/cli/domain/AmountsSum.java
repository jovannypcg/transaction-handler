package mx.jovannypcg.transaction.handler.cli.domain;

import com.google.gson.Gson;

public class AmountsSum {
    private int userId;
    private double sum;

    public AmountsSum(int userId, double sum) {
        this.userId = userId;
        this.sum = sum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
