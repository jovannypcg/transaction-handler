package mx.jovannypcg.transaction.handler.cli.command.impl;

import mx.jovannypcg.transaction.handler.cli.command.Command;
import mx.jovannypcg.transaction.handler.cli.domain.AmountsSum;
import mx.jovannypcg.transaction.handler.cli.domain.Transaction;
import mx.jovannypcg.transaction.handler.cli.repository.CommandRepository;
import mx.jovannypcg.transaction.handler.cli.validator.TransactionArgumentValidator;
import org.springframework.stereotype.Component;

@Component
public class TransactionCommand implements Command {
    private CommandRepository<Transaction> commandRepository;

    public TransactionCommand(CommandRepository<Transaction> commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Override
    public void exec(String... args) {
        TransactionArgumentValidator validator = new TransactionArgumentValidator(args);

        if (!validator.argumentListSizeIsValid()) {
            System.out.println("Not enough or too many arguments (min: " +
                    TransactionArgumentValidator.MINIMUM_ARGS_SIZE + " , max: " +
                    TransactionArgumentValidator.MAXIMUM_ARGS_SIZE + ")");
        } else if (validator.isListTask()) {
            int userId = Integer.valueOf(args[TransactionArgumentValidator.USER_ID_INDEX_IN_ARGS]);

            list(userId);
        } else if (validator.isSumTask()) {
            int userId = Integer.valueOf(args[TransactionArgumentValidator.USER_ID_INDEX_IN_ARGS]);

            sum(userId);
        } else if(validator.isShowTask()) {
            int userId = Integer.valueOf(args[TransactionArgumentValidator.USER_ID_INDEX_IN_ARGS]);
            String transactionId = args[TransactionArgumentValidator.TRANSACTION_ID_INDEX_IN_ARGS];

            show(userId, transactionId);
        } else if (validator.isAddTask()) {
            add(args);
        } else {
            System.out.println("Usage: java -jar transaction-handler.jar <user_id> " +
                    "[transaction_id] [sum] [list] [add <transaction_json>]");
        }
    }

    protected void add(String[] args) {
        Transaction transaction = new Transaction();
        transaction.setAmount(3.2f);
        transaction.setDate("18/05/2018");
        transaction.setUserId(235);
        transaction.setDescription("Simple transaction to be saved");

        Transaction t = commandRepository.save(transaction);
        System.out.println(t);
    }

    protected void list(int userId) {
        commandRepository.findByUserId(userId).forEach(System.out::println);
    }

    protected void listAll() {
        commandRepository.findAll().forEach(System.out::println);
    }

    protected void show(int userId, String transactionId) {
        commandRepository.findByUserIdAndTransactionId(userId, transactionId).forEach(System.out::println);
    }

    protected void sum(int userId) {
        double sum = commandRepository.sumAmountsByUserId(userId);

        System.out.println(new AmountsSum(userId, sum));
    }
}
