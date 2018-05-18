package mx.jovannypcg.transaction.handler.cli;

import mx.jovannypcg.transaction.handler.cli.command.impl.TransactionCommand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TransactionHandlerCLI implements CommandLineRunner {
    private TransactionCommand transactionCommand;

    public TransactionHandlerCLI(TransactionCommand transactionCommand) {
        this.transactionCommand = transactionCommand;
    }

    @Override
    public void run(String... args) {
        transactionCommand.exec(args);
    }
}
