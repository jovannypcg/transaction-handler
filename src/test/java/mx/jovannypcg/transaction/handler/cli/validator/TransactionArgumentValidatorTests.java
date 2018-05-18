package mx.jovannypcg.transaction.handler.cli.validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionArgumentValidatorTests {
    private static final String USER_ID = "2345";
    private static final String TRANSACTION_JSON = "{ \"amount\": 1.23, \"date\":\"2018-12-30\", \"user_id\": 345 }";

    private static final String[] ARGS_FOR_ADD_TASK =
            new String[] { USER_ID, TransactionArgumentValidator.ADD_TASK, TRANSACTION_JSON };
    private static final String[] ARGS_FOR_SHOW_TASK =
            new String[] { USER_ID, UUID.randomUUID().toString() };
    private static final String[] ARGS_FOR_LIST_TASK =
            new String[] { USER_ID, TransactionArgumentValidator.LIST_TASK };
    private static final String[] ARGS_FOR_SUM_TASK =
            new String[] { USER_ID, TransactionArgumentValidator.SUM_TASK };

    private TransactionArgumentValidator transactionArgumentValidator;

    @Before
    public void init() {
        transactionArgumentValidator = new TransactionArgumentValidator();
    }

    @Test
    public void argumentListSizeIsValid_shouldReturnTrue() {
        String[] twoArgs = new String[] { USER_ID, "sum" };
        String[] threeArgs = new String[] { USER_ID, "add", "{\"user_id\": 2299ce24}" };

        transactionArgumentValidator.setArgs(ARGS_FOR_SUM_TASK);
        assertThat(transactionArgumentValidator.argumentListSizeIsValid()).isTrue();

        transactionArgumentValidator.setArgs(ARGS_FOR_ADD_TASK);
        assertThat(transactionArgumentValidator.argumentListSizeIsValid()).isTrue();
    }

    @Test
    public void argumentListSizeIsValid_shouldReturnFalse() {
        String[] oneArg = new String[] { USER_ID };
        String[] fourArgs = new String[] { USER_ID, "add", "{\"user_id\": 2299ce24}", "list" };

        transactionArgumentValidator.setArgs(oneArg);
        assertThat(transactionArgumentValidator.argumentListSizeIsValid()).isFalse();

        transactionArgumentValidator.setArgs(fourArgs);
        assertThat(transactionArgumentValidator.argumentListSizeIsValid()).isFalse();
    }

    @Test
    public void isAddTask_shouldReturnTrue() {
        transactionArgumentValidator.setArgs(ARGS_FOR_ADD_TASK);
        assertThat(transactionArgumentValidator.isAddTask()).isTrue();
    }

    @Test
    public void isAddTask_shouldReturnFalse() {
        transactionArgumentValidator.setArgs(ARGS_FOR_SUM_TASK);
        assertThat(transactionArgumentValidator.isAddTask()).isFalse();

        transactionArgumentValidator.setArgs(ARGS_FOR_LIST_TASK);
        assertThat(transactionArgumentValidator.isAddTask()).isFalse();

        transactionArgumentValidator.setArgs(ARGS_FOR_SHOW_TASK);
        assertThat(transactionArgumentValidator.isAddTask()).isFalse();
    }

    @Test
    public void isListTask_shouldReturnTrue() {
        transactionArgumentValidator.setArgs(ARGS_FOR_LIST_TASK);
        assertThat(transactionArgumentValidator.isListTask()).isTrue();
    }

    @Test
    public void isListTask_shouldReturnFalse() {
        transactionArgumentValidator.setArgs(ARGS_FOR_ADD_TASK);
        assertThat(transactionArgumentValidator.isListTask()).isFalse();

        transactionArgumentValidator.setArgs(ARGS_FOR_SHOW_TASK);
        assertThat(transactionArgumentValidator.isListTask()).isFalse();

        transactionArgumentValidator.setArgs(ARGS_FOR_SHOW_TASK);
        assertThat(transactionArgumentValidator.isListTask()).isFalse();
    }

    @Test
    public void isShowTask_shouldReturnTrue() {
        transactionArgumentValidator.setArgs(ARGS_FOR_SHOW_TASK);
        assertThat(transactionArgumentValidator.isShowTask()).isTrue();
    }

    @Test
    public void isShowTask_shouldReturnFalse() {
        transactionArgumentValidator.setArgs(ARGS_FOR_ADD_TASK);
        assertThat(transactionArgumentValidator.isShowTask()).isFalse();

        transactionArgumentValidator.setArgs(ARGS_FOR_LIST_TASK);
        assertThat(transactionArgumentValidator.isShowTask()).isFalse();

        transactionArgumentValidator.setArgs(ARGS_FOR_SUM_TASK);
        assertThat(transactionArgumentValidator.isShowTask()).isFalse();
    }

    @Test
    public void isSumTask_shouldReturnTrue() {
        transactionArgumentValidator.setArgs(ARGS_FOR_SUM_TASK);
        assertThat(transactionArgumentValidator.isSumTask()).isTrue();
    }

    @Test
    public void isSumTask_shouldReturnFalse() {
        transactionArgumentValidator.setArgs(ARGS_FOR_ADD_TASK);
        assertThat(transactionArgumentValidator.isSumTask()).isFalse();

        transactionArgumentValidator.setArgs(ARGS_FOR_SHOW_TASK);
        assertThat(transactionArgumentValidator.isSumTask()).isFalse();

        transactionArgumentValidator.setArgs(ARGS_FOR_LIST_TASK);
        assertThat(transactionArgumentValidator.isSumTask()).isFalse();
    }
}
