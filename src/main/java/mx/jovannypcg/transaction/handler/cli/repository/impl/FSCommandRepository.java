package mx.jovannypcg.transaction.handler.cli.repository.impl;

import com.google.gson.Gson;
import mx.jovannypcg.transaction.handler.cli.domain.Transaction;
import mx.jovannypcg.transaction.handler.cli.repository.CommandRepository;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class FSCommandRepository implements CommandRepository<Transaction> {
    private Gson gson;
    private PrintWriter transactionWriter;
    private BufferedReader transactionReader;

    public FSCommandRepository(Gson gson, PrintWriter transactionWriter, BufferedReader transactionReader) {
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
        return transactionReader
                .lines()
                .map(transactionLine -> gson.fromJson(transactionLine, Transaction.class))
                .collect(Collectors.toList());
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

    @Override
    public double sumAmountsByUserId(int userId) {
        return findByUserId(userId)
                .stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
}
