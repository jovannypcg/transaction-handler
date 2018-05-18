package mx.jovannypcg.transaction.handler.cli.exception;

/**
 * Meant to be thrown when the list of arguments passed to
 * {@link mx.jovannypcg.transaction.handler.cli.TransactionHandlerCLI} is not valid
 * based on {@link mx.jovannypcg.transaction.handler.cli.validator.ArgumentValidator}
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
