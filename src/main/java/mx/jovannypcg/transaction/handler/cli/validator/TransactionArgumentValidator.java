package mx.jovannypcg.transaction.handler.cli.validator;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.springframework.stereotype.Component;

/**
 * Class to validate the arguments passed to {@link mx.jovannypcg.transaction.handler.cli.TransactionHandlerCLI}.
 *
 * This class provides a way to verify that the number of arguments is correct, the format of a given argument is
 * the expected or if a non-valid task for the CLI is passed.
 *
 * @author Jovanny Cruz
 */
public class TransactionArgumentValidator {
    /**
     * Minimum args expected: user_id and another one that might be transaction_id or
     * one of the expected tasks: list, sum, add
     */
    public static final int MINIMUM_ARGS_SIZE = 2;

    /**
     * Maximum args expected.
     * e.g.: $ transaction-handler [user_id] add [transaction_json]
     */
    public static final int MAXIMUM_ARGS_SIZE = 3;

    public static final int ADD_TASK_ARGS_SIZE = 3;
    public static final int SHOW_TASK_ARGS_SIZE = 2;
    public static final int LIST_TASK_ARGS_SIZE = 2;
    public static final int SUM_TASK_ARGS_SIZE = 2;

    public static final int TASK_INDEX_IN_ARGS = 1;
    public static final int TRANSACTION_JSON_INDEX_IN_ARGS = 2;

    public static final String ADD_TASK = "add";
    public static final String LIST_TASK = "list";
    public static final String SUM_TASK = "sum";

    /** The arguments to validate */
    private String[] args;

    public TransactionArgumentValidator(String... args) {
        this.args = args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    /**
     * Verifies if the given <em>args</em> has a length between <em>MINIMUM_ARGS_SIZE</em>
     * and <em>MAXIMUM_ARGS_SIZE</em>.
     *
     * @return true if the length of <em>args</em> is the expected, false otherwise.
     */
    public boolean argumentListSizeIsValid() {
        return args.length >= MINIMUM_ARGS_SIZE && args.length <= MAXIMUM_ARGS_SIZE;
    }

    /**
     * Verifies if the arguments correspond to the <em>add</em> task.
     *
     * @return true if the arguments match with the <em>add</em> task, false otherwise.
     */
    public boolean isAddTask() {
        return args.length == ADD_TASK_ARGS_SIZE &&
                ADD_TASK.equals(args[TASK_INDEX_IN_ARGS]) &&
                isValidJson(args[TRANSACTION_JSON_INDEX_IN_ARGS]);
    }

    /**
     * Verifies if the arguments correspond to the <em>list</em> task.
     *
     * @return true if the arguments match with the <em>list</em> task, false otherwise.
     */
    public boolean isListTask() {
        return args.length == LIST_TASK_ARGS_SIZE &&
                LIST_TASK.equals(args[TASK_INDEX_IN_ARGS]);
    }

    /**
     * Verifies if the arguments correspond to the <em>sum</em> task.
     *
     * @return true if the arguments match with the <em>sum</em> task, false otherwise.
     */
    public boolean isSumTask() {
        return args.length == SUM_TASK_ARGS_SIZE &&
                SUM_TASK.equals(args[TASK_INDEX_IN_ARGS]);
    }

    /**
     * Verifies if the arguments correspond to the <em>show</em> task.
     *
     * @return true if the arguments match with the <em>show</em> task, false otherwise.
     */
    public boolean isShowTask() {
        return args.length == SHOW_TASK_ARGS_SIZE &&
                !isListTask() &&
                !isSumTask();
    }

    /**
     * Verifies is the String is a valid JSON object.
     *
     * @param json String representing a JSON object.
     * @return true if the given <em>json</em> is a valid JSON object, false otherwise.
     */
    private boolean isValidJson(String json) {
        try {
            new Gson().fromJson(json, Object.class);
        } catch (JsonSyntaxException e) {
            return false;
        }

        return true;
    }
}
