package mx.jovannypcg.transaction.handler.cli.config;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

@Configuration
public class TransactionFSWriterConfiguration {
    @Value("${transactions.path}")
    private String directory;

    @Value("${transactions.file}")
    private String file;

    @Bean
    public PrintWriter printWriter() throws IOException {
        File transactionFile = new File(directory + file);
        transactionFile.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(transactionFile, true);

        return new PrintWriter(fos);
    }

    @Bean
    public BufferedReader bufferedReader() throws IOException {
        File transactionFile = new File(directory + file);
        FileReader fr = new FileReader(transactionFile);

        return new BufferedReader(fr);
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
