package mx.jovannypcg.transaction.handler.cli.repository.impl;

import com.google.gson.Gson;
import mx.jovannypcg.transaction.handler.cli.domain.Transaction;
import mx.jovannypcg.transaction.handler.cli.repository.CommandRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FSCommandRepositoryTests {
    @Value("${transactions.path}")
    private String transactionsDirectory;

    @Value("${transactions.file}")
    private String transactionsFile;

    @Mock
    private PrintWriter transactionWriter;

    @Autowired
    private Gson gson;

    private CommandRepository<Transaction> repository;
    private List<Transaction> transactionsFromFS;

    @Before
    public void init() throws FileNotFoundException {
        repository = new FSCommandRepository(gson, transactionWriter, getTransactionReader());

        transactionsFromFS = getTransactionReader()
                .lines()
                .map(line -> gson.fromJson(line, Transaction.class))
                .collect(Collectors.toList());
    }

    @Test
    public void save_shouldPrintTransactionSuccessfully() {
        repository.save(transactionsFromFS.get(0));

        Mockito.verify(transactionWriter).println(anyString());
    }

    @Test
    public void findAll_shouldGetTransactions() {
        List<Transaction> transactionsFromRepository = repository.findAll();

        assertThat(transactionsFromRepository)
                .hasSameSizeAs(transactionsFromFS)
                .containsExactlyInAnyOrderElementsOf(transactionsFromFS);
    }

    @Test
    public void findByUserId_shouldGetAllRelatedTransactions() {
        final int USER_ID = 235;

        List<Transaction> expectedTransactions = transactionsFromFS
                .stream()
                .filter(transaction -> transaction.getUserId() == USER_ID)
                .collect(Collectors.toList());

        List<Transaction> transactionsFromRepository = repository.findByUserId(USER_ID);

        assertThat(transactionsFromRepository)
                .hasSameSizeAs(expectedTransactions)
                .containsExactlyInAnyOrderElementsOf(expectedTransactions);
    }

    @Test
    public void findByUserId_shouldReturnEmptyListWhenNonExistentUserId() {
        final int USER_ID = 65434543;

        List<Transaction> transactionsFromRepository = repository.findByUserId(USER_ID);

        assertThat(transactionsFromRepository).hasSize(0);
    }

    @Test
    public void findByUserIdAndTransactionId_shouldGetAllRelatedTransactions() {
        final int USER_ID = 154;
        final String TRANSACTION_ID = "07f791c0-4cd9-47cf-86de-62dc9aa481e8";

        List<Transaction> expectedTransactions = transactionsFromFS
                .stream()
                .filter(transaction -> {
                    return transaction.getUserId() == USER_ID &&
                            TRANSACTION_ID.equals(transaction.getTransactionId());
                }).collect(Collectors.toList());

        List<Transaction> transactionsFromRepository = repository.findByUserIdAndTransactionId(USER_ID, TRANSACTION_ID);

        assertThat(transactionsFromRepository)
                .hasSameSizeAs(expectedTransactions)
                .containsExactlyInAnyOrderElementsOf(expectedTransactions);
    }

    @Test
    public void findByUserIdAndTransactionId_shouldReturnEmptyList() {
        final int USER_ID = 65434543;
        final String TRANSACTION_ID = "07f791c0-4cd9-47cf-86de-62dc9aa481e8";

        List<Transaction> transactionsFromRepository = repository.findByUserIdAndTransactionId(USER_ID, TRANSACTION_ID);

        assertThat(transactionsFromRepository).hasSize(0);
    }

    @Test
    public void sumAmountsByUserId_shouldSumCorrectly() {
        final int USER_ID = 235;
        final double EXPECTED_SUM = 170.63;

        double sumFromRepository = repository.sumAmountsByUserId(USER_ID);

        assertThat(sumFromRepository).isEqualTo(EXPECTED_SUM);
    }

    @Test
    public void sumAmountsByUserId_shouldReturn0WithNonExistentUserId() {
        final int USER_ID = 432525364;
        final double EXPECTED_SUM = 0;

        double sumFromRepository = repository.sumAmountsByUserId(USER_ID);

        assertThat(sumFromRepository).isEqualTo(EXPECTED_SUM);
    }

    /**
     * Gets a instance to read from the transaction file for testing.
     *
     * @return Instance with transactions loaded.
     * @throws FileNotFoundException Thrown if anything goes wrong during reading.
     */
    private BufferedReader getTransactionReader() throws FileNotFoundException {
        File transactionFile = new File(transactionsDirectory + transactionsFile);
        FileReader fr = new FileReader(transactionFile);

        return new BufferedReader(fr);
    }
}
