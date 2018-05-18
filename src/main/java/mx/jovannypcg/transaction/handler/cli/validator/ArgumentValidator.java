package mx.jovannypcg.transaction.handler.cli.validator;

/**
 * Class to validate the arguments passed to {@link mx.jovannypcg.transaction.handler.cli.TransactionHandlerCLI}.
 *
 * This class provides a way to verify that the number of arguments is correct, the format of a given argument is
 * the expected or if a non-valid task for the CLI is passed.
 *
 * @author Jovanny Cruz
 */
public class ArgumentValidator {
    /**
     * Minimum args expected: user_id and another one that might be transaction_id or
     * one of the expected tasks: list, sum, add
     */
    private static final int MINIMUM_ARGS_SIZE = 2;

    /**
     * Maximum args expected.
     * e.g.: $ transaction-handler [user_id] add [transaction_json]
     */
    private static final int MAXIMUM_ARGS_SIZE = 3;

    public static boolean argumentListSizeIsValid(String... args) {
        return args.length >= MINIMUM_ARGS_SIZE && args.length <= MAXIMUM_ARGS_SIZE;
    }
}
