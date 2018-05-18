package mx.jovannypcg.transaction.handler.cli.exception;

import mx.jovannypcg.transaction.handler.cli.validator.TransactionArgumentValidator;

/**
 * Meant to be thrown when the list of arguments passed to
 * {@link mx.jovannypcg.transaction.handler.cli.TransactionHandlerCLI} is not valid
 * based on {@link TransactionArgumentValidator}
 *
 * @author Jovanny Cruz
 */
public class ArgumentListNotValidException extends Exception {
    private ArgumentListNotValidException(String message) {
        super(message);
    }

    public static ArgumentListNotValidException withMessage(String message) {
        return new ArgumentListNotValidException(message);
    }
}
