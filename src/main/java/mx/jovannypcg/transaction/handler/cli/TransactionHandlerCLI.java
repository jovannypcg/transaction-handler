package mx.jovannypcg.transaction.handler.cli;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TransactionHandlerCLI implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println(args.length);
    }
}
