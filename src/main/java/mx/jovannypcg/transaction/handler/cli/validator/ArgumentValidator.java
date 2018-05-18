package mx.jovannypcg.transaction.handler.cli.validator;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

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

    /**
     * Verifies if the given <em>args</em> has a length between <em>MINIMUM_ARGS_SIZE</em>
     * and <em>MAXIMUM_ARGS_SIZE</em>.
     *
     * @param args Array to verify.
     * @return true if the length of <em>args</em> is the expected, false otherwise.
     */
    public static boolean argumentListSizeIsValid(String... args) {
        return args.length >= MINIMUM_ARGS_SIZE && args.length <= MAXIMUM_ARGS_SIZE;
    }

    /**
     * Verifies is the String is a valid JSON object.
     *
     * @param json String representing a JSON object.
     * @return true if the given <em>json</em> is a valid JSON object, false otherwise.
     */
    public static boolean isValidJson(String json) {
        try {
            new Gson().fromJson(json, Object.class);
        } catch (JsonSyntaxException e) {
            return false;
        }

        return true;
    }
}
