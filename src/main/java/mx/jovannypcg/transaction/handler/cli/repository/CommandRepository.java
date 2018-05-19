package mx.jovannypcg.transaction.handler.cli.repository;

import mx.jovannypcg.transaction.handler.cli.domain.Transaction;

import java.util.List;

public interface CommandRepository<T extends Transaction> {
    T save(T transaction);
    List<T> findAll();
    List<T> findByUserId(int userId);
    List<T> findByUserIdAndTransactionId(int userId, String transactionId);
    double sumAmountsByUserId(int userId);
}
