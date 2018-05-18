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
}
