package mx.jovannypcg.transaction.handler.cli.repository.impl;

import com.google.gson.Gson;
import mx.jovannypcg.transaction.handler.cli.domain.Transaction;
import mx.jovannypcg.transaction.handler.cli.repository.TransactionRepository;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class FSTransactionRepository implements TransactionRepository<Transaction> {
    private Gson gson;
    private PrintWriter transactionWriter;
    private BufferedReader transactionReader;

    public FSTransactionRepository(Gson gson, PrintWriter transactionWriter, BufferedReader transactionReader) {
        this.gson = gson;
        this.transactionWriter = transactionWriter;
        this.transactionReader = transactionReader;
    }

    @Override
    public Transaction save(Transaction transaction) {
        transaction.setTransactionId(UUID.randomUUID().toString());

        String transactionToStore = gson.toJson(transaction);
        transactionWriter.println(transactionToStore);

        return transaction;
    }

    @Override
    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<>();
        String currentTransactionString;

        try {
            while ((currentTransactionString = transactionReader.readLine()) != null) {
                Transaction currentTransaction = gson.fromJson(currentTransactionString, Transaction.class);
                transactions.add(currentTransaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    @Override
    public List<Transaction> findByUserId(int userId) {
        return findAll()
                .stream()
                .filter(transaction -> transaction.getUserId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> findByUserIdAndTransactionId(int userId, String transactionId) {
        return findAll()
                .stream()
                .filter(transaction -> {
                    return transaction.getUserId() == userId && transactionId.equals(transaction.getTransactionId());
                })
                .collect(Collectors.toList());
    }
}
