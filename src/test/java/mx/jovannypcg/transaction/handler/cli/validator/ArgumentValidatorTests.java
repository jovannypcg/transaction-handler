package mx.jovannypcg.transaction.handler.cli.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArgumentValidatorTests {
    @Test
    public void argumentListSizeIsValid_shouldReturnTrue() {
        String[] twoArgs = new String[] { UUID.randomUUID().toString(), "sum" };
        String[] threeArgs = new String[] { UUID.randomUUID().toString(), "add", "{\"user_id\": 2299ce24}" };

        assertThat(ArgumentValidator.argumentListSizeIsValid(twoArgs)).isTrue();
        assertThat(ArgumentValidator.argumentListSizeIsValid(threeArgs)).isTrue();
    }

    @Test
    public void argumentListSizeIsValid_shouldReturnFalse() {
        String[] oneArg = new String[] { UUID.randomUUID().toString() };
        String[] fourArgs = new String[] { UUID.randomUUID().toString(), "add", "{\"user_id\": 2299ce24}", "list" };

        assertThat(ArgumentValidator.argumentListSizeIsValid(oneArg)).isFalse();
        assertThat(ArgumentValidator.argumentListSizeIsValid(fourArgs)).isFalse();
    }

    @Test
    public void isValidJson_shouldReturnTrue() {
        String validJson = "{ \"amount\": 1.23, \"date\":\"2018-12-30\", \"user_id\": 345 }";

        assertThat(ArgumentValidator.isValidJson(validJson)).isTrue();
    }

    @Test
    public void isValidJson_shouldReturnFalse() {
        String anyString = "any string";
        String invalidJson = "{ 1.23, \"date\":\"2018-12-30\", \"user_id\": 345 }";

        assertThat(ArgumentValidator.isValidJson(anyString)).isFalse();
        assertThat(ArgumentValidator.isValidJson(invalidJson)).isFalse();
    }
}
