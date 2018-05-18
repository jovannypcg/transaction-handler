package mx.jovannypcg.transaction.handler.cli.command;

/**
 * Functional interface to represent the command design pattern.
 *
 * @author Jovanny Cruz.
 */
@FunctionalInterface
public interface Command {
    void exec(String... args);
}
