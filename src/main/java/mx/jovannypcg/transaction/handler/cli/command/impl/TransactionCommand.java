package mx.jovannypcg.transaction.handler.cli.command.impl;

import mx.jovannypcg.transaction.handler.cli.command.Command;
import mx.jovannypcg.transaction.handler.cli.validator.TransactionArgumentValidator;
import org.springframework.stereotype.Component;

@Component
public class TransactionCommand implements Command {
    @Override
    public void exec(String... args) {
        TransactionArgumentValidator validator = new TransactionArgumentValidator(args);

        if (!validator.argumentListSizeIsValid()) {
            System.out.println("Not enough or too many arguments (min: " +
                    TransactionArgumentValidator.MINIMUM_ARGS_SIZE + " , max: " +
                    TransactionArgumentValidator.MAXIMUM_ARGS_SIZE + ")");
        } else if (validator.isListTask()) {
            System.out.println("Executing list...");
        } else if (validator.isSumTask()) {
            System.out.println("Executing sum...");
        } else if (validator.isAddTask()) {
            System.out.println("Executing add...");
        } else {
            System.out.println("Usage: java -jar transaction-handler.jar <user_id> " +
                    "[transaction_id] [sum] [list] [add <transaction_json>]");
        }
    }
}
